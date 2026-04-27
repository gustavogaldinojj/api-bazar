package com.bazar.api.usuarios.service;

import com.bazar.api.usuarios.dto.DadosAtualizaUsuario;
import com.bazar.api.usuarios.dto.DadosCadastroUsuario;
import com.bazar.api.usuarios.model.Usuario;
import com.bazar.api.usuarios.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrar(DadosCadastroUsuario dados) {
        var usuario = new Usuario(dados);
        return repository.save(usuario);
    }

    public Page<Usuario> listar(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable);
    }

    public Usuario detalhar(Long id) {
        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado ou inativo"));
    }

    public Usuario atualizar(Long id, DadosAtualizaUsuario dados) {
        var usuario = repository.getReferenceById(id);
        usuario.atualizarInformacoes(dados);
        return usuario;
    }

    public void excluir(Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.excluir();
    }
}
