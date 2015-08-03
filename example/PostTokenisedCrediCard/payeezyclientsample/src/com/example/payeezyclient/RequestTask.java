package com.example.payeezyclient;

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
}

@SuppressLint("DefaultLocale")
public class RequestTask extends AsyncTask<String, String, String>{

	private Context context = null;
	TransactionDataProvider tdpbasic = new TransactionDataProvider();
	TransactionDataProviderBase tdpbase  = new TransactionDataProviderBase();
	TransactionDataProviderMC tdpmc  = new TransactionDataProviderMC();
	TransactionDataProviderVisa tdpvisa  = new TransactionDataProviderVisa();
	TransactionDataProviderD tdpdis  = new TransactionDataProviderD();
	TransactionDataProviderAmex tdpamex  = new TransactionDataProviderAmex();
	TransactionDataProviderVL tdpvl  = new TransactionDataProviderVL();
	TransactionDataProviderTC tdptc  = new TransactionDataProviderTC();
	TransactionDataProviderL3 tdpl3  = new TransactionDataProviderL3();
	CardType cardtype = CardType.CARD_VISA;
	CardType cardtypeSecondary = CardType.CARD_VISA;
	TransactionCategory category = TransactionCategory.CATEGORY_AVS;
	EnvCat envcat = new EnvCat(context);
	
	public RequestTask(Context pcontext)
	{
		context = pcontext;
		//TransactionDataProviderBase tdpbase = new TransactionDataProviderMC();
		//TransactionDataProviderMC tdpmc = (TransactionDataProviderMC)tdpbase;
		//TransactionDataProviderMC tdp = (TransactionDataProviderMC)tdpbase;
		tdpbase  = new TransactionDataProviderBase();
		tdpmc  = new TransactionDataProviderMC();
		tdpvisa  = new TransactionDataProviderVisa();
		tdpdis  = new TransactionDataProviderD();
		tdpamex  = new TransactionDataProviderAmex();
		tdpvl  = new TransactionDataProviderVL();
		tdptc  = new TransactionDataProviderTC();
		
	}
	private String statusString = "";
	private String splitter = "~~~~~~~~";
	
