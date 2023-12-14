package com.mateus.henrique.demoparkapi.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.henrique.demoparkapi.entity.Cliente;
import com.mateus.henrique.demoparkapi.jwt.JwtUserDetails;
import com.mateus.henrique.demoparkapi.service.ClienteService;
import com.mateus.henrique.demoparkapi.service.UsuarioService;
import com.mateus.henrique.demoparkapi.web.dto.ClienteCreateDto;
import com.mateus.henrique.demoparkapi.web.dto.ClienteResponseDto;
import com.mateus.henrique.demoparkapi.web.dto.mapper.ClienteMapper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
  
  private final ClienteService clienteService;
  private final UsuarioService usuarioService;

  @PostMapping
  @PreAuthorize("hasRole('CLIENTE')")
  public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
    Cliente cliente =  ClienteMapper.toCliente(dto);
    cliente.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
    clienteService.salvar(cliente);

    return ResponseEntity.status(201).body(ClienteMapper.toDto(cliente));
  }
}
