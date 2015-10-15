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
			CallAVSGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE,uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "avs-purchasevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdavspurchaserefund")) {
			CallAVSGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE,uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "avs-purchaserefund";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdavscreditvoid")) {
			CallAVSGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.CREDIT,uri);
			CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "avs-creditvoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdsdpurchasevoid")) {
			CallSDGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE,uri);
			CallSDGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "softdescriptor-purchasevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdsdpurchaserefund")) {
			CallSDGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE,uri);
			CallSDGermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "softdescriptor-purchaserefund";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdsdcreditvoid")) {
			CallSDGermanDirectPurchaseCredit(TransactionTypePrimarySecondary.CREDIT,uri);
			CallSDGermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "softdescriptor-creditvoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdl2l3purchasevoid")) {
			CallL2L3GermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE,uri);
			CallL2L3GermanDirectVoidRefund(TransactionTypePrimarySecondary.VOID);

			return "L2L3--purchasevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdl2l3purchaserefund")) {
			CallL2L3GermanDirectPurchaseCredit(TransactionTypePrimarySecondary.PURCHASE,uri);
			CallL2L3GermanDirectVoidRefund(TransactionTypePrimarySecondary.REFUND);

			return "L2L3-purchaserefund";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("gdl2l3creditvoid")) {
			CallL2L3GermanDirectPurchaseCredit(TransactionTypePrimarySecondary.CREDIT,uri);
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
	private void CallAVSGermanDirectPurchaseCredit(TransactionTypePrimarySecondary transactionType, String[] uri) {
		try {
			Initialise();
			TransactionRequest trans = getPrimaryTransactionGD(uri);
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
	private TransactionRequest getPrimaryTransactionGD(String[] uri) {
		TransactionRequest request = new TransactionRequest();

		request.setPaymentMethod(uri[1]);
		request.setTransactionType(uri[2]);

		request.setAmount(uri[3]);

		request.setCurrency(uri[4]);

		//request.setPaymentMethod("debit_card");
		Card card = new Card();

		card.setiban(uri[5]);
		card.setmandate_ref(uri[6]);
		card.setbic(uri[7]);

		request.setdCard(card);


		Address address = new Address();


		address.setName(uri[8]);
		address.setCity(uri[9]);
		address.setCountry(uri[10]);
		address.setEmail(uri[11]);

		address.setZip(uri[16]);


		Phone phone = new Phone();

		phone.setType(uri[12]);
		phone.setNumber(uri[13]);
		address.setPhone(phone);
		address.setAddressLine1(uri[14]);
		address.setState(uri[15]);
		request.setBilling(address);

		return request;
	}

	//GD-Secondary Transaction--void
	private void CallAVSGermanDirectVoidRefund(TransactionTypePrimarySecondary transactionType) {
		try {
			Initialise();

			TransactionRequest trans = getSecondaryTransactionGD();
			if ((transactionType) .equals (TransactionTypePrimarySecondary.VOID))

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


	private void CallSDGermanDirectPurchaseCredit(TransactionTypePrimarySecondary transactionType,String[] uri) {
		try {
			Initialise();
			TransactionRequest trans = getPrimaryTransactionSD(uri);
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
	private TransactionRequest getPrimaryTransactionSD(String[] uri) {
		TransactionRequest request = new TransactionRequest();

		request.setPaymentMethod(uri[1]);
		request.setTransactionType(uri[2]);

		request.setAmount(uri[3]);

		request.setCurrency(uri[4]);

		//request.setPaymentMethod("debit_card");
		Card card = new Card();

		card.setiban(uri[5]);
		card.setmandate_ref(uri[6]);
		card.setbic(uri[7]);

		request.setdCard(card);

		//Soft Descriptors

		SoftDescriptor softdescriptor = new SoftDescriptor();
		softdescriptor.setDba_name(uri[8]);
		softdescriptor.setStreet(uri[9]);
		softdescriptor.setRegion(uri[10]);
		softdescriptor.setMid(uri[11]);
		softdescriptor.setMcc(uri[12]);
		softdescriptor.setPostalCode(uri[13]);
		softdescriptor.setCountryCode(uri[14]);
		softdescriptor.setMerchantContactInfo(uri[15]);
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

	private void CallL2L3GermanDirectPurchaseCredit(TransactionTypePrimarySecondary transactionType,String[] uri) {
		try {
			Initialise();
			TransactionRequest trans = getPrimaryTransactionL2L3(uri);
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
	private TransactionRequest getPrimaryTransactionL2L3(String[] uri) {
		TransactionRequest request = new TransactionRequest();

		request.setPaymentMethod(uri[1]);
		request.setTransactionType(uri[2]);
		request.setAmount(uri[3]);

		request.setCurrency(uri[4]);



		Card card = new Card();

		card.setiban(uri[5]);
		card.setmandate_ref(uri[6]);
		card.setbic(uri[7]);

		request.setdCard(card);

		//Set L2 and L3 objects
		Level2 lvl2 = new Level2();
		lvl2.setTax1_amount(uri[8]);
		lvl2.setTax1_number(uri[9]);
		lvl2.setTax2_amount(uri[10]);
		lvl2.setTax2_number(uri[11]);
		lvl2.setCustomer_ref(uri[12]);

		request.setLevel2(lvl2);

		Level3 lvl3 = new Level3();
		lvl3.setAlt_tax_amount(uri[13]);
		lvl3.setAlt_tax_id(uri[14]);
		lvl3.setDiscount_amount(uri[15]);
		lvl3.setDuty_amount(uri[16]);
		lvl3.setFreight_amount(uri[17]);
		lvl3.setShip_from_zip(uri[18]);

		Ship_to_address shiptoaddr = new Ship_to_address();
		shiptoaddr.setCity(uri[19]);
		shiptoaddr.setState(uri[20]);
		shiptoaddr.setZip(uri[21]);
		shiptoaddr.setCountry(uri[22]);
		shiptoaddr.setEmail(uri[23]);
		shiptoaddr.setName(uri[24]);
		shiptoaddr.setPhone(uri[25]);
		shiptoaddr.setAddress_1(uri[26]);
		shiptoaddr.setCustomer_number(uri[27]);

		lvl3.setShip_to_address(shiptoaddr);

		Line_item[] lineitem = new Line_item[1];
		lineitem[0] = new Line_item();
		lineitem[0].setDescription(uri[28]);
		lineitem[0].setQuantity(uri[29]);
		lineitem[0].setCommodity_code(uri[30]);
		lineitem[0].setDiscount_amount(uri[31]);
		lineitem[0].setDiscount_indicator(uri[32]);
		lineitem[0].setGross_net_indicator(uri[33]);
		lineitem[0].setLine_item_total(uri[34]);
		lineitem[0].setProduct_code(uri[35]);
		lineitem[0].setTax_amount(uri[36]);
		lineitem[0].setTax_rate(uri[37]);
		lineitem[0].setTax_type(uri[38]);
		lineitem[0].setUnit_cost(uri[39]);
		lineitem[0].setUnit_of_measure(uri[40]);

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