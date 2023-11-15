package com.example.vendas.resources;

import com.example.vendas.dto.PedidoDTO;
import com.example.vendas.entities.Pedido;
import com.example.vendas.services.PedidoService;
import com.example.vendas.services.impl.PedidoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    private PedidoServiceImpl service;

    public PedidoResource(PedidoServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
}
