create table subcategory (
id bigint unsigned not null auto_increment,
name varchar(40) not null,
date_creation datetime not null,
date_update datetime null,
category_id bigint unsigned not null,
primary key (id),
constraint fk_subcategory_category
foreign key (category_id)
references category (id)
)engine = InnoDB;

 create table brand (
 id bigint unsigned not null auto_increment,
 name varchar(40) not null,
 date_creation datetime not null,
 date_update datetime null,
 primary key (id)
 )engine = InnoDB;
 
 
 alter table sale
 add discount_percentage bigint unsigned null after subtotal;
alter table sale
add discount_value decimal(10,4) null after discount_percentage;

alter table sale
add amount_paid decimal(10,4) not null after total_value;

alter table sale
add type_sale tinyint not null after status_sale;

alter table product
add gender tinyint null;

alter table product
add subcategory_id bigint unsigned not null after category_id;

alter table product
add constraint fk_product_subcategory foreign key (subcategory_id)
references subcategory(id);

alter table product
add brand_id bigint unsigned not null;

alter table product
add constraint fk_product_brand foreign key (brand_id) references brand(id);