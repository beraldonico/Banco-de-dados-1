

CREATE TABLE Medico (
cod_medico integer PRIMARY KEY,
nome varchar(50)
);

CREATE TABLE Paciente (
cod_paciente integer PRIMARY KEY,
nome varchar(50),
uf varchar(2),
endereco varchar(50),
cidade varchar(50),
data_nasc timestamp
);

CREATE TABLE Consulta (
cod_consulta integer PRIMARY KEY,
cod_paciente integer,
cod_medico integer,
data timestamp,
valor numeric,
FOREIGN KEY(cod_paciente) REFERENCES Paciente (cod_paciente),
FOREIGN KEY(cod_medico) REFERENCES Medico (cod_medico)
);

CREATE SEQUENCE seq_paciente_pk INCREMENT BY 1 MINVALUE 1 START 1;

select * from seq_paciente_pk

select nextval('seq_paciente_pk')



