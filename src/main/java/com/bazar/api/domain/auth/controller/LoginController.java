package com.bazar.api.domain.auth.controller;

import com.bazar.api.domain.auth.dto.DadosLogin;
import com.bazar.api.domain.auth.service.LoginService;
import com.bazar.api.domain.usuarios.dto.DadosDetalhamentoUsuario;
import com.bazar.api.domain.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody DadosLogin dados){

        try {
            var token = service.autenticar(dados);

            return ResponseEntity.ok(token);

        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
