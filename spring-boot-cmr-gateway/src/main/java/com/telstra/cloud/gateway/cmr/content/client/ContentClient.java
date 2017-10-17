package com.cmr.cloud.gateway.cmr.content.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "content-service")
public interface ContentClient {
	@GetMapping(value = "/content", produces = "application/json")
	public List<Content> getContentForDirector(@RequestParam("director") String director);
}

