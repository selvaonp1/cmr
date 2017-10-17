package com.telstra.cloud.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.cloud.content.domain.ContentRating;
import com.telstra.cloud.content.domain.ContentRatingWrapper;
import com.telstra.cloud.content.service.ContentRatingCriteria;
import com.telstra.cloud.content.service.ContentRatingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ContentRatingController {

	@Autowired
	private ContentRatingService contentRatingService;

	@GetMapping(value = "/contentrating", produces = "application/json")
	@ResponseBody
	public List<ContentRating> getContentRating(
			@ModelAttribute("contentRatingCriteria") ContentRatingCriteria contentRatingCriteria) {
		return this.contentRatingService.getRatingsForContent(contentRatingCriteria);
	}

	@PostMapping(value = "/contentrating", produces = "application/json")
	public ContentRatingWrapper saveContentRating(@RequestBody ContentRatingWrapper contentRatingWrapper,
			@RequestParam("processNewRatingsOnly") boolean processNewRatingsOnly) {
		List<ContentRating> result = null;
		if (processNewRatingsOnly) {
			result = this.contentRatingService.saveNewOnly(contentRatingWrapper.getContentRatings());
		} else {
			result = this.contentRatingService.saveAll(contentRatingWrapper.getContentRatings());
		}
		return new ContentRatingWrapper(result);

	}

}
