package com.xzlcorp.exception.dashboard.model.pojo;

import java.util.Date;
import javax.annotation.Generated;

public class SourceMap {

    private Integer id;


    private String apiKey;


    private String appVersion;


    private String appType;


    private SourceMapData data;


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


    public String getAppVersion() {
        return appVersion;
    }


    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }


    public String getAppType() {
        return appType;
    }


    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }


    public SourceMapData getData() {
        return data;
    }


    public void setData(SourceMapData data) {
        this.data = data;
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