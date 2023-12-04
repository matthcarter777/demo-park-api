package com.mateus.henrique.demoparkapi.jwt;

import org.springframework.stereotype.Service;

import com.mateus.henrique.demoparkapi.entity.Usuario;
import com.mateus.henrique.demoparkapi.service.UsuarioService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JwtUserDatailsService implements UserDetailsService {
  
  private final UsuarioService usuarioService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioService.buscarPorUsername(username);

    return new JwtUserDetails(usuario);
  }

  public JwtToken getTokenAuthenticated(String username) {
    Usuario.Role role = usuarioService.buscarRolePorUsername(username);
    return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
  }
  
}
