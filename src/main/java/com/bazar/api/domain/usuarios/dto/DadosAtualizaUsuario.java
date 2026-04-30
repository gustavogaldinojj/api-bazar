package com.bazar.api.domain.usuarios.dto;

public record DadosAtualizaUsuario(
        String nome,
        String email,
        String nivelUsuario,
        DadosEndereco endereco) {
}
