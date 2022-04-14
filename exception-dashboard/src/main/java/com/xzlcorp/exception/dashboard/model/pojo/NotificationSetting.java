package com.xzlcorp.exception.dashboard.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler;
import com.xzlcorp.exception.dashboard.model.pojo.notification.*;
import lombok.Data;

/**
 * 通知设置表
 * @TableName t_notification_setting
 */
@TableName(value ="s_exception.t_notification_setting", autoResultMap = true)
@Data
public class NotificationSetting implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    @TableField(typeHandler = JsonbTypeHandler.class)
    private NotificationSettingEmails emails;

    /**
     * 
     */
    @TableField(typeHandler = JsonbTypeHandler.class)
    private NotificationSettingBrowser browser;

    /**
     * 
     */
    @TableField(typeHandler = JsonbTypeHandler.class)
    private NotificationSettingWebHooks webhooks;

    /**
     * 
     */

    private Integer project;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private Date updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}