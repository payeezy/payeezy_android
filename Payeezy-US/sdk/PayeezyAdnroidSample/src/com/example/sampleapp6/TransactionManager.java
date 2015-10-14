package com.example.sampleapp6;

import android.content.Context;

import com.firstdata.firstapi.client.FirstAPIClientV2Helper;
import com.firstdata.firstapi.client.domain.TransactionType;
import com.firstdata.firstapi.client.domain.v2.Address;
import com.firstdata.firstapi.client.domain.v2.Card;
import com.firstdata.firstapi.client.domain.v2.ThreeDomainSecureToken;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;

public class TransactionManager {

	String filePathString = "";
	Context context = null;
	
	public TransactionManager(Context con)
	{
		context = con;
	}
	
	public void ExecuteAll()
	{
		ExecuteAuthorize();
		ExecutePurchase();
		ExecuteCapture();
		ExecuteRefund();
		ExecuteVoid();
	}
	public void SetFilePath(String path)
	{
		filePathString = path;
	}
	
	public void ExecuteAuthorize()
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
			//TransactionRequest trans = getPrimaryTransaction();
			//TransactionResponse response = clientHelper.authorizeTransaction(trans);
			//System.out.println("Response : " + response.toString());
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//clientHelper.authorizeTransaction(trans);
			
			CustomFileReader2 reader = new CustomFileReader2(context);
			String[][] data = reader.GetFileContents("TestPayloads.xls");
			for(int i=0;i<data.length;i++)
			{
				String transactionType = data[i][0];
				String requestData = data[i][4];
				if(transactionType.toLowerCase().contains("authorize"))
				{
					TransactionRequest request = getPrimaryTransaction(requestData);
					Object responseData = clientHelper.authorizeTransaction(request);
					String strResponse = responseData.toString();
					System.out.println(strResponse);
					int index = strResponse.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				}
				if(transactionType.toLowerCase().contains("purchase"))
				{
					TransactionRequest request = getSecondaryTransaction(requestData);
					Object responseData = clientHelper.purchaseTransaction(request);
					String strResponse = responseData.toString();
					System.out.println(strResponse);
					int index = strResponse.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				}
				if(transactionType.toLowerCase().contains("capture"))
				{
					TransactionRequest request = getSecondaryTransaction(requestData);
					Object responseData = clientHelper.captureTransaction(request);
					String strResponse = responseData.toString();
					System.out.println(strResponse);
					int index = strResponse.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				}
				if(transactionType.toLowerCase().contains("refund"))
				{
					TransactionRequest request = getSecondaryTransaction(requestData);
					Object responseData = clientHelper.refundTransaction(request);
					String strResponse = responseData.toString();
					System.out.println(strResponse);
					int index = strResponse.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				}
				if(transactionType.toLowerCase().startsWith("void"))
				{
					TransactionRequest request = getSecondaryTransaction(requestData);
					Object responseData = clientHelper.voidTransaction(request);
					String strResponse = responseData.toString();
					System.out.println(strResponse);
					int index = strResponse.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				}
				//reader.WriteToFile("RequestDataFile.txt", data[i][0]);
				//reader.WriteToFile("RequestDataFile.txt", data[i][4]);
				//reader.WriteToFile("RequestDataFile.txt", "\n");
				//for(int j =0; j<data[0].length;j++)
				//{
				//	System.out.println(data[i][j]);
				//	reader.WriteToFile("ResultsFile.txt", data[i][j]);
				//}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void ExecutePurchase()
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
			TransactionResponse response = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + response.toString());
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//clientHelper.authorizeTransaction(trans);
			
			CustomFileReader2 reader = new CustomFileReader2(context);
			String[][] data = reader.GetFileContents("TestPayloads.xls");
			for(int i=0;i<data.length;i++)
			{
				String transactionType = data[i][0];
				String requestData = data[i][4];
				if(transactionType.toLowerCase().contains("purchase"))
				{
					TransactionRequest request1 = getPrimaryTransaction(requestData);
					Object responseData = clientHelper.purchaseTransaction(request1);
					String strResponse = responseData.toString();
					System.out.println(strResponse);
					int index = strResponse.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				}
				
				//reader.WriteToFile("RequestDataFile.txt", data[i][0]);
				//reader.WriteToFile("RequestDataFile.txt", data[i][4]);
				//reader.WriteToFile("RequestDataFile.txt", "\n");
				//for(int j =0; j<data[0].length;j++)
				//{
				//	System.out.println(data[i][j]);
				//	reader.WriteToFile("ResultsFile.txt", data[i][j]);
				//}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void ExecuteCapture()
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
			TransactionResponse response = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + response.toString());
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//clientHelper.authorizeTransaction(trans);
			
