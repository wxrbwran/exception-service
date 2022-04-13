create table tenant_info
(
    id            bigint auto_increment comment 'id'
        primary key,
    kp            varchar(128)            not null comment 'kp',
    tenant_id     varchar(128) default '' null comment 'tenant_id',
    tenant_name   varchar(128) default '' null comment 'tenant_name',
    tenant_desc   varchar(256)            null comment 'tenant_desc',
    create_source varchar(32)             null comment 'create_source',
    gmt_create    bigint                  not null comment '创建时间',
    gmt_modified  bigint                  not null comment '修改时间',
    constraint uk_tenant_info_kptenantid
        unique (kp, tenant_id)
)
    comment 'tenant_info' collate = utf8_bin;

create index idx_tenant_id
    on tenant_info (tenant_id);

INSERT INTO nacos.tenant_info (id, kp, tenant_id, tenant_name, tenant_desc, create_source, gmt_create, gmt_modified) VALUES (1, '1', 'dev', 'dev', 'dev', 'nacos', 1641955615088, 1641955615088);
INSERT INTO nacos.tenant_info (id, kp, tenant_id, tenant_name, tenant_desc, create_source, gmt_create, gmt_modified) VALUES (2, '1', 'test', 'test', 'test', 'nacos', 1641955622817, 1641955622817);
INSERT INTO nacos.tenant_info (id, kp, tenant_id, tenant_name, tenant_desc, create_source, gmt_create, gmt_modified) VALUES (3, '1', 'prod', 'prod', 'prod', 'nacos', 1641955629443, 1641955629443);
INSERT INTO nacos.tenant_info (id, kp, tenant_id, tenant_name, tenant_desc, create_source, gmt_create, gmt_modified) VALUES (4, '1', 'seata', 'seata', 'seata', 'nacos', 1641955635824, 1641955635824);
INSERT INTO nacos.tenant_info (id, kp, tenant_id, tenant_name, tenant_desc, create_source, gmt_create, gmt_modified) VALUES (5, '1', 'master', 'master', 'master', 'nacos', 1642239088151, 1642239088151);