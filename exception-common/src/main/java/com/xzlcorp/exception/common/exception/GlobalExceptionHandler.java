package com.xzlcorp.exception.common.exception;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wuxiaoran
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Object handleException(Exception e) {
    log.error("DefaultException", e);
    return ApiRestResponse.error(XzlCorpExceptionEnum.SYSTEM_ERROR);
  }

  @ExceptionHandler(XzlCorpException.class)
  @ResponseBody
  public Object handleXZLCorpTeamException(XzlCorpException e) {
    log.error("XZLCorpTeamException", e);
    return ApiRestResponse.error(e.getCode(), e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ApiRestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error("MethodArgumentNotValidException", e);

    return handleBindingResult(e.getBindingResult());
  }

  private ApiRestResponse handleBindingResult(BindingResult result) {
    // 把异常处理处理为对外暴露的提示
    List<String> list = new ArrayList<>();
    if (result.hasErrors()) {
      List<ObjectError> allErrors = result.getAllErrors();
      for (ObjectError objectError : allErrors) {
        String message = objectError.getDefaultMessage();
        list.add(message);
      }
    }
    if (list.size() == 0) {
      return ApiRestResponse.error(XzlCorpExceptionEnum.REQUEST_PARAM_ERROR);
    }
    return ApiRestResponse.error(
        XzlCorpExceptionEnum.REQUEST_PARAM_ERROR.getCode(),
        list.toString());
  }
}
