package com.bazar.api.domain.usuarios.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank
        String senha,
        @NotBlank
        @Email
        String email,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
