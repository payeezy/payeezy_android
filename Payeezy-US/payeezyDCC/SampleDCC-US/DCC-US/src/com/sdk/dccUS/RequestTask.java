package com.sdk.dccUS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.firstdata.firstapi.client.FirstAPIClientV2Helper;
import com.firstdata.firstapi.client.domain.TransactionType;
import com.firstdata.firstapi.client.domain.v2.Address;
import com.firstdata.firstapi.client.domain.v2.Address.Phone;
import com.firstdata.firstapi.client.domain.v2.Card;
import com.firstdata.firstapi.client.domain.v2.Level2;
import com.firstdata.firstapi.client.domain.v2.Level3;
import com.firstdata.firstapi.client.domain.v2.Line_item;
import com.firstdata.firstapi.client.domain.v2.RateReference;
import com.firstdata.firstapi.client.domain.v2.Ship_to_address;
import com.firstdata.firstapi.client.domain.v2.SoftDescriptor;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;
import com.firstdata.firstapi.client.domain.v2.UserTransactionResponse;

import com.sample.dccUS.MainActivity;

enum CardType {
	CARD_VISA ,
	CARD_MASTERCARD ,
	CARD_AMEX ,
	CARD_DISCOVER ,
	CARD_VALUELINK, 
	CARD_TELECHECK,
	CARD_NONE,
	//CARD_DEBITCARD
	CARD_DIRECTDEBIT
};

enum RateType{
	CARD_RATE,
	MERCHANT_RATE
};

enum TransactionTypePrimarySecondary
{
	PURCHASE,
	CREDIT,
	VOID,
	REFUND,
	NAKEDREFUND

};
enum CardFeature{
	AVS,
	CVV,
	SOFT_DESCRIPTOR
};

@SuppressLint("DefaultLocale")
public class RequestTask extends AsyncTask<String, String, String> {

	private Context context = null;


	CardType cardtype;

	FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();


	public RequestTask(Context pcontext) {
		context = pcontext;
	}


	private String statusString = "";
	private String splitter = "~~~~~~~~";


	@Override
	protected String doInBackground(String... uri) {
/*************************DCC*************************************/

		if (uri[0].toLowerCase().equalsIgnoreCase("dcccvvpurchasevoid")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE, CardFeature.CVV,uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "dcc-cvv-purchasevoidCardRate";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("dcccvvpurchaserefund")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE, CardFeature.CVV, uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "dcc-cvv-purchaserefundCardRate";
		}

		if (uri[0].toLowerCase().equalsIgnoreCase("dccavspurchasevoid")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE, CardFeature.AVS,uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "dcc-avs-purchasevoidCardRate";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("dccavspurchaserefund")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE,CardFeature.AVS, uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "dcc-avs-purchaserefundCardRate";
		}

		if (uri[0].toLowerCase().equalsIgnoreCase("dccsdpurchasevoid")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE, CardFeature.SOFT_DESCRIPTOR,uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "dcc-sd-purchasevoidCardRate";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("dccsdpurchaserefund")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE,CardFeature.SOFT_DESCRIPTOR, uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "dcc-sd-purchaserefundCardRate";
		}

		if (uri[0].toLowerCase().equalsIgnoreCase("dcccvvpurchasevoidMerchantRate")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE, CardFeature.CVV,uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "dcc-cvv-purchasevoidMerchantRate";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("dcccvvpurchaserefundMerchantRate")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE, CardFeature.CVV, uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "dcc-cvv-purchaserefundMerchantRate";
		}

		if (uri[0].toLowerCase().equalsIgnoreCase("dccavspurchasevoidMerchantRate")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE, CardFeature.AVS,uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "dcc-avs-purchasevoidMerchantRate";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("dccavspurchaserefundMerchantRate")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE,CardFeature.AVS, uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "dcc-avs-purchaserefundMerchantRate";
		}

		if (uri[0].toLowerCase().equalsIgnoreCase("dccsdpurchasevoidMerchantRate")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE, CardFeature.SOFT_DESCRIPTOR,uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "dcc-sd-purchasevoidMerchantRate";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("dccsdpurchaserefundMerchantRate")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.PURCHASE,CardFeature.SOFT_DESCRIPTOR, uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "dcc-sd-purchaserefundMerchantRate";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("dcccvvnakedrefund")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.NAKEDREFUND, CardFeature.CVV, uri);


			return "dcc-cvv-nakedrefundCardRate";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("dccavsnakedrefund")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.NAKEDREFUND, CardFeature.AVS, uri);


