package com.project.distance.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.distance.dao.city.ICityDAO;
import com.project.distance.models.CitiesDistance;
import com.project.distance.models.CitiesList;
import com.project.distance.models.City;
import com.project.distance.utils.CitiesDistanceCalculator;

/**
 * This service list all the cities, combine them 
 * into pairs and calculates the distance between them
 * 
 * @author Diego Francklin Martins dos Santos
 */
@Service
public class CitiesService implements ICitiesService {

	@Autowired
	private ICityDAO dao;

	/**
	 * Returns the list of cities combined into pairs and the given distance between
	 * the two cities in an predetermined unity (KM or MI)
	 * 
	 * @param informedUnity
	 * 
	 * @return CitiesList
	 */
	@Override
	public CitiesList combinedCitiesWithDistance(String informedUnity) throws Exception {
		AllowedUnities unity;
		
		try {
			unity = AllowedUnities.valueOf(informedUnity.toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("Unity \"" + informedUnity + "\" not recognized. "
					+ "The accepted unities are \"KM (Kilometers)\" and \"MI (Miles)\"");
		}
		
		List<City> cities = null;
		
		try {
			cities = dao.listAll();
		} catch (Exception e) {
			throw new Exception("Couldn't list the cities. "
					+ "Please, try again later or contact the system administrator.");
		}
		
		List<CitiesDistance> citiesDistances = combineCities(cities, unity);
		CitiesList cityList = new CitiesList(citiesDistances);
		
		return cityList;
	}

	/**
	 * Algorithm that combines a list of cities into pairs without repetitions,
	 * for instance, combine the cities A, B and C:
	 *   A B
	 *   A C
	 *   B C
	 * The combinations "A A" or "B A" would be a repetition, therefore, these 
	 * kind of combination are ignored
	 * 
	 * @param cities
	 * @param unity
	 * 
	 * @return
	 */
	private List<CitiesDistance> combineCities(List<City> cities, AllowedUnities unity) {
		List<CitiesDistance> citiesDistances = new ArrayList<>();
		CitiesDistanceCalculator calculator = new CitiesDistanceCalculator(unity);
		
		for (int indexA = 0; indexA < cities.size(); indexA++) {
			for (int indexB = indexA + 1; indexB < cities.size(); indexB++) {
				City cityA = cities.get(indexA);
				City cityB = cities.get(indexB);
				Double distance = calculator.calculate(cityA, cityB);
				
				CitiesDistance citiesDistance = new CitiesDistance(cityA, cityB, distance);
				
				citiesDistances.add(citiesDistance);
			}
		}
		
		return citiesDistances;
	}

	/**
	 * Enum indicates the allowed unities
	 * 
	 * @author Diego Francklin Martins dos Santos
	 *
	 */
	public enum AllowedUnities {
		KM, MI
	}

}
