package com.example.payeezytokenised;

import com.firstdata.firstapi.client.domain.v2.TransactionRequest;

public class PayeezySDK {

	String apikey = ""; 
	String secret = ""; 
	String token = "";
	public PayeezySDK()
	{
		
	}
	public PayeezySDK(String apikey, String secret, String token)
	{
		this.apikey = apikey;
		this.secret = secret;
		this.token = token;
	}
	
	public String MakePayment(TransactionRequest request)
	{
		
		EnvCat envcat = new EnvCat(null);
		String responseString = envcat.ProcessPayment("","","","",null);
		return responseString;
	}
	
	public String MakeRecurringPayment(TransactionRequest request)
	{
		EnvCat envcat = new EnvCat(null);
		String responseString = envcat.ProcessRecurringPayment();
		return responseString;
	}
}
