package com.bazar.api.usuarios.dto;

import com.bazar.api.usuarios.model.Endereco;
import com.bazar.api.usuarios.model.Usuario;

public record DadosListaUsuario(Long id, String nome, String senha, String email, Endereco endereco) {

    public DadosListaUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getSenha(), usuario.getNome(), usuario.getEmail(), usuario.getEndereco());
    }
}
