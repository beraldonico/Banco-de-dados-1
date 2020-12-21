
CREATE TABLE LIVRO (
isbn integer PRIMARY KEY,
editora varchar(50),
titulo varchar(50)
);

CREATE TABLE AUTOR (
cod_autor integer PRIMARY KEY,
nome varchar(50)
);

CREATE TABLE EXEMPLAR (
cod_exemplar integer PRIMARY KEY,
isbn integer,
FOREIGN KEY(isbn) REFERENCES LIVRO (isbn)
);

CREATE TABLE ASSOCIADO (
nome varchar(50),
endereco varchar(50),
cod_associado integer PRIMARY KEY
);

CREATE TABLE PALAVRA_CHAVE (
descricao varchar(50),
cod_plv_chave integer PRIMARY KEY
);

CREATE TABLE PLV_CHAVE_LIVRO (
isbn integer,
cod_plv_chave integer,
PRIMARY KEY(isbn,cod_plv_chave),
FOREIGN KEY(isbn) REFERENCES LIVRO (isbn),
FOREIGN KEY(cod_plv_chave) REFERENCES PALAVRA_CHAVE (cod_plv_chave)
);

CREATE TABLE COAUTOR (
isbn integer,
cod_autor integer,
PRIMARY KEY(isbn,cod_autor),
FOREIGN KEY(isbn) REFERENCES LIVRO (isbn),
FOREIGN KEY(cod_autor) REFERENCES AUTOR (cod_autor)
);

CREATE TABLE EMPRESTIMO (
data_emprestimo timestamp,
data_devolucao timestamp,
seq_emprestimo integer PRIMARY KEY,
cod_associado integer,
cod_exemplar integer,
FOREIGN KEY(cod_associado) REFERENCES ASSOCIADO (cod_associado),
FOREIGN KEY(cod_exemplar) REFERENCES EXEMPLAR (cod_exemplar)
);



insert into livro (isbn, titulo, editora) values (1, 'Livro 1', 'Editora 1');
insert into livro (isbn, titulo, editora) values (2, 'Livro 2', 'Editora 2');
insert into livro (isbn, titulo, editora) values (3, 'Livro 3', 'Editora 3');

insert into palavra_chave (cod_plv_chave, descricao) values (1, 'Palavra-chave 1');
insert into palavra_chave (cod_plv_chave, descricao) values (2, 'Palavra-chave 2');
insert into palavra_chave (cod_plv_chave, descricao) values (3, 'Palavra-chave 3');

insert into autor (cod_autor, nome) values (1, 'Autor 1');
insert into autor (cod_autor, nome) values (2, 'Autor 2');
insert into autor (cod_autor, nome) values (3, 'Autor 2');

insert into plv_chave_livro (isbn, cod_plv_chave) values (1, 1);
insert into plv_chave_livro (isbn, cod_plv_chave) values (1, 2);
insert into plv_chave_livro (isbn, cod_plv_chave) values (2, 2);
insert into plv_chave_livro (isbn, cod_plv_chave) values (2, 3);
insert into plv_chave_livro (isbn, cod_plv_chave) values (3, 1);
insert into plv_chave_livro (isbn, cod_plv_chave) values (3, 3);

insert into coautor (isbn, cod_autor) values (1, 1);
insert into coautor (isbn, cod_autor) values (1, 3);
insert into coautor (isbn, cod_autor) values (2, 2);
insert into coautor (isbn, cod_autor) values (2, 3);
insert into coautor (isbn, cod_autor) values (3, 1);

insert into associado (cod_associado, nome, endereco) values (1, 'Associado 1', 'Endereco 1');
insert into associado (cod_associado, nome, endereco) values (2, 'Associado 2', 'Endereco 2');
insert into associado (cod_associado, nome, endereco) values (3, 'Associado 3', 'Endereco 3');
insert into associado (cod_associado, nome, endereco) values (4, 'Associado 4', 'Endereco 4');

insert into exemplar (cod_exemplar, isbn) values (1, 1);
insert into exemplar (cod_exemplar, isbn) values (2, 1);
insert into exemplar (cod_exemplar, isbn) values (3, 2);
insert into exemplar (cod_exemplar, isbn) values (4, 2);
insert into exemplar (cod_exemplar, isbn) values (5, 2);
insert into exemplar (cod_exemplar, isbn) values (6, 3);
insert into exemplar (cod_exemplar, isbn) values (7, 3);
insert into exemplar (cod_exemplar, isbn) values (8, 3);
insert into exemplar (cod_exemplar, isbn) values (9, 3);
 
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (1, 1, 1, '2013-05-02', '2013-05-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (2, 1, 1, '2013-06-02', '2013-06-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (3, 1, 3, '2013-06-02', '2013-06-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (4, 2, 1, '2013-05-02', '2013-05-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (5, 2, 4, '2013-05-02', '2013-05-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (6, 2, 4, '2013-06-02', '2013-06-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (7, 2, 7, '2013-06-02', '2013-06-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (8, 3, 1, '2013-05-02', '2013-05-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (9, 3, 1, '2013-06-02', '2013-06-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (10, 3, 3, '2013-06-02', '2013-06-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (11, 3, 8, '2013-04-02', '2013-04-08');
insert into emprestimo (seq_emprestimo, cod_associado, cod_exemplar, data_emprestimo, data_devolucao) values (12, 3, 9, '2013-06-02', '2013-06-08');







