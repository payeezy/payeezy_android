package com.sdk.payeezytokenised;


public class TransactionDataProviderL3 {

	//public  String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
	//public  String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
	//public  String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
	//public  String url = "https://api-cert.payeezy.com/v1";
	
	public  String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
	public  String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
	public  String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
	public  String url = "https://api-cat.payeezy.com/v1";
	
	
	//public  String  Amount = "1100";
	public  String  Amount = "00";
	public  String Currency ="USD";
	public  String Method ="credit_card";
	public  String TransactionType = "AUTHORIZE";
    
	public  String Cvv = "123";
	public  String ExpiryDt = "1216";
	public  String Name = "Test data ";
	public  String Type = "visa";
    
	public  String Number = "4012000033330026";
    public  String State = "NY";
	public  String AddressLine1 = "sss";
	public  String Zip ="11747";
	public  String Country = "US";

	public  String merchantRef = "abc1412096293369";
	public  String referenceNo = "abc1412096293369";
	
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
	public  String voidType = "visa";
    
	public  String voidNumber = "4012000033330026";
    public  String voidState = "NY";
	public  String voidAddressLine1 = "sss";
	public  String voidZip ="11747";
	public  String voidCountry = "US";

	//Level2
	public String level2_tax2_number = "3";
    public String level2_tax2_amount = "5";
    public String level2_tax1_number = "2";
    public String level2_tax1_amount = "10";
    public String level2_customer_ref = "customer1";
	
	//Level3
    public String level3_duty_amount = "0.5";
	//public Ship_to_address shiptoaddress = new Ship_to_address();
	
	public String level3_shiptoaddress_Cust_number = "12345";
	public String level3_shiptoaddress_Phone = "212-515-1111";
	public String level3_shiptoaddress_Email = "abc@firstdata.com";
	public String level3_shiptoaddress_Name = "Bob Smith";
	public String level3_shiptoaddress_State = "NY";
	public String level3_shiptoaddress_Zip = "11235";
	public String level3_shiptoaddress_Address_1 = "123 Main Street";
	public String level3_shiptoaddress_Country = "USA";
	public String level3_shiptoaddress_City = "New York";
	
	public String level3_alt_tax_id = "098841111";
	public String level3_alt_tax_amount = "10";
	public String level3_freight_amount = "5";
	public String level3_ship_from_zip = "11235";
	public String level3_discount_amount = "1";
	
	//line_items
	public String litem_tax_type = "Federal";
	public String litem_unit_cost = "1";
	public String litem_discount_amount = "1";
	public String litem_line_item_total = "10";
	public String litem_unit_of_measure = "meters";
	public String litem_discount_indicator = "G";
	public String litem_gross_net_indicator = "P";
	public String litem_description = "item 1";
	public String litem_commodity_code = "C";
	public String litem_product_code = "F";
	public String litem_tax_amount = "5";
	public String litem_quantity = "5";
	public String litem_tax_rate = "0.2800000000000000266453525910037569701671600341796875";
	
	//lineitem_level3
	//line_items
	public String level3_litem_tax_type = "Federal";
	public String level3_litem_unit_cost = "1";
	public String level3_litem_discount_amount = "1";
	public String level3_litem_line_item_total = "10";
	public String level3_litem_unit_of_measure = "meters";
	public String level3_litem_discount_indicator = "G";
	public String level3_litem_gross_net_indicator = "P";
	public String level3_litem_description = "item 1";
	public String level3_litem_commodity_code = "C";
	public String level3_litem_product_code = "F";
	public String level3_litem_tax_amount = "5";
	public String level3_litem_quantity = "5";
	public String level3_litem_tax_rate = "0.2800000000000000266453525910037569701671600341796875";
	
	
	//paypal_transaction_details
	public String paypal_message = "Success";
	public String paypal_timestamp = "2014/10/06 15:00:06";
	public String paypal_payer_id = "ADUMMYPAYERID";
	public String paypal_gross_amount_currency_id = "USD";
	public String paypal_authorization = "O-123456789ABCDEFGH";
	public String paypal_cardholder_name = "ARTHUR DIGBY SELLERS";
	public String paypal_success = "true";
	public String paypal_correlation_id = "11d8cac11e8";
	
	public String paypal_method = "paypal";
	public String paypal_transactiontype = "order";
	
	
	//recurring
	public String recurring_amount = "";
	public String recurring_merchant_ref = "";
	public String recurring_transaction_type = "";
	public String recurring_transaction_tag = "";
	public String recurring_currency_code = "";
	
	public String recurring_eci_indicator = "5";
	
	public String split_shipment = "1/3";
	
	
	//shiptoaddress
	public String ship_to_address_zip = "";
	public String ship_to_address_customer_number = "";
	public String ship_to_address_phone = "";
	public String ship_to_address_email = "";
	public String ship_to_address_name = "";
	public String ship_to_address_state = "";
	public String ship_to_address_address_1 = "";
	public String ship_to_address_country = "";
	public String ship_to_address_shipto_city = "";
	
