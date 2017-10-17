package com.cmr.cloud.config;

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
	public void initialize() {
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS UserMovieRating");

			statement.executeUpdate("CREATE TABLE UserMovieRating (" + "id INTEGER Primary key, "
					+ "userName varchar(30) not null," + "contentType varchar(30) not null, "
					+ "contentId varchar(30) not null," + "rating REAL  not null )");

			statement.executeUpdate("INSERT INTO UserMovieRating " + "(userName,contentType,contentId,rating) "
					+ "VALUES " + "('user1', 'Netflix Roulette', '60032563',3.8)");
			statement.executeUpdate("INSERT INTO UserMovieRating " + "(userName,contentType,contentId,rating) "
					+ "VALUES " + "('user1', 'Netflix Roulette', '880640',4.1)");
			statement.executeUpdate("INSERT INTO UserMovieRating " + "(userName,contentType,contentId,rating) "
					+ "VALUES " + "('user1', 'Netflix Roulette', '60010514',3.7)");			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
