package com.mateus.henrique.demoparkapi.service;

import org.springframework.stereotype.Service;

import com.mateus.henrique.demoparkapi.entity.Usuario;
import com.mateus.henrique.demoparkapi.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;

  @Transactional
  public Usuario salvar(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

}
