-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.



CREATE TABLE Objetos (
ID_objeto int PRIMARY KEY,
Nome varchar(30),
Classe varchar(30),
Vida decimal(4,2),
Mana decimal(4,2),
Dano decimal(4,2),
Armadura decimal(4,2),
Resistenci_magica decimal(4,2),
ID_jogo int
);

CREATE TABLE Circuito (
ID_Circuito int PRIMARY KEY,
Nome varchar(30),
Premio_dinheiro int,
Num_times int,
ID_regiao int
);

CREATE TABLE Pais (
ID_pais int PRIMARY KEY,
Nome_pais varchar(30),
ID_regiao int
);

CREATE TABLE Time (
ID_time int PRIMARY KEY,
Nome varchar(30),
Num_jogadores int,
ID_Circuito int,
ID_pais int,
FOREIGN KEY(ID_Circuito) REFERENCES Circuito (ID_Circuito),
FOREIGN KEY(ID_pais) REFERENCES Pais (ID_pais)
);

CREATE TABLE Patrocinadores (
ID_patrocinio int PRIMARY KEY,
Nome varchar(30),
Valor_patrocinio int,
ID_time int,
FOREIGN KEY(ID_time) REFERENCES Time (ID_time)
);

CREATE TABLE Jogador (
ID_jogador int PRIMARY KEY,
Nome varchar(30),
Nickname varchar(30),
Posicao varchar(30),
ID_time int,
ID_objeto int,
FOREIGN KEY(ID_time) REFERENCES Time (ID_time),
FOREIGN KEY(ID_objeto) REFERENCES Objetos (ID_objeto)
);

CREATE TABLE Regiao (
ID_regiao int PRIMARY KEY,
Nome varchar(30),
Num_paises int,
ID_jogo int
);

CREATE TABLE Jogo (
ID_jogo int PRIMARY KEY,
Genero varchar(30),
Desenvolvedora varchar(30),
Ano_Lancamento int,
Jogadores_por_jogo int
);

ALTER TABLE Objetos ADD FOREIGN KEY(ID_jogo) REFERENCES Jogo (ID_jogo);
ALTER TABLE Circuito ADD FOREIGN KEY(ID_regiao) REFERENCES Regiao (ID_regiao);
ALTER TABLE Pais ADD FOREIGN KEY(ID_regiao) REFERENCES Regiao (ID_regiao);
ALTER TABLE Regiao ADD FOREIGN KEY(ID_jogo) REFERENCES Jogo (ID_jogo);
