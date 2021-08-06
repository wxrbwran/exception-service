package com.xzlcorp.exception.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wuxiaoran
 */
@EnableEurekaServer
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ExceptionEurekaApplication {
  public static void main(String[] args) {
    SpringApplication.run(ExceptionEurekaApplication.class, args);
//    new SpringApplicationBuilder(ExceptionEurekaApplication.class)
//        .web(WebApplicationType.REACTIVE)
//        .run(args);
  }
}
