drop database `distance-calculator`;
CREATE DATABASE `distance-calculator`;

USE `distance-calculator`;

insert into city(name,longtitude,latitude) values('Samara',324,365);
insert into city(name,longtitude,latitude) values('Moscow',134,246);
insert into city(name,longtitude,latitude) values('Omsk',6867,345);
insert into city(name,longtitude,latitude) values('Tolyatti',124,365);
insert into city(name,longtitude,latitude) values('Saint Petersburg',127,721);
insert into city(name,longtitude,latitude) values('Sochi',127,721);

insert into distance(from_city,to_city,distance) values(1,4,6453);
insert into distance(from_city,to_city,distance) values(2,5,3453);
insert into distance(from_city,to_city,distance) values(3,6,2343);