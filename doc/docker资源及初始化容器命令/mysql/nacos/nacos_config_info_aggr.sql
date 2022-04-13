create table config_info_aggr
(
    id           bigint auto_increment comment 'id'
        primary key,
    data_id      varchar(255)            not null comment 'data_id',
    group_id     varchar(255)            not null comment 'group_id',
    datum_id     varchar(255)            not null comment 'datum_id',
    content      longtext                not null comment '内容',
    gmt_modified datetime                not null comment '修改时间',
    app_name     varchar(128)            null,
    tenant_id    varchar(128) default '' null comment '租户字段',
    constraint uk_configinfoaggr_datagrouptenantdatum
        unique (data_id, group_id, tenant_id, datum_id)
)
    comment '增加租户字段' collate = utf8_bin;

