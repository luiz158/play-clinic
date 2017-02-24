# --- !Ups

CREATE TABLE owners (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    city VARCHAR(255),
    telephone VARCHAR(255),
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE owners;