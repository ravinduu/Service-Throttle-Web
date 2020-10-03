package com.servicethrottle.stuaaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class StUaaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StUaaServiceApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.servicethrottle"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails(){
		return new ApiInfo(
				"Service Throttle User API",
				"API for handle user management",
				"1.0",
				"Licenced",
				new springfox.documentation.service.Contact("ravindu","","ravindu.dilshan.rd@gmail.com"),
				"Apache-2.0",
				"https://github.com/ravdsn/Service-Throttle-Web/blob/master/LICENSE",
				Collections.emptyList()
		);
	}
}
