create table s_exception.t_user
(
    id            serial not null
        constraint t_user_pkey
            primary key,
    name          text,
    email         text,
    password      text,
    activated     boolean                  default false,
    mobile        text,
    avatar        text,
    oauth         jsonb,
    organizations integer[],
    projects      integer[],
    created_at    timestamp with time zone default now(),
    updated_at    timestamp with time zone default now()
);

comment on column s_exception.t_user.id is '唯一主键';

comment on column s_exception.t_user.name is '用户昵称';

comment on column s_exception.t_user.email is '用户邮箱';

comment on column s_exception.t_user.password is '密码';

comment on column s_exception.t_user.activated is '激活状态';

comment on column s_exception.t_user.mobile is '手机号码';

comment on column s_exception.t_user.avatar is '用户头像';

comment on column s_exception.t_user.oauth is '第三方登录信息';

comment on column s_exception.t_user.organizations is 'user 所属的 organization (多对多)';

comment on column s_exception.t_user.projects is 'user 所属的 project (多对多)';

comment on column s_exception.t_user.created_at is '用户创建时间';

comment on column s_exception.t_user.updated_at is '用户更新时间';

alter table s_exception.t_user
    owner to postgres;

create table s_exception.t_organization
(
    id           serial  not null
        constraint t_organization_pk
            primary key,
    name         text    not null,
    introduction text,
    admin        integer not null,
    projects     integer[],
    count        integer                  default 0,
    created_at   timestamp with time zone default now(),
    updated_at   timestamp with time zone default now(),
    users        integer[]
);

comment on table s_exception.t_organization is '组织表';

comment on column s_exception.t_organization.id is '唯一主键';

comment on column s_exception.t_organization.name is '名称';

comment on column s_exception.t_organization.introduction is '简介';

comment on column s_exception.t_organization.projects is 'organization 所拥有的 project (一对多)';

comment on column s_exception.t_organization.count is 'organization 当前承载的 event 数';

comment on column s_exception.t_organization.users is 'organization 所拥有的 user (多对多)';

alter table s_exception.t_organization
    owner to postgres;

create unique index t_organization_id_uindex
    on s_exception.t_organization (id);

create table s_exception.t_project
(
    id                   serial not null
        constraint t_project_pk
            primary key,
    api_key              text,
    name                 text,
    type                 text,
    admin                integer,
    organization         integer,
    users                integer[],
    notification_rules   integer[],
    notification_setting integer,
    created_at           timestamp with time zone default now(),
    updated_at           timestamp with time zone default now()
);

comment on table s_exception.t_project is '项目表';

comment on column s_exception.t_project.name is '项目名	';

comment on column s_exception.t_project.type is '项目类型';

comment on column s_exception.t_project.admin is 'project 的管理员用户(多对一)';

comment on column s_exception.t_project.organization is 'project 所属的 organization (多对一)';

comment on column s_exception.t_project.users is 'project 所拥有的 users (多对多)';

comment on column s_exception.t_project.notification_rules is 'project 所拥有的 notification rules ';

comment on column s_exception.t_project.notification_setting is 'project 所拥有的 notification settings';

alter table s_exception.t_project
    owner to postgres;

create unique index t_project_id_uindex
    on s_exception.t_project (id);

create table s_exception.t_notification_setting
(
    id         serial not null
        constraint t_notification_setting_pk
            primary key,
    emails     jsonb,
    browser    jsonb,
    webhooks   jsonb,
    project    integer,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

comment on table s_exception.t_notification_setting is '通知设置表';

alter table s_exception.t_notification_setting
    owner to postgres;

create unique index t_notification_setting_id_uindex
    on s_exception.t_notification_setting (id);

create table s_exception.t_invite
(
    id           serial not null
        constraint t_invite_pk
            primary key,
    uuid         text,
    hash         text,
    auth         text,
    url          text,
    expires      timestamp with time zone,
    projects     integer[],
    organization integer,
    inviter      integer
);

comment on table s_exception.t_invite is '邀请表';

comment on column s_exception.t_invite.auth is 'invite 邀请人的权限';

comment on column s_exception.t_invite.url is 'invite 邀请链接';

comment on column s_exception.t_invite.expires is 'invite 过期时间';

comment on column s_exception.t_invite.projects is 'invite 链接所对应的项目';

comment on column s_exception.t_invite.organization is 'invite 链接所对应的团队';

comment on column s_exception.t_invite.inviter is 'invite 链接所对应的邀请人';

alter table s_exception.t_invite
    owner to postgres;

create unique index t_invite_id_uindex
    on s_exception.t_invite (id);

create table s_exception.t_notification_rule
(
    id         serial not null
        constraint t_notification_rule_pk
            primary key,
    name       text,
    data       jsonb,
    white_list jsonb,
    black_list jsonb,
    level      text,
    interval   integer                  default 600000,
    open       boolean                  default true,
    recently   timestamp with time zone,
    count      integer                  default 0,
    project    integer,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

comment on table s_exception.t_notification_rule is '提醒规则表';

comment on column s_exception.t_notification_rule.name is 'notification 名称';

comment on column s_exception.t_notification_rule.data is 'notification 规则';

comment on column s_exception.t_notification_rule.level is 'notification 级别';

comment on column s_exception.t_notification_rule.interval is 'notification 静默期
默认 10 分钟';

comment on column s_exception.t_notification_rule.open is 'notification 开关';

comment on column s_exception.t_notification_rule.recently is 'notification 最近通知的日期';

comment on column s_exception.t_notification_rule.count is 'notification 通知总数';

comment on column s_exception.t_notification_rule.project is 'notification 的 project (多对一)';

alter table s_exception.t_notification_rule
    owner to postgres;

create unique index t_notification_rule_id_uindex
    on s_exception.t_notification_rule (id);

create table s_exception.t_source_map
(
    id          serial not null
        constraint t_source_map_pk
            primary key,
    api_key     text,
    app_version text,
    app_type    text,
    data        jsonb,
    created_at  timestamp with time zone default now(),
    updated_at  timestamp with time zone default now()
);

comment on table s_exception.t_source_map is 'sourceMap表';

comment on column s_exception.t_source_map.data is '所有的 sourceMap 文件信息';

alter table s_exception.t_source_map
    owner to postgres;

create unique index t_source_map_id_uindex
    on s_exception.t_source_map (id);

create table s_exception.t_issue
(
    id           serial not null
        constraint t_issue_pkey
            primary key,
    intro        text,
    api_key      text   not null,
    type         text,
    metadata     jsonb,
    events       integer[],
    events_count integer,
    users        integer[],
    users_count  integer,
    created_at   timestamp with time zone default now(),
    updated_at   timestamp with time zone default now()
);

comment on column s_exception.t_issue.id is '唯一主键';

comment on column s_exception.t_issue.intro is '每个event的特征hash';

comment on column s_exception.t_issue.api_key is 'issue 对应的 apiKey 通过它拿到所属的 project';

comment on column s_exception.t_issue.type is '对应 event 的 type';

comment on column s_exception.t_issue.metadata is 'issue 所对应的 metadata';

comment on column s_exception.t_issue.events is '所对应的 events (id)';

comment on column s_exception.t_issue.events_count is ' 所对应的 events count';

comment on column s_exception.t_issue.users is '受此 issue 影响的用户';

comment on column s_exception.t_issue.users_count is '受此 issue 影响的用户 count';

comment on column s_exception.t_issue.created_at is '首条 event 的时间';

comment on column s_exception.t_issue.updated_at is '最近一条 event 的时间';

alter table s_exception.t_issue
    owner to postgres;

