package com.bazar.api.domain.movimentacao.dto;

import com.bazar.api.domain.movimentacao.model.TipoMovimentacao;

public record DadosMovimentacao(
        Long roupaId,
        TipoMovimentacao tipo,
        Integer quantidade
) {
}
