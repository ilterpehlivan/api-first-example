package com.ilt.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.ilt.api.rest", "com.ilt.api.rest.interfaces" , "org.openapitools.configuration"})
public class ApiFirstApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiFirstApplication.class, args);
  }
}
