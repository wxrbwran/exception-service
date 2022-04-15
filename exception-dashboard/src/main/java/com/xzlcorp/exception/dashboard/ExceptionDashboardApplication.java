package com.xzlcorp.exception.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wuxiaoran
 */
@ComponentScan(basePackages = {
    "com.xzlcorp.exception.common.exception",
    "com.xzlcorp.exception.dashboard.*",
})
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class ExceptionDashboardApplication {
  public static void main(String[] args) {
    SpringApplication.run(ExceptionDashboardApplication.class, args);
//    new SpringApplicationBuilder(DashboardApplication.class)
//        .web(WebApplicationType.REACTIVE)
//        .run(args);
  }
}
