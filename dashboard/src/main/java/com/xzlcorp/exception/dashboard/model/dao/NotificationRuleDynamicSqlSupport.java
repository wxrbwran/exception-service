package com.xzlcorp.exception.dashboard.model.dao;

import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationRuleData;
import com.xzlcorp.exception.dashboard.model.pojo.WhiteBlackList;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class NotificationRuleDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final NotificationRule notificationRule = new NotificationRule();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = notificationRule.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = notificationRule.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<NotificationRuleData> data = notificationRule.data;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<WhiteBlackList> whiteList = notificationRule.whiteList;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<WhiteBlackList> blackList = notificationRule.blackList;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> level = notificationRule.level;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> interval = notificationRule.interval;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> open = notificationRule.open;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> recently = notificationRule.recently;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> count = notificationRule.count;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> project = notificationRule.project;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = notificationRule.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = notificationRule.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class NotificationRule extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<NotificationRuleData> data = column("data", JDBCType.OTHER, "com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler");

        public final SqlColumn<WhiteBlackList> whiteList = column("white_list", JDBCType.OTHER, "com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler");

        public final SqlColumn<WhiteBlackList> blackList = column("black_list", JDBCType.OTHER, "com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler");

        public final SqlColumn<String> level = column("level", JDBCType.VARCHAR);

        public final SqlColumn<Integer> interval = column("interval", JDBCType.INTEGER);

        public final SqlColumn<Boolean> open = column("open", JDBCType.BIT);

        public final SqlColumn<Date> recently = column("recently", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> count = column("count", JDBCType.INTEGER);

        public final SqlColumn<Integer> project = column("project", JDBCType.INTEGER);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public NotificationRule() {
            super("s_bug.t_notification_rule");
        }
    }
}