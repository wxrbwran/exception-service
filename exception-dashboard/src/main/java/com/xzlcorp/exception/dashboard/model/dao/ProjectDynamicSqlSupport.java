package com.xzlcorp.exception.dashboard.model.dao;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ProjectDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Project project = new Project();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = project.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> apiKey = project.apiKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = project.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> type = project.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> admin = project.admin;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> organization = project.organization;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer[]> users = project.users;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer[]> notificationRules = project.notificationRules;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> notificationSetting = project.notificationSetting;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = project.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = project.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Project extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> apiKey = column("api_key", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<Integer> admin = column("admin", JDBCType.INTEGER);

        public final SqlColumn<Integer> organization = column("organization", JDBCType.INTEGER);

        public final SqlColumn<Integer[]> users = column("users", JDBCType.ARRAY, "com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler");

        public final SqlColumn<Integer[]> notificationRules = column("notification_rules", JDBCType.ARRAY, "com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler");

        public final SqlColumn<Integer> notificationSetting = column("notification_setting", JDBCType.INTEGER);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public Project() {
            super("s_exception.t_project");
        }
    }
}