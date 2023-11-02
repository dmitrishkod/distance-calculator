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

insert into city(name,longtitude,latitude) values('Samara',324,365);
insert into city(name,longtitude,latitude) values('Moscow',134,246);
insert into city(name,longtitude,latitude) values('Omsk',6867,345);
insert into city(name,longtitude,latitude) values('Tolyatti',124,365);
insert into city(name,longtitude,latitude) values('Saint Petersburg',127,721);
insert into city(name,longtitude,latitude) values('Sochi',127,721);

insert into distance(from_city,to_city,distance) values(1,4,6453);
insert into distance(from_city,to_city,distance) values(2,5,3453);
insert into distance(from_city,to_city,distance) values(3,6,2343);