package com.example.vendas.resources;

import com.example.vendas.entities.ItemPedido;
import com.example.vendas.services.ItemPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/itens_pedidos")
public class ItemPedidoResource {

    private ItemPedidoService service;

    @GetMapping
    public ResponseEntity<List<ItemPedido>> findAll() {
        List<ItemPedido> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
