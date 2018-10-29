package com.project.distance.services;

import com.project.distance.models.CitiesList;

/**
 * This interface represents service that will list all the cities, combine
 * them into pairs and calculates the distance between them
 * 
 * @author Diego Francklin Martins dos Santos
 */
public interface ICitiesService {

	/**
	 * Returns the list of cities combined into pairs and the given distance between
	 * the two cities in an predetermined unity (KM or MI)
	 * 
	 * @param informedUnity
	 * 
	 * @return CitiesList
	 */
	CitiesList combinedCitiesWithDistance(String unity) throws Exception;

}
