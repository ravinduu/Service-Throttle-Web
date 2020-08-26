package com.servicethrottle.stauthapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableEurekaClient
public class StAuthApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(StAuthApiGatewayApplication.class, args);
	}

}
