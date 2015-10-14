package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ThreeDomainSecureToken {
	
	public ThreeDomainSecureToken() {

	}
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("type")
	private String type;    
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("transactionId")
	private String transactionId;	
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("encryptedData")
	private String encryptedData;	
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("publicKeyHash")
	private String publicKeyHash;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("wrappedKey")
	private String wrappedKey;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("symmetricKeyInfo")
	private String symmetricKeyInfo;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("asymmetricKeyInfo")
	private String asymmetricKeyInfo;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("pkcs7Signature")
	private String pkcs7Signature;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("eciIndicator")
	private String eciIndicator;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchantIdentifier")
	private String merchantIdentifier;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("signatureAlgInfo")
	private String signatureAlgInfo;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("cavv")
	private String cavv;
	

	
	
	@JsonProperty("cavv")
	public String getCavv() {
		return cavv;
	}
	
	@JsonProperty("cavv")
	public void setCavv(String cavv) {
		this.cavv = cavv;
	}
	
	@JsonProperty("type")
	public String getType() {
		return type;
	}
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonProperty("transactionId")
	public String getTransactionId() {
		return transactionId;
	}

	@JsonProperty("transactionId")
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}	
	
	@JsonProperty("encryptedData")
	public String getEncryptedData() {
		return encryptedData;
	}

	@JsonProperty("encryptedData")
	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}
	
	@JsonProperty("publicKeyHash")
	public String getPublicKeyHash() {
		return publicKeyHash;
	}

	@JsonProperty("publicKeyHash")
	public void setPublicKeyHash(String publicKeyHash) {
		this.publicKeyHash = publicKeyHash;
	}	
	
	@JsonProperty("wrappedKey")
	public String getWrappedKey() {
		return wrappedKey;
	}

	@JsonProperty("wrappedKey")
	public void setWrappedKey(String wrappedKey) {
		this.wrappedKey = wrappedKey;
	}
	
	
	@JsonProperty("symmetricKeyInfo")
	public String getSymmetricKeyInfo() {
		return symmetricKeyInfo;
	}

	@JsonProperty("symmetricKeyInfo")
	public void setSymmetricKeyInfo(String symmetricKeyInfo) {
		this.symmetricKeyInfo = symmetricKeyInfo;
	}		
	
	@JsonProperty("asymmetricKeyInfo")
	public String getAsymmetricKeyInfo() {
		return asymmetricKeyInfo;
	}

	@JsonProperty("asymmetricKeyInfo")
	public void setAsymmetricKeyInfo(String asymmetricKeyInfo) {
		this.asymmetricKeyInfo = asymmetricKeyInfo;
	}		
	
	@JsonProperty("pkcs7Signature")
	public String Pkcs7Signature() {
		return pkcs7Signature;
	}

	@JsonProperty("pkcs7Signature")
	public void setPkcs7Signature(String pkcs7Signature) {
		this.pkcs7Signature = pkcs7Signature;
	}		
	
	@JsonProperty("eciIndicator")
	public String EciIndicator() {
		return eciIndicator;
	}

	@JsonProperty("eciIndicator")
	public void setEciIndicator(String eciIndicator) {
		this.eciIndicator = eciIndicator;
	}		
	
	@JsonProperty("merchantIdentifier")
	public String MerchantIdentifier() {
		return merchantIdentifier;
	}

	@JsonProperty("merchantIdentifier")
	public void setMerchantIdentifier(String merchantIdentifier) {
		this.merchantIdentifier = merchantIdentifier;
	}	
	
	@JsonProperty("signatureAlgInfo")
	public String SignatureAlgInfo() {
		return signatureAlgInfo;
	}

	@JsonProperty("signatureAlgInfo")
	public void setSignatureAlgInfo(String signatureAlgInfo) {
		this.signatureAlgInfo = signatureAlgInfo;
	}		
	
	/*******************/
	
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
	
	/*
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("type")
	private String type;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("cavv")
	private String cavv;
	*/
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
	/*
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
	*/
	
}
