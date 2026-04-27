package com.bazar.api.usuarios.controller;

import com.bazar.api.usuarios.dto.DadosAtualizaUsuario;
import com.bazar.api.usuarios.dto.DadosCadastroUsuario;
import com.bazar.api.usuarios.dto.DadosDetalhamentoUsuario;
import com.bazar.api.usuarios.dto.DadosListaUsuario;
import com.bazar.api.usuarios.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody @Valid DadosCadastroUsuario dados,
            UriComponentsBuilder uriBuilder
    ){
        var usuario = service.cadastrar(dados);

        var uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaUsuario>> listar(
            @ParameterObject
            @PageableDefault(size = 10, sort = "nome") Pageable pageable
    ){
        var page = service.listar(pageable)
                .map(DadosListaUsuario::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var usuario = service.detalhar(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizaUsuario dados
    ){
        var usuario = service.atualizar(id, dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}