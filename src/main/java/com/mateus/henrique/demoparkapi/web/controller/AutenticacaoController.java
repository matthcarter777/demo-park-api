package com.mateus.henrique.demoparkapi.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.henrique.demoparkapi.jwt.JwtToken;
import com.mateus.henrique.demoparkapi.jwt.JwtUserDatailsService;
import com.mateus.henrique.demoparkapi.web.dto.UsuarioLoginDto;
import com.mateus.henrique.demoparkapi.web.exception.ErrorMessage;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AutenticacaoController {
  
  private final JwtUserDatailsService detailsService;
  private final AuthenticationManager authenticationManager;

  @PostMapping("/auth")
  public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto dto, HttpServletRequest request) {
    log.info("Processo de autenticação pelo login {}", dto.getUsername());

    try {
      UsernamePasswordAuthenticationToken authenticationToken = 
        new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
      
      authenticationManager.authenticate(authenticationToken);

      JwtToken token = detailsService.getTokenAuthenticated(dto.getPassword());

      return ResponseEntity.ok(token);
    } catch (AuthenticationException e) {
      log.warn("Bad credentials from username '{}'", dto.getUsername());
    }

    return ResponseEntity
      .badRequest()
      .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais Inválidas"));
  }
}
