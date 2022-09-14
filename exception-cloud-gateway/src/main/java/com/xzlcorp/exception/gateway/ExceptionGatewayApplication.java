package com.xzlcorp.exception.gateway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wuxiaoran
 */
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableDiscoveryClient
public class ExceptionGatewayApplication {
  public static void main(String[] args) {
        new SpringApplicationBuilder(ExceptionGatewayApplication.class)
        .web(WebApplicationType.REACTIVE)
        .run(args);
//    SpringApplication.run(ExceptionGatewayApplication.class, args);
  }
}
