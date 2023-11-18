package com.example.vendas.services;

import com.example.vendas.enums.StatusPedido;
import com.example.vendas.rest.dto.PedidoDTO;
import com.example.vendas.entities.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> findAll();
    Pedido salvar(PedidoDTO dto) ;
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
