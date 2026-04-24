package com.bazar.api.roupas.model;

import com.bazar.api.roupas.dto.DadosAtualizaRoupa;
import com.bazar.api.roupas.dto.DadosCadastroRoupa;
import com.bazar.api.usuarios.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roupas")
public class Roupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tamanho;
    private String cor;
    private BigDecimal preco;

    private Integer quantidade;

    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Roupa(DadosCadastroRoupa dados) {
        this.nome = dados.nome();
        this.tamanho = dados.tamanho();
        this.cor = dados.cor();
        this.preco = dados.preco();
        this.quantidade = dados.quantidade();
        this.ativo = true;
    }

    public void atualizar(DadosAtualizaRoupa dados) {
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.tamanho() != null) this.tamanho = dados.tamanho();
        if (dados.cor() != null) this.cor = dados.cor();
        if (dados.preco() != null) this.preco = dados.preco();
        if (dados.quantidade() != null) this.quantidade = dados.quantidade();
    }

    public void excluir() {
        this.ativo = false;
    }
}
