package com.telstra.cloud.user.service;

import org.springframework.data.repository.CrudRepository;

import com.telstra.cloud.user.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

   User findUserByUserName(String username);
}
