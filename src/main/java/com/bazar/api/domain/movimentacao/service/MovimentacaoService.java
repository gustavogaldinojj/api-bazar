package com.bazar.api.domain.movimentacao.service;

import com.bazar.api.domain.movimentacao.dto.DadosGrafico;
import com.bazar.api.domain.movimentacao.model.AgrupamentoGrafico;
import com.bazar.api.domain.movimentacao.model.Movimentacao;
import com.bazar.api.domain.movimentacao.model.TipoGrafico;
import com.bazar.api.domain.movimentacao.model.TipoMovimentacao;
import com.bazar.api.domain.movimentacao.repository.MovimentacaoRepository;
import com.bazar.api.domain.roupas.model.Roupa;
import com.bazar.api.domain.roupas.repository.RoupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    RoupaRepository roupaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public Movimentacao movimentacao(Long roupaId, TipoMovimentacao tipo, Integer quantidade){

        Roupa roupa = roupaRepository.findById(roupaId)
                .orElseThrow(() -> new RuntimeException("Roupa não encontrada"));

        if (tipo == TipoMovimentacao.SAIDA) {
            if (roupa.getQuantidade() < quantidade) {
                throw new RuntimeException("Estoque insuficiente");
            }
            roupa.setQuantidade(roupa.getQuantidade() - quantidade);
            if (roupa.getQuantidade() <= 0) {
                roupa.excluir(); // chama o método já existente na entidade que seta ativo = false
            }
        } else {
            roupa.setQuantidade(roupa.getQuantidade() + quantidade);

            if (!roupa.getAtivo()) {
                roupa.setAtivo(true);
            }
        }

        Movimentacao mov = new Movimentacao();
        mov.setRoupa(roupa);
        mov.setTipo(tipo);
        mov.setQuantidade(quantidade);
        mov.setData(LocalDateTime.now());
        mov.setValorUnitario(roupa.getPreco());

        roupaRepository.save(roupa);
        return movimentacaoRepository.save(mov);
    }

    public List<Movimentacao> listarTodas(){
        return movimentacaoRepository.findAllByOrderByDataDesc();
    }

    public List<Movimentacao> listarPorRoupa(Long roupaId) {
        return movimentacaoRepository.findByRoupa_IdOrderByDataDesc(roupaId);
    }

    public Integer totalVendido() {
        return movimentacaoRepository.totalVendido(TipoMovimentacao.SAIDA);
    }

    public Integer totalVendidoPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return movimentacaoRepository.totalVendidoPorPeriodo(
                TipoMovimentacao.SAIDA, inicio, fim
        );
    }

    public Integer totalVendidoPorRoupa(Long roupaId) {
        return movimentacaoRepository.totalVendidoPorRoupa(
                TipoMovimentacao.SAIDA, roupaId
        );
    }

    public BigDecimal faturamentoTotal() {
        return movimentacaoRepository.faturamentoTotal(TipoMovimentacao.SAIDA);
    }

    public BigDecimal faturamentoPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return movimentacaoRepository.faturamentoPorPeriodo(TipoMovimentacao.SAIDA, inicio, fim);
    }

    public BigDecimal faturamentoPorRoupa(Long roupaId) {
        return movimentacaoRepository.faturamentoPorRoupa(TipoMovimentacao.SAIDA, roupaId);
    }

    public List<DadosGrafico> gerarGrafico(
            TipoGrafico tipo,
            AgrupamentoGrafico agrupamento,
            LocalDateTime inicio,
            LocalDateTime fim
    ) {

        return switch (tipo) {

            case VENDAS -> gerarGraficoVendas(
                    agrupamento,
                    inicio,
                    fim
            );

            case FATURAMENTO -> gerarGraficoFaturamento(
                    agrupamento,
                    inicio,
                    fim
            );
        };
    }

    private List<DadosGrafico> gerarGraficoVendas(
            AgrupamentoGrafico agrupamento,
            LocalDateTime inicio,
            LocalDateTime fim
    ) {

        return switch (agrupamento) {

            case DIA -> movimentacaoRepository.vendasPorDia(
                    TipoMovimentacao.SAIDA,
                    inicio,
                    fim
            );

            case MES -> movimentacaoRepository.vendasPorMes(
                    TipoMovimentacao.SAIDA
            );
        };
    }

    private List<DadosGrafico> gerarGraficoFaturamento(
            AgrupamentoGrafico agrupamento,
            LocalDateTime inicio,
            LocalDateTime fim
    ) {

        return switch (agrupamento) {

            case DIA -> movimentacaoRepository.faturamentoPorDia(
                    TipoMovimentacao.SAIDA,
                    inicio,
                    fim
            );

            case MES -> movimentacaoRepository.faturamentoPorMes(
                    TipoMovimentacao.SAIDA
            );
        };
    }
}
