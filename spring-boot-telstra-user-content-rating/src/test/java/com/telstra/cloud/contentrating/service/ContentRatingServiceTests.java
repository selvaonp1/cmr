package com.telstra.cloud.contentrating.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import com.telstra.cloud.contentrating.service.ContentRatingService;
import com.telstra.cloud.contentrating.domain.ContentRating;
import com.telstra.cloud.contentrating.domain.ContentType;
import com.telstra.cloud.contentrating.service.ContentRatingCriteria;

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
public class ContentRatingServiceTests {

	@Autowired
	ContentRatingService ratingService;

	@Test
	public void getUserRatingsByUserNameAndContentTypeAndContentIdIn()  {
		String contentIds[] = new String[] { "880640", "60010514" };
		ContentRatingCriteria ratingCriteria = new ContentRatingCriteria("user1",
				ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		List<ContentRating> contentRating = ratingService.getUserRatingsForContent(ratingCriteria);
		assertThat(contentRating.size()).isEqualTo(2);
		assertThat(contentRating).containsExactlyInAnyOrder(
				new ContentRating("user1", ContentType.NETFLIXMOVIES.contentType(), "60010514", 3.7f),
				new ContentRating("user1", ContentType.NETFLIXMOVIES.contentType(), "880640", 4.1f));
	}

	@Test
	public void saveContentRating() throws DuplicateRatingException {
		this.ratingService.save(new ContentRating("user1", ContentType.NETFLIXMOVIES.contentType(), "65", 3.7f));

		String contentIds[] = new String[] { "65" };
		ContentRatingCriteria ratingCriteria = new ContentRatingCriteria("user1",
				ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		List<ContentRating> contentRatingList = ratingService.getUserRatingsForContent(ratingCriteria);
		assertThat(contentRatingList.size()).isEqualTo(1);
		ContentRating contentRating = contentRatingList.get(0);
		assertThat(contentRating.getContentType()).isEqualTo(ContentType.NETFLIXMOVIES.contentType());
		assertThat(contentRating.getUserName()).isEqualTo("user1");
		assertThat(contentRating.getContentId()).isEqualTo("65");
		assertThat(contentRating.getRating()).isEqualTo(3.7f);
	}

	@Test(expected = DuplicateRatingException.class)
	public void saveContentRatingFail() throws DuplicateRatingException {
		this.ratingService.save(new ContentRating("user1", ContentType.NETFLIXMOVIES.contentType(), "65", 3.7f));
	}
}
