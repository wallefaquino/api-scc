package com.scc.Smart.Cattle.Control.Model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class WeightDTO {
	
	@JsonProperty
	private double weight;

	public WeightDTO(double weight) {
		this.weight = weight;
	}	
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
