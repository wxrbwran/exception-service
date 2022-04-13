create table config_info_beta
(
    id           bigint auto_increment comment 'id'
        primary key,
    data_id      varchar(255)                           not null comment 'data_id',
    group_id     varchar(128)                           not null comment 'group_id',
    app_name     varchar(128)                           null comment 'app_name',
    content      longtext                               not null comment 'content',
    beta_ips     varchar(1024)                          null comment 'betaIps',
    md5          varchar(32)                            null comment 'md5',
    gmt_create   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    src_user     text                                   null comment 'source user',
    src_ip       varchar(50)                            null comment 'source ip',
    tenant_id    varchar(128) default ''                null comment '租户字段',
    constraint uk_configinfobeta_datagrouptenant
        unique (data_id, group_id, tenant_id)
)
    comment 'config_info_beta' collate = utf8_bin;

