package com.caraveo.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caraveo.crud.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
	Optional<Prestamo> findById(String id);
}