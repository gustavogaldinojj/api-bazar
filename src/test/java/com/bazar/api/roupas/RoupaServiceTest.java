// src/test/java/com/bazar/api/domain/roupas/service/RoupaServiceTest.java
package com.bazar.api.roupas;

import com.bazar.api.domain.roupas.dto.DadosAtualizaRoupa;
import com.bazar.api.domain.roupas.dto.DadosCadastroRoupa;
import com.bazar.api.domain.roupas.model.Roupa;
import com.bazar.api.domain.roupas.repository.RoupaRepository;
import com.bazar.api.domain.roupas.service.RoupaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoupaServiceTest {

    @Mock
    private RoupaRepository repository;

    @InjectMocks
    private RoupaService service;

    // ─── helper para criar DTO sem repetição ────────────────────────────────
    private DadosCadastroRoupa dadosValidos() {
        return new DadosCadastroRoupa("Camisa", "M", "Azul", new BigDecimal("59.90"), 10);
    }

    @Test
    @DisplayName("cadastrar: deve salvar e retornar a roupa")
    void deveSalvarRoupaComSucesso() {
        var dados = dadosValidos();
        var roupaSalva = new Roupa(dados);
        when(repository.save(any(Roupa.class))).thenReturn(roupaSalva);

        var resultado = service.cadastrar(dados);

        assertNotNull(resultado);
        assertEquals("Camisa", resultado.getNome());
        verify(repository, times(1)).save(any(Roupa.class));
    }

    @Test
    @DisplayName("listar: deve retornar page de roupas ativas")
    void deveRetornarPaginaDeRoupasAtivas() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Roupa> paginaEsperada = new PageImpl<>(List.of(new Roupa(dadosValidos())));
        when(repository.findAllByAtivoTrue(pageable)).thenReturn(paginaEsperada);

        var resultado = service.listar(pageable);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.getTotalElements());
        verify(repository).findAllByAtivoTrue(pageable);
    }

    @Test
    @DisplayName("detalhar: deve retornar roupa quando id existe e está ativa")
    void deveRetornarRoupaPorId() {
        var roupa = new Roupa(dadosValidos());
        when(repository.findByIdAndAtivoTrue(1L)).thenReturn(Optional.of(roupa));

        var resultado = service.detalhar(1L);

        assertNotNull(resultado);
        assertEquals("Camisa", resultado.getNome());
    }

    @Test
    @DisplayName("detalhar: deve lançar RuntimeException quando roupa não encontrada ou inativa")
    void deveLancarExcecaoQuandoRoupaNaoEncontrada() {
        when(repository.findByIdAndAtivoTrue(99L)).thenReturn(Optional.empty());

        var ex = assertThrows(RuntimeException.class, () -> service.detalhar(99L));

        assertEquals("Roupa não encontrada ou inativa", ex.getMessage());
    }

    @Test
    @DisplayName("atualizar: deve chamar roupa.atualizar() com os dados recebidos")
    void deveAtualizarRoupaComSucesso() {
        var roupaMock = mock(Roupa.class);
        var dados = new DadosAtualizaRoupa(1L, "Calça", "G", "Preta", new BigDecimal("89.90"), 2);
        when(repository.getReferenceById(1L)).thenReturn(roupaMock);

        service.atualizar(1L, dados);

        verify(roupaMock, times(1)).atualizar(dados);   // confirma que o método foi chamado
    }

    @Test
    @DisplayName("excluir: deve chamar roupa.excluir() (soft delete, não remove do banco)")
    void deveExecutarSoftDelete() {
        var roupaMock = mock(Roupa.class);
        when(repository.getReferenceById(1L)).thenReturn(roupaMock);

        service.excluir(1L);

        verify(roupaMock, times(1)).excluir();          // garante soft delete
        verify(repository, never()).delete(any());       // garante que NÃO deletou fisicamente
    }
}