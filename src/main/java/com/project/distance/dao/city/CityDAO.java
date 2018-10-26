package com.project.distance.dao.city;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.distance.models.City;

@Component
public class CityDAO implements ICityDAO {

	@Autowired
	private Connection connection;

	@Override
	public List<City> listAll() throws Exception {
		connection.prepareStatement("SHOW TABLES");
		List<City> cities = Arrays.asList(
			new City(1, "Sorocity"),
			new City(1, "AlphaHell"),
			new City(1, "Barueri")
		);
		
		return cities;
	}

}
