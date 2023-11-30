package com.example.vendas.entities;

import com.example.vendas.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private Instant dataPedido;

    @Column(name = "total", scale = 2, precision = 20)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> pedidos;
}
