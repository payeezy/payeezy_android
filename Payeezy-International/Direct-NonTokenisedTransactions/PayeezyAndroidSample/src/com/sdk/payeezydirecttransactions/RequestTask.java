package com.sdk.payeezydirecttransactions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.sample.payeezydirecttransactions.MainActivity;
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
import com.firstdata.firstapi.client.domain.v2.Telecheck;
import com.firstdata.firstapi.client.domain.v2.ThreeDomainSecureToken;
import com.firstdata.firstapi.client.domain.v2.Token;
import com.firstdata.firstapi.client.domain.v2.Token_data;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;
import com.firstdata.firstapi.client.domain.v2.Transarmor;
import com.firstdata.firstapi.client.domain.v2.UserTransactionResponse;
import com.firstdata.firstapi.client.domain.v2.ValueLinkCard;

import java.util.Random;
import java.util.UUID;

enum CardType {
	CARD_VISA ,
	CARD_MASTERCARD ,
	CARD_AMEX ,
	CARD_DISCOVER ,
	CARD_VALUELINK, 
	CARD_TELECHECK,

}

enum TransactionCategory
{

	CATEGORY_CVV2,
	CATEGORY_AVS,
	CATEGORY_SPLITSHIPMENT,
	CATEGORY_LEVEL2,
	CATEGORY_LEVEL3,
	CATEGORY_SOFTDESCRIPTORS,
	CATEGORY_NAKEDREFUNDS,
	CATEGORY_NAKEDVOIDS,
	CATEGORY_3DS,
	CATEGORY_TRANSARMOR,
	CATEGORY_ZERODOLLAR,
	CATEGORY_RECURRING,
	CATEGORY_GENERATETOKEN,
	CATEGORY_FDTOKEN
}

enum TransactionTypePrimarySecondary
{
	AUTHORIZE,
	PURCHASE,
	CREDIT,
	VOID,
	REFUND,
	CAPTURE

};


@SuppressLint("DefaultLocale")
public class RequestTask extends AsyncTask<String, String, String> {

	private Context context = null;


	CardType cardtype;

	TransactionCategory category;

	FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
	Random rand = new Random();

	public RequestTask(Context pcontext) {
		context = pcontext;

	}

	private String statusString = "";
	private String splitter = "~~~~~~~~";

	@Override
	protected String doInBackground(String... uri) {

		//AVS Transactions
		if (uri[0].toLowerCase().equalsIgnoreCase("avsauthorizevoid")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_AVS,TransactionTypePrimarySecondary.AUTHORIZE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_AVS,TransactionTypePrimarySecondary.VOID);

			return "avsauthorizevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("avspurchasevoid")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_AVS,TransactionTypePrimarySecondary.PURCHASE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_AVS,TransactionTypePrimarySecondary.VOID);

			return "avspurchasevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("avsauthcapture")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_AVS,TransactionTypePrimarySecondary.AUTHORIZE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_AVS,TransactionTypePrimarySecondary.CAPTURE);

			return "avsauthcapture";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("avspurchaserefund")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_AVS,TransactionTypePrimarySecondary.PURCHASE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_AVS,TransactionTypePrimarySecondary.REFUND);

			return "avspurchaserefund";
		}
		//3DS Transactions
		if (uri[0].toLowerCase().equalsIgnoreCase("3dsauthorizevoid")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_3DS,TransactionTypePrimarySecondary.AUTHORIZE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_3DS,TransactionTypePrimarySecondary.VOID);

			return "3dsauthorizevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("3dspurchasevoid")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_3DS,TransactionTypePrimarySecondary.PURCHASE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_3DS,TransactionTypePrimarySecondary.VOID);

			return "3dspurchasevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("3dsauthcapture")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_3DS,TransactionTypePrimarySecondary.AUTHORIZE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_3DS,TransactionTypePrimarySecondary.CAPTURE);

			return "3dsauthcapture";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("3dspurchaserefund")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_3DS,TransactionTypePrimarySecondary.PURCHASE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_3DS,TransactionTypePrimarySecondary.REFUND);

			return "3dspurchaserefund";
		}
