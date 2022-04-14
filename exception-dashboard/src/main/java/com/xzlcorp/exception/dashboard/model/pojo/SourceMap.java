package com.xzlcorp.exception.dashboard.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler;
import com.xzlcorp.exception.dashboard.model.pojo.SourceMapData;
import lombok.Data;

/**
 * sourceMap表
 * @TableName t_source_map
 */
@TableName(value ="s_exception.t_source_map", autoResultMap = true)
@Data
public class SourceMap implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private String apiKey;

    /**
     * 
     */
    private String appVersion;

    /**
     * 
     */
    private String appType;

    /**
     * 所有的 sourceMap 文件信息
     */
    @TableField(typeHandler = JsonbTypeHandler.class)
    private SourceMapData data;

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