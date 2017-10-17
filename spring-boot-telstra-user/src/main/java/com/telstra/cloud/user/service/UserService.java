package com.telstra.cloud.user.service;

import com.telstra.cloud.user.domain.UserInfo;


public interface UserService {
    public UserInfo getUser(UserSearchCriteria loginModel) throws UserNotFoundException;
}
