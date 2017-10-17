package com.cmr.cloud.contentrating.service;

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

import com.cmr.cloud.content.model.ContentRatingModel;
import com.cmr.cloud.content.service.ContentRatingService;
import com.cmr.cloud.content.service.ContentRatingCriteria;
import com.cmr.cloud.content.domain.ContentType;
import com.cmr.cloud.content.domain.ContentRating;
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
	public void getRatingsByContentTypeAndContentIdIn() {
		String contentIds[] = new String[] {"880640","60010514"};
		ContentRatingCriteria ratingCriteria = new ContentRatingCriteria(ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		List<ContentRating> contentRating = ratingService.getRatingsForContent(ratingCriteria);
		assertThat(contentRating.size()).isEqualTo(2);
		assertThat(contentRating).containsExactlyInAnyOrder(new ContentRating("Netflix Roulette", "60010514", 3.7f), new ContentRating("Netflix Roulette", "880640", 4.1f));
	}

	@Test
	public void saveAllContentRating() {
		List<ContentRating> ratingsList = new ArrayList<ContentRating>();
		ratingsList.add( new ContentRating(ContentType.NETFLIXMOVIES.contentType(), "60010515", 3.7f));
		ratingsList.add( new ContentRating(ContentType.NETFLIXMOVIES.contentType(), "60010519", 3.7f));
		this.ratingService.saveAll(ratingsList);
		
		String contentIds[] = new String[] {"60010515", "60010519"};
		ContentRatingCriteria ratingCriteria = new ContentRatingCriteria(ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		List<ContentRating> contentRating = ratingService.getRatingsForContent(ratingCriteria);
		assertThat(contentRating.size()).isEqualTo(2);
		assertThat(contentRating).containsExactlyInAnyOrder(new ContentRating("Netflix Roulette", "60010515", 3.7f), new ContentRating("Netflix Roulette", "60010519", 3.7f));
		
		ratingsList = new ArrayList<ContentRating>();
		ratingsList.add( new ContentRating(ContentType.NETFLIXMOVIES.contentType(), "60010515", 5f));
		ratingsList.add( new ContentRating(ContentType.NETFLIXMOVIES.contentType(), "60010519", 5f));
		this.ratingService.saveAll(ratingsList);	
		
		contentIds = new String[] {"60010515", "60010519"};
		ratingCriteria = new ContentRatingCriteria(ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		contentRating = ratingService.getRatingsForContent(ratingCriteria);
		assertThat(contentRating).containsExactlyInAnyOrder(new ContentRating("Netflix Roulette", "60010515", 4.35f), new ContentRating("Netflix Roulette", "60010519", 4.35f));
		assertThat(contentRating.size()).isEqualTo(2);		
		
		ratingsList = new ArrayList<ContentRating>();
		ratingsList.add( new ContentRating(ContentType.NETFLIXMOVIES.contentType(), "60010515", 2.1f));
		ratingsList.add( new ContentRating(ContentType.NETFLIXMOVIES.contentType(), "60010519", 4f));
		this.ratingService.saveAll(ratingsList);	
		
		contentIds = new String[] {"60010515", "60010519"};
		ratingCriteria = new ContentRatingCriteria(ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		contentRating = ratingService.getRatingsForContent(ratingCriteria);
		assertThat(contentRating).containsExactlyInAnyOrder(new ContentRating("Netflix Roulette", "60010515", 3.6f), new ContentRating("Netflix Roulette", "60010519", 4.233333f));
		assertThat(contentRating.size()).isEqualTo(2);		
		
	}	
	
	@Test
	public void saveAllNewOnly() {
		List<ContentRating> ratingsList = new ArrayList<ContentRating>();
		ratingsList.add( new ContentRating(ContentType.NETFLIXMOVIES.contentType(), "5", 4.7f));
		ratingsList.add( new ContentRating(ContentType.NETFLIXMOVIES.contentType(), "6", 3.7f));
		this.ratingService.saveNewOnly(ratingsList);
		
		String contentIds[] = new String[] {"5", "6"};
		ContentRatingCriteria ratingCriteria = new ContentRatingCriteria(ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		List<ContentRating> contentRating = ratingService.getRatingsForContent(ratingCriteria);
		assertThat(contentRating.size()).isEqualTo(2);
		assertThat(contentRating).containsExactlyInAnyOrder(new ContentRating("Netflix Roulette", "5", 4.7f), new ContentRating("Netflix Roulette", "6", 3.7f));		

		ratingsList = new ArrayList<ContentRating>();
		ratingsList.add( new ContentRating(ContentType.NETFLIXMOVIES.contentType(), "5", 25f));
		ratingsList.add( new ContentRating(ContentType.NETFLIXMOVIES.contentType(), "7", 5f));
		this.ratingService.saveNewOnly(ratingsList);	
		
		contentIds = new String[] {"5", "7"};
		ratingCriteria = new ContentRatingCriteria(ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		contentRating = ratingService.getRatingsForContent(ratingCriteria);
		assertThat(contentRating.size()).isEqualTo(2);		
		assertThat(contentRating).containsExactlyInAnyOrder(new ContentRating("Netflix Roulette", "5", 4.7f), new ContentRating("Netflix Roulette", "7", 5.0f));
	}		
	
}
