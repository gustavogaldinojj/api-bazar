package com.bazar.api.roupas.dto;

import com.bazar.api.roupas.model.Roupa;

import java.math.BigDecimal;

public record DadosListarRoupa(
        String nome,
        String tamanho,
        String cor,
        BigDecimal preco,
        Integer quantidade
) {
    public DadosListarRoupa(Roupa roupa){
        this(roupa.getNome(), roupa.getTamanho(), roupa.getCor(), roupa.getPreco(), roupa.getQuantidade());
    }
}
