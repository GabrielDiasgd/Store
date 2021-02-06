CREATE TABLE IF NOT EXISTS state (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS city (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `state_id` bigint unsigned NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_city_state_id` (`state_id` ASC),
  CONSTRAINT `fk_city_state`
    FOREIGN KEY (`state_id`)
    REFERENCES state (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS address (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(60) NOT NULL,
  `number` VARCHAR(10) NOT NULL,
  `neighborhood` VARCHAR(60) NOT NULL,
  `complement` VARCHAR(60) NULL,
   `cep` VARCHAR(45) NOT NULL,
   `city_id` bigint unsigned NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  index `fk_address_city_id` (`city_id` ASC),
  constraint `fk_address_city`
  foreign key (`city_id`)
  references city(`id`)
  )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS provider (
  `id`  bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cnpj` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `phone` varchar(12) null,
  `cell_phone` varchar(12) null,
  `sac` varchar(45) null,
  `site` varchar(30) null,
  `contact` varchar (45),
  `note` VARCHAR(90) NULL,
  `active` TINYINT NOT NULL,
  `address_id` bigint unsigned  NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  index `fk_povider_address_id` (`address_id` ASC),
  CONSTRAINT `fk_provider_address`
  Foreign key (`address_id`)
  references address(`id`)
  )
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS category (
  `id` bigint unsigned  NOT NULL auto_increment,
  `description` VARCHAR(45) NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS product (
  `id` bigint unsigned  NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(120) NULL,
  `stock` bigint unsigned  NULL,
  `price` DECIMAL(10,4) NULL,
  `buy_price` DECIMAL(10,4) NULL,
  `active` TINYINT(1) NOT NULL,
  `date_last_sale` DATETIME NULL,
  `date_last_purchase` DATETIME NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  `provider_id` bigint unsigned  NOT NULL,
  `category_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_provider_id` (`provider_id` ASC),
  INDEX `fk_product_category_id` (`category_id` ASC),
  CONSTRAINT `fk_product_provider`
    FOREIGN KEY (`provider_id`)
    REFERENCES provider(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_category`
    FOREIGN KEY (`category_id`)
    REFERENCES category (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS client (
  `id` bigint unsigned  NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(70) NOT NULL,
  `cpf` VARCHAR(20) NULL,
  `rg` VARCHAR(15) NULL,
  `birth_date` DATETIME NULL,
  `email` VARCHAR(60) NULL,
  `active` TINYINT(1) NOT NULL,
  `note` VARCHAR(100) NULL,
  `client_type` TINYINT(3) NOT NULL,
  `client_status` TINYINT(3) NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS profile (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS user (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `cpf` VARCHAR(20) NULL,
  `rg` VARCHAR(15) NULL,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(120) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  `profile_id` BIGINT unsigned  NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_profile_id` (`profile_id` ASC),
  CONSTRAINT `fk_user_profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES profile (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS sale (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `date_sale` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `subtotal` DECIMAL(10,4) NOT NULL,
  `total_value` DECIMAL(10,4) NOT NULL,
  `code_sale` VARCHAR(45) NULL,
  `status_sale` TINYINT(2) NULL,
  `change` DECIMAL(10,4) NULL,
  `client_id` bigint unsigned  NOT NULL,
  `user_id` bigint unsigned  NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sale_client_id` (`client_id` ASC) ,
  INDEX `fk_sale_user_id` (`user_id` ASC),
  CONSTRAINT `fk_sale_client`
    FOREIGN KEY (`client_id`)
    REFERENCES client (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sale_user`
    FOREIGN KEY (`user_id`)
    REFERENCES user (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS product_sale (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint unsigned  NOT NULL,
  `sale_id` bigint unsigned  NOT NULL,
  `unitary_value` DECIMAL(10,4) NOT NULL,
  `total_value` DECIMAL(10,4) NOT NULL,
  `quantity` bigint NOT NULL,
  INDEX `fk_product_has_sale_sale_id` (`sale_id` ASC) ,
  INDEX `fk_product_has_sale_product_id` (`product_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_product_has_sale_product`
    FOREIGN KEY (`product_id`)
    REFERENCES product (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_has_sale_sale`
    FOREIGN KEY (`sale_id`)
    REFERENCES sale (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS client_address (
  `client_id` bigint unsigned  NOT NULL,
  `address_id` bigint unsigned  NOT NULL,
  INDEX `fk_client_has_address_address_id` (`address_id` ASC) ,
  INDEX `fk_client_has_address_client_id` (`client_id` ASC),
  CONSTRAINT `fk_client_has_address_client`
    FOREIGN KEY (`client_id`)
    REFERENCES client (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_has_address_address`
    FOREIGN KEY (`address_id`)
    REFERENCES address (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS phone (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(20) NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_update` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS client_phone (
  `client_id` bigint unsigned  NOT NULL,
  `phone_id` bigint unsigned  NOT NULL,
  INDEX `fk_client_has_phone_phone_id` (`phone_id` ASC) ,
  INDEX `fk_client_has_phone_client_id` (`client_id` ASC),
  CONSTRAINT `fk_client_has_phone_client`
    FOREIGN KEY (`client_id`)
    REFERENCES client (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_has_phone_phone`
    FOREIGN KEY (`phone_id`)
    REFERENCES phone (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS permission (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `descripiton` VARCHAR(45) NOT NULL,
  `date_creation` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_upadate` DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS permission_profile(
  `permission_id` bigint unsigned NOT NULL,
  `profile_id` bigint unsigned  NOT NULL,
  INDEX `fk_permission_has_perfil_perfil_id` (`profile_id` ASC),
  INDEX `fk_permission_has_perfil_permission_id` (`permission_id` ASC),
  CONSTRAINT `fk_permission_has_perfil_permission`
    FOREIGN KEY (`permission_id`)
    REFERENCES permission (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_permission_has_profile_profile`
    FOREIGN KEY (`profile_id`)
    REFERENCES profile (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;