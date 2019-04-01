drop sequence proto;
drop sequence admin_seq;
drop sequence entidade_seq;
drop sequence multentidade;
drop sequence cod_multentidade;
drop table entidade;
drop table usuario;
drop table protocolo;
drop table idDoProtocolo;

create sequence proto start with 1 increment by 1;
create sequence multentidade start with 1 increment by 1;
create sequence cod_multentidade start with 1 increment by 1;

create sequence admin_seq start with 1 increment by 1;

create sequence entidade_seq start with 1 increment by 1;

--select proto.nextval from dual;

create table usuario(
nome varchar(100),
email varchar(50),
senha varchar(50),
tipo int
);

create table entidade(
id_entidade int primary key,
nome varchar(100),
email varchar(50),
tipo varchar(50),
telefone varchar(15),
morada varchar(50)
);

create table protocolo(
id_protocolo int,
objetivo varchar(200),
ambito varchar(50),
cod_entidade int,
dia date,
tempo int,
condicao varchar(100),
caminho varchar(100)
);
create table idDoProtocolo(
id_na_outra_tabela_protocolo int,
cod_protocolo int
);
insert into entidade values (1,'Instituicao','i@i','a','132123','st');
insert into entidade values (2,'Instituicao2','i@i','a','132123','st');
--insert into protocolo values (1,'comer','comida',1,'2019-01-03',30,'encher o bucho','ftp://');

--select * from entidade;
--select * from protocolo;
--select * from idDoProtocolo;
--select * from idDoProtocolo where id_na_outra_tabela_protocolo=1;