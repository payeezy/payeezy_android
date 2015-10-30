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
public class RequestTask extends AsyncTask<String, String, String>{

	private Context context = null;

	CardType cardtype ;
	CardType cardtypeSecondary = CardType.CARD_VISA;
	TransactionCategory category;

	FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();

	public RequestTask(Context pcontext)
	{
		context = pcontext;

	}
	private String statusString = "";
	private String splitter = "~~~~~~~~";
	
    @Override
    protected String doInBackground(String... uri) {
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorize"))
    	{
			AuthorizePurchase(TransactionTypePrimarySecondary.AUTHORIZE,uri);
    		return "authorize";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase( "purchase"))
    	{
			AuthorizePurchase(TransactionTypePrimarySecondary.PURCHASE,uri);
    		return "purchase";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase(  "capture"))
    	{
			AuthorizePurchase(TransactionTypePrimarySecondary.AUTHORIZE,uri);
			CaptureRefundVoid(TransactionTypePrimarySecondary.CAPTURE);
    		return "capture";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase( "refund")) {
			AuthorizePurchase(TransactionTypePrimarySecondary.PURCHASE,uri);
			CaptureRefundVoid(TransactionTypePrimarySecondary.REFUND);
    		return "refund";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase( "void"))
    	{
			AuthorizePurchase(TransactionTypePrimarySecondary.AUTHORIZE,uri);
			CaptureRefundVoid(TransactionTypePrimarySecondary.VOID);
    		return "void";
    	}
    /*	if(uri[0].toLowerCase().equalsIgnoreCase( "valuelink"))
    	{

    		CallValueLinkCards();
    		return "valuelink";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase( "telecheck"))
    	{

    		CallTelecheckCards();
    		return "telecheck";
    	}
*/
		if(uri[0].toLowerCase().equalsIgnoreCase("authorizeavs"))
		{

			AvsSdL2L3Ta3dszd(TransactionCategory.CATEGORY_AVS,uri);
			return "authorizeavs";
		}


    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizesoftdescriptors"))
    	{

			AvsSdL2L3Ta3dszd(TransactionCategory.CATEGORY_SOFTDESCRIPTORS,uri);
    		return "authorizesoftdescriptors";
    	}

		if(uri[0].toLowerCase().equalsIgnoreCase("authorizesplitshipments"))
		{

			SplitShipments(uri);
			return "authorizesplitshipments";
		}

    	if(uri[0].toLowerCase().equalsIgnoreCase("authorize3ds"))
    	{

			AvsSdL2L3Ta3dszd(TransactionCategory.CATEGORY_3DS,uri);
    		return "authorize3ds";
    	}

    	if(uri[0].toLowerCase().equalsIgnoreCase("recurring"))
    	{

    		Recurring();

    		return "authorizerecurring";
    	}

		if(uri[0].toLowerCase().equalsIgnoreCase("authorizelevel2"))
		{

			AvsSdL2L3Ta3dszd(TransactionCategory.CATEGORY_LEVEL2,uri);
			return "authorizelevel2";
		}

		if(uri[0].toLowerCase().equalsIgnoreCase("authorizelevel3"))
		{

			AvsSdL2L3Ta3dszd(TransactionCategory.CATEGORY_LEVEL3,uri);
			return "authorizelevel3";
		}


    	if(uri[0].toLowerCase().equalsIgnoreCase("zerodollar"))
    	{
			AvsSdL2L3Ta3dszd(TransactionCategory.CATEGORY_ZERODOLLAR,uri);
    		return "zerodollar";
    	}


    	return "Authorize";

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        System.out.println("Button Authorize Clicked" + result);
		MainActivity.displayResult.setText(result + statusString);


    }
    

    private void CallTelecheckCards()
    {
   // 	CallPurchaseTelecheck();

    }
    
    private void CallValueLinkCards()
    {
    /*	CallActivationValueLink();
    	CallPurchaseValueLink();
    	CallRefundValueLink();
    	CallSplitPurchaseValueLink();
    	CallVoidValueLink();
    	CallReloadValueLink();
    	CallCashoutValueLink();
    	CallDeactivationValueLink();
    	CallBalanceEnquiryValueLink();
    */
    }

