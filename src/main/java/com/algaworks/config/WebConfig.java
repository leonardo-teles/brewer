package com.algaworks.config;

import javax.cache.Caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@EnableAsync
@Configuration
@EnableCaching
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public LayoutDialect layoutDialect() {
	  return new LayoutDialect();
	}
	
	@Bean
	public DataAttributeDialect dataAttributeDialect() {
		return new DataAttributeDialect();
	}
	
	@Bean
	public CacheManager cacheManager() throws Exception {
	    return new JCacheCacheManager(Caching.getCachingProvider().getCacheManager(
	    		getClass().getResource("/cache/ehcache.xml").toURI(),
	    		getClass().getClassLoader()));
	}
}
