package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Token
{
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("token_data")
	//private Token_data token_data;
	private Transarmor tokendata;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("token_type")
	private String token_type;
	
	//@JsonInclude(Include.NON_NULL)
	//@JsonProperty("token_data")
	//private Token_data token_data;
	//private Transarmor tokendata;
	
	
	public Transarmor getTokenData ()
	{
		return tokendata;
	}
	
	public void setTokenData (Transarmor tokendata)
	{
		this.tokendata = tokendata;
	}
	/*
	public Token_data getToken_data ()
	{
		return token_data;
	}
	
	public void setToken_data (Token_data tokendata)
	{
		this.token_data = tokendata;
	}
	*/
	public String getToken_type ()
	{
		return token_type;
	}
	
	public void setToken_type (String token_type)
	{
	this.token_type = token_type;
	}
}
	
	
