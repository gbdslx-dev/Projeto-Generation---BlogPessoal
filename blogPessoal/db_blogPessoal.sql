CREATE DATABASE db_BlogPessoal;

USE db_BlogPessoal;

CREATE TABLE tb_temas (
id_temas BIGINT AUTO_INCREMENT,
descricao VARCHAR (255),
PRIMARY KEY (id_temas)
);

CREATE TABLE tb_usuarios (
id_usuarios BIGINT AUTO_INCREMENT,
nome VARCHAR (255),
usuario VARCHAR (255),
senha VARCHAR (255),
foto VARCHAR (255),
PRIMARY KEY (id_usuarios)
);

CREATE TABLE tb_postagens (
id_postagens BIGINT AUTO_INCREMENT,
titulo VARCHAR (100),
texto VARCHAR (1000),
data DATE,
tema_id BIGINT,
usuario_id BIGINT,
PRIMARY KEY (id_postagens),
FOREIGN KEY (tema_id) REFERENCES tb_temas (id_temas),
FOREIGN KEY (usuario_id) REFERENCES tb_usuarios (id_usuarios)
);