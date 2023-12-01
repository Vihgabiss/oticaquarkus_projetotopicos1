-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


-- Inserção para Fornecedor
insert into fornecedor(nome, telefone, email, endereco, cnpj) values ('LuxOtica',  '6848464511', 'luxotica@gmail.com','110 norte', '11.111.111/0001-00');
insert into fornecedor(nome, telefone, email, endereco, cnpj) values ('Safilo',  '6848464511', 'safilo@gmail.com', '110 norte', '22.222.222/0001-00');


--Inserção de Marca
insert into marca(nome, id_fornecedor) values('Ray-Ban', 1);
insert into marca(nome, id_fornecedor) values('Ray-Ban', 2);
insert into marca(nome, id_fornecedor) values('GUESS', 2);
insert into marca(nome, id_fornecedor) values('Tommy Hilfiger', 1);


-- inserção de óculos
insert into oculos (referencia, cor, tamanho, precoCusto, precoVenda, quantidade, id_marca, tipooculos, nomeImagem) values ('C12A3', 'A2', '145', 85.00, 120.00, 10, 1, 1, 'oculos1.jpg');
insert into oculos (referencia, cor, tamanho, precoCusto, precoVenda, quantidade, id_marca, tipooculos, nomeImagem) values ('B2023', 'C6', '145', 78.00, 110.00, 5, 2, 2, 'oculos2.jpg');
insert into oculos (referencia, cor, tamanho, precoCusto, precoVenda, quantidade, id_marca, tipooculos, nomeImagem) values ('B2023', 'C5', '140', 99.90, 135.80, 2, 2, 3, 'oculos3.jpg');
insert into oculos (referencia, cor, tamanho, precoCusto, precoVenda, quantidade, id_marca, tipooculos, nomeImagem) values ('A62G3', 'B2', '50', 100.00, 110.00, 12, 1, 3, 'oculos4.jpg');

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

insert into endereco(cep, bairro, rua, numero, complemento, id_cidade) values('77006-020', '110 Norte', 'alameda 15', '25', 'casa', 1);
insert into endereco(cep, bairro, rua, numero, complemento, id_cidade) values('77010-000', '106 Norte', 'Av.JK', '10', 'loja 1, Sapataria', 1);
insert into endereco(cep, bairro, rua, numero, complemento, id_cidade) values('77006-500', '204 Sul', 'alameda 02', '13', 'casa', 2);

insert into usuario_endereco (id_usuario, id_endereco) values(1, 1); 
insert into usuario_endereco (id_usuario, id_endereco) values(1, 2); 
insert into usuario_endereco (id_usuario, id_endereco) values(2, 3); 

-- teste de venda

insert into venda(id_usuario, dataHora, tipoPagamento, valorTotal, statusVenda) values (1, '2023-11-30T14:57:14.1212501', 1, 100.00, 1);
insert into itemvenda(preco, quantidade, id_oculos, id_venda) values (200.00, 2, 1, 1);