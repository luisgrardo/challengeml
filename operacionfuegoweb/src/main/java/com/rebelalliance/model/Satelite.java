package com.rebelalliance.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Satelite {

	@JsonProperty("name")
	private String name;
	@JsonProperty("distance")
	private double distance;
	@JsonProperty("message")
	private String [] message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String [] getMessage() {
		return message;
	}

	public void setMessage(String [] message) {
		this.message = message;
	}

}
