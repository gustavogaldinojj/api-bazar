package com.bazar.api.usuarios.dto;

import com.bazar.api.usuarios.model.Endereco;
import com.bazar.api.usuarios.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String nome,
        String senha,
        String email,
        Endereco endereco) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getEndereco());
    }
}
