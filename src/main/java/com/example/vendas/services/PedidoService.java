package com.example.vendas.services;

import com.example.vendas.entities.Cliente;
import com.example.vendas.entities.Pedido;
import com.example.vendas.repositories.ClienteRepository;
import com.example.vendas.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private PedidoRepository repository;

    public List<Pedido> findAll() {
        return repository.findAll();
    }
}
