create table form_payment (
id bigint unsigned not null auto_increment,
description varchar(45) not null,
primary key (id)
)engine = InnoDB;


create table installment_sale (
id bigint unsigned not null auto_increment,
client_id bigint unsigned not null,
sale_id bigint unsigned not null,
due_date datetime,
date_payment datetime,
status tinyint not null,
date_creation datetime not null,
date_update datetime null,
primary key (id),
constraint fk_installment_sale_client
foreign key (client_id) references client (id),
constraint fk_installment_sale_sale
foreign key (sale_id) references sale (id)
)engine = InnoDB;


alter table sale 
add form_payment_id bigint unsigned not null,
add constraint fk_sale_form_payment
foreign key (form_payment_id) references form_payment(id);