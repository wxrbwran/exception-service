package com.xzlcorp.exception.dashboard.mapper;

import com.xzlcorp.exception.dashboard.model.pojo.NotificationSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wuxiaoran
* @description 针对表【t_notification_setting(通知设置表)】的数据库操作Mapper
* @createDate 2022-04-13 15:13:33
* @Entity com.xzlcorp.exception.dashboard.model.pojo.NotificationSetting
*/
@Mapper
public interface NotificationSettingMapper extends BaseMapper<NotificationSetting> {

}




