package com.bazar.api.domain.usuarios.repository;

import com.bazar.api.domain.usuarios.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
    Optional<Usuario> findByIdAndAtivoTrue(Long id);
    Optional<Usuario> findByEmail(String email);
}
