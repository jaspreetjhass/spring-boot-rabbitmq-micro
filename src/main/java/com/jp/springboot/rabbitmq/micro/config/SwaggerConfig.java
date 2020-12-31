package com.jp.springboot.rabbitmq.micro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableSet;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).consumes(ImmutableSet.of("text/plain")).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();
	}
	
}
