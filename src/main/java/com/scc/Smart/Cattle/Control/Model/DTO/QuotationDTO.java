package com.scc.Smart.Cattle.Control.Model.DTO;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotationDTO implements Serializable {

	public QuotationDTO(String description, double quotation_value) {
		this.description = description;
		this.quotation_value = quotation_value;
	}
	
	public QuotationDTO() {
		
	}	
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("descricao")
	private String description;

	@JsonProperty("cotacao")
	private double quotation_value;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getQuotation_value() {
		return quotation_value;
	}

	public void setQuotation_value(double quotation_value) {
		this.quotation_value = quotation_value;
	}

}
