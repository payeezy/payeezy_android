package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Recurring
{
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("amount")
	private String amount;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchant_ref")
	private String merchant_ref;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("transaction_type")
	private String transaction_type;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("transaction_tag")
	private String transaction_tag;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("currency_code")
	private String currency_code;
	
	public String getAmount ()
	{
	return amount;
	}
	
	public void setAmount (String amount)
	{
	this.amount = amount;
	}
	
	public String getMerchant_ref ()
	{
	return merchant_ref;
	}
	
	public void setMerchant_ref (String merchant_ref)
	{
	this.merchant_ref = merchant_ref;
	}
	
	public String getTransaction_type ()
	{
	return transaction_type;
	}
	
	public void setTransaction_type (String transaction_type)
	{
	this.transaction_type = transaction_type;
	}
	
	public String getTransaction_tag ()
	{
	return transaction_tag;
	}
	
	public void setTransaction_tag (String transaction_tag)
	{
	this.transaction_tag = transaction_tag;
	}
	
	public String getCurrency_code ()
	{
	return currency_code;
	}
	
	public void setCurrency_code (String currency_code)
	{
	this.currency_code = currency_code;
	}
}


