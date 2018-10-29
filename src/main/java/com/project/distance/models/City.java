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

	private Double latitude;

	private Double longitude;

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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
