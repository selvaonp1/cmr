package com.cmr.cloud.content.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cmr.cloud.content.domain.ContentType;


@Service
public class ContentServices {

	private final Map<String, ContentService> servicesByContentType = new HashMap<>();

	  public void register(ContentType contentType, ContentService service) {
	    this.servicesByContentType.put(contentType.contentType(), service);
	  }

	  public ContentService getContentService(ContentType contentType){
	    return this.servicesByContentType.get(contentType.contentType());
	  }
	}
