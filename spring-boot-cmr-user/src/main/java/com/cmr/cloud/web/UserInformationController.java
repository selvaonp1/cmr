package com.cmr.cloud.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cmr.cloud.user.domain.UserInfo;
import com.cmr.cloud.user.service.UserSearchCriteria;
import com.cmr.cloud.user.service.UserService;
import com.cmr.cloud.user.service.UserNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserInformationController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/user/{username}", produces = "application/json")
	public UserInfo getUser(@PathVariable(value = "username") String userName)
			throws UserNotFoundException {
		return this.userService.getUser(new UserSearchCriteria(userName));
	}

}
