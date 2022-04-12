package com.xzlcorp.exception.manager.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_issue
 */
@TableName(value ="t_issue")
@Data
public class Issue implements Serializable {
    /**
     * 唯一主键
     */
    @TableId
    private Object id;

    /**
     * 每个event的特征hash
     */
    private String intro;

    /**
     * issue 对应的 apiKey 通过它拿到所属的 project
     */
    private String apiKey;

    /**
     * 对应 event 的 type
     */
    private String type;

    /**
     * issue 所对应的 metadata
     */
    private Object metadata;

    /**
     * 所对应的 events (doc:_id)
     */
    private String events;

    /**
     *  所对应的 events count
     */
    private Object eventsCount;

    /**
     * 受此 issue 影响的用户
     */
    private Object users;

    /**
     * 受此 issue 影响的用户 count
     */
    private Object usersCount;

    /**
     * 首条 event 的时间
     */
    private Date createdAt;

    /**
     * 最近一条 event 的时间
     */
    private Date updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}