package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.ANY, setterVisibility = Visibility.ANY)
public class Ship_to_address
{
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("zip")
	private String zip;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("customer_number")
	private String customer_number;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("phone")
	private String phone;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("email")
	private String email;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("name")
	private String name;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("state")
	private String state;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("address_1")
	private String address_1;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("country")
	private String country;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("city")
	private String city;
	
	public String getZip ()
	{
	return zip;
	}
	
	public void setZip (String zip)
	{
	this.zip = zip;
	}
	
	public String getCustomer_number ()
	{
	return customer_number;
	}
	
	public void setCustomer_number (String customer_number)
	{
	this.customer_number = customer_number;
	}
	
	public String getPhone ()
	{
	return phone;
	}
	
	public void setPhone (String phone)
	{
	this.phone = phone;
	}
	
	public String getEmail ()
	{
	return email;
	}
	
	public void setEmail (String email)
	{
	this.email = email;
	}
	
	public String getName ()
	{
	return name;
	}
	
	public void setName (String name)
	{
	this.name = name;
	}
	
	public String getState ()
	{
	return state;
	}
	
	public void setState (String state)
	{
	this.state = state;
	}
	
	public String getAddress_1 ()
	{
	return address_1;
	}
	
	public void setAddress_1 (String address_1)
	{
	this.address_1 = address_1;
	}
	
	public String getCountry ()
	{
	return country;
	}
	
	public void setCountry (String country)
	{
	this.country = country;
	}
	
	public String getCity ()
	{
	return city;
	}
	
	public void setCity (String city)
	{
	this.city = city;
	}
}
