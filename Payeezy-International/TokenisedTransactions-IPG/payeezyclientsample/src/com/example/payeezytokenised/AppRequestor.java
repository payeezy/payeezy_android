package com.example.payeezytokenised;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AppRequestor {

	public AppRequestor()
	{
		
	}
	
	public String MakeRequest(String jsonString, String appUrl, String tokenid, String appId, String token, String secureId, String transactionType, String merchantid, String trtoken)
	{
		String responseString = "";
		try {
			//String access_token = "";

	        /*URL url = new URL("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet"
	                + "&key=AIzaSyAhONZJpMCBqCfQjFUj21cR2klf6JWbVSo"
	                + "&access_token=" + access_token);*/
			appUrl = appUrl.replace("http:", "https:");
			URL url = new URL(appUrl );
			//HttpsURLConnection url = new HttpsURLConnection( new URL(appUrl) );
			/*if((tokenid != null) && (tokenid.length() != 0) )
			{
				url = new URL(appUrl + "/" + tokenid);
			}*/
			
	        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setUseCaches(false);
	        conn.setRequestMethod("POST");
	        //conn.setChunkedStreamingMode(2048);
	        conn.setRequestProperty("Content-Type", "application/json");
	        
	        conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
	        conn.setRequestProperty("Accept","*/*");
	        //conn.setRequestProperty("apikey", TransactionDataProvider.appId);
	        //conn.setRequestProperty("Authorization", TransactionDataProvider.secureId);
	        //conn.setRequestProperty("token", TransactionDataProvider.token);
	        
	        conn.setRequestProperty("apikey", appId);
	        conn.setRequestProperty("Authorization", secureId);
	        conn.setRequestProperty("token", token);
	        
	        if( url.toString().endsWith("tokens"))
	        {
	        	conn.setRequestProperty("trtoken" , merchantid);
	        }
	        //if( (transactionType ==null) || (transactionType == "") )
	        //{
	        	//conn.setRequestProperty("x-merchant-identifier" , merchantid);
	        //}
	        
	        /*if( !((transactionType.toLowerCase().equalsIgnoreCase("authorize")) &&
					(transactionType.toLowerCase().equalsIgnoreCase("purchase")) &&
					(transactionType.toLowerCase().equalsIgnoreCase("activate")) &&
					(transactionType.toLowerCase().equalsIgnoreCase("deactivate")) &&
					(transactionType.toLowerCase().equalsIgnoreCase("balanceenquiry")) &&
					(transactionType.toLowerCase().equalsIgnoreCase("split")) 
					))
			{*/
	        if( !((transactionType.toLowerCase() != "authorize") ||
					(transactionType.toLowerCase() != "purchase") ||
					(transactionType.toLowerCase() != "activate") ||
					(transactionType.toLowerCase() != "deactivate") ||
					(transactionType.toLowerCase() != "balanceenquiry") ||
					(transactionType.toLowerCase()  != "split") 
					))
			{
	        	conn.setRequestProperty("transactionid", tokenid);
			}
	        
	        
	        //String input = "{ \"snippet\": {\"playlistId\": \"WL\",\"resourceId\": {\"videoId\": \""+videoId+"\",\"kind\": \"youtube#video\"},\"position\": 0}}";
	        String input = jsonString;

	        OutputStream os = conn.getOutputStream();
	        os.write(input.getBytes());
	        os.flush();

	        /*
	        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                + conn.getResponseCode());
	        }*/

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));

	        String output;
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	            responseString = responseString + output;
	        }
	        
	        conn.disconnect();

	      } catch (MalformedURLException e) {

	        e.printStackTrace();

	      } catch (IOException e) {

	        e.printStackTrace();

	     }
		 catch (Exception e) {

	        e.printStackTrace();

	     }
		return responseString;
	}
	
	
	public String MakeRequest(String jsonString, String appUrl, String tokenid)
	{
		String responseString = "";
		try {
			//String access_token = "";

	        /*URL url = new URL("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet"
	                + "&key=AIzaSyAhONZJpMCBqCfQjFUj21cR2klf6JWbVSo"
	                + "&access_token=" + access_token);*/
			appUrl = appUrl.replace("http:", "https:");
			URL url = new URL(appUrl );
			//HttpsURLConnection url = new HttpsURLConnection( new URL(appUrl) );
			if((tokenid != null) && (tokenid.length() != 0) )
			{
				url = new URL(appUrl + "/" + tokenid);
			}
			
	        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setUseCaches(false);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");

	        conn.setRequestProperty("apikey", TransactionDataProvider.appId);
	        conn.setRequestProperty("Authorization", TransactionDataProvider.secureId);
	        conn.setRequestProperty("token", TransactionDataProvider.token);
	        
	        
	        //String input = "{ \"snippet\": {\"playlistId\": \"WL\",\"resourceId\": {\"videoId\": \""+videoId+"\",\"kind\": \"youtube#video\"},\"position\": 0}}";
	        String input = jsonString;

	        OutputStream os = conn.getOutputStream();
	        os.write(input.getBytes());
	        os.flush();

	        /*
	        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                + conn.getResponseCode());
	        }*/

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));

	        String output;
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	            responseString = responseString + output;
	        }
	        
	        conn.disconnect();

	      } catch (MalformedURLException e) {

	        e.printStackTrace();

	      } catch (IOException e) {

	        e.printStackTrace();

	     }
		 catch (Exception e) {

	        e.printStackTrace();

	     }
		return responseString;
	}

	public String MakeGetRequest(String[][] params,String jsonString, String appUrl, String tokenid, String appId, String token, String secureId, String transactionType, String merchantid, String trtoken)
	{
		String responseString = "";
		try 
		{
			//String access_token = "";

	        /*URL url = new URL("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet"
	                + "&key=AIzaSyAhONZJpMCBqCfQjFUj21cR2klf6JWbVSo"
	                + "&access_token=" + access_token);*/
			if(params.length>0)
			{
				appUrl = appUrl + "?";
			}
			for(int i=0;i<params.length;i++)
			{
				appUrl = appUrl + params[i][0] + "=" + params[i][0] + "&";  
			}
			appUrl = appUrl.replace("http:", "https:");
			URL url =  new URL(appUrl );
			
			//HttpsURLConnection url = new HttpsURLConnection( new URL(appUrl) );
			/*if((tokenid != null) && (tokenid.length() != 0) )
			{
				url = new URL(appUrl + "/" + tokenid);
			}*/
			
	        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setUseCaches(false);
	        conn.setRequestMethod("GET");
	        //conn.setChunkedStreamingMode(2048);
	        conn.setRequestProperty("Content-Type", "application/json");
	        
	        conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
	        //conn.setRequestProperty("Accept","*/*");
	        conn.setRequestProperty("Accept","application/json");
	        //conn.setRequestProperty("apikey", TransactionDataProvider.appId);
	        //conn.setRequestProperty("Authorization", TransactionDataProvider.secureId);
	        //conn.setRequestProperty("token", TransactionDataProvider.token);
	        
	        conn.setRequestProperty("apikey", appId);
	        conn.setRequestProperty("Authorization", secureId);
	        conn.setRequestProperty("token", token);
	        
	        if( url.toString().endsWith("tokens"))
	        {
	        	conn.setRequestProperty("trtoken" , merchantid);
	        }
	        //if( (transactionType ==null) || (transactionType == "") )
	        if(url.toString().contains("?"))
	        {
	        	// GET call
	        	// requires merchant token
	        	conn.setRequestProperty("x-merchant-identifier" , merchantid);
	        }
	        
	        if( !((transactionType.toLowerCase() != "authorize") ||
					(transactionType.toLowerCase() != "purchase") ||
					(transactionType.toLowerCase() != "activate") ||
					(transactionType.toLowerCase() != "deactivate") ||
					(transactionType.toLowerCase() != "balanceenquiry") ||
					(transactionType.toLowerCase()  != "split") 
					))
			{
	        	conn.setRequestProperty("transactionid", tokenid);
			}
	        
	        
	        //String input = "{ \"snippet\": {\"playlistId\": \"WL\",\"resourceId\": {\"videoId\": \""+videoId+"\",\"kind\": \"youtube#video\"},\"position\": 0}}";
	        String input = jsonString;

	        OutputStream os = conn.getOutputStream();
	        os.write(input.getBytes());
	        os.flush();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));

	        String output;
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	            responseString = responseString + output;
	        }
	        
	        conn.disconnect();

	      } catch (MalformedURLException e) {

	        e.printStackTrace();

	      } catch (IOException e) {

	        e.printStackTrace();

	     }
		 catch (Exception e) {

	        e.printStackTrace();

	     }
		return responseString;
	}
	
	
	@SuppressWarnings("unused")
	private boolean typeof(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
