create database if not exists planetas;

DROP USER IF EXISTS 'usuario_planeta'@localhost;

flush privileges;

create user 'usuario_planeta'@localhost identified by '1234';

grant ALL ON `planetas`.* TO 'usuario_planeta'@localhost;

flush privileges;