package com.caraveo.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caraveo.crud.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	Optional<Cliente> findById(String id);
}