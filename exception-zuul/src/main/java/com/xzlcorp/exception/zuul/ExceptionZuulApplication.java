package com.xzlcorp.exception.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author wuxiaoran
 */
@EnableZuulProxy
@EnableFeignClients
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableRedisHttpSession
@SpringCloudApplication
public class ExceptionZuulApplication {
  public static void main(String[] args) {
    SpringApplication.run(ExceptionZuulApplication.class, args);
  }
}