			return "dcc-avs-nakedrefundCardRate";
		}

		if (uri[0].toLowerCase().equalsIgnoreCase("dccsdnakedrefund")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.NAKEDREFUND, CardFeature.SOFT_DESCRIPTOR, uri);


			return "dcc-sd-nakedrefundCardRate";
		}
//check the secondary
		if (uri[0].toLowerCase().equalsIgnoreCase("dcccvvnakedrefundMerchantRate")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.NAKEDREFUND, CardFeature.CVV, uri);


			return "dcc-cvv-nakedrefundMerchantRate";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("dccavsnakedrefundMerchantRate")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.NAKEDREFUND, CardFeature.AVS, uri);


			return "dcc-avs-nakedrefundMerchantRate";
		}

		if (uri[0].toLowerCase().equalsIgnoreCase("dccsdnakedrefundMerchantRate")) {
			CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary.NAKEDREFUND, CardFeature.SOFT_DESCRIPTOR, uri);


			return "dcc-sd-nakedrefundMerchantRate";
		}

		/******************************German Direct Debit*******************/
		//German Direct Debit
		if (uri[0].toLowerCase().equalsIgnoreCase("gdavspurchasevoid")) {
			CallAVSGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "avs-purchasevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdavspurchaserefund")) {
			CallAVSGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "avs-purchaserefund";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdavscreditvoid")) {
			CallAVSGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.CREDIT);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "avs-creditvoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdsdpurchasevoid")) {
			CallSDGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE);
			CallSDGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "softdescriptor-purchasevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdsdpurchaserefund")) {
			CallSDGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE);
			CallSDGermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "softdescriptor-purchaserefund";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdsdcreditvoid")) {
			CallSDGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.CREDIT);
			CallSDGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "softdescriptor-creditvoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdl2l3purchasevoid")) {
			CallL2L3GermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE);
			CallL2L3GermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "L2L3--purchasevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdl2l3purchaserefund")) {
			CallL2L3GermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE);
			CallL2L3GermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "L2L3-purchaserefund";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdl2l3creditvoid")) {
			CallL2L3GermanDirectPurchaseCredit(TransactionTypePrimarySecondary.CREDIT);
			CallL2L3GermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "L2L3-creditvoid";
		}


		return "fromdoInBackground";

	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		MainActivity.displayResult.setText(result);

		System.out.println("Button Authorize Clicked" + result);
		MainActivity.displayResult.setText(result + statusString);

}
	//Initialise method: for initialising the CardType, appId,SecretKey, token and Url for German Direct Debit
	private void Initialise() {
		cardtype = CardType.CARD_DIRECTDEBIT;
		clientHelper.setAppId(TransactionDataProvider.appIdGD);
		clientHelper.setSecuredSecret(TransactionDataProvider.secIDGD);
		clientHelper.setToken(TransactionDataProvider.tokenGD);
		clientHelper.setUrl(TransactionDataProvider.urlGD);
	}
