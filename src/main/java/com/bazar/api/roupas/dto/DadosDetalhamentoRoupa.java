package com.bazar.api.roupas.dto;

import com.bazar.api.roupas.model.Roupa;

import java.math.BigDecimal;

public record DadosDetalhamentoRoupa(
        String nome,
        String tamanho,
        String cor,
        BigDecimal preco,
        Integer quantidade
) {
    public DadosDetalhamentoRoupa(Roupa roupa){
        this(roupa.getNome(), roupa.getTamanho(), roupa.getCor(), roupa.getPreco(), roupa.getQuantidade());
    }
}
