package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.dashboard.model.request.LoginRequest;
import com.xzlcorp.exception.dashboard.model.request.SignupRequest;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import com.xzlcorp.exception.dashboard.service.AuthService;
import io.swagger.annotations.Api;
import java.security.NoSuchAlgorithmException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxiaoran
 */
@Api(value = "Auth", tags = {"前台权限验证"})
@RestController
@RequestMapping("auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("signup")
  public ApiRestResponse signup(@Valid @RequestBody SignupRequest request)
      throws NoSuchAlgorithmException {
    UserVO userVO = authService.signup(request);
    return ApiRestResponse.success(userVO);
  }

  @PostMapping("login")
  public ApiRestResponse login(@Valid @RequestBody LoginRequest request)
      throws NoSuchAlgorithmException {
    UserVO userVO = authService.login(request);
    return ApiRestResponse.success(userVO);
  }
}
