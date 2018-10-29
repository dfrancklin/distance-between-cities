package com.project.distance.dao.city;

import java.util.List;

import com.project.distance.models.City;

/**
 * Interface to the Data Access Object that will
 * handle access to the table "city"
 * 
 * @author Diego Francklin Martins dos Santos
 */
public interface ICityDAO {

	/**
	 * Method that lists all the cities into the table "city"
	 * 
	 * @return List<City>
	 */
	List<City> listAll() throws Exception;

}
