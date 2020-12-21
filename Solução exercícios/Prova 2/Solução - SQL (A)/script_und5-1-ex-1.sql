drop table venda;
drop table nota_produto;
drop table produto;
drop table nota;
drop table cliente;

CREATE TABLE cliente (
cod_cliente integer PRIMARY KEY,
nome varchar(50),
cidade varchar(50),
uf varchar(2),
endereco varchar(50),
data_nasc date
);

CREATE TABLE nota (
num_nota integer PRIMARY KEY,
data timestamp,
num_parcela integer,
cod_cliente integer NOT NULL,
FOREIGN KEY(cod_cliente) REFERENCES cliente (cod_cliente)
);

CREATE TABLE produto (
cod_produto integer PRIMARY KEY,
qtde_estoque integer,
descricao varchar(50),
valor numeric
);

CREATE TABLE venda (
num_nota integer,
data_vencto date,
data_pagto date,
multa_juro numeric,
valor numeric,
desconto numeric,
PRIMARY KEY(num_nota,data_vencto),
FOREIGN KEY(num_nota) REFERENCES nota (num_nota)
);

CREATE TABLE nota_produto (
num_nota integer,
cod_produto integer,
valor numeric,
qtde integer,
PRIMARY KEY(num_nota,cod_produto),
FOREIGN KEY(num_nota) REFERENCES nota (num_nota),
FOREIGN KEY(cod_produto) REFERENCES produto (cod_produto)
);
 
