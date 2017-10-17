package com.telstra.cloud.contentrating.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import com.telstra.cloud.contentrating.domain.ContentType;
import com.telstra.cloud.contentrating.model.UserContentRatingModel;
import com.telstra.cloud.contentrating.service.ContentRatingRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link ContentRatingRepository}.
 *
 * @author Selva ONP
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//Separate profile for web tests to avoid using dev databases
@ActiveProfiles("test")
public class ContentRatingsRepositoryTests {

	@Autowired
	ContentRatingRepository repository;

	@Test
	public void testGetUserRatingsByUserNameAndContentTypeAndContentIdIn() {
		String contentIds[] = new String[] {"880640","60010514"};
		
		List<UserContentRatingModel> ratings = this.repository.getUserRatingsByUserNameAndContentTypeAndContentIdIn(
				"user1", ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		assertThat(ratings.size()).isEqualTo(2);
		
	}
	
	@Test
	public void testGetUserRatingsByUserNameAndContentTypeAndContentIdInForOne() {
		UserContentRatingModel userRating = new UserContentRatingModel("user1", ContentType.NETFLIXMOVIES.contentType(), "902003", 4.0f);
		this.repository.save(userRating);
		
		String contentIds[] = new String[] {"902003"};
		List<UserContentRatingModel> ratings = this.repository.getUserRatingsByUserNameAndContentTypeAndContentIdIn(
				"user1", ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		assertThat(ratings.size()).isEqualTo(1);
		
	}	
}