	//Initialise method: for initialising the CardType, appId,SecretKey, token and Url
	private void Initialise() {
		cardtype = CardType.CARD_VISA ;
		clientHelper.setAppId(TransactionDataProvider.appIdCert);
		clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCert);
		clientHelper.setToken(TransactionDataProvider.tokenCert);
		clientHelper.setUrl(TransactionDataProvider.urlCert);
	}

    private void AuthorizePurchase(TransactionTypePrimarySecondary transactionType,String[] uri)
	{
		try
		{

			Initialise();
			TransactionRequest trans = getPrimaryTransaction(uri);
			if(transactionType == TransactionTypePrimarySecondary.PURCHASE)
			trans.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());

			else
			trans.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());

			Object responseObject = clientHelper.doPrimaryTransaction(trans);
			System.out.println("Response : " + responseObject.toString());

			String resString = ((UserTransactionResponse) responseObject).getResponseString();
			stringParser(resString);

			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;

		}catch(Exception e)
		{

			System.out.println(e.getMessage());
		}
	}




	/*********************************capture   REFUND   Void****************************/
	//trying
	//Secondary transaction payload formation
	public void CaptureRefundVoid(TransactionTypePrimarySecondary transactiontype)
	{
		Initialise();
		System.out.println("In secondary\n");
		try{


			TransactionRequest trans=new TransactionRequest();


			if (transactiontype.equals( TransactionTypePrimarySecondary.CAPTURE))
				trans.setTransactionType(TransactionType.CAPTURE.name().toLowerCase());


			else if (transactiontype.equals( TransactionTypePrimarySecondary.REFUND))
				trans.setTransactionType(TransactionType.REFUND.name().toLowerCase());

			else
				trans.setTransactionType(TransactionType.VOID.name().toLowerCase());
			System.out.println("method="+TransactionResponse.getMethod());
			System.out.println("amount="+TransactionResponse.getAmount());
			System.out.println("currency="+TransactionResponse.getCurrency());
			System.out.println("Tr-tag="+TransactionResponse.getTransactionTag());

		trans.setPaymentMethod(TransactionResponse.getMethod());
			trans.setAmount(TransactionResponse.getAmount());
			trans.setCurrency(TransactionResponse.getCurrency());
			trans.setTransactionTag(TransactionResponse.getTransactionTag());
trans.setTransactionId(TransactionResponse.getTransactionId());

			Object responseObject2 = clientHelper.doSecondaryTransaction(trans);
			statusString = statusString + ((UserTransactionResponse) responseObject2).getResponseString() + splitter;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}


	}

	//end trying
	/*private void CaptureRefundVoid(TransactionTypePrimarySecondary transactionType,String[] uri)
	{
		try {
			Initialise();
			//	TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
//trying


			//trying
			TransactionRequest transReq = getPrimaryTransaction(uri);
			if (transactionType == TransactionTypePrimarySecondary.CAPTURE || transactionType == TransactionTypePrimarySecondary.VOID)
				transReq.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());

			else
				transReq.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());


			TransactionResponse responseObject = clientHelper.doPrimaryTransaction(transReq);
			String resString = ((UserTransactionResponse) responseObject).getResponseString();
			stringParser(resString);
			statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;
			System.out.println("In secondary\n");



			TransactionRequest trans=new TransactionRequest();

			if (transactionType.equals( TransactionTypePrimarySecondary.CAPTURE))
				trans.setTransactionType(TransactionType.CAPTURE.name());


			else if (transactionType.equals( TransactionTypePrimarySecondary.REFUND))
				trans.setTransactionType(TransactionType.REFUND.name());

			else
				trans.setTransactionType(TransactionType.VOID.name());

			System.out.println("method="+TransactionResponse.getMethod());
			System.out.println("amount="+TransactionResponse.getAmount());
			System.out.println("currency="+TransactionResponse.getCurrency());
			System.out.println("Tr-tag="+TransactionResponse.getTransactionTag());


			trans.setPaymentMethod(TransactionResponse.getMethod());
			trans.setAmount(TransactionResponse.getAmount());
			trans.setCurrency(TransactionResponse.getCurrency());
			trans.setTransactionTag(TransactionResponse.getTransactionTag());


			Object responseObject2 = clientHelper.doSecondaryTransaction(trans);
			statusString = statusString + ((UserTransactionResponse) responseObject2).getResponseString() + splitter;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
*/

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

	private void AvsSdL2L3Ta3dszd(TransactionCategory transactionCategory,String[] uri)
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
				Object responseObject = clientHelper.doPrimaryTransaction(trans);
				statusString = statusString + ((UserTransactionResponse) responseObject).getResponseString() + splitter;
			}
		}catch(Exception e)
		{

			System.out.println(e.getMessage());
		}
	}
	
	private void SplitShipments(String[] uri)
	{
		try
		{
			Initialise();
			category = TransactionCategory.CATEGORY_SPLITSHIPMENT;
			TransactionRequest trans = getPrimaryTransactionForTransType(uri);
			
			Object responseObject = clientHelper.doPrimaryTransaction(trans);

			int startIndex = ((UserTransactionResponse)responseObject).getResponseString().indexOf("transaction_tag");
			startIndex = ((UserTransactionResponse)responseObject).getResponseString().indexOf("=", startIndex + 1);
			int endIndex = ((UserTransactionResponse)responseObject).getResponseString().indexOf(",", startIndex);


			String transaction_tag = ((UserTransactionResponse)responseObject).getResponseString().substring(startIndex, endIndex);
			transaction_tag = transaction_tag.replace(" ", "");
			transaction_tag = transaction_tag.replace(":", "");
			transaction_tag = transaction_tag.replace("=", "");
			
			startIndex = ((UserTransactionResponse)responseObject).getResponseString().indexOf("transaction_id");
			startIndex = ((UserTransactionResponse)responseObject).getResponseString().indexOf("=", startIndex + 1);
			endIndex = ((UserTransactionResponse)responseObject).getResponseString().indexOf(",", startIndex);
			String transaction_id = ((UserTransactionResponse)responseObject).getResponseString().substring(startIndex, endIndex);
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");

			String splitShipment = trans.getSplitShipment();
			String[] ss = splitShipment.split("/");
			int count = Integer.parseInt(ss[0]);
			int total = Integer.parseInt(ss[1]);
			for(int i=count; i<=total;i++)
			{

				TransactionRequest trans2 = getSecondaryTransactionForTransType();
				String split = String.valueOf(i) + "/" + String.valueOf(total);
				trans2.setSplitShipment(split);

				trans2.setTransactionId(null);
				trans2.setTransactionTag(transaction_tag);
				trans2.setTransactionId(transaction_id);
			//	trans2.setId(transaction_id);
				if(i==1)
				{
					trans2.setAmount("0009");
				}
				if(i==2)
				{
					trans2.setAmount("0021");
				}
				if(i==3)
				{
					trans2.setAmount("0109");
				}
				Object responseObject2 = clientHelper.SplitTransaction(trans2);

				statusString = statusString + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;

				//check if last of the split
				if(((UserTransactionResponse)responseObject2).getResponseString().toLowerCase().contains("split_shipment"))
				{
					int start = ((UserTransactionResponse)responseObject2).getResponseString().indexOf("split_shipment");
					start = ((UserTransactionResponse)responseObject2).getResponseString().indexOf("=", start + 1);
					int end = ((UserTransactionResponse)responseObject2).getResponseString().indexOf(",", start);
					System.out.println("start : " + Integer.toString(start) + "end : " + Integer.toString(end) );  
					String splitShip = ((UserTransactionResponse)responseObject2).getResponseString().substring(start, end);
					splitShip = splitShip.replace(" ", "");
					splitShip = splitShip.replace(":", "");
					splitShip = splitShip.replace("=", "");
					
					String[] completedSplits = splitShip.split("/");

					int totalComplete = Integer.parseInt(completedSplits[1]);


					if(totalComplete >= i)
					{
						break;
					}
				}
			}
			
		}catch(Exception e)
		{

			System.out.println(e.getMessage());
		}
	}

	private void Recurring()
	{
		try
		{
			Initialise();
			category = TransactionCategory.CATEGORY_RECURRING;
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			transReq.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());

			TransactionResponse responseObject1 = clientHelper.doPrimaryTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex + 1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex + 1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getPrimaryTransactionForSecondaryModified();
			transReq.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());
			Object responseObject2 = clientHelper.doPrimaryTransaction(trans);

			statusString = statusString + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;
		}catch(Exception e)
		{

			System.out.println(e.getMessage());
		}
	}
	private TransactionRequest getPrimaryTransaction(String[] uri) {
        TransactionRequest request=new TransactionRequest();

        request.setAmount(uri[1]);
        
        request.setCurrency(uri[2]);
        
        request.setPaymentMethod(uri[3]);

		request.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());
        Card card=new Card();
		card.setCvv(uri[4]);
        card.setExpiryDt(uri[5]);
		card.setName(uri[6]);
		card.setType(uri[7]);
		card.setNumber(uri[8]);

        request.setCard(card);
    /*    Address address=new Address();
        request.setBilling(address);
        address.setState(uri[9]);
        address.setAddressLine1(uri[10]);
        address.setZip(uri[11]);
        address.setCountry(uri[12]);
		address.setCity(uri[13]);*/
        /*if(cardtype == CardType.CARD_TELECHECK)
        {
        	Telecheck tc = new Telecheck();
        	tc.setAccount_number("123");
        	tc.setAccountholder_name("Tom Eck");
        	tc.setCheck_number("4788250000028291");
        	tc.setCheck_type("C");
        	tc.setClerk_id("RVK_001");
        	tc.setClient_email("rajan.veeramani@firstdata.com");
        	tc.setCustomer_id_number("7623786df");
        	tc.setCustomer_id_type("1");
        	tc.setDate_of_birth("01012010");
        	tc.setDevice_id("jkhsdfjkhsk");
        	tc.setGift_card_amount("100");
        	tc.setRegistration_date("01012014");
        	tc.setRegistration_number("12345");
        	tc.setRelease_type("X");
        	tc.setRouting_number("123456789");
        	tc.setVip("n");
        	tc.setMicr("jkhjkh");
        	request.setCheck(tc);
        }
        
        if(cardtype == CardType.CARD_VALUELINK)
        {
        	ValueLinkCard giftcard = new ValueLinkCard();
        	giftcard.setCardholder_name("Joe Smith");
        	//giftcard.setCc_number("7777045839985463");
        	giftcard.setCc_number("7777061906912522");
        	giftcard.setCredit_card_type("Gift");
        	giftcard.setCard_cost("5");
        	request.setGiftcard(giftcard);
        	
        	request.setCard(null);
        	request.setBilling(null);
        	request.setTransactionType(TransactionType.PURCHASE.getValue());
        }*/
        if(category == TransactionCategory.CATEGORY_ZERODOLLAR)
        {
        	request.setAmount(uri[1]);
        }
        return request;
    }
	public TransactionRequest getPrimaryTransactionForTransType(String[] uri) {
		TransactionRequest request = new TransactionRequest();
		TransactionDataProviderL3 tdpl3 = new TransactionDataProviderL3();


		request.setAmount(uri[1]);

		request.setCurrency(uri[2]);

		request.setPaymentMethod(uri[3]);

		request.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());
		Card card=new Card();
		card.setCvv(uri[4]);
		card.setExpiryDt(uri[5]);
		card.setName(uri[6]);
		card.setType(uri[7]);
		card.setNumber(uri[8]);

		request.setCard(card);
		Address address=new Address();
		request.setBilling(address);
		address.setState(uri[9]);
		address.setAddressLine1(uri[10]);
		address.setZip(uri[11]);
		address.setCountry(uri[12]);
		address.setCity(uri[13]);

		if(category == TransactionCategory.CATEGORY_RECURRING)
		{
			request.setEciindicator("2");
			request.setBilling(null);
		}


		if (cardtypeSecondary == CardType.CARD_TELECHECK) {
			Telecheck tc = new Telecheck();
			tc.setAccount_number("123");
			tc.setAccountholder_name("Tom Eck");
			tc.setCheck_number("4788250000028291");
			tc.setCheck_type("C");
			tc.setClerk_id("RVK_001");
			tc.setClient_email("rajan.veeramani@firstdata.com");
			tc.setCustomer_id_number("7623786df");
			tc.setCustomer_id_type("1");
			tc.setDate_of_birth("01012010");
			tc.setDevice_id("jkhsdfjkhsk");
			tc.setGift_card_amount("100");
			tc.setRegistration_date("01012014");
			tc.setRegistration_number("12345");
			tc.setRelease_type("X");
			tc.setRouting_number("123456789");
			tc.setVip("n");
			tc.setMicr("jkhjkh");
			request.setCheck(tc);
		}

		if (cardtypeSecondary == CardType.CARD_VALUELINK) {
			ValueLinkCard giftcard = new ValueLinkCard();
			giftcard.setCardholder_name("Joe Smith");
			giftcard.setCc_number("7777045839985463");
			giftcard.setCc_number(null);
			giftcard.setCredit_card_type("Gift");
			request.setGiftcard(giftcard);

			request.setCard(null);
			request.setBilling(null);
			//request.setTransactionType(TransactionType.PURCHASE.getValue());
		}

		// category specific
		if (category == TransactionCategory.CATEGORY_AVS) {

			Phone phone = new Phone();
			phone.setNumber(uri[14]);
			phone.setType(uri[15]);
			address.setPhone(phone);
			request.setBilling(address);
		}


		// category specific
		if (category == TransactionCategory.CATEGORY_3DS) {
			request.setPaymentMethod("3DS");
			card.setCavv(tdpl3.ThreeDS_cavv);
			card.setXid(tdpl3.ThreeDS_xid);
			card.setName(tdpl3.ThreeDS_cardholder_name);
			card.setNumber(tdpl3.ThreeDS_card_number);
			card.setType(tdpl3.ThreeDS_type);
			card.setExpiryDt(tdpl3.ThreeDS_exp_date);
			card.setCvv(tdpl3.ThreeDS_cvv);
			ThreeDomainSecureToken threeDST = new ThreeDomainSecureToken();
			//threeDST.setAsymmetricKeyInfo(asymmetricKeyInfo);
			threeDST.setCard_number(tdpl3.ThreeDS_card_number);
			threeDST.setCardholder_name(tdpl3.ThreeDS_cardholder_name);
			threeDST.setCavv(tdpl3.ThreeDS_cavv);
			threeDST.setCvv(tdpl3.ThreeDS_cvv);
			//threeDST.setEciIndicator(tdpl3.eci_indicator);
			//threeDST.setEncryptedData(encryptedData);
			threeDST.setExp_date(tdpl3.ThreeDS_exp_date);
			//threeDST.setMerchantIdentifier(merchantIdentifier);
			//threeDST.setPkcs7Signature(pkcs7Signature);
			//threeDST.setPublicKeyHash(publicKeyHash);
			//threeDST.setSignatureAlgInfo(signatureAlgInfo);
			//threeDST.setSymmetricKeyInfo(symmetricKeyInfo);
			//threeDST.setTransactionId(transactionId);
			threeDST.setType(tdpl3.ThreeDS_type);
			//threeDST.setWrappedKey(wrappedKey);
			threeDST.setXid(tdpl3.ThreeDS_xid);

			request.setThreeDomainSecureToken(threeDST);
			request.setEciindicator(tdpl3.eci_indicator);
			request.setCard(null);


		}

		//category specific
		if(category == TransactionCategory.CATEGORY_TRANSARMOR)
		{
			request.setPaymentMethod("token");
			com.firstdata.firstapi.client.domain.v2.Token token = new com.firstdata.firstapi.client.domain.v2.Token();
			token.setToken_type(tdpl3.token_type);
			Token_data token_data = new Token_data();
			//token.setToken_data(token_data);
			token.setTokenData( new Transarmor() );//()token_data);
			token_data.setCardholder_name(tdpl3.td_cardholder_name);
			token_data.setCvv(tdpl3.td_cvv);
			token_data.setExp_date(tdpl3.td_exp_date);
			token_data.setType(tdpl3.td_type);
			token_data.setValue(tdpl3.td_value);
			//token.setToken_data(token_data);
			token.setTokenData( new Transarmor() );//()token_data);
			request.setToken(token);

			request.setAmount("0733");
			request.setReferenceNo("GODADDY");
			request.setCard(null);
			request.setBilling(null);
        	/*token.getToken_data().setCardholder_name(tdpl3.td_cardholder_name);
        	token.getToken_data().setCvv(tdpl3.td_cvv);
        	token.getToken_data().setExp_date(tdpl3.td_exp_date);
        	token.getToken_data().setType(tdpl3.td_type);
        	token.getToken_data().setValue(tdpl3.td_value);
        	token.setToken_data(token.getToken_data());*/

			//request.setTo(card);
		}

		if(category == TransactionCategory.CATEGORY_SOFTDESCRIPTORS)
		{
			//request.setDescriptor(descriptor);
			SoftDescriptor descriptor = new SoftDescriptor();
			descriptor.setCity(tdpl3.sdescriptor_sd_city);
			descriptor.setCountryCode(tdpl3.sdescriptor_countryCode);
			descriptor.setDba_name(tdpl3.sdescriptor_dba_name);
			descriptor.setMcc(tdpl3.sdescriptor_mcc);
			descriptor.setMerchantContactInfo(tdpl3.sdescriptor_merchantContactInfo);
			descriptor.setMid(tdpl3.sdescriptor_mid);
			descriptor.setPostalCode(tdpl3.sdescriptor_postalCode);
			descriptor.setRegion(tdpl3.sdescriptor_region);
			descriptor.setStreet(tdpl3.sdescriptor_street);
			request.setDescriptor(descriptor);
		}
		//level2
		if(category == TransactionCategory.CATEGORY_LEVEL2)
		{

			//request.setDescriptor(descriptor);
			Level2 l2 = new Level2();
			l2.setCustomer_ref(uri[18]);
			l2.setTax1_amount(uri[17]);
			l2.setTax1_number(uri[16]);
			l2.setTax2_amount(uri[15]);
			l2.setTax2_number(uri[14]);

			request.setLevel2(l2);
		}

		if(category == TransactionCategory.CATEGORY_LEVEL3)

		{
			//request.setDescriptor(descriptor);
			Level3 l3 = new Level3();
			l3.setAlt_tax_amount(tdpl3.level3_alt_tax_amount);
			l3.setAlt_tax_amount(uri[25]);
			l3.setAlt_tax_id(tdpl3.level3_alt_tax_id);
			l3.setAlt_tax_id(uri[24]);
			l3.setDiscount_amount(tdpl3.level3_discount_amount);
			l3.setDiscount_amount(uri[28]);
			l3.setDuty_amount(tdpl3.level3_duty_amount);
			l3.setDuty_amount(uri[14]);
			l3.setFreight_amount(tdpl3.level3_freight_amount);
			l3.setFreight_amount(uri[26]);
			l3.setShip_from_zip(tdpl3.level3_ship_from_zip);
			l3.setShip_from_zip(uri[27]);

			Ship_to_address level3_shiptoaddress = new Ship_to_address();
			level3_shiptoaddress.setAddress_1(tdpl3.level3_shiptoaddress_Address_1);
			level3_shiptoaddress.setAddress_1(uri[21]);
			level3_shiptoaddress.setCity(tdpl3.level3_shiptoaddress_City);
			level3_shiptoaddress.setCity(uri[23]);
			level3_shiptoaddress.setCountry(tdpl3.level3_shiptoaddress_Country);
			level3_shiptoaddress.setCountry(uri[22]);
			level3_shiptoaddress.setCustomer_number(tdpl3.level3_shiptoaddress_Cust_number);
			level3_shiptoaddress.setCustomer_number(uri[15]);
			level3_shiptoaddress.setEmail(tdpl3.level3_shiptoaddress_Email);
			level3_shiptoaddress.setEmail(uri[17]);
			level3_shiptoaddress.setName(tdpl3.level3_shiptoaddress_Name);
			level3_shiptoaddress.setName(uri[18]);
			level3_shiptoaddress.setPhone(tdpl3.level3_shiptoaddress_Phone);
			level3_shiptoaddress.setPhone(uri[16]);
			level3_shiptoaddress.setState(tdpl3.level3_shiptoaddress_State);
			level3_shiptoaddress.setState(uri[19]);
			level3_shiptoaddress.setZip(tdpl3.level3_shiptoaddress_Zip);
			level3_shiptoaddress.setZip(uri[20]);
			l3.setShip_to_address(level3_shiptoaddress);

			Line_item lineitem1 = new Line_item();
			lineitem1.setCommodity_code(tdpl3.litem_commodity_code);
			lineitem1.setCommodity_code(uri[36]);
			lineitem1.setDescription(tdpl3.litem_description);
			lineitem1.setDiscount_amount(tdpl3.litem_discount_amount);
			lineitem1.setDiscount_amount(uri[31]);
			lineitem1.setDiscount_indicator(tdpl3.litem_discount_indicator);
			lineitem1.setDiscount_indicator(uri[34]);
			lineitem1.setGross_net_indicator(tdpl3.litem_gross_net_indicator);
			lineitem1.setGross_net_indicator(uri[35]);
			lineitem1.setLine_item_total(tdpl3.litem_line_item_total);
			lineitem1.setLine_item_total(uri[32]);
			lineitem1.setProduct_code(tdpl3.litem_product_code);
			lineitem1.setProduct_code(uri[37]);
			lineitem1.setQuantity(tdpl3.litem_quantity);
			lineitem1.setQuantity(uri[39]);
			lineitem1.setTax_amount(tdpl3.litem_tax_amount);
			lineitem1.setTax_amount(uri[38]);
			lineitem1.setTax_rate(tdpl3.litem_tax_rate);
			lineitem1.setTax_rate(uri[40]);
			lineitem1.setTax_type(tdpl3.litem_tax_type);
			lineitem1.setTax_type(uri[29]);
			lineitem1.setUnit_cost(tdpl3.litem_unit_cost);
			lineitem1.setUnit_cost(uri[30]);
			lineitem1.setUnit_of_measure(tdpl3.litem_unit_of_measure);
			lineitem1.setUnit_of_measure(uri[33]);

			int lineItemCount1 = 1;
			Line_item[] lineitems1 = new Line_item[lineItemCount1];
			for(int i=0;i<lineItemCount1 ; i++)
			{
				lineitems1[i] = lineitem1;
			}
			l3.setLineitems(lineitems1);
			request.setLevel3(l3);

			Level2 l2 = new Level2();
			l2.setCustomer_ref(tdpl3.level2_customer_ref);
			l2.setCustomer_ref(uri[57]);
			l2.setTax1_amount(tdpl3.level2_tax1_amount);
			l2.setTax1_amount(uri[56]);
			l2.setTax1_number(tdpl3.level2_tax1_number);
			l2.setTax1_number(uri[55]);
			l2.setTax2_amount(tdpl3.level2_tax2_amount);
			l2.setTax2_amount(uri[54]);
			l2.setTax2_number(tdpl3.level2_tax2_number);
			l2.setTax2_number(uri[53]);

			request.setLevel2(l2);

			Line_item lineitem = new Line_item();
			lineitem.setCommodity_code(tdpl3.litem_commodity_code);
			lineitem.setCommodity_code(uri[49]);
			lineitem.setDescription(tdpl3.litem_description);
			lineitem.setDescription(uri[48]);
			lineitem.setDiscount_amount(tdpl3.litem_discount_amount);
			lineitem.setDiscount_amount(uri[43]);
			lineitem.setDiscount_indicator(tdpl3.litem_discount_indicator);
			lineitem.setDiscount_indicator(uri[46]);
			lineitem.setGross_net_indicator(tdpl3.litem_gross_net_indicator);
			lineitem.setGross_net_indicator(uri[47]);
			lineitem.setLine_item_total(tdpl3.litem_line_item_total);
			lineitem.setLine_item_total(uri[44]);
			lineitem.setProduct_code(tdpl3.litem_product_code);
			lineitem.setProduct_code(uri[50]);
			lineitem.setQuantity(tdpl3.litem_quantity);
			lineitem.setQuantity(uri[52]);
			lineitem.setTax_amount(tdpl3.litem_tax_amount);
			lineitem.setTax_amount(uri[51]);
			lineitem.setTax_rate(tdpl3.litem_tax_rate);
			lineitem.setTax_rate(uri[53]);
			lineitem.setTax_type(tdpl3.litem_tax_type);
			lineitem.setTax_type(uri[41]);
			lineitem.setUnit_cost(tdpl3.litem_unit_cost);
			lineitem.setUnit_cost(uri[42]);
			lineitem.setUnit_of_measure(tdpl3.litem_unit_of_measure);
			lineitem.setUnit_of_measure(uri[45]);

			int lineItemCount = 1;
			Line_item[] lineitems = new Line_item[lineItemCount];
			for(int i=0;i<lineItemCount ; i++)
			{
				lineitems[i] = lineitem;
			}
			//request.setLineitems(lineitems);

			//request.getLevel3().setLineitems(lineitems);
			request.setReferenceNo(tdpl3.referenceNo);
			request.setBilling(null);

		}

		if((category == TransactionCategory.CATEGORY_NAKEDREFUNDS )||(category == TransactionCategory.CATEGORY_NAKEDVOIDS ))
		{
			//request.setDescriptor(descriptor);
			request.setEciindicator(tdpl3.eci_indicator);
			card.setType(tdpl3.nakedrefundcardtype);
			card.setNumber(tdpl3.nakedrefundcardnumber);
			card.setName(tdpl3.nakedrefundcardholdername);
			card.setExpiryDt(tdpl3.nakedrefundcardexpdate);
			card.setCavv(null);
			card.setCvv(null);
			card.setCvv2(null);
			card.setXid(null);

			request.setCard(card);
			request.setBilling(null);
		}
		if(category == TransactionCategory.CATEGORY_NAKEDVOIDS )
		{
			//request.setBilling(null);
		}

		if(category == TransactionCategory.CATEGORY_SPLITSHIPMENT)
		{

			//request.setDescriptor(descriptor);
			request.setEciindicator(uri[15]);
			request.setSplitShipment(uri[14]);

		}

		if(category == TransactionCategory.CATEGORY_CVV2)
		{
			card.setCvv2(tdpl3.Cvv2);
			request.setCard(card);
		}
		//request.setAmount(tdpl3.Amount);
		if(cardtype == CardType.CARD_VALUELINK)
		{
			ValueLinkCard giftcard = new ValueLinkCard();
			giftcard.setCardholder_name("Joe Smith");
			//giftcard.setCc_number("7777045839985463");
			giftcard.setCc_number("7777061906912522");
			giftcard.setCredit_card_type("Gift");
			giftcard.setCard_cost("5");
			request.setGiftcard(giftcard);

			request.setCard(null);
			request.setBilling(null);
			request.setTransactionType(TransactionType.PURCHASE.getValue());
		}

		if(category == TransactionCategory.CATEGORY_GENERATETOKEN)
		{
			request.setType("FDToken");

			request.setCard(card) ;
			request.getCard().setNumber("5424180279791732");
			request.getCard().setName("John Smith");
			request.getCard().setExpiryDt("0416");
			request.getCard().setCvv("123");
			request.getCard().setType("mastercard");

			request.setAuth("false");
			request.setTa_token("NOIW");

			request.setToken(null);
			request.setBilling(null);
			request.setTransactionType(null);
			request.setPaymentMethod(null);
			request.setAmount(null);
			request.setCurrency(null);

		}

		if(category == TransactionCategory.CATEGORY_FDTOKEN)
		{
			request.setType("FDToken");

			request.setReferenceNo("abc1412096293369");
			request.setTransactionType("authorize");
			request.setPaymentMethod("token");
			request.setAmount("0");
			request.setCurrency("USD");

			Token token = new Token();
			Transarmor ta = new Transarmor();

			ta.setValue("2833693264441732");
			ta.setName("John Smith");
			ta.setExpiryDt("0416");
			ta.setType("mastercard");

			token.setTokenData(ta);
			token.setToken_type("FDToken");
			request.setToken(token);

			request.setCard(null);
			request.setBilling(null);
			request.setAuth("false");
			request.setTa_token("NOIW");

			//request.setToken(null);
			request.setBilling(null);
			request.setTransactionType(null);
			request.setPaymentMethod(null);
			request.setAmount(null);
			request.setCurrency(null);

		}

		return request;
	}


    public TransactionRequest getPrimaryTransactionForSecondaryModified() {
    	TransactionRequest request=new TransactionRequest();
    	request.setAmount("1100");
        request.setCurrency("USD");

        request.setPaymentMethod("credit_card");
        if(cardtypeSecondary == CardType.CARD_TELECHECK)
        {
        	request.setPaymentMethod("tele_check");
        }
        if(cardtypeSecondary == CardType.CARD_VALUELINK)
        {
        	request.setPaymentMethod("valuelink");
        }

		request.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());
        Card card=new Card();
        card.setCvv("123");
        card.setExpiryDt("1220");
        card.setName("Test data ");
        card.setType("visa");
        card.setNumber("4012000033330026");
        request.setCard(card);

        Address address=new Address();
        request.setBilling(address);
        address.setState("NY");
        address.setAddressLine1("sss");
        address.setZip("11747");
        address.setCountry("US");

        if(cardtypeSecondary == CardType.CARD_TELECHECK)
        {
        	Telecheck tc = new Telecheck();
        	tc.setAccount_number("123");
        	tc.setAccountholder_name("Tom Eck");
        	tc.setCheck_number("4788250000028291");
        	tc.setCheck_type("C");
        	tc.setClerk_id("RVK_001");
        	tc.setClient_email("rajan.veeramani@firstdata.com");
        	tc.setCustomer_id_number("7623786df");
        	tc.setCustomer_id_type("1");
        	tc.setDate_of_birth("01012010");
        	tc.setDevice_id("jkhsdfjkhsk");
        	tc.setGift_card_amount("100");
        	tc.setRegistration_date("01012014");
        	tc.setRegistration_number("12345");
        	tc.setRelease_type("X");
        	tc.setRouting_number("123456789");
        	tc.setVip("n");
        	tc.setMicr("jkhjkh");
        	request.setCheck(tc);
        }

        if(cardtypeSecondary == CardType.CARD_VALUELINK)
        {
        	ValueLinkCard giftcard = new ValueLinkCard();
        	giftcard.setCardholder_name("Joe Smith");
        	giftcard.setCc_number("7777045839985463");
        	giftcard.setCredit_card_type("Gift");
        	request.setGiftcard(giftcard);

        	request.setCard(null);
        	request.setBilling(null);
        	request.setTransactionType(TransactionType.PURCHASE.getValue());
        }

        if(category == TransactionCategory.CATEGORY_RECURRING)
        {
        	request.setEciindicator("2");
        	request.setBilling(null);
        }

        return request;
    }


    private TransactionRequest getSecondaryTransactionForTransType() {
        TransactionRequest trans=new TransactionRequest();
        TransactionDataProviderL3 tdpl3 = new TransactionDataProviderL3();
        trans.setPaymentMethod("credit_card");
        trans.setAmount("1100");
        trans.setCurrency("USD");
        trans.setTransactionTag("349990997");
        //trans.setTransactionId("07698G");
        trans.setId("07698G");
        trans.setReferenceNo("abc1412096293369");

        if(cardtypeSecondary == CardType.CARD_VALUELINK)
        {
        	trans.setPaymentMethod("valuelink");
        	trans.setCard(null);
        	ValueLinkCard giftcard = new ValueLinkCard();
        	giftcard.setCardholder_name("Joe Smith");
        	giftcard.setCc_number("7777045839985463");
        	giftcard.setCc_number(null);
        	giftcard.setCredit_card_type("Gift");
        	trans.setGiftcard(giftcard);

        	trans.setCard(null);
        	trans.setBilling(null);
        }
        if(cardtypeSecondary == CardType.CARD_TELECHECK)
        {
        	trans.setPaymentMethod("tele_check");
        	Address address=new Address();
            address.setState("NY");
            address.setAddressLine1("sss");
            address.setZip("11747");
            address.setCountry("US");
            trans.setBilling(address);
        }

        /************/
     // category specific
        if(category == TransactionCategory.CATEGORY_AVS)
        {
        	Address address=new Address();
            trans.setBilling(address);
            address.setState("NY");
            address.setAddressLine1("sss");
            address.setZip("11747");
            address.setCountry("US");
        	Phone phone  = new Phone();
        	phone.setNumber(tdpl3.phone_number);
        	phone.setType(tdpl3.phone_type);
        	address.setPhone(phone);
        	trans.setBilling(address);
        }


        // category specific
        if(category == TransactionCategory.CATEGORY_3DS)
        {
        	trans.setPaymentMethod("3DS");


        }

        //category specific
        if(category == TransactionCategory.CATEGORY_TRANSARMOR)
        {
        	trans.setPaymentMethod("token");
        	com.firstdata.firstapi.client.domain.v2.Token token = new com.firstdata.firstapi.client.domain.v2.Token();
        	token.setToken_type(tdpl3.token_type);

        	token.getTokenData().setName(tdpl3.td_cardholder_name);
        	token.getTokenData().setCvv(tdpl3.td_cvv);
        	token.getTokenData().setExpiryDt(tdpl3.td_exp_date);
        	token.getTokenData().setType(tdpl3.td_type);
        	token.getTokenData().setValue(tdpl3.td_value);
        	token.setTokenData( new Transarmor());
        }

        if(category == TransactionCategory.CATEGORY_SOFTDESCRIPTORS)
        {
        	//request.setDescriptor(descriptor);
        	/*SoftDescriptor descriptor = new SoftDescriptor();
        	descriptor.setCity(tdpl3.sdescriptor_sd_city);
        	descriptor.setCountryCode(tdpl3.sdescriptor_countryCode);
        	descriptor.setDba_name(tdpl3.sdescriptor_dba_name);
        	descriptor.setMcc(tdpl3.sdescriptor_mcc);
        	descriptor.setMerchantContactInfo(tdpl3.sdescriptor_merchantContactInfo);
        	descriptor.setMid(tdpl3.sdescriptor_mid);
        	descriptor.setPostalCode(tdpl3.sdescriptor_postalCode);
        	descriptor.setRegion(tdpl3.sdescriptor_region);
        	descriptor.setStreet(tdpl3.sdescriptor_street);
        	request.setDescriptor(descriptor);*/
        }
        //level2
        if(category == TransactionCategory.CATEGORY_LEVEL2)
        {
        	//request.setDescriptor(descriptor);
        	Level2 l2 = new Level2();
        	l2.setCustomer_ref(tdpl3.level2_customer_ref);
        	l2.setTax1_amount(tdpl3.level2_tax1_amount);
        	l2.setTax1_number(tdpl3.level2_tax1_number);
        	l2.setTax2_amount(tdpl3.level2_tax2_amount);
        	l2.setTax2_number(tdpl3.level2_tax2_number);
        	trans.setLevel2(l2);
        }

        if(category == TransactionCategory.CATEGORY_LEVEL3)
        {
        	//request.setDescriptor(descriptor);
        	Level3 l3 = new Level3();
        	l3.setAlt_tax_amount(tdpl3.level3_alt_tax_amount);
        	l3.setAlt_tax_id(tdpl3.level3_alt_tax_id);
        	l3.setDiscount_amount(tdpl3.level3_discount_amount);
        	l3.setDuty_amount(tdpl3.level3_duty_amount);
        	l3.setFreight_amount(tdpl3.level3_freight_amount);
        	l3.setShip_from_zip(tdpl3.level3_ship_from_zip);

        	Ship_to_address level3_shiptoaddress = new Ship_to_address();
        	level3_shiptoaddress.setAddress_1(tdpl3.level3_shiptoaddress_Address_1);
        	level3_shiptoaddress.setCity(tdpl3.level3_shiptoaddress_City);
        	level3_shiptoaddress.setCountry(tdpl3.level3_shiptoaddress_Country);
        	level3_shiptoaddress.setCustomer_number(tdpl3.level3_shiptoaddress_Cust_number);
        	level3_shiptoaddress.setEmail(tdpl3.level3_shiptoaddress_Email);
        	level3_shiptoaddress.setName(tdpl3.level3_shiptoaddress_Name);
        	level3_shiptoaddress.setPhone(tdpl3.level3_shiptoaddress_Phone);
        	level3_shiptoaddress.setState(tdpl3.level3_shiptoaddress_State);
        	level3_shiptoaddress.setZip(tdpl3.level3_shiptoaddress_Zip);
        	l3.setShip_to_address(level3_shiptoaddress);

        	Line_item lineitem1 = new Line_item();
        	lineitem1.setCommodity_code(tdpl3.litem_commodity_code);
        	lineitem1.setDescription(tdpl3.litem_description);
        	lineitem1.setDiscount_amount(tdpl3.litem_discount_amount);
        	lineitem1.setDiscount_indicator(tdpl3.litem_discount_indicator);
        	lineitem1.setGross_net_indicator(tdpl3.litem_gross_net_indicator);
        	lineitem1.setLine_item_total(tdpl3.litem_line_item_total);
        	lineitem1.setProduct_code(tdpl3.litem_product_code);
        	lineitem1.setQuantity(tdpl3.litem_quantity);
        	lineitem1.setTax_amount(tdpl3.litem_tax_amount);
        	lineitem1.setTax_rate(tdpl3.litem_tax_rate);
        	lineitem1.setTax_type(tdpl3.litem_tax_type);
        	lineitem1.setUnit_cost(tdpl3.litem_unit_cost);
        	lineitem1.setUnit_of_measure(tdpl3.litem_unit_of_measure);

        	int lineItemCount1 = 1;
        	Line_item[] lineitems1 = new Line_item[lineItemCount1];
        	for(int i=0;i<lineItemCount1 ; i++)
        	{
        		lineitems1[i] = lineitem1;
        	}

        	l3.setLineitems(lineitems1);

        	trans.setLevel3(l3);

        	Line_item lineitem = new Line_item();
        	lineitem.setCommodity_code(tdpl3.litem_commodity_code);
        	lineitem.setDescription(tdpl3.litem_description);
        	lineitem.setDiscount_amount(tdpl3.litem_discount_amount);
        	lineitem.setDiscount_indicator(tdpl3.litem_discount_indicator);
        	lineitem.setGross_net_indicator(tdpl3.litem_gross_net_indicator);
        	lineitem.setLine_item_total(tdpl3.litem_line_item_total);
        	lineitem.setProduct_code(tdpl3.litem_product_code);
        	lineitem.setQuantity(tdpl3.litem_quantity);
        	lineitem.setTax_amount(tdpl3.litem_tax_amount);
        	lineitem.setTax_rate(tdpl3.litem_tax_rate);
        	lineitem.setTax_type(tdpl3.litem_tax_type);
        	lineitem.setUnit_cost(tdpl3.litem_unit_cost);
        	lineitem.setUnit_of_measure(tdpl3.litem_unit_of_measure);

        	int lineItemCount = 1;
        	Line_item[] lineitems = new Line_item[lineItemCount];
        	for(int i=0;i<lineItemCount ; i++)
        	{
        		lineitems[i] = lineitem;
        	}


        	trans.setReferenceNo(tdpl3.referenceNo);

        }

        if(category == TransactionCategory.CATEGORY_NAKEDREFUNDS)
        {

        	trans.setEciindicator(tdpl3.eci_indicator);
        }
        if(category == TransactionCategory.CATEGORY_NAKEDVOIDS)
        {

        	trans.setEciindicator(tdpl3.eci_indicator);
        	//trans.setBilling(null);
        }
        if(category == TransactionCategory.CATEGORY_SPLITSHIPMENT)
        {

        	trans.setSplitShipment(tdpl3.split_shipment);
        }
        if(category == TransactionCategory.CATEGORY_CVV2)
        {

        }
        if(category == TransactionCategory.CATEGORY_GENERATETOKEN)
        {
        	trans.setType("FDToken");

        	trans.setReferenceNo("abc1412096293369");
        	trans.setTransactionType("");
        	trans.setPaymentMethod("token");
        	trans.setAmount("1");
        	trans.setCurrency("USD");


            Card ta = new Card();

            ta.setNumber("5424180279791732");
            ta.setName("John Smith");
            ta.setExpiryDt("0416");
            ta.setType("mastercard");
            ta.setCvv("123");

            trans.setCard(ta);
            trans.setBilling(null);
            trans.setAuth("false");
            trans.setTa_token("NOIW");

            //trans.setToken(null);
            trans.setBilling(null);
            trans.setTransactionType(null);
            trans.setPaymentMethod(null);
            trans.setAmount(null);
            trans.setCurrency(null);

        }
        if(category == TransactionCategory.CATEGORY_FDTOKEN)
        {
        	trans.setType("FDToken");

        	//trans.setReferenceNo("abc1412096293369");
        	trans.setReferenceNo("Astonishing-Sale");
        	//trans.setTransactionType("purchase");
        	trans.setPaymentMethod("token");
        	trans.setAmount("1");
        	trans.setCurrency("USD");

            Token token = new Token();
            Transarmor ta = new Transarmor();

            ta.setValue("2833693264441732");
            ta.setName("John Smith");
            ta.setExpiryDt("0416");
            ta.setType("mastercard");

            token.setTokenData(ta);
            token.setToken_type("FDToken");
            trans.setToken(token);

            trans.setCard(null);
            trans.setBilling(null);
            trans.setAuth("false");
            trans.setTa_token("NOIW");

            //trans.setToken(null);
            trans.setCard(null);
            trans.setBilling(null);

        }
        return trans;
    }

}