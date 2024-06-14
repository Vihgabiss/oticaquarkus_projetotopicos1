-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into usuario(nome, cpf, email, perfil, senha) values('Elon Musk', '111.111.111-11', 'musk@gmail.com', 2, 'DzdKfFtHned4y7fLASqK0gH9EqUAMZgn6HuhapPc6l0ycYnZ/AZB2mFjbV5ADHvCpr8u3Vm8SkIIJ55gmKQDdA==');
insert into usuario(nome, cpf, email, perfil, senha) values('Bill Gates','222.222.222-22', 'gates@gmail.com', 1, 'cQa5YaODDHhULIAmdDvDQ/YyU9jAzqqhz1hzmFU7LB1CHLRrUEgu9r/O5cyup6ghql/1J5J60tVChoWwa5XL6Q==');

insert into telefone (codigoArea, numero) values('63', '9999-9999');
insert into telefone (codigoArea, numero) values('62', '8888-8888');
insert into telefone (codigoArea, numero) values('61', '7777-7777');
insert into telefone (codigoArea, numero) values('55', '6666-6666');

insert into usuario_telefone (id_usuario, id_telefone) values(1, 1);
insert into usuario_telefone (id_usuario, id_telefone) values(1, 2);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 3);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 4);

insert into estado(nome, sigla) values('Tocantins', 'TO');
insert into estado(nome, sigla) values('Bahia', 'BA');

insert into cidade(nome, id_estado) values('Palmas', 1);
insert into cidade(nome, id_estado) values('Paraíso', 1);
insert into cidade(nome, id_estado) values('Salvador', 2);

insert into endereco(cep, bairro, rua, numero, complemento, id_cidade) values('77006-020', '110 Norte', 'alameda 15', 25, 'casa', 1);
insert into endereco(cep, bairro, rua, numero, complemento, id_cidade) values('77010-000', '106 Norte', 'Av.JK', 10, 'loja 1, Sapataria', 1);
insert into endereco(cep, bairro, rua, numero, complemento, id_cidade) values('77006-500', '204 Sul', 'alameda 02', 13, 'casa', 2);

insert into usuario_endereco (id_usuario, id_endereco) values(1, 1); 
insert into usuario_endereco (id_usuario, id_endereco) values(1, 2); 
insert into usuario_endereco (id_usuario, id_endereco) values(2, 3); 