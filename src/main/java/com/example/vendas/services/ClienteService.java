package com.example.vendas.services;

import com.example.vendas.entities.Cliente;
import com.example.vendas.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public void deleteByName(String nome) {
        repository.deleteByNome("Aline Maria");
    }
}
