create table permissions
(
    role     varchar(50)  not null,
    resource varchar(255) not null,
    action   varchar(8)   not null,
    constraint uk_role_permission
        unique (role, resource, action)
);

