create table users
(
    username varchar(50)  not null
        primary key,
    password varchar(500) not null,
    enabled  tinyint(1)   not null
);

INSERT INTO nacos.users (username, password, enabled) VALUES ('nacos', '$2a$10$uQ2LotcyTYwfGrjxcZmup.1fqYRa8KcQJ4gDbbfCzJa1RD6p7bI.i', 1);