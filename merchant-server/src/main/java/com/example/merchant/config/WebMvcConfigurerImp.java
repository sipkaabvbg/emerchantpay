package com.example.merchant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class represents Web Mvc Configurer
 */

@Configuration
public class WebMvcConfigurerImp implements WebMvcConfigurer {

	private final long MAX_AGE_SECS = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "HEAD", "OPTIONS", "PATCH", "DELETE").maxAge(MAX_AGE_SECS);
	}

@Override
public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

	//set path extension to false
	configurer.favorPathExtension(false).
    //request parameter ("format" by default) should be used to determine the requested media type
    favorParameter(true).
    //the favour parameter is set to "mediaType" instead of default "format"
    parameterName("mediaType").
    //ignore the accept headers
    ignoreAcceptHeader(true).
    //dont use Java Activation Framework since we are manually specifying the mediatypes required below
    useJaf(false).
    defaultContentType(MediaType.APPLICATION_JSON).
    mediaType("xml", MediaType.APPLICATION_XML).
    mediaType("json", MediaType.APPLICATION_JSON);
  }

}
