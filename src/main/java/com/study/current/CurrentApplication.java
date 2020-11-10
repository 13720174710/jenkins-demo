package com.study.current;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.study.current")
@EnableEurekaClient
public class CurrentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrentApplication.class, args);
    }

}
