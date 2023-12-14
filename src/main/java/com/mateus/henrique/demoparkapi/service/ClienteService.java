package com.mateus.henrique.demoparkapi.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mateus.henrique.demoparkapi.entity.Cliente;
import com.mateus.henrique.demoparkapi.exception.CpfUniqueViolationException;
import com.mateus.henrique.demoparkapi.repository.ClienteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClienteService {
  private final ClienteRepository clienteRepository;

  @Transactional
  public Cliente salvar(Cliente cliente) {
    try {
      return clienteRepository.save(cliente);
    } catch (DataIntegrityViolationException e) {
      throw new CpfUniqueViolationException(String.format("CPF não pode ser cadastrado, já existe no sistema."));
    }
  }

}
