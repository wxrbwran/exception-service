package com.xzlcorp.exception.transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wuxiaoran
 */
@ComponentScan(basePackages = {
    "com.xzlcorp.exception.common.exception",
    "com.xzlcorp.exception.transfer.*",
})
@EnableFeignClients
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ExceptionTransferApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExceptionTransferApplication.class, args);
  }
}
