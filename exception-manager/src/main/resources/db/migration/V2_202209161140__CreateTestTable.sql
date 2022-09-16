create table s_exception.t_test_flyaway
(
    id   bigserial
        primary key
        unique,
    name varchar(100) not null
);

comment on table s_exception.t_test_flyaway is '测试flyaway';

comment on column s_exception.t_test_flyaway.name is '姓名';

