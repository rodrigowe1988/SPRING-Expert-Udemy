package com.example.vendas.repositories;

import com.example.vendas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //Consulta usando o @Query
    @Query(value = "select c from Cliente c where c.nome like %:nome%")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClientFetchPedidos(@Param("id") Integer id);


    //ou como query nativa do sql
    @Query(value = "select c from Cliente c where c.nome like :nome", nativeQuery = true)
    List<Cliente> encontrarPorNomeQueryNativa(@Param("nome") String nome);

    //Consultas via @Query Methods
    List<Cliente> findByNomeLike(String nome);

    boolean existsByNome(String nome);
}
