package com.bazar.api.domain.roupas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DadosCadastroRoupa(

        @NotBlank
        String nome,
        @NotBlank
        String tamanho,
        @NotBlank
        String cor,
        @NotNull
        @Positive
        BigDecimal preco,
        Integer quantidade
) {
}
