package com.xzlcorp.exception.dashboard.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wuxiaoran
 */
@Data
public class EditProjectRequest {

    @NotNull(message="项目名称不能为空")
    @Size(min=2, max=20, message="项目名称长度为2-20")
    private String name;

    @NotNull(message="项目类型不能为空")
    private String type;

}