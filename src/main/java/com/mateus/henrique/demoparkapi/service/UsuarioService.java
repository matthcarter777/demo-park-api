package com.mateus.henrique.demoparkapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mateus.henrique.demoparkapi.entity.Usuario;
import com.mateus.henrique.demoparkapi.entity.Usuario.Role;
import com.mateus.henrique.demoparkapi.jwt.JwtUserDetails;
import com.mateus.henrique.demoparkapi.repository.UsuarioRepository;
import com.mateus.henrique.demoparkapi.web.exception.EntityNotFoundException;
import com.mateus.henrique.demoparkapi.web.exception.UsernameUniqueViolationException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;

  @Transactional
  public Usuario salvar(Usuario usuario) {
    try {
      return usuarioRepository.save(usuario);
    } catch (org.springframework.dao.DataIntegrityViolationException ex) {
      throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado", usuario.getUsername()));
    }
  }

  @Transactional
  public Usuario buscarPorId(Long id) {
    return usuarioRepository.findById(id).orElseThrow(
      () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id))
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

  @Transactional
  public Usuario buscarPorUsername(String username) {
    return usuarioRepository.findUserByUsername(username).orElseThrow(
      () -> new EntityNotFoundException(String.format("User usuarname=%s not found", username))
    );
  }

  @Transactional
  public Usuario.Role buscarRolePorUsername(String username) {
    return usuarioRepository.findRoleByUsername(username);
  }  
}
