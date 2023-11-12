package com.example.vendas.resources;

import ch.qos.logback.core.net.server.Client;
import com.example.vendas.entities.Cliente;
import com.example.vendas.services.ClienteService;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    private ClienteService service;

    public ClienteResource(ClienteService service) {
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
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = service.insert(cliente);
        return ResponseEntity.ok().body(clienteSalvo);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return service.findById(id).map( clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            service.save(cliente);
            return ResponseEntity.ok().body(cliente);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = service.findById(id);
        if (cliente.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();

        }else  {
            System.out.println("Cliente com id " + id + " não encontrado!");
            return ResponseEntity.notFound().build();
        }
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

