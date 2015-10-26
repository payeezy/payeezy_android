package com.firstdata.firstapi.client;



import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import android.annotation.SuppressLint;
import com.fasterxml.jackson.core.JsonParser;
import com.firstdata.firstapi.client.domain.TransactionType;
import com.firstdata.firstapi.client.domain.v2.Card;
import com.firstdata.firstapi.client.domain.v2.Token;
import com.firstdata.firstapi.client.domain.v2.TransactionRequest;
import com.firstdata.firstapi.client.domain.v2.TransactionResponse;
import com.firstdata.firstapi.client.domain.v2.Transarmor;
import com.firstdata.firstapi.client.domain.v2.UserTransactionResponse;


@SuppressLint("DefaultLocale")
public class FirstAPIClientV2Helper {
    
    //private static final Logger log=LoggerFactory.getLogger(FirstAPIClientV2Helper.class);
    
  //  @Autowired
    RestTemplate restTemplate;
    
    private String url;
    private String appId;
    private String securedSecret;
    private String token;
    private String trToken;
    private String merchantid ;
    private String urltoken;
private String tokenurl;
	private String jsSecurityKey;

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public FirstAPIClientV2Helper()
    {
    	// Create a new RestTemplate instance
    	restTemplate = new RestTemplate();

    	// Add the Jackson and String message converters
    	org.springframework.http.converter.StringHttpMessageConverter sconverter = new StringHttpMessageConverter();
		restTemplate.getMessageConverters().add(sconverter);
    	restTemplate.getMessageConverters().add(new org.springframework.http.converter.xml.SourceHttpMessageConverter());
    	restTemplate.getMessageConverters().add(new org.springframework.http.converter.FormHttpMessageConverter());
    	org.springframework.http.converter.json.MappingJacksonHttpMessageConverter converter = new org.springframework.http.converter.json.MappingJacksonHttpMessageConverter();
    	converter.getObjectMapper().configure(Feature.WRITE_NULL_MAP_VALUES, false);
    	converter.getObjectMapper().configure(Feature.WRITE_NULL_PROPERTIES, false);
    	converter.getObjectMapper().configure(Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
    	
    	restTemplate.getMessageConverters().add(converter);
    	
    	restTemplate.setRequestFactory( new org.springframework.http.client.HttpComponentsClientHttpRequestFactory());
    	this.setUrl("https://api-cert.payeezy.com/v1");
    	this.setAppId("y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a");
    	this.setSecuredSecret("86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7");
    	this.setToken("fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6");
    	this.setMerchantid("OGEzNGU3NjM0ODQyMTU3NzAxNDg0MjE4NDY4ZTAwMDA");
    }
    
    public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getUrl() {
        return url;
    }

    
    public void setUrl(String url) {
        this.url = url;
    }

    
    public String getAppId() {
        return appId;
    }

    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    
    public String getSecuredSecret() {
        return securedSecret;
    }

    
    public void setSecuredSecret(String secureId) {
        this.securedSecret = secureId;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTrToken() {
		return trToken;
	}

	public void setTrToken(String trToken) {
		this.trToken = trToken;
	}

	public String getTokenurl() {
		return tokenurl;
	}

	public void setTokenurl(String tokenurl) {
		this.tokenurl = tokenurl;
	}

	public String getJsSecurityKey() {
		return jsSecurityKey;
	}

	public void setJsSecurityKey(String jsSecurityKey) {
		this.jsSecurityKey = jsSecurityKey;
	}
    private static final String NONCE="nonce";
    
    public static final String APIKEY="apikey";
    public static final String APISECRET="pzsecret";
    public static final String TOKEN="token";
    public static final String TIMESTAMP="timestamp";
    public static final String AUTHORIZE="Authorization";
    public static final String PAYLOAD="payload";
    public static final String OVERRIDE="override";
    
    private Map<String,String> getSecurityKeys(String appId,String secureId,String payLoad) throws Exception{

        Map<String,String> returnMap=new HashMap<String,String>();
        long nonce;
        try {
            
            nonce = Math.abs(SecureRandom.getInstance("SHA1PRNG").nextLong());
            MessageLogger.logMessage(String.format("SecureRandom nonce:{}",nonce));
            
            returnMap.put(NONCE, Long.toString(nonce));
            returnMap.put(APIKEY, this.appId);
            returnMap.put(TIMESTAMP, Long.toString(System.currentTimeMillis()));
            returnMap.put(TOKEN, this.token);
            returnMap.put(APISECRET, this.securedSecret);
            returnMap.put(PAYLOAD, payLoad);
            returnMap.put(AUTHORIZE, getMacValue(returnMap));
            return returnMap;
            
        } catch (NoSuchAlgorithmException e) {
            //log.error(e.getMessage(),e);
            MessageLogger.logMessage(e.getMessage());
            throw new RuntimeException(e.getMessage(),e);
        }       
    }
    
    public String getMacValue(Map<String,String> data) throws Exception{
        Mac mac=Mac.getInstance("HmacSHA256");
        String apiSecret= data.get(APISECRET);
        MessageLogger.logMessage(String.format("API_SECRET:{}",apiSecret));
        SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(), "HmacSHA256");
        mac.init(secret_key);
        StringBuilder buff=new StringBuilder();
        buff.append(data.get(APIKEY))
        .append(data.get(NONCE))
        .append(data.get(TIMESTAMP));
        if(data.get(TOKEN)!=null)
            buff.append(data.get(TOKEN));
        if(data.get(PAYLOAD)!=null)
            buff.append(data.get(PAYLOAD));
        String bufferData = buff.toString();
        MessageLogger.logMessage(String.format(bufferData));
        byte[] macHash=mac.doFinal(bufferData.getBytes("UTF-8"));
        MessageLogger.logMessage(Integer.toString(macHash.length));
        MessageLogger.logMessage(String.format("MacHAsh:{}",Arrays.toString( macHash)));
        
		String authorizeString=android.util.Base64.encodeToString(toHex(macHash), android.util.Base64.NO_WRAP);
        
        MessageLogger.logMessage(String.format("Authorize: {}",authorizeString));
        return authorizeString;
}
    
    public byte[] toHex(byte[] arr) {
    	MessageLogger.logMessage(Integer.toString(arr.length));
        String hex= byteArrayToHex(arr);
        MessageLogger.logMessage(String.format("Apache common value:{}" , hex));
        return hex.getBytes();
    }
    
    public static String byteArrayToHex(byte[] a) {
    	   StringBuilder sb = new StringBuilder(a.length * 2);
    	   for(byte b: a)
    	      sb.append(String.format("%02x", b & 0xff));
    	   return sb.toString();
    	}
    
    private HttpHeaders getHttpHeader(String appId,String securityKey,String payload ) throws Exception{
        Map<String,String> encriptedKey=getSecurityKeys(appId, securityKey,payload);
        HttpHeaders header=new HttpHeaders();
        Iterator<String> iter=encriptedKey.keySet().iterator();
        while(iter.hasNext()) {
            String key=iter.next();
            if(PAYLOAD.equals(key))
                continue;
            header.add(key, encriptedKey.get(key));
        }

        header.add("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");


        header.add("contentType", "application/json; charset=UTF-8");
        header.add("Accept", "*/*");
        header.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediatypes = new ArrayList<MediaType>();
        mediatypes.add(MediaType.APPLICATION_JSON);
        mediatypes.add(new MediaType("application", "json", Charset.forName("UTF-8")));
        header.setAccept(mediatypes);
        
        	header.add("x-merchant-identifier", this.merchantid);

        header.add("trtoken", this.trToken);
        
        return header;
    }
    
    public String getJSONObject(Object data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        OutputStream stream = new BufferedOutputStream(byteStream);
        JsonGenerator jsonGenerator =
            objectMapper.getJsonFactory().createJsonGenerator(stream,
                                                              JsonEncoding.UTF8);
        
        objectMapper.configure(Feature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.getSerializationConfig().setSerializationInclusion(org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL);
        objectMapper.getDeserializationConfig().set(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.getDeserializationConfig().set(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        objectMapper.writeValue(jsonGenerator, data);

        stream.flush();
        return new String(byteStream.toByteArray());
    }
    
    public UserTransactionResponse getJSONResponse(Object data) throws IOException {
    	ObjectMapper objectMapper = new ObjectMapper();
        
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        OutputStream stream = new BufferedOutputStream(byteStream);
        JsonGenerator jsonGenerator =
            objectMapper.getJsonFactory().createJsonGenerator(stream,
                                                              JsonEncoding.UTF8);
        objectMapper.writeValue(jsonGenerator, data);
        stream.flush();
        byte[] bytes = byteStream.toByteArray();
        
        com.fasterxml.jackson.databind.ObjectMapper fasterObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        java.io.InputStream inputStream = new java.io.BufferedInputStream(byteInputStream);
        @SuppressWarnings("deprecation")
		JsonParser jsonParser = fasterObjectMapper.getJsonFactory().createJsonParser(inputStream);
        UserTransactionResponse utr = new UserTransactionResponse();
        utr = jsonParser.readValueAs(UserTransactionResponse.class );
        stream.flush();
        UserTransactionResponse res = fasterObjectMapper.convertValue(data, UserTransactionResponse.class);
        System.out.println(utr.toString());
        return res;
    }
   //modified 7th aug
    public UserTransactionResponse GetTransactionResponse(String responseMessage)
    {

		UserTransactionResponse response = new UserTransactionResponse();
		response.setResponseString(responseMessage);
		Token token = new Token();
		Transarmor tokenData = new Transarmor();
		token.setTokenData(tokenData);
		response.setToken(token);
		int beginIndex = 0;
		int endIndex = 0;
		//String objstr = obj.toString();
		String objstr = responseMessage;
		boolean tokenResponse = false;
		objstr = objstr.trim();
		if(objstr.startsWith("Payeezy.callback"))
		{
			objstr = objstr.substring(19, objstr.length()); //("Payeezy.callback", "");
			objstr = objstr.trim();
			tokenResponse = true;
		}

		String[] responseData = objstr.split(",");

		for(int i=0;i<responseData.length;i++)
		{
			String str = responseData[i];

			String[] dataVals = str.split("=");

			if(tokenResponse)
			{
				str =str.trim();
				dataVals = str.split(",");
			}

			if(dataVals.length >=2)
			{
				dataVals[1] = dataVals[1].replace("{", "");
				dataVals[1] = dataVals[1].replace("}", "");
				dataVals[1] = dataVals[1].replace(":", "");
				dataVals[1] = dataVals[1].replace("\"", "");
				dataVals[1] = dataVals[1].replace("[", "");
			}
			if(dataVals.length >=3)
			{
				dataVals[2] = dataVals[2].replace("{", "");
				dataVals[2] = dataVals[2].replace("}", "");
				dataVals[2] = dataVals[2].replace(":", "");
				dataVals[2] = dataVals[2].replace("\"", "");
				dataVals[2] = dataVals[2].replace("[", "");
			}

			if(dataVals[0].contains("results"))
			{
				String correlationID = dataVals[2];
				response.setCorrelationID(correlationID);
			}


			if(dataVals[0].contains("correlation_id"))
			{
				String correlationID = dataVals[1];
				response.setCorrelationID(correlationID);
			}
			if(dataVals[0].contains("status"))
			{
				if(tokenResponse)
				{
					String status = dataVals[1];
					try
					{
						int stat = Integer.parseInt(status);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
					if(status.length()<6)
					{
						response.setStatus(status);
					}
				}
				else
				{
					String status = dataVals[1];
					response.setStatus(status);
				}
			}
			if(dataVals[0].contains("type"))
			{
				String type = dataVals[1];
				TransactionResponse.getToken().setToken_type(type);
			}
			if(dataVals[0].contains("token"))
			{
				String cardtype = dataVals[1];
				if(dataVals.length >2)
				{
					cardtype = dataVals[2];
				}
				TransactionResponse.getToken().getTokenData().setType(cardtype);
			}
			if(dataVals[0].contains("cardholder_name"))
			{
				String name = dataVals[1];
				TransactionResponse.getToken().getTokenData().setName(name);
			}

			if(dataVals[0].contains("exp_date"))
			{
				String expDate = dataVals[1];
				TransactionResponse.getToken().getTokenData().setExpiryDt(expDate);
			}
			if(dataVals[0].contains("value"))
			{

				String value = dataVals[1];
				TransactionResponse.getToken().getTokenData().setValue(value);
			}

			if(dataVals[0].contains("transaction_id"))
			{
				String transactionId = dataVals[1];

				response.setTransactionId(transactionId);
			}
			if(dataVals[0].contains("transaction_tag"))
			{
				String transactionTag = dataVals[1];
				response.setTransactionTag(transactionTag);
			}
			if(dataVals[0].contains("amount"))
			{
				String amount = dataVals[1];
				response.setAmount(amount);
			}
			if(dataVals[0].contains("transaction_status"))
			{
				String transactionStatus = dataVals[1];
				response.setTransactionStatus(transactionStatus);
			}
			if(dataVals[0].contains("validation_status"))
			{
				String validation_status = dataVals[1];
				response.setValidationStatus(validation_status);
			}
			if(dataVals[0].contains("transaction_type"))
			{
				String transaction_type = dataVals[1];
				response.setTransactionType(transaction_type);
			}
			if(dataVals[0].contains("method"))
			{
				String method = dataVals[1];
				response.setMethod(method);
			}
			if(dataVals[0].contains("currency"))
			{
				String currency = dataVals[1];
				response.setCurrency(currency);

			}

		}
		System.out.println("transaction id after authorize="+TransactionResponse.getTransactionId());
		System.out.println("transaction tag after authorize="+TransactionResponse.getTransactionTag());
		return response;

    }

	private TransactionResponse doPrimaryTransaction(TransactionRequest trans) throws Exception{

		String url=this.url+"/transactions";

		if((trans.getTransactionType() == null) || (trans.getTransactionType() == "" ))
		{

			url="https://api-cert.payeezy.com/v1"+"/transactions/tokens";
		}
		else
		{
			if((trans.getPaymentMethod().toLowerCase() != "valuelink") && (trans.getPaymentMethod().toLowerCase() != "token")  && (trans.getPaymentMethod().toLowerCase() != "3ds"))
			{
				Assert.notNull(trans.getCard().getName(),"Card holder name is empty");

				Assert.notNull(trans.getCard().getExpiryDt(),"Card Expiry date is not present");
				Assert.notNull(trans.getCard().getNumber(),"Card number is not present");
			}

			if(trans.getPaymentMethod().toLowerCase() == "valuelink")
			{
				Assert.notNull(trans.getGiftcard().getCc_number(),"Value Link Card number is not present");
			}
			if( (trans.getTransactionType() != null) || (trans.getTransactionType() != "")  || (trans.getTransactionType().toLowerCase() != "deactivate"))
			{
				if(!(url.endsWith("tokens")))
				{
					//Assert.notNull(trans.getAmount(),"Amount is not present");
				}
			}
			if(!(url.endsWith("tokens")))
			{
				//Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
			}

			if((trans.getEciindicator() == "5") && (trans.getTransactionType().toLowerCase().equalsIgnoreCase("void")))
			{
				trans.setBilling(null);
				trans.setEciindicator(null);
			}
		}
		String payload=getJSONObject(trans);
		HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
		System.out.println("url="+url);
		System.out.println("request="+request);
		ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
		System.out.println(response.toString());
		String resString = response.toString();

		UserTransactionResponse uresponseStr =  new UserTransactionResponse();
			uresponseStr = GetTransactionResponse(resString);
			uresponseStr.setResponseString(resString);
			TransactionResponse responseStr = (TransactionResponse)uresponseStr;
			System.out.println(responseStr);


		return uresponseStr;


	}

//new method for GET- 4th aug
    public TransactionResponse doPrimaryTransactionGET(String url) throws Exception{

		System.out.println("url="+url);
		String response=restTemplate.getForObject(url, String.class);
		System.out.println("response="+response);
		TransactionResponse r = GetTokenTransactionResponse(response.toString());
		return r;
	}



    private TransactionResponse doSecondaryTransaction(TransactionRequest trans) throws Exception 
    {
        Assert.notNull(TransactionResponse.getTransactionTag(),"Transaction Tag is not present");
        Assert.notNull(TransactionResponse.getTransactionId(),"Id is not present");
        Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
        String url=this.url+"/transactions/{id}";
        String payload=getJSONObject(trans);
        
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
        ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class,TransactionResponse.getTransactionId());
        System.out.println(response.toString());
        String resString = response.toString();
        UserTransactionResponse uresponseStr = GetTransactionResponse(resString);
        uresponseStr.setResponseString(resString);
        return uresponseStr;

    }
    

//Added for GET method aug 4th
/*	public TransactionResponse GETgetTokenTransaction(TransactionRequest trans) throws Exception {
		//return doGetPrimaryTransaction(trans);
	//	return doPrimaryTransactionGET(trans);
		//return doPrimaryTransaction(trans);
	}
*/
	/*public TransactionResponse GETgetTokenTransaction(TransactionRequest trans) throws Exception {

		return doPrimaryTransactionGET(trans);
	}
    */
    //used for GET method
    private UserTransactionResponse GetTokenTransactionResponse(String obj)
    {
     	   UserTransactionResponse response = new UserTransactionResponse();
     	   response.setResponseString(obj);
     	   Token token = new Token();
     	   Transarmor tokenData = new Transarmor();
     	   token.setTokenData(tokenData);
     	   response.setToken(token);
	       int beginIndex = 0;
	       int endIndex = 0;

	       String objstr = obj;
     	   boolean tokenResponse = false;
     	   objstr = objstr.trim();
     	   if(objstr.startsWith("Payeezy.callback"))
     	   {
     		   objstr = objstr.substring(19, objstr.length());
     		   objstr = objstr.trim();
     		   tokenResponse = true;
     	   }
     	   
     	   String[] responseData = objstr.split(",");
     	   
     	   for(int i=0;i<responseData.length;i++)
     	   {
     		   String str = responseData[i];
     		   
     		   String[] dataVals = str.split("=");

     		   if(tokenResponse)
     		   {
     			  str =str.trim();
     			  dataVals = str.split(":");
     		   }
     		   if(str.contains(":"))
     		   {
     			  str =str.trim();
     			  dataVals = str.split(":");
     		   }
     		   if(dataVals.length >=2)
     		   {
	       			dataVals[1] = dataVals[1].replace("{", "");
	       			dataVals[1] = dataVals[1].replace("}", "");
	       			dataVals[1] = dataVals[1].replace(":", "");
	       			dataVals[1] = dataVals[1].replace("\"", "");
	       			dataVals[1] = dataVals[1].replace("[", "");
     		   }
     		   if(dataVals.length >=3)
     		   {
	       			dataVals[2] = dataVals[2].replace("{", "");
	       			dataVals[2] = dataVals[2].replace("}", "");
	       			dataVals[2] = dataVals[2].replace(":", "");
	       			dataVals[2] = dataVals[2].replace("\"", "");
	       			dataVals[2] = dataVals[2].replace("[", "");
     		   }
     		   
     		   if(dataVals[0].contains("results"))
    		   {
		       	   String correlationID = dataVals[2];
    			   response.setCorrelationID(correlationID);
    		   }
     		   
     		   if(dataVals[0].contains("correlation_id"))
     		   {
		       	   String correlationID = dataVals[1];
     			   response.setCorrelationID(correlationID);
     		   }
     		   if(dataVals[0].contains("status"))
     		   {
     			   if(tokenResponse)
     			   {
     				    String status = dataVals[1];
     				    try
     				    {
     				    	int stat = Integer.parseInt(status);
     				    }
     				    catch(Exception e)
     				    {
     				    	System.out.println(e.getMessage());
     				    }
     				    if(status.length()<6)
     				    {
     				    	response.setStatus(status);
     				    }
     			   }
     			   else
     			   {
			       	    String status = dataVals[1];
		       			response.setStatus(status);
     			   }
     		   }
     		   if(dataVals[0].contains("type"))
     		   {
		       	    String type = dataVals[1];
	       			TransactionResponse.getToken().setToken_type(type);
     		   }
     		   if(dataVals[0].contains("token"))
     		   {
		       	    String cardtype = dataVals[1];
		       	    if(dataVals.length >2)
		       	    {
		       		   cardtype = dataVals[2];
		       	    }
				   TransactionResponse.getToken().getTokenData().setType(cardtype);
     		   }
     		   if(dataVals[0].contains("cardholder_name"))
     		   {
		       	    String name = dataVals[1];
				   TransactionResponse.getToken().getTokenData().setName(name);
     		   }
     		   
     		   if(dataVals[0].contains("exp_date"))
     		   {
		       	    String expDate = dataVals[1];
				   TransactionResponse.getToken().getTokenData().setExpiryDt(expDate);
     		   }
     		   if(dataVals[0].contains("value"))
     		   {

		       	    String value = dataVals[1];
				   int indexOfOpenBracket = 0;
				   int indexOfLastBracket = value.lastIndexOf(" ");
				   value=value.substring(indexOfOpenBracket, indexOfLastBracket);
				   System.out.println("tokenvalue after substr="+value);
				   value=value.trim();

				   System.out.println("tokenvalue after trim=" + value);
				   TransactionResponse.getToken().getTokenData().setValue(value);
     		   }
     		   
     		   if(dataVals[0].contains("transaction_id"))
     		   {
		       	    String transactionId = dataVals[1];
				   response.setTransactionId(transactionId);
     		   }
     		   if(dataVals[0].contains("transaction_tag"))
     		   {
		       	    String transactionTag = dataVals[1];
	       			response.setTransactionTag(transactionTag);
     		   }
     		   if(dataVals[0].contains("amount"))
     		   {
		       	    String amount = dataVals[1];
				   response.setAmount(amount);
     		   }
     		   if(dataVals[0].contains("transaction_status"))
     		    {
		       	    String transactionStatus = dataVals[1];
	       			response.setTransactionStatus(transactionStatus);
     		    }
     			if(dataVals[0].contains("validation_status"))
     		    {
		       	    String validation_status = dataVals[1];
	       			response.setValidationStatus(validation_status);
     		    }
     			if(dataVals[0].contains("transaction_type"))
     		    {
		       	    String transaction_type = dataVals[1];
	       			response.setTransactionType(transaction_type);
     		    }
     			if(dataVals[0].contains("method"))
     		    {
		       	    String method = dataVals[1];
	       			response.setMethod(method);
     		    }
     		
     	   }
     	   return response;
 	   
    }
   
    public TransactionResponse doPrimaryTransactionObject(TransactionRequest trans) throws Exception{
	    

		String url=this.url;
		System.out.println("url from authorise="+url);
        if( ( trans.getToken() == null) || ( trans.getType() == "FDToken") || ( trans.getToken().getTokenData().getValue() == "") || ( trans.getToken().getTokenData().getValue() == "NOIW"))
        {
	           	url=this.url+"/transactions/tokens";
        }
        this.urltoken = url;
        if(!(url.endsWith("tokens")))
        {
            Assert.notNull(trans.getAmount(),"Amount is not present");
	           Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
        }

        System.out.println("token before JSON="+TransactionResponse.getToken().getTokenData().getValue());
        String payload=getJSONObject(trans);
		System.out.println("Purchasepayload after JSON="+payload);
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));

		System.out.println("url for authorise="+url);
        ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class);

        Object o2 = response.getBody();


        TransactionResponse resp = GetTransactionResponse(o2.toString());

		System.out.println("message after purchase="+o2.toString());

        return resp;

    
   }
   public TransactionResponse doSecondaryTransactionObject(TransactionRequest trans) throws Exception {

	   Assert.notNull(TransactionResponse.getTransactionTag(), "Transaction Tag is not present");
        Assert.notNull(TransactionResponse.getTransactionId(), "Id is not present");

	   Assert.notNull(TransactionResponse.getToken().getTokenData().getType(), "Transaction type is not present");
	   url="https://api-cert.payeezy.com/v1/transactions/+{id}";
	   System.out.println("url capture=" + url);
       System.out.println("url capture="+url);

        String payload=getJSONObject(trans);
	   System.out.println("secondary trans capture transaction id="+ TransactionResponse.getTransactionId());
	   System.out.println("secondary trans capture token="+TransactionResponse.getToken().getTokenData().getValue());
	   System.out.println("payload for capture="+payload);

        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));


		   ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class, TransactionResponse.getTransactionId());

		   Object o2 = response.getBody();


		   System.out.println("response msg capture=" + o2.toString());

	   System.out.println(response.toString());
	   String resString = response.toString();

	   UserTransactionResponse uresponseStr =  new UserTransactionResponse();
	   uresponseStr.setResponseString(resString);

	   System.out.println(resString);
	return uresponseStr;
    }
   


//Added for German Direct Debit
private TransactionResponse doPurchaseVoidAVSGD(TransactionRequest trans) throws Exception{

	String payload=getJSONObject(trans);
	HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
	System.out.println("url="+url);
	System.out.println("request="+request);
	ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
	System.out.println(response.toString());
	String resString = response.toString();

	UserTransactionResponse uresponseStr =  new UserTransactionResponse();

	uresponseStr.setResponseString(resString);

	System.out.println(resString);


	return uresponseStr;


}

}
	