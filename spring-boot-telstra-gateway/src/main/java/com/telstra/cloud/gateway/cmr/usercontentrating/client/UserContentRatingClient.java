package com.telstra.cloud.gateway.cmr.usercontentrating.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "user-contentrating-service")
public interface UserContentRatingClient {
	@RequestMapping(value = "/user/{username}/contentrating", produces = "application/json", method = {
			RequestMethod.GET })
	public List<UserContentRating> getContentRating(
			@RequestParam(value = "contentType") String contentType,
			@RequestParam(value = "contentIds") List<String> contentIds,
			@PathVariable(value = "username") String userName);

	@RequestMapping(value = "/user/{username}/contentrating", produces = "application/json", method = {
			RequestMethod.POST })
	public UserContentRating saveContentRating(@RequestBody UserContentRating userContentRating,
			@PathVariable(value = "username") String userName);
}
