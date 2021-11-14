package com.xzlcorp.exception.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wuxiaoran
 */
@ComponentScan(basePackages = {
    "com.xzlcorp.exception.common.exception",
    "com.xzlcorp.exception.dashboard.*",
})
@EnableAutoConfiguration(exclude={
    RedisAutoConfiguration.class
})
@EnableFeignClients
@SpringBootApplication
public class DashboardApplication {
  public static void main(String[] args) {
    SpringApplication.run(DashboardApplication.class, args);
//    new SpringApplicationBuilder(DashboardApplication.class)
//        .web(WebApplicationType.REACTIVE)
//        .run(args);
  }
}
