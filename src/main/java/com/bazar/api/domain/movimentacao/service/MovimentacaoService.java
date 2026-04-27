package com.bazar.api.domain.movimentacao.service;

import com.bazar.api.domain.movimentacao.model.Movimentacao;
import com.bazar.api.domain.movimentacao.model.TipoMovimentacao;
import com.bazar.api.domain.movimentacao.repository.MovimentacaoRepository;
import com.bazar.api.domain.roupas.model.Roupa;
import com.bazar.api.domain.roupas.repository.RoupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    RoupaRepository roupaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    public Movimentacao movimentacao(Long roupaId, TipoMovimentacao tipo, Integer quantidade){

        Roupa roupa = roupaRepository.findById(roupaId)
                .orElseThrow(() -> new RuntimeException("Roupa não encontrada"));

        if (tipo == TipoMovimentacao.SAIDA) {
            if (roupa.getQuantidade() < quantidade) {
                throw new RuntimeException("Estoque insuficiente");
            }
            roupa.setQuantidade(roupa.getQuantidade() - quantidade);
        } else {
            roupa.setQuantidade(roupa.getQuantidade() + quantidade);
        }

        Movimentacao mov = new Movimentacao();
        mov.setRoupa(roupa);
        mov.setTipo(tipo);
        mov.setQuantidade(quantidade);
        mov.setData(LocalDateTime.now());

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

}
