package com.cmr.cloud.gateway.cmr.user.client;



public class UserInfo {

	private Integer id;
	private String firstName;
	private String lastName;
	private String userName;
	private String mobile;
	private String email;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public boolean equals(Object obj){
		if (this == obj){
			return true;
		}
		UserInfo person = (UserInfo) obj;
		if (firstName != null ?
				!firstName.equals(person.firstName)
				:person.firstName != null){
			return false;
		}
		else {
			return true;
		}	
	}
	@Override
	public String toString() {
	    return "UserLoginModel [id=" + id + ", firstName=" + firstName
		    + ", lastName=" + lastName + ", userName=" + userName
		    + ", mobile=" + mobile
		    + ", email=" + email + "]";
	}
	public String getUserName() {
	    return userName;
	}
	public void setUserName(String userName) {
	    this.userName = userName;
	}
	public String getMobile() {
	    return mobile;
	}
	public void setMobile(String mobile) {
	    this.mobile = mobile;
	}
	public String getEmail() {
	    return email;
	}
	public void setEmail(String email) {
	    this.email = email;
	}
}
