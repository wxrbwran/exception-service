package com.xzlcorp.exception.dashboard.model.pojo.notification;

import java.util.Date;
import javax.annotation.Generated;

public class NotificationSetting {

    private Integer id;


    private NotificationSettingEmails emails;


    private NotificationSettingBrowser browser;


    private NotificationSettingWebHooks webhooks;


    private Integer project;


    private Date createdAt;


    private Date updatedAt;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public NotificationSettingEmails getEmails() {
        return emails;
    }


    public void setEmails(NotificationSettingEmails emails) {
        this.emails = emails;
    }


    public NotificationSettingBrowser getBrowser() {
        return browser;
    }


    public void setBrowser(NotificationSettingBrowser browser) {
        this.browser = browser;
    }


    public NotificationSettingWebHooks getWebhooks() {
        return webhooks;
    }


    public void setWebhooks(NotificationSettingWebHooks webhooks) {
        this.webhooks = webhooks;
    }


    public Integer getProject() {
        return project;
    }


    public void setProject(Integer project) {
        this.project = project;
    }


    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}