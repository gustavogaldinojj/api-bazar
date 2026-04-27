package com.bazar.api.domain.usuarios.dto;

import com.bazar.api.domain.usuarios.model.Endereco;
import com.bazar.api.domain.usuarios.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String nome,
        String senha,
        String email,
        Endereco endereco) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getSenha(),  usuario.getEmail(), usuario.getEndereco());
    }
}
