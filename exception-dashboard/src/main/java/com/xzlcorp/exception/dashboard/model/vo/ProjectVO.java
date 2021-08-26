package com.xzlcorp.exception.dashboard.model.vo;

import com.xzlcorp.exception.dashboard.model.pojo.User;
import java.util.List;
import lombok.Data;

/**
 * @author wuxiaoran
 */
@Data
public class ProjectVO {
    private Integer id;

    private String apiKey;

    private String name;

    private String type;

    private Integer admin;

    private Integer organization;

    private List<UserVO> users;

    private Integer[] notificationRules;

    private Integer notificationSetting;

}