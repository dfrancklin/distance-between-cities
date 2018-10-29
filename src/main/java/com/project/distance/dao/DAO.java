package com.project.distance.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Defines the common methods between the Data Access Objects
 * 
 * @author Diego Francklin Martins dos Santos
 */
public abstract class DAO {

	@Autowired
	private ConnectionInfo info;

	public Connection openConnection() throws Exception {
		return DriverManager.getConnection(
			info.getUrl(),
			info.getUsername(),
			info.getPassword()
		);
	}

}
