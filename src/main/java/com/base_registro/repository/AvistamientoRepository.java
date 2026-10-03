package com.base_registro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base_registro.model.AvistamientoModel;

public interface AvistamientoRepository extends JpaRepository<AvistamientoModel, Long> {
}
