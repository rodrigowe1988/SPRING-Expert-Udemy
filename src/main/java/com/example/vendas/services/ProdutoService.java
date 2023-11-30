package com.example.vendas.services;

import com.example.vendas.entities.Produto;
import com.example.vendas.repositories.ProdutoRepository;
import com.example.vendas.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Optional<Produto> findById(Integer id) {return repository.findById(id);}

    public void delete(Integer id) {
        Optional<Produto> cliente = repository.findById(id);
        if (cliente.isPresent()) {
            repository.deleteById(cliente.get().getId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
    }

    public Produto update(Integer id, Produto produto) {
        try {
            Produto entity = repository.getReferenceById(id);
            entity.setDescricao(produto.getDescricao());
            entity.setPrecoUnitario(produto.getPrecoUnitario());
            entity = repository.save(entity);
            return new Produto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id -> " + id + " not found");
        }
    }

    public Produto save (Produto produto) {return repository.save(produto);}

    public Produto insert(Produto produto) {
        Produto produtoNew = new Produto();
        produtoNew.setDescricao(produto.getDescricao());
        produtoNew.setPrecoUnitario(produto.getPrecoUnitario());
        produtoNew = repository.save(produtoNew);
        return new Produto(produtoNew);
    }
}
