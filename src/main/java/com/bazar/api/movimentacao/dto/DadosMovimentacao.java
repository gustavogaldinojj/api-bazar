package com.bazar.api.movimentacao.dto;

import com.bazar.api.movimentacao.model.TipoMovimentacao;

public record DadosMovimentacao(
        Long roupaId,
        TipoMovimentacao tipo,
        Integer quantidade
) {
}
