package com.xzlcorp.exception.dashboard.model.pojo.notification;

import java.util.Date;
import javax.annotation.Generated;

public class NotificationSetting {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private NotificationSettingEmails emails;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private NotificationSettingBrowser browser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private NotificationSettingWebHooks webhooks;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer project;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public NotificationSettingEmails getEmails() {
        return emails;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEmails(NotificationSettingEmails emails) {
        this.emails = emails;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public NotificationSettingBrowser getBrowser() {
        return browser;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setBrowser(NotificationSettingBrowser browser) {
        this.browser = browser;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public NotificationSettingWebHooks getWebhooks() {
        return webhooks;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setWebhooks(NotificationSettingWebHooks webhooks) {
        this.webhooks = webhooks;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getProject() {
        return project;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setProject(Integer project) {
        this.project = project;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreatedAt() {
        return createdAt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}