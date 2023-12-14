package com.mateus.henrique.demoparkapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateus.henrique.demoparkapi.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  
}
