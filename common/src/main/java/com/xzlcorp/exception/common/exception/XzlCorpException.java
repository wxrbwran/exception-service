package com.xzlcorp.exception.common.exception;

/**
 * @author wuxiaoran
 */
public class XzlCorpException extends RuntimeException {

  private final Integer code;
  private final String message;

  public XzlCorpException(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public XzlCorpException(XzlCorpExceptionEnum exceptionEnum) {
    this(exceptionEnum.getCode(), exceptionEnum.getMsg());
  }

  public Integer getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
