package com.xzlcorp.exception.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wuxiaoran
 */
@ComponentScan(basePackages = {
    "com.xzlcorp.exception.common.exception",
    "com.xzlcorp.exception.manager.*",
})
@SpringBootApplication
@EnableFeignClients
public class ManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ManagerApplication.class, args);
  }
}
