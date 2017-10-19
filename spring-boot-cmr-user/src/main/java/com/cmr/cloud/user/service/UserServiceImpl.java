package com.cmr.cloud.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmr.cloud.user.domain.UserInfo;
import com.cmr.cloud.user.model.User;

import org.springframework.stereotype.Component;

@Component("userService")
public class UserServiceImpl implements UserService {

	private final UserRepository userDao;

	public UserServiceImpl(UserRepository userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserInfo getUser(UserSearchCriteria search) throws UserNotFoundException {
		UserInfo info = new UserInfo();
		User user = userDao.findUserByUserName(search.getUserName());
		if (user != null && user.getId() !=null) {
			info.setEmail(user.getEmail());
			info.setFirstName(user.getFirstName());
			info.setLastName(user.getLastName());
			info.setMobile(user.getMobile());
			info.setUserName(user.getUserName());
		} else {
			throw new UserNotFoundException();
		}
		return info;
	}

}
