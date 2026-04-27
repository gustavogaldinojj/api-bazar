package com.bazar.api.domain.roupas.dto;

import java.math.BigDecimal;

public record DadosAtualizaRoupa(
        Long id,
        String nome,
        String tamanho,
        String cor,
        BigDecimal preco,
        Integer quantidade
) {
}
