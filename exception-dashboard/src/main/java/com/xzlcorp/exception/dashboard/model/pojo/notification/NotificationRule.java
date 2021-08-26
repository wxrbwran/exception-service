package com.xzlcorp.exception.dashboard.model.pojo.notification;

import com.xzlcorp.exception.dashboard.model.pojo.WhiteBlackList;
import java.util.Date;
import javax.annotation.Generated;

public class NotificationRule {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private NotificationRuleData data;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private WhiteBlackList whiteList;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private WhiteBlackList blackList;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String level;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer interval;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Boolean open;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date recently;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer count;

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
    public String getName() {
        return name;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public NotificationRuleData getData() {
        return data;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setData(NotificationRuleData data) {
        this.data = data;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public WhiteBlackList getWhiteList() {
        return whiteList;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setWhiteList(WhiteBlackList whiteList) {
        this.whiteList = whiteList;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public WhiteBlackList getBlackList() {
        return blackList;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setBlackList(WhiteBlackList blackList) {
        this.blackList = blackList;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLevel() {
        return level;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getInterval() {
        return interval;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Boolean getOpen() {
        return open;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getRecently() {
        return recently;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRecently(Date recently) {
        this.recently = recently;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getCount() {
        return count;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCount(Integer count) {
        this.count = count;
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