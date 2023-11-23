package com.mateus.henrique.demoparkapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateus.henrique.demoparkapi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  
}
