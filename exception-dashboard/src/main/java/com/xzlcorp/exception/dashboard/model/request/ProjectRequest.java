package com.xzlcorp.exception.dashboard.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class ProjectRequest {

    @NotNull(message="项目名称不能为空")
    @Size(min=2, max=20, message="项目名称长度为2-20")
    private String name;

    @NotNull(message="项目类型不能为空")
    private String type;

    @NotNull(message="项目管理员不能为空")
    private Integer admin;

    @NotNull(message="项目所在组织机构不能为空")
    private Integer organization;

}