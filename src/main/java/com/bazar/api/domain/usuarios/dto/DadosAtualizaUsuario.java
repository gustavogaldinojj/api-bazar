package com.bazar.api.domain.usuarios.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaUsuario(
        @NotNull
        Long id,
        String nome,
        String email,
        String nivelUsuario,
        DadosEndereco endereco) {
}
