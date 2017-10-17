package com.cmr.cloud.content.service;

import java.util.List;

import com.cmr.cloud.content.domain.Content;
import com.cmr.cloud.content.domain.ContentType;

public interface ContentService {
	
	public List<Content> getContent(ContentSearchCriteria contentSearchCriteria);
	public ContentType getContentType();

}

