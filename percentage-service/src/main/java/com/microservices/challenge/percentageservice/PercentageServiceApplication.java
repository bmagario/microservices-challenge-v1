package com.microservices.challenge.percentageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PercentageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PercentageServiceApplication.class, args);
    }

}
