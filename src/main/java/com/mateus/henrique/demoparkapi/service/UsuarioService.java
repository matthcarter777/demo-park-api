package com.mateus.henrique.demoparkapi.service;

import java.util.List;

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

  @Transactional
  public Usuario buscarPorId(Long id) {
    return usuarioRepository.findById(id).orElseThrow(
      () -> new RuntimeException("Usuario não encontrado")
    );
  }

  @Transactional
  public Usuario editarSenha(Long id, String password) {
    Usuario user = buscarPorId(id);

    user.setPassword(password);

    return user;
  }

  @Transactional
  public List<Usuario> listarTodos() {
    return usuarioRepository.findAll();
  }
}
