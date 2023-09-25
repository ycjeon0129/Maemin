package com.tft.storeservice.configuration;


import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@org.springframework.context.annotation.Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 서버
		registry.addMapping("/**")
			.allowedOriginPatterns("https://i9c110.p.ssafy.io")
			.allowedMethods("*")
			.allowedHeaders("*")
			.allowCredentials(true);
		// 로컬
		// registry.addMapping("/**")
		// 	.allowedOriginPatterns("*")
		// 	.allowedMethods("*")
		// 	.allowedHeaders("*")
		// 	.allowCredentials(true);
	}
}
