package com.cmr.cloud.content.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class ContentServiceConfig {
	
	private static ContentServices contentServices;

	@Autowired
	public ContentServiceConfig(ContentServices contentServices) {
		this.contentServices = contentServices;
	}

	
	@Bean
	public static NetFlixRoletteContentServiceImpl contentService() {
		NetFlixRoletteContentServiceImpl service = new NetFlixRoletteContentServiceImpl();
		contentServices.register(service.getContentType(), service);
		return service;
	}
	
	

}
