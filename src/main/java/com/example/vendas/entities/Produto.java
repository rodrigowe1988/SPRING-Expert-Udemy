package com.example.vendas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produtos")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;
    @Column(name = "preco_unitario")
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal precoUnitario;

    public Produto(Produto produto) {}
}
