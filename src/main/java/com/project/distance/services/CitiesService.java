package com.project.distance.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.distance.dao.city.ICityDAO;
import com.project.distance.models.CitiesDistance;
import com.project.distance.models.CitiesList;
import com.project.distance.models.City;

@Service
public class CitiesService implements ICitiesService {

	@Autowired
	private ICityDAO dao;

	@Override
	public CitiesList combinedCitiesWithDistance(String informedUnity) throws Exception {
		AllowedUnities unity;
		
		try {
			unity = AllowedUnities.valueOf(informedUnity.toUpperCase());
		}catch (Exception e) {
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

	private List<CitiesDistance> combineCities(List<City> cities, AllowedUnities unity) {
		List<CitiesDistance> citiesDistances = new ArrayList<>();
		
		for (int indexA = 0; indexA < cities.size(); indexA++) {
			for (int indexB = indexA + 1; indexB < cities.size(); indexB++) {
				City cityA = cities.get(indexA);
				City cityB = cities.get(indexB);
				Double distance = calculateDistance(cityA, cityB, unity);
				CitiesDistance citiesDistance = new CitiesDistance(cityA, cityB, distance);
				
				citiesDistances.add(citiesDistance);
			}
		}
		
		return citiesDistances;
	}

	private Double calculateDistance(City cityA, City cityB, AllowedUnities unity) {
		if (AllowedUnities.KM.equals(unity)) {
			return 68d;
		} else if (AllowedUnities.MI.equals(unity)) {
			return 30d;
		} else {
			return 0d;
		}
	}

	public enum AllowedUnities {
		KM, MI
	}

}