//SD Transactions

		if (uri[0].toLowerCase().equalsIgnoreCase("sdauthorizevoid")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.AUTHORIZE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.VOID);

			return "sdauthorizevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("sdpurchasevoid")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.PURCHASE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.VOID);

			return "sdpurchasevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("sdauthcapture")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.AUTHORIZE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.CAPTURE);

			return "sdauthcapture";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("sdpurchaserefund")) {

			AvsSdL2L3Ta3dszd(uri,TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.PURCHASE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.REFUND);

			return "sdpurchaserefund";
		}

		//CVV Transactions

		if (uri[0].toLowerCase().equalsIgnoreCase("cvvauthorizevoid")) {
			AuthorizePurchase(uri,TransactionTypePrimarySecondary.AUTHORIZE);
		//	AvsSdL2L3Ta3dszd(TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.AUTHORIZE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_CVV2,TransactionTypePrimarySecondary.VOID);

			return "cvvauthorizevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("cvvpurchasevoid")) {
			AuthorizePurchase(uri,TransactionTypePrimarySecondary.PURCHASE);
		//	AvsSdL2L3Ta3dszd(TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.PURCHASE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_CVV2,TransactionTypePrimarySecondary.VOID);

			return "cvvpurchasevoid";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("cvvauthcapture")) {
			AuthorizePurchase(uri,TransactionTypePrimarySecondary.AUTHORIZE);
		//	AvsSdL2L3Ta3dszd(TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.AUTHORIZE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_CVV2,TransactionTypePrimarySecondary.CAPTURE);

			return "cvvauthcapture";
		}
		if (uri[0].toLowerCase().equalsIgnoreCase("cvvpurchaserefund")) {
			AuthorizePurchase(uri,TransactionTypePrimarySecondary.PURCHASE);
		//	AvsSdL2L3Ta3dszd(TransactionCategory.CATEGORY_SOFTDESCRIPTORS,TransactionTypePrimarySecondary.PURCHASE);
			PayloadFormSecondaryTransaction(TransactionCategory.CATEGORY_CVV2,TransactionTypePrimarySecondary.REFUND);

			return "cvvpurchaserefund";
		}

		return "Authorize";

	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		System.out.println("Button Authorize Clicked" + result);
		MainActivity.displayResult.setText(result + statusString);


	}


	//Initialise method: for initialising the CardType, appId,SecretKey, token and Url
	private void Initialise() {
		cardtype = CardType.CARD_VISA;
		clientHelper.setAppId(TransactionDataProvider.appIdCert);
		clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCert);
		clientHelper.setToken(TransactionDataProvider.tokenCert);
		clientHelper.setUrl(TransactionDataProvider.urlCert);
	}

	private void AuthorizePurchase(String[] uri,TransactionTypePrimarySecondary transactionType) {
		try {

			Initialise();
			TransactionRequest trans = getPrimaryTransaction(uri);
			if (transactionType == TransactionTypePrimarySecondary.PURCHASE)
				trans.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());

			else
				trans.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());

			Object responseObject = clientHelper.doPrimaryTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			String resString = ((UserTransactionResponse) responseObject).getResponseString();
			stringParser(resString);
			statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;


		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}


