package com.hemera.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hemera.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}

