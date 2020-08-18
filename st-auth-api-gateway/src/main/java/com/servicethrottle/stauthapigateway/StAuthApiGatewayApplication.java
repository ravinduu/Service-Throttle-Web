package com.servicethrottle.stauthapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StAuthApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(StAuthApiGatewayApplication.class, args);
	}

}
