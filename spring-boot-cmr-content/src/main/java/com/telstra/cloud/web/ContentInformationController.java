package com.cmr.cloud.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cmr.cloud.content.domain.Content;
import com.cmr.cloud.content.domain.ContentType;
import com.cmr.cloud.content.service.ContentSearchCriteria;
import com.cmr.cloud.content.service.ContentService;
import com.cmr.cloud.content.service.ContentServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ContentInformationController {

	@Autowired
	private ContentServices contentServices;

	@GetMapping(value = "/content", produces = "application/json")
	public List<Content> getContentForDirector(@RequestParam("director") String director) {
		ContentSearchCriteria criteria = new ContentSearchCriteria(director);
		ContentService service = contentServices.getContentService(ContentType.NETFLIXMOVIES);
    	return service.getContent(criteria);
	}	
	
}
