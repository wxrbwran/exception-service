package com.xzlcorp.exception.manager.model.dao;

import com.xzlcorp.exception.common.model.pojo.event.MetaData;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class IssueDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Issue issue = new Issue();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = issue.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> intro = issue.intro;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> apiKey = issue.apiKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> type = issue.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<MetaData> metadata = issue.metadata;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer[]> events = issue.events;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> eventsCount = issue.eventsCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer[]> users = issue.users;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> usersCount = issue.usersCount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = issue.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = issue.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Issue extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> intro = column("intro", JDBCType.VARCHAR);

        public final SqlColumn<String> apiKey = column("api_key", JDBCType.VARCHAR);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<MetaData> metadata = column("metadata", JDBCType.OTHER, "com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler");

        public final SqlColumn<Integer[]> events = column("events", JDBCType.OTHER, "com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler");

        public final SqlColumn<Integer> eventsCount = column("events_count", JDBCType.INTEGER);

        public final SqlColumn<Integer[]> users = column("users", JDBCType.OTHER, "com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler");

        public final SqlColumn<Integer> usersCount = column("users_count", JDBCType.INTEGER);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public Issue() {
            super("s_bug.t_issue");
        }
    }
}