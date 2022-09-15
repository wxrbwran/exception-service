package com.xzlcorp.exception.dashboard.controller;

import com.xzlcorp.exception.common.common.ApiRestResponse;
import com.xzlcorp.exception.common.utils.RedisConstants;
import com.xzlcorp.exception.dashboard.feign.ManagerFeignClient;
import com.xzlcorp.exception.dashboard.model.pojo.User;
import com.xzlcorp.exception.dashboard.model.request.LoginRequest;
import com.xzlcorp.exception.dashboard.model.request.SignupRequest;
import com.xzlcorp.exception.dashboard.model.request.UpdateUserRequest;
import com.xzlcorp.exception.dashboard.model.vo.UserVO;
import com.xzlcorp.exception.dashboard.service.UserService;
import com.xzlcorp.exception.dashboard.utils.CacheClient;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @author wuxiaoran
 */
@Slf4j
@RestController
@RequestMapping({"/events"})
@Api(value = "Events", tags = {"events相关接口"})
public class EventController {

  @Autowired
  private ManagerFeignClient managerFeignClient;

  @GetMapping("latest")
  public ApiRestResponse getLatestEventByIssueId(
//          @PathVariable Integer eventId,
          @RequestParam(value = "issue_id") Integer issueId) {
    log.info("issueId, {}", issueId);
    return managerFeignClient.getLatestEventByIssueId(issueId);
  }

  @GetMapping("{eventId}")
  public ApiRestResponse getEventByIdAndIssueId(
      @PathVariable String eventId,
      @RequestParam(value = "issue_id") Integer issueId) {
    log.info("issueId, {}", issueId);
    log.info("eventId, {}", eventId);
    return managerFeignClient.getEventByIdAndIssueId(issueId, eventId);
  }

}
