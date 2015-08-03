package com.example.sampleapp6;


public class TransactionDataProviderSS extends TransactionDataProviderBase {

	public  String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
	public  String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
	public  String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
	public  String url = "https://api-cert.payeezy.com/v1";
	
	public  String  Amount = "1100";
	public  String Currency ="USD";
	public  String Method ="credit_card";
	public  String TransactionType = "AUTHORIZE";
    
	public  String Cvv = "123";
	public  String ExpiryDt = "1216";
	public  String Name = "Test data ";
	public  String Type = "discover";
    
	public  String Number = "6011000990099818";
    public  String State = "NY";
	public  String AddressLine1 = "sss";
	public  String Zip ="11747";
	public  String Country = "US";

	public  String secPaymentMethod = "credit_card";
	public  String secAmount = "1100";
	public  String secCurrency = "USD";
	public  String secTransactionTag = "349990997";
	public  String secTransactionId = "07698G";
	
	public  String secAppId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
	public  String secSecureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
	public  String secToken = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";

	public  String voidAmount = "1100";
	public  String voidCurrency ="USD";
	public  String voidMethod ="credit_card";
	public  String voidTransactionType = "AUTHORIZE";
    
	public  String voidCvv = "123";
	public  String voidExpiryDt = "1216";
	public  String voidName = "Test data ";
	public  String voidType = "discover";
    
	public  String voidNumber = "6011000990099818";
    public  String voidState = "NY";
	public  String voidAddressLine1 = "sss";
	public  String voidZip ="11747";
	public  String voidCountry = "US";

	//Level2
	public String tax2_number = "3";
    public String tax2_amount = "5";
    public String tax1_number = "2";
    public String tax1_amount = "10";
    public String customer_ref = "customer1";
	
	//Level3
    public String duty_amount = "0.5";
	//public Ship_to_address shiptoaddress = new Ship_to_address();
	
	public String shiptoaddress_Cust_number = "12345";
	public String shiptoaddress_Phone = "212-515-1111";
	public String shiptoaddress_Email = "abc@firstdata.com";
	public String shiptoaddress_Name = "Bob Smith";
	public String shiptoaddress_State = "NY";
	public String shiptoaddress_Zip = "11235";
	public String shiptoaddress_Address_1 = "123 Main Street";
	public String shiptoaddress_Country = "USA";
	public String shiptoaddress_City = "New York";
	
	
	public String alt_tax_id = "098841111";
	public String alt_tax_amount = "10";
	public String freight_amount = "5";
	public String ship_from_zip = "11235";
	public String level3_discount_amount = "1";
	
	//line_items
	public String tax_type = "Federal";
	public String unit_cost = "1";
	public String lineitem_discount_amount = "1";
	public String line_item_total = "10";
	public String unit_of_measure = "meters";
	public String discount_indicator = "G";
	public String gross_net_indicator = "P";
	public String description = "item 1";
	public String commodity_code = "C";
	public String product_code = "F";
	public String tax_amount = "5";
	public String quantity = "5";
	public String tax_rate = "0.2800000000000000266453525910037569701671600341796875";
	
	//paypal_transaction_details
	public String message = "Success";
	public String timestamp = "2014/10/06 15:00:06";
	public String payer_id = "ADUMMYPAYERID";
	public String gross_amount_currency_id = "USD";
	public String authorization = "O-123456789ABCDEFGH";
	public String cardholder_name = "ARTHUR DIGBY SELLERS";
	public String success = "true";
	public String correlation_id = "11d8cac11e8";
	
	public String paypal_method = "paypal";
	public String paypal_transactiontype = "order";
	
	
	//recurring
	public String amount = "";
	public String merchant_ref = "";
	public String transaction_type = "";
	public String transaction_tag = "";
	public String currency_code = "";
	
	public String recurring_eci_indicator = "5";
	
	public String split_shipment = "1/3";
	
	
	//shiptoaddress
	public String zip = "";
	public String customer_number = "";
	public String phone = "";
	public String email = "";
	public String name = "";
	public String state = "";
	public String address_1 = "";
	public String country = "";
	public String shipto_city = "";
	
	//softdescriptor
	public String dba_name = "SoftDesc 1";
	public String street = "123 Main Street";
	public String sd_city = "New York";
	public String region = "NY";
	public String mid = "000367337278884";
	public String mcc = "8812";
	public String postalCode = "113750000000000";
	public String countryCode = "USA";
	public String merchantContactInfo = "123 Main street";
	
	//ThreeDomainSecureToken
	public String type = "";    
	public String transactionId = "";	
	public String encryptedData = "";	
	public String publicKeyHash = "";
	public String wrappedKey = "";
	public String symmetricKeyInfo = "";
	public String asymmetricKeyInfo = "";
	public String pkcs7Signature = "";
	public String eciIndicator = "5";
	public String merchantIdentifier = "";
	public String signatureAlgInfo = "";
	public String cavv = "";
	
	//3DS
	public String ThreeDS_card_number = "4788250000028291";
	public String xid = "1234567";
	public String cvv = "123";
	public String ThreeDS_exp_date = "1014";
	public String ThreeDS_cardholder_name = "xyz";
	public String ThreeDS_type = "D";
	public String ThreeDS_cavv = "01ade6ae340005c681c3a1890418b53000020000";
	
	public String method3DS = "3DS";
	
	//token_data
	public String td_cvv = "999";
	public String td_value = "8467121442600026";
	public String td_exp_date = "0416";
	public String td_cardholder_name = "xyz";
	public String td_type = "visa";
	
	//token
	//public Token_data token_data = new Token_data();
	public String token_type = "transarmor";
	
	//AVS
	public String phone_number = "212-515-1212";
	public String phone_type = "cell";
	
}