//DCC-CVV

	//Initialise method: for initialising the CardType, appId,SecretKey, token and Url for German Direct Debit
	private void InitialiseDCC() {
		//change to DCC setttings
		clientHelper.setAppId(TransactionDataProvider.appIdDCC);
		clientHelper.setSecuredSecret(TransactionDataProvider.secIDDCC);
		clientHelper.setToken(TransactionDataProvider.tokenDCC);
		clientHelper.setUrl(TransactionDataProvider.urlDCC);
	}
	private void CallDCCCVVAVSSDPurchase(TransactionTypePrimarySecondary transactionType, CardFeature cardFeature,String[] uri){

		try {
			TransactionRequest request=new TransactionRequest();
			InitialiseDCC();
		//The request for sending the rateid payload
			if((uri[1]).equals ("card_rate"))
			 request = getPrimaryCardRateIDTransactionDCC(uri);
			else
			if((uri[1]).equals ("merchant_rate"))
			 request = getPrimaryMerchantRateIDTransactionDCC(uri);

			//if it is for the exchange rate;
			clientHelper.setUrl(TransactionDataProvider.urlDCCExchangeRate);
			Object responseObject1 = clientHelper.doPrimaryTransactionGD(request);

			statusString = statusString + ((UserTransactionResponse) responseObject1).getResponseString() + splitter;

			clientHelper.setUrl(TransactionDataProvider.urlDCC);
			TransactionRequest trans = getPrimaryTransactionDCC(uri);

				trans.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());



			switch(cardFeature)
			{

				case AVS:
					 trans=	callDCCAVSObjInit(trans,uri);
					break;
				case SOFT_DESCRIPTOR:
					 trans=CallDCCSoftDescObjInit(trans,uri);
					break;
				default:
					break;


			};
			Object responseObject2 = clientHelper.doPrimaryTransactionGD(trans);


			statusString = statusString + ((UserTransactionResponse) responseObject2).getResponseString() + splitter;
			TransactionRequest transactionRequest=new TransactionRequest();
			if ((transactionType) .equals (TransactionTypePrimarySecondary.NAKEDREFUND))
			{
				transactionRequest= getPrimaryTransactionDCC(uri);
				switch(cardFeature)
				{

					case AVS:
						transactionRequest=	callDCCAVSObjInit(trans,uri);
						break;
					case SOFT_DESCRIPTOR:
						transactionRequest=CallDCCSoftDescObjInit(trans,uri);
						break;
					default:
						break;


				};
				transactionRequest.setTransactionType(TransactionType.REFUND.name().toLowerCase());
				//transactionRequest=CallDCCNakedRefund();
				TransactionResponse responseObject = clientHelper.doSecondaryTransactionGD(transactionRequest);
				System.out.println("Response : " + responseObject.toString());

				statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	private TransactionRequest getPrimaryCardRateIDTransactionDCC(String[]uri)
	{
		TransactionRequest request=new TransactionRequest();

request.setRateType(uri[1]);
		request.setBin(uri[2]);
		request.setAmount(uri[3]);

		//Added for DCC-US
		//request.setType(uri[14]);

return request;
	}

	private TransactionRequest getPrimaryMerchantRateIDTransactionDCC(String[]uri)
	{
		TransactionRequest request=new TransactionRequest();
		request.setRateType(uri[1]);
		request.setCurrency(uri[2]);
		request.setAmount(uri[3]);
		return request;
	}
	private TransactionRequest callDCCAVSObjInit(TransactionRequest trans, String[] uri)
	{
Address address=new Address();
		address.setCity(uri[13]);
		address.setCountry(uri[14]);
		address.setEmail(uri[15]);

		Phone phone=new Phone();
		phone.setType(uri[16]);
		phone.setNumber(uri[17]);
		address.setPhone(phone);

		address.setAddressLine1(uri[18]);
		address.setState(uri[19]);
		address.setZip(uri[20]);

		trans.setBilling(address);

		return trans;
	}

	private TransactionRequest CallDCCSoftDescObjInit(TransactionRequest trans,String[]uri)
	{
SoftDescriptor softDescriptor=new SoftDescriptor();
		softDescriptor.setDba_name(uri[13]);
		softDescriptor.setStreet(uri[14]);
		softDescriptor.setRegion(uri[15]);
		softDescriptor.setMid(uri[16]);
		softDescriptor.setMcc(uri[17]);
		softDescriptor.setPostalCode(uri[18]);
		softDescriptor.setCountryCode(uri[19]);
		softDescriptor.setMerchantContactInfo(uri[20]);

		trans.setDescriptor(softDescriptor);
		return trans;
	}

	private TransactionRequest getPrimaryTransactionDCC(String[]uri ) {
		TransactionRequest request = new TransactionRequest();
System.out.println("I am in getPrimaryTransactionDCC");
		request.setAmount(uri[4]);
		request.setPaymentMethod(uri[6]);
		request.setCurrency(uri[7]);

		Card card = new Card();

		card.setType(uri[8]);
		card.setName(uri[9]);
		card.setNumber(uri[10]);
		card.setExpiryDt(uri[11]);
		card.setCvv(uri[12]);

		request.setCard(card);

		RateReference rateReference=new RateReference();
		rateReference.setRate_id(TransactionResponse.getRate_id());
		rateReference.setDccaccepted(TransactionResponse.getDcc_offered());

		/*if((uri[1]).equals ("card_rate"))
			rateReference.setType("dcc");
		else
		if((uri[1]).equals ("merchant_rate"))
			rateReference.setType("dp");*/
		request.setRateReference(rateReference);

		return request;
	}


//Naked Refund --Secondary
	private TransactionRequest CallDCCNakedRefund()
	{
		TransactionRequest request = new TransactionRequest();

		//to set the credit card

		//rate reference

		request.setAmount(TransactionResponse.getAmount());

		request.setCurrency(TransactionResponse.getCurrency());

		request.setPaymentMethod(TransactionResponse.getMethod());

request.setTransactionType(TransactionType.REFUND.name().toLowerCase());
	//	request.setTransactionTag(TransactionResponse.getTransactionTag());

		Card card = new Card();

		card.setType(TransactionResponse.getType());
		card.setName(TransactionResponse.getCardHolderName());
		card.setNumber("5471320000000001");
	//	card.setNumber(TransactionResponse.getCardnumber());
		card.setExpiryDt(TransactionResponse.getExpiryDate());
	//	card.setCvv(uri[12]);

		request.setCard(card);

		RateReference rateReference=new RateReference();
		rateReference.setRate_id(TransactionResponse.getRate_id());
		rateReference.setDccaccepted(TransactionResponse.getDcc_offered());
		request.setRateReference(rateReference);
		return request;
	}
	//German Direct-AVS
	private void CallAVSGermanDirectPurchaseCredit(TransactionTypePrimarySecondary transactionType) {
		try {
			Initialise();
			TransactionRequest trans = getPrimaryTransactionGD();
			if ((transactionType).equals (TransactionTypePrimarySecondary.PURCHASE))
				trans.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());

			else
				trans.setTransactionType(TransactionType.CREDIT.name().toLowerCase());

			Object responseObject = clientHelper.doPrimaryTransactionGD(trans);

			statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	//GD
	private TransactionRequest getPrimaryTransactionGD() {
		TransactionRequest request = new TransactionRequest();

		request.setAmount("1000");

		request.setCurrency("EUR");

		//request.setPaymentMethod("debit_card");
		request.setPaymentMethod("direct_debit");

		Card card = new Card();

		card.setiban("DE34500100600032121604");
		card.setmandate_ref("ABCD1234");
		card.setbic("PBNKDEFFXXX");

		request.setdCard(card);


		Address address = new Address();


		address.setName("P.Kunde");
		address.setCity("random");
		address.setEmail("Monalisa.mishra@firstdata.com");
		address.setZip("04103");
		address.setCountry("Germany");

		Phone phone = new Phone();

		phone.setType("Cell");
		phone.setNumber("678-468-2665");
		address.setPhone(phone);
		address.setAddressLine1("LangeStr.12");
		address.setState("LEIPZIG");
		request.setBilling(address);

		return request;
	}


	//GD-Secondary Transaction--void
	private void CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary transactionType) {
		try {
			InitialiseDCC();

			TransactionRequest trans = getSecondaryTransactionGD();
			if ((transactionType).equals (TransactionTypePrimarySecondary.VOID))

				trans.setTransactionType(TransactionType.VOID.name().toLowerCase());
			else
				trans.setTransactionType(TransactionType.REFUND.name().toLowerCase());

			TransactionResponse responseObject = clientHelper.doSecondaryTransactionGD(trans);
			System.out.println("Response : " + responseObject.toString());

			statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}


	}

	private TransactionRequest getSecondaryTransactionGD() {
		TransactionRequest request = new TransactionRequest();

		request.setAmount(TransactionResponse.getAmount());

		request.setCurrency(TransactionResponse.getCurrency());

		request.setPaymentMethod(TransactionResponse.getMethod());


		request.setTransactionTag(TransactionResponse.getTransactionTag());
		return request;


	}


	private void CallSDGermanDirectPurchaseCredit(TransactionTypePrimarySecondary transactionType) {
		try {
			Initialise();
			TransactionRequest trans = getPrimaryTransactionSD();
			if ((transactionType) .equals (TransactionTypePrimarySecondary.PURCHASE))
				trans.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());
			else
				trans.setTransactionType(TransactionType.CREDIT.name().toLowerCase());

			Object responseObject = clientHelper.doPrimaryTransactionGD(trans);

			statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//GD
	private TransactionRequest getPrimaryTransactionSD() {
		TransactionRequest request = new TransactionRequest();

		request.setAmount("1000");

		request.setCurrency("EUR");
	//	request.setPaymentMethod("debit_card");

		request.setPaymentMethod("direct_debit");


		Card card = new Card();

		card.setiban("DE34500100600032121604");
		card.setmandate_ref("ABCD1234");
		card.setbic("PBNKDEFFXXX");

		request.setdCard(card);


		Address address = new Address();


		address.setName("P.Kunde");
		address.setCity("random");
		address.setEmail("Monalisa.mishra@firstdata.com");
		address.setZip("04103");
		address.setCountry("Germany");

		Phone phone = new Phone();

		phone.setType("Cell");
		phone.setNumber("678-468-2665");
		address.setPhone(phone);
		address.setAddressLine1("LangeStr.12");
		address.setState("LEIPZIG");
		request.setBilling(address);

		//Soft Descriptors

		SoftDescriptor softdescriptor = new SoftDescriptor();
		softdescriptor.setDba_name("SoftDesc 1");
		softdescriptor.setStreet("123 Main Street");
		softdescriptor.setRegion("NY");
		softdescriptor.setMid("367337278884");
		softdescriptor.setMcc("8812");
		softdescriptor.setPostalCode("11375");
		softdescriptor.setCountryCode("USA");
		softdescriptor.setMerchantContactInfo("123 Main street");
		request.setDescriptor(softdescriptor);


		return request;
	}

	//GD-Secondary Transaction--void
	private void CallSDGermanDirectVoidRefund(TransactionTypePrimarySecondary transactionType) {
		try {
			Initialise();

			TransactionRequest trans = getSecondaryTransactionSD();
			if ((transactionType) .equals (TransactionTypePrimarySecondary.VOID))

				trans.setTransactionType(TransactionType.VOID.name().toLowerCase());
			else
				trans.setTransactionType(TransactionType.REFUND.name().toLowerCase());
			Object responseObject = clientHelper.doSecondaryTransactionGD(trans);

			statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}


	}

	private TransactionRequest getSecondaryTransactionSD() {
		TransactionRequest request = new TransactionRequest();

		request.setAmount(TransactionResponse.getAmount());

		request.setCurrency(TransactionResponse.getCurrency());

		request.setPaymentMethod(TransactionResponse.getMethod());


		request.setTransactionTag(TransactionResponse.getTransactionTag());
		return request;


	}


//L2L3

	private void CallL2L3GermanDirectPurchaseCredit(TransactionTypePrimarySecondary transactionType) {
		try {
			Initialise();
			TransactionRequest trans = getPrimaryTransactionL2L3();
			if ((transactionType) .equals (TransactionTypePrimarySecondary.PURCHASE))
				trans.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());
			else
				trans.setTransactionType(TransactionType.CREDIT.name().toLowerCase());

			Object responseObject = clientHelper.doPrimaryTransactionGD(trans);
			statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}

	//GD
	private TransactionRequest getPrimaryTransactionL2L3() {
		TransactionRequest request = new TransactionRequest();

		request.setAmount("1000");

		request.setCurrency("EUR");

		request.setPaymentMethod("direct_debit");

		Card card = new Card();

		card.setiban("DE34500100600032121604");
		card.setmandate_ref("ABCD1234");
		card.setbic("PBNKDEFFXXX");

		request.setdCard(card);

		//Set L2 and L3 objects
		Level2 lvl2 = new Level2();
		lvl2.setTax1_amount("10");
		lvl2.setTax2_number("2");
		lvl2.setTax2_amount("5");
		lvl2.setTax2_number("3");
		lvl2.setCustomer_ref("customer1");

		request.setLevel2(lvl2);

		Level3 lvl3 = new Level3();
		lvl3.setAlt_tax_amount("10");
		lvl3.setAlt_tax_id("098841111");
		lvl3.setDiscount_amount("1");
		lvl3.setDuty_amount("0.5");
		lvl3.setFreight_amount("5");
		lvl3.setShip_from_zip("11235");

		Ship_to_address shiptoaddr = new Ship_to_address();
		shiptoaddr.setCity("New York");
		shiptoaddr.setState("NY");
		shiptoaddr.setZip("11235");
		shiptoaddr.setCountry("USA");
		shiptoaddr.setEmail("abc@firstdata.com");
		shiptoaddr.setName("Bob Smith");
		shiptoaddr.setPhone("212-515-1111");
		shiptoaddr.setAddress_1("123 Main Street");
		shiptoaddr.setCustomer_number("12345");

		lvl3.setShip_to_address(shiptoaddr);

		Line_item[] lineitem = new Line_item[1];
		lineitem[0] = new Line_item();
		lineitem[0].setDescription("item 1");
		lineitem[0].setQuantity("5");
		lineitem[0].setCommodity_code("C");
		lineitem[0].setDiscount_amount("1");
		lineitem[0].setDiscount_indicator("G");
		lineitem[0].setGross_net_indicator("P");
		lineitem[0].setLine_item_total("10");
		lineitem[0].setProduct_code("F");
		lineitem[0].setTax_amount("5");
		lineitem[0].setTax_rate("0.2800000000000000266453525910037569701671600341796875");
		lineitem[0].setTax_type("Federal");
		lineitem[0].setUnit_cost("1");
		lineitem[0].setUnit_of_measure("meters");

		lvl3.setLineitems(lineitem);

		request.setLevel3(lvl3);


		return request;
	}

	//GD-Secondary Transaction--void
	private void CallL2L3GermanDirectVoidRefund(TransactionTypePrimarySecondary transactionType) {
		try {
			Initialise();

			TransactionRequest trans = getSecondaryTransactionL2L3();
			if ((transactionType) .equals (TransactionTypePrimarySecondary.VOID))

				trans.setTransactionType(TransactionType.VOID.name().toLowerCase());
			else
				trans.setTransactionType(TransactionType.REFUND.name().toLowerCase());
			Object responseObject = clientHelper.doSecondaryTransactionGD(trans);
			statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

	private TransactionRequest getSecondaryTransactionL2L3() {
		TransactionRequest request = new TransactionRequest();

		request.setAmount(TransactionResponse.getAmount());

		request.setCurrency(TransactionResponse.getCurrency());

		request.setPaymentMethod(TransactionResponse.getMethod());


		request.setTransactionTag(TransactionResponse.getTransactionTag());
		return request;


	}

	//L2L3
}