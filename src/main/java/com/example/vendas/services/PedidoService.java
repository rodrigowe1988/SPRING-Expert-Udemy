package com.example.vendas.services;

import com.example.vendas.dto.PedidoDTO;
import com.example.vendas.entities.Pedido;
import com.example.vendas.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PedidoService {

    List<Pedido> findAll();
    Pedido salvar(PedidoDTO dto) ;
}
