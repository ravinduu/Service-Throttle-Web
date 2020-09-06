package com.servicethrottle.steurekadiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StEurekaDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StEurekaDiscoveryServerApplication.class, args);
	}

}
