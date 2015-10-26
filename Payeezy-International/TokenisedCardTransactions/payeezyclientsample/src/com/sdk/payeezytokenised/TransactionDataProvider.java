package com.sdk.payeezytokenised;


import org.codehaus.jackson.annotate.JsonProperty;

public class TransactionDataProvider {

	//use these credentials after 14th oct
	public static String appIdCert ="y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
	public static String tokenCert ="fdoa-d790ce8951daa73262645cf102641994c1e55e7cdf4c03c8";

	public static String urlCert ="https://api-cert.payeezy.com/v1/transactions";
	public static String secureIdCert="86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
	public static String tokenUrl="https://api-cert.payeezy.com/v1/securitytokens?";
public static String jsSecurityKey="js-10466ef43dd9b0762626f1c20b7f026710466ef43dd9b076";

	//German Direct Debit
	public static String appIdGD ="ehCz1VGlwNeeGcN5LjA5c2nvWKTnEZRn";
	public static String tokenGD ="fdoa-d790ce8951daa73262645cf102641994c1e55e7cdf4c03c8";
	public static String urlGD ="https://api-qa.payeezy.com/v1/transactions";
	public static String secIDGD="2b940ece234ee38131e70cc617aa2afa3d7ff8508856917958e7feb3gk289436";

	//Random number for merchantrefno
	@JsonProperty("merchant_ref")
	 static String merchantref;

	public static String getmerchantref()
	{
		return merchantref;
	}


}
