package com.telstra.cloud.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.telstra.cloud.user.domain.UserInfo;
import com.telstra.cloud.user.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link UserService}.
 *
 * @author Selva ONP
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//Separate profile for web tests to avoid using dev databases
@ActiveProfiles("test")
public class UserServiceTests {

	@Autowired
	UserService userService;

	@Test
	public void findUserLoginByUserName() throws UserNotFoundException {
		UserInfo user = this.userService.getUser(new UserSearchCriteria("user1"));
		assertThat(user.getEmail()).isEqualToIgnoringCase("user1@gmail.com");
	}
	
	@Test(expected = UserNotFoundException.class)
	public void findUserLoginByUserNameNotFoundError() throws UserNotFoundException {
		this.userService.getUser(new UserSearchCriteria("user1000"));
	}	
}
