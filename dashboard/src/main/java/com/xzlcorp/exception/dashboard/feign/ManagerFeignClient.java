package com.xzlcorp.exception.dashboard.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wuxiaoran
 */
@FeignClient(value="manager")
public interface ManagerFeignClient {

}
