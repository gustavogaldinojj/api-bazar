package com.bazar.api.domain.movimentacao.dto;

import com.bazar.api.domain.movimentacao.model.Movimentacao;
import com.bazar.api.domain.movimentacao.model.TipoMovimentacao;

import java.time.LocalDateTime;

public record DadosListarMovimentacao(
        Long id,
        String nomeProduto,
        TipoMovimentacao tipo,
        Integer quantidade,
        LocalDateTime data
) {
    public DadosListarMovimentacao(Movimentacao dados){
        this(dados.getId(), dados.getRoupa().getNome(), dados.getTipo(), dados.getQuantidade(), dados.getData());
    }
}
