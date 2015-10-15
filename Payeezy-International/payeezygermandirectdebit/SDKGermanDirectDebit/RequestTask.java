package com.sdk.germandirectdebit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.sample.germandirectdebit.MainActivity;
import com.firstdata.firstapi.client.FirstAPIClientV2Helper;
import com.firstdata.firstapi.client.domain.TransactionType;
import com.firstdata.firstapi.client.domain.v2.Address;
import com.firstdata.firstapi.client.domain.v2.Address.Phone;
import com.firstdata.firstapi.client.domain.v2.Card;
import com.firstdata.firstapi.client.domain.v2.Level2;
import com.firstdata.firstapi.client.domain.v2.Level3;
import com.firstdata.firstapi.client.domain.v2.Line_item;
import com.firstdata.firstapi.client.domain.v2.Ship_to_address;
import com.firstdata.firstapi.client.domain.v2.SoftDescriptor;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;
import com.firstdata.firstapi.client.domain.v2.UserTransactionResponse;




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
enum TransactionTypePrimarySecondary
{
	PURCHASE,
	CREDIT,
	VOID,
	REFUND

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
		MainActivity.displayResult.setText(result+statusString);

}
	//Initialise method: for initialising the CardType, appId,SecretKey, token and Url
	private void Initialise() {
		cardtype = CardType.CARD_DIRECTDEBIT;
		clientHelper.setAppId(TransactionDataProvider.appIdGD);
		clientHelper.setSecuredSecret(TransactionDataProvider.secIDGD);
		clientHelper.setToken(TransactionDataProvider.tokenGD);
		clientHelper.setUrl(TransactionDataProvider.urlGD);
	}


	//German Direct-AVS
	private void CallAVSGermanDirectPurchaseCredit(TransactionTypePrimarySecondary transactionType) {
		try {
			Initialise();
			TransactionRequest trans = getPrimaryTransactionGD();
			if (transactionType == TransactionTypePrimarySecondary.PURCHASE)
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
			Initialise();

			TransactionRequest trans = getSecondaryTransactionGD();
			if (transactionType == TransactionTypePrimarySecondary.VOID)

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
			if (transactionType == TransactionTypePrimarySecondary.PURCHASE)
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
			if (transactionType == TransactionTypePrimarySecondary.VOID)

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
			if (transactionType == TransactionTypePrimarySecondary.PURCHASE)
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
			if (transactionType == TransactionTypePrimarySecondary.VOID)

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