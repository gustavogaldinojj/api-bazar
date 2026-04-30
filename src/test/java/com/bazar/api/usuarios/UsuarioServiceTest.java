package com.bazar.api.usuarios;

import com.bazar.api.domain.roupas.dto.DadosAtualizaRoupa;
import com.bazar.api.domain.roupas.model.Roupa;
import com.bazar.api.domain.usuarios.dto.DadosAtualizaUsuario;
import com.bazar.api.domain.usuarios.dto.DadosCadastroUsuario;
import com.bazar.api.domain.usuarios.dto.DadosEndereco;
import com.bazar.api.domain.usuarios.model.Usuario;
import com.bazar.api.domain.usuarios.repository.UsuarioRepository;
import com.bazar.api.domain.usuarios.service.UsuarioService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    // ─── helper para criar DTO sem repetição ────────────────────────────────
    private DadosCadastroUsuario dadosCadastroUsuario(){
        return new DadosCadastroUsuario("Gustavo", "12345", "gustavogaldino@gmail.com", "Atendente",
                new DadosEndereco("Morada do sol", "Sol Nascente", "05281110", "São Paulo", "SP", "casa", "31"));
    }

    @Test
    @DisplayName("cadastrar: deve salvar e retornar a usuario")
    void deveSalvarUsuario(){
        var dados = dadosCadastroUsuario();
        var usuarioSalvo = new Usuario(dados);
        when(repository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        var resultado = service.cadastrar(dados);

        assertNotNull(resultado);
        assertEquals("Gustavo", resultado.getNome());
        verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("listar: deve retornar page de usuarios ativos")
    void deveRetornarPaginaDeUsuarioAtivas() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Usuario> paginaEsperada = new PageImpl<>(List.of(new Usuario(dadosCadastroUsuario())));
        when(repository.findAllByAtivoTrue(pageable)).thenReturn(paginaEsperada);

        var resultado = service.listar(pageable);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.getTotalElements());
        verify(repository).findAllByAtivoTrue(pageable);
    }

    @Test
    @DisplayName("detalhar: deve retornar usuario quando id existe e está ativa")
    void deveRetornarUsuarioPorId() {
        var usuario = new Usuario(dadosCadastroUsuario());
        when(repository.findByIdAndAtivoTrue(1L)).thenReturn(Optional.of(usuario));

        var resultado = service.detalhar(1L);

        assertNotNull(resultado);
        assertEquals("Gustavo", resultado.getNome());
    }

    @Test
    @DisplayName("detalhar: deve lançar RuntimeException quando usuario não encontrada ou inativa")
    void deveLancarExcecaoQuandoUsuarioNaoEncontrada() {
        when(repository.findByIdAndAtivoTrue(99L)).thenReturn(Optional.empty());

        var ex = assertThrows(RuntimeException.class, () -> service.detalhar(99L));

        assertEquals("Usuário não encontrado ou inativo", ex.getMessage());
    }

    @Test
    @DisplayName("atualizar: deve chamar usuario.atualizarInformacoes() com os dados recebidos")
    void deveAtualizarUsuarioComSucesso() {
        var usuarioMock = mock(Usuario.class);
        var dados = new DadosAtualizaUsuario("Gustavo", "gustavoalves@gmail.com", "atendendte",
                new DadosEndereco("Morada do sol", "Sol Nascente", "05281110", "barabas", "SP", "casa", "31"));
        when(repository.getReferenceById(1L)).thenReturn(usuarioMock);

        service.atualizar(1L, dados);

        verify(usuarioMock, times(1)).atualizarInformacoes(dados);
    }

    @Test
    @DisplayName("excluir: deve chamar roupa.excluir() (soft delete, não remove do banco)")
    void deveExecutarSoftDelete() {
        var usuarioMock = mock(Usuario.class);
        when(repository.getReferenceById(1L)).thenReturn(usuarioMock);

        service.excluir(1L);

        verify(usuarioMock, times(1)).excluir();
        verify(repository, never()).delete(any());
    }

}
