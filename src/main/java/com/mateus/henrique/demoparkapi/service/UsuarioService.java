package com.mateus.henrique.demoparkapi.service;

import org.springframework.stereotype.Service;

import com.mateus.henrique.demoparkapi.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;

}
