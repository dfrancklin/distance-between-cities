package com.project.distance.utils;

import com.project.distance.models.City;
import com.project.distance.services.CitiesService.AllowedUnities;

/**
 * Calculates the distance between to cities
 * 
 * @author Diego Francklin Martins dos Santos
 */
public class CitiesDistanceCalculator {

	private AllowedUnities unity;

	public CitiesDistanceCalculator(AllowedUnities unity) {
		this.unity = unity;
	}

	/**
	 * Calculates the distance between to given cities (cityA, cityB)
	 * in a given unity (KM or MI)
	 * 
	 * @param cityA
	 * @param cityB
	 * 
	 * @return
	 */
	public Double calculate(City cityA, City cityB) {
		// Conversão de graus pra radianos das latitudes
		double latitudeAInRad = Math.toRadians(cityA.getLatitude());
		double latitudeBInRad = Math.toRadians(cityB.getLatitude());
		
		// Diferença das longitudes
		double deltaLongitude = cityB.getLongitude() - cityA.getLongitude();
		double deltaLongitudeInRad = Math.toRadians(deltaLongitude);
		
		// Cálcula da distância entre os pontos
		double sinLatitudeA = Math.sin(latitudeAInRad);
		double cosLatitudeA = Math.cos(latitudeAInRad);
		double sinLatitudeB = Math.sin(latitudeBInRad);
		double cosLatitudeB = Math.cos(latitudeBInRad);
		double cosDeltaLongitude = Math.cos(deltaLongitudeInRad);
		
		EarthRadius radius = EarthRadius.valueOf(unity.name());
		
		return
			Math.acos(
				cosLatitudeA * cosLatitudeB * cosDeltaLongitude +
				sinLatitudeA * sinLatitudeB
			) * radius.getRadius()
		;
	}

	/**
	 * Enum that indicates the radius of the earth in Kilometers or Miles
	 * 
	 * @author Diego Francklin Martins dos Santos
	 */
	public enum EarthRadius {
		
		KM(6371), MI(3959);
		
		private int radius;

		private EarthRadius(int radius) {
			this.radius = radius;
		}
		
		public int getRadius() {
			return radius;
		}
		
	}

}
