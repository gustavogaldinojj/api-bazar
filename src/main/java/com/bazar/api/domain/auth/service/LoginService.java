package com.bazar.api.domain.auth.service;

import com.bazar.api.domain.auth.dto.DadosLogin;
import com.bazar.api.domain.usuarios.model.Usuario;
import com.bazar.api.domain.usuarios.repository.UsuarioRepository;
import com.bazar.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String autenticar(DadosLogin dados) {

        var usuario = repository.findByEmail(dados.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(dados.senha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        // 🔥 GERA O TOKEN AQUI
        return tokenService.gerarToken(usuario);
    }
}
