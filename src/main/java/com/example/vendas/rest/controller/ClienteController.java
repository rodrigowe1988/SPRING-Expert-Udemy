package com.example.vendas.rest.controller;

import com.example.vendas.entities.Cliente;
import com.example.vendas.services.ClienteService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = service.findById(id);
        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Cliente>> getClienteByNome(@RequestParam String nome) {
        List<Cliente> cliente = service.findByNome(nome);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid Cliente cliente) {
        service.insert(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
        service.update(id, cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/busca-geral")
    public ResponseEntity find (Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = service.findAllFiltered(example);
        return ResponseEntity.ok(lista);
    }
}

