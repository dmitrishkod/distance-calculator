drop database `distance-calculator`;
CREATE DATABASE `distance-calculator`;

USE `distance-calculator`;

CREATE TABLE IF NOT EXISTS city (
                                    id INT(11) NOT NULL AUTO_INCREMENT,
                                    name VARCHAR(255) NOT NULL,
                                    latitude DECIMAL(10,6) NOT NULL,
                                    longtitude DECIMAL(10,6) NOT NULL,
                                    PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS distance (
                                        id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        from_city INT(11) UNSIGNED NOT NULL,
                                        to_city INT(11) UNSIGNED NOT NULL,
                                        distance BIGINT(21) NOT NULL,
                                        PRIMARY KEY(`id`),
                                        KEY(from_city),
                                        KEY(to_city),
                                        FOREIGN KEY (from_city)
                                            REFERENCES city(id)
                                            ON DELETE SET NULL ,
                                        FOREIGN KEY (to_city)
                                            REFERENCES city(id)
                                            ON DELETE set null
);

insert into city(name,longtitude,latitude) values('SMR',324,365);
insert into city(name,longtitude,latitude) values('MSK',134,246);
insert into city(name,longtitude,latitude) values('OMSK',6867,345);
insert into city(name,longtitude,latitude) values('TLT',124,365);
insert into city(name,longtitude,latitude) values('SPB',127,721);

insert into distance(from_city,to_city,distance) values(1,2,6453);
insert into distance(from_city,to_city,distance) values(2,3,3453);
insert into distance(from_city,to_city,distance) values(4,5,2343);