package com.example.vendas.rest.controller;

import com.example.vendas.entities.Cliente;
import com.example.vendas.services.ClienteService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/clientes")
@Api("API Clientes")
public class ClienteController {

    private ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("Listar todos os clientes")
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado com o ID informado")
    })
    public ResponseEntity<Cliente> getClienteById( @PathVariable @ApiParam("Id do cliente") Integer id) {
        Optional<Cliente> cliente = service.findById(id);
        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nome")
    @ApiOperation(" Buscar cliente por nome")
    public ResponseEntity<List<Cliente>> getClienteByNome(@RequestParam String nome) {
        List<Cliente> cliente = service.findByNome(nome);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    @ApiOperation("Salva um novo cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente criado com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public ResponseEntity save(@RequestBody @Valid Cliente cliente) {
        service.insert(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar dados de um cliente")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
        service.update(id, cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Deletar um cliente")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/busca-geral")
    @ApiOperation("Buscar cliente por nome e/ou cpf")
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

