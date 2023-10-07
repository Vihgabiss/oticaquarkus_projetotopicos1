-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- inserção de óculos
insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade) values('C12A3', 'A2', '145', 85.00, 120.00, 10);
insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade) values('B2023', 'C6', '145', 78.00, 110.00, 5);
insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade) values('B2023', 'C5', '140', 99.90, 135.80, 2);
insert into oculos(referencia, cor, tamanho, precoCusto,precoVenda,quantidade) values('A62G3', 'B2', '50', 100.00, 110.00, 12);
 
-- inserção de fornecedor
insert into fornecedor(nome, telefone, email, endereco, cnpj) values ();
insert into fornecedor(nome, telefone, email, endereco, cnpj) values ();
insert into fornecedor(nome, telefone, email, endereco, cnpj) values ();
insert into fornecedor(nome, telefone, email, endereco, cnpj) values ();

-- inserção de marca
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


-- Inserção para Telefone
INSERT INTO telefone(id, codigoarea, numero) VALUES(nextval('seq_telefone'), '11', '999999999');

-- Inserção para Endereço
INSERT INTO endereco(id, cep, bairro, rua, numero, complemento, cidade) VALUES(nextval('seq_endereco'), '01234-567', 'Bairro Exemplo', 'Rua Exemplo', 123, 'Apto 456', 'Cidade Exemplo');

-- Inserção para Fornecedor
INSERT INTO fornecedor(id, nome, email, cnpj) VALUES(nextval('seq_fornecedor'), 'Fornecedor Exemplo', 'fornecedor@example.com', '00.000.000/0001-00');

-- Associando Telefone e Endereço ao Fornecedor (assumindo que os IDs são 1, pois são os primeiros registros inseridos)
INSERT INTO fornecedor_telefone(id_fornecedor, id_telefone) VALUES(1, 1);
INSERT INTO fornecedor_endereco(id_fornecedor, id_endereco) VALUES(1, 1);

-- Inserção para Marca
INSERT INTO marca(id, nome, id_fornecedor) VALUES(nextval('seq_marca'), 'Marca Exemplo', 1);

-- Inserção para Óculos
INSERT INTO oculos(id, referencia, cor, tamanho, precocusto, precovenda, quantidade, id_marca) VALUES(nextval('seq_oculos'), 'REF123', 'Azul', 'M', 100.00, 200.00, 50, 1);
