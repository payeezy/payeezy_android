package com.example.payeezyclient;

public class PZToken {

	String APIKey = "";
	String Secret = "";
	String Token = "";
	String TRToken = "";
	
	public String getTRToken() {
		return TRToken;
	}
	public void setTRToken(String tRToken) {
		TRToken = tRToken;
	}
	public String getAPIKey() {
		return APIKey;
	}
	public void setAPIKey(String aPIKey) {
		APIKey = aPIKey;
	}
	public String getSecret() {
		return Secret;
	}
	public void setSecret(String secret) {
		Secret = secret;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	
}