    @Override
    protected String doInBackground(String... uri) {
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("gettokenget"))
    	{
    		CallGenerateTokenVisaGetTokenGet();
    		return "gettokenget";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("gettoken"))
    	{
    		CallGetTokenTransactions();
    		return "gettoken";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("gettokenvisa"))
    	{
    		CallGetTokenTransactionsVisa();
    		return "gettokenvisa";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("gettokenmc"))
    	{
    		CallGetTokenTransactionsMC();
    		return "gettokenmc";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("gettokendiscover"))
    	{
    		CallGetTokenTransactionsDiscover();
    		return "gettokendiscover";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("gettokenamex"))
    	{
    		CallGetTokenTransactionsAmex();
    		return "gettokenamex";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorize"))
    	{
    		//CallDirectAPI();
    		//CallAuthorize();
    		CallAuthorizeCards();
    		return "authorize";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase( "purchase"))
    	{
    		//CallPurchase();
    		CallPurchaseCards();
    		return "purchase";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase(  "capture"))
    	{
    		//CallCapture();
    		CallCaptureCards();
    		return "capture";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase( "refund"))
    	{
    		//CallRefund();
    		CallRefundCards();
    		return "refund";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase( "void"))
    	{
    		//CallVoid();
    		CallVoidCards();
    		return "void";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase( "valuelink"))
    	{
    		//CallVoid();
    		CallValueLinkCards();
    		return "valuelink";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase( "telecheck"))
    	{
    		//CallVoid();
    		CallTelecheckCards();
    		return "telecheck";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizevisa"))
    	{
    		CallAuthorizeVisa();
    		return "authorizevisa";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizemastercard"))
    	{
    		CallAuthorizeMC();
    		return "authorizemastercard";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizediscover"))
    	{
    		CallAuthorizeDiscover();
    		return "authorizediscover";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizeamex"))
    	{
    		CallAuthorizeAmex();
    		return "authorizeamex";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizevaluelink"))
    	{
    		CallPurchaseValueLink();
    		return "authorizevaluelink";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizetelecheck"))
    	{
    		CallPurchaseTelecheck();
    		return "authorizetelecheck";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizeavs"))
    	{
    		//CallAuthorizeVisaAVS();
    		CallAuthorizeCardsAVS();
    		return "authorizeavs";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizelevel2"))
    	{
    		//CallAuthorizeVisaLevel2();
    		CallAuthorizeCardsLevel2();
    		return "authorizelevel2";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizelevel3"))
    	{
    		//CallAuthorizeVisaLevel3();
    		CallAuthorizeCardsLevel3();
    		return "authorizelevel3";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizesplitshipments"))
    	{
    		//CallAuthorizeVisaSplitShipments();
    		CallAuthorizeCardsSplitShipments();
    		return "authorizesplitshipments";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizetransarmor"))
    	{
    		//CallAuthorizeVisaTransarmor();
    		CallAuthorizeCardsTransarmor();
    		return "authorizetransarmor";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizenakedrefunds"))
    	{
    		//CallAuthorizeVisaNakedRefunds();
    		CallAuthorizeCardsNakedRefunds();
    		return "authorizenakedrefunds";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizenakedrefundsothers"))
    	{
    		CallAuthorizeCardsNakedRefundsOthers();
    		return "authorizenakedrefundsothers";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizesoftdescriptors"))
    	{
    		//CallAuthorizeVisaSoftDescriptors();
    		CallAuthorizeCardsSoftDescriptors();
    		return "authorizesoftdescriptors";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizepaypal"))
    	{
    		//CallAuthorizeVisaPaypal();
    		CallAuthorizeCardsPaypal();
    		return "authorizepaypal";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("authorize3ds"))
    	{
    		//CallAuthorizeVisa3ds();
    		CallAuthorizeCards3DS();
    		return "authorize3ds";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("recurring"))
    	{
    		//CallAuthorizeVisa3ds();
    		CallAuthorizeVisaRecurring();
    		CallAuthorizeMCRecurring();
    		CallAuthorizeAmexRecurring();
    		CallAuthorizeDiscoverRecurring();
    		return "authorizerecurring";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("recurringothers"))
    	{
    		CallAuthorizeCardsRecurring();
    		return "recurringothers";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("splitshipmentsothers"))
    	{
    		CallAuthorizeCardsSplitShipmentsOthers();
    		return "splitshipmentsothers";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("partialpurchase"))
    	{
    		CallPartialPurchaseValueLink();
    		return "partialpurchase";
    	}
    	if(uri[0].toLowerCase().equalsIgnoreCase("partialpurchasenakedrefunds"))
    	{
    		CallPartialPurchaseVisaNakedRefunds();
    		return "partialpurchasenakedrefunds";
    	}
    	
    	
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("zerodollar"))
    	{
    		CallZeroDollarCardsAuthorize();
    		return "zerodollar";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("direct"))
    	{
    		CallDirectAPI3();
    		return "direct";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("directw"))
    	{
    		CallDirectAPI5();
    		return "directw";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("w"))
    	{
    		CallDirectAPI4();
    		return "direct";
    	}
    	
    	if(uri[0].toLowerCase().equalsIgnoreCase("test"))
    	{
    		CallActivationValueLink();
    		CallSplitPurchaseValueLink();
    		return "test";
    	}
    	
    	
    	
    	//CallAuthorize();
    	return "Authorize";
	    /*    
    	HttpClient httpclient = new DefaultHttpClient();
	        HttpResponse response;
	        String responseString = null;
	        try {
	            response = httpclient.execute(new HttpGet(uri[0]));
	            StatusLine statusLine = response.getStatusLine();
	            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	                ByteArrayOutputStream out = new ByteArrayOutputStream();
	                response.getEntity().writeTo(out);
	                responseString = out.toString();
	                out.close();
	            } else{
	                //Closes the connection.
	                response.getEntity().getContent().close();
	                throw new IOException(statusLine.getReasonPhrase());
	            }
	        } catch (ClientProtocolException e) {
	            //TODO Handle problems..
	        } catch (IOException e) {
	            //TODO Handle problems..
        	}
        return responseString;
        */
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..
        //Toast.makeText(Context.get, text, duration)
        //Toast.makeText( MainActivity.getAppContext(), " Button Authorize Clicked", Toast.LENGTH_SHORT).show();
        System.out.println("Button Authorize Clicked" + result);
        //Toast.makeText( context, result + ":" + statusString, Toast.LENGTH_SHORT).show();
        String[] messages = statusString.split(splitter);
        for(int i=0; i < messages.length; i++)
        {
        	Toast.makeText( context, result + ":" + messages[i], Toast.LENGTH_SHORT).show();
        	//Toast.makeText( context, result + ":" + statusString, Toast.LENGTH_SHORT).show();
        }
    }
    
    
    
    private void CallAuthorizeCards()
    {
    	CallAuthorizeVisa();
    	CallAuthorizeMC();
    	CallAuthorizeDiscover();
    	CallAuthorizeAmex();
    }
    
    private void CallPurchaseCards()
    {
    	CallPurchaseVisa();
    	CallPurchaseMC();
    	CallPurchaseDiscover();
    	CallPurchaseAmex();
    }
    
    private void CallCaptureCards()
    {
    	CallCaptureVisa();
    	CallCaptureMC();
    	CallCaptureDiscover();
    	CallCaptureAmex();
    }
    
    private void CallRefundCards()
    {
    	CallRefundVisa();
    	CallRefundMC();
    	CallRefundDiscover();
    	CallRefundAmex();
    }
    
    private void CallVoidCards()
    {
    	CallVoidVisa();
    	CallVoidMC();
    	CallVoidDiscover();
    	CallVoidAmex();
    }
    
    private void CallTelecheckCards()
    {
    	CallPurchaseTelecheck();
    	CallRefundTelecheck();
    	CallVoidTelecheck();
    	CallTaggedRefundTelecheck();
    	CallTaggedVoidTelecheck();
    }
    
    private void CallValueLinkCards()
    {
    	CallActivationValueLink();
    	CallPurchaseValueLink();
    	CallRefundValueLink();
    	CallSplitPurchaseValueLink();
    	CallVoidValueLink();
    	CallReloadValueLink();
    	CallCashoutValueLink();
    	CallDeactivationValueLink();
    	CallBalanceEnquiryValueLink();
    	
    }
    
    private void CallAuthorizeCardsAVS()
    {
    	CallAuthorizeVisaAVS();
    	CallPurchaseVisaAVS();
    	CallCaptureVisaAVS();
    	CallRefundVisaAVS();
    	CallVoidVisaAVS();
    }
    
    private void CallAuthorizeCardsLevel2()
    {
    	CallAuthorizeVisaLevel2();
    	CallPurchaseVisaLevel2();
    	CallCaptureVisaLevel2();
    	CallRefundVisaLevel2();
    	CallVoidVisaLevel2();
    }
    private void CallAuthorizeCardsLevel3()
    {
    	CallAuthorizeVisaLevel3();
    	CallPurchaseVisaLevel3();
    	CallCaptureVisaLevel3();
    	CallRefundVisaLevel3();
    	CallVoidVisaLevel3();
    }
    
    private void CallAuthorizeCardsNakedRefunds()
    {
    	String res = envcat.CallRefundVisaNakedRefunds();
    	statusString = statusString + res;
    	res = envcat.CallVoidVisaNakedVoids();
    	statusString = statusString + res;
    	//CallRefundVisaNakedRefunds();
    	//CallVoidVisaNakedVoids();
    	//CallAuthorizeVisaNakedAuthorize();
    	//CallPurchaseVisaNakedPurchase();
    	//CallCaptureVisaNakedCapture();
    	
    }
    
    private void CallAuthorizeCardsNakedRefundsOthers()
    {
    	CallRefundVisaNakedRefunds();
    	CallVoidVisaNakedVoids();
    	CallAuthorizeVisaNakedAuthorize();
    	CallPurchaseVisaNakedPurchase();
    	CallCaptureVisaNakedCapture();

    }
    
    private void CallAuthorizeCardsTransarmor()
    {
    	CallAuthorizeVisaTransarmor();
    	CallPurchaseVisaTransarmor();
    	CallCaptureVisaTransarmor();
    	CallRefundVisaTransarmor();
    	CallVoidVisaTransarmor();
    }
    
    private void CallAuthorizeCardsSplitShipments()
    {
    	CallAuthorizeVisaSplitShipments();
    	//CallPurchaseVisaSplitShipments();
    	//CallCaptureVisaSplitShipments();
    	//CallRefundVisaSplitShipments();
    	//CallVoidVisaSplitShipments();
    }
    
    private void CallAuthorizeCardsSplitShipmentsOthers()
    {
    	
    	CallPurchaseVisaSplitShipments();
    	CallCaptureVisaSplitShipments();
    	CallRefundVisaSplitShipments();
    	CallVoidVisaSplitShipments();
    }
    
    private void CallAuthorizeCardsSoftDescriptors()
    {
    	CallAuthorizeVisaSoftDescriptors();
    	CallPurchaseVisaSoftDescriptors();
    	CallCaptureVisaSoftDescriptors();
    	CallRefundVisaSoftDescriptors();
    	CallVoidVisaSoftDescriptors();
    }
    

    private void CallAuthorizeCardsPaypal()
    {
    	CallAuthorizeVisaPaypal();
    	CallPurchaseVisaPaypal();
    	CallCaptureVisaPaypal();
    	CallRefundVisaPaypal();
    	CallVoidVisaPaypal();
    }

    private void CallAuthorizeCards3DS()
    {
    	CallAuthorizeVisa3DS();
    	CallPurchaseVisa3DS();
    	CallCaptureVisa3DS();
    	CallRefundVisa3DS();
    	CallVoidVisa3DS();
    }
    

    private void CallAuthorizeCardsRecurring()
    {
    	CallAuthorizeVisaRecurring();
    	CallAuthorizeMCRecurring();
    	CallAuthorizeDiscoverRecurring();
    	CallAuthorizeAmexRecurring();
    }
    
    private void CallZeroDollarCardsAuthorize()
    {
    	CallZeroDollarAuthorizeVisa();
    	CallZeroDollarAuthorizeMC();
    	CallZeroDollarAuthorizeDiscover();
    	CallZeroDollarAuthorizeAmex();
    }
    
    private void CallGetTokenTransactions()
    {
    	CallGetTokenTransactionsMC();
    	//CallGetTokenTransactionsVisa();
    	//CallGetTokenTransactionsAmex();
    	//CallGetTokenTransactionsDiscover();
    	
    	//CallGenerateTokenVisaGetToken();
    	//CallAuthorizeVisaGetToken();
    	//CallPurchaseVisaGetToken();
    	//CallCaptureVisaGetToken();
    	//CallRefundVisaGetToken();
    	//CallVoidVisaGetToken();
    	
    }
    
    private void CallGetTokenTransactionsVisa()
    {
    	CallGenerateTokenVisaGetToken();
    	CallAuthorizeVisaGetToken();
    	CallPurchaseVisaGetToken();
    //	CallCaptureVisaGetToken();
    //	CallRefundVisaGetToken();
    //	CallVoidVisaGetToken();
    	
    }
    
    private void CallGetTokenTransactionsMC()
    {
    	CallGenerateTokenMCGetToken();
    	CallAuthorizeMCGetToken();
    	CallPurchaseMCGetToken();
    	CallCaptureMCGetToken();
    	CallRefundMCGetToken();
    	CallVoidMCGetToken();
    	
    }
    
    private void CallGetTokenTransactionsAmex()
    {
    	CallGenerateTokenAmexGetToken();
    	CallAuthorizeAmexGetToken();
    	CallPurchaseAmexGetToken();
    	CallCaptureAmexGetToken();
    	CallRefundAmexGetToken();
    	CallVoidAmexGetToken();
    	
    }
    
    private void CallGetTokenTransactionsDiscover()
    {
    	CallGenerateTokenDiscoverGetToken();
    	CallAuthorizeDiscoverGetToken();
    	CallPurchaseDiscoverGetToken();
    	CallCaptureDiscoverGetToken();
    	CallRefundDiscoverGetToken();
    	CallVoidDiscoverGetToken();
    	
    }
    
    
    private void CallAuthorize()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter; 
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
    /*
	private void CallPurchase()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			TransactionResponse responseObject = clientHelper.purchaseTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			//statusString = responseObject.toString();
			//TransactionResponse resp = clientHelper.GetTransactionResponse(responseString);
			//UserTransactionResponse uresp = clientHelper.GetTransactionResponse(responseString);
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//System.out.println(resp.getTransactionStatus() );
			//statusString = uresp.getResponseMessage(); //resp.getTransactionStatus() ;
			//statusString = uresp.getResponseString();
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage() );
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	private void CallCapture()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			//https://api-cert.payeezy.com/v1/transactions_id
			//clientHelper.setAppId(TransactionDataProvider.appIdCat);
			//clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			//clientHelper.setToken(TransactionDataProvider.tokenCat);
			//clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondary();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			////String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallRefund()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondary();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString();
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage() );
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	private void CallVoid()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondary();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			
			
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage() );
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	*/
	
	private void CallAuthorizeVisa()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallAuthorizeMC()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_MASTERCARD;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			//ResponseEntity<List<JSONObject>> rs = (ResponseEntity<List<JSONObject>>)responseObject;
			//Object responseObject = clientHelper.authorizeTransaction(trans);
			//TransactionResponse response = (TransactionResponse)responseObject;
			//TransactionResponse response = clientHelper.authorizeTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			//TransactionResponse resp = clientHelper.GetTransactionResponse(responseString);
			//UserTransactionResponse uresp = clientHelper.GetTransactionResponse(responseString);
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//System.out.println(resp.getTransactionStatus() );
			//statusString = uresp.getResponseMessage(); //resp.getTransactionStatus() ;
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallAuthorizeDiscover()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_DISCOVER;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallAuthorizeAmex()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_AMEX;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			trans.getGiftcard().setCard_cost(null);
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseTelecheck()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_TELECHECK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/*************************************purchase********************/
	
	private void CallPurchaseVisa()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseMC()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_MASTERCARD;
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseDiscover()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_DISCOVER;
			
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseAmex()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_AMEX;
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/*********************************capture****************************/
	
	private void CallCaptureVisa()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallCaptureMC()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_MASTERCARD;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallCaptureDiscover()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_DISCOVER;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallCaptureAmex()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_AMEX;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}

	/*******************************refund**********************************/
	
	private void CallRefundVisa()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallRefundMC()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_MASTERCARD;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString();
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallRefundDiscover()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_DISCOVER;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallRefundAmex()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_AMEX;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString();
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}

	
	/********************************void***************************************/
	
	private void CallVoidVisa()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallVoidMC()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_MASTERCARD;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallVoidDiscover()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_DISCOVER;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallVoidAmex()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_AMEX;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}

	/******************************telecheck***************************************/
	
	private void CallRefundTelecheck()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_TELECHECK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.nakedRefundTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallVoidTelecheck()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_TELECHECK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.nakedVoidTransaction(trans);
			//ResponseEntity<List<JSONObject>> rs = (ResponseEntity<List<JSONObject>>)responseObject;
			//Object responseObject = clientHelper.authorizeTransaction(trans);
			//TransactionResponse response = (TransactionResponse)responseObject;
			//TransactionResponse response = clientHelper.authorizeTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			//TransactionResponse resp = clientHelper.GetTransactionResponse(responseString);
			//UserTransactionResponse uresp = clientHelper.GetTransactionResponse(responseString);
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//System.out.println(resp.getTransactionStatus() );
			//statusString = uresp.getResponseMessage(); //resp.getTransactionStatus() ;
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallTaggedVoidTelecheck()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_TELECHECK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.voidTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallTaggedRefundTelecheck()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_TELECHECK;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getSecondaryTransactionModified();
			trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString();
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}

	/*****************************valuelink******************************************/
	
	
	private void CallRefundValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			trans.getGiftcard().setCard_cost(null);
			trans.setAmount("10");
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			String resString = uresp.getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			cardtypeSecondary = CardType.CARD_VALUELINK;
			//void
			TransactionRequest trans2 = getSecondaryTransactionForTransType();
			trans2.setAmount("10");
			trans2.getGiftcard().setCard_cost(null);
			//trans2.setTransactionId(transaction_id);
			trans2.setId(transaction_id);
			trans2.setTransactionTag(null);
			//trans2.setTransactionTag(transaction_tid);
			Object responseObject2 = clientHelper.refundTransaction(trans2);
			System.out.println("Response : " + responseObject2.toString());
			
			UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = uresp2; //uresp.getBody();
			System.out.println(uresp2.getResponseMessage() );
			statusString = statusString + uresp2.getResponseString() + splitter;
			if(resp2.getTransactionStatus() == "approved")
			{
				System.out.println(uresp2.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		cardtypeSecondary = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
				
	}
	
	private void CallVoidValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			trans.getGiftcard().setCard_cost(null);
			trans.setAmount("10");
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			String resString = uresp.getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			cardtypeSecondary = CardType.CARD_VALUELINK;
			//void
			//TransactionRequest trans2 = getPrimaryTransaction();
			//TransactionRequest trans2 = getSecondaryTransaction();
			TransactionRequest trans2 = getSecondaryTransactionForTransType();
			trans2.setAmount("10");
			trans2.getGiftcard().setCard_cost(null);
			//trans2.setTransactionId(transaction_id);
			trans2.setId(transaction_id);
			//trans2.setTransactionTag(transaction_tid);
			trans2.setTransactionTag(null);
			//Object responseObject2 = clientHelper.nakedVoidTransaction(trans2);
			Object responseObject2 = clientHelper.voidTransaction(trans2);
			System.out.println("Response : " + responseObject2.toString());
			
			UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = uresp2; //uresp.getBody();
			System.out.println(uresp2.getResponseMessage() );
			//statusString = uresp2.getResponseString(); 
			statusString = statusString + uresp2.getResponseString() + splitter;
			if(resp2.getTransactionStatus() == "approved")
			{
				System.out.println(uresp2.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		cardtype = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
	}
	
	private void CallCashoutValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			trans.getGiftcard().setCard_cost(null);
			trans.setAmount(null);
			trans.setCurrency(null);
			//Object responseObject = clientHelper.purchaseTransaction(trans);
			Object responseObject = clientHelper.CashoutTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		cardtype = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
	}
	
	private void CallReloadValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			trans.getGiftcard().setCard_cost(null);
			//Object responseObject = clientHelper.purchaseTransaction(trans);
			Object responseObject = clientHelper.ReloadTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		cardtype = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
	}
	
	
	private void CallPartialPurchaseValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			trans.getGiftcard().setCard_cost(null);
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		cardtype = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
	}
	
	
	private void CallActivationValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			TransactionDataProviderBase tdpbase = new TransactionDataProviderVL();
			//TransactionDataProviderMC tdpmc = (TransactionDataProviderMC)tdpbase;
			TransactionDataProviderVL tdp = (TransactionDataProviderVL)tdpbase;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			tdp.vlcard.setCard_cost("5");
			//category = TransactionCategory.
			//TransactionRequest trans = getPrimaryTransaction();
			TransactionRequest trans = getPrimaryTransaction();
			
			//trans.getGiftcard().setCard_cost(tdp.vlcard.getCard_cost());
			//trans.getGiftcard().setCcnumber(null);
			//trans.getGiftcard().setName(null);
			//Object responseObject = clientHelper.purchaseTransaction(trans);
			Object responseObject = clientHelper.ActivationTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		cardtype = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
	}
	
	private void CallDeactivationValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			trans.getGiftcard().setCard_cost(null);
			trans.setAmount(null);
			trans.setCurrency(null);
			//Object responseObject = clientHelper.purchaseTransaction(trans);
			Object responseObject = clientHelper.DeactivationTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		cardtype = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
	}
	
	private void CallBalanceEnquiryValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			trans.getGiftcard().setCard_cost(null);
			trans.setAmount(null);
			trans.setCurrency(null);
			//Object responseObject = clientHelper.purchaseTransaction(trans);
			Object responseObject = clientHelper.BalanceInquiryTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		cardtype = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
	}

	private void CallSplitPurchaseValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			clientHelper.setAppId(TransactionDataProvider.appIdValueLink);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdValueLink);
			clientHelper.setToken(TransactionDataProvider.tokenValueLink);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			TransactionRequest trans = getPrimaryTransaction();
			trans.getGiftcard().setCard_cost(null);
			trans.setAmount("1200");
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			String resString = uresp.getResponseString();
			int startIndex = resString.indexOf("split_tender_id");
			startIndex = resString.indexOf("=", startIndex);
			int endIndex = resString.indexOf(",", startIndex);
			String splitTenderId = resString.substring(startIndex, endIndex);  
			splitTenderId = splitTenderId.replace(" ", "");
			splitTenderId = splitTenderId.replace(":", "");
			splitTenderId = splitTenderId.replace("=", "");
			
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			//////////////reload card///////////
			
			TransactionRequest trans3 = getPrimaryTransaction();
			trans3.getGiftcard().setCard_cost(null);
			trans3.setAmount("1500");
			Object responseObject3 = clientHelper.ReloadTransaction(trans3);
			System.out.println("Response : " + responseObject3.toString());
			
			UserTransactionResponse uresp3 = (UserTransactionResponse)(responseObject3);
			//TransactionResponse resp3 = uresp3; //uresp.getBody();
			System.out.println(uresp3.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp3.getResponseString() + splitter;
			
			/////////////////////////////////////////
			
			
			//cardtypeSecondary = CardType.CARD_VALUELINK;
			//void
			TransactionRequest trans2 = getPrimaryTransaction();
			trans2.getGiftcard().setCard_cost(null);
			//trans2.setTransactionId(transaction_id);
			//trans2.setId(transaction_id);
			trans2.setSplit_tender_id(splitTenderId);
			Object responseObject2 = clientHelper.purchaseTransaction(trans2);
			System.out.println("Response : " + responseObject2.toString());
			
			UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = uresp2; //uresp.getBody();
			System.out.println(uresp2.getResponseMessage() );
			//statusString = uresp2.getResponseString(); 
			statusString = statusString + uresp2.getResponseString() + splitter;
			if(resp2.getTransactionStatus() == "approved")
			{
				System.out.println(uresp2.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		cardtypeSecondary = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
				
	}


	
	/***************************************************************************/
	
	/********************************AVS****************************************/
	
	/* JSON payload for authorize AVS
	 * {
		    "amount": "000",
		    "transaction_type": "authorize",
		    "merchant_ref": "abc1412096293369",
		    "method": "credit_card",
		    "currency_code": "USD",
		    "credit_card": {
		        "type": "visa",
		        "cardholder_name": "Eck Test 3",
		        "card_number": "4012000033330026",
		        "exp_date": "0416"
		    },
		    "billing_address": {
		        "city": "St. Louis",
		        "country": "US",
		        "email": "abc@main.com",
		        "phone": {
		            "type": "cell",
		            "number": "212-515-1212"
		        },
		        "street": "12115 LACKLAND",
		        "state_province": "MO",
		        "zip_postal_code": "63146 "
		    }
		}
	 */
	
	private void CallAuthorizeVisaAVS()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * JSON payload for purchase AVS
	 * 
	 * {
		    "amount": "2200",
		    "transaction_type": "purchase",
		    "merchant_ref": "abc1412096293369",
		    "method": "credit_card",
		    "currency_code": "USD",
		    "credit_card": {
		        "type": "mastercard",
		        "cardholder_name": "Eck Test 3",
		        "card_number": "5424180279791732",
		        "exp_date": "0416"
		    },
		    "billing_address": {
		        "city": "St. Louis",
		        "country": "US",
		        "email": "abc@main.com",
		        "phone": {
		            "type": "cell",
		            "number": "212-515-1212"
		        },
		        "street": "12115 LACKLAND",
		        "state_province": "MO",
		        "zip_postal_code": "63146 "
		    }
		}
	 * 
	 */
	
	private void CallPurchaseVisaAVS()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
						
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	
	
	private void CallCaptureVisaAVS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionRequest transReq = getSecondaryTransactionForTransType();
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * JSON payload for AVS refund
	 * {
		    "merchant_ref": "abc1412096293369",
		    "transaction_tag": "1849773",
		    "transaction_type": "refund",
		    "method": "credit_card",
		    "amount": "2200",
		    "currency_code": "USD"
		}
	 */
	
	private void CallRefundVisaAVS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * JSON Payload for AVS void
	 * 
	 * {
		    "merchant_ref": "abc1412096293369",
		    "transaction_tag": "1849707",
		    "transaction_type": "void",
		    "method": "credit_card",
		    "amount": "0733",
		    "currency_code": "USD"
		}
	 * 
	 */
	
	private void CallVoidVisaAVS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			////String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			////String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			////String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	
	/*************************level2********************/
	
	private void CallAuthorizeVisaLevel2()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_LEVEL2;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}

	/* JSON payload for level 2 purchase
	 * {
		    "amount": "0800",
		    "transaction_type": "purchase",
		    "merchant_ref": "abc1412096293369",
		    "method": "credit_card",
		    "currency_code": "USD",
		    "credit_card": {
		        "type": "American Express",
		        "cardholder_name": "Eck Test 3",
		        "card_number": "341111597241002",
		        "exp_date": "0416",
		        "cvv": "1234"
		    },
		    "level2": {
		        "tax1_amount": 10,
		        "tax1_number": "2",
		        "tax2_amount": 5,
		        "tax2_number": "3",
		        "customer_ref": "customer1"
		    }
		}
	 */
	
	private void CallPurchaseVisaLevel2()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_LEVEL2;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/* JSON pay load for level 2 capture
	 * {
		    "amount": "1100",
		    "transaction_type": "capture",
		    "transaction_tag": "1849500",
		    "merchant_ref": "abc1412096293369",
		    "method": "credit_card",
		    "currency_code": "USD",
		    "credit_card": {
		        "type": "American Express",
		        "cardholder_name": "Eck Test 3",
		        "card_number": "373953192351004",
		        "exp_date": "0416",
		        "cvv": "1234"
		    },
		    "level2": {
		        "tax1_amount": 10,
		        "tax1_number": "2",
		        "tax2_amount": 5,
		        "tax2_number": "3",
		        "customer_ref": "customer1"
		    }
		}
	 */
	private void CallCaptureVisaLevel2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_LEVEL2;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionRequest transReq = getSecondaryTransactionForTransType();
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	
	
	private void CallRefundVisaLevel2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_LEVEL2;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	
	
	private void CallVoidVisaLevel2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_LEVEL2;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	
	
	/*************************level3********************/
	
	/* JSON pay load for level 3 authorize
	 * 
	 */
	
	private void CallAuthorizeVisaLevel3()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_LEVEL3;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/* JSON pay load for level 3 purchase
	 * {
		    "amount": "9200",
		    "transaction_type": "purchase",
		    "merchant_ref": "abc1412096293369",
		    "method": "credit_card",
		    "currency_code": "USD",
		    "credit_card": {
		        "type": "visa",
		        "cardholder_name": "Eck Test 3",
		        "card_number": "4012000033330026",
		        "exp_date": "0416",
		        "cvv": "123"
		    },
		    "level2": {
		        "tax1_amount": 10,
		        "tax1_number": "2",
		        "tax2_amount": 5,
		        "tax2_number": "3",
		        "customer_ref": "customer1"
		    },
		    "level3": {
		        "alt_tax_amount": 10,
		        "alt_tax_id": "098841111",
		        "discount_amount": 1,
		        "duty_amount": 0.5,
		        "freight_amount": 5,
		        "ship_from_zip": "11235",
		        "ship_to_address": {
		            "city": "New York",
		            "state": "NY",
		            "zip": "11235",
		            "country": "USA",
		            "email": "abc@firstdata.com",
		            "name": "Bob Smith",
		            "phone": "212-515-1111",
		            "address_1": "123 Main Street",
		            "customer_number": "12345"
		        },
		        "line_items": [
		            {
		                "description": "item 1",
		                "quantity": "5",
		                "commodity_code": "C",
		                "discount_amount": 1,
		                "discount_indicator": "G",
		                "gross_net_indicator": "P",
		                "line_item_total": 10,
		                "product_code": "F",
		                "tax_amount": 5,
		                "tax_rate": 0.28,
		                "tax_type": "Federal",
		                "unit_cost": 1,
		                "unit_of_measure": "meters"
		            }
		        ]
		    }
		}
	 */
	
	private void CallPurchaseVisaLevel3()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_LEVEL3;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/* JSON pay load for level 3 Capture
	 * {
		    "amount": "5500",
		    "transaction_tag": "1849515",
		    "transaction_type": "capture",
		    "merchant_ref": "abc1412096293369",
		    "method": "credit_card",
		    "currency_code": "USD",
		    "credit_card": {
		        "type": "visa",
		        "cardholder_name": "Eck Test 3",
		        "card_number": "4012000033330026",
		        "exp_date": "0416",
		        "cvv": "123"
		    },
		    "level2": {
		        "tax1_amount": 10,
		        "tax1_number": "2",
		        "tax2_amount": 5,
		        "tax2_number": "3",
		        "customer_ref": "customer1"
		    },
		    "level3": {
		        "alt_tax_amount": 10,
		        "alt_tax_id": "098841111",
		        "discount_amount": 1,
		        "duty_amount": 0.5,
		        "freight_amount": 5,
		        "ship_from_zip": "11235",
		        "ship_to_address": {
		            "city": "New York",
		            "state": "NY",
		            "zip": "11235",
		            "country": "USA",
		            "email": "abc@firstdata.com",
		            "name": "Bob Smith",
		            "phone": "212-515-1111",
		            "address_1": "123 Main Street",
		            "customer_number": "12345"
		        },
		        "line_items": [
		            {
		                "description": "item 1",
		                "quantity": "5",
		                "commodity_code": "C",
		                "discount_amount": 1,
		                "discount_indicator": "G",
		                "gross_net_indicator": "P",
		                "line_item_total": 10,
		                "product_code": "F",
		                "tax_amount": 5,
		                "tax_rate": 0.28,
		                "tax_type": "Federal",
		                "unit_cost": 1,
		                "unit_of_measure": "meters"
		            }
		        ]
		    }
		}
	 */
	
	private void CallCaptureVisaLevel3()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_LEVEL3;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionRequest transReq = getSecondaryTransactionForTransType();
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_LEVEL3;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/* JSON pay load for level 3 Refund
	 * 
	 */
	
	private void CallRefundVisaLevel3()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_LEVEL3;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_LEVEL3;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/* JSON pay load for level 3 void
	 * 
	 */
	
	private void CallVoidVisaLevel3()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			////String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_LEVEL3;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_LEVEL3;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	
	/*************************TA********************/
	
	/* JSON pay load for transarmor authorize
	 * 
	 */
	
	private void CallAuthorizeVisaTransarmor()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_TRANSARMOR;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}

	/* JSON pay load for transarmor purchase
	 * 
	 */
	
	private void CallPurchaseVisaTransarmor()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_TRANSARMOR;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/* JSON pay load for transarmor capture
	 * 
	 */
	private void CallCaptureVisaTransarmor()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_TRANSARMOR;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionRequest transReq = getSecondaryTransactionForTransType();
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_TRANSARMOR;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/* JSON pay load for transarmor refund
	 * 
	 */
	private void CallRefundVisaTransarmor()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_TRANSARMOR;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_TRANSARMOR;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/* JSON pay load for transarmor void
	 * 
	 */
	private void CallVoidVisaTransarmor()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_TRANSARMOR;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_TRANSARMOR;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	

	
	/*************************Soft Descriptors********************/
	private void CallAuthorizeVisaSoftDescriptors()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_SOFTDESCRIPTORS;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseVisaSoftDescriptors()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_SOFTDESCRIPTORS;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallCaptureVisaSoftDescriptors()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_SOFTDESCRIPTORS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionRequest transReq = getSecondaryTransactionForTransType();
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_SOFTDESCRIPTORS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallRefundVisaSoftDescriptors()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_SOFTDESCRIPTORS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_SOFTDESCRIPTORS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallVoidVisaSoftDescriptors()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_SOFTDESCRIPTORS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_SOFTDESCRIPTORS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	
	/*************************naked refunds********************/
	private void CallAuthorizeVisaNakedAuthorize()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_NAKEDREFUNDS;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseVisaNakedPurchase()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_NAKEDREFUNDS;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallCaptureVisaNakedCapture()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_NAKEDREFUNDS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionRequest transReq = getSecondaryTransactionForTransType();
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			//category = TransactionCategory.CATEGORY_NAKEDREFUNDS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			//trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallRefundVisaNakedRefunds()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_NAKEDREFUNDS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			transReq.getCard().setName(tdpl3.nakedrefundcardholdernamecat);
			transReq.getCard().setNumber(tdpl3.nakedrefundcardnumbercat);
			transReq.getCard().setType(tdpl3.nakedrefundcardtypecat);
			transReq.getCard().setExpiryDt(tdpl3.nakedrefundcardexpdatecat);
			
			
			TransactionResponse responseObject1 = clientHelper.nakedRefundTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			System.out.println(resString);
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			/*
			category = TransactionCategory.CATEGORY_NAKEDREFUNDS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			//trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.nakedRefundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			
			//TransactionResponse responseObject = responseObject1 ;
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			*/
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallVoidVisaNakedVoids()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//category = TransactionCategory.CATEGORY_NAKEDVOIDS;
			category = TransactionCategory.CATEGORY_NONE;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_NAKEDVOIDS;
			//TransactionRequest trans = getSecondaryTransaction();
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			//TransactionRequest trans = getSecondaryTransactionForTransType();
			//trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.nakedVoidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			
			//TransactionResponse responseObject  = responseObject1;
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPartialPurchaseVisaNakedRefunds()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_NAKEDREFUNDS;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.nakedRefundTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	
	/*************************split shipments********************/
	private void CallAuthorizeVisaSplitShipments()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_SPLITSHIPMENT;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			String resString = uresp.getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			statusString = statusString + uresp.getResponseString() + splitter;
			
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			String splitShipment = trans.getSplitShipment();
			String[] ss = splitShipment.split("/");
			int count = Integer.parseInt(ss[0]);
			int total = Integer.parseInt(ss[1]);
			for(int i=count; i<=total;i++)
			{
				//TransactionRequest trans2 = getPrimaryTransactionForTransType();
				TransactionRequest trans2 = getSecondaryTransactionForTransType();
				String split = String.valueOf(i) + "/" + String.valueOf(total);
				trans2.setSplitShipment(split);
				//trans2.setTransactionId(transaction_id);
				trans2.setTransactionId(null);
				trans2.setTransactionTag(transaction_tid);
				trans2.setId(transaction_id);
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
				System.out.println("Response : " + responseObject2.toString());
				
				UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
				TransactionResponse resp2 = uresp2; //uresp.getBody();
				System.out.println(uresp2.getResponseMessage() );
				statusString = statusString + uresp2.getResponseString() + splitter;
				
				String resString2 = uresp2.getResponseString();
				//check if last of the split
				if(resString2.toLowerCase().contains("split_shipment"))
				{
					int start = resString2.indexOf("split_shipment");
					start = resString2.indexOf("=", start+1);
					int end = resString2.indexOf(",", start);
					System.out.println("start : " + Integer.toString(start) + "end : " + Integer.toString(end) );  
					String splitShip = resString2.substring(start, end);  
					splitShip = splitShip.replace(" ", "");
					splitShip = splitShip.replace(":", "");
					splitShip = splitShip.replace("=", "");
					
					String[] completedSplits = splitShip.split("/");
					//int countComplete = Integer.parseInt(completedSplits[0]);
					int totalComplete = Integer.parseInt(completedSplits[1]);

					if(resp2.getTransactionStatus() == "approved")
					{
						System.out.println(uresp2.getTransactionStatus() );
					}
					if(totalComplete >= i)
					{
						break;
					}
				}
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseVisaSplitShipments()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_SPLITSHIPMENT;
			//TransactionRequest trans = getPrimaryTransactionForTransType();
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallCaptureVisaSplitShipments()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_SPLITSHIPMENT;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionRequest transReq = getSecondaryTransactionForTransType();
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_SPLITSHIPMENT;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallRefundVisaSplitShipments()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_SPLITSHIPMENT;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallVoidVisaSplitShipments()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_AVS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_SPLITSHIPMENT;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	
	/*************************paypal********************/
	private void CallAuthorizeVisaPaypal()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_PAYPAL;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseVisaPaypal()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_PAYPAL;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallCaptureVisaPaypal()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_PAYPAL;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionRequest transReq = getSecondaryTransactionForTransType();
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_PAYPAL;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallRefundVisaPaypal()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_PAYPAL;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_PAYPAL;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallVoidVisaPaypal()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_PAYPAL;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_PAYPAL;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	

	/*************************3ds********************/
	private void CallAuthorizeVisa3DS()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_3DS;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseVisa3DS()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_3DS;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallCaptureVisa3DS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_3DS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionRequest transReq = getSecondaryTransactionForTransType();
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_3DS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallRefundVisa3DS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_3DS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_3DS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallVoidVisa3DS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_3DS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_3DS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/*************************recurring********************/
	/**
	 * JSON payload for recurring visa transaction
	 *  {
		    "amount": "5500",
		    "transaction_type": "authorize",
		    "merchant_ref": "abc1412096293369",
		    "method": "credit_card",
		    "currency_code": "USD",
		    "credit_card": {
		        "type": "visa",
		        "cardholder_name": "Eck Test 3",
		        "card_number": "4012000033330026",
		        "exp_date": "0416",
		        "cvv": "123"
		    },
		    "eci_indicator": "2"
		}
	 */
	
	private void CallAuthorizeVisaRecurring()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_RECURRING;
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject = clientHelper.purchaseTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallAuthorizeMCRecurring()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_MASTERCARD;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_RECURRING;
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject = clientHelper.purchaseTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallAuthorizeDiscoverRecurring()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_DISCOVER;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_RECURRING;
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getPrimaryTransactionForSecondaryModified();
			//trans.setTransactionTag(transaction_tid);
			//trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.purchaseTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallAuthorizeAmexRecurring()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_AMEX;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_RECURRING;
			TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			TransactionRequest trans = getPrimaryTransactionForSecondaryModified();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.purchaseTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}

	
	/*************************Cvv2********************/
	
	/*
	private void CallAuthorizeVisaCvv2()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			TransactionDataProviderBase tdpbase = new TransactionDataProviderVisa();
			//TransactionDataProviderMC tdpmc = (TransactionDataProviderMC)tdpbase;
			TransactionDataProviderVisa tdp = (TransactionDataProviderVisa)tdpbase;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_CVV2;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchaseVisaCvv2()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			TransactionDataProviderBase tdpbase = new TransactionDataProviderVisa();
			//TransactionDataProviderMC tdpmc = (TransactionDataProviderMC)tdpbase;
			TransactionDataProviderVisa tdp = (TransactionDataProviderVisa)tdpbase;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = TransactionCategory.CATEGORY_CVV2;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallCaptureVisaCvv2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = TransactionCategory.CATEGORY_CVV2;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionRequest transReq = getSecondaryTransactionForTransType();
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_CVV2;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallRefundVisaCvv2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_CVV2;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			category = TransactionCategory.CATEGORY_CVV2;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallVoidVisaCvv2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_CVV2;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			String resString = ((UserTransactionResponse)responseObject1).getResponseString();
			int startIndex = resString.indexOf("transaction_tag");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String transaction_tid = resString.substring(startIndex, endIndex);  
			transaction_tid = transaction_tid.replace(" ", "");
			transaction_tid = transaction_tid.replace(":", "");
			transaction_tid = transaction_tid.replace("=", "");
			
			startIndex = resString.indexOf("transaction_id");
			startIndex = resString.indexOf("=", startIndex+1);
			endIndex = resString.indexOf(",", startIndex);
			String transaction_id = resString.substring(startIndex, endIndex);  
			transaction_id = transaction_id.replace(" ", "");
			transaction_id = transaction_id.replace(":", "");
			transaction_id = transaction_id.replace("=", "");
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_CVV2;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	*/
	
	/***********************AuthorizeZeroDollar*****************/
	
	/* JSON payload for authorize zero dollar
	 * {
		    "amount": "000",
		    "transaction_type": "authorize",
		    "merchant_ref": "abc1412096293369",
		    "method": "credit_card",
		    "currency_code": "USD",
		    "credit_card": {
		        "type": "visa",
		        "cardholder_name": "Eck Test 3",
		        "card_number": "4012000033330026",
		        "exp_date": "0416"
		    },
		    "billing_address": {
		        "city": "St. Louis",
		        "country": "US",
		        "email": "abc@main.com",
		        "phone": {
		            "type": "cell",
		            "number": "212-515-1212"
		        },
		        "street": "12115 LACKLAND",
		        "state_province": "MO",
		        "zip_postal_code": "63146 "
		    }
		}
	 */
	
	private void CallZeroDollarAuthorizeVisa()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			category = TransactionCategory.CATEGORY_ZERODOLLAR;
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/* JSON payload for authorize zero dollar
	 * {
		    "amount": "000",
		    "transaction_type": "authorize",
		    "merchant_ref": "abc1412096293369",
		    "method": "credit_card",
		    "currency_code": "USD",
		    "credit_card": {
		        "type": "visa",
		        "cardholder_name": "Eck Test 3",
		        "card_number": "4012000033330026",
		        "exp_date": "0416"
		    },
		    "billing_address": {
		        "city": "St. Louis",
		        "country": "US",
		        "email": "abc@main.com",
		        "phone": {
		            "type": "cell",
		            "number": "212-515-1212"
		        },
		        "street": "12115 LACKLAND",
		        "state_province": "MO",
		        "zip_postal_code": "63146 "
		    }
		}
	 */
	
	private void CallZeroDollarAuthorizeMC()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_MASTERCARD;
			
			category = TransactionCategory.CATEGORY_ZERODOLLAR;
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			//ResponseEntity<List<JSONObject>> rs = (ResponseEntity<List<JSONObject>>)responseObject;
			//Object responseObject = clientHelper.authorizeTransaction(trans);
			//TransactionResponse response = (TransactionResponse)responseObject;
			//TransactionResponse response = clientHelper.authorizeTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			//TransactionResponse resp = clientHelper.GetTransactionResponse(responseString);
			//UserTransactionResponse uresp = clientHelper.GetTransactionResponse(responseString);
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//System.out.println(resp.getTransactionStatus() );
			//statusString = uresp.getResponseMessage(); //resp.getTransactionStatus() ;
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallZeroDollarAuthorizeDiscover()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_DISCOVER;
			
			category = TransactionCategory.CATEGORY_ZERODOLLAR;
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			//ResponseEntity<List<JSONObject>> rs = (ResponseEntity<List<JSONObject>>)responseObject;
			//Object responseObject = clientHelper.authorizeTransaction(trans);
			//TransactionResponse response = (TransactionResponse)responseObject;
			//TransactionResponse response = clientHelper.authorizeTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			//TransactionResponse resp = clientHelper.GetTransactionResponse(responseString);
			//UserTransactionResponse uresp = clientHelper.GetTransactionResponse(responseString);
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//System.out.println(resp.getTransactionStatus() );
			//statusString = uresp.getResponseMessage(); //resp.getTransactionStatus() ;
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallZeroDollarAuthorizeAmex()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_AMEX;
			
			category = TransactionCategory.CATEGORY_ZERODOLLAR;
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			
			System.out.println("Response : " + responseObject.toString());
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	/********************GET TOKEN *******************************************/
	
	
	// Generate token

	//changes made July 29th 11.13 am for cert settings
	private void CallGenerateTokenVisaGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			/*clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);*/

			clientHelper.setAppId(TransactionDataProvider.appIdCert);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCert);
			clientHelper.setToken(TransactionDataProvider.tokenCert);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlCert);

			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("4012000033330026");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("visa");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
			//statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			//statusString = responseObject.toString();
			TransactionResponse tr1 = (TransactionResponse)responseObject;
			//statusString = tr1.getStatus();
			//statusString = tr1.getToken().getTokenData().getValue();
			statusString = statusString  + ((UserTransactionResponse)tr1).getResponseString() + splitter;
			/*
			System.out.println("Response : " + responseObject.toString());
			
			statusString = responseObject.toString();
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			*/
		}catch ( Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}

	//
	//changes made july 28th5-15pm for cert settings
	private void CallAuthorizeVisaGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//Generate token
			/*clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);*/

			clientHelper.setAppId(TransactionDataProvider.appIdCert);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCert);
			clientHelper.setToken(TransactionDataProvider.tokenCert);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlCert);
		
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("4012000033330026");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("visa");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received generate transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called generate transaction";
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)responseObject; //uresp.getBody();
			//System.out.println(uresp.getResponseMessage() );
			statusString = statusString  + ((UserTransactionResponse)resp).getResponseString() + splitter;
			System.out.println(resp.getStatus());
			
			TransactionResponse responseToken = resp;
			
			//Authorize
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			
			//category = TransactionCategory.CATEGORY_FDTOKEN;
			
			//TransactionRequest trans = getPrimaryTransactionForTransType();
			//trans = getPrimaryTransactionForTransType();
			//trans.getToken().getTokenData().setValue(responseToken.getToken().getTokenData().getValue());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionType("authorize");
	        trans.setPaymentMethod("token");
	        trans.setAmount("0");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();
	        
	        ta.setValue("2833693264441732");
	        //String s = responseToken.getToken().getTokenData().getValue();
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        token.setTokenData(ta);
	        //token.setTokenType("FDToken");
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling transaction";
			Object responseObject2 = clientHelper.authorizeTransactionToken(trans);
			//System.out.println("Response : " + responseObject2.toString());
			//statusString = "authorize called";
			statusString = statusString  + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;
			/*
			UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = uresp2; //uresp.getBody();
			System.out.println(uresp2.getResponseMessage() );
			statusString = statusString + uresp2.getResponseString() + splitter;
			if(resp2.getTransactionStatus() == "approved")
			{
				System.out.println(uresp2.getTransactionStatus() );
			}
			*/
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//System.out.println(e.getMessage());
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}

	/* JSON pay load for transarmor purchase
	 * 
	 */
	
	private void CallPurchaseVisaGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//Generate token
			//Changes made July 29th 11.28am for Cert settings
			/*clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);*/

			clientHelper.setAppId(TransactionDataProvider.appIdCert);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCert);
			clientHelper.setToken(TransactionDataProvider.tokenCert);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlCert);
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("4012000033330026");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("visa");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			statusString = statusString  + ((UserTransactionResponse)resp).getResponseString() + splitter;
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			statusString = statusString  + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			
			/*
			System.out.println("Response : " + responseObject1.toString());
			
			//UserTransactionResponse uresp1 = (UserTransactionResponse)(responseObject1);
			//TransactionResponse resp1 = uresp1; //uresp.getBody();
			TransactionResponse resp1 = (TransactionResponse)(responseObject1);
			System.out.println(resp1.getStatus() );
			statusString = statusString + resp1.getStatus() + splitter;
			if(resp1.getTransactionStatus() == "approved")
			{
				System.out.println(resp1.getTransactionStatus() );
			}
			*/
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}
	
	/* JSON pay load for transarmor capture
	 * 
	 */
	private void CallCaptureVisaGetToken()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			//Generate Token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("4012000033330026");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("visa");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received generate transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called generate transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter; 
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)responseObject; //uresp.getBody();
			//System.out.println(uresp.getResponseMessage() );
			System.out.println(resp.getStatus());
			
			TransactionResponse responseToken = resp;
			
			//Authorize
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			
			//category = TransactionCategory.CATEGORY_FDTOKEN;
			
			//TransactionRequest trans = getPrimaryTransactionForTransType();
			//trans = getPrimaryTransactionForTransType();
			//trans.getToken().getTokenData().setValue(responseToken.getToken().getTokenData().getValue());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionType("authorize");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();
	        
	        ta.setValue("2833693264441732");
	        //String s = responseToken.getToken().getTokenData().getValue();
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        //token.setTokenType("FDToken");
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling transaction";
			Object responseObject2 = clientHelper.authorizeTransactionToken(trans);
			//System.out.println("Response : " + responseObject2.toString());
			//statusString = "authorize called";
			statusString = statusString + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;
			//System.out.println("Response : " + responseObject2.toString());
			
			//UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = (TransactionResponse)(responseObject2); //uresp2; //uresp.getBody();
			//System.out.println(resp2.getResponseMessage() );
			//statusString = statusString + resp2.getStatus() + splitter;
			//if(resp2.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp2.getTransactionStatus() );
			//	System.out.println(resp2.getTransactionStatus() );
			//}
			//UserTransactionResponse responseToken2 =  uresp2;
			TransactionResponse responseToken2 =  resp2;
			
			//statusString = "0";
			//statusString = responseToken2.getTransactionTag();
			
			//capture
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			trans = getPrimaryTransactionForTransType();
			
			trans.setTransactionTag("349990997");
	        //trans.setTransactionId("07698G");
	        trans.setId("07698G");
	        
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionTag("1871007");
	        trans.setTransactionType(TransactionType.CAPTURE.name()) ; 
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        //statusString = "1";
	        trans.setTransactionTag(responseToken2.getTransactionTag());
	        //statusString = "2";
	        trans.setId(responseToken2.getTransactionId());
	        //statusString = "3";
	        token = new Token();
	        ta = new Transarmor();
	        //statusString = "4";
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        //statusString = "5";
	        /*ta.setName(responseToken.getToken().getTokenData().getName());
	        statusString = "6";
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        statusString = "7";
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        statusString = "8";
	        */
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling capture";
	        TransactionResponse responseObject4 = clientHelper.captureTransactionToken(trans);
	        //statusString = "called capture";
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject4.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp4 = (UserTransactionResponse)(responseObject4);
			TransactionResponse resp4 = (TransactionResponse)(responseObject4); //uresp4; //uresp.getBody();
			statusString = statusString + ((UserTransactionResponse)responseObject4).getResponseString() + splitter;
			//System.out.println(resp4.getResponseMessage() );
			//System.out.println(resp4.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp4.getResponseString() + splitter;
			//statusString = statusString + resp4.getStatus() + splitter;
			//if(resp4.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp4.getTransactionStatus() );
				//System.out.println(resp4.getTransactionStatus() );
			//}
	        
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//statusString = statusString + "Exception :"+ e.getMessage() + splitter;
			//statusString = statusString + " Exception :"+ e.getMessage() ;
		}
	}
	
	/* JSON pay load for transarmor refund
	 * 
	 */
	private void CallRefundVisaGetToken()
	{
		try
		{
			
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			//Generate Token
			//Generate token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
		
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("4012000033330026");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("visa");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			
			TransactionResponse responseToken2 = (TransactionResponse)responseObject1;
			
			//refund
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest trans = getSecondaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setTransactionTag("349990997");
	        //trans.setTransactionId("07698G");
	        trans.setId("07698G");
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType(TransactionType.REFUND.name()) ; 
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        //if(responseToken2 != null)
	        //{
		        trans.setTransactionTag(responseToken2.getTransactionTag());
		        trans.setId(responseToken2.getTransactionId());
	        //}
	        token = new Token();
	        ta = new Transarmor();
	        
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        
	        /*ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        */
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //SoftDescriptor sd = new SoftDescriptor();
	        //sd.setRegion("region");
	        //trans.setDescriptor(sd);
	        
	        //statusString = "calling refund";
			TransactionResponse responseObject5 = clientHelper.refundTransactionToken(trans);
			statusString = statusString + ((UserTransactionResponse)responseObject5).getResponseString() + splitter;
			//statusString = "called refund";
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject5.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp5 = (UserTransactionResponse)(responseObject5);
			TransactionResponse resp5 = (TransactionResponse)(responseObject5); //uresp5; //uresp.getBody();
			//System.out.println(uresp5.getResponseMessage() );
			//System.out.println(resp5.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp5.getResponseString() + splitter;
			//statusString = statusString + resp5.getStatus() + splitter;
			//if(resp5.getTransactionStatus() == "approved")
			//{
				//System.out.println(resp.getStatus() );
			//}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//System.out.println(e.getMessage());
			statusString = statusString + " Exception :"+ e.getMessage() ;//+ splitter;
		}
	}
	
	/* JSON pay load for transarmor void
	 * 
	 */
	private void CallVoidVisaGetToken()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("4012000033330026");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("visa");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			
			TransactionResponse responsetoken2 = (TransactionResponse)responseObject1;
			
			//void
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest trans = getSecondaryTransactionForTransType();
			trans = getSecondaryTransactionForTransType();
			//trans.setTransactionTag(responsetoken2.getTransactionTag());
			//trans.setTransactionId(responsetoken2.getTransactionId());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        trans.setTransactionTag(responsetoken2.getTransactionTag());
	        trans.setId(responsetoken2.getTransactionId());
	        
	        token = new Token();
	        ta = new Transarmor();
	        
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //TransactionResponse response2=client.postTokenTransaction(trans);
	        TransactionResponse responseObject4 = clientHelper.voidTransactionToken(trans);
	        statusString = statusString + ((UserTransactionResponse)responseObject4).getResponseString() + splitter;
	        
			//trans4.setId(transaction_id);
			//TransactionResponse responseObject6 = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject4.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp4 = (UserTransactionResponse)(responseObject4);
			TransactionResponse resp4 = (TransactionResponse)(responseObject4); //uresp4; //uresp.getBody();
			//System.out.println(resp4.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp4.getResponseString() + splitter;
			//statusString = statusString + resp4.getStatus() + splitter;
			//if(resp4.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp4.getTransactionStatus() );
				//System.out.println(resp4.getTransactionStatus() );
			//}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}
	
	///////////////////////////////Amex/////////////////////
	

	// Generate token
	
	private void CallGenerateTokenAmexGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("373953192351004");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("1234");
	        trans.getCard().setType("American Express");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
			//statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			//statusString = responseObject.toString();
			TransactionResponse tr1 = (TransactionResponse)responseObject;
			//statusString = tr1.getStatus();
			//statusString = tr1.getToken().getTokenData().getValue();
			statusString = statusString + ((UserTransactionResponse)tr1).getResponseString() + splitter;
			
			/*
			System.out.println("Response : " + responseObject.toString());
			
			statusString = responseObject.toString();
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			*/
		}catch ( Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}

	//
	
	private void CallAuthorizeAmexGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//Generate token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
		
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("373953192351004");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("1234");
	        trans.getCard().setType("American Express");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received generate transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called generate transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)responseObject; //uresp.getBody();
			//System.out.println(uresp.getResponseMessage() );
			System.out.println(resp.getStatus());
			
			TransactionResponse responseToken = resp;
			
			//Authorize
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			
			//category = TransactionCategory.CATEGORY_FDTOKEN;
			
			//TransactionRequest trans = getPrimaryTransactionForTransType();
			//trans = getPrimaryTransactionForTransType();
			//trans.getToken().getTokenData().setValue(responseToken.getToken().getTokenData().getValue());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionType("authorize");
	        trans.setPaymentMethod("token");
	        trans.setAmount("0");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();
	        
	        ta.setValue("2833693264441732");
	        //String s = responseToken.getToken().getTokenData().getValue();
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        token.setTokenData(ta);
	        //token.setTokenType("FDToken");
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling transaction";
			Object responseObject2 = clientHelper.authorizeTransactionToken(trans);
			//System.out.println("Response : " + responseObject2.toString());
			//statusString = "authorize called";
			
			statusString = statusString + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;
			
			/*
			UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = uresp2; //uresp.getBody();
			System.out.println(uresp2.getResponseMessage() );
			statusString = statusString + uresp2.getResponseString() + splitter;
			if(resp2.getTransactionStatus() == "approved")
			{
				System.out.println(uresp2.getTransactionStatus() );
			}
			*/
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//System.out.println(e.getMessage());
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}

	/* JSON pay load for transarmor purchase
	 * 
	 */
	
	private void CallPurchaseAmexGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//Generate token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("373953192351004");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("1234");
	        trans.getCard().setType("American Express");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			
			/*
			System.out.println("Response : " + responseObject1.toString());
			
			//UserTransactionResponse uresp1 = (UserTransactionResponse)(responseObject1);
			//TransactionResponse resp1 = uresp1; //uresp.getBody();
			TransactionResponse resp1 = (TransactionResponse)(responseObject1);
			System.out.println(resp1.getStatus() );
			statusString = statusString + resp1.getStatus() + splitter;
			if(resp1.getTransactionStatus() == "approved")
			{
				System.out.println(resp1.getTransactionStatus() );
			}
			*/
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}
	
	/* JSON pay load for transarmor capture
	 * 
	 */
	private void CallCaptureAmexGetToken()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			//Generate Token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("373953192351004");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("1234");
	        trans.getCard().setType("American Express");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received generate transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called generate transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)responseObject; //uresp.getBody();
			//System.out.println(uresp.getResponseMessage() );
			System.out.println(resp.getStatus());
			
			TransactionResponse responseToken = resp;
			
			//Authorize
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			
			//category = TransactionCategory.CATEGORY_FDTOKEN;
			
			//TransactionRequest trans = getPrimaryTransactionForTransType();
			//trans = getPrimaryTransactionForTransType();
			//trans.getToken().getTokenData().setValue(responseToken.getToken().getTokenData().getValue());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionType("authorize");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();
	        
	        ta.setValue("2833693264441732");
	        //String s = responseToken.getToken().getTokenData().getValue();
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        token.setTokenData(ta);
	        //token.setTokenType("FDToken");
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling transaction";
			Object responseObject2 = clientHelper.authorizeTransactionToken(trans);
			//System.out.println("Response : " + responseObject2.toString());
			//statusString = "authorize called";
			//System.out.println("Response : " + responseObject2.toString());
			statusString = statusString + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;
			
			//UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = (TransactionResponse)(responseObject2); //uresp2; //uresp.getBody();
			//System.out.println(resp2.getResponseMessage() );
			//statusString = statusString + resp2.getStatus() + splitter;
			//if(resp2.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp2.getTransactionStatus() );
			//	System.out.println(resp2.getTransactionStatus() );
			//}
			//UserTransactionResponse responseToken2 =  uresp2;
			TransactionResponse responseToken2 =  resp2;
			
			//statusString = "0";
			//statusString = responseToken2.getTransactionTag();
			
			//capture
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			trans = getPrimaryTransactionForTransType();
			
			trans.setTransactionTag("349990997");
	        //trans.setTransactionId("07698G");
	        trans.setId("07698G");
	        
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionTag("1871007");
	        trans.setTransactionType(TransactionType.CAPTURE.name()) ; 
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        //statusString = "1";
	        trans.setTransactionTag(responseToken2.getTransactionTag());
	        //statusString = "2";
	        trans.setId(responseToken2.getTransactionId());
	        //statusString = "3";
	        token = new Token();
	        ta = new Transarmor();
	        //statusString = "4";
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        //statusString = "5";
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        /*ta.setName(responseToken.getToken().getTokenData().getName());
	        statusString = "6";
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        statusString = "7";
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        statusString = "8";
	        */
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling capture";
	        TransactionResponse responseObject4 = clientHelper.captureTransactionToken(trans);
	        //statusString = "called capture";
	        statusString = statusString + ((UserTransactionResponse)responseObject4).getResponseString() + splitter;
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject4.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp4 = (UserTransactionResponse)(responseObject4);
			TransactionResponse resp4 = (TransactionResponse)(responseObject4); //uresp4; //uresp.getBody();
			//System.out.println(resp4.getResponseMessage() );
			//System.out.println(resp4.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp4.getResponseString() + splitter;
			//statusString = statusString + resp4.getStatus() + splitter;
			//if(resp4.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp4.getTransactionStatus() );
				//System.out.println(resp4.getTransactionStatus() );
			//}
	        
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//statusString = statusString + "Exception :"+ e.getMessage() + splitter;
			//statusString = statusString + " Exception :"+ e.getMessage() ;
		}
	}
	
	/* JSON pay load for transarmor refund
	 * 
	 */
	private void CallRefundAmexGetToken()
	{
		try
		{
			
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			//Generate Token
			//Generate token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
		
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("373953192351004");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("1234");
	        trans.getCard().setType("American Express");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			
			TransactionResponse responseToken2 = (TransactionResponse)responseObject1;
			
			//refund
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest trans = getSecondaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setTransactionTag("349990997");
	        //trans.setTransactionId("07698G");
	        trans.setId("07698G");
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType(TransactionType.REFUND.name()) ; 
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        //if(responseToken2 != null)
	        //{
		        trans.setTransactionTag(responseToken2.getTransactionTag());
		        trans.setId(responseToken2.getTransactionId());
	        //}
	        token = new Token();
	        ta = new Transarmor();
	        
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //SoftDescriptor sd = new SoftDescriptor();
	        //sd.setRegion("region");
	        //trans.setDescriptor(sd);
	        
	        //statusString = "calling refund";
			TransactionResponse responseObject5 = clientHelper.refundTransactionToken(trans);
			//statusString = "called refund";
			statusString = statusString + ((UserTransactionResponse)responseObject5).getResponseString() + splitter;
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject5.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp5 = (UserTransactionResponse)(responseObject5);
			TransactionResponse resp5 = (TransactionResponse)(responseObject5); //uresp5; //uresp.getBody();
			//System.out.println(uresp5.getResponseMessage() );
			//System.out.println(resp5.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp5.getResponseString() + splitter;
			//statusString = statusString + resp5.getStatus() + splitter;
			//if(resp5.getTransactionStatus() == "approved")
			//{
				//System.out.println(resp.getStatus() );
			//}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//System.out.println(e.getMessage());
			statusString = statusString + " Exception :"+ e.getMessage() ;//+ splitter;
		}
	}
	
	/* JSON pay load for transarmor void
	 * 
	 */
	private void CallVoidAmexGetToken()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("373953192351004");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("American Express");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			TransactionResponse responsetoken2 = (TransactionResponse)responseObject1;
			
			//void
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest trans = getSecondaryTransactionForTransType();
			trans = getSecondaryTransactionForTransType();
			//trans.setTransactionTag(responsetoken2.getTransactionTag());
			//trans.setTransactionId(responsetoken2.getTransactionId());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        trans.setTransactionTag(responsetoken2.getTransactionTag());
	        trans.setId(responsetoken2.getTransactionId());
	        
	        token = new Token();
	        ta = new Transarmor();
	        
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        //ta.setName("John Smith");
	        //ta.setExpiryDt("0416");
	        //ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //TransactionResponse response2=client.postTokenTransaction(trans);
	        TransactionResponse responseObject4 = clientHelper.voidTransactionToken(trans);
	        statusString = statusString + ((UserTransactionResponse)responseObject4).getResponseString() + splitter;
	        
			//trans4.setId(transaction_id);
			//TransactionResponse responseObject6 = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject4.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp4 = (UserTransactionResponse)(responseObject4);
			TransactionResponse resp4 = (TransactionResponse)(responseObject4); //uresp4; //uresp.getBody();
			//System.out.println(resp4.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp4.getResponseString() + splitter;
			//statusString = statusString + resp4.getStatus() + splitter;
			//if(resp4.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp4.getTransactionStatus() );
				//System.out.println(resp4.getTransactionStatus() );
			//}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}
	
	////////////////////////////Discover//////////////////
	

	// Generate token
	
	private void CallGenerateTokenDiscoverGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("6510000000001248");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("discover");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
			//statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			//statusString = responseObject.toString();
			TransactionResponse tr1 = (TransactionResponse)responseObject;
			statusString = statusString + ((UserTransactionResponse)tr1).getResponseString() + splitter;
			//statusString = tr1.getStatus();
			//statusString = tr1.getToken().getTokenData().getValue();
			/*
			System.out.println("Response : " + responseObject.toString());
			
			statusString = responseObject.toString();
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			*/
		}catch ( Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}

	//
	
	private void CallAuthorizeDiscoverGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//Generate token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
		
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("6510000000001248");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("Discover");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        statusString = "received generate transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			statusString = "called generate transaction";
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)responseObject; //uresp.getBody();
			statusString = statusString + ((UserTransactionResponse)resp).getResponseString() + splitter;
			//System.out.println(uresp.getResponseMessage() );
			System.out.println(resp.getStatus());
			
			TransactionResponse responseToken = resp;
			
			//Authorize
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			
			//category = TransactionCategory.CATEGORY_FDTOKEN;
			
			//TransactionRequest trans = getPrimaryTransactionForTransType();
			//trans = getPrimaryTransactionForTransType();
			//trans.getToken().getTokenData().setValue(responseToken.getToken().getTokenData().getValue());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionType("authorize");
	        trans.setPaymentMethod("token");
	        trans.setAmount("0");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();
	        
	        ta.setValue("2833693264441732");
	        //String s = responseToken.getToken().getTokenData().getValue();
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        token.setTokenData(ta);
	        //token.setTokenType("FDToken");
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling transaction";
			Object responseObject2 = clientHelper.authorizeTransactionToken(trans);
			statusString = statusString + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;
			//System.out.println("Response : " + responseObject2.toString());
			//statusString = "authorize called";
			/*
			UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = uresp2; //uresp.getBody();
			System.out.println(uresp2.getResponseMessage() );
			statusString = statusString + uresp2.getResponseString() + splitter;
			if(resp2.getTransactionStatus() == "approved")
			{
				System.out.println(uresp2.getTransactionStatus() );
			}
			*/
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//System.out.println(e.getMessage());
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}

	/* JSON pay load for transarmor purchase
	 * 
	 */
	
	private void CallPurchaseDiscoverGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//Generate token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("6510000000001248");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("Discover");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			
			/*
			System.out.println("Response : " + responseObject1.toString());
			
			//UserTransactionResponse uresp1 = (UserTransactionResponse)(responseObject1);
			//TransactionResponse resp1 = uresp1; //uresp.getBody();
			TransactionResponse resp1 = (TransactionResponse)(responseObject1);
			System.out.println(resp1.getStatus() );
			statusString = statusString + resp1.getStatus() + splitter;
			if(resp1.getTransactionStatus() == "approved")
			{
				System.out.println(resp1.getTransactionStatus() );
			}
			*/
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}
	
	/* JSON pay load for transarmor capture
	 * 
	 */
	private void CallCaptureDiscoverGetToken()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			//Generate Token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("6510000000001248");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("Discover");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received generate transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called generate transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)responseObject; //uresp.getBody();
			//System.out.println(uresp.getResponseMessage() );
			System.out.println(resp.getStatus());
			
			TransactionResponse responseToken = resp;
			
			//Authorize
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			
			//category = TransactionCategory.CATEGORY_FDTOKEN;
			
			//TransactionRequest trans = getPrimaryTransactionForTransType();
			//trans = getPrimaryTransactionForTransType();
			//trans.getToken().getTokenData().setValue(responseToken.getToken().getTokenData().getValue());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionType("authorize");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();
	        
	        ta.setValue("2833693264441732");
	        //String s = responseToken.getToken().getTokenData().getValue();
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        token.setTokenData(ta);
	        //token.setTokenType("FDToken");
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling transaction";
			Object responseObject2 = clientHelper.authorizeTransactionToken(trans);
			//System.out.println("Response : " + responseObject2.toString());
			//statusString = "authorize called";
			statusString = statusString + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;
			//System.out.println("Response : " + responseObject2.toString());
			
			//UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = (TransactionResponse)(responseObject2); //uresp2; //uresp.getBody();
			//System.out.println(resp2.getResponseMessage() );
			//statusString = statusString + resp2.getStatus() + splitter;
			//if(resp2.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp2.getTransactionStatus() );
			//	System.out.println(resp2.getTransactionStatus() );
			//}
			//UserTransactionResponse responseToken2 =  uresp2;
			TransactionResponse responseToken2 =  resp2;
			
			//statusString = "0";
			//statusString = responseToken2.getTransactionTag();
			
			//capture
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			trans = getPrimaryTransactionForTransType();
			
			trans.setTransactionTag("349990997");
	        //trans.setTransactionId("07698G");
	        trans.setId("07698G");
	        
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionTag("1871007");
	        trans.setTransactionType(TransactionType.CAPTURE.name()) ; 
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        //statusString = "1";
	        trans.setTransactionTag(responseToken2.getTransactionTag());
	        //statusString = "2";
	        trans.setId(responseToken2.getTransactionId());
	        //statusString = "3";
	        token = new Token();
	        ta = new Transarmor();
	        //statusString = "4";
	        ta.setValue(responseToken.getToken().getTokenData().getValue());

	        /*ta.setName(responseToken.getToken().getTokenData().getName());

	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());

	        ta.setType(responseToken.getToken().getTokenData().getType());

	        */
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling capture";
	        TransactionResponse responseObject4 = clientHelper.captureTransactionToken(trans);
	        //statusString = "called capture";
	        statusString = statusString + ((UserTransactionResponse)responseObject4).getResponseString() + splitter;
	        
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject4.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp4 = (UserTransactionResponse)(responseObject4);
			TransactionResponse resp4 = (TransactionResponse)(responseObject4); //uresp4; //uresp.getBody();
			//System.out.println(resp4.getResponseMessage() );
			//System.out.println(resp4.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp4.getResponseString() + splitter;
			//statusString = statusString + resp4.getStatus() + splitter;
			//if(resp4.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp4.getTransactionStatus() );
				//System.out.println(resp4.getTransactionStatus() );
			//}
	        
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//statusString = statusString + "Exception :"+ e.getMessage() + splitter;
			//statusString = statusString + " Exception :"+ e.getMessage() ;
		}
	}
	
	/* JSON pay load for transarmor refund
	 * 
	 */
	private void CallRefundDiscoverGetToken()
	{
		try
		{
			
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			//Generate Token
			//Generate token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
		
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("6510000000001248");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("Discover");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        //ta.setName(responseToken.getToken().getTokenData().getName());
	        //ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        //ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			
			
			TransactionResponse responseToken2 = (TransactionResponse)responseObject1;
			
			//refund
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest trans = getSecondaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setTransactionTag("349990997");
	        //trans.setTransactionId("07698G");
	        trans.setId("07698G");
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType(TransactionType.REFUND.name()) ; 
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        //if(responseToken2 != null)
	        //{
		        trans.setTransactionTag(responseToken2.getTransactionTag());
		        trans.setId(responseToken2.getTransactionId());
	        //}
	        token = new Token();
	        ta = new Transarmor();
	        
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        
	        /*ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        */
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //SoftDescriptor sd = new SoftDescriptor();
	        //sd.setRegion("region");
	        //trans.setDescriptor(sd);
	        
	        //statusString = "calling refund";
			TransactionResponse responseObject5 = clientHelper.refundTransactionToken(trans);
			//statusString = "called refund";
			statusString = statusString + ((UserTransactionResponse)responseObject5).getResponseString() + splitter;
			
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject5.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp5 = (UserTransactionResponse)(responseObject5);
			TransactionResponse resp5 = (TransactionResponse)(responseObject5); //uresp5; //uresp.getBody();
			//System.out.println(uresp5.getResponseMessage() );
			//System.out.println(resp5.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp5.getResponseString() + splitter;
			//statusString = statusString + resp5.getStatus() + splitter;
			//if(resp5.getTransactionStatus() == "approved")
			//{
				//System.out.println(resp.getStatus() );
			//}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//System.out.println(e.getMessage());
			statusString = statusString + " Exception :"+ e.getMessage() ;//+ splitter;
		}
	}
	
	/* JSON pay load for transarmor void
	 * 
	 */
	private void CallVoidDiscoverGetToken()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("6510000000001248");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("Discover");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			
			TransactionResponse responsetoken2 = (TransactionResponse)responseObject1;
			
			//void
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest trans = getSecondaryTransactionForTransType();
			trans = getSecondaryTransactionForTransType();
			//trans.setTransactionTag(responsetoken2.getTransactionTag());
			//trans.setTransactionId(responsetoken2.getTransactionId());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        trans.setTransactionTag(responsetoken2.getTransactionTag());
	        trans.setId(responsetoken2.getTransactionId());
	        
	        token = new Token();
	        ta = new Transarmor();
	        
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        
	        //ta.setName(responseToken.getToken().getTokenData().getName());
	        //ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        //ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //TransactionResponse response2=client.postTokenTransaction(trans);
	        TransactionResponse responseObject4 = clientHelper.voidTransactionToken(trans);
	        statusString = statusString + ((UserTransactionResponse)responseObject4).getResponseString() + splitter;
	        
			//trans4.setId(transaction_id);
			//TransactionResponse responseObject6 = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject4.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp4 = (UserTransactionResponse)(responseObject4);
			TransactionResponse resp4 = (TransactionResponse)(responseObject4); //uresp4; //uresp.getBody();
			//System.out.println(resp4.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp4.getResponseString() + splitter;
			//statusString = statusString + resp4.getStatus() + splitter;
			//if(resp4.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp4.getTransactionStatus() );
				//System.out.println(resp4.getTransactionStatus() );
			//}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}
	
	
	//////////////////////////Mastercard///////////////////////
	

	// Generate token
	
	private void CallGenerateTokenMCGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("5424180279791732");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("mastercard");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
			//statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			//statusString = "called transaction";
			//statusString = responseObject.toString();
			TransactionResponse tr1 = (TransactionResponse)responseObject;
			//statusString = tr1.getStatus();
			//statusString = tr1.getToken().getTokenData().getValue();
			/*
			System.out.println("Response : " + responseObject.toString());
			
			statusString = responseObject.toString();
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			*/
		}catch ( Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}

	//
	
	private void CallAuthorizeMCGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//Generate token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
		
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("5424180279791732");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("mastercard");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received generate transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called generate transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)responseObject; //uresp.getBody();
			//System.out.println(uresp.getResponseMessage() );
			System.out.println(resp.getStatus());
			
			TransactionResponse responseToken = resp;
			
			//Authorize
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			
			//category = TransactionCategory.CATEGORY_FDTOKEN;
			
			//TransactionRequest trans = getPrimaryTransactionForTransType();
			//trans = getPrimaryTransactionForTransType();
			//trans.getToken().getTokenData().setValue(responseToken.getToken().getTokenData().getValue());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionType("authorize");
	        trans.setPaymentMethod("token");
	        trans.setAmount("0");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();
	        
	        ta.setValue("2833693264441732");
	        //String s = responseToken.getToken().getTokenData().getValue();
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        token.setTokenData(ta);
	        //token.setTokenType("FDToken");
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling transaction";
			Object responseObject2 = clientHelper.authorizeTransactionToken(trans);
			//System.out.println("Response : " + responseObject2.toString());
			//statusString = "authorize called";
			statusString = statusString + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;
			/*
			UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = uresp2; //uresp.getBody();
			System.out.println(uresp2.getResponseMessage() );
			statusString = statusString + uresp2.getResponseString() + splitter;
			if(resp2.getTransactionStatus() == "approved")
			{
				System.out.println(uresp2.getTransactionStatus() );
			}
			*/
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//System.out.println(e.getMessage());
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}

	/* JSON pay load for transarmor purchase
	 * 
	 */
	
	private void CallPurchaseMCGetToken()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//Generate token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("5424180279791732");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("mastercard");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			
			/*
			System.out.println("Response : " + responseObject1.toString());
			
			//UserTransactionResponse uresp1 = (UserTransactionResponse)(responseObject1);
			//TransactionResponse resp1 = uresp1; //uresp.getBody();
			TransactionResponse resp1 = (TransactionResponse)(responseObject1);
			System.out.println(resp1.getStatus() );
			statusString = statusString + resp1.getStatus() + splitter;
			if(resp1.getTransactionStatus() == "approved")
			{
				System.out.println(resp1.getTransactionStatus() );
			}
			*/
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}
	
	/* JSON pay load for transarmor capture
	 * 
	 */
	private void CallCaptureMCGetToken()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			//Generate Token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
	        
	        trans.getCard().setNumber("5424180279791732");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("mastercard");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received generate transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called generate transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)responseObject; //uresp.getBody();
			//System.out.println(uresp.getResponseMessage() );
			System.out.println(resp.getStatus());
			
			TransactionResponse responseToken = resp;
			
			//Authorize
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			
			//category = TransactionCategory.CATEGORY_FDTOKEN;
			
			//TransactionRequest trans = getPrimaryTransactionForTransType();
			//trans = getPrimaryTransactionForTransType();
			//trans.getToken().getTokenData().setValue(responseToken.getToken().getTokenData().getValue());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionType("authorize");
	        trans.setPaymentMethod("token");
	        trans.setAmount("0");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();
	        
	        ta.setValue("2833693264441732");
	        //String s = responseToken.getToken().getTokenData().getValue();
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        token.setTokenData(ta);
	        //token.setTokenType("FDToken");
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling transaction";
			Object responseObject2 = clientHelper.authorizeTransactionToken(trans);
			//System.out.println("Response : " + responseObject2.toString());
			//statusString = "authorize called";
			statusString = statusString + ((UserTransactionResponse)responseObject2).getResponseString() + splitter;
			//System.out.println("Response : " + responseObject2.toString());
			
			//UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
			TransactionResponse resp2 = (TransactionResponse)(responseObject2); //uresp2; //uresp.getBody();
			//System.out.println(resp2.getResponseMessage() );
			//statusString = statusString + resp2.getStatus() + splitter;
			//if(resp2.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp2.getTransactionStatus() );
			//	System.out.println(resp2.getTransactionStatus() );
			//}
			//UserTransactionResponse responseToken2 =  uresp2;
			TransactionResponse responseToken2 =  resp2;
			
			statusString = "0";
			//statusString = responseToken2.getTransactionTag();
			
			//capture
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			trans = getPrimaryTransactionForTransType();
			
			trans.setTransactionTag("349990997");
	        //trans.setTransactionId("07698G");
	        trans.setId("07698G");
	        
			trans.setReferenceNo("abc1412096293369");
	        trans.setTransactionTag("1871007");
	        trans.setTransactionType(TransactionType.CAPTURE.name()) ; 
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        statusString = "1";
	        trans.setTransactionTag(responseToken2.getTransactionTag());
	        statusString = "2";
	        trans.setId(responseToken2.getTransactionId());
	        statusString = "3";
	        token = new Token();
	        ta = new Transarmor();
	        statusString = "4";
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        statusString = "5";
	        /*ta.setName(responseToken.getToken().getTokenData().getName());
	        statusString = "6";
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        statusString = "7";
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        statusString = "8";
	        */
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "calling capture";
	        TransactionResponse responseObject4 = clientHelper.captureTransactionToken(trans);
	        //statusString = "called capture";
	        statusString = statusString + ((UserTransactionResponse)responseObject4).getResponseString() + splitter;
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject4.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp4 = (UserTransactionResponse)(responseObject4);
			TransactionResponse resp4 = (TransactionResponse)(responseObject4); //uresp4; //uresp.getBody();
			//System.out.println(resp4.getResponseMessage() );
			//System.out.println(resp4.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp4.getResponseString() + splitter;
			//statusString = statusString + resp4.getStatus() + splitter;
			//if(resp4.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp4.getTransactionStatus() );
				//System.out.println(resp4.getTransactionStatus() );
			//}
	        
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//statusString = statusString + "Exception :"+ e.getMessage() + splitter;
			//statusString = statusString + " Exception :"+ e.getMessage() ;
		}
	}
	
	/* JSON pay load for transarmor refund
	 * 
	 */
	private void CallRefundMCGetToken()
	{
		try
		{
			
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			//Generate Token
			//Generate token
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
		
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("5424180279791732");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("mastercard");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        //ta.setName(responseToken.getToken().getTokenData().getName());
	        //ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        //ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			//statusString = " called purchase transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			TransactionResponse responseToken2 = (TransactionResponse)responseObject1;
			
			//refund
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest trans = getSecondaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setTransactionTag("349990997");
	        //trans.setTransactionId("07698G");
	        trans.setId("07698G");
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType(TransactionType.REFUND.name()) ; 
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        //if(responseToken2 != null)
	        //{
		        trans.setTransactionTag(responseToken2.getTransactionTag());
		        trans.setId(responseToken2.getTransactionId());
	        //}
	        token = new Token();
	        ta = new Transarmor();
	        
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        
	        /*ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        */
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //SoftDescriptor sd = new SoftDescriptor();
	        //sd.setRegion("region");
	        //trans.setDescriptor(sd);
	        
	        //statusString = "calling refund";
			TransactionResponse responseObject5 = clientHelper.refundTransactionToken(trans);
			//statusString = "called refund";
			statusString = statusString + ((UserTransactionResponse)responseObject5).getResponseString() + splitter;
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject5.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp5 = (UserTransactionResponse)(responseObject5);
			TransactionResponse resp5 = (TransactionResponse)(responseObject5); //uresp5; //uresp.getBody();
			//System.out.println(uresp5.getResponseMessage() );
			//System.out.println(resp5.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp5.getResponseString() + splitter;
			//statusString = statusString + resp5.getStatus() + splitter;
			//if(resp5.getTransactionStatus() == "approved")
			//{
				//System.out.println(resp.getStatus() );
			//}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//System.out.println(e.getMessage());
			statusString = statusString + " Exception :"+ e.getMessage() ;//+ splitter;
		}
	}
	
	/* JSON pay load for transarmor void
	 * 
	 */
	private void CallVoidMCGetToken()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.appIdInt);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
			clientHelper.setToken(TransactionDataProvider.tokenInt);
			clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
			clientHelper.setUrl(TransactionDataProvider.urlInt);
			
			//generate
			category = TransactionCategory.CATEGORY_GENERATETOKEN;
			TransactionRequest trans = getPrimaryTransactionForTransType();
			
			trans.setType("FDToken");
			
	        trans.getCard().setNumber("5424180279791732");
	        trans.getCard().setName("John Smith");
	        trans.getCard().setExpiryDt("0416");
	        trans.getCard().setCvv("123");
	        trans.getCard().setType("mastercard");
	        
	        trans.setAuth("false");
	        trans.setTa_token("NOIW");
	        
	        trans.setToken(null);
	        trans.setBilling(null);
	        trans.setTransactionType(null);
	        trans.setPaymentMethod(null);
	        trans.setAmount(null);
	        trans.setCurrency(null);
			
	        //statusString = "received transaction";
			Object responseObject = clientHelper.getTokenTransaction(trans);
			//statusString = "called transaction";
			statusString = statusString + ((UserTransactionResponse)responseObject).getResponseString() + splitter;
			
			System.out.println("Response : " + responseObject.toString());
			
			//UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			//TransactionResponse resp = uresp; //uresp.getBody();
			TransactionResponse resp = (TransactionResponse)(responseObject);; //uresp.getBody();
			System.out.println(resp.getStatus() );
			
			TransactionResponse responseToken = resp;
			
			// purchase
			
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest transGen = getPrimaryTransactionForTransType();
			trans = getPrimaryTransactionForTransType();
			
			trans.setReferenceNo("Astonishing-Sale");
	        trans.setTransactionType("purchase");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        Token token = new Token();
	        Transarmor ta = new Transarmor();

	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        ta.setName(responseToken.getToken().getTokenData().getName());
	        ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //statusString = "before purchase transaction";
	        //statusString = trans.getToken().getToken_type().toUpperCase();
			Object responseObject1 = clientHelper.purchaseTransactionToken(trans);
			//statusString = ((TransactionResponse) responseObject1).getValidationStatus();
			statusString = statusString + ((UserTransactionResponse)responseObject1).getResponseString() + splitter;
			//statusString = " called purchase transaction";
			TransactionResponse responsetoken2 = (TransactionResponse)responseObject1;
			
			//void
			
			//TransactionRequest trans = getSecondaryTransaction();
			category = TransactionCategory.CATEGORY_FDTOKEN;
			//TransactionRequest trans = getSecondaryTransactionForTransType();
			trans = getSecondaryTransactionForTransType();
			//trans.setTransactionTag(responsetoken2.getTransactionTag());
			//trans.setTransactionId(responsetoken2.getTransactionId());
			
			trans.setReferenceNo("abc1412096293369");
	        trans.setPaymentMethod("token");
	        trans.setAmount("1");
	        trans.setCurrency("USD");
	        
	        trans.setTransactionTag(responsetoken2.getTransactionTag());
	        trans.setId(responsetoken2.getTransactionId());
	        
	        token = new Token();
	        ta = new Transarmor();
	        
	        ta.setValue(responseToken.getToken().getTokenData().getValue());
	        
	        //ta.setName(responseToken.getToken().getTokenData().getName());
	        //ta.setExpiryDt(responseToken.getToken().getTokenData().getExpiryDt());
	        //ta.setType(responseToken.getToken().getTokenData().getType());
	        
	        ta.setName("John Smith");
	        ta.setExpiryDt("0416");
	        ta.setType("mastercard");
	        
	        token.setTokenData(ta);
	        token.setToken_type("FDToken");
	        trans.setToken(token);
	        
	        trans.setCard(null);
	        trans.setBilling(null);
	        trans.setAuth(null);
	        trans.setTa_token(null);
	        trans.setType(null);
	        
	        //TransactionResponse response2=client.postTokenTransaction(trans);
	        TransactionResponse responseObject4 = clientHelper.voidTransactionToken(trans);
	        statusString = statusString + ((UserTransactionResponse)responseObject4).getResponseString() + splitter;
	        
			//trans4.setId(transaction_id);
			//TransactionResponse responseObject6 = clientHelper.voidTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//System.out.println("Response : " + responseObject4.toString());
			//String responseString = responseObject.toString();
			//UserTransactionResponse uresp4 = (UserTransactionResponse)(responseObject4);
			TransactionResponse resp4 = (TransactionResponse)(responseObject4); //uresp4; //uresp.getBody();
			//System.out.println(resp4.getStatus() );
			//statusString = uresp.getResponseString(); 
			//statusString = statusString + uresp4.getResponseString() + splitter;
			//statusString = statusString + resp4.getStatus() + splitter;
			//if(resp4.getTransactionStatus() == "approved")
			//{
				//System.out.println(uresp4.getTransactionStatus() );
				//System.out.println(resp4.getTransactionStatus() );
			//}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			//statusString = statusString + "Exception :"+ e.getMessage() + splitter;
		}
	}
	
	
	
	/********************GET TOKEN via GET*******************************************/
	
	
	// Generate token
	
		private void CallGenerateTokenVisaGetTokenGet()
		{
			try
			{
				FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
				cardtype = CardType.CARD_VISA;
				
				clientHelper.setAppId(TransactionDataProvider.appIdInt);
				clientHelper.setSecuredSecret(TransactionDataProvider.secureIdInt);
				clientHelper.setToken(TransactionDataProvider.tokenInt);
				clientHelper.setTrToken(TransactionDataProvider.trTokenInt);
				clientHelper.setUrl(TransactionDataProvider.urlInt);
				category = TransactionCategory.CATEGORY_GENERATETOKEN;
				TransactionRequest trans = getPrimaryTransactionForTransType();
				
				trans.setType("FDToken");
		        
		        trans.getCard().setNumber("4012000033330026");
		        trans.getCard().setName("John Smith");
		        trans.getCard().setExpiryDt("0416");
		        trans.getCard().setCvv("123");
		        trans.getCard().setType("visa");
		        
		        trans.setAuth("false");
		        trans.setTa_token("NOIW");
		        
		        trans.setToken(null);
		        trans.setBilling(null);
		        trans.setTransactionType(null);
		        trans.setPaymentMethod(null);
		        trans.setAmount(null);
		        trans.setCurrency(null);
				
				//statusString = "received transaction";
				Object responseObject = clientHelper.getTokenTransaction(trans);
				//statusString = "called transaction";
				//statusString = responseObject.toString();
				TransactionResponse tr1 = (TransactionResponse)responseObject;
				//statusString = tr1.getStatus();
				//statusString = tr1.getToken().getTokenData().getValue();
				statusString = statusString  + ((UserTransactionResponse)tr1).getResponseString() + splitter;
				/*
				System.out.println("Response : " + responseObject.toString());
				
				statusString = responseObject.toString();
				
				UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
				TransactionResponse resp = uresp; //uresp.getBody();
				System.out.println(uresp.getResponseMessage() );
				statusString = statusString + uresp.getResponseString() + splitter;
				if(resp.getTransactionStatus() == "approved")
				{
					System.out.println(uresp.getTransactionStatus() );
				}
				*/
			}catch ( Exception e)
			{
				//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
				System.out.println(e.getMessage());
				statusString = statusString + "Exception :"+ e.getMessage() + splitter;
			}
		}

	
	
	/******************DirectAPI***********************/
	
	
	private void CallDirectAPI3()
	{
		//try
		//{
		
			AppRequestor requestor = new AppRequestor();
			String jsonString = "{ " +
					"  \"merchant_ref\": \"Astonishing-Sale\"," +
							"  \"transaction_type\": \"purchase\"," +
									"  \"method\": \"credit_card\"," +
											"  \"amount\": \"1299\"," +
													"  \"currency_code\": \"USD\"," +
															"  \"credit_card\": {" + 
													" \"type\": \"visa\", " +
	     " \"cardholder_name\": \"John Smith\"," +
		 " \"card_number\": \"4788250000028291\", " +
	     " \"exp_date\": \"1020\","+ 
		 " \"cvv\": \"123\" " +
		 " }}";
			
			//String appUrlCert = "https://api-cert.payeezy.com/v1";
			String tokenid = "";
			String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			String appUrl = "http://api-cert.payeezy.com/v1";
			String apiType = "transactions";
			String transId = ""; 
			String corelationid = ""; 
			String splitTenderId = ""; 
			String transactiontag = ""; 
			String extractedTransactionId = "";
			String extractedTransactionTid = "";
			
			String merchantid = "";
			String trtoken = "";
			
			System.out.println(apiType + transId+ corelationid + splitTenderId + transactiontag + extractedTransactionId + extractedTransactionTid);
			
			CustomFileReader2 reader = new CustomFileReader2(context);
			String[][] data = reader.GetFileContents("newTestPayloads.xls");
			for(int i=1;i<data.length;i++)
			{
				//try
				//{
					String transactionType = data[i][0];
					String requestData = data[i][1];
					String tokenData = data[i][7];
					if(data[i].length >=2)
					{
						appId = data[i][2];
						token = data[i][3];;
						secureId = data[i][4];;
						appUrl = data[i][5];
						apiType = data[i][6];
						tokenid = data[i][7];
						transactiontag = data[i][8];
						corelationid = data[i][9];
						splitTenderId = data[i][10];
						
						if(data[i].length>10)
						{
							merchantid = data[i][10];
						}
						if(data[i].length>11)
						{
							trtoken = data[i][11];
						}
						
						System.out.println(tokenData); 
					}
					String trType = "";
					if(requestData.contains("transaction_type"))
					{
						int start = requestData.indexOf("transaction_type");
						start = requestData.indexOf(":", start + 1);
						int end = requestData.indexOf(",", start );
						trType = requestData.substring(start , end );  
						trType = trType.replace(" ", "");
						trType = trType.replace(":", "");
						trType = trType.replace("=", "");
						System.out.println(trType );
					}
					transactionType = trType;
					transactionType = transactionType.toLowerCase();
					//tokenid = "";
					
					//appUrl = appUrl + "/transactions";
					appUrl = data[i][5] + "/" + data[i][6] ;
					//appUrl = data[i][5] + "/" + data[i][6] + "/" + tokenid;
					//appUrl = appUrl.replace("api-cat", "api-cert");
					//appUrl = appUrl.replace("https:", "http:");
					jsonString = requestData;
					
					//boolean tMod = false;
					//if (transactionType == "authorize")
					//{
					//	tMod = true;
					//}
					//if (transactionType != "authorize")
					//{
					//	tMod = true;
					//}
							
					if( !((transactionType != "authorize") || (transactionType != "purchase") 
							|| (transactionType != "activate") || (transactionType != "deactivate") 
							|| (transactionType != "balanceenquiry") || (transactionType != "split")) )
					{
						//tMod = true;
						//secondary transaction
						appUrl = appUrl + "/" + tokenid;
						
						//appUrl = appUrl + "/{" + transId + "}";
						//appUrl = appUrl + "/{" + tokenid + "}";
						//if(jsonString.toLowerCase().contains("transaction_tag"))
						//{
						//	int startIndex = jsonString.indexOf("transaction_tag");
						//	startIndex = jsonString.indexOf("=", startIndex+1);
						//	int endIndex = jsonString.indexOf(",", startIndex);
						//	String current_tid = jsonString.substring(startIndex+1, endIndex);
						//	jsonString.replace(current_tid, extractedTransactionTid);
						//	System.out.println(extractedTransactionTid);
						//}
						
					}
					
					if((transactionType == null) || (transactionType == ""))
					{
						//generate token
					}
					
					
					String response =  requestor.MakeRequest(jsonString, appUrl, tokenid, appId, secureId, token, transactionType,merchantid, trtoken);
					System.out.println(response);
					//statusString = statusString + response + splitter;
					statusString = response ;
					reader.WriteToFile("resultsFile.txt", response);
					if((transactionType.toLowerCase().equalsIgnoreCase("authorize")) ||
							(transactionType.toLowerCase().equalsIgnoreCase("purchase")))
					{
						String resString = response;
						int startIndex =0;
						int endIndex = 0;
						if(resString.length() >0)
						{
							if(resString.toLowerCase().contains("transaction_tag"))
							{
								startIndex = resString.indexOf("transaction_tag");
								startIndex = resString.indexOf("=", startIndex+1);
								endIndex = resString.indexOf(",", startIndex);
								String transaction_tid = resString.substring(startIndex, endIndex);
								transaction_tid = transaction_tid.replace(" ", "");
								transaction_tid = transaction_tid.replace(":", "");
								transaction_tid = transaction_tid.replace("=", "");
								extractedTransactionTid = transaction_tid ;
								System.out.println(transaction_tid);
							}
							if(resString.toLowerCase().contains("transaction_id"))
							{
								startIndex = resString.indexOf("transaction_id");
								startIndex = resString.indexOf("=", startIndex+1);
								endIndex = resString.indexOf(",", startIndex);
								String transaction_id = resString.substring(startIndex, endIndex);  
								transaction_id = transaction_id.replace(" ", "");
								transaction_id = transaction_id.replace(":", "");
								transaction_id = transaction_id.replace("=", "");
								extractedTransactionId = transaction_id ;
								System.out.println(transaction_id);
							}
						}
					}
					
					
					int index = response.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				//}catch(Exception e)
				//{
				//	System.out.println(e.getMessage());
				//}
			}
			
		//}
		//catch(Exception e)
		//{
		//	System.out.println(e.getMessage());
		//}
	}
	
	
	
	private void CallDirectAPI4()
	{
		CustomFileReader2 cf = new CustomFileReader2(context);
		String[] colData = new String[] {"test1", "test2", "test3"};
		cf.WriteToExcel("resultsTP.xls", colData);
	}
	
	
	private void CallDirectAPI5()
	{
		//try
		//{
		
			AppRequestor requestor = new AppRequestor();
			String jsonString = "{ " +
					"  \"merchant_ref\": \"Astonishing-Sale\"," +
							"  \"transaction_type\": \"purchase\"," +
									"  \"method\": \"credit_card\"," +
											"  \"amount\": \"1299\"," +
													"  \"currency_code\": \"USD\"," +
															"  \"credit_card\": {" + 
													" \"type\": \"visa\", " +
	     " \"cardholder_name\": \"John Smith\"," +
		 " \"card_number\": \"4788250000028291\", " +
	     " \"exp_date\": \"1020\","+ 
		 " \"cvv\": \"123\" " +
		 " }}";
			
			//String appUrlCert = "https://api-cert.payeezy.com/v1";
			String tokenid = "";
			String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			String appUrl = "http://api-cert.payeezy.com/v1";
			String apiType = "transactions";
			String transId = ""; 
			String corelationid = ""; 
			String splitTenderId = ""; 
			String transactiontag = ""; 
			String extractedTransactionId = "";
			String extractedTransactionTid = "";
			
			String merchantid = "";
			String trtoken= "";
			
			System.out.println(apiType + transId+ corelationid + splitTenderId + transactiontag + extractedTransactionId + extractedTransactionTid);
			
			CustomFileReader2 reader = new CustomFileReader2(context);
			String[][] data = reader.GetFileContents("newTestPayloads.xls");
			String[] resultData = new String[data.length];
			for(int i=1;i<data.length;i++)
			{
				//try
				//{
					String transactionType = data[i][0];
					String requestData = data[i][1];
					String tokenData = data[i][7];
					if(data[i].length >=2)
					{
						appId = data[i][2];
						token = data[i][3];;
						secureId = data[i][4];;
						appUrl = data[i][5];
						apiType = data[i][6];
						tokenid = data[i][7];
						transactiontag = data[i][8];
						corelationid = data[i][9];
						splitTenderId = data[i][10];
						
						if(data[i].length >10)
						{
							merchantid = data[i][11];
						}
						if(data[i].length >11)
						{
							trtoken = data[i][12];
						}
						
						System.out.println(tokenData);
					}
					String trType = "";
					if(requestData.contains("transaction_type"))
					{
						int start = requestData.indexOf("transaction_type");
						start = requestData.indexOf(":", start + 1);
						int end = requestData.indexOf(",", start );
						trType = requestData.substring(start , end );  
						trType = trType.replace(" ", "");
						trType = trType.replace(":", "");
						trType = trType.replace("=", "");
						System.out.println(trType );
					}
					transactionType = trType;
					transactionType = transactionType.toLowerCase();
					//tokenid = "";
					
					//appUrl = appUrl + "/transactions";
					appUrl = data[i][5] + "/" + data[i][6] ;
					//appUrl = data[i][5] + "/" + data[i][6] + "/" + tokenid;
					//appUrl = appUrl.replace("api-cat", "api-cert");
					//appUrl = appUrl.replace("https:", "http:");
					jsonString = requestData;
					
					//boolean tMod = false;
					//if (transactionType == "authorize")
					//{
					//	tMod = true;
					//}
					//if (transactionType != "authorize")
					//{
					//	tMod = true;
					//}
					
							
					if( !((transactionType != "authorize") || (transactionType != "purchase") 
							|| (transactionType != "activate") || (transactionType != "deactivate") 
							|| (transactionType != "balanceenquiry") || (transactionType != "split")) )
					{
						//tMod = true;
						//secondary transaction
						appUrl = appUrl + "/" + tokenid;
						
						////appUrl = appUrl + "/{" + transId + "}";
						//appUrl = appUrl + "/{" + tokenid + "}";
						//if(jsonString.toLowerCase().contains("transaction_tag"))
						//{
						//	int startIndex = jsonString.indexOf("transaction_tag");
						//	startIndex = jsonString.indexOf("=", startIndex+1);
						//	int endIndex = jsonString.indexOf(",", startIndex);
						//	String current_tid = jsonString.substring(startIndex+1, endIndex);
						//	jsonString.replace(current_tid, extractedTransactionTid);
						//	System.out.println(extractedTransactionTid);
						//}
						
					}
					
					String response =  requestor.MakeRequest(jsonString, appUrl, tokenid, appId, secureId, token, transactionType, merchantid, trtoken);
					System.out.println(response);
					//statusString = statusString + response + splitter;
					statusString = response ;
					resultData[i-1] = response;
					reader.WriteToFile("resultsFile.txt", response);
					
					if((transactionType.toLowerCase().equalsIgnoreCase("authorize")) ||
							(transactionType.toLowerCase().equalsIgnoreCase("purchase")))
					{
						String resString = response;
						int startIndex =0;
						int endIndex = 0;
						if(resString.length() >0)
						{
							if(resString.toLowerCase().contains("transaction_tag"))
							{
								startIndex = resString.indexOf("transaction_tag");
								startIndex = resString.indexOf("=", startIndex+1);
								endIndex = resString.indexOf(",", startIndex);
								String transaction_tid = resString.substring(startIndex, endIndex);
								transaction_tid = transaction_tid.replace(" ", "");
								transaction_tid = transaction_tid.replace(":", "");
								transaction_tid = transaction_tid.replace("=", "");
								extractedTransactionTid = transaction_tid ;
								System.out.println(transaction_tid);
							}
							if(resString.toLowerCase().contains("transaction_id"))
							{
								startIndex = resString.indexOf("transaction_id");
								startIndex = resString.indexOf("=", startIndex+1);
								endIndex = resString.indexOf(",", startIndex);
								String transaction_id = resString.substring(startIndex, endIndex);  
								transaction_id = transaction_id.replace(" ", "");
								transaction_id = transaction_id.replace(":", "");
								transaction_id = transaction_id.replace("=", "");
								extractedTransactionId = transaction_id ;
								System.out.println(transaction_id);
							}
						}
					}
					
					
					int index = response.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				//}catch(Exception e)
				//{
				//	System.out.println(e.getMessage());
				//}
			}
			reader.WriteToExcel("resultsPayloads.xls", resultData);
		//}
		//catch(Exception e)
		//{
		//	System.out.println(e.getMessage());
		//}
	}

	
