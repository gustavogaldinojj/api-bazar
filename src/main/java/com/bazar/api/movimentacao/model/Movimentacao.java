package com.bazar.api.movimentacao.model;

import com.bazar.api.roupas.model.Roupa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "roupa_id")
    private Roupa roupa;

    @Enumerated(EnumType.STRING)
    TipoMovimentacao tipo;

    private Integer quantidade;
    private LocalDateTime data;
    private BigDecimal valorUnitario;
}
