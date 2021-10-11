package com.xzlcorp.exception.common.exception;

/**
 * 描述： 异常枚举
 *
 * @author wuxiaoran
 */
public enum XzlCorpExceptionEnum {

  // NORMAL ERROR
  NEED_USER_NAME(10001, "用户名不能为空"),
  NEED_PASSWORD(10002, "密码不能为空"),
  PASSWORD_TOO_SHORT(10003, "密码长度不能小于8位"),
  NAME_EXISTED(10004, "不允许重名"),
  INSERT_FAILED(10005, "插入失败，请重试"),
  WRONG_PASSWORD(10006, "密码错误"),
  NEED_LOGIN(10007, "用户未登录"),
  UPDATE_FAILED(10008, "更新失败"),
  NEED_ADMIN(10009, "无管理员权限"),
  NAME_NOT_NULL(10010, "名字不能为空"),
  CREATE_FAILED(10011, "新增失败"),
  REQUEST_PARAM_ERROR(10012, "请求参数错误"),
  DELETE_FAILED(10013, "删除失败"),
  MAKE_DIR_FAILED(10014, "文件夹创建失败"),
  UPLOAD_FAILED(10015, "图片上传失败"),
  NO_ISSUE_KEY(10016, "ISSUE不存在"),
  TOKEN_OUT_DATE(10017, "token已过期，请重新授权"),
  EMAIL_EXISTED(10018, "邮箱已存在"),
  INVALID_EMAIL_PASSWORD(10019, "邮箱或密码错误"),
  INVITE_NOT_EXIST(10020, "邀请地址不存在"),
  TOO_MUCH(10021, "超过最大数量"),


  //  SYSTEM_ERROR
  SYSTEM_ERROR(20000, "系统异常");

  Integer code;
  String msg;

  XzlCorpExceptionEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

}
