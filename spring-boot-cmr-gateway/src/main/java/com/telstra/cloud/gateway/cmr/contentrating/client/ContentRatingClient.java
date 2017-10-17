package com.cmr.cloud.gateway.cmr.contentrating.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "contentrating-service")
public interface ContentRatingClient {
	@RequestMapping(value = "/contentrating", produces = "application/json", method = {RequestMethod.GET})
	public List<ContentRating> getContentRating(@RequestParam(value = "contentType") String contentType,
			@RequestParam(value = "contentIds") List<String> contentIds);

	@RequestMapping(value = "/contentrating", produces = "application/json", method = {RequestMethod.POST})
	public ContentRatingWrapper saveContentRating(@RequestBody ContentRatingWrapper contentRatingWrapper
			,@RequestParam("processNewRatingsOnly") boolean processNewRatingsOnly);

	
}
