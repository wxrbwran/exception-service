package com.xzlcorp.exception.manager.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xzlcorp.exception.common.model.pojo.event.MetaData;
import com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler;
import com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler;
import com.xzlcorp.exception.common.utils.mybatis.TextArrayTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_issue
 */
@TableName(value ="s_exception.t_issue")
@Data
public class Issue implements Serializable {
    /**
     * 唯一主键
     */
    @TableId
    private Integer id;

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
    @TableField(typeHandler = JsonbTypeHandler.class)
    private MetaData metadata;

    /**
     * 所对应的 events (doc:_id)
     */
    @TableField(typeHandler = TextArrayTypeHandler.class)
    private String[] events;

    /**
     *  所对应的 events count
     */
    private Integer eventsCount;

    /**
     * 受此 issue 影响的用户
     */
    @TableField(typeHandler = IntArrayTypeHandler.class)
    private Integer[] users;

    /**
     * 受此 issue 影响的用户 count
     */
    private Integer usersCount;

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