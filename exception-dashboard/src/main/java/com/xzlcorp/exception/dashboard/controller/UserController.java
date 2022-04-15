package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.common.utils.RedisConstants;
import com.xzlcorp.exception.dashboard.model.pojo.Organization;
import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.LoginRequest;
import com.xzlcorp.exception.dashboard.model.request.SignupRequest;
import com.xzlcorp.exception.dashboard.model.request.UpdateUserRequest;
import com.xzlcorp.exception.dashboard.model.vo.OrganizationVO;
import com.xzlcorp.exception.dashboard.model.vo.ProjectVO;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import com.xzlcorp.exception.dashboard.service.OrganizationService;
import com.xzlcorp.exception.dashboard.service.ProjectService;
import com.xzlcorp.exception.dashboard.service.UserService;
import com.xzlcorp.exception.dashboard.utils.CacheClient;
import io.swagger.annotations.Api;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author wuxiaoran
 */
@Slf4j
@RestController
@Api(value = "User", tags = {"用户信息相关接口"})
public class UserController {

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private UserService userService;

  @Autowired
  private CacheClient cacheClient;

  @GetMapping("/users/{userId}")
  public ApiRestResponse getUserInfo(@PathVariable Integer userId) {
    // 互斥锁解决缓存击穿
    UserVO userVO = cacheClient
        .queryWithMutex(RedisConstants.USER_INFO_KEY, userId,
            UserVO.class, userService::getFullUserInfo,
            RedisConstants.CACHE_USER_INFO_TTL, TimeUnit.DAYS,
            RedisConstants.LOCK_USER_INFO_KEY + userId
        );
    return ApiRestResponse.success(userVO);
  }

  @PatchMapping("/users/{userId}")
  public ApiRestResponse<User> updateUserInfo(@PathVariable Integer userId, @RequestBody UpdateUserRequest request) {
    User user = userService.updateUserById(userId, request);
    return ApiRestResponse.success(user);
  }


  @PostMapping("/auth/signup")
  public ApiRestResponse signup(@Valid @RequestBody SignupRequest request)
      throws NoSuchAlgorithmException {
    UserVO userVO = userService.signup(request);
    return ApiRestResponse.success(userVO);
  }

  @PostMapping("/auth/login")
  public ApiRestResponse login(@Valid @RequestBody LoginRequest request)
      throws NoSuchAlgorithmException {
    UserVO userVO = userService.login(request);
    return ApiRestResponse.success(userVO);
  }
}