//String Parser
public void stringParser(String resString)
{

	int startIndex = resString.indexOf("transaction_tag");
	startIndex=resString.indexOf("=",startIndex+1);
	int endIndex = resString.indexOf(",", startIndex);
	String transaction_tid = resString.substring(startIndex, endIndex);
	transaction_tid=transaction_tid.replace(" ", "");
	transaction_tid=transaction_tid.replace(":", "");
	transaction_tid=transaction_tid.replace("=", "");
	TransactionResponse.transactionTag=transaction_tid;

	startIndex=resString.indexOf("method");
	startIndex=resString.indexOf("=", startIndex + 1);
	endIndex=resString.indexOf(",", startIndex);
	String method = resString.substring(startIndex, endIndex);
	method=method.replace(" ","");
	method=method.replace(":","");
	method=method.replace("=", "");
	TransactionResponse.method=method;

	startIndex=resString.indexOf("amount");
	startIndex=resString.indexOf("=", startIndex + 1);
	endIndex=resString.indexOf(",", startIndex);
	String amount = resString.substring(startIndex, endIndex);
	amount=amount.replace(" ","");
	amount=amount.replace(":","");
	amount=amount.replace("=", "");
	TransactionResponse.amount=amount;
	startIndex=resString.indexOf("currency");
	startIndex=resString.indexOf("=",startIndex+1);
	endIndex=resString.indexOf(",", startIndex);
	String currency = resString.substring(startIndex, endIndex);
	currency=currency.replace(" ","");
	currency=currency.replace(":","");
	currency=currency.replace("=", "");
	TransactionResponse.currency=currency;


	startIndex=resString.indexOf("transaction_id");
	startIndex=resString.indexOf("=",startIndex+1);
	endIndex=resString.indexOf(",", startIndex);
	String transaction_id = resString.substring(startIndex, endIndex);
	transaction_id=transaction_id.replace(" ","");
	transaction_id=transaction_id.replace(":","");
	transaction_id=transaction_id.replace("=","");
	TransactionResponse.transactionId=transaction_id;


}
	private void AvsSdL2L3Ta3dszd(String[] uri,TransactionCategory transactionCategory,TransactionTypePrimarySecondary transactiontype)
	{
		try
		{
			Initialise();
			switch(transactionCategory)
			{
				case CATEGORY_AVS:
					category = TransactionCategory.CATEGORY_AVS;
					break;
				case CATEGORY_LEVEL2:
					category = TransactionCategory.CATEGORY_LEVEL2;
					break;
				case CATEGORY_LEVEL3:
					category = TransactionCategory.CATEGORY_LEVEL3;
					break;
				case CATEGORY_TRANSARMOR:
					category = TransactionCategory.CATEGORY_TRANSARMOR;
					break;
				case CATEGORY_SOFTDESCRIPTORS:
					category=TransactionCategory.CATEGORY_SOFTDESCRIPTORS;
					break;
				case CATEGORY_3DS:
					category=TransactionCategory.CATEGORY_3DS;
					break;

				case CATEGORY_ZERODOLLAR:
					TransactionRequest trans = getPrimaryTransaction(uri);
					Object responseObject = clientHelper.doPrimaryTransaction(trans);
					statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;
					break;

				default:
					break;
			}

			if( transactionCategory!=TransactionCategory.CATEGORY_ZERODOLLAR  ) {

				TransactionRequest trans = getPrimaryTransactionForTransType(uri);
				if(transactiontype.equals(TransactionTypePrimarySecondary.AUTHORIZE))
					trans.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());
				else
					trans.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());
				Object responseObject = clientHelper.doPrimaryTransaction(trans);
				String resString = ((UserTransactionResponse) responseObject).getResponseString();
				stringParser(resString);
				statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;
			}
		}catch(Exception e)
		{

			System.out.println(e.getMessage());
		}
	}

	//Secondary transaction payload formation
	public void PayloadFormSecondaryTransaction(TransactionCategory transactionCategory,TransactionTypePrimarySecondary transactiontype)
	{
		Initialise();
		System.out.println("In secondary\n");
		try{


		TransactionRequest trans=new TransactionRequest();

		if (transactiontype.equals( TransactionTypePrimarySecondary.CAPTURE))
			trans.setTransactionType(TransactionType.CAPTURE.name());


		else if (transactiontype.equals( TransactionTypePrimarySecondary.REFUND))
			trans.setTransactionType(TransactionType.REFUND.name());

		else
			trans.setTransactionType(TransactionType.VOID.name());
System.out.println("method="+TransactionResponse.getMethod());
			System.out.println("amount="+TransactionResponse.getAmount());
			System.out.println("currency="+TransactionResponse.getCurrency());
			System.out.println("Tr-tag="+TransactionResponse.getTransactionTag());
			System.out.println("mer-ref" + TransactionDataProvider.getmerchantref());
			if(transactionCategory.equals(TransactionCategory.CATEGORY_3DS))
				trans.setPaymentMethod("credit_card");
			else
		trans.setPaymentMethod(TransactionResponse.getMethod());
		trans.setAmount(TransactionResponse.getAmount());
		trans.setCurrency(TransactionResponse.getCurrency());
		trans.setTransactionTag(TransactionResponse.getTransactionTag());
		trans.setReferenceNo(TransactionDataProvider.getmerchantref());

			Object responseObject2 = clientHelper.doSecondaryTransaction(trans);
			statusString = statusString + ((UserTransactionResponse) responseObject2).getResponseString() + splitter;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}


	}

	private TransactionRequest getPrimaryTransaction(String[] uri) {
        TransactionRequest request=new TransactionRequest();

		request.setAmount(uri[1]);
        
        request.setCurrency(uri[3]);
        
        request.setPaymentMethod(uri[2]);
		randomnumbergeneration();

		request.setReferenceNo(TransactionDataProvider.merchantref);

        request.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());
        Card card=new Card();
        card.setCvv(uri[8]);
        card.setExpiryDt(uri[7]);
        card.setName(uri[5]);
        card.setType(uri[4]);
        card.setNumber(uri[6]);

        request.setCard(card);

        return request;
    }
	public void randomnumbergeneration()
	{
		UUID uuid=UUID.randomUUID();
		int newrandom=rand.nextInt( Integer.MAX_VALUE ) + 1;

		TransactionDataProvider.merchantref=null;
		//TransactionDataProvider.merchantref=Integer.toString(newrandom);
		TransactionDataProvider.merchantref=uuid.toString();

	}
	public TransactionRequest getPrimaryTransactionForTransType(String[] uri) {
		TransactionRequest request = new TransactionRequest();


		request.setAmount(uri[1]);
		request.setCurrency(uri[3]);

		request.setPaymentMethod(uri[2]);

		randomnumbergeneration();

		request.setReferenceNo(TransactionDataProvider.merchantref);
		request.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());
		Card card = new Card();

		card.setExpiryDt(uri[7]);
		card.setName(uri[5]);
		card.setType(uri[4]);

		card.setNumber(uri[6]);
		request.setCard(card);

		Address address = new Address();
		request.setBilling(address);
		address.setState(uri[14]);
		address.setCity(uri[9]);
		address.setAddressLine1(uri[13]);
		address.setZip(uri[15]);
		address.setCountry(uri[10]);
		address.setEmail(uri[16]);

		// category specific
		if (category == TransactionCategory.CATEGORY_AVS) {

			Phone phone = new Phone();
			phone.setNumber(uri[12]);
			phone.setType(uri[11]);
			address.setPhone(phone);
			request.setBilling(address);
		}
		// category specific
		if (category == TransactionCategory.CATEGORY_3DS)
		{
			request.setPaymentMethod("3DS");

			ThreeDomainSecureToken threeDST = new ThreeDomainSecureToken();
			threeDST.setCvv(uri[8]);
			threeDST.setCardholder_name(uri[5]);
			threeDST.setCard_number(uri[6]);
			threeDST.setType(uri[4]);
			threeDST.setExp_date(uri[7]);
			threeDST.setCavv(uri[14]);

			request.setThreeDomainSecureToken(threeDST);

			request.setCard(null);


		}
		if(category == TransactionCategory.CATEGORY_SOFTDESCRIPTORS)
		{
			SoftDescriptor descriptor = new SoftDescriptor();
			descriptor.setCountryCode(uri[15]);
			descriptor.setDba_name(uri[9]);
			descriptor.setMcc(uri[13]);
			descriptor.setMerchantContactInfo(uri[16]);
			descriptor.setMid(uri[12]);
			descriptor.setPostalCode(uri[14]);
			descriptor.setRegion(uri[11]);
			descriptor.setStreet(uri[10]);
			request.setDescriptor(descriptor);
		}


		return request;
	}

}