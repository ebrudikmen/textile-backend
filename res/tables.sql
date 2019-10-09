USE textile;

DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
    customer_id INT(10) NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) DEFAULT NULL,
    surname     VARCHAR(50) DEFAULT NULL,
    date        DATE    NOT NULL,
    PRIMARY KEY (customer_id),
    UNIQUE KEY customer_id_unique (customer_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    product_id INT(10) NOT NULL AUTO_INCREMENT,
    name       VARCHAR(50) DEFAULT NULL,
    quantity   INT(10)     DEFAULT NULL,
    price      INT(10)     DEFAULT NULL,
    PRIMARY KEY (product_id),
    UNIQUE KEY product_id_unique (product_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS supplier;
CREATE TABLE supplier
(
    supplier_id INT(10) NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) DEFAULT NULL,
    surname     VARCHAR(50) DEFAULT NULL,
    date        DATE    NOT NULL,
    PRIMARY KEY (supplier_id),
    UNIQUE KEY supplier_id_unique (supplier_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;