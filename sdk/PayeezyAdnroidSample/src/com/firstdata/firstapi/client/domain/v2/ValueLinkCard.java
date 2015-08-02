package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ValueLinkCard {
	
	public ValueLinkCard()
	{
		
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("cc_number")
	private String cc_number;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("credit_card_type")
	private String credit_card_type;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("cardholder_name")
	private String cardholder_name;
	

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("card_cost")
	private String card_cost;
	
		
	public String getCard_cost() {
		return card_cost;
	}

	public void setCard_cost(String card_cost) {
		this.card_cost = card_cost;
	}
	
	public String getCredit_card_type() {
		return credit_card_type;
	}
	
	public void setCredit_card_type(String type) {
		this.credit_card_type = type;
	}
	
	public String getCardholder_name() {
		return cardholder_name;
	}
	
	public void setCardholder_name(String name) {
		this.cardholder_name = name;
	}
	
		
	public String getCc_number() {
		return cc_number;
	}

	public void setCc_number(String ccnumber) {
		this.cc_number = ccnumber;
	}
		
}
