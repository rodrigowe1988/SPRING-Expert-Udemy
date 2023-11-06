package com.example.vendas.services;

import com.example.vendas.entities.Cliente;
import com.example.vendas.entities.ItemPedido;
import com.example.vendas.repositories.ClienteRepository;
import com.example.vendas.repositories.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    private ItemPedidoRepository repository;

    public List<ItemPedido> findAll() {
        return repository.findAll();
    }
}
