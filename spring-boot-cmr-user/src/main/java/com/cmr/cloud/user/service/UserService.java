package com.cmr.cloud.user.service;

import com.cmr.cloud.user.domain.UserInfo;


public interface UserService {
    public UserInfo getUser(UserSearchCriteria loginModel) throws UserNotFoundException;
}
