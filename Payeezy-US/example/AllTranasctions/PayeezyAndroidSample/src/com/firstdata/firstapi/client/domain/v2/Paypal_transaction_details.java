package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Paypal_transaction_details
{
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("message")
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("timestamp")
	private String timestamp;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("payer_id")
	private String payer_id;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("gross_amount_currency_id")
	private String gross_amount_currency_id;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("authorization")
	private String authorization;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("cardholder_name")
	private String cardholder_name;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("success")
	private String success;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("correlation_id")
	private String correlation_id;
	
	
	public String getMessage ()
	{
	return message;
	}
	
	public void setMessage (String message)
	{
	this.message = message;
	}
	
	public String getTimestamp ()
	{
	return timestamp;
	}
	
	public void setTimestamp (String timestamp)
	{
	this.timestamp = timestamp;
	}
	
	public String getPayer_id ()
	{
	return payer_id;
	}
	
	public void setPayer_id (String payer_id)
	{
	this.payer_id = payer_id;
	}
	
	public String getGross_amount_currency_id ()
	{
	return gross_amount_currency_id;
	}
	
	public void setGross_amount_currency_id (String gross_amount_currency_id)
	{
	this.gross_amount_currency_id = gross_amount_currency_id;
	}
	
	public String getAuthorization ()
	{
	return authorization;
	}
	
	public void setAuthorization (String authorization)
	{
	this.authorization = authorization;
	}
	
	public String getCardholder_name ()
	{
	return cardholder_name;
	}
	
	public void setCardholder_name (String cardholder_name)
	{
	this.cardholder_name = cardholder_name;
	}
	
	public String getSuccess ()
	{
	return success;
	}
	
	public void setSuccess (String success)
	{
	this.success = success;
	}
	
	public String getCorrelation_id ()
	{
	return correlation_id;
	}
	
	public void setCorrelation_id (String correlation_id)
	{
	this.correlation_id = correlation_id;
	}
}

