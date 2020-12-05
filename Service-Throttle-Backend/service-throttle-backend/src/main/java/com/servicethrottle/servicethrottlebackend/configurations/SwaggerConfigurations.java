package com.servicethrottle.servicethrottlebackend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {
    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Service Throttle Backend API",
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
