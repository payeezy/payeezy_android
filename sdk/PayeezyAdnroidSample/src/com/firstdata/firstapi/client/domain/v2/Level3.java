package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Level3
{
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("duty_amount")
	private String duty_amount;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("ship_to_address")
	private Ship_to_address ship_to_address;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("alt_tax_id")
	private String alt_tax_id;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("alt_tax_amount")
	private String alt_tax_amount;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("freight_amount")
	private String freight_amount;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("ship_from_zip")
	private String ship_from_zip;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("discount_amount")
	private String discount_amount;
	
	public String getDuty_amount ()
	{
	return duty_amount;
	}
	
	public void setDuty_amount (String duty_amount)
	{
	this.duty_amount = duty_amount;
	}
	
	public Ship_to_address getShip_to_address ()
	{
	return ship_to_address;
	}
	
	public void setShip_to_address (Ship_to_address ship_to_address)
	{
	this.ship_to_address = ship_to_address;
	}
	
	public String getAlt_tax_id ()
	{
	return alt_tax_id;
	}
	
	public void setAlt_tax_id (String alt_tax_id)
	{
	this.alt_tax_id = alt_tax_id;
	}
	
	public String getAlt_tax_amount ()
	{
	return alt_tax_amount;
	}
	
	public void setAlt_tax_amount (String alt_tax_amount)
	{
	this.alt_tax_amount = alt_tax_amount;
	}
	
	public String getFreight_amount ()
	{
	return freight_amount;
	}
	
	public void setFreight_amount (String freight_amount)
	{
	this.freight_amount = freight_amount;
	}
	
	public String getShip_from_zip ()
	{
	return ship_from_zip;
	}
	
	public void setShip_from_zip (String ship_from_zip)
	{
	this.ship_from_zip = ship_from_zip;
	}
	
	public String getDiscount_amount ()
	{
	return discount_amount;
	}
	
	public void setDiscount_amount (String discount_amount)
	{
	this.discount_amount = discount_amount;
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("line_items")
	private Line_item[] lineitems;
	
	@JsonInclude(Include.NON_NULL)
	public Line_item[] getLineitems() {
		return lineitems;
	}
	@JsonInclude(Include.NON_NULL)
	public void setLineitems(Line_item[] lineitems2) {
		this.lineitems = lineitems2;
	}
}