	//softdescriptor
	public String sdescriptor_dba_name = "SoftDesc 1";
	public String sdescriptor_street = "123 Main Street";
	public String sdescriptor_sd_city = "New York";
	public String sdescriptor_region = "NY";
	public String sdescriptor_mid = "000367337278884";
	public String sdescriptor_mcc = "8812";
	public String sdescriptor_postalCode = "113750000000000";
	public String sdescriptor_countryCode = "USA";
	public String sdescriptor_merchantContactInfo = "123 Main street";
	
	//ThreeDomainSecureToken
	public String ThreeDomainSecureToken_type = "";    
	public String ThreeDomainSecureToken_transactionId = "";	
	public String ThreeDomainSecureToken_encryptedData = "";	
	public String ThreeDomainSecureToken_publicKeyHash = "";
	public String ThreeDomainSecureToken_wrappedKey = "";
	public String ThreeDomainSecureToken_symmetricKeyInfo = "";
	public String ThreeDomainSecureToken_asymmetricKeyInfo = "";
	public String ThreeDomainSecureToken_pkcs7Signature = "";
	public String ThreeDomainSecureToken_eciIndicator = "5";
	public String ThreeDomainSecureToken_merchantIdentifier = "";
	public String ThreeDomainSecureToken_signatureAlgInfo = "";
	public String ThreeDomainSecureToken_cavv = "";
	
	//3DS
	public String ThreeDS_card_number = "4788250000028291";
	public String ThreeDS_xid = "1234567";
	public String ThreeDS_cvv = "123";
	public String ThreeDS_exp_date = "1014";
	public String ThreeDS_cardholder_name = "xyz";
	public String ThreeDS_type = "D";
	public String ThreeDS_cavv = "01ade6ae340005c681c3a1890418b53000020000";
	
	public String method3DS = "3DS";
	
	//token_data
	/*
	public String td_cvv = "999";
	public String td_value = "8467121442600026";
	public String td_exp_date = "0416";
	public String td_cardholder_name = "xyz";
	public String td_type = "visa";
	*/
	public String td_cvv = "1234";
	public String td_value = "028426321341004";
	public String td_exp_date = "0416";
	public String td_cardholder_name = "xyz";
	public String td_type = "American Express";

	
	//token
	//public Token_data token_data = new Token_data();
	public String token_type = "transarmor";
	
	//AVS
	public String phone_number = "212-515-1212";
	public String phone_type = "cell";
	
	//Naked Refunds
	public String eci_indicator = "5";
	public String nakedrefundcardtype = "mastercard";
	public String nakedrefundcardholdername = "JT Refund";
	public String nakedrefundcardnumber = "5186009100016415";
	public String nakedrefundcardexpdate = "1217";
	
	public String nakedrefundcardtypecat = "visa";
	public String nakedrefundcardholdernamecat = "Eck Test 3";
	public String nakedrefundcardnumbercat = "4788250000028291";
	public String nakedrefundcardexpdatecat = "1217";
	
	
	public String Cvv2 = "123";
	
	public String valuelink_cardcost = "5";
	//public String valuelink_cardcost = "5";
	//public String valuelink_cardcost = "5";
	//public String valuelink_cardcost = "5";
	
	//L3Cat
	/*
	public String amount = 9200;
	public String transaction_type = purchase;
	public String merchant_ref = abc1412096293369;
	public String method = credit_card;
	public String currency_code = USD;
	//public String credit_card":{
	public String type = visa; 
	public String cardholder_name = Eck Test 3;
	public String card_number = 4012000033330026;
	public String exp_date = 0416;
	public String cvv = 123"

		//"level2":{
	public String tax1_amount":10,
	public String tax1_number = 2;
	public String tax2_amount":5,
	public String tax2_number = 3;
	public String customer_ref = customer1"
//
		//"level3":{
	public String alt_tax_amount":10,
	public String alt_tax_id = 098841111;
	public String discount_amount":1,
	public String duty_amount":0.5,
	public String freight_amount":5,
	public String ship_from_zip = 11235;
	public String ship_to_address":{
	public String city = New York;
	public String state = NY"
			public String zip = 11235;
	public String country = USA;
	public String email = abc@firstdata.com;
	public String name = Bob Smith;
	public String phone = 212-515-1111;
	public String address_1 = 123 Main Street;
	public String customer_number = 12345"
	//	},
	//public String line_items":
	public String description = item 1;
	public String quantity = 5;
	public String commodity_code = C;
	public String discount_amount":1,
	public String discount_indicator = G;
	public String gross_net_indicator = P;
	public String line_item_total":10,
	public String product_code = F;
	public String tax_amount":5,
	public String tax_rate":0.2800000000000000266453525910037569701671600341796875,
	public String tax_type = Federal;
	public String unit_cost":1;
	public String unit_of_measure = meters";
		
	*/
	
	
	
}
