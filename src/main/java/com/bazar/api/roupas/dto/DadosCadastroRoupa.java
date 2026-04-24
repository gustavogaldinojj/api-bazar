package com.bazar.api.roupas.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record DadosCadastroRoupa(

        @NotBlank
        String nome,
        @NotBlank
        String tamanho,
        @NotBlank
        String cor,
        @NotBlank
        BigDecimal preco,
        @NotBlank
        Integer quantidade
) {
}
