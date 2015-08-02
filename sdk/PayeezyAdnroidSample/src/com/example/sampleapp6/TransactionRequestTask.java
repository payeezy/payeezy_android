package com.example.sampleapp6;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.firstdata.firstapi.client.FirstAPIClientV2Helper;
import com.firstdata.firstapi.client.domain.TransactionType;
import com.firstdata.firstapi.client.domain.v2.Address;
import com.firstdata.firstapi.client.domain.v2.Card;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;
import com.firstdata.firstapi.client.domain.v2.UserTransactionResponse;

public class TransactionRequestTask extends AsyncTask<String, String, String>{

	private Context context = null; 
	public TransactionRequestTask(Context pcontext)
	{
		context = pcontext;
	}
	private String statusString = "";
	private String filePathString = "";
	public void SetFilePath(String path)
	{
		filePathString = path;
	}
	
	
	
    @Override
    protected String doInBackground(String... uri) {
    	TransactionManager transactionManager = new TransactionManager(context);
    	transactionManager.SetFilePath(filePathString);
    	String transType = uri[0].toLowerCase(); 
    	if(transType.toLowerCase() == "all")
    	{
    		//CallAuthorize();
    		//transactionManager.ExecuteAll();
    		transactionManager.ExecuteTransactions();
    		return "all";
    	}
    	if(transType.toLowerCase()  == "authorize")
    	{
    		//CallAuthorize();
    		transactionManager.ExecuteAuthorize();
    		return "authorize";
    	}
    	if(transType.toLowerCase() == "purchase")
    	{
    		//CallPurchase();
    		transactionManager.ExecutePurchase();
    		return "Purchase";
    	}
    	if(transType.toLowerCase() == "capture")
    	{
    		//CallCapture();
    		transactionManager.ExecuteCapture();
    		return "capture";
    	}
    	if(transType.toLowerCase() == "refund")
    	{
    		//CallRefund();
    		transactionManager.ExecuteRefund();
    		return "refund";
    	}
    	if(transType.toLowerCase() == "void")
    	{
    		//CallVoid();
    		transactionManager.ExecuteVoid();
    		return "void";
    	}
    	if(transType.toLowerCase() == "executeall")
    	{
    		//CallVoid();
    		ExecuteAll();
    		return "void";
    	}
    	//CallAuthorize();
    	//transactionManager.ExecuteAll();
    	transactionManager.ExecuteTransactions();
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
        Toast.makeText( context, result + ":" + statusString, Toast.LENGTH_SHORT).show();
        System.out.println("Button Authorize Clicked" + result);
    }
    
    private void ExecuteAll()
    {
    	CallAuthorize();
    	CallCapture();
    	CallPurchase();
    	CallRefund();
    	CallVoid();
    	
    }
    
    private void CallAuthorize()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			
			String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(appId);
			clientHelper.setSecuredSecret(secureId);
			clientHelper.setToken(token);
			clientHelper.setUrl(url);
			TransactionRequest trans = getPrimaryTransaction();
			Object responseObject = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			String responseString = responseObject.toString();
			System.out.println(responseString );
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			//statusString = responseString;
			statusString = uresp.getResponseString();
			 //uresp.getResponseMessage(); //resp.getTransactionStatus() ;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(resp.getTransactionStatus() );
			}
			
			/*System.out.println("Response : " + responseObject.toString());
			String responseString = responseObject.toString();
			UserTransactionResponse uresp = clientHelper.GetTransactionResponse(responseString);
			TransactionResponse resp = (TransactionResponse) uresp; // uresp.getBody();
			System.out.println(resp.getTransactionStatus() );
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(resp.getTransactionStatus() );
			}*/
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
			System.out.println(e.getMessage());
		}
	}
	
	private void CallPurchase()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(appId);
			clientHelper.setSecuredSecret(secureId);
			clientHelper.setToken(token);
			clientHelper.setUrl(url);
			TransactionRequest trans = getPrimaryTransaction();
			//TransactionResponse response = clientHelper.purchaseTransaction(trans);
			TransactionResponse responseObject = clientHelper.purchaseTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			String responseString = responseObject.toString();
			System.out.println(responseString );
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = uresp.getResponseMessage(); //resp.getTransactionStatus() ;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(resp.getTransactionStatus() );
			}
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	private void CallCapture()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(appId);
			clientHelper.setSecuredSecret(secureId);
			clientHelper.setToken(token);
			clientHelper.setUrl(url);
			TransactionRequest trans = getSecondaryTransaction();
			//TransactionResponse response = clientHelper.captureTransaction(trans);
			TransactionResponse responseObject = clientHelper.captureTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			String responseString = responseObject.toString();
			System.out.println(responseString );
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = uresp.getResponseMessage(); //resp.getTransactionStatus() ;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(resp.getTransactionStatus() );
			}
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	private void CallRefund()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(appId);
			clientHelper.setSecuredSecret(secureId);
			clientHelper.setToken(token);
			clientHelper.setUrl(url);
			TransactionRequest trans = getSecondaryTransaction();
			//TransactionResponse response = clientHelper.refundTransaction(trans);
			TransactionResponse responseObject = clientHelper.refundTransaction(trans);
			System.out.println("Response : " + responseObject.toString());
			String responseString = responseObject.toString();
			System.out.println(responseString );
			
			UserTransactionResponse uresp = (UserTransactionResponse)(responseObject);
			TransactionResponse resp = uresp; //uresp.getBody();
			System.out.println(uresp.getResponseMessage() );
			statusString = uresp.getResponseMessage(); //resp.getTransactionStatus() ;
			if(resp.getTransactionStatus() == "approved")
			{
				System.out.println(resp.getTransactionStatus() );
			}
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	private void CallVoid()
	{
		try
		{
			FirstAPIClientV2Helper clientHelper = new FirstAPIClientV2Helper();
			String appId = "y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a";
			String secureId = "86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f786fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7";
			String token = "fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6";
			String url = "https://api-cert.payeezy.com/v1";
			
			clientHelper.setAppId(appId);
			clientHelper.setSecuredSecret(secureId);
			clientHelper.setToken(token);
			clientHelper.setUrl(url);
			TransactionRequest trans = getSecondaryTransaction();
			TransactionResponse response = clientHelper.voidTransaction(trans);
			System.out.println(response.toString() );
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			
		}catch(Exception e)
		{
			//Toast.makeText(getApplicationContext(), " Exception :" + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	private TransactionRequest getPrimaryTransaction() {
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
     
    private TransactionRequest getSecondaryTransaction() {
        TransactionRequest trans=new TransactionRequest();
        trans.setPaymentMethod("credit_card");
        trans.setAmount("1100");
        trans.setCurrency("USD");
        trans.setTransactionTag("349990997");
        trans.setTransactionId("07698G");
        return trans;
    }
    
}