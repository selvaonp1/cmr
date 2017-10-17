package com.telstra.cloud.user.service;

public class UserSearchCriteria {

	private String userName;

	public UserSearchCriteria(String userName) {
    	this.userName = userName;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
