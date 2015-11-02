package com.sdk.payeezytokenised;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

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
import com.sample.payeezytokenised.MainActivity;


enum CardType {
	CARD_VISA ,
	CARD_MASTERCARD ,
	CARD_AMEX ,
	CARD_DISCOVER ,
	CARD_VALUELINK, 
	CARD_TELECHECK,
	CARD_NONE
};
enum TransactionCategory
{
	CATEGORY_NONE,
	CATEGORY_CVV,
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
	CATEGORY_PAYPAL,
	CATEGORY_ZERODOLLAR,
	CATEGORY_RECURRING,
	CATEGORY_VALUELINK,
	CATEGORY_GENERATETOKEN,
	CATEGORY_FDTOKEN
};
enum TransactionTypePrimarySecondary
{
	AUTHORISE,
	PURCHASE,
	CREDIT,
	VOID,
	REFUND,
	NAKEDREFUND,
	CAPTURE

};


@SuppressLint("DefaultLocale")
public class RequestTask extends AsyncTask<String, String, String>{

	private Context context = null;

	FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();


	TransactionDataProvider tdpbasic = new TransactionDataProvider();

	TransactionDataProviderL3 tdpl3  = new TransactionDataProviderL3();
	CardType cardtype = CardType.CARD_VISA;
	CardType cardtypeSecondary = CardType.CARD_VISA;
	TransactionCategory category = TransactionCategory.CATEGORY_AVS;

	
	public RequestTask(Context pcontext)
	{
		context = pcontext;


		
	}
	private String statusString = "";
	private String splitter = "~~~~~~~~";

	
    @Override
    protected String doInBackground(String... uri) {

		//For Token Generation using POST method-08-11
		if(uri[0].toLowerCase().equalsIgnoreCase("posttokenvisa"))
		{
			CallGenerateTokenVisaPostToken(uri);
			return "TokenGeneration POST";
		}
    	//Added for GETgettoken aug 3rd
		if(uri[0].toLowerCase().equalsIgnoreCase("gettokenvisa"))
		{
			CallGenerateGETTokenVisaGetToken(uri);
			return "TokenGeneration GET";
		}
		//Added for GETauthorisetoken aug 6th
		if(uri[0].toLowerCase().equalsIgnoreCase("getauthorisetoken"))
		{
			CallAuthorizePurchaseVisaGetGetToken(uri, TransactionTypePrimarySecondary.AUTHORISE);
			return "Authorise Response";
		}


		//Added for GETpurchasetoken aug 6th
		if(uri[0].toLowerCase().equalsIgnoreCase("getpurchasetoken"))
		{
			CallAuthorizePurchaseVisaGetGetToken(uri, TransactionTypePrimarySecondary.PURCHASE);
			return "Purchase Response";
		}

		//Added for GETauthcapturetoken aug 6th
		if(uri[0].toLowerCase().equalsIgnoreCase("getauthcapturetoken"))
		{
			CallAuthorizePurchaseVisaGetGetToken(uri, TransactionTypePrimarySecondary.AUTHORISE);
			CallCaptureRefundVoidVisaGetGetToken(TransactionTypePrimarySecondary.CAPTURE);
			return "Auth + Capture Response";
		}

//Added for GETauthvoidtoken aug 6th
		if(uri[0].toLowerCase().equalsIgnoreCase("getauthvoidtoken"))
		{
			CallAuthorizePurchaseVisaGetGetToken(uri, TransactionTypePrimarySecondary.AUTHORISE);
			CallCaptureRefundVoidVisaGetGetToken(TransactionTypePrimarySecondary.VOID);
			return "Auth + Void Response";
		}
//Added for GETpurchaserefundtoken aug 6th
		if(uri[0].toLowerCase().equalsIgnoreCase("getpurchaserefundtoken"))
		{
			CallAuthorizePurchaseVisaGetGetToken(uri, TransactionTypePrimarySecondary.PURCHASE);
			CallCaptureRefundVoidVisaGetGetToken(TransactionTypePrimarySecondary.REFUND);;
			return "Purchase + Refund Response";
		}


    	return "Authorize";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        System.out.println("Button Authorize Clicked" + result);



		System.out.println("Button Authorize Clicked" + result);
		MainActivity.displayResult.setText(result + statusString);

    }
    /********************GET TOKEN *******************************************/
	
	
	// Generate token using POST method

