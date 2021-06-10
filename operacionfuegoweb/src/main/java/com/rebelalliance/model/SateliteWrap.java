package com.rebelalliance.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SateliteWrap {
	
	@JsonProperty("satelite")
	private List <Satelite> satelite;

	public List<Satelite> getSatelite() {
		return satelite;
	}

	public void setSatelite(List<Satelite> satelite) {
		this.satelite = satelite;
	}

}
