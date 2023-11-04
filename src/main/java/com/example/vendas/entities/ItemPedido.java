package com.example.vendas.entities;

import lombok.Data;

@Data
public class ItemPedido {
    private Integer id;
    private Pedido pedido;
    private Produto produto;
    private Integer numeric;

}
