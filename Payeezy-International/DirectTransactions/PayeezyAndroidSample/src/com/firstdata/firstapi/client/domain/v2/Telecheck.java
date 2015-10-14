package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Telecheck 
{
	/*
	 *                 "tele_check":
                {   "check_number": "4788250000028291",
                "check_type": "C",
                "routing_number":"123456789",
                "account_number": "123",
                "accountholder_name": "Tom Eck",
                "customer_id_type":"1",
                "customer_id_number":"7623786df",
                "client_email":"rajan.veeramani@firstdata.com",
                "gift_card_amount":"100",
                "vip":"n",
                "clerk_id":"RVK_001",
                "device_id":"jkhsdfjkhsk",
                    "micr":"jkhjkh",
                "release_type":"X",
                "registration_number":"12345",
                "registration_date":"01012014",
                "date_of_birth":"01012010"
    },
	 * 
	 */
	
	public Telecheck() {
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("check_number")
	private String check_number;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("check_type")
	private String check_type;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("routing_number")
	private String routing_number;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("account_number")
	private String account_number;
	
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("accountholder_name")
	private String accountholder_name;
	
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("customer_id_type")
	private String customer_id_type;
	
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("customer_id_number")
	private String customer_id_number;
	
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("client_email")
	private String client_email;
    
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("gift_card_amount")
	private String gift_card_amount;
	
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("vip")
	private String vip;
    
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("clerk_id")
	private String clerk_id;
    
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("device_id")
	private String device_id;
    
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("release_type")
	private String release_type;
    
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("registration_number")
	private String registration_number;
    
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("registration_date")
	private String registration_date;
    
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("date_of_birth")
	private String date_of_birth;
    
	@JsonInclude(Include.NON_NULL)
    @JsonProperty("micr")
	private String micr;
    
	public String getCheck_number() {
		return check_number;
	}

	public void setCheck_number(String check_number) {
		this.check_number = check_number;
	}
		
	public String getCheck_type() {
		return check_type;
	}

	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}

	public String getRouting_number() {
		return routing_number;
	}

	public String getMicr() {
		return micr;
	}

	public void setMicr(String micr) {
		this.micr = micr;
	}

	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccountholder_name() {
		return accountholder_name;
	}

	public void setAccountholder_name(String accountholder_name) {
		this.accountholder_name = accountholder_name;
	}

	public String getCustomer_id_type() {
		return customer_id_type;
	}

	public void setCustomer_id_type(String customer_id_type) {
		this.customer_id_type = customer_id_type;
	}

	public String getCustomer_id_number() {
		return customer_id_number;
	}

	public void setCustomer_id_number(String customer_id_number) {
		this.customer_id_number = customer_id_number;
	}

	public String getClient_email() {
		return client_email;
	}

	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}

	public String getGift_card_amount() {
		return gift_card_amount;
	}

	public void setGift_card_amount(String gift_card_amount) {
		this.gift_card_amount = gift_card_amount;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getClerk_id() {
		return clerk_id;
	}

	public void setClerk_id(String clerk_id) {
		this.clerk_id = clerk_id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getRelease_type() {
		return release_type;
	}

	public void setRelease_type(String release_type) {
		this.release_type = release_type;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	@Override
	public Telecheck clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Telecheck)super.clone();
	}

	
	
}
