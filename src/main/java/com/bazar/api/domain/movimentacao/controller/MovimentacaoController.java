package com.bazar.api.domain.movimentacao.controller;

import com.bazar.api.domain.movimentacao.dto.DadosMovimentacao;
import com.bazar.api.domain.movimentacao.model.Movimentacao;
import com.bazar.api.domain.movimentacao.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Movimentacao>> buscar(){
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/roupa/{id}")
    public ResponseEntity<List<Movimentacao>> listarPorRoupa(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorRoupa(id));
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
}
