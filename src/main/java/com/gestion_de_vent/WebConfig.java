package com.gestion_de_vent;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {


	public void addCorsMappings(CorsRegistry registry) {
		
		
		registry.addMapping("/**") //authorisser tous les segments statique des controlleur 
		.allowedMethods("*") //authorisser tous les mehtode
		.allowedOrigins("*"); //authorisser tous les route et les mehtode
	}

	
}
