package com.example.vendas.services;

import com.example.vendas.entities.Cliente;
import com.example.vendas.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }
    public List<Cliente> findAllFiltered(Example example) {
        return repository.findAll(example);
    }

    public Optional<Cliente> findById(Integer id) {return repository.findById(id);}

    public List<Cliente> findByNome(String nome) { return repository.encontrarPorNome(nome);}

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Cliente save (Cliente cliente) {return repository.save(cliente);}

    public Cliente insert(Cliente cliente) {
        Cliente clienteNew = new Cliente();
        clienteNew.setNome(cliente.getNome());
        clienteNew = repository.save(clienteNew);
        return new Cliente(clienteNew);
    }
}
