package com.bazar.api.auth.controller;

import com.bazar.api.auth.dto.DadosLogin;
import com.bazar.api.usuarios.dto.DadosDetalhamentoUsuario;
import com.bazar.api.usuarios.repository.UsuarioRepository;
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
    private UsuarioRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody DadosLogin dados){

        var usuario = repository.findByEmail(dados.email())
                .orElse(null);

        if(usuario == null){
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        if(!usuario.getSenha().equals(dados.senha())){
            return ResponseEntity.status(401).body("Senha inválida");
        }

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }
}
