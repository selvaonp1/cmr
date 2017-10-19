package com.cmr.cloud.gateway.cmr;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Integration test to run the application.
 *
 * @author Selva ONP
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
//Separate profile for web tests to avoid using dev databases
@ActiveProfiles("integrationtest")

public class CMRGatewayIntegrationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testGetUserContent() throws Exception {
		double[] result = new double[1];
		result[0] = 3.8;
		MvcResult res = this.mvc.perform(get("/gateway/user/user2/content")).andExpect(status().isOk())
		.andExpect(content().contentType(WebTestConstants.APPLICATION_JSON_UTF8)).andReturn();
		
		String json = res.getResponse().getContentAsString();
		ReadContext ctx = JsonPath.parse(json);
		List<Double> rating = ctx.read("$[?(@.show_id == \"60032563\")].rating");
		assertThat(rating.get(0)).isEqualTo(3.8);

		String request = "{\n" + 
				"	\"contentType\": \"Netflix Roulette\",\n" + 
				"	\"contentId\": \"520179\",\n" + 
				"	\"rating\": 10.1\n" + 
				"}";
				
		mvc.perform(post("/user/user2/contentrating").contentType(MediaType.APPLICATION_JSON).content(request)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	
	}
	

}
