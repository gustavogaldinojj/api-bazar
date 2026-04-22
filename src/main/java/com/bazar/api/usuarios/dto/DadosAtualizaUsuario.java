package com.bazar.api.usuarios.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaUsuario(
        @NotNull
        Long id,
        String nome,
        String email,
        DadosEndereco endereco) {
}
