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
rename table usuario to tbusuario;
insert into tbusuario(nome,email,senha,datanasc) values ('arthur', 'arthur.teste1@gmail.com', 123456, '2008-08-05');
select * from tbusuario;
delete from tbusuario where usuario = 1;

update tbusuario set senha = sha1("123456") where usuario= 1;