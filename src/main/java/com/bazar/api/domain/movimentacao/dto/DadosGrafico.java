package com.bazar.api.domain.movimentacao.dto;

import java.math.BigDecimal;

public record DadosGrafico(

        String periodo,
        BigDecimal total
) {
}
