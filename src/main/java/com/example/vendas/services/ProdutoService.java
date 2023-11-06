package com.example.vendas.services;

import com.example.vendas.entities.Produto;
import com.example.vendas.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository repository;

    public List<Produto> findAll() {
        return repository.findAll();
    }
}
