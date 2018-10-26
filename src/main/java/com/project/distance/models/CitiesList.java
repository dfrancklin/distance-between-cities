package com.project.distance.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="cities-list")
@XmlSeeAlso(CitiesDistance.class)
public class CitiesList {

	List<CitiesDistance> cities;

	public CitiesList() {
		cities = new ArrayList<>();
	}
	
	public CitiesList(List<CitiesDistance> cities) {
		this.cities = cities;
	}

	@XmlElement
	public List<CitiesDistance> getCities() {
		return cities;
	}

}
