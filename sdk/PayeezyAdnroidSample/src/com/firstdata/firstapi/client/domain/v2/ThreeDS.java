package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ThreeDS
{
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("card_number")
	private String card_number;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("xid")
	private String xid;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("cvv")
	private String cvv;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("exp_date")
	private String exp_date;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("cardholder_name")
	private String cardholder_name;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("type")
	private String type;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("cavv")
	private String cavv;
	
	public String getCard_number ()
	{
	return card_number;
	}
	
	public void setCard_number (String card_number)
	{
	this.card_number = card_number;
	}
	
	public String getXid ()
	{
	return xid;
	}
	
	public void setXid (String xid)
	{
	this.xid = xid;
	}
	
	public String getCvv ()
	{
	return cvv;
	}
	
	public void setCvv (String cvv)
	{
	this.cvv = cvv;
	}
	
	public String getExp_date ()
	{
	return exp_date;
	}
	
	public void setExp_date (String exp_date)
	{
	this.exp_date = exp_date;
	}
	
	public String getCardholder_name ()
	{
	return cardholder_name;
	}
	
	public void setCardholder_name (String cardholder_name)
	{
	this.cardholder_name = cardholder_name;
	}
	
	public String getType ()
	{
	return type;
	}
	
	public void setType (String type)
	{
	this.type = type;
	}
	
	public String getCavv ()
	{
	return cavv;
	}
	
	public void setCavv (String cavv)
	{
	this.cavv = cavv;
	}
}

