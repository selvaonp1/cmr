package com.telstra.cloud.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.telstra.cloud.user.model.User;
import com.telstra.cloud.user.service.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link UserRepository}.
 *
 * @author Selva ONP
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//Separate profile for web tests to avoid using dev databases
@ActiveProfiles("test")
public class UserRepositoryTests {

	@Autowired
	UserRepository repository;

	@Test
	public void testfindUserLoginByUserName() {
		User user = this.repository.findUserByUserName("user1");
		assertThat(user.getEmail()).isEqualToIgnoringCase("user1@gmail.com");
	}
	
}
