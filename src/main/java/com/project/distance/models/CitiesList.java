package com.project.distance.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Represent the list of the distances between cities
 * 
 * @see CitiesDistance
 * 
 * @author Diego Francklin Martins dos Santos
 */
@XmlRootElement(name="cities-list")
@XmlSeeAlso(CitiesDistance.class)
public class CitiesList {

	private List<CitiesDistance> cities;

	public CitiesList() {
		cities = new ArrayList<>();
	}
	
	public CitiesList(List<CitiesDistance> cities) {
		this.cities = cities;
	}

	@XmlElement(name="cities-distance")
	public List<CitiesDistance> getCities() {
		return cities;
	}

	public void setCities(List<CitiesDistance> cities) {
		this.cities = cities;
	}

}
