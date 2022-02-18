package com.xzlcorp.exception.dashboard.model.dao;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OrganizationDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Organization organization = new Organization();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = organization.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = organization.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> introduction = organization.introduction;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> admin = organization.admin;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer[]> projects = organization.projects;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> count = organization.count;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = organization.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = organization.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer[]> users = organization.users;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Organization extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> introduction = column("introduction", JDBCType.VARCHAR);

        public final SqlColumn<Integer> admin = column("admin", JDBCType.INTEGER);

        public final SqlColumn<Integer[]> projects = column("projects", JDBCType.ARRAY, "com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler");

        public final SqlColumn<Integer> count = column("count", JDBCType.INTEGER);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer[]> users = column("users", JDBCType.ARRAY, "com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler");

        public Organization() {
            super("s_exception.t_organization");
        }
    }
}