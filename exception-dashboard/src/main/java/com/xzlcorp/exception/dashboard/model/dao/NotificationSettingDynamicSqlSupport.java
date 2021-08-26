package com.xzlcorp.exception.dashboard.model.dao;

import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingEmails;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingBrowser;
import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSettingWebHooks;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class NotificationSettingDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final NotificationSetting notificationSetting = new NotificationSetting();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = notificationSetting.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<NotificationSettingEmails> emails = notificationSetting.emails;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<NotificationSettingBrowser> browser = notificationSetting.browser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<NotificationSettingWebHooks> webhooks = notificationSetting.webhooks;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> project = notificationSetting.project;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = notificationSetting.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = notificationSetting.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class NotificationSetting extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<NotificationSettingEmails> emails = column("emails", JDBCType.OTHER, "com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler");

        public final SqlColumn<NotificationSettingBrowser> browser = column("browser", JDBCType.OTHER, "com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler");

        public final SqlColumn<NotificationSettingWebHooks> webhooks = column("webhooks", JDBCType.OTHER, "com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler");

        public final SqlColumn<Integer> project = column("project", JDBCType.INTEGER);

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public NotificationSetting() {
            super("s_bug.t_notification_setting");
        }
    }
}