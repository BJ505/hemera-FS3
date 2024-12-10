package com.hemera.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hemera.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
