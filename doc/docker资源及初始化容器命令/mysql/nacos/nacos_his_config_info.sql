create table his_config_info
(
    id           bigint(64) unsigned                    not null,
    nid          bigint unsigned auto_increment
        primary key,
    data_id      varchar(255)                           not null,
    group_id     varchar(128)                           not null,
    app_name     varchar(128)                           null comment 'app_name',
    content      longtext                               not null,
    md5          varchar(32)                            null,
    gmt_create   datetime     default CURRENT_TIMESTAMP not null,
    gmt_modified datetime     default CURRENT_TIMESTAMP not null,
    src_user     text                                   null,
    src_ip       varchar(50)                            null,
    op_type      char(10)                               null,
    tenant_id    varchar(128) default ''                null comment '租户字段'
)
    comment '多租户改造' collate = utf8_bin;

create index idx_did
    on his_config_info (data_id);

create index idx_gmt_create
    on his_config_info (gmt_create);

create index idx_gmt_modified
    on his_config_info (gmt_modified);

INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 1, 'exception-dashboard-dev', 'DEFAULT_GROUP', '', 'server:
  port: 9999
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8
  tomcat:
    connection-timeout: 300000


spring:
  main:
    allow-bean-definition-overriding: true
  mvc:
    async:
      request-timeout: 20000
  # database
  datasource:
    url: jdbc:postgresql://121.37.67.93:5432/dev
    username: postgres
    password: 123456
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 30000
      idle-timeout: 30000
      minimum-idle: 10
      maximum-pool-size: 10
      pool-prepared-statements: true
      max-open-prepared-statements: 100

