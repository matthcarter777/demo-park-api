package com.mateus.henrique.demoparkapi.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.henrique.demoparkapi.entity.Usuario;
import com.mateus.henrique.demoparkapi.service.UsuarioService;
import com.mateus.henrique.demoparkapi.web.dto.UsuarioCreateDto;
import com.mateus.henrique.demoparkapi.web.dto.UsuarioResponseDto;
import com.mateus.henrique.demoparkapi.web.dto.UsuarioSenhaDto;
import com.mateus.henrique.demoparkapi.web.dto.mapper.UsuarioMapper;
import com.mateus.henrique.demoparkapi.web.exception.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "usuarios", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um usuário.")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  @Operation(summary = "Criar um novo usuario", description = "Recurso para criar um novo usuario",
    responses = {
      @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
      @ApiResponse(responseCode = "409", description = "Usuario ja está cadastrado no sistema.", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),  
      @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos.", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) 
  })
  @PostMapping
  public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
    Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
    return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
  }
  
  @Operation(summary = "Buscar usuario pelo ID", description = "Buscar usuario pelo ID",
    responses = {
      @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso.", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
      @ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) 
  })
  @GetMapping("/{id}")
  public ResponseEntity<UsuarioResponseDto> show(@PathVariable Long id) {
    Usuario user = usuarioService.buscarPorId(id);

    return ResponseEntity.ok(UsuarioMapper.toDto(user));
  }

    @Operation(summary = "Atualizar senha", description = "Atualizar senha",
    responses = {
      @ApiResponse(responseCode = "204", description = "Recurso atualizado com sucesso.", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
      @ApiResponse(responseCode = "400", description = "Senha não confere", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "404", description = "Recurso não encontrado", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) 
  })
  @PatchMapping("/{id}")
  public ResponseEntity<Void> updatePassword(@PathVariable Long id,@Valid @RequestBody UsuarioSenhaDto dto) {
    usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmarSenha());

    return ResponseEntity.noContent().build();
  }


  @Operation(summary = "Listar todos usuarios", description = "Listar todos usuarios",
  responses = {
    @ApiResponse(responseCode = "200", description = "Recurso criado com sucesso.", 
      content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class)))),
  })
  @GetMapping("")
  public ResponseEntity<List<UsuarioResponseDto>> index() {
    List<Usuario> users = usuarioService.listarTodos();

    return ResponseEntity.ok(UsuarioMapper.toListDto(users));
  }
}