/*
	
	private void CallDirectAPI2()
	{
		
		AppRequestor requestor = new AppRequestor();
		String jsonString = "{ " +
				"  \"merchant_ref\": \"Astonishing-Sale\"," +
						"  \"transaction_type\": \"purchase\"," +
								"  \"method\": \"credit_card\"," +
										"  \"amount\": \"1299\"," +
												"  \"currency_code\": \"USD\"," +
														"  \"credit_card\": {" + 
												" \"type\": \"visa\", " +
     " \"cardholder_name\": \"John Smith\"," +
	 " \"card_number\": \"4788250000028291\", " +
     " \"exp_date\": \"1020\","+ 
	 " \"cvv\": \"123\" " +
	 " }}";
		
		String appUrl = "https://api-cert.payeezy.com/v1";
		String tokenid = "";
		String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
		String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
		String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
		String url = "https://api-cert.payeezy.com/v1";
		String apiType = "transactions";
		String transId = ""; 
		
		CustomFileReader2 reader = new CustomFileReader2(context);
		String[][] data = reader.GetFileContents("TestPayloads.xls");
		for(int i=0;i<data.length;i++)
		{
			String transactionType = data[i][0];
			String requestData = data[i][4];
			String tokenData = data[i][3];
			if(data[i].length >=18)
			{
				appId = data[i][12];
				secureId = data[i][13];;
				token = data[i][14];;
				url = data[i][15];
				apiType = data[i][16];
				tokenid = data[i][17];
			}
			
			tokenid = "";
			
			//appUrl = appUrl + "/transactions";
			appUrl = appUrl + "/" + apiType;
			jsonString = requestData;
			String response =  requestor.MakeRequest(jsonString, appUrl, tokenid);
			System.out.println(response);
			statusString = response;
			if((transactionType.toLowerCase().equalsIgnoreCase("authorize")) ||
					(transactionType.toLowerCase().equalsIgnoreCase("purchase")))
			{
				String resString = response;
				int startIndex =0;
				int endIndex = 0;
				if(resString.length() >0)
				{
					if(resString.toLowerCase().contains("transaction_tag"))
					{
						startIndex = resString.indexOf("transaction_tag");
						startIndex = resString.indexOf("=", startIndex+1);
						endIndex = resString.indexOf(",", startIndex);
						String transaction_tid = resString.substring(startIndex, endIndex);  
						transaction_tid = transaction_tid.replace(" ", "");
						transaction_tid = transaction_tid.replace(":", "");
						transaction_tid = transaction_tid.replace("=", "");
						System.out.println(transaction_tid);
					}
					if(resString.toLowerCase().contains("transaction_id"))
					{
						startIndex = resString.indexOf("transaction_id");
						startIndex = resString.indexOf("=", startIndex+1);
						endIndex = resString.indexOf(",", startIndex);
						String transaction_id = resString.substring(startIndex, endIndex);  
						transaction_id = transaction_id.replace(" ", "");
						transaction_id = transaction_id.replace(":", "");
						transaction_id = transaction_id.replace("=", "");
						System.out.println(transaction_id);
					}
				}
			}
			int index = response.indexOf("approved");
			if(index >=0)
			{
				System.out.println("Transaction Approved");
			}
		}
		
	}
	*/
	
	/*
	private void CallDirectAPI10()
	{
		//try
		//{
		
			AppRequestor requestor = new AppRequestor();
			String jsonString = "{ " +
					"  \"merchant_ref\": \"Astonishing-Sale\"," +
							"  \"transaction_type\": \"purchase\"," +
									"  \"method\": \"credit_card\"," +
											"  \"amount\": \"1299\"," +
													"  \"currency_code\": \"USD\"," +
															"  \"credit_card\": {" + 
													" \"type\": \"visa\", " +
	     " \"cardholder_name\": \"John Smith\"," +
		 " \"card_number\": \"4788250000028291\", " +
	     " \"exp_date\": \"1020\","+ 
		 " \"cvv\": \"123\" " +
		 " }}";
			
			String appUrlCert = "https://api-cert.payeezy.com/v1";
			String tokenid = "";
			String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			String appUrl = "http://api-cert.payeezy.com/v1";
			String apiType = "transactions";
			String transId = ""; 
			String corelationid = ""; 
			String splitTenderId = ""; 
			String transactiontag = ""; 
			String extractedTransactionId = "";
			String extractedTransactionTid = "";
			
			//String response =  requestor.MakeGetRequest(params, jsonString, appUrl, tokenid, appId, token, secureId, transactionType)
					
			//System.out.println(response);
			//statusString = statusString + response + splitter;
			//statusString = response ;
			
			
		
	}
	*/

	/*
	private void CallDirectAPI()
	{
		
		AppRequestor requestor = new AppRequestor();
		String jsonString = "{ " +
				"  \"merchant_ref\": \"Astonishing-Sale\"," +
						"  \"transaction_type\": \"purchase\"," +
								"  \"method\": \"credit_card\"," +
										"  \"amount\": \"1299\"," +
												"  \"currency_code\": \"USD\"," +
														"  \"credit_card\": {" + 
												" \"type\": \"visa\", " +
     " \"cardholder_name\": \"John Smith\"," +
	 " \"card_number\": \"4788250000028291\", " +
     " \"exp_date\": \"1020\","+ 
	 " \"cvv\": \"123\" " +
	 " }}";
		
		String appUrl = "https://api-cert.payeezy.com/v1";
		String tokenid = "";
		String response =  requestor.MakeRequest(jsonString, appUrl, tokenid);
		System.out.println(response);
		statusString = response;
		
	}
	*/

	/*
	private void CallDirectAPI()
	{
		
		AppRequestor requestor = new AppRequestor();
		String jsonString = "{ " +
				"  \"merchant_ref\": \"Astonishing-Sale\"," +
						"  \"transaction_type\": \"purchase\"," +
								"  \"method\": \"credit_card\"," +
										"  \"amount\": \"1299\"," +
												"  \"currency_code\": \"USD\"," +
														"  \"credit_card\": {" + 
												" \"type\": \"visa\", " +
     " \"cardholder_name\": \"John Smith\"," +
	 " \"card_number\": \"4788250000028291\", " +
     " \"exp_date\": \"1020\","+ 
	 " \"cvv\": \"123\" " +
	 " }}";
		
		String appUrl = "https://api-cert.payeezy.com/v1";
		String tokenid = "";
		String response =  requestor.MakeRequest(jsonString, appUrl, tokenid);
		System.out.println(response);
		statusString = response;
		
	}
	*/

	/*	
	private void CallDirectAPI4()
	{
		CustomFileReader2 cf = new CustomFileReader2(context);
		String[] colData = new String[] {"test1", "test2", "test3"};
		cf.WriteToExcel("resultsTP.xls", colData);
	}
	 */	
	
	
	
	/****************************************************/
	public void ShowMessage(String messageStr)
	{
		Handler handler = new Handler(Looper.getMainLooper());

		handler.post(new Runnable() {

		        @Override
		        public void run() {
		            //Your UI code here
		        	//Toast.makeText(context, messageStr + "Authorize Completed", Toast.LENGTH_SHORT).show();
		        }
		    });
		
	}
	
	/**************************************************************************************/
	private TransactionRequest getPrimaryTransaction() {
        TransactionRequest request=new TransactionRequest();
        
        request.setAmount("1100");
        
        request.setCurrency("USD");
        
        request.setPaymentMethod("credit_card");
        if(cardtype == CardType.CARD_TELECHECK)
        {
        	request.setPaymentMethod("tele_check");
        }
        if(cardtype == CardType.CARD_VALUELINK)
        {
        	request.setPaymentMethod("valuelink");
        }
        request.setTransactionType(TransactionType.AUTHORIZE.getValue());
        
        Card card=new Card();
        card.setCvv("123");
        card.setExpiryDt("1220");
        card.setName("Test data ");
        card.setType("visa");
        card.setNumber("4012000033330026");
        if(cardtype == CardType.CARD_VISA)
        {
        	card.setType("visa");
        	card.setNumber("4012000033330026");
        }
        if(cardtype == CardType.CARD_MASTERCARD)
        {
        	card.setType("mastercard");
        	card.setNumber("5424180279791732");
        }
        if(cardtype == CardType.CARD_DISCOVER)
        {
        	card.setType("discover");
        	card.setNumber("6510000000001248");
        }
        if(cardtype == CardType.CARD_AMEX)
        {
        	card.setType("American Express");
        	card.setNumber("373953192351004");
        	card.setCvv("1234");
        }
        
        //card.setNumber("4788250000028291");
        
        request.setCard(card);
        Address address=new Address();
        request.setBilling(address);
        address.setState("NY");
        address.setAddressLine1("sss");
        address.setZip("11747");
        address.setCountry("US");

        if(cardtype == CardType.CARD_TELECHECK)
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
        }
        if(category == TransactionCategory.CATEGORY_ZERODOLLAR)
        {
        	request.setAmount("0");
        }
        return request;
    }
     
    private TransactionRequest getSecondaryTransaction() {
        TransactionRequest trans=new TransactionRequest();
        trans.setPaymentMethod("credit_card");
        trans.setAmount("1100");
        trans.setCurrency("USD");
        trans.setTransactionTag("349990997");
        //trans.setTransactionId("07698G");
        trans.setId("07698G");
        trans.setReferenceNo("abc1412096293369");
        return trans;
    }
    
    public TransactionRequest getPrimaryTransactionForSecondary() {
        TransactionRequest request=new TransactionRequest();
        request.setAmount("1100");
        request.setCurrency("USD");
        request.setPaymentMethod("credit_card");
        request.setTransactionType(TransactionType.AUTHORIZE.getValue());
        Card card=new Card();
        card.setCvv("123");
        card.setExpiryDt("1220");
        card.setName("Test data ");
        card.setType("visa");
        //card.setNumber("4788250000028291");
        card.setNumber("4012000033330026");
        request.setCard(card);
        Address address=new Address();
        request.setBilling(address);
        address.setState("NY");
        address.setAddressLine1("sss");
        address.setZip("11747");
        address.setCountry("US");
		
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
        request.setTransactionType(TransactionType.AUTHORIZE.getValue());
        
        Card card=new Card();
        card.setCvv("123");
        card.setExpiryDt("1220");
        card.setName("Test data ");
        card.setType("visa");
        card.setNumber("4012000033330026");
        if(cardtypeSecondary == CardType.CARD_VISA)
        {
        	card.setType("visa");
        	card.setNumber("4012000033330026");
        }
        if(cardtypeSecondary == CardType.CARD_MASTERCARD)
        {
        	card.setType("mastercard");
        	card.setNumber("5424180279791732");
        }
        if(cardtypeSecondary == CardType.CARD_DISCOVER)
        {
        	card.setType("discover");
        	card.setNumber("6510000000001248");
        }
        if(cardtypeSecondary == CardType.CARD_AMEX)
        {
        	card.setType("American Express");
        	card.setNumber("373953192351004");
        	card.setCvv("1234");
        }
        
        //card.setNumber("4788250000028291");
        
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
    
    private TransactionRequest getSecondaryTransactionModified() {
        TransactionRequest trans=new TransactionRequest();
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
        return trans;
    }

    public TransactionRequest getPrimaryTransactionForTransType() {
    	TransactionRequest request=new TransactionRequest();
    	TransactionDataProviderL3 tdpl3 = new TransactionDataProviderL3();
    	
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
        request.setTransactionType(TransactionType.AUTHORIZE.getValue());
        
        Card card=new Card();
        card.setCvv("123");
        card.setExpiryDt("1220");
        card.setName("Test data ");
        card.setType("visa");
        card.setNumber("4012000033330026");
        if(cardtypeSecondary == CardType.CARD_VISA)
        {
        	card.setType("visa");
        	card.setNumber("4012000033330026");
        }
        if(cardtypeSecondary == CardType.CARD_MASTERCARD)
        {
        	card.setType("mastercard");
        	card.setNumber("5424180279791732");
        }
        if(cardtypeSecondary == CardType.CARD_DISCOVER)
        {
        	card.setType("discover");
        	card.setNumber("6510000000001248");
        }
        if(cardtypeSecondary == CardType.CARD_AMEX)
        {
        	card.setType("American Express");
        	card.setNumber("373953192351004");
        	card.setCvv("1234");
        }
        
        //card.setNumber("4788250000028291");
        
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
        	giftcard.setCc_number(null);
        	giftcard.setCredit_card_type("Gift");
        	request.setGiftcard(giftcard);
        	
        	request.setCard(null);
        	request.setBilling(null);
        	//request.setTransactionType(TransactionType.PURCHASE.getValue());
        }
        
        // category specific
        if(category == TransactionCategory.CATEGORY_AVS)
        {
        	Phone phone  = new Phone();
        	phone.setNumber(tdpl3.phone_number);
        	phone.setType(tdpl3.phone_type);
        	address.setPhone(phone);
        	request.setBilling(address);
        }
        
        
        // category specific
        if(category == TransactionCategory.CATEGORY_3DS)
        {
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
        	threeDST.setEciIndicator(tdpl3.eci_indicator);
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
        	l2.setCustomer_ref(tdpl3.level2_customer_ref);
        	l2.setTax1_amount(tdpl3.level2_tax1_amount);
        	l2.setTax1_number(tdpl3.level2_tax1_number);
        	l2.setTax2_amount(tdpl3.level2_tax2_amount);
        	l2.setTax2_number(tdpl3.level2_tax2_number);
        	
        	request.setLevel2(l2);
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
        	request.setLevel3(l3);
        	
        	Level2 l2 = new Level2();
        	l2.setCustomer_ref(tdpl3.level2_customer_ref);
        	l2.setTax1_amount(tdpl3.level2_tax1_amount);
        	l2.setTax1_number(tdpl3.level2_tax1_number);
        	l2.setTax2_amount(tdpl3.level2_tax2_amount);
        	l2.setTax2_number(tdpl3.level2_tax2_number);
        	
        	request.setLevel2(l2);
        	
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
        	request.setEciindicator(tdpl3.eci_indicator);
        	request.setSplitShipment(tdpl3.split_shipment);
        	
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
        	/*
        	token.getToken_data().setCardholder_name(tdpl3.td_cardholder_name);
        	token.getToken_data().setCvv(tdpl3.td_cvv);
        	token.getToken_data().setExp_date(tdpl3.td_exp_date);
        	token.getToken_data().setType(tdpl3.td_type);
        	token.getToken_data().setValue(tdpl3.td_value);
        	token.setToken_data(token.getToken_data());
        	*/
        	token.getTokenData().setName(tdpl3.td_cardholder_name);
        	token.getTokenData().setCvv(tdpl3.td_cvv);
        	token.getTokenData().setExpiryDt(tdpl3.td_exp_date);
        	token.getTokenData().setType(tdpl3.td_type);
        	token.getTokenData().setValue(tdpl3.td_value);
        	token.setTokenData( new Transarmor());//(Transarmor)token.getToken_data());
        	//request.setTo(card);
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
        	
        	//trans.setLineitems(lineitems);
        	trans.setReferenceNo(tdpl3.referenceNo);
        	
        }
        
        if(category == TransactionCategory.CATEGORY_NAKEDREFUNDS)
        {
        	//request.setDescriptor(descriptor);
        	trans.setEciindicator(tdpl3.eci_indicator);
        }
        if(category == TransactionCategory.CATEGORY_NAKEDVOIDS)
        {
        	//request.setDescriptor(descriptor);
        	trans.setEciindicator(tdpl3.eci_indicator);
        	//trans.setBilling(null);
        }
        if(category == TransactionCategory.CATEGORY_SPLITSHIPMENT)
        {
        	//request.setDescriptor(descriptor);
        	//trans.setEciindicator(tdpl3.eci_indicator);
        	trans.setSplitShipment(tdpl3.split_shipment);
        }
        if(category == TransactionCategory.CATEGORY_CVV2)
        {
        	//card.setCvv2(tdpl3.Cvv2);
        	//trans.setCard(card);
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