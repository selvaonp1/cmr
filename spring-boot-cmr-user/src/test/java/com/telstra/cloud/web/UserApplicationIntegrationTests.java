package com.cmr.cloud.web;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
@SpringBootTest({"server.port:0", "eureka.client.enabled:false"})
//Separate profile for web tests to avoid using dev databases
@ActiveProfiles("integrationtest")

public class UserApplicationIntegrationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testGetUser() throws Exception {
		this.mvc.perform(get("/user/user2")).andExpect(status().isOk())
		.andExpect(content().contentType(WebTestConstants.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.email", is("user2@gmail.com")));
	}
	
	@Test
	public void testUserNotFound() throws Exception {
		this.mvc.perform(get("/user/user000"))
		.andExpect(status().isNotFound());
	}


}
