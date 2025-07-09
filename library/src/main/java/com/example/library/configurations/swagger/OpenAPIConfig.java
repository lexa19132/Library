package com.example.library.configurations.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
		return new OpenAPI();
		//потом допишу что-нить, это пока что неважно.
	}
}
