create table roles
(
    username varchar(50) not null,
    role     varchar(50) not null,
    constraint idx_user_role
        unique (username, role)
);

INSERT INTO nacos.roles (username, role) VALUES ('nacos', 'ROLE_ADMIN');