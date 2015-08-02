package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserTransactionResponse extends TransactionResponse implements Cloneable {

	/*
	 * X-Backside-Transport=[OK OK,OK OK], 
			Transfer-Encoding=[chunked], 
			X-Powered-By=[Servlet/3.0], 
			OPTR_CXT=[010001000071f0923d-b5a2-492b-9a09-85d28769924f00000000-0000-0000-0000-000000000000-1  
			          HTTP    ;],
			          Content-Type=[application/json], 
			          Content-Language=[en-US],
			          Date=[Thu, 05 Feb 2015 18:55:37 GMT], 
			          X-Client-IP=[10.191.25.124,54.236.202.5], 
			          X-Archived-Client-IP=[10.191.25.124],
			          Connection=[keep-alive]
	 * */
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		UserTransactionResponse cloneObject = (UserTransactionResponse)super.clone();
		return cloneObject;
	}

	@JsonProperty("X-Backside-Transport")
	private String XBacksideTransport;
	
	@JsonProperty("Transfer-Encoding")
	private String TransferEncoding;
	
	@JsonProperty("OPTR_CXT")
	private String OPTR_CXT;
	
	@JsonProperty("Content-Type")
	private String ContentType;
	
	@JsonProperty("Content-Language")
	private String ContentLanguage;
	
	@JsonProperty("Date")
	private String Date;
	
	@JsonProperty("X-Client-IP")
	private String XClientIP;
	
	@JsonProperty("X-Archived")
	private String XArchived;
	
	@JsonProperty("Connection")
	private String Connection;
	
	//@JsonProperty("Body")
	//private TransactionResponse body;
	
	@JsonProperty("ResponseCode")
	private String ResponseCode;
	
	@JsonProperty("ResponseMessage")
	private String ResponseMessage;
	
	@JsonProperty("correlation_id")
	private String correlationId;
	
	@JsonProperty("avs")
	private String avs;
	
	public String getAvs() {
		return avs;
	}

	public void setAvs(String avs) {
		this.avs = avs;
	}
	
	@JsonProperty("X-Powered-By")
	private String xpoweredby;
	
	public String getXpoweredby() {
		return xpoweredby;
	}

	public void setXpoweredby(String xpoweredby) {
		this.xpoweredby = xpoweredby;
	}
	
	@JsonProperty("token_type")
	private String tokentype;
	
	public String getTokenType() {
		return tokentype;
	}

	public void setTokenType(String tokentype) {
		this.tokentype = tokentype;
	}
	
	@JsonProperty("token_data")
	private String tokendata;
	
	public String getTokenData() {
		return tokendata;
	}

	public void setTokenData(String tokendata) {
		this.tokendata = tokendata;
	}
	
	@JsonProperty("X-Archived-Client-IP")
	private String XArchivedClientIP;
	
	public String getXArchivedClientIP() {
		return XArchivedClientIP;
	}

	public void setXArchivedClientIP(String XArchivedClientIP) {
		this.XArchivedClientIP = XArchivedClientIP;
	}
	
	
	public String getResponseCode() {
		return ResponseCode;
	}

	public void setResponseCode(String ResponseCode) {
		this.ResponseCode = ResponseCode;
	}
	
	public String getcorrelationId() {
		return correlationId;
	}

	public void setcorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	
	public String getResponseMessage() {
		return ResponseMessage;
	}

	public void setResponseMessage(String ResponseMessage) {
		this.ResponseMessage = ResponseMessage;
	}
	
	public String getXBacksideTransport() {
		return XBacksideTransport;
	}

	public void setXBacksideTransport(String XBacksideTransport) {
		this.XBacksideTransport = XBacksideTransport;
	}
	
	public String getTransferEncoding() {
		return TransferEncoding;
	}

	public void setTransferEncoding(String TransferEncoding) {
		this.TransferEncoding = TransferEncoding;
	}
	
	public String getContentType() {
		return ContentType;
	}

	public void setContentType(String ContentType) {
		this.ContentType = ContentType;
	}
	
	public String getOPTR_CXT() {
		return OPTR_CXT;
	}

	public void setOPTR_CXT(String OPTR_CXT) {
		this.OPTR_CXT = OPTR_CXT;
	}
	
	
	public String getContentLanguage() {
		return ContentLanguage;
	}

	public void setContentLanguage(String ContentLanguage) {
		this.ContentLanguage = ContentLanguage;
	}
	
	public String getDate() {
		return Date;
	}

	public void setDate(String Date) {
		this.Date = Date;
	}
	
	public String getXClientIP() {
		return XClientIP;
	}

	public void setXClientIP(String XClientIP) {
		this.XClientIP = XClientIP;
	}
	
	public String getXArchived() {
		return XArchived;
	}

	public void setXArchived(String XArchived) {
		this.XArchived = XArchived;
	}
	
	public String getConnection() {
		return Connection;
	}

	public void setConnection(String Connection) {
		this.Connection = Connection;
	}
	

	
	private String appResponseString;
	
	public String getResponseString() {
		return appResponseString;
	}
	public void setResponseString(String responseString) {
		appResponseString = responseString;
	}
	
	//public TransactionResponse getBody() {
	public TransactionResponse getBody1() {
		//return body;
		return this;
		/*
		TransactionResponse tr = null;
		try
		{
			tr = (TransactionResponse)super.clone();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return tr;
		*/
	}

	public void setBody(TransactionResponse body) {
		//this.body = body;
		this.setAmount(body.getAmount()) ;
		this.setAvs(body.getAvs()) ;
		this.setBankMessage(body.getBankMessage()) ;
		this.setBankRespCode(body.getBankRespCode()) ;
		this.setCard(body.getCard()) ;
		this.setCorrelationID(body.getCorrelationID()) ;
		this.setCurrency(body.getCurrency()) ;
		this.setCvv2(body.getCvv2()) ;
		this.setGatewayMessage(body.getGatewayMessage()) ;
		this.setGatewayRespCode(body.getGatewayRespCode()) ;
		this.setMethod(body.getMethod()) ;
		this.setSTARTOBJECT(body.getSTARTOBJECT()) ;
		this.setTokenString(body.getTokenString()) ;
		this.setTransactionId(body.getTransactionId()) ;
		this.setTransactionStatus(body.getTransactionStatus()) ;
		this.setTransactionTag(body.getTransactionTag()) ;
		this.setTransactionType(body.getTransactionType()) ;
		this.setValidationStatus(body.getValidationStatus()) ;
		this.setCard(body.getCard()) ;
		//this.setErrMessage(body.getError()) ;
		
	}
}
