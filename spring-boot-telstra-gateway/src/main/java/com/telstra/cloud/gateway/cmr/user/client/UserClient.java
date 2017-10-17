package com.telstra.cloud.gateway.cmr.user.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user-service")
public interface UserClient {
    @RequestMapping(value = "/user/{username}", method = {RequestMethod.GET}, produces = "application/json")
    UserInfo getUser(@PathVariable(value = "username") String userName);
}


