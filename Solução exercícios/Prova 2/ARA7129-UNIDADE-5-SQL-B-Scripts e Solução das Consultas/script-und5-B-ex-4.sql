CREATE TABLE MESA (
cod_mesa integer PRIMARY KEY,
cod_bar integer
);

CREATE TABLE BAR (
cod_bar integer PRIMARY KEY,
endereco varchar(50),
nome varchar(50)
);

CREATE TABLE GARCOM (
cod_garcom integer PRIMARY KEY,
nome varchar(50)
);

CREATE TABLE PRODUTO (
cod_produto integer PRIMARY KEY,
descricao varchar(50),
valor numeric
);

CREATE TABLE NOTA (
cod_nota integer PRIMARY KEY,
data timestamp,
cod_mesa integer,
FOREIGN KEY(cod_mesa) REFERENCES MESA (cod_mesa)
);


CREATE TABLE PEDIDO (
valor numeric,
quantidade integer,
cod_produto integer,
cod_nota integer,
cod_garcom integer,
PRIMARY KEY(cod_produto,cod_nota,cod_garcom)
);

ALTER TABLE MESA ADD FOREIGN KEY(cod_bar) REFERENCES BAR (cod_bar);

insert into bar (cod_bar, nome) values (1, 'bar 1');

insert into mesa (cod_mesa, cod_bar) values (1, 1);
insert into mesa (cod_mesa, cod_bar) values (2, 1);

insert into nota (cod_nota, data, cod_mesa) values (1, '2011-11-01', 1);

insert into produto (cod_produto, descricao, valor) values (1, 'prod 1', 10.00);
insert into produto (cod_produto, descricao, valor) values (2, 'prod 2', 8.00);

insert into garcom (cod_garcom, nome) values (1, 'garcom 1');
insert into garcom (cod_garcom, nome) values (2, 'garcom 2');


insert into pedido (cod_nota, cod_produto, cod_garcom, valor, quantidade) values (1, 1, 1, 10,1);
insert into pedido (cod_nota, cod_produto, cod_garcom, valor, quantidade) values (1, 2, 1,  10,1);
commit;