mybatis:
  mapper-locations: classpath:mappers/*.xml
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000

', 'e6eb07165188a893ff9f8f2811de9607', '2022-01-12 02:51:37', '2022-01-12 02:51:37', null, '118.247.206.0', 'I', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 2, 'exception-transfer-dev.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 9997
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8

spring:
  main:
    allow-bean-definition-overriding: true
  # database
  redis:
    host:103.105.200.216
    port: 6379

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000', 'a1fdc90a192141d5e2fc332e5434d819', '2022-01-12 02:53:14', '2022-01-12 02:53:14', null, '118.247.206.0', 'I', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 3, 'exception-manager-dev', 'DEFAULT_GROUP', '', 'server:
  port: 9998
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8

spring:
  main:
    allow-bean-definition-overriding: true
  # redis
  redis:
    host:103.105.200.216
    port: 6379
  # database
  datasource:
    url: jdbc:postgresql://121.37.67.93:5432/dev
    username: postgres
    password: 123456
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 30000
      idle-timeout: 30000
      minimum-idle: 10
      maximum-pool-size: 10
      pool-prepared-statements: true
      max-open-prepared-statements: 100
  # elasticsearch
  data:
    elasticsearch:
      host:103.105.200.216
      port: 9200
  #kafka配置
  kafka:
    #这里改为你的kafka服务器ip和端口号
    bootstrap-servers: 121.37.67.93:9092
    #=============== producer  =======================
    producer:
      #如果该值大于零时，表示启用重试失败的发送次数
      retries: 0
      #每当多个记录被发送到同一分区时，生产者将尝试将记录一起批量处理为更少的请求，默认值为16384(单位字节)
      batch-size: 16384
      #生产者可用于缓冲等待发送到服务器的记录的内存总字节数，默认值为3355443
      buffer-memory: 33554432
      #key的Serializer类，实现类实现了接口org.apache.kafka.common.serialization.Serializer
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      #value的Serializer类，实现类实现了接口org.apache.kafka.common.serialization.Serializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer

mybatis:
  mapper-locations: classpath:mappers/*.xml
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000
', 'a9c59c1ea136f8fbd1ed355091d5ba47', '2022-01-12 02:55:25', '2022-01-12 02:55:26', null, '118.247.206.0', 'I', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 4, 'exception-cloud-gateway-dev.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 10086
spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: false # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由
      routes:
        - id: exception-transfer
          uri: lb://exception-transfer
          predicates:
            - Path=/transfer/** #断言,路径相匹配的进行路由
          filters:
            - RewritePath=/transfer/(?<segment>.*), /$\\{segment}
        - id: exception-manager
          uri: lb://exception-manager
          predicates:
            - Path=/manager/**
          filters:
            - RewritePath=/manager/(?<segment>.*), /$\\{segment}
        - id: exception-dashboard
          uri: lb://exception-dashboard
          predicates:
            - Path=/dashboard/**
          filters:
            - RewritePath=/dashboard/(?<segment>.*), /$\\{segment}
', 'a03ee85e7c26e71fe7e9000e88590e73', '2022-01-12 02:57:55', '2022-01-12 02:57:56', null, '118.247.206.0', 'I', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 5, 'exception-transfer-dev.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 9997
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8

spring:
  main:
    allow-bean-definition-overriding: true
  # database
  redis:
    host:103.105.200.216
    port: 6379

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000', 'a1fdc90a192141d5e2fc332e5434d819', '2022-01-12 02:58:07', '2022-01-12 02:58:07', null, '118.247.206.0', 'I', 'dev');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 6, 'exception-transfer-test.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 9997
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8

spring:
  main:
    allow-bean-definition-overriding: true
  # database
  redis:
    host:103.105.200.216
    port: 6379

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000', 'a1fdc90a192141d5e2fc332e5434d819', '2022-01-12 08:55:47', '2022-01-12 08:55:48', null, '118.247.206.0', 'I', 'test');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (6, 7, 'exception-transfer-test.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 9997
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8

spring:
  main:
    allow-bean-definition-overriding: true
  # database
  redis:
    host:103.105.200.216
    port: 6379

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000', 'a1fdc90a192141d5e2fc332e5434d819', '2022-01-12 08:56:04', '2022-01-12 08:56:04', 'nacos', '118.247.206.0', 'U', 'test');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 8, 'exception-dashboard-dev.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 9999
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8
  tomcat:
    connection-timeout: 300000


spring:
  main:
    allow-bean-definition-overriding: true
  mvc:
    async:
      request-timeout: 20000
  # database
  datasource:
    url: jdbc:postgresql://121.37.67.93:5432/dev
    username: postgres
    password: 123456
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 30000
      idle-timeout: 30000
      minimum-idle: 10
      maximum-pool-size: 10
      pool-prepared-statements: true
      max-open-prepared-statements: 100

mybatis:
  mapper-locations: classpath:mappers/*.xml
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000

', 'e6eb07165188a893ff9f8f2811de9607', '2022-01-15 09:20:53', '2022-01-15 09:20:53', null, '111.192.79.149', 'I', 'dev');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 9, 'exception-manager-dev.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 9998
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8

spring:
  main:
    allow-bean-definition-overriding: true
  # redis
  redis:
    host:103.105.200.216
    port: 6379
  # database
  datasource:
    url: jdbc:postgresql://121.37.67.93:5432/dev
    username: postgres
    password: 123456
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 30000
      idle-timeout: 30000
      minimum-idle: 10
      maximum-pool-size: 10
      pool-prepared-statements: true
      max-open-prepared-statements: 100
  # elasticsearch
  data:
    elasticsearch:
      host:103.105.200.216
      port: 9200
  #kafka配置
  kafka:
    #这里改为你的kafka服务器ip和端口号
    bootstrap-servers: 121.37.67.93:9092
    #=============== producer  =======================
    producer:
      #如果该值大于零时，表示启用重试失败的发送次数
      retries: 0
      #每当多个记录被发送到同一分区时，生产者将尝试将记录一起批量处理为更少的请求，默认值为16384(单位字节)
      batch-size: 16384
      #生产者可用于缓冲等待发送到服务器的记录的内存总字节数，默认值为3355443
      buffer-memory: 33554432
      #key的Serializer类，实现类实现了接口org.apache.kafka.common.serialization.Serializer
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      #value的Serializer类，实现类实现了接口org.apache.kafka.common.serialization.Serializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer

mybatis:
  mapper-locations: classpath:mappers/*.xml
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000
', 'a9c59c1ea136f8fbd1ed355091d5ba47', '2022-01-15 09:20:53', '2022-01-15 09:20:53', null, '111.192.79.149', 'I', 'dev');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (1, 10, 'exception-dashboard-dev', 'DEFAULT_GROUP', '', 'server:
  port: 9999
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8
  tomcat:
    connection-timeout: 300000


spring:
  main:
    allow-bean-definition-overriding: true
  mvc:
    async:
      request-timeout: 20000
  # database
  datasource:
    url: jdbc:postgresql://121.37.67.93:5432/dev
    username: postgres
    password: 123456
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 30000
      idle-timeout: 30000
      minimum-idle: 10
      maximum-pool-size: 10
      pool-prepared-statements: true
      max-open-prepared-statements: 100

mybatis:
  mapper-locations: classpath:mappers/*.xml
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000

', 'e6eb07165188a893ff9f8f2811de9607', '2022-01-15 09:21:07', '2022-01-15 09:21:07', null, '111.192.79.149', 'D', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (3, 11, 'exception-manager-dev', 'DEFAULT_GROUP', '', 'server:
  port: 9998
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8

spring:
  main:
    allow-bean-definition-overriding: true
  # redis
  redis:
    host:103.105.200.216
    port: 6379
  # database
  datasource:
    url: jdbc:postgresql://121.37.67.93:5432/dev
    username: postgres
    password: 123456
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 30000
      idle-timeout: 30000
      minimum-idle: 10
      maximum-pool-size: 10
      pool-prepared-statements: true
      max-open-prepared-statements: 100
  # elasticsearch
  data:
    elasticsearch:
      host:103.105.200.216
      port: 9200
  #kafka配置
  kafka:
    #这里改为你的kafka服务器ip和端口号
    bootstrap-servers: 121.37.67.93:9092
    #=============== producer  =======================
    producer:
      #如果该值大于零时，表示启用重试失败的发送次数
      retries: 0
      #每当多个记录被发送到同一分区时，生产者将尝试将记录一起批量处理为更少的请求，默认值为16384(单位字节)
      batch-size: 16384
      #生产者可用于缓冲等待发送到服务器的记录的内存总字节数，默认值为3355443
      buffer-memory: 33554432
      #key的Serializer类，实现类实现了接口org.apache.kafka.common.serialization.Serializer
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      #value的Serializer类，实现类实现了接口org.apache.kafka.common.serialization.Serializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer

mybatis:
  mapper-locations: classpath:mappers/*.xml
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000
', 'a9c59c1ea136f8fbd1ed355091d5ba47', '2022-01-15 09:21:07', '2022-01-15 09:21:07', null, '111.192.79.149', 'D', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (2, 12, 'exception-transfer-dev.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 9997
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8

spring:
  main:
    allow-bean-definition-overriding: true
  # database
  redis:
    host:103.105.200.216
    port: 6379

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000', 'a1fdc90a192141d5e2fc332e5434d819', '2022-01-15 11:12:18', '2022-01-15 11:12:18', null, '111.192.79.149', 'D', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 13, 'exception-cloud-gateway-test.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 10086
spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: false # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由
      routes:
        - id: exception-transfer
          uri: lb://exception-transfer
          predicates:
            - Path=/transfer/** #断言,路径相匹配的进行路由
          filters:
            - RewritePath=/transfer/(?<segment>.*), /$\\{segment}
        - id: exception-manager
          uri: lb://exception-manager
          predicates:
            - Path=/manager/**
          filters:
            - RewritePath=/manager/(?<segment>.*), /$\\{segment}
        - id: exception-dashboard
          uri: lb://exception-dashboard
          predicates:
            - Path=/dashboard/**
          filters:
            - RewritePath=/dashboard/(?<segment>.*), /$\\{segment}
', 'a03ee85e7c26e71fe7e9000e88590e73', '2022-01-16 04:24:23', '2022-01-16 04:24:24', null, '111.192.79.149', 'I', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 14, 'exception-dashboard-test.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 9999
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8
  tomcat:
    connection-timeout: 300000


spring:
  main:
    allow-bean-definition-overriding: true
  mvc:
    async:
      request-timeout: 20000
  # database
  datasource:
    url: jdbc:postgresql://121.37.67.93:5432/dev
    username: postgres
    password: 123456
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 30000
      idle-timeout: 30000
      minimum-idle: 10
      maximum-pool-size: 10
      pool-prepared-statements: true
      max-open-prepared-statements: 100

mybatis:
  mapper-locations: classpath:mappers/*.xml
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000

', 'e6eb07165188a893ff9f8f2811de9607', '2022-01-16 04:25:22', '2022-01-16 04:25:22', null, '111.192.79.149', 'I', 'test');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified, src_user, src_ip, op_type, tenant_id) VALUES (0, 15, 'exception-manager-test.yaml', 'DEFAULT_GROUP', '', 'server:
  port: 9998
#  address: 0.0.0.0
  servlet:
    encoding:
      force: true
      charset: UTF-8

spring:
  main:
    allow-bean-definition-overriding: true
  # redis
  redis:
    host:103.105.200.216
    port: 6379
  # database
  datasource:
    url: jdbc:postgresql://121.37.67.93:5432/dev
    username: postgres
    password: 123456
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 30000
      idle-timeout: 30000
      minimum-idle: 10
      maximum-pool-size: 10
      pool-prepared-statements: true
      max-open-prepared-statements: 100
  # elasticsearch
  data:
    elasticsearch:
      host:103.105.200.216
      port: 9200
  #kafka配置
  kafka:
    #这里改为你的kafka服务器ip和端口号
    bootstrap-servers: 121.37.67.93:9092
    #=============== producer  =======================
    producer:
      #如果该值大于零时，表示启用重试失败的发送次数
      retries: 0
      #每当多个记录被发送到同一分区时，生产者将尝试将记录一起批量处理为更少的请求，默认值为16384(单位字节)
      batch-size: 16384
      #生产者可用于缓冲等待发送到服务器的记录的内存总字节数，默认值为3355443
      buffer-memory: 33554432
      #key的Serializer类，实现类实现了接口org.apache.kafka.common.serialization.Serializer
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      #value的Serializer类，实现类实现了接口org.apache.kafka.common.serialization.Serializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer

mybatis:
  mapper-locations: classpath:mappers/*.xml
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

feign:
  client:
    config:
      dashboard:
        connect-timeout: 5000
        read-timeout: 10000
      manager:
        connect-timeout: 5000
        read-timeout: 10000
', 'a9c59c1ea136f8fbd1ed355091d5ba47', '2022-01-16 04:25:22', '2022-01-16 04:25:22', null, '111.192.79.149', 'I', 'test');

