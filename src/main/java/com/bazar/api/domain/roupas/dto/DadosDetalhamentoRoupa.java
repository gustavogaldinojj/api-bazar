package com.bazar.api.domain.roupas.dto;

import com.bazar.api.domain.roupas.model.Roupa;

import java.math.BigDecimal;

public record DadosDetalhamentoRoupa(
        Long id,
        String nome,
        String categoria,
        String tamanho,
        String cor,
        BigDecimal preco,
        Integer quantidade
) {
    public DadosDetalhamentoRoupa(Roupa roupa){
        this(roupa.getId(), roupa.getNome(), roupa.getCategoria() ,roupa.getTamanho(), roupa.getCor(), roupa.getPreco(), roupa.getQuantidade());
    }
}
