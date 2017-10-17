package com.cmr.cloud.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cmr.cloud.contentrating.domain.ContentRating;
import com.cmr.cloud.contentrating.service.ContentRatingCriteria;
import com.cmr.cloud.contentrating.service.ContentRatingService;
import com.cmr.cloud.contentrating.service.DuplicateRatingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class UserContentRatingController {

	@Autowired
	private ContentRatingService contentRatingService;

	@GetMapping(value = "/user/{username}/contentrating", produces = "application/json")
	public List<ContentRating> getContentRating(
			@ModelAttribute("contentRatingCriteria") ContentRatingCriteria contentRatingCriteria,
			@PathVariable(value = "username") String userName)  {
		contentRatingCriteria.setUserName(userName);
		return this.contentRatingService.getUserRatingsForContent(contentRatingCriteria);
	}

	@PostMapping(value = "/user/{username}/contentrating", produces = "application/json")
	public ResponseEntity<String> saveContentRating(@RequestBody ContentRating contentRating,
			@PathVariable(value = "username") String userName)
			throws DuplicateRatingException {
		contentRating.setUserName(userName);
		this.contentRatingService.save(contentRating);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
