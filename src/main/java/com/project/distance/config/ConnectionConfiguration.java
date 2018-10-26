package com.project.distance.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("connection")
public class ConnectionConfiguration {

	@NotNull
	private String url;

	@NotNull
	private String username;

	@NotNull
	private String password;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Bean
	public Connection connection() throws SQLException {
		String connectionString = String.format("%s?user=%s&password=%s", url, username, password);
		
		return DriverManager.getConnection(connectionString);
	}

}