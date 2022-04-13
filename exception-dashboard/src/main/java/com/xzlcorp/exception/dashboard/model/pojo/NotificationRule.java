package com.xzlcorp.exception.dashboard.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationRuleData;
import lombok.Data;

/**
 * 提醒规则表
 * @TableName t_notification_rule
 */
@TableName(value ="s_exception.t_notification_rule")
@Data
public class NotificationRule implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * notification 名称
     */
    private String name;

    /**
     * notification 规则
     */
    private NotificationRuleData data;

    /**
     * 
     */
    private WhiteBlackList whiteList;

    /**
     * 
     */
    private WhiteBlackList blackList;

    /**
     * notification 级别
     */
    private String level;

    /**
     * notification 静默期
默认 10 分钟
     */
    private Integer interval;

    /**
     * notification 开关
     */
    private Boolean open;

    /**
     * notification 最近通知的日期
     */
    private Date recently;

    /**
     * notification 通知总数
     */
    private Object count;

    /**
     * notification 的 project (多对一)
     */
    private Object project;

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