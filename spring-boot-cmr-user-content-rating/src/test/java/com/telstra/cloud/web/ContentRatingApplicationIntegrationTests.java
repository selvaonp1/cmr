package com.cmr.cloud.web;

import com.cmr.cloud.contentrating.domain.ContentRating;
import com.cmr.cloud.contentrating.domain.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.management.ManagementFactory;
import javax.management.ObjectName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

/**
 * Integration test to run the application.
 *
 * @author Selva ONP
 */
@RunWith(SpringRunner.class)
@SpringBootTest
// Separate profile for web tests to avoid using dev databases
@ActiveProfiles("integrationtest")

public class ContentRatingApplicationIntegrationTests {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private ObjectMapper mapper;
	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testGetRatings() throws Exception {
		this.mvc.perform(get("/user/user1/contentrating?contentType=Netflix Roulette&contentIds=880640,60010514"))
				.andExpect(status().isOk()).andExpect(content().contentType(WebTestConstants.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testPostUserContentRatings() throws Exception {
		String json = mapper
				.writeValueAsString(new ContentRating("user2", ContentType.NETFLIXMOVIES.contentType(), "65", 3.7f));
		mvc.perform(post("/user/user2/contentrating").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void testPostUserContentRatingsDuplicateError() throws Exception {
		String json = mapper
				.writeValueAsString(new ContentRating("user2", ContentType.NETFLIXMOVIES.contentType(), "63", 3.7f));
		mvc.perform(post("/user/user2/contentrating").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		json = mapper
				.writeValueAsString(new ContentRating("user2", ContentType.NETFLIXMOVIES.contentType(), "63", 3.7f));
		mvc.perform(post("/user/user2/contentrating").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
	}

}
