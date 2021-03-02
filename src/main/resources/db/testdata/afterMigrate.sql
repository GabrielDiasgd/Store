set foreign_key_checks = 0;

delete from address;
delete from category;
delete from city;
delete from client;
delete from client_address;
delete from client_phone;
delete from permission;
delete from permission_profile;
delete from phone;
delete from product_sale;
delete from product;
delete from profile;
delete from provider;
delete from sale;
delete from state;
delete from user;
delete from subcategory;
delete from brand;
delete from form_payment;
delete from installment_sale;

set foreign_key_checks = 1;

alter table address auto_increment = 1;
alter table category auto_increment = 1;
alter table city auto_increment = 1;
alter table client auto_increment = 1;
alter table permission auto_increment = 1;
alter table phone auto_increment = 1;
alter table product_sale auto_increment = 1;
alter table product auto_increment = 1;
alter table profile auto_increment = 1;
alter table provider auto_increment = 1;
alter table sale auto_increment = 1;
alter table state auto_increment = 1;
alter table user auto_increment = 1;
alter table subcategory auto_increment = 1;
alter table brand auto_increment = 1;
alter table form_payment auto_increment = 1;
alter table installment_sale auto_increment = 1;

insert into category (description, date_creation, date_update) values ("Roupas", utc_timestamp(), utc_timestamp());
insert into category (description, date_creation, date_update) values ("Calçados", utc_timestamp(), utc_timestamp());
insert into category (description, date_creation, date_update) values ("Acessórios", utc_timestamp(), utc_timestamp());
insert into category (description, date_creation, date_update) values ("Equipamentos", utc_timestamp(), utc_timestamp());

insert into subcategory (name,date_creation, date_update, category_id ) values ("Botas",utc_timestamp(), utc_timestamp(), 2);
insert into subcategory (name,date_creation, date_update, category_id ) values ("Bermudas",utc_timestamp(), utc_timestamp(),1);
insert into subcategory (name,date_creation, date_update, category_id ) values ("Relogio",utc_timestamp(), utc_timestamp(),3);
insert into subcategory (name,date_creation, date_update, category_id ) values ("Bolas de Basquete",utc_timestamp(), utc_timestamp(),4);

insert into brand (name, date_creation, date_update) values ("Nike", utc_timestamp(), utc_timestamp());
insert into brand (name, date_creation, date_update) values ("Adidas", utc_timestamp(), utc_timestamp());
insert into brand (name, date_creation, date_update) values ("Mormaii", utc_timestamp(), utc_timestamp());
insert into brand (name, date_creation, date_update) values ("Spalding", utc_timestamp(), utc_timestamp());

insert into state (name) values ("São Paulo");
insert into state (name) values ("Rio de Janeiro");
insert into state (name) values ("Paraná");
insert into state (name) values ("Brasilia");

insert into city (name, state_id) values ("Assis", 1);
insert into city (name, state_id) values ("Rio de Janeiro",2);
insert into city (name,state_id) values ("São Paulo",1);
insert into city (name,state_id) values ("Curitiba", 3);

insert into address (street, number, neighborhood,date_creation, date_update, cep, city_id) values ("Rua Vicente Bagnoli", "140", "Vila Cluadia", utc_timestamp(), utc_timestamp(), "19815310", 1);
insert into address (street, number, neighborhood,date_creation, date_update, cep, city_id) values ("Rua Brasil", "500", "Centro", utc_timestamp(), utc_timestamp(), "19815310", 1);
insert into address (street, number, neighborhood,date_creation, date_update, cep, city_id) values ("Av dom Antonio", "1425", "Centro", utc_timestamp(), utc_timestamp(), "19815310", 1);


insert into provider (name, cnpj, email, note, active, site, address_id, date_creation, date_update) values ("Nike", "1324165731531", "nike@gmail.com", "", 1, "nike.com.br",  2, utc_timestamp(), utc_timestamp());
insert into provider (name, cnpj, email, note, active, site, address_id, date_creation, date_update) values ("Adidas", "2968798631321", "adidas@gmail.com", "", 1, "adidas.com.br", 1, utc_timestamp(), utc_timestamp());
insert into provider (name, cnpj, email, note, active, site, address_id, date_creation, date_update) values ("Puma", "98765435132", "puma@gmail.com", "", 1, "puma.com.br", 3, utc_timestamp(), utc_timestamp());


insert into product (name, description, stock, price, buy_price, active, date_last_sale, date_last_purchase, date_creation, date_update, provider_id, category_id, subcategory_id, brand_id) 
values ("Camiseta Nike", "", 10, 79, 59.90, 1, utc_timestamp(), utc_timestamp(),utc_timestamp(), utc_timestamp(), 1,1,1,1);
insert into product (name, description, stock, price, buy_price, active, date_creation, provider_id, category_id,subcategory_id, brand_id) 
values ("Camiseta Adidas", "", 15, 89.90, 50, 1, utc_timestamp(),2,1,1,2);
insert into product (name, description, stock, price, buy_price, active, date_creation, provider_id, category_id,subcategory_id, brand_id)
 values ( "Nike SB", "", 8, 279, 160, 1, utc_timestamp(),2,1,2,1);
insert into product (name, description, stock, price, buy_price, active,  date_creation,  provider_id, category_id, subcategory_id, brand_id) 
values ("Bola de basquete", "", 10, 99.99, 60, 1, utc_timestamp(),2,3,4, 4);


insert into client (name, cpf, rg, birth_date, email, active, client_type, client_status, date_creation, date_update)
values ("Gabriel Dias", "447191990839", "434055839", 19980525, "gabriel@gmail.com", 1, 1,1, utc_timestamp(), utc_timestamp());

insert into client (name, cpf, rg, birth_date, email, active, client_type, client_status, date_creation, date_update)
values ("Silvana Dias", "11081670800", "1654643211351", 19640213, "silvana@gmail.com", 1, 1,1, utc_timestamp(), utc_timestamp());
 
insert into client (name, cpf, rg, birth_date, email, active, client_type, client_status, date_creation, date_update)
values ("Noel", "05161733837", "687983132", 19640602, "noel@gmail.com", 1, 1,0, utc_timestamp(), utc_timestamp());


insert into profile (description, date_creation, date_update) values ("Administrador", utc_timestamp(), utc_timestamp());

insert into user (name, cpf, rg, email, password, active, date_creation, date_update, profile_id )  values ("Gabriel", "44719190839", "434055839", "gabriel@gmail.com", "g25d05o98", 1 , utc_timestamp(), utc_timestamp(), 1);

insert into client_address (client_id, address_id) values (1,1);
insert into client_address (client_id, address_id) values (1,2);
insert into client_address (client_id, address_id) values (3,3);

insert into form_payment (description) values ("Dinheiro");
insert into form_payment (description) values ("Cartão de Crédito");
insert into form_payment (description) values ("Cartão de Débito");
insert into form_payment (description) values ("Cheque");
insert into form_payment (description) values ("Pix");

