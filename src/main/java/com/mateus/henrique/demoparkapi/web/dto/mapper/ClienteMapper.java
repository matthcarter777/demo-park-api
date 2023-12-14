package com.mateus.henrique.demoparkapi.web.dto.mapper;

import org.modelmapper.ModelMapper;

import com.mateus.henrique.demoparkapi.entity.Cliente;
import com.mateus.henrique.demoparkapi.web.dto.ClienteCreateDto;
import com.mateus.henrique.demoparkapi.web.dto.ClienteResponseDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {
  
  public static Cliente toCliente(ClienteCreateDto dto) {
    return new ModelMapper().map(dto, Cliente.class);
  }

  public static ClienteResponseDto toDto(Cliente cliente) {
    return new ModelMapper().map(cliente, ClienteResponseDto.class);
  }

}
