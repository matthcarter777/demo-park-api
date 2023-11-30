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
  public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmarSenha) {
    

    if(!novaSenha.equals(confirmarSenha)) {
      throw new RuntimeException("Nova senha não confere com confirmação de senha");
    }

    Usuario user = buscarPorId(id);
    
    if (!user.getPassword().equals(senhaAtual)) {
       throw new RuntimeException("Sua senha não confere.");
    }

    user.setPassword(novaSenha);

    return user;
  }

  @Transactional
  public List<Usuario> listarTodos() {
    return usuarioRepository.findAll();
  }
}
