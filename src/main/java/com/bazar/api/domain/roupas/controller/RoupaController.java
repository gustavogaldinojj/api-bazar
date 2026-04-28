package com.bazar.api.domain.roupas.controller;

import com.bazar.api.domain.roupas.dto.DadosAtualizaRoupa;
import com.bazar.api.domain.roupas.dto.DadosCadastroRoupa;
import com.bazar.api.domain.roupas.dto.DadosDetalhamentoRoupa;
import com.bazar.api.domain.roupas.dto.DadosListarRoupa;
import com.bazar.api.domain.roupas.service.RoupaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/roupas")
public class RoupaController {

    @Autowired
    private RoupaService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarRoupa(
            @RequestBody @Valid DadosCadastroRoupa dados,
            UriComponentsBuilder uriBuilder) {

        var roupa = service.cadastrar(dados);

        var uri = uriBuilder.path("/roupas/{id}")
                .buildAndExpand(roupa.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoRoupa(roupa));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListarRoupa>> listarRoupa(
            @ParameterObject
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        var page = service.listar(pageable)
                .map(DadosListarRoupa::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarRoupa(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizaRoupa dados){

        var roupa = service.atualizar(id, dados);

        return ResponseEntity.ok(new DadosDetalhamentoRoupa(roupa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirRoupa(@PathVariable Long id){

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
