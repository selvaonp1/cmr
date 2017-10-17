package com.telstra.cloud.gateway.cmr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.telstra.cloud.gateway.cmr.user.client.UserClient;
import com.telstra.cloud.gateway.cmr.user.client.UserInfo;
import com.telstra.cloud.gateway.cmr.usercontentrating.client.UserContentRating;
import com.telstra.cloud.gateway.cmr.usercontentrating.client.UserContentRatingClient;
import com.telstra.cloud.gateway.cmr.content.client.Content;
import com.telstra.cloud.gateway.cmr.content.client.ContentClient;
import com.telstra.cloud.gateway.cmr.content.client.common.ContentType;
import com.telstra.cloud.gateway.cmr.contentrating.client.ContentRating;
import com.telstra.cloud.gateway.cmr.contentrating.client.ContentRatingClient;
import com.telstra.cloud.gateway.cmr.usercontentrating.client.ContentRatingCriteria;
import com.telstra.cloud.gateway.cmr.contentrating.client.ContentRatingWrapper;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

@RestController
public class CMRController {

	private final UserClient userClient;
	private final ContentClient contentClient;
	private final ContentRatingClient contentRatingClient;
	private final UserContentRatingClient userContentRatingClient;

	@Autowired
	public CMRController(UserClient userClient, ContentClient contentClient, ContentRatingClient contentRatingClient,
			UserContentRatingClient userContentRatingClient) {
		this.userClient = userClient;
		this.contentClient = contentClient;
		this.contentRatingClient = contentRatingClient;
		this.userContentRatingClient = userContentRatingClient;
	}

	@GetMapping(value = "/gateway/user/{username}/content", produces = "application/json")
	public ResponseEntity<?> getContents(@PathVariable(value = "username") String userName,
			@RequestParam(value = "director", defaultValue = "Quentin%20Tarantino") String director) {
		try {

			userName = userName == null ? "user1" : userName;
			UserInfo userInfo = this.userClient.getUser(userName);

			List<Content> contentList = this.contentClient.getContentForDirector(director);
			List<ContentRating> contentRatingList = contentList.stream()
					.map(content -> new ContentRating(ContentType.NETFLIXMOVIES.contentType(), content.getShowId(),
							content.getRating()))
					.collect(Collectors.toList());
			ContentRatingWrapper contentRatingWrapper = new ContentRatingWrapper(contentRatingList);
			this.contentRatingClient.saveContentRating(contentRatingWrapper, true);

			List<String> contentIds = contentList.stream().map(Content::getShowId).collect(Collectors.toList());

			List<ContentRating> contentRatings = this.contentRatingClient
					.getContentRating(ContentType.NETFLIXMOVIES.contentType(), contentIds);
			final Map<String, Float> ratingMap = new HashMap<String, Float>();

			contentRatings.forEach(
					contentRating -> ratingMap.put(contentRating.getContentId(), new Float(contentRating.getRating())));
			contentList.forEach(content -> content.setRating(ratingMap.get(content.getShowId())));

			List<UserContentRating> userContentRatings = this.userContentRatingClient
					.getContentRating(ContentType.NETFLIXMOVIES.contentType(), contentIds, userName);
			final Map<String, Float> myratingMap = new HashMap<String, Float>();
			userContentRatings.forEach(userContentRating -> myratingMap.put(userContentRating.getContentId(),
					new Float(userContentRating.getRating())));
			contentList.forEach(content -> {
				if (myratingMap.containsKey(content.getShowId())) {
					content.setMyRating(myratingMap.get(content.getShowId()));
				}
			});

			return new ResponseEntity<>(contentList, HttpStatus.OK);
		} catch (feign.FeignException e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.valueOf(e.status()));
		}
	}

	@PostMapping(value = "/gateway/user/{username}/contentrating", produces = "application/json")
	public ResponseEntity<HttpStatus> saveContentRating(@RequestBody UserContentRating contentRating,
			@PathVariable(value = "username") String userName) {
		contentRating.setUserName(userName);
		try {
			this.userContentRatingClient.saveContentRating(contentRating, userName);
			List<ContentRating> contentRatingList = new ArrayList<ContentRating>();
			contentRatingList.add(new ContentRating(contentRating.getContentType(), contentRating.getContentId(),
					contentRating.getRating()));
			ContentRatingWrapper contentRatingWrapper = new ContentRatingWrapper(contentRatingList);
			this.contentRatingClient.saveContentRating(contentRatingWrapper, false);
			return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		} catch (feign.FeignException e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.valueOf(e.status()));
		}
	}
}
