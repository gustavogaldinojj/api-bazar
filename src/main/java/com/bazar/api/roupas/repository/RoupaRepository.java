package com.bazar.api.roupas.repository;

import com.bazar.api.roupas.model.Roupa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoupaRepository extends JpaRepository<Roupa, Long> {

    Page<Roupa> findAllByAtivoTrue(Pageable pageable);

}
