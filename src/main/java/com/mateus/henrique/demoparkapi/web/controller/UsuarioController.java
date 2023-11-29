package com.mateus.henrique.demoparkapi.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.henrique.demoparkapi.entity.Usuario;
import com.mateus.henrique.demoparkapi.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
  private final UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
    Usuario user = usuarioService.salvar(usuario);

    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Usuario> show(@PathVariable Long id) {
    Usuario user = usuarioService.buscarPorId(id);

     return ResponseEntity.ok(user);
  }
}
