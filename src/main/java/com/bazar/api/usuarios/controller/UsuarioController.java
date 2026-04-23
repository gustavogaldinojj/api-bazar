package com.bazar.api.usuarios.controller;

import com.bazar.api.usuarios.dto.DadosCadastroUsuario;
import com.bazar.api.usuarios.dto.DadosDetalhamentoUsuario;
import com.bazar.api.usuarios.dto.DadosListaUsuario;
import com.bazar.api.usuarios.model.Usuario;
import com.bazar.api.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(
            @RequestBody
            @Valid
            DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder
    ){
        var usuario = new Usuario(dados);
        repository.save(usuario);

        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListaUsuario>> listar(
        @PageableDefault(size = 10, sort = "nome")Pageable pageable){

        var page = repository.findAllByAtivoTrue(pageable).map(DadosListaUsuario::new);

        return ResponseEntity.ok(page);
    }

}
