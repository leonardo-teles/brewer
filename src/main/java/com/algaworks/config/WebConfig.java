package com.algaworks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@EnableWebMvc
@Configuration
@SuppressWarnings("deprecation")
public class WebConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
	  return new LayoutDialect();
	}
}
