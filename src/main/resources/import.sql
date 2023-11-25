-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


-- Inserção para Fornecedor
insert into fornecedor(nome, email, cnpj, telefone, endereco) values ('LuxOtica','luxotica@gmail.com', '11.111.111/0001-00', '(63) 99111-0101', 'Bairro Professor José Lopes Rua 01' );
insert into fornecedor(nome, email, cnpj, telefone, endereco) values ('Safilo','safilo@gmail.com', '22.222.222/0001-00', '(63) 99222-0221', 'Bairro do Limoeiro Rua 20');
insert into fornecedor(nome, email, cnpj, telefone, endereco) values ('RVGlasses','rvglasses@gmail.com', '33.333.333/0001-00', '(63) 99333-3333', 'Bairro Laranja Lima Rua 01');
insert into fornecedor(nome, email, cnpj, telefone, endereco) values ('Cluss','cluss@gmail.com', '44.444.444/0001-00', '(63) 90000-0000', 'Bairro JK Rua 28');

-- Inserção de Marca
insert into marca(nome, id_fornecedor) values('Ray-Ban', 1);
insert into marca(nome, id_fornecedor) values('Ray-Ban', 4);
insert into marca(nome, id_fornecedor) values('GUESS', 2);
insert into marca(nome, id_fornecedor) values('Tommy Hilfiger', 3);


-- inserção de óculos
 insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade, id_marca, tipooculos) values ('C12A3', 'A2', '145', 85.00, 120.00, 10, 1, 1);
 insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade, id_marca, tipooculos) values ('B2023', 'C6', '145', 78.00, 110.00, 5, 2, 2);
 insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade, id_marca, tipooculos) values ('B2023', 'C5', '140', 99.90, 135.80, 2, 3, 2);
 insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade, id_marca, tipooculos) values ('A62G3', 'B2', '50', 100.00, 110.00, 12, 4, 3);


insert into usuario(nome, cpf, email, perfil, senha) values('Elon Musk', '111.111.111-11', 'musk@gmail.com', 1, 'DzdKfFtHned4y7fLASqK0gH9EqUAMZgn6HuhapPc6l0ycYnZ/AZB2mFjbV5ADHvCpr8u3Vm8SkIIJ55gmKQDdA==');
insert into usuario(nome, cpf, email, perfil, senha) values('Bill Gates','222.222.222-22', 'gates@gmail.com', 1, 'cQa5YaODDHhULIAmdDvDQ/YyU9jAzqqhz1hzmFU7LB1CHLRrUEgu9r/O5cyup6ghql/1J5J60tVChoWwa5XL6Q==');

insert into telefone (codigoArea, numero) values('63', '9999-9999');
insert into telefone (codigoArea, numero) values('62', '8888-8888');
insert into telefone (codigoArea, numero) values('61', '7777-7777');
insert into telefone (codigoArea, numero) values('55', '6666-6666');

insert into usuario_telefone (id_usuario, id_telefone) values(1, 1);
insert into usuario_telefone (id_usuario, id_telefone) values(1, 2);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 3);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 4);

insert into endereco(cep, bairro, rua, numero, complemento, cidade) values('77006-020', '110 Norte', 'alameda 15', '25', 'casa', 'Palmas');
insert into endereco(cep, bairro, rua, numero, complemento, cidade) values('77010-000', '106 Norte', 'Av.JK', '10', 'loja 1, Sapataria', 'Salvador');
insert into endereco(cep, bairro, rua, numero, complemento, cidade) values('77006-500', '204 Sul', 'alameda 02', '13', 'casa', 'Palmas');

insert into usuario_endereco (id_usuario, id_endereco) values(1, 1); 
insert into usuario_endereco (id_usuario, id_endereco) values(1, 2); 
insert into usuario_endereco (id_usuario, id_endereco) values(2, 3); 

insert into estado(nome, sigla) values('Tocantins', 'TO');
insert into estado(nome, sigla) values('Bahia', 'BA');

insert into cidade(nome) values('Palmas');
insert into cidade(nome) values('Paraíso');
insert into cidade(nome) values('Salvador');

insert into estado_cidade(id_estado, id_cidade) values(1, 1);
insert into estado_cidade(id_estado, id_cidade) values(1, 2);
insert into estado_cidade(id_estado, id_cidade) values(2, 3);