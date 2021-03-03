create table cashier (
id bigint unsigned not null auto_increment,
closing_date datetime,
opening_date datetime not null,
status tinyint not null,
total_value decimal(10,4),
value_reported decimal(10,4),
cards_value decimal(10,4),
money_value decimal(10,4),
primary key (id)
)Engine = InnoDB;

create table sale_cashier (
cashier_id bigint unsigned not null,
sale_id bigint unsigned not null,
constraint fk_sale_cashier_cashier foreign key (cashier_id) references cashier(id),
constraint fk_sale_cashier_sale foreign key (sale_id) references sale(id)
)engine = InnoDB;


alter table sale
add cashier_id bigint unsigned not null,
add constraint fk_sale_cashier foreign key (cashier_id) references cashier(id);