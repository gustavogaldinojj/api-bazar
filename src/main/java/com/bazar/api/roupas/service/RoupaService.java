package com.bazar.api.roupas.service;

import com.bazar.api.roupas.dto.DadosAtualizaRoupa;
import com.bazar.api.roupas.dto.DadosCadastroRoupa;
import com.bazar.api.roupas.model.Roupa;
import com.bazar.api.roupas.repository.RoupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoupaService {

    @Autowired
    RoupaRepository repository;

    public Roupa cadastrar(DadosCadastroRoupa dados) {
        var roupa = new Roupa(dados);
        return repository.save(roupa);
    }

    public Page<Roupa> listar(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable);
    }

    public Roupa detalhar(Long id) {
        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Roupa não encontrada ou inativa"));
    }

    public Roupa atualizar(Long id, DadosAtualizaRoupa dados) {
        var roupa = repository.getReferenceById(id);
        roupa.atualizar(dados);
        return roupa;
    }

    public void excluir(Long id) {
        var roupa = repository.getReferenceById(id);
        roupa.excluir();
    }
}
