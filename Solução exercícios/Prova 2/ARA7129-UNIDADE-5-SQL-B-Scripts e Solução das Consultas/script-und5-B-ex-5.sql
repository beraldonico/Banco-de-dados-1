CREATE TABLE perfil (
codigo integer PRIMARY KEY,
descricao varchar(50),
custo_hora numeric
);

CREATE TABLE atividade_colaborador (
cod_colaborador integer,
cod_atividade integer,
perc_colaboracao integer,
PRIMARY KEY(cod_colaborador,cod_atividade)
);

CREATE TABLE colaborador (
codigo integer PRIMARY KEY,
nome varchar(50),
cod_perfil integer,
FOREIGN KEY(cod_perfil) REFERENCES perfil (codigo)
);

CREATE TABLE projeto (
codigo integer PRIMARY KEY,
descricao varchar(50)
);

CREATE TABLE categoria (
codigo integer PRIMARY KEY,
descricao varchar(50)
);

CREATE TABLE atividade (
codigo integer PRIMARY KEY,
descricao varchar(50),
qtde_hora_prevista integer,
qtde_hora_total integer,
cod_projeto integer,
cod_categoria integer,
FOREIGN KEY(cod_projeto) REFERENCES projeto (codigo),
FOREIGN KEY(cod_categoria) REFERENCES categoria (codigo)
);

ALTER TABLE atividade_colaborador 
   ADD FOREIGN KEY(cod_colaborador) REFERENCES colaborador (codigo);

ALTER TABLE atividade_colaborador 
   ADD FOREIGN KEY(cod_atividade) REFERENCES atividade (codigo);


insert into perfil (codigo, descricao, custo_hora) values (1, 'Analista', 10.00);
insert into perfil (codigo, descricao, custo_hora) values (2, 'Desenvolvedor I', 7.00);
insert into perfil (codigo, descricao, custo_hora) values (3, 'Desenvolvedor II', 6.20);
insert into perfil (codigo, descricao, custo_hora) values (4, 'Gerente', 11.00);

insert into colaborador (codigo, nome, cod_perfil) values (1, 'Colaborador 1', 1);
insert into colaborador (codigo, nome, cod_perfil) values (2, 'Colaborador 2', 1);
insert into colaborador (codigo, nome, cod_perfil) values (3, 'Colaborador 3', 2);
insert into colaborador (codigo, nome, cod_perfil) values (4, 'Colaborador 4', 2);
insert into colaborador (codigo, nome, cod_perfil) values (5, 'Colaborador 5', 3);
insert into colaborador (codigo, nome, cod_perfil) values (6, 'Colaborador 6', 4);

insert into categoria (codigo, descricao) values (1, 'Categoria 1');
insert into categoria (codigo, descricao) values (2, 'Categoria 2');
insert into categoria (codigo, descricao) values (3, 'Categoria 3');
insert into categoria (codigo, descricao) values (4, 'Categoria 4');

insert into projeto (codigo, descricao) values (1, 'Projeto 1');
insert into projeto (codigo, descricao) values (2, 'Projeto 2');
insert into projeto (codigo, descricao) values (3, 'Projeto 3');
insert into projeto (codigo, descricao) values (4, 'Projeto 4');

insert into atividade (codigo, descricao, qtde_hora_prevista, qtde_hora_total, cod_projeto, cod_categoria) 
   values (1, 'Atividade 1', 10, 15, 1, 1);
insert into atividade (codigo, descricao, qtde_hora_prevista, qtde_hora_total, cod_projeto, cod_categoria) 
   values (2, 'Atividade 2', 10, 8, 1, 2);
insert into atividade (codigo, descricao, qtde_hora_prevista, qtde_hora_total, cod_projeto, cod_categoria) 
   values (3, 'Atividade 3', 5, 5, 2, 1);
insert into atividade (codigo, descricao, qtde_hora_prevista, qtde_hora_total, cod_projeto, cod_categoria) 
   values (4, 'Atividade 4', 6, 8, 2, 3);
insert into atividade (codigo, descricao, qtde_hora_prevista, qtde_hora_total, cod_projeto, cod_categoria) 
   values (5, 'Atividade 5', 5, 5, 3, 2);
insert into atividade (codigo, descricao, qtde_hora_prevista, qtde_hora_total, cod_projeto, cod_categoria) 
   values (6, 'Atividade 6', 6, 8, 3, 4);
insert into atividade (codigo, descricao, qtde_hora_prevista, qtde_hora_total, cod_projeto, cod_categoria) 
   values (7, 'Atividade 7', 5, 5, 4, 1);
insert into atividade (codigo, descricao, qtde_hora_prevista, qtde_hora_total, cod_projeto, cod_categoria) 
   values (8, 'Atividade 8', 12, 9, 4, 4);


insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (1, 1, 70);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (2, 1, 30);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (2, 2, 20);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (3, 2, 30);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (4, 2, 50);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (3, 3, 65);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (6, 3, 35);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (2, 4, 25);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (5, 4, 30);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (6, 4, 45);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (1, 5, 30);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (3, 5, 10);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (5, 5, 35);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (4, 5, 25);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (2, 6, 33);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (3, 6, 67);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (1, 7, 20);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (6, 7, 25);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (4, 7, 55);
insert into atividade_colaborador (cod_colaborador, cod_atividade, perc_colaboracao) values (6, 8, 100);



