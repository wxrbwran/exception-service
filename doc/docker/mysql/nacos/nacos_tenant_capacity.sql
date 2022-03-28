create table tenant_capacity
(
    id                bigint unsigned auto_increment comment '主键ID'
        primary key,
    tenant_id         varchar(128) default ''                not null comment 'Tenant ID',
    quota             int unsigned default 0                 not null comment '配额，0表示使用默认值',
    `usage`           int unsigned default 0                 not null comment '使用量',
    max_size          int unsigned default 0                 not null comment '单个配置大小上限，单位为字节，0表示使用默认值',
    max_aggr_count    int unsigned default 0                 not null comment '聚合子配置最大个数',
    max_aggr_size     int unsigned default 0                 not null comment '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    max_history_count int unsigned default 0                 not null comment '最大变更历史数量',
    gmt_create        datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    constraint uk_tenant_id
        unique (tenant_id)
)
    comment '租户容量信息表' collate = utf8_bin;

