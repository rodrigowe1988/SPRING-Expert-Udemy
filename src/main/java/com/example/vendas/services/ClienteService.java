package com.example.vendas.services;

import com.example.vendas.entities.Cliente;
import com.example.vendas.repositories.ClienteRepository;
import com.example.vendas.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<Cliente> cliente = repository.findById(id);
        if (cliente.isPresent()) {
            repository.deleteById(cliente.get().getId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }

    public Cliente update(Integer id, Cliente cliente) {
        try {
            Cliente entity = repository.getReferenceById(id);
            entity.setNome(cliente.getNome());
            entity.setCpf(cliente.getCpf());
            entity = repository.save(entity);
            return new Cliente(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id -> " + id + " not found");
        }
    }

    public Cliente save (Cliente cliente) {return repository.save(cliente);}

    public Cliente insert(Cliente cliente) {
        Cliente clienteNew = new Cliente();
        clienteNew.setNome(cliente.getNome());
        clienteNew.setCpf(cliente.getCpf());
        clienteNew = repository.save(clienteNew);
        return new Cliente(clienteNew);
    }
}
