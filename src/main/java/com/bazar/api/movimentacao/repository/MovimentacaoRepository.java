package com.bazar.api.movimentacao.repository;

import com.bazar.api.movimentacao.model.Movimentacao;
import com.bazar.api.movimentacao.model.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByRoupa_Id(Long roupaId);

    List<Movimentacao> findAllByOrderByDataDesc();

    List<Movimentacao> findByRoupa_IdOrderByDataDesc(Long roupaId);

    List<Movimentacao> findByTipo(TipoMovimentacao tipo);

    @Query("""
        SELECT COALESCE(SUM(m.quantidade), 0)
        FROM Movimentacao m
        WHERE m.tipo = :tipo
    """)
    Integer totalVendido(@Param("tipo") TipoMovimentacao tipo);

    @Query("""
        SELECT COALESCE(SUM(m.quantidade), 0)
        FROM Movimentacao m
        WHERE m.tipo = :tipo
        AND m.data BETWEEN :inicio AND :fim
    """)
    Integer totalVendidoPorPeriodo(
            @Param("tipo") TipoMovimentacao tipo,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );

    @Query("""
        SELECT COALESCE(SUM(m.quantidade), 0)
        FROM Movimentacao m
        WHERE m.tipo = :tipo
        AND m.roupa.id = :roupaId
    """)
    Integer totalVendidoPorRoupa(
            @Param("tipo") TipoMovimentacao tipo,
            @Param("roupaId") Long roupaId
    );

}
