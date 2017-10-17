package com.telstra.cloud.content.service;

import java.util.List;

import com.telstra.cloud.content.domain.Content;
import com.telstra.cloud.content.domain.ContentType;

public interface ContentService {
	
	public List<Content> getContent(ContentSearchCriteria contentSearchCriteria);
	public ContentType getContentType();

}

