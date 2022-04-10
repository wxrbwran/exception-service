package com.xzlcorp.exception.dashboard.model.pojo.notification;

import com.xzlcorp.exception.dashboard.model.pojo.WhiteBlackList;
import java.util.Date;
import javax.annotation.Generated;

public class NotificationRule {

    private Integer id;


    private String name;


    private NotificationRuleData data;


    private WhiteBlackList whiteList;


    private WhiteBlackList blackList;


    private String level;


    private Integer interval;


    private Boolean open;


    private Date recently;


    private Integer count;


    private Integer project;


    private Date createdAt;


    private Date updatedAt;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public NotificationRuleData getData() {
        return data;
    }


    public void setData(NotificationRuleData data) {
        this.data = data;
    }


    public WhiteBlackList getWhiteList() {
        return whiteList;
    }


    public void setWhiteList(WhiteBlackList whiteList) {
        this.whiteList = whiteList;
    }


    public WhiteBlackList getBlackList() {
        return blackList;
    }


    public void setBlackList(WhiteBlackList blackList) {
        this.blackList = blackList;
    }


    public String getLevel() {
        return level;
    }


    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }


    public Integer getInterval() {
        return interval;
    }


    public void setInterval(Integer interval) {
        this.interval = interval;
    }


    public Boolean getOpen() {
        return open;
    }


    public void setOpen(Boolean open) {
        this.open = open;
    }


    public Date getRecently() {
        return recently;
    }


    public void setRecently(Date recently) {
        this.recently = recently;
    }


    public Integer getCount() {
        return count;
    }


    public void setCount(Integer count) {
        this.count = count;
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