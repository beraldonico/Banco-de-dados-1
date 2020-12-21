CREATE TABLE Fornecedor (
cod_fornecedor integer PRIMARY KEY,
nome varchar(50)
);

CREATE TABLE Fornecedor_Produto (
cod_fornecedor integer,
cod_produto integer,
valor numeric,
PRIMARY KEY(cod_fornecedor,cod_produto),
FOREIGN KEY(cod_fornecedor) REFERENCES Fornecedor (cod_fornecedor)
);

CREATE TABLE Produto (
cod_produto integer PRIMARY KEY,
descricao varchar(50)
);

ALTER TABLE Fornecedor_Produto ADD FOREIGN KEY(cod_produto) REFERENCES Produto (cod_produto);	

insert into produto (cod_produto, descricao) values (1, 'produto 1');
insert into produto (cod_produto, descricao) values (2, 'produto 2');

insert into fornecedor  (cod_fornecedor, nome) values (1, 'fornecedor 1');
insert into fornecedor  (cod_fornecedor, nome) values (2, 'fornecedor 2');

insert into fornecedor_produto (cod_fornecedor, cod_produto, valor) values (1, 1, 100.00);
insert into fornecedor_produto (cod_fornecedor, cod_produto, valor) values (1, 2, 130.00);
insert into fornecedor_produto (cod_fornecedor, cod_produto, valor) values (2, 1, 90.00);
insert into fornecedor_produto (cod_fornecedor, cod_produto, valor) values (2, 2, 110.20);

