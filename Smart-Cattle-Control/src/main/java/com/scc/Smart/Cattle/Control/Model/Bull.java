package com.scc.Smart.Cattle.Control.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Bull {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String breed;
	
	@NotNull
	private double input_weight;
	
	@NotNull
	private double current_weight;
	
	@ManyToOne
	@JoinColumn(name = "lot_id", nullable = false)
	private Lot lot;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public double getInput_weight() {
		return input_weight;
	}

	public void setInput_weight(double input_weight) {
		this.input_weight = input_weight;
	}

	public double getCurrent_weight() {
		return current_weight;
	}

	public void setCurrent_weight(double current_weight) {
		this.current_weight = current_weight;
	}	
}
