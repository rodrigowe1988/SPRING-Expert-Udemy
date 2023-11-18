package com.example.vendas.repositories;

import com.example.vendas.entities.Cliente;
import com.example.vendas.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query(" select p from Pedido p left join fetch p.pedidos where p.id = :id ")
    Optional<Pedido> findByIdFetchPedidos(@Param("id") Integer id);
}
