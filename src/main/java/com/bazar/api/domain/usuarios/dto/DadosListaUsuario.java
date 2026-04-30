package com.bazar.api.domain.usuarios.dto;

import com.bazar.api.domain.usuarios.model.Endereco;
import com.bazar.api.domain.usuarios.model.Usuario;

public record DadosListaUsuario(Long id, String nome, String senha, String email, String nivelUsuario, Endereco endereco) {

    public DadosListaUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getEmail(), usuario.getNivelUsuario(), usuario.getEndereco());
    }
}
