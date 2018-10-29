package com.project.distance.dao.city;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.distance.dao.DAO;
import com.project.distance.models.City;

@Component
public class CityDAO extends DAO implements ICityDAO {

	@Override
	public List<City> listAll() throws Exception {
		String sql = "SELECT * FROM city";
		Connection connection = openConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		
		List<City> cities = mapResult(rs);
		
		connection.close();
		prepareStatement.close();
		rs.close();
		
		return cities;
	}

	private List<City> mapResult(ResultSet rs) throws Exception {
		List<City> cities = new ArrayList<>();
		
		City city = null;
		
		while (rs.next()) {
			city = new City();
			
			city.setId(rs.getInt("id"));
			city.setName(rs.getString("name"));
			city.setLatitude(rs.getDouble("latitude"));
			city.setLongitude(rs.getDouble("longitude"));
			
			cities.add(city);
		}
		
		return cities;
	}

}
