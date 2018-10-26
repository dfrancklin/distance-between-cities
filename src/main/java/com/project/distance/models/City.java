package com.project.distance.models;

import javax.xml.bind.annotation.XmlRootElement;

// CITY [id, name, latitude, longitude]
@XmlRootElement(name="city")
public class City {

	private Integer id;

	private String name;

	private Long latitude;

	private Long longitude;

	public City() {}

	public City(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

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

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

}
