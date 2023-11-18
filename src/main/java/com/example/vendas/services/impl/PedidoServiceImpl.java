package com.example.vendas.services.impl;

import com.example.vendas.rest.dto.ItemPedidoDTO;
import com.example.vendas.rest.dto.PedidoDTO;
import com.example.vendas.entities.Cliente;
import com.example.vendas.entities.ItemPedido;
import com.example.vendas.entities.Pedido;
import com.example.vendas.entities.Produto;
import com.example.vendas.repositories.ClienteRepository;
import com.example.vendas.repositories.ItemPedidoRepository;
import com.example.vendas.repositories.PedidoRepository;
import com.example.vendas.repositories.ProdutoRepository;
import com.example.vendas.services.PedidoService;
import com.example.vendas.services.exceptions.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    //adicionar o final para o RequiredArgsConstructor criar o construtor para esses repositories
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException(idCliente + " é um id inválido de cliente."));
        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(Instant.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);
        pedido.setPedidos(itensPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchPedidos(id);
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> items) {
        if(items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens!");
        }
        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException(idProduto + " é um id inválido de produto."));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