			CustomFileReader2 reader = new CustomFileReader2(context);
			String[][] data = reader.GetFileContents("TestPayloads.xls");
			for(int i=0;i<data.length;i++)
			{
				String transactionType = data[i][0];
				String requestData = data[i][4];
				if(transactionType.toLowerCase().contains("capture"))
				{
					TransactionRequest request = getSecondaryTransaction(requestData);
					Object responseData = clientHelper.captureTransaction(request);
					String strResponse = responseData.toString();
					System.out.println(strResponse);
					int index = strResponse.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				}
				
				//reader.WriteToFile("RequestDataFile.txt", data[i][0]);
				//reader.WriteToFile("RequestDataFile.txt", data[i][4]);
				//reader.WriteToFile("RequestDataFile.txt", "\n");
				//for(int j =0; j<data[0].length;j++)
				//{
				//	System.out.println(data[i][j]);
				//	reader.WriteToFile("ResultsFile.txt", data[i][j]);
				//}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void ExecuteRefund()
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
			TransactionResponse response = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + response.toString());
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//clientHelper.authorizeTransaction(trans);
			
			CustomFileReader2 reader = new CustomFileReader2(context);
			String[][] data = reader.GetFileContents("TestPayloads.xls");
			for(int i=0;i<data.length;i++)
			{
				String transactionType = data[i][0];
				String requestData = data[i][4];
				if(transactionType.toLowerCase().contains("refund"))
				{
					TransactionRequest request = getSecondaryTransaction(requestData);
					Object responseData = clientHelper.captureTransaction(request);
					String strResponse = responseData.toString();
					System.out.println(strResponse);
					int index = strResponse.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				}
				
				//reader.WriteToFile("RequestDataFile.txt", data[i][0]);
				//reader.WriteToFile("RequestDataFile.txt", data[i][4]);
				//reader.WriteToFile("RequestDataFile.txt", "\n");
				//for(int j =0; j<data[0].length;j++)
				//{
				//	System.out.println(data[i][j]);
				//	reader.WriteToFile("ResultsFile.txt", data[i][j]);
				//}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void ExecuteVoid()
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
			TransactionResponse response = clientHelper.authorizeTransaction(trans);
			System.out.println("Response : " + response.toString());
			//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
			//clientHelper.authorizeTransaction(trans);
			
			CustomFileReader2 reader = new CustomFileReader2(context);
			String[][] data = reader.GetFileContents("TestPayloads.xls");
			for(int i=0;i<data.length;i++)
			{
				String transactionType = data[i][0];
				String requestData = data[i][4];
				if(transactionType.toLowerCase().contains("void"))
				{
					TransactionRequest request = getSecondaryTransaction(requestData);
					Object responseData = clientHelper.captureTransaction(request);
					String strResponse = responseData.toString();
					System.out.println(strResponse);
					int index = strResponse.indexOf("approved");
					if(index >=0)
					{
						System.out.println("Transaction Approved");
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void ExecuteTransactions()
	{
		//TransactionDataProvider tdp = new TransactionDataProvider();
		
		CustomFileReader2 fileReader = new CustomFileReader2(context);
		fileReader.LoadFile(filePathString);
		AppRequestor appRequester = new AppRequestor();
		String[] row = fileReader.GetNextRow();
		
		while(row != null)
		{
			String reqString = row[5];
			String response  = appRequester.MakeRequest(reqString, TransactionDataProvider.url, "");
			fileReader.WriteToFile("results.txt", response + "\n");
		}
	}
	
	private TransactionRequest getPrimaryTransaction(String requestData) 
	{
		
        TransactionRequest request=new TransactionRequest();
        int start = requestData.indexOf("amount");
        start = requestData.indexOf(":",start);
        int end = requestData.indexOf(",", start+1);
        String amount = requestData.substring(start+1, end);
        amount.replace("\"", "");
        amount.replace(" ", "");
        request.setAmount(amount);
        
        start = requestData.indexOf("currency");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String currency = requestData.substring(start+10, end);
        currency.replace("\"", "");
        currency.replace(" ", "");
        request.setCurrency(currency);
        start = requestData.indexOf("method");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String paymentMethod = requestData.substring(start+1, end);
        paymentMethod.replace("\"", "");
        paymentMethod.replace(" ", "");
        request.setPaymentMethod(paymentMethod);
        start = requestData.indexOf("transaction_type");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String transactionType = requestData.substring(start+1, end);
        transactionType.replace("\"", "");
        transactionType.replace(" ", "");
        
        //if(transactionType.toUpperCase().contains("AUTHORIZE"))
        //{
        	request.setTransactionType(TransactionType.AUTHORIZE.getValue());
        //}
        Card card=new Card();
        start = requestData.indexOf("cvv");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String cvv = requestData.substring(start+1, end);
        cvv.replace("\"", "");
        cvv.replace(" ", "");
        
        card.setCvv(cvv);
        start = requestData.indexOf("exp_date");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String expDate = requestData.substring(start+1, end);
        expDate.replace("\"", "");
        expDate.replace(" ", "");
        
        card.setExpiryDt(expDate);
        start = requestData.indexOf("cardholder_name");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String cardholderName = requestData.substring(start+1, end);
        cardholderName.replace("\"", "");
        cardholderName.replace(" ", "");
        
        card.setName(cardholderName);
        start = requestData.indexOf("type");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String type = requestData.substring(start+1, end);
        type.replace("\"", "");
        type.replace(" ", "");
        
        card.setType(type);
        start = requestData.indexOf("card_number");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String cardNumber = requestData.substring(start+1, end);
        cardNumber.replace("\"", "");
        cardNumber.replace(" ", "");
        
        card.setNumber(cardNumber);
        //card.setNumber("4788250000028291");
        request.setCard(card);
        /*Address address=new Address();
        start = requestData.indexOf("address");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String address = requestData.substring(start+1, end);*/
        Address address = new Address();
        
        start = requestData.indexOf("state");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String state = requestData.substring(start+1, end);
        state.replace("\"", "");
        state.replace(" ", "");
        
        address.setState(state);
        start = requestData.indexOf("address_line_1");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String addressLine1 = requestData.substring(start+1, end);
        addressLine1.replace("\"", "");
        addressLine1.replace(" ", "");
        
        address.setAddressLine1(addressLine1);
        start = requestData.indexOf("zip");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String zip = requestData.substring(start+1, end);
        zip.replace("\"", "");
        zip.replace(" ", "");
        address.setZip(zip);
        address.setAddressLine1(addressLine1);
        start = requestData.indexOf("country");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String country = requestData.substring(start+1, end);
        country.replace("\"", "");
        country.replace(" ", "");
        
        address.setCountry(country);
        request.setBilling(address);
        ThreeDomainSecureToken secureToken = new ThreeDomainSecureToken();
        start = requestData.indexOf("token_type");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String tokentype = requestData.substring(start+1, end);
        tokentype.replace("\"", "");
        tokentype.replace(" ", "");
        
        secureToken.setType(tokentype);
        start = requestData.indexOf("token_data");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String tokendata = requestData.substring(start+1, end);
        tokendata.replace("\"", "");
        tokendata.replace(" ", "");
        
        secureToken.setEncryptedData(tokendata);
        request.setThreeDomainSecureToken(secureToken);
        
        return request;
    }
	
	private TransactionRequest getSecondaryTransaction(String requestData) {
        TransactionRequest request =new TransactionRequest();
        int start = requestData.indexOf("transaction_type");
        start = requestData.indexOf(":",start);
        int end = requestData.indexOf(",", start+1);
        String transactionType = requestData.substring(start+1, end);
        transactionType.replace("\"", "");
        transactionType.replace(" ", "");
        
        if(transactionType.toUpperCase().contains("PURCHASE"))
        {
        	request.setTransactionType(TransactionType.PURCHASE.getValue());
        }
        if(transactionType.toUpperCase().contains("CAPTURE"))
        {
        	request.setTransactionType(TransactionType.PURCHASE.getValue());
        }
        if(transactionType.toUpperCase().contains("REFUND"))
        {
        	request.setTransactionType(TransactionType.PURCHASE.getValue());
        }
        if(transactionType.toUpperCase().contains("VOID"))
        {
        	request.setTransactionType(TransactionType.PURCHASE.getValue());
        }
        //Card card=new Card();

        start = requestData.indexOf("amount");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String amount = requestData.substring(start+1, end);
        amount.replace("\"", "");
        amount.replace(" ", "");
        request.setAmount(amount);
        
        start = requestData.indexOf("currency");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String currency = requestData.substring(start+10, end);
        currency.replace("\"", "");
        currency.replace(" ", "");
        
        request.setCurrency(currency);
        start = requestData.indexOf("method");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String paymentMethod = requestData.substring(start+1, end);
        paymentMethod.replace("\"", "");
        paymentMethod.replace(" ", "");
        
        request.setPaymentMethod(paymentMethod);
        
        start = requestData.indexOf("transaction_tag");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String transactiontag = requestData.substring(start+1, end);
        transactiontag.replace("\"", "");
        transactiontag.replace(" ", "");
        
        request.setTransactionTag(transactiontag);
        
        start = requestData.indexOf("transaction_id");
        start = requestData.indexOf(":",start);
        end = requestData.indexOf(",", start+1);
        String transactionid = requestData.substring(start+1, end);
        transactionid.replace("\"", "");
        transactionid.replace(" ", "");
        request.setTransactionId(transactionid);
        
        
        //trans.setPaymentMethod("credit_card");
        //trans.setAmount("0.00");
        //trans.setCurrency("USD");
        //trans.setTransactionTag("349990997");
        //trans.setTransactionId("07698G");
        return request;
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
        card.setNumber("4788250000028291");
        request.setCard(card);
        Address address=new Address();
        request.setBilling(address);
        address.setState("NY");
        address.setAddressLine1("sss");
        address.setZip("11747");
        address.setCountry("US");

        return request;
    }
     
    /*private TransactionRequest getSecondaryTransaction() {
        TransactionRequest trans=new TransactionRequest();
        
        
        trans.setPaymentMethod("credit_card");
        trans.setAmount("0.00");
        trans.setCurrency("USD");
        trans.setTransactionTag("349990997");
        trans.setTransactionId("07698G");
        return trans;
    }*/
    
}
