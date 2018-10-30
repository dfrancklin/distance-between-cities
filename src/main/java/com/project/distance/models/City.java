package com.project.distance.models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a city in the database
 * 
 * @author Diego Francklin Martins dos Santos
 */
@XmlRootElement(name="city")
public class City {

	private Integer id;

	private String name;

	private double latitude;

	private double longitude;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
