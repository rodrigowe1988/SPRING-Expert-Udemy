package com.example.vendas.repositories;

import com.example.vendas.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Query(value = "select p from Produto p where p.id = :id")
    Produto getReferenceById(Integer id);
}