	//changes made July 29th 11.13 am for cert settings
	private void CallGenerateTokenVisaPostToken(String[] uri)
	{
		try {

			initialize();



			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans=new TransactionRequest();
trans=formpayloadforposttoken(trans,uri);

			trans.setToken(null);
			trans.setBilling(null);
			trans.setTransactionType(null);
			trans.setPaymentMethod(null);
			trans.setAmount(null);
			trans.setCurrency(null);


			Object responseObject = clientHelper.doPrimaryTransaction(trans);
			TransactionResponse tr1 = (TransactionResponse) responseObject;
			statusString = statusString + ((UserTransactionResponse) tr1).getResponseString() + splitter;

	}catch ( Exception e)
		{

			System.out.println(e.getMessage());
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}

	TransactionRequest formpayloadforposttoken(TransactionRequest trans,String[] uri)
	{
		trans.setCurrency(uri[1]);
		trans.setType(uri[2]);

		Card card= new Card();
		trans.setCard(card);
		trans.getCard().setNumber(uri[3]);

		trans.getCard().setName(uri[4]);
		trans.getCard().setExpiryDt(uri[5]);
		trans.getCard().setCvv(uri[6]);
		trans.getCard().setType(uri[7]);

		trans.setAuth(uri[8]);
		trans.setReferenceNo(uri[9]);
		trans.setTa_token("NOIW");

		return trans;
	}
public void initialize()
{
	clientHelper.setAppId(TransactionDataProvider.appIdCert);
	clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCert);
	clientHelper.setToken(TransactionDataProvider.tokenCert);
	clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
	clientHelper.setUrl(TransactionDataProvider.urlCert);

}
	//Method added for GET token August 3rd//Do not delete
	public void CallGenerateGETTokenVisaGetToken(String[] uri) {
		try {
			initialize();


			category = TransactionCategory.CATEGORY_GENERATETOKEN;

			//trying

			clientHelper.setTokenurl(TransactionDataProvider.tokenUrl);
			clientHelper.setJsSecurityKey(TransactionDataProvider.jsSecurityKey);

			String url = clientHelper.getTokenurl() +"ta_token=NOIW"+"&auth=" + uri[1] + "&apikey=" + clientHelper.getAppId() + "&js_security_key=" +
					clientHelper.getJsSecurityKey() + "&callback=" + uri[2] + "&currency=" + uri[3] + "&type=" + uri[4] + "&credit_card.type=" + uri[5]
					+ "&credit_card.cardholder_name=" + uri[6] + "&credit_card.card_number=" + uri[7] + "&credit_card.exp_date=" + uri[8]
					+ "&credit_card.cvv=" + uri[9];


			TransactionResponse response = clientHelper.doPrimaryTransactionGET(url);

			System.out.println("Token gen=" + TransactionResponse.getToken().getTokenData().getValue());

			statusString = "gettoken called    token generated ==";
			statusString = statusString + TransactionResponse.getToken().getTokenData().getValue();
			statusString = statusString + ((UserTransactionResponse) response).getResponseString() + splitter;

			System.out.println("Response : " + response.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			statusString = statusString + "Exception :" + e.getMessage() + splitter;
		}
	}

		//***********************************/
//for GET method
	private void CallAuthorizePurchaseVisaGetGetToken(String[] uri,TransactionTypePrimarySecondary transactionType)
	{
		try
		{
			initialize();
			//generate
			category = com.sdk.payeezytokenised.TransactionCategory.CATEGORY_FDTOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType(uri);

			if ((transactionType) .equals (TransactionTypePrimarySecondary.AUTHORISE))
				trans.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());
			else
				trans.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());

			Object responseObject2 =clientHelper.doPrimaryTransactionObject(trans);

			statusString = statusString + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;
		}catch(Exception e)
		{
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}

    public TransactionRequest getPrimaryTransactionForTransType(String[] uri) {

		TransactionRequest request=new TransactionRequest();


		request.setPaymentMethod(uri[1]);
		request.setAmount(uri[2]);
		request.setCurrency(uri[3]);

		Token token = new Token();
		Transarmor ta = new Transarmor();
		ta.setValue(TransactionResponse.getToken().getTokenData().getValue());
		System.out.println("tokenvalue from authorise"+TransactionResponse.getToken().getTokenData().getValue());
		ta.setName(TransactionResponse.getToken().getTokenData().getName());
		ta.setExpiryDt(TransactionResponse.getToken().getTokenData().getExpiryDt());
		ta.setType(TransactionResponse.getToken().getTokenData().getType());
		token.setTokenData(ta);

		token.setToken_type("FDToken");
		request.setToken(token);
		System.out.println("Token data in authorise"+TransactionResponse.getToken().getTokenData().getValue());
		request.setCard(null);
		request.setBilling(null);
		request.setAuth(null);
		request.setTa_token(null);
		request.setType(null);

		return request;
    }

	private void CallCaptureRefundVoidVisaGetGetToken(TransactionTypePrimarySecondary transactionType)
	{
		try
		{
			cardtypeSecondary = com.sdk.payeezytokenised.CardType.CARD_VISA;

			initialize();

			TransactionRequest trans =new TransactionRequest();

			if ((transactionType) .equals (TransactionTypePrimarySecondary.CAPTURE))
				trans.setTransactionType(TransactionType.CAPTURE.name().toLowerCase());
			else if ((transactionType) .equals (TransactionTypePrimarySecondary.VOID))
				trans.setTransactionType(TransactionType.VOID.name().toLowerCase());
			else if ((transactionType) .equals (TransactionTypePrimarySecondary.REFUND))
				trans.setTransactionType(TransactionType.REFUND.name().toLowerCase());

			//trans.setReferenceNo(TransactionResponse.getTransactionId());

			trans=getpayloadforsecondary(trans);

			TransactionResponse responseObject4 = clientHelper.doSecondaryTransactionObject(trans);
			TransactionResponse resp4 = (TransactionResponse)(responseObject4);
			statusString = statusString + ((UserTransactionResponse)responseObject4).getResponseString() + splitter;

		}catch(Exception e)
		{
		}

	}

	private TransactionRequest getpayloadforsecondary(TransactionRequest trans) {
		trans.setPaymentMethod(TransactionResponse.getMethod());
		trans.setAmount(TransactionResponse.getAmount());
		trans.setCurrency(TransactionResponse.getCurrency());

		trans.setTransactionTag(TransactionResponse.getTransactionTag());
		trans.setId(TransactionResponse.getTransactionId());
		Token token = new Token();
		Transarmor ta = new Transarmor();


		ta.setValue(TransactionResponse.getToken().getTokenData().getValue());
		ta.setName(TransactionResponse.getToken().getTokenData().getName());
		ta.setExpiryDt(TransactionResponse.getToken().getTokenData().getExpiryDt());
		ta.setType(TransactionResponse.getToken().getTokenData().getType());

		token.setTokenData(ta);
		token.setToken_type("FDToken");
		trans.setToken(token);


		return trans;
	}

}