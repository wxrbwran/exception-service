package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.Date;
import javax.annotation.Generated;

public class Project {

    private Integer id;


    private String apiKey;


    private String name;


    private String type;


    private Integer admin;


    private Integer organization;


    private Integer[] users;


    private Integer[] notificationRules;


    private Integer notificationSetting;


    private Date createdAt;


    private Date updatedAt;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getApiKey() {
        return apiKey;
    }


    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }


    public Integer getAdmin() {
        return admin;
    }


    public void setAdmin(Integer admin) {
        this.admin = admin;
    }


    public Integer getOrganization() {
        return organization;
    }


    public void setOrganization(Integer organization) {
        this.organization = organization;
    }


    public Integer[] getUsers() {
        return users;
    }


    public void setUsers(Integer[] users) {
        this.users = users;
    }


    public Integer[] getNotificationRules() {
        return notificationRules;
    }


    public void setNotificationRules(Integer[] notificationRules) {
        this.notificationRules = notificationRules;
    }


    public Integer getNotificationSetting() {
        return notificationSetting;
    }


    public void setNotificationSetting(Integer notificationSetting) {
        this.notificationSetting = notificationSetting;
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