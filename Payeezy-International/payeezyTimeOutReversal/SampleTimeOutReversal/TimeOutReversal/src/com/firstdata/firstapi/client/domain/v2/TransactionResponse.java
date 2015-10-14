package com.firstdata.firstapi.client.domain.v2;

import java.util.ArrayList;
import org.codehaus.jackson.annotate.JsonAutoDetect;
//import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransactionResponse implements Cloneable{

    @Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		TransactionResponse cloneObject = (TransactionResponse)super.clone();
		cloneObject.card = cloneObject.card.clone();
		cloneObject.error = (Error)cloneObject.error.clone(); 
		return cloneObject;
	}



	/**
     * {"method":"credit_card","amount":"1100","currency":"USD","avs":"Z","cvv2":"I",
     * "card":{"type":"visa","cvv":"123","cardholder_name":"Ritesh Shah","card_number":"8291","exp_date":"1214"},
     * "transaction_status":"approved","validation_status":"success","transaction_type":"PURCHASE",
     * "transaction_id":"OK3740","transaction_tag":"1597368","transarmor_token":"2537446225198291",
     * "correlation_id":"55.1410534812572"} 
     * */
	/*public TransactionResponse() {
	}*/
	
	@JsonProperty("transaction_status")
	private String transactionStatus;
	@JsonProperty("validation_status")
	private String validationStatus;
	@JsonProperty("transaction_type")
	public static String transactionType;


	@JsonProperty("transaction_id")
	public static String transactionId;

	//changed to static for tokenised
	@JsonProperty("transaction_tag")

	public static String transactionTag;
	@JsonProperty("method")
	public static String method;
	@JsonProperty("amount")
	public static String amount;
	@JsonProperty("currency")
	public static String currency;
	@JsonProperty("avs")	
	private String avs;
	@JsonProperty("cvv2")
	private String cvv2;
    //@JsonProperty("transarmor_token")
	@JsonProperty("token")
	private String tokenString;
	
	@JsonProperty("token")
	static Token token;
//for DCC


	@JsonProperty("rate_id")
	public static String rate_id;

	@JsonProperty("dcc_offered")
	public static String dcc_offered;


	@JsonProperty("type")
	public static String type;

	@JsonProperty("cardholder_name")
	public static String cardholdername;

	@JsonProperty("card_number")
	public static String cardnumber;

	@JsonProperty("exp_date")
	public static String expirydate;
//check
	@JsonProperty("cvv")
	public static String cvv;

	public static String getExpiryDate() {
		return expirydate;
	}

	public static String getCardnumber() {
		return cardnumber;
	}

	public static String getCardHolderName() {
		return cardholdername;
	}
	public static String getType() {
		return type;
	}
	public static String getRate_id() {
		return rate_id;
	}

	public static String getDcc_offered() {
		return dcc_offered;
	}
//	public void setTransactionId(String transactionId) {
//		this.transactionId = transactionId;
//	}



	//change for the tokenised transaction
    public static Token getToken() {
		return token;
	}


	public void setToken(Token token) {
		this.token = token;
	}



	@JsonProperty("card")
    private Card card;
    @JsonProperty("Error")
    private Error error;
	@JsonProperty("correlation_id")
	//private String correlationID;
	public static String correlationID;
	@JsonProperty("bank_resp_code")
	private String bankRespCode;
	
	@JsonProperty("bank_message")
	private String bankMessage;
	
	@JsonProperty("gateway_resp_code")
	private String gatewayRespCode;
	
	@JsonProperty("gateway_message")
	private String gatewayMessage;
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	//public String getType() {
	//	return type;
//	}


	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("status")
    private String status;

//	@JsonProperty("type")
  //  private String type;
	
	@JsonProperty("START_OBJECT")
	private String STARTOBJECT;
	
	public String getSTARTOBJECT() {
		return STARTOBJECT;
	}

	
	public void setSTARTOBJECT(String STARTOBJECT) {
		this.STARTOBJECT = STARTOBJECT;
	}
	
	
	public String getBankRespCode() {
		return bankRespCode;
	}

	
	public void setBankRespCode(String bankRespCode) {
		this.bankRespCode = bankRespCode;
	}
	
	public String getBankMessage() {
		return bankMessage;
	}
	public void setBankMessage(String bankMessage) {
		this.bankMessage = bankMessage;
	}

	public String getGatewayRespCode() {
		return gatewayRespCode;
	}
	public void setGatewayRespCode(String gatewayRespCode) {
		this.gatewayRespCode = gatewayRespCode;
	}
	
	public String getGatewayMessage() {
		return gatewayMessage;
	}
	public void setGatewayMessage(String gatewayMessage) {
		this.gatewayMessage = gatewayMessage;
	}

	
	
	public String getTransactionStatus() {
		return transactionStatus;
	}

	
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	
	public String getValidationStatus() {
		return validationStatus;
	}

	
	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}


	
	
	public static String getTransactionType() {
		return transactionType;
	}


	
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	
	public static String getTransactionId() {
		return transactionId;
	}

	
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	
	public static String getTransactionTag() {
		return transactionTag;
	}


	
	public void setTransactionTag(String transactionTag) {
		this.transactionTag = transactionTag;
	}
	
	
	public static String getMethod() {
		return method;
	}

	
	public void setMethod(String method) {
		this.method = method;
	}


	
	public static String getAmount() {
		return amount;
	}


	
	public void setAmount(String amount) {
		this.amount = amount;
	}


	
	public static String getCurrency() {
		return currency;
	}


	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	
	public String getAvs() {
		return avs;
	}


	
	public void setAvs(String avs) {
		this.avs = avs;
	}


	
	public String getCvv2() {
		return cvv2;
	}


	
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}




	public String getTokenString() {
		return tokenString;
	}


	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}

	
	public Card getCard() {
		return card;
	}


	
	public void setCard(Card card) {
		this.card = card;
	}


	
	public Error getError() {
		return error;
	}

	
	
	public void setErrMessage(ArrayList<String> messages){
		error= new Error();
		error.setMessage(messages);
	}


    
    public static String getCorrelationID() {
        return correlationID;
    }


    
    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }


    
/*    public void setError(Error error) {
        this.error = error;
    }
*/
	//For nakedRefund DCC



}
