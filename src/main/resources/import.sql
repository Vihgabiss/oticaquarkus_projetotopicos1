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