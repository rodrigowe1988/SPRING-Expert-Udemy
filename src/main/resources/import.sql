INSERT INTO clientes (nome, cpf) values ('Rogerio Weber','423.568.870-75');
INSERT INTO clientes (nome, cpf) values ('Cláudia Maria','619.574.550-20');
INSERT INTO clientes (nome, cpf) values ('Aline Maria', '175.605.580-70');
INSERT INTO clientes (nome, cpf) values ('Rodrigo Weber','383.473.670-86');


INSERT INTO pedidos (cliente_id,data_pedido,total) values (1, TIMESTAMP WITH TIME ZONE '1988-01-13',2000.0);

INSERT INTO produtos (descricao,preco_unitario) values ('TV SAMSUNG 40 polegadas', 2000.0);
INSERT INTO produtos (descricao,preco_unitario) values ('Macbook PRO M1 MAX', 15000.0);
INSERT INTO produtos (descricao,preco_unitario) values ('TV LG 32 polegadas', 1500.0);