package com.project.distance.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="cities-distance")
@XmlSeeAlso(City.class)
public class CitiesDistance {

	private City cityA;

	private City cityB;

	private Double distance;

	public CitiesDistance() {}

	public CitiesDistance(City cityA, City cityB, Double distance) {
		this.cityA = cityA;
		this.cityB = cityB;
		this.distance = distance;
	}

	@XmlElement
	public City getCityA() {
		return cityA;
	}

	public void setCityA(City cityA) {
		this.cityA = cityA;
	}

	@XmlElement
	public City getCityB() {
		return cityB;
	}

	public void setCityB(City cityB) {
		this.cityB = cityB;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

}
