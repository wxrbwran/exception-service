package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.Date;
import javax.annotation.Generated;

public class SourceMap {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String apiKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String appVersion;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String appType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private SourceMapData data;

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
    public String getApiKey() {
        return apiKey;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAppVersion() {
        return appVersion;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAppType() {
        return appType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public SourceMapData getData() {
        return data;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setData(SourceMapData data) {
        this.data = data;
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