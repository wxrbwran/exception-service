package com.xzlcorp.exception.dashboard.model.dao;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class InviteDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Invite invite = new Invite();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = invite.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> uuid = invite.uuid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> hash = invite.hash;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> auth = invite.auth;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> url = invite.url;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> expires = invite.expires;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer[]> projects = invite.projects;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> organization = invite.organization;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> inviter = invite.inviter;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Invite extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> uuid = column("uuid", JDBCType.VARCHAR);

        public final SqlColumn<String> hash = column("hash", JDBCType.VARCHAR);

        public final SqlColumn<String> auth = column("auth", JDBCType.VARCHAR);

        public final SqlColumn<String> url = column("url", JDBCType.VARCHAR);

        public final SqlColumn<Date> expires = column("expires", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer[]> projects = column("projects", JDBCType.ARRAY, "com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler");

        public final SqlColumn<Integer> organization = column("organization", JDBCType.INTEGER);

        public final SqlColumn<Integer> inviter = column("inviter", JDBCType.INTEGER);

        public Invite() {
            super("s_bug.t_invite");
        }
    }
}