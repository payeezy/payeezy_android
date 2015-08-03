package com.example.payeezyclient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

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
import com.firstdata.firstapi.client.domain.v2.Token_data;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;
import com.firstdata.firstapi.client.domain.v2.Transarmor;
import com.firstdata.firstapi.client.domain.v2.UserTransactionResponse;
import com.firstdata.firstapi.client.domain.v2.ValueLinkCard;

@SuppressLint("DefaultLocale")
public class EnvCat {

	public Context context = null;
	TransactionDataProvider tdpbasic = new TransactionDataProvider();
	TransactionDataProviderBase tdpbase  = new TransactionDataProviderBase();
	TransactionDataProviderMC tdpmc  = new TransactionDataProviderMC();
	TransactionDataProviderVisa tdpvisa  = new TransactionDataProviderVisa();
	TransactionDataProviderD tdpdis  = new TransactionDataProviderD();
	TransactionDataProviderAmex tdpamex  = new TransactionDataProviderAmex();
	TransactionDataProviderVL tdpvl  = new TransactionDataProviderVL();
	TransactionDataProviderTC tdptc  = new TransactionDataProviderTC();
	CardType cardtype = CardType.CARD_VISA;
	CardType cardtypeSecondary = CardType.CARD_VISA;
	TransactionCategory category = TransactionCategory.CATEGORY_AVS;
	
	public EnvCat(Context pcontext)
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
	public String statusString = "";
	public String splitter = "~~~~~~~~";
	
	/************************************public*****************/
	
	//public String ProcessPayment(PZToken pztoken, TransactionRequest request)
	public String ProcessPayment(String appId, String secretId, String token, String url, TransactionRequest request)
	{
		String result = "";
		try
		{
			FirstAPIClientV2Helper clientHelper = new  FirstAPIClientV2Helper();
			clientHelper.setAppId(appId);
			clientHelper.setSecuredSecret(secretId);
			clientHelper.setToken(token);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			/*
			TransactionRequest request=new TransactionRequest();
	        
	        request.setAmount("1100");
	        request.setCurrency("USD");
	        request.setPaymentMethod("credit_card");
	        
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
	        */
			TransactionResponse response = clientHelper.purchaseTransaction(request);
			result = ((UserTransactionResponse)(response)).getResponseString();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public String ProcessRecurringPayment()
	{
		return CallAuthorizeVisaRecurring();
	}
	
	
	
	public void Authorize()
	{
		
	}
	
	public void Purchase()
	{
		
	}
	
	public void Capture()
	{
		
	}
	
	public void Refund()
	{
		
	}
	
	public void Void()
	{
		
	}
	
	
	
	/*********************************************************/
	
    
    public void CallAuthorizeCards()
    {
    	CallAuthorizeVisa();
    	CallAuthorizeMC();
    	CallAuthorizeDiscover();
    	CallAuthorizeAmex();
    }
    
    public void CallPurchaseCards()
    {
    	CallPurchaseVisa();
    	CallPurchaseMC();
    	CallPurchaseDiscover();
    	CallPurchaseAmex();
    }
    
    public void CallCaptureCards()
    {
    	CallCaptureVisa();
    	CallCaptureMC();
    	CallCaptureDiscover();
    	CallCaptureAmex();
    }
    
    public void CallRefundCards()
    {
    	CallRefundVisa();
    	CallRefundMC();
    	CallRefundDiscover();
    	CallRefundAmex();
    }
    
    public void CallVoidCards()
    {
    	CallVoidVisa();
    	CallVoidMC();
    	CallVoidDiscover();
    	CallVoidAmex();
    }
    
    public void CallTelecheckCards()
    {
    	CallVoidVisa();
    	CallVoidMC();
    	CallVoidDiscover();
    	CallVoidAmex();
    }
    
    public void CallValueLinkCards()
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
    
    public void CallAuthorizeCardsAVS()
    {
    	CallAuthorizeVisaAVS();
    	CallPurchaseVisaAVS();
    	CallCaptureVisaAVS();
    	CallRefundVisaAVS();
    	CallVoidVisaAVS();
    }
    
    public void CallAuthorizeCardsLevel2()
    {
    	CallAuthorizeVisaLevel2();
    	CallPurchaseVisaLevel2();
    	CallCaptureVisaLevel2();
    	CallRefundVisaLevel2();
    	CallVoidVisaLevel2();
    }
    public void CallAuthorizeCardsLevel3()
    {
    	CallAuthorizeVisaLevel3();
    	CallPurchaseVisaLevel3();
    	CallCaptureVisaLevel3();
    	CallRefundVisaLevel3();
    	CallVoidVisaLevel3();
    }
    
    public void CallAuthorizeCardsNakedRefunds()
    {
    	CallRefundVisaNakedRefunds();
    	//CallVoidVisaNakedVoids();
    	//CallAuthorizeVisaNakedAuthorize();
    	//CallPurchaseVisaNakedPurchase();
    	//CallCaptureVisaNakedCapture();
    	
    }
    public void CallAuthorizeCardsNakedVoid()
    {
    	//CallRefundVisaNakedRefunds();
    	CallVoidVisaNakedVoids();
    	//CallAuthorizeVisaNakedAuthorize();
    	//CallPurchaseVisaNakedPurchase();
    	//CallCaptureVisaNakedCapture();
    	
    }
    
    public void CallAuthorizeCardsTransarmor()
    {
    	CallAuthorizeVisaTransarmor();
    	CallPurchaseVisaTransarmor();
    	CallCaptureVisaTransarmor();
    	CallRefundVisaTransarmor();
    	CallVoidVisaTransarmor();
    }
    
    public void CallAuthorizeCardsSplitShipments()
    {
    	CallAuthorizeVisaSplitShipments();
    	//CallPurchaseVisaSplitShipments();
    	//CallCaptureVisaSplitShipments();
    	//CallRefundVisaSplitShipments();
    	//CallVoidVisaSplitShipments();
    }
    
    public void CallAuthorizeCardsSoftDescriptors()
    {
    	CallAuthorizeVisaSoftDescriptors();
    	CallPurchaseVisaSoftDescriptors();
    	CallCaptureVisaSoftDescriptors();
    	CallRefundVisaSoftDescriptors();
    	CallVoidVisaSoftDescriptors();
    }
    

    public void CallAuthorizeCardsPaypal()
    {
    	CallAuthorizeVisaPaypal();
    	CallPurchaseVisaPaypal();
    	CallCaptureVisaPaypal();
    	CallRefundVisaPaypal();
    	CallVoidVisaPaypal();
    }

    public void CallAuthorizeCards3DS()
    {
    	CallAuthorizeVisa3DS();
    	CallPurchaseVisa3DS();
    	CallCaptureVisa3DS();
    	CallRefundVisa3DS();
    	CallVoidVisa3DS();
    }
    

    public void CallAuthorizeCardsRecurring()
    {
    	CallAuthorizeVisaRecurring();
    	CallAuthorizeMCRecurring();
    	CallAuthorizeDiscoverRecurring();
    	CallAuthorizeAmexRecurring();
    	
    }
    
    
    
    public void CallAuthorize()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			//TransactionDataProviderBase tdpbase = new TransactionDataProviderMC();
			//TransactionDataProviderMC tdpmc = (TransactionDataProviderMC)tdpbase;
			//TransactionDataProviderMC tdp = (TransactionDataProviderMC)tdpbase;
			
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
			////String responseString = responseObject.toString();
			//TransactionResponse resp = clientHelper.GetTransactionResponse(responseString);
			//UserTransactionResponse uresp = clientHelper.GetTransactionResponse(responseString);
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//System.out.println(resp.getTransactionStatus() );
			//statusString = uresp.getResponseMessage(); //resp.getTransactionStatus() ;
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
	
	public void CallPurchase()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
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
	
	public void CallCapture()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			//https://api-cert.payeezy.com/v1/transactions_id
			//clientHelper.setAppId(TransactionDataProvider.appIdCat);
			//clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			//clientHelper.setToken(TransactionDataProvider.tokenCat);
			//clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallRefund()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
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
	
	public void CallVoid()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
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
	
	public void CallAuthorizeVisa()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			//TransactionDataProviderBase tdpbase = new TransactionDataProviderVisa();
			//TransactionDataProviderMC tdpmc = (TransactionDataProviderMC)tdpbase;
			//TransactionDataProviderVisa tdp = (TransactionDataProviderVisa)tdpbase;
			
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
	
	public void CallAuthorizeMC()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_MASTERCARD;
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			//TransactionDataProviderBase tdpbase = new TransactionDataProviderMC();
			//TransactionDataProviderMC tdpmc = (TransactionDataProviderMC)tdpbase;
			//TransactionDataProviderMC tdp = (TransactionDataProviderMC)tdpbase;
			
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
	
	public void CallAuthorizeDiscover()
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
	
	public void CallAuthorizeAmex()
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
	
	public void CallPurchaseValueLink()
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
	
	public void CallPurchaseTelecheck()
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
	
	public FirstAPIClientV2Helper SetToken(PZToken pztoken, FirstAPIClientV2Helper clientHelper)
	{
		if(pztoken != null)
		{
			if(!pztoken.APIKey.equalsIgnoreCase("") )
			{
				clientHelper.setAppId(TransactionDataProvider.appIdCat);
			}
			if(!pztoken.Secret.equalsIgnoreCase("") )
			{
				clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			}
			if(!pztoken.Token.equalsIgnoreCase("") )
			{
				clientHelper.setToken(TransactionDataProvider.tokenCat);
			}
		}
		return clientHelper;
	}
	
	//public String CallPurchaseVisa(PZToken pztoken)
	public String CallPurchaseVisa()
	{
		String result = "";
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
			result = uresp.getResponseString();
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public void CallPurchaseMC()
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
	
	public void CallPurchaseDiscover()
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
	
	public void CallPurchaseAmex()
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
	
	/*********************************capture****************************/
	
	public void CallCaptureVisa()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallCaptureMC()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_MASTERCARD;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
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
	
	public void CallCaptureDiscover()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_DISCOVER;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
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
	
	public void CallCaptureAmex()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_AMEX;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
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

	/*******************************refund**********************************/
	
	public void CallRefundVisa()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
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
	
	public void CallRefundMC()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_MASTERCARD;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
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
	
	public void CallRefundDiscover()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_DISCOVER;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallRefundAmex()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_AMEX;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
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

	
	/********************************void***************************************/
	
	public void CallVoidVisa()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidMC()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_MASTERCARD;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidDiscover()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_DISCOVER;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidAmex()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_AMEX;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
						
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
			trans.setId(transaction_id);
			TransactionResponse responseObject = clientHelper.voidTransaction(trans);
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

	/******************************telecheck***************************************/
	
	public void CallRefundTelecheck()
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
	
	public void CallVoidTelecheck()
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
	
	public void CallTaggedVoidTelecheck()
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
	
	public void CallTaggedRefundTelecheck()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_TELECHECK;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
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
	
	
	public void CallRefundValueLink()
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
	
	public void CallVoidValueLink()
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
	
	public void CallCashoutValueLink()
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
		cardtype = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
	}
	
	public void CallReloadValueLink()
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
			Object responseObject = clientHelper.ReloadTransaction(trans);
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
		cardtype = CardType.CARD_VISA;
		category = TransactionCategory.CATEGORY_NONE;
	}
	
	public void CallPartialPurchaseValueLink()
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
	
	public void CallActivationValueLink()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VALUELINK;
			category = TransactionCategory.CATEGORY_VALUELINK;
			
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
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
	
	public void CallDeactivationValueLink()
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
	
	public void CallBalanceEnquiryValueLink()
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

	public void CallSplitPurchaseValueLink()
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
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = statusString + uresp.getResponseString() + splitter;
			String resString = uresp.getResponseString();
			int startIndex = resString.indexOf("split_tender_id");
			startIndex = resString.indexOf("=", startIndex+1);
			int endIndex = resString.indexOf(",", startIndex);
			String splitTenderId = resString.substring(startIndex, endIndex);  
			splitTenderId = splitTenderId.replace(" ", "");
			splitTenderId = splitTenderId.replace(":", "");
			splitTenderId = splitTenderId.replace("=", "");
			
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			cardtypeSecondary = CardType.CARD_VALUELINK;
			//void
			TransactionRequest trans2 = getPrimaryTransactionForTransType();
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
	
	public void CallAuthorizeVisaAVS()
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
	
	public void CallPurchaseVisaAVS()
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
	
	public void CallCaptureVisaAVS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallRefundVisaAVS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidVisaAVS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallAuthorizeVisaLevel2()
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

	public void CallPurchaseVisaLevel2()
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
	
	public void CallCaptureVisaLevel2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallRefundVisaLevel2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidVisaLevel2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallAuthorizeVisaLevel3()
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
			//Object responseObject = clientHelper.purchaseTransaction(trans);
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
	

	public void CallPurchaseVisaLevel3()
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
	
	public void CallCaptureVisaLevel3()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallRefundVisaLevel3()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidVisaLevel3()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallAuthorizeVisaTransarmor()
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


	public void CallPurchaseVisaTransarmor()
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
	
	public void CallCaptureVisaTransarmor()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallRefundVisaTransarmor()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidVisaTransarmor()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	public void CallAuthorizeVisaSoftDescriptors()
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
	
	public void CallPurchaseVisaSoftDescriptors()
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
	
	public void CallCaptureVisaSoftDescriptors()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallRefundVisaSoftDescriptors()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidVisaSoftDescriptors()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	public void CallAuthorizeVisaNakedAuthorize()
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
	
	public void CallPurchaseVisaNakedPurchase()
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
	
	public void CallCaptureVisaNakedCapture()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public String CallRefundVisaNakedRefunds()
	{
		String result = "";
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_NAKEDREFUNDS;
			TransactionRequest transReq = getPrimaryTransactionForTransType();
			//TransactionResponse responseObject1 = clientHelper.purchaseTransaction(transReq);
			TransactionResponse responseObject1 = clientHelper.nakedRefundTransaction(transReq);
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
			
			/*
			category = TransactionCategory.CATEGORY_NAKEDREFUNDS;
			TransactionRequest trans = getSecondaryTransactionForTransType();
			//TransactionRequest trans = getSecondaryTransaction();
			trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			*/
			TransactionResponse responseObject = responseObject1 ;
			System.out.println("Response : " + responseObject.toString());
			//String responseString = responseObject.toString();
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = uresp.getResponseString(); 
			statusString = statusString + uresp.getResponseString() + splitter;
			result = statusString;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public String CallVoidVisaNakedVoids()
	{
		String result = "";
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = TransactionCategory.CATEGORY_NAKEDVOIDS;
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
			
			
			//TransactionRequest trans = getSecondaryTransaction();
			TransactionRequest trans = getPrimaryTransactionForTransType();
			//TransactionResponse responseObject1 = clientHelper.authorizeTransaction(transReq);
			//TransactionRequest trans = getSecondaryTransactionForTransType();
			//trans.setTransactionTag(transaction_tid);
			trans.setTransactionId(transaction_id);
			//trans.setId(transaction_id);
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
			result = statusString;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(uresp.getTransactionStatus() );
			}
			
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public void CallPartialPurchaseVisaNakedRefunds()
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
	
	
	/*************************split shipments********************/
	public void CallAuthorizeVisaSplitShipments()
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
				Object responseObject2 = clientHelper.SplitTransaction(trans2);
				System.out.println("Response : " + responseObject2.toString());
				
				UserTransactionResponse uresp2 = (UserTransactionResponse)(responseObject2);
				TransactionResponse resp2 = uresp2; //uresp.getBody();
				System.out.println(uresp2.getResponseMessage() );
				statusString = statusString + uresp2.getResponseString() + splitter;
				
				String resString2 = uresp2.getResponseString();
				//check if last of the split
				int start = resString2.indexOf("split_shipment");
				start = resString2.indexOf("=", start +1);
				int end = resString2.indexOf(",", start);
				String splitShip = resString2.substring(start , end);  
				splitShip = splitShip.replace(" ", "");
				splitShip = splitShip.replace(":", "");
				splitShip = splitShip.replace("=", "");
				
				String[] completedSplits = splitShip.split("/");
				int countComplete = Integer.parseInt(completedSplits[0]);
				int totalComplete = Integer.parseInt(completedSplits[1]);
				
				if(resp2.getTransactionStatus() == "approved")
				{
					System.out.println(uresp2.getTransactionStatus() );
				}
				if((totalComplete >= i) ||(countComplete>=totalComplete))
				{
					break;
				}
			}
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	public void CallPurchaseVisaSplitShipments()
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
	
	public void CallCaptureVisaSplitShipments()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallRefundVisaSplitShipments()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidVisaSplitShipments()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	public void CallAuthorizeVisaPaypal()
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
	
	public void CallPurchaseVisaPaypal()
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
	
	public void CallCaptureVisaPaypal()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallRefundVisaPaypal()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidVisaPaypal()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	public void CallAuthorizeVisa3DS()
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
	
	public void CallPurchaseVisa3DS()
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
	
	public void CallCaptureVisa3DS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallRefundVisa3DS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallVoidVisa3DS()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public String CallAuthorizeVisaRecurring()
	{
		String result = "";
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
			result = uresp.getResponseString();
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public void CallAuthorizeMCRecurring()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_MASTERCARD;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallAuthorizeDiscoverRecurring()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_DISCOVER;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	
	public void CallAuthorizeAmexRecurring()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_AMEX;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
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
	public void CallAuthorizeVisaCvv2()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			TransactionDataProviderBase tdpbase = new TransactionDataProviderVisa();
			//TransactionDataProviderMC tdpmc = (TransactionDataProviderMC)tdpbase;
			TransactionDataProviderVisa tdp = (TransactionDataProviderVisa)tdpbase;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = category.CATEGORY_CVV2;
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
	
	public void CallPurchaseVisaCvv2()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			cardtype = CardType.CARD_VISA;
			
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			TransactionDataProviderBase tdpbase = new TransactionDataProviderVisa();
			//TransactionDataProviderMC tdpmc = (TransactionDataProviderMC)tdpbase;
			TransactionDataProviderVisa tdp = (TransactionDataProviderVisa)tdpbase;
			
			clientHelper.setAppId(TransactionDataProvider.appIdCat);
			clientHelper.setSecuredSecret(TransactionDataProvider.secureIdCat);
			clientHelper.setToken(TransactionDataProvider.tokenCat);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			category = category.CATEGORY_CVV2;
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
	
	public void CallCaptureVisaCvv2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			category = category.CATEGORY_CVV2;
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
			
			category = category.CATEGORY_CVV2;
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
	
	public void CallRefundVisaCvv2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = category.CATEGORY_CVV2;
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
			
			category = category.CATEGORY_CVV2;
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
	
	public void CallVoidVisaCvv2()
	{
		try
		{
			cardtypeSecondary = CardType.CARD_VISA;
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			//String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			//String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			//String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			//String url = "https://api-cat.payeezy.com/v1";
			
			clientHelper.setAppId(TransactionDataProvider.secAppId);
			clientHelper.setSecuredSecret(TransactionDataProvider.secSecureId);
			clientHelper.setToken(TransactionDataProvider.secToken);
			clientHelper.setUrl(TransactionDataProvider.urlCat);
			
			//TransactionRequest transReq = getPrimaryTransactionForSecondaryModified();
			category = category.CATEGORY_CVV2;
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
			category = category.CATEGORY_CVV2;
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
	
	public void CallZeroDollarAuthorizeVisa()
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
	
	public void CallZeroDollarAuthorizeMC()
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
	
	public void CallZeroDollarAuthorizeDiscover()
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
	
	public void CallZeroDollarAuthorizeAmex()
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
	
	/******************DirectAPI***********************/
	
	public void CallDirectAPI()
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
	
	
	/*
	@SuppressLint("DefaultLocale")
	public void CallDirectAPI2()
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
		String url = "https://api-cat.payeezy.com/v1";
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

	@SuppressWarnings("unused")
	public void CallDirectAPI3()
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
		
		String appUrlCert = "https://api-cert.payeezy.com/v1";
		String tokenid = "";
		String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
		String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
		String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
		String appUrl = "http://api-cat.payeezy.com/v1";
		String apiType = "transactions";
		String transId = ""; 
		String corelationid = ""; 
		String splitTenderId = ""; 
		String transactiontag = ""; 
		String extractedTransactionId = "";
		String extractedTransactionTid = "";
		
		String merchantid = "";
		String trtoken = "";
		
		CustomFileReader2 reader = new CustomFileReader2(context);
		String[][] data = reader.GetFileContents("newTestPayloads.xls");
		for(int i=1;i<data.length;i++)
		{
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
			
			boolean tMod = false;
			if (transactionType == "authorize")
			{
				tMod = true;
			}
			if (transactionType != "authorize")
			{
				tMod = true;
			}
					
			if( !((transactionType != "authorize") || (transactionType != "purchase") 
					|| (transactionType != "activate") || (transactionType != "deactivate") 
					|| (transactionType != "balanceenquiry") || (transactionType != "split")) )
			{
				tMod = true;
				//secondary transaction
				appUrl = appUrl + "/" + tokenid;
				/*
				//appUrl = appUrl + "/{" + transId + "}";
				appUrl = appUrl + "/{" + tokenid + "}";
				if(jsonString.toLowerCase().contains("transaction_tag"))
				{
					int startIndex = jsonString.indexOf("transaction_tag");
					startIndex = jsonString.indexOf("=", startIndex+1);
					int endIndex = jsonString.indexOf(",", startIndex);
					String current_tid = jsonString.substring(startIndex+1, endIndex);
					jsonString.replace(current_tid, extractedTransactionTid);
					System.out.println(extractedTransactionTid);
				}
				*/
			}
			
			String response =  requestor.MakeRequest(jsonString, appUrl, tokenid, appId, secureId, token, transactionType, merchantid, trtoken);
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
		}
		
	}
	
	
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
	public TransactionRequest getPrimaryTransaction() {
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
     
    public TransactionRequest getSecondaryTransaction() {
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
    
    public TransactionRequest getSecondaryTransactionModified() {
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
        	request.setLineitems(lineitems);
        	request.setReferenceNo(tdpl3.referenceNo);
        	request.setBilling(null);
        	
        }
        
        if((category == TransactionCategory.CATEGORY_NAKEDREFUNDS )||(category == TransactionCategory.CATEGORY_NAKEDVOIDS ))
        {
        	//request.setDescriptor(descriptor);
        	request.setEciindicator(tdpl3.eci_indicator);
        	card.setType(tdpl3.nakedrefundcardtypecat);
        	card.setNumber(tdpl3.nakedrefundcardnumbercat);
        	card.setName(tdpl3.nakedrefundcardholdernamecat);
        	card.setExpiryDt(tdpl3.nakedrefundcardexpdatecat);
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
        
        return request;
    }
    
    public TransactionRequest getSecondaryTransactionForTransType() {
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
        	
        	trans.setLineitems(lineitems);
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
        
        return trans;
    }

}