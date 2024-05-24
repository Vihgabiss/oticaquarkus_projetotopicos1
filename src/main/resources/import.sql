-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

--Inserção de Marca
insert into marca(nome) values('Ray-Ban');
insert into marca(nome) values('GUESS');
insert into marca(nome) values('Tommy Hilfiger');

-- Inserção para Fabricante
insert into fabricante(nome, telefone, email, endereco, cnpj) values ('LuxOtica',  '6848464511', 'luxotica@gmail.com','110 norte', '11.111.111/0001-00');
insert into fabricante(nome, telefone, email, endereco, cnpj) values ('Safilo',  '6848464511', 'safilo@gmail.com', '110 norte', '22.222.222/0001-00');

insert into colecao(nome, descricao, dataLancamento) values('Coleção Verão 2023', 'Nova coleção de óculos de sol para o verão', '2023-06-21');
insert into colecao (nome, descricao, dataLancamento) values('Coleção Inverno 2023', 'Nova coleção de óculos de sol para o inverno', '2023-12-21');

insert into estilooculos(nome, descricao) values('Aviador', 'Caracterizados por uma forma de gota invertida, lentes grandes e uma ponte dupla sobre o nariz. Popularizados na década de 1930, são conhecidos por seu estilo clássico e elegante.');
insert into estilooculos(nome, descricao) values('Retrô/Vintage', 'Inspirados por estilos de décadas passadas, os óculos retrô ou vintage capturam a nostalgia da moda do passado. Podem apresentar uma variedade de formas e detalhes, incluindo armações redondas, lentes coloridas e detalhes decorativos.');

insert into fabricante_marca (id_fabricante, id_marca) values(1, 1);
insert into fabricante_marca (id_fabricante, id_marca) values(1, 2);
insert into fabricante_marca (id_fabricante, id_marca) values(2, 3);

-- inserção de óculos
insert into armacao (referencia, cor, tamanho, precoCusto, precoVenda, quantidade, id_fabricante,tipo_aro_armacao, material_armacao, nomeImagem, id_estiloOculos,id_colecao) values ('C12A3', 'A2', '145', 85.00, 120.00, 10, 1,1,1, 'armacao1.jpg', 1,1);
insert into armacao (referencia, cor, tamanho, precoCusto, precoVenda, quantidade, id_fabricante,tipo_aro_armacao, material_armacao, nomeImagem, id_estiloOculos,id_colecao) values ('B2023', 'C6', '145', 78.00, 110.00, 5, 2,3,2,'armacao2.jpg', 2, 2);
insert into armacao (referencia, cor, tamanho, precoCusto, precoVenda, quantidade, id_fabricante,tipo_aro_armacao, material_armacao, nomeImagem, id_estiloOculos,id_colecao) values ('B2023', 'C5', '140', 99.90, 135.80, 2, 2,2,3,'armacao3.jpg', 2, 1);
insert into armacao (referencia, cor, tamanho, precoCusto, precoVenda, quantidade, id_fabricante,tipo_aro_armacao, material_armacao, nomeImagem, id_estiloOculos,id_colecao) values ('A62G3', 'B2', '50', 100.00, 110.00, 12, 1,1,2, 'armacao4.jpg', 1,2);
insert into armacaograu(id) values (1);
insert into armacaosolar(id, tipo_lente_solar) values (2, 2);
insert into armacaosolar(id, tipo_lente_solar) values (3, 1);
insert into armacaoclipon(id, tipo_lente_solar) values (4, 3);

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


insert into colecao(nome, descricao, dataLancamento) values('Coleção Verão 2023', 'Nova coleção de óculos de sol para o verão', '2023-06-21');
insert into colecao (nome, descricao, dataLancamento) values('Coleção Inverno 2023', 'Nova coleção de óculos de sol para o inverno', '2023-12-21');

insert into endereco(cep, bairro, rua, numero, complemento, id_cidade, idUsuario) values('77006-020', '110 Norte', 'alameda 15', 25, 'casa', 1, 1);
insert into endereco(cep, bairro, rua, numero, complemento, id_cidade, idUsuario) values('77010-000', '106 Norte', 'Av.JK', 10, 'loja 1, Sapataria', 1, 1);
insert into endereco(cep, bairro, rua, numero, complemento, id_cidade, idUsuario) values('77006-500', '204 Sul', 'alameda 02', 13, 'casa', 2, 2);

insert into usuario_endereco (id_usuario, id_endereco) values(1, 1); 
insert into usuario_endereco (id_usuario, id_endereco) values(1, 2); 
insert into usuario_endereco (id_usuario, id_endereco) values(2, 3); 

-- teste de venda

insert into venda(id_usuario, dataHora, tipoPagamento, valorTotal, statusVenda) values (1, '2023-11-30T14:57:14.1212501', 1, 100.00, 1);
insert into venda(id_usuario, dataHora, tipoPagamento, valorTotal, statusVenda) values (1, '2023-11-30T14:57:14.1212501', 3, 200.00, 2);
insert into venda(id_usuario, dataHora, tipoPagamento, valorTotal, statusVenda) values (2, '2023-11-30T14:57:14.1212501', 1, 100.00, 1);
insert into venda(id_usuario, dataHora, tipoPagamento, valorTotal, statusVenda) values (2, '2023-11-30T14:57:14.1212501', 3, 200.00, 2);

insert into itemvenda(preco, quantidade, id_armacao, id_venda) values (200.00, 2, 1, 1);
insert into itemvenda(preco, quantidade, id_armacao, id_venda) values (400.00, 3, 2, 1);
insert into itemvenda(preco, quantidade, id_armacao, id_venda) values (200.00, 2, 3, 2);
insert into itemvenda(preco, quantidade, id_armacao, id_venda) values (400.00, 3, 4, 2);
 
-- inserção de evento

insert into evento(descricao, nome) values ('Comemoração do mês da mulher', 'Mês da Mulher - MARÇO');
insert into evento(descricao, nome) values ('Feliz Natal', 'Natal - Dezembro');

insert into cupom (nome, descricao, dataLancamento, id_evento) values ('Cupom do Mês da Mulher', 'Este cupom oferece 20% de desconto em todos os produtos', '2024-04-04', 1);
insert into cupom (nome, descricao, dataLancamento, id_evento) values ('Cupom de Natal', 'Use este cupom para obter frete grátis no Natal!', '2024-03-30', 2);