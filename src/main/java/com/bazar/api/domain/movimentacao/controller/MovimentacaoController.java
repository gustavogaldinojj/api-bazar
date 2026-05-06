package com.bazar.api.domain.movimentacao.controller;

import com.bazar.api.domain.movimentacao.dto.DadosGrafico;
import com.bazar.api.domain.movimentacao.dto.DadosListarMovimentacao;
import com.bazar.api.domain.movimentacao.dto.DadosMovimentacao;
import com.bazar.api.domain.movimentacao.model.Movimentacao;
import com.bazar.api.domain.movimentacao.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService service;

    @PostMapping
    public ResponseEntity movimentar(@RequestBody DadosMovimentacao dados){

        Movimentacao mov = service.movimentacao(
                dados.roupaId(),
                dados.tipo(),
                dados.quantidade()
        );

        return ResponseEntity.ok(mov);
    }

    @GetMapping
    public ResponseEntity<List<DadosListarMovimentacao>> buscar(){
        var lista = service.listarTodas()
                .stream()
                .map(DadosListarMovimentacao::new)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/roupa/{id}")
    public ResponseEntity<List<DadosListarMovimentacao>> listarPorRoupa(@PathVariable Long id) {

        var lista = service.listarPorRoupa(id)
                .stream()
                .map(DadosListarMovimentacao::new)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/relatorios/total-vendido")
    public ResponseEntity<Integer> totalVendido() {
        return ResponseEntity.ok(service.totalVendido());
    }

    @GetMapping("/relatorios/por-periodo")
    public ResponseEntity<Integer> totalPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        return ResponseEntity.ok(service.totalVendidoPorPeriodo(inicio, fim));
    }

    @GetMapping("/relatorios/por-roupa/{id}")
    public ResponseEntity<Integer> totalPorRoupa(@PathVariable Long id) {
        return ResponseEntity.ok(service.totalVendidoPorRoupa(id));
    }

    @GetMapping("/relatorios/faturamento")
    public ResponseEntity<BigDecimal> faturamentoTotal() {
        return ResponseEntity.ok(service.faturamentoTotal());
    }

    @GetMapping("/relatorios/faturamento/por-periodo")
    public ResponseEntity<BigDecimal> faturamentoPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        return ResponseEntity.ok(service.faturamentoPorPeriodo(inicio, fim));
    }

    @GetMapping("/relatorios/faturamento/por-roupa/{id}")
    public ResponseEntity<BigDecimal> faturamentoPorRoupa(@PathVariable Long id) {
        return ResponseEntity.ok(service.faturamentoPorRoupa(id));
    }

    @GetMapping("/relatorios/vendas-por-mes")
    public ResponseEntity<List<DadosGrafico>> vendasPorMes() {
        return ResponseEntity.ok(service.vendasPorMes());
    }
}
