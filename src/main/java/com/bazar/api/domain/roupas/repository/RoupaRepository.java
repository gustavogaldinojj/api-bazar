package com.bazar.api.domain.roupas.repository;

import com.bazar.api.domain.roupas.model.Roupa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoupaRepository extends JpaRepository<Roupa, Long> {

    Page<Roupa> findAllByAtivoTrue(Pageable pageable);
    Optional<Roupa> findByIdAndAtivoTrue(Long id);
}
