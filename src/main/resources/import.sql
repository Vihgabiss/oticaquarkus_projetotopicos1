-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Inserção para Telefone
INSERT INTO telefone(codigoarea, numero) VALUES('63', '911111111');
INSERT INTO telefone(codigoarea, numero) VALUES('11', '922222222');
INSERT INTO telefone(codigoarea, numero) VALUES('11', '933333333');
INSERT INTO telefone(codigoarea, numero) VALUES('11', '944444444');

-- Inserção para Endereço
INSERT INTO endereco( cep, bairro, rua, numero, complemento, cidade) VALUES( '22222-222', 'Bairro Professor José Lopes', 'Rua 01', 123, 'Apto 456', 'Palmas');
INSERT INTO endereco( cep, bairro, rua, numero, complemento, cidade) VALUES( '11111-111', 'Bairro do Limoeiro', 'Rua 12', 01,  'Casa', 'São Paulo');
INSERT INTO endereco( cep, bairro, rua, numero, complemento, cidade) VALUES( '33333-333', 'Bairro Laranja Lima', 'Rua 14', 33, 'Apto 055', 'Rio de Janeiro');
INSERT INTO endereco( cep, bairro, rua, numero, complemento, cidade) VALUES( '44444-444', 'Bairro JK', 'Rua 11', 13, 'Casa', 'Palmas');


-- Inserção para Fornecedor
insert into fornecedor(nome, email, cnpj) values ("LuxOtica","luxotica@gmail.com", "11.111.111/0001-00" );
insert into fornecedor(nome, email, cnpj) values ("Safilo","safilo@gmail.com", "22.222.222/0001-00");
insert into fornecedor(nome, email, cnpj) values ("RVGlasses","rvglasses@gmail.com", "33.333.333/0001-00");
insert into fornecedor(nome, email, cnpj) values ("Cluss","cluss@gmail.com", "44.444.444/0001-00");

-- Associando Telefone e Endereço ao Fornecedor 
INSERT INTO fornecedor_telefone(id_fornecedor, id_telefone) VALUES(1, 1);
INSERT INTO fornecedor_endereco(id_fornecedor, id_endereco) VALUES(1, 1);
INSERT INTO fornecedor_telefone(id_fornecedor, id_telefone) VALUES(2, 2);
INSERT INTO fornecedor_endereco(id_fornecedor, id_endereco) VALUES(2, 2);
INSERT INTO fornecedor_telefone(id_fornecedor, id_telefone) VALUES(3, 3);
INSERT INTO fornecedor_endereco(id_fornecedor, id_endereco) VALUES(3, 3);
INSERT INTO fornecedor_telefone(id_fornecedor, id_telefone) VALUES(4, 4);
INSERT INTO fornecedor_endereco(id_fornecedor, id_endereco) VALUES(4, 4);

-- Inserção de Marca
insert into marca(nome, id_oculos) values('Ray-Ban', 1);
insert into marca(nome, id_oculos) values('Ray-Ban', 4);
insert into marca(nome, id_oculos) values('GUESS', 2);
insert into marca(nome, id_oculos) values('Tommy Hilfiger', 3);


-- inserção de óculos
insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade) values('C12A3', 'A2', '145', 85.00, 120.00, 10);
insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade) values('B2023', 'C6', '145', 78.00, 110.00, 5);
insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade) values('B2023', 'C5', '140', 99.90, 135.80, 2);
insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade) values('A62G3', 'B2', '50', 100.00, 110.00, 12);


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

insert into endereco(cep, bairro, rua, numero, complemento, cidade) values('77006-020', '110 Norte', 'alameda 15', '25', 'casa', 'Palmas');
insert into endereco(cep, bairro, rua, numero, complemento, cidade) values('77010-000', '106 Norte', 'Av.JK', '10', 'loja 1, Sapataria', 'Salvador');
insert into endereco(cep, bairro, rua, numero, complemento, cidade) values('77006-500', '204 Sul', 'alameda 02', '13', 'casa', 'Palmas');

insert into usuario_endereco (id_usuario, id_endereco) values(1, 1); 
insert into usuario_endereco (id_usuario, id_endereco) values(1, 2); 
insert into usuario_endereco (id_usuario, id_endereco) values(2, 3); 
