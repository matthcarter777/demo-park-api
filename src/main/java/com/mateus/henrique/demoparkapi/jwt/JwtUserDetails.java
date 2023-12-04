package com.mateus.henrique.demoparkapi.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.mateus.henrique.demoparkapi.entity.Usuario;

public class JwtUserDetails extends User {

  private Usuario usuario;

  public JwtUserDetails(Usuario usuario) {
    super(usuario.getUsername(), usuario.getPassword(), AuthorityUtils.createAuthorityList(usuario.getRole().name()));
    this.usuario = usuario;
  }
  
  public Long getId() {
    return this.usuario.getId();
  }

  public String getRole() {
    return this.usuario.getRole().name();
  }
}
