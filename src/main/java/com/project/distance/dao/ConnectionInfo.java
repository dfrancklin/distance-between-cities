package com.project.distance.dao;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Represents the connection information that can be found
 * at the "application.properties"
 * 
 * @author Diego Francklin Martins dos Santos
 */
@Component
@ConfigurationProperties("connection")
public class ConnectionInfo {

	@NotNull
	private String url;

	@NotNull
	private String username;

	@NotNull
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}