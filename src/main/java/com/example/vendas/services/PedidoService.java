package com.example.vendas.services;

import com.example.vendas.rest.dto.PedidoDTO;
import com.example.vendas.entities.Pedido;

import java.util.List;

public interface PedidoService {

    List<Pedido> findAll();

    Pedido salvar(PedidoDTO dto) ;

    Pedido obterPedidoCompleto(Integer id);
}
