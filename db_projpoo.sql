create database db_projPoo;
use db_projPoo;

create table usario(
usuario int primary key auto_increment,
nome varchar(60) not null,
email varchar(40) not null unique,
senha varchar(40) not null,
datanasc date,
ativo bool
);
rename table usario to usuario;