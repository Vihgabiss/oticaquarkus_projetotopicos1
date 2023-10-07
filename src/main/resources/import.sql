-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into oculos(referencia, cor, tamanho) values('C12A3', 'A2', '145');
insert into oculos(referencia, cor, tamanho) values('B2023', 'C6', '145');
insert into oculos(referencia, cor, tamanho) values('B2023', 'C5', '140');
insert into oculos(referencia, cor, tamanho) values('A62G3', 'B2', '50');

insert into marca(nome, id_oculos) values('Ray-Ban', 1);
insert into marca(nome, id_oculos) values('Ray-Ban', 4);
insert into marca(nome, id_oculos) values('GUESS', 2);
insert into marca(nome, id_oculos) values('Tommy Hilfiger', 3);

insert into usuario(nome, cpf, email, senha) values('Elon Musk', '111.111.111-11', 'musk@gmail.com', '111');
insert into usuario(nome, cpf, email, senha) values('Bill Gates','222.222.222-22', 'gates@gmail.com', '222');

insert into telefone (codigoArea, numero) values('63', '9999-9999');
insert into telefone (codigoArea, numero) values('62', '8888-8888');
insert into telefone (codigoArea, numero) values('61', '7777-7777');
insert into telefone (codigoArea, numero) values('55', '6666-6666');

insert into usuario_telefone (id_usuario, id_telefone) values(1, 1);
insert into usuario_telefone (id_usuario, id_telefone) values(1, 2);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 3);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 4);

-- insert into endereco(cep, bairro, rua, numero, complemento, cidade) values('77006-020', '110 Norte', 'alameda 15', '25', 'casa', 'Palmas');
-- insert into endereco(cep, bairro, rua, numero, complemento, cidade) values('77010-000', '106 Norte', 'Av.JK', '10', 'loja 1, Sapataria', 'Salvador');
-- insert into endereco(cep, bairro, rua, numero, complemento, cidade) values('77006-500', '204 Sul', 'alameda 02', '13', 'casa', 'Palmas');

-- insert into usuario_endereco (id_usuario, id_endereco) values(1, 1); 
-- insert into usuario_endereco (id_usuario, id_endereco) values(1, 2); 
-- insert into usuario_endereco (id_usuario, id_endereco) values(2, 3); 
