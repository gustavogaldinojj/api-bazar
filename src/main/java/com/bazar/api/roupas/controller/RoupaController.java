package com.bazar.api.roupas.controller;

import com.bazar.api.roupas.dto.DadosCadastroRoupa;
import com.bazar.api.roupas.dto.DadosDetalhamentoRoupa;
import com.bazar.api.roupas.dto.DadosListarRoupa;
import com.bazar.api.roupas.model.Roupa;
import com.bazar.api.roupas.repository.RoupaRepository;
import com.bazar.api.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roupas")
public class RoupaController {

    @Autowired
    private RoupaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRoupa dados) {

        var roupa = new Roupa(dados);
        repository.save(roupa);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListarRoupa>> listar(Pageable pageable) {

        var page = repository.findAllByAtivoTrue(pageable)
                .map(DadosListarRoupa::new);

        return ResponseEntity.ok(page);
    }

}
