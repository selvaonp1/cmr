package com.telstra.cloud.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializeConfig {

	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void initialize(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS User");
			statement.executeUpdate(
					"CREATE TABLE User(" +
					"id INTEGER Primary key, " +
					"userName varchar(30) not null," +
					"firstName varchar(30) not null, " +
					"lastName varchar(30) not null," +
					"email varchar(100) not null," + 
					"mobile varchar(30) not null)" 
					);
			statement.executeUpdate(
					"INSERT INTO User " +
					"(userName,firstName,lastName,email,mobile) " +
					"VALUES " + "('user1','Bharat','Verma',"
						+ " 'user1@gmail.com','00998877687')"
					);
			statement.executeUpdate(
					"INSERT INTO User " +
					"(userName,firstName,lastName,email,mobile) " +
					"VALUES " + "('user2','John','Smith',"
						+ " 'user2@gmail.com','00998877689')"
					);	
			statement.executeUpdate(
					"INSERT INTO User " +
					"(userName,firstName,lastName,email,mobile) " +
					"VALUES " + "('user3','Anand','Raj',"
						+ " 'user3@gmail.com','00998877679')"
					);				
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
