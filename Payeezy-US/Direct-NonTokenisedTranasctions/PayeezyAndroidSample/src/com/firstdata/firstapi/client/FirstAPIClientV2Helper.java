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

/*    private String getValue(String value){
        return new StringBuilder().append("\"").append(value).append("\"").toString();
    }
    */
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
            //String logMessage = String.format("SecureRandom nonce:{}",nonce);
            //System.out.println(logMessage);
            //log.debug("SecureRandom nonce:{}",nonce);
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

	//check can be removed 09-16
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
    
    public UserTransactionResponse GetTransactionResponse(String responseMessage)
    {
    	
    	String responseString = responseMessage;
    	UserTransactionResponse  transResponse = new UserTransactionResponse();
    	try
    	{
	    	TransactionResponse response = new TransactionResponse();
	    	Card card = new Card();
	    	response.setCard(card);
	    	transResponse.setBody(response);
	    	
	    	String[] strArray =  responseString.split(",");
	    	
	    	int start = responseString.indexOf("<") ;
	    	int end = responseString.indexOf(",") ;
	    	String resCode = responseString.substring(start, end);
	    	if(resCode.startsWith("<"))
	    	{
	    		resCode = resCode.replaceFirst("<", "");
	    	}
	    	String[] codes = resCode.split(" ");
	    	transResponse.setResponseCode(codes[0]);
	    	transResponse.setResponseMessage(codes[1]);
	    	
	    	end = responseString.lastIndexOf(",");
	    	responseString = responseString.substring(start, end);

	    	for(int i=0;i<strArray.length;i++)
	    	{
	    		try
	    		{
		    		if(strArray[i] != null)
		    		{
		    			strArray[i] = strArray[i].trim();
			    		if(strArray[i].startsWith("{") )
			    		{
			    			strArray[i] = strArray[i].replace("{", "");
			    		}
			    		if(strArray[i].endsWith("}") )
			    		{
			    			strArray[i] = strArray[i].replace("}", "");
			    		}
			    		if(strArray[i].endsWith("]") )
			    		{
			    			strArray[i] = strArray[i].replace("]", "");
			    		}
			    		if(strArray[i].startsWith("[") )
			    		{
			    			strArray[i] = strArray[i].replace("[", "");
			    		}
			    		String[] values =  strArray[i].split("=");
			    		if(values.length>1)
			    		{
				    		if(values[1] == null)
				    		{
				    			continue;
				    		}
				    		values[0] = values[0].trim();
				    		values[1] = values[1].trim();
				    		if(values[0] != null)
				    		{
				    			if(values[0] == "method")
					    		{
					    			//transResponse.getBody().setMethod(values[1]); ;
					    			transResponse.setMethod(values[1]);
								}
					    		
					    		if(values[0] == "transaction_status")
					    		{
					    			//transResponse.getBody().setTransactionStatus(values[1]); ;
					    			transResponse.setTransactionStatus(values[1]);
								}
					    		if(values[0] == "validation_status")
					    		{
					    			//transResponse.getBody().setValidationStatus(values[1]);
					    			transResponse.setValidationStatus(values[1]);
					    		}
					    		if(values[0] == "transaction_type")
					    		{
					    			//transResponse.getBody().setTransactionType(values[1]);
					    			transResponse.setTransactionType(values[1]);
					    		}
					    		if(values[0] == "transaction_id")
					    		{
					    			//transResponse.getBody().setTransactionId(values[1]);
					    			transResponse.setTransactionId(values[1]);
					    		}
					    		if(values[0] == "transaction_tag")
					    		{
					    			//transResponse.getBody().setTransactionTag(values[1]);
					    			transResponse.setTransactionTag(values[1]);
					    		}
					    		
					    		if(values[0] == "amount")
					    		{
					    			//transResponse.getBody().setAmount(values[1]); 
					    			transResponse.setAmount(values[1]);
					    		}
					    		if(values[0] == "cardholder_name")
					    		{
					    			//transResponse.getBody().getCard().setName(values[1]);
					    			transResponse.getCard().setName(values[1]);
					    		}
					    		if(values[0] == "card_number")
					    		{
					    			//transResponse.getBody().getCard().setNumber(values[1]);
					    			transResponse.getCard().setNumber(values[1]);
					    		}
					    		if(values[0] == "exp_date")
					    		{
					    			//transResponse.getBody().getCard().setExpiryDt(values[1]); 
					    			transResponse.getCard().setExpiryDt(values[1]);
					    		}
					    		if(values[0] == "amount")
					    		{
					    			//transResponse.getBody().setAmount(values[1]);
					    			transResponse.setAmount(values[1]);
					    		}
					    		
					    		if(values[0] == "bank_resp_code")
					    		{
					    			//transResponse.getBody().setBankRespCode(values[1]);
					    			transResponse.setBankRespCode(values[1]);
					    		}
					    		if(values[0] == "bank_message")
					    		{
					    			//transResponse.getBody().setBankMessage(values[1]);
					    			transResponse.setBankMessage(values[1]);
					    		}
					    		if(values[0] == "gateway_resp_code")
					    		{
					    			//transResponse.getBody().setGatewayRespCode(values[1]);
					    			transResponse.setGatewayRespCode(values[1]);
					    		}
					    		if(values[0] == "gateway_message")
					    		{
					    			//transResponse.getBody().setGatewayMessage(values[1]);
					    			transResponse.setGatewayMessage(values[1]);
					    		}
					    		if(values[0] == "correlation_id")
					    		{
					    			//transResponse.getBody().setCorrelationID(values[1]); 
					    			transResponse.setCorrelationID(values[1]);
					    		}
					    		if(values[0] == "valuelink")
					    		{
					    			//transResponse.getBody().setCorrelationID(values[1]); 
					    			card.setName(values[1]);
					    		}
					    		//get token
					    		if(values[0].contains("status"))
					       		   {
							       	   String status = values[1];
						       			status = status.replace("{", "");
						       			status = status.replace("}", "");
						       			status = status.replace(":", "");
						       			status = status.replace("\"", "");
						       			transResponse.setStatus(status);
					       		   }
					       		   if(values[0].contains("type"))
					       		   {
							       	   String type = values[1];
						       			type = type.replace("{", "");
						       			type = type.replace("}", "");
						       			type = type.replace(":", "");
						       			type = type.replace("\"", "");
							       	   response.getToken().setToken_type(type);
					       		   }
					       		   if(values[0].contains("token"))
					       		   {
							       	   String cardtype = values[1];
							       	   if(values.length >2)
							       	   {
							       		   cardtype = values[2];
							       	   }
						       			cardtype = cardtype.replace("{", "");
						       			cardtype = cardtype.replace("}", "");
						       			cardtype = cardtype.replace(":", "");
						       			cardtype = cardtype.replace("\"", "");
						       			//response.getToken().getToken_data().setType(cardtype);
						       			response.getToken().getTokenData().setType(cardtype);
					       		   }
				    		}
			    		}
		    		}
	    		}
	    		catch(Exception e)
	    		{
	    			System.out.println(e.getMessage());
	    		}
	    	}
	    	transResponse.setBody(response);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	return transResponse;
    }
    
    public TransactionResponse doPrimaryTransaction(TransactionRequest trans) throws Exception{
    	

		String url="https://api-cert.payeezy.com/v1/transactions";

    	if((trans.getTransactionType() == null) || (trans.getTransactionType() == "" ))
    	{
    		url="https://api-cert.payeezy.com/v1"+"/transactions/tokens";
    	}
    	else
    	{

			if(trans.getPaymentMethod().toLowerCase() == "3DS")
			{
			//	Assert.notNull(trans.getThreeDomainSecureToken().getCard_number(),"3DS card number not present");
			//	Assert.notNull(trans.getThreeDomainSecureToken().getCardholder_name(),"3DS card holder name not present");
			//	Assert.notNull(trans.getThreeDomainSecureToken().getCavv(),"3DS cavv not present");
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
		System.out.println("payload****="+payload);
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));


        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, request, Object.class);

	        System.out.println(response.toString());
	        String resString = response.toString();

	        UserTransactionResponse uresponseStr =  new UserTransactionResponse();
		uresponseStr.setResponseString(resString);
		return uresponseStr;
		//trying

	/*	Object o2 = response.getBody();
		TransactionResponse resp = GetTransactionResponse(o2.toString());

		return resp;*/

	//	end trying
	 /*       if(resString.contains("FDToken"))
	        {
	        	uresponseStr.setResponseString(resString);

	        }
	        else
	        {

		        uresponseStr.setResponseString(resString);

	        }
	   */
	     //   return uresponseStr;

            
    }
    
    @SuppressWarnings("unused")
	private TransactionResponse doGetPrimaryTransaction(TransactionRequest trans) throws Exception{
    	if(trans.getPaymentMethod().toLowerCase() != "valuelink")
    	{
	        Assert.notNull(trans.getCard().getName(),"Card holder name is empty");
	        Assert.notNull(trans.getCard().getExpiryDt(),"Card Expiry date is not present");
	        Assert.notNull(trans.getCard().getNumber(),"Card number is not present");
    	}
        
        if(trans.getPaymentMethod().toLowerCase() == "valuelink")
    	{
        	Assert.notNull(trans.getGiftcard().getCc_number(),"Value Link Card number is not present");
    	}
        
        Assert.notNull(trans.getAmount(),"Amount is not present");
        Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
    
        String url=this.url+"/transactions";
        String payload=getJSONObject(trans);
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
        //request.getHeaders().setUserAgent(System.getProperty("http.agent"));
        ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
        System.out.println(response.toString());
        String resString = response.toString();
        UserTransactionResponse uresponseStr = GetTransactionResponse(resString);
        uresponseStr.setResponseString(resString);
        //TransactionResponse responseStr = (TransactionResponse)uresponseStr;
        return uresponseStr;
    
    }
    
    public TransactionResponse doSecondaryTransaction(TransactionRequest trans) throws Exception
    {
        Assert.notNull(trans.getTransactionTag(), "Transaction Tag is not present");

        Assert.notNull(trans.getTransactionId(),"Id is not present");
        Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
        String url=this.url+"/transactions/{id}";
        String payload=getJSONObject(trans);
        System.out.println("payload within secondary="+payload);
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
        //ResponseEntity<TransactionResponse> response= restTemplate.exchange(url, HttpMethod.POST, request, TransactionResponse.class,trans.getTransactionId());
        //ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class,trans.getTransactionId());
        //request.getHeaders().setUserAgent(System.getProperty("http.agent"));
        ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class,trans.getTransactionId());
        System.out.println(response.toString());
        String resString = response.toString();
		UserTransactionResponse uresponseStr=  new UserTransactionResponse();


			uresponseStr.setResponseString(resString);
			//	UserTransactionResponse tres = GetTransactionResponse(resString);
			//	uresponseStr = tres;


      //  UserTransactionResponse uresponseStr = GetTransactionResponse(resString);
    //    uresponseStr.setResponseString(resString);
        //TransactionResponse responseStr = (TransactionResponse)uresponseStr;
        //        return doTransaction(trans,credentials);
        //return response.getBody();
        return uresponseStr;
//           return null;
    }
    
    /*
    @SuppressWarnings("unused")
	private TransactionResponse doSecondaryTransactionSplit(TransactionRequest trans) throws Exception{
        Assert.notNull(trans.getTransactionTag(),"Transaction Tag is not present");
        //Assert.notNull(trans.getTransactionId(),"Id is not present"); 
        Assert.notNull(trans.getId(),"Id is not present"); 
        Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
        String url=this.url+"/transactions/{id}";
        String payload=getJSONObject(trans);
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
        //ResponseEntity<TransactionResponse> response= restTemplate.exchange(url, HttpMethod.POST, request, TransactionResponse.class,trans.getTransactionId());
        //ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class,trans.getTransactionId());
        //client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, System.getProperty("http.agent"));
        //request.getHeaders().setUserAgent(System.getProperty("http.agent"));
        ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class,trans.getId());
        System.out.println(response.toString());
        String resString = response.toString();
        UserTransactionResponse uresponseStr = GetTransactionResponse(resString);
        uresponseStr.setResponseString(resString);
        //TransactionResponse responseStr = (TransactionResponse)uresponseStr;
        //        return doTransaction(trans,credentials);
        //return response.getBody();
        return uresponseStr;
//       return null;
    }
    */
    
    /*
    private TransactionResponse doPrimaryTransactionGet(TransactionRequest trans) throws Exception{
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
        if((trans.getTransactionType().toLowerCase() != "deactivate"))
        {
        	Assert.notNull(trans.getAmount(),"Amount is not present");
        }
        Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
    
        if((trans.getEciindicator() == "5") && (trans.getTransactionType().toLowerCase().equalsIgnoreCase("void")))
        {
        	trans.setBilling(null);
        	trans.setEciindicator(null);
        }
        String url=this.url+"/securitytokens";
       // * 
       //  *  apikey=y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a
       //  *  &trtoken=y6pzAbc3Def123&callback=Payeezy.callback
       //  *  &type=payeezy&credit_card.type=visa
       //  *  &credit_card.cardholder_name=John%20Smith
       //  *  &credit_card.card_number=4788250000028291
       //  *  &credit_card.exp_date=1030&credit_card.cvv=123 
       //  * *
       //  *
        url = url+"?apikey=" + this.getAppId();
        url = url+"&trtoken=" + trans.getPztoken().getTRToken();
        url = url+"&callback=Payeezy.callback&type=payeezy" +
        		"&credit_card.type=" + trans.getCard().getType();
        url = url+"&credit_card.cardholder_name=" + trans.getCard().getName();
        url = url+"&credit_card.card_number=" + trans.getCard().getNumber();
        url = url+"&credit_card.exp_date=" + trans.getCard().getExpiryDt();
        url = url+"&credit_card.cvv=" + trans.getCard().getCvv();
        
        String payload=getJSONObject(trans);
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
        ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
        System.out.println(response.toString());
        String resString = response.toString();
        UserTransactionResponse uresponseStr = GetTransactionResponse(resString);
        uresponseStr.setResponseString(resString);
        TransactionResponse responseStr = (TransactionResponse)uresponseStr;
        return uresponseStr;
    }
    */
    
    /*public TransactionResponse purchaseTransaction(TransactionRequest trans) throws Exception{
        trans.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());
        return doPrimaryTransaction(trans);
    }
    
    public TransactionResponse authorizeTransaction(TransactionRequest trans) throws Exception{
        trans.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());
        return doPrimaryTransaction(trans);
    }*/
    public TransactionResponse captureTransaction(TransactionRequest trans)throws Exception{
        trans.setTransactionType(TransactionType.CAPTURE.name().toLowerCase());
        return doSecondaryTransaction(trans);
    }
    public TransactionResponse refundTransaction(TransactionRequest trans)throws Exception{
        trans.setTransactionType(TransactionType.REFUND.name().toLowerCase());
        return doSecondaryTransaction(trans);
    }
    public TransactionResponse voidTransaction(TransactionRequest trans)throws Exception{
        trans.setTransactionType(TransactionType.VOID.name().toLowerCase()); 
        return doSecondaryTransaction(trans);
    }
    
    public TransactionResponse nakedVoidTransaction(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.VOID.name());
        return doPrimaryTransaction(trans);
    }
    
    public TransactionResponse nakedRefundTransaction(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.REFUND.name());
        return doPrimaryTransaction(trans);
    }
    
    public TransactionResponse CashoutTransaction(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.CASHOUT.name());
        return doPrimaryTransaction(trans);
    }
    
    public TransactionResponse ReloadTransaction(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.RELOAD.name());
        return doPrimaryTransaction(trans);
    }
    
    public TransactionResponse PartialPurchaseTransaction(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.PURCHASE.name());
        return doPrimaryTransaction(trans);
    }
    
    public TransactionResponse ActivationTransaction(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.ACTIVATION.name());
        return doPrimaryTransaction(trans);
    }
    
    public TransactionResponse DeactivationTransaction(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.DEACTIVATION.name());
        return doPrimaryTransaction(trans);
    }
    
    public TransactionResponse BalanceInquiryTransaction(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.BALANCEENQUIRY.name());
        return doPrimaryTransaction(trans);
    }
    
    public TransactionResponse SplitTransaction(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.SPLIT.name());
        return doSecondaryTransaction(trans);
    }
    
    public TransactionResponse getTokenTransaction(TransactionRequest trans) throws Exception {
        
        return doPrimaryTransaction(trans);
    }
    
 public TransactionResponse purchaseTransactionToken(TransactionRequest trans) throws Exception{
        
        trans.setTransactionType(TransactionType.PURCHASE.name().toLowerCase());
        return doPrimaryTransactionObject(trans);
    }
    
    public TransactionResponse authorizeTransactionToken(TransactionRequest trans) throws Exception{
        trans.setTransactionType(TransactionType.AUTHORIZE.name().toLowerCase());
        return doPrimaryTransactionObject(trans);
    }
    public TransactionResponse captureTransactionToken( TransactionRequest trans)throws Exception{
        trans.setTransactionType(TransactionType.CAPTURE.name().toLowerCase());
        return doSecondaryTransactionObject(trans);
    }
    public TransactionResponse refundTransactionToken(TransactionRequest trans)throws Exception{
        trans.setTransactionType(TransactionType.REFUND.name().toLowerCase());
        return doSecondaryTransactionObject(trans);
    }
    public TransactionResponse voidTransactionToken(TransactionRequest trans)throws Exception{
        trans.setTransactionType(TransactionType.VOID.name().toLowerCase()); 
        return doSecondaryTransactionObject(trans);
    }
    

    public TransactionResponse getTokenTransactionGet(TransactionRequest trans) throws Exception {
        
        return doPrimaryTransactionGet(trans);
    }
    
    
    
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
     	   //String objstr = obj.toString();
	       String objstr = obj;
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
     		   //String[] dataVals = str.split(":");
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
     		   
     		   
     		   
     		   
     		   //if(str.contains("correlation_id"))
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
     				    if(status.length()<6)  //if(stat>0)
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
	       			response.getToken().setToken_type(type);
     		   }
     		   if(dataVals[0].contains("token"))
     		   {
		       	    String cardtype = dataVals[1];
		       	    if(dataVals.length >2)
		       	    {
		       		   cardtype = dataVals[2];
		       	    }
	       			response.getToken().getTokenData().setType(cardtype);
     		   }
     		   if(dataVals[0].contains("cardholder_name"))
     		   {
		       	    String name = dataVals[1];
	       			response.getToken().getTokenData().setName(name);
     		   }
     		   
     		   if(dataVals[0].contains("exp_date"))
     		   {
		       	    String expDate = dataVals[1];
	       			response.getToken().getTokenData().setExpiryDt(expDate);
     		   }
     		   if(dataVals[0].contains("value"))
     		   {
		       	    String value = dataVals[1];
	       			response.getToken().getTokenData().setValue(value);
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
   
    public TransactionResponse doPrimaryTransactionObject2(TransactionRequest trans) throws Exception{
	    
    	String url=this.url+"/transactions";
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
        /*
        String payload=getJSONObject(trans);
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
        //ResponseEntity<TransactionResponse> response= restTemplate.exchange(url, HttpMethod.POST, request, TransactionResponse.class);
        ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
        //ResponseEntity<HashMap> response= restTemplate.exchange(url, HttpMethod.POST, request, HashMap.class);
        //return doTransaction(trans,credentials);
        Object o2 = response.getBody();
        TransactionResponse resp = GetTransactionResponse(o2.toString());
        //return (TransactionResponse) response.getBody();
        return resp;
        */
        return null;
    
        
   }
    
    public TransactionResponse doPrimaryTransactionObject(TransactionRequest trans) throws Exception{
	    
    	String url=this.url+"/transactions";
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
        
        String payload=getJSONObject(trans);
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
        //ResponseEntity<TransactionResponse> response= restTemplate.exchange(url, HttpMethod.POST, request, TransactionResponse.class);
        ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
        //ResponseEntity<HashMap> response= restTemplate.exchange(url, HttpMethod.POST, request, HashMap.class);
        //return doTransaction(trans,credentials);
        Object o2 = response.getBody();
        TransactionResponse resp = GetTokenTransactionResponse(o2.toString());
        //return (TransactionResponse) response.getBody();
        return resp;
//         return null;
    
   }
   public TransactionResponse doSecondaryTransactionObject(TransactionRequest trans) throws Exception {
        Assert.notNull(trans.getTransactionTag(),"Transaction Tag is not present");
        Assert.notNull(trans.getId(),"Id is not present");    
        Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
        String url=this.url+"/transactions/{id}";
        trans.setTransactionType(trans.getTransactionType().toLowerCase());
        String payload=getJSONObject(trans);
        HttpEntity<TransactionRequest> request=new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
        //ResponseEntity<TransactionResponse> response= restTemplate.exchange(url, HttpMethod.POST, request, TransactionResponse.class,trans.getId());
        ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class, trans.getId());
//           return doTransaction(trans,credentials);
        
        Object o2 = response.getBody();
        TransactionResponse resp = GetTokenTransactionResponse(o2.toString());
        return resp;
        //return (TransactionResponse)response.getBody();
//           return null;
    }
   

	  	private TransactionResponse doPrimaryTransactionGet(TransactionRequest trans) throws Exception
	  	{
	      if(trans.getPaymentMethod().toLowerCase() != "valuelink")
	      {
	  	        Assert.notNull(trans.getCard().getName(),"Card holder name is empty");
	  	        Assert.notNull(trans.getCard().getExpiryDt(),"Card Expiry date is not present");
	  	        Assert.notNull(trans.getCard().getNumber(),"Card number is not present");
	      }
	      
	      if(trans.getPaymentMethod().toLowerCase() == "valuelink")
	      {
	          	Assert.notNull(trans.getGiftcard().getCc_number(),"Value Link Card number is not present");
	      }
	      
	      Assert.notNull(trans.getAmount(),"Amount is not present");
	      Assert.notNull(trans.getTransactionType(),"Transaction type is not present");
	  
	      //String url=this.url+"/securitytokens";
	      String url=this.url+"/transactions/tokens";
	      String payload=getJSONObject(trans);
	      HttpEntity<TransactionRequest> request = new HttpEntity<TransactionRequest>(trans,getHttpHeader(this.appId, this.securedSecret,payload));
	      
	      //request.getHeaders().setUserAgent(System.getProperty("http.agent"));
	      //GET /v1/securitytokens?apikey=y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a
	      //&trtoken=y6pzAbc3Def123
	      //&callback=Payeezy.callback
	      //&type=payeezy
	      //&credit_card.type=visa
	      //&credit_card.cardholder_name=John%20Smith
	      //&credit_card.card_number=4788250000028291
	      //&credit_card.exp_date=1030
	      //&credit_card.cvv=123 HTTP/1.1
	      
	      //working get call
	      //https://api-int.payeezy.com/v1/securitytokens?auth=false&ta_token=NOIW&apikey=fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ&trtoken=y6pzAbc3Def123&callback=Payeezy.callback&type=FDToken&credit_card.type=American%20Express&credit_card.cardholder_name=xyz&credit_card.card_number=373953192351004&credit_card.exp_date=0416&credit_card.cvv=1234&auth=false&ta_token=NOIW
	      //String surl = "https://api-int.payeezy.com/v1/securitytokens?auth={auth1}&ta_token={tatoken2}&apikey={apikey3}&trtoken={trtoken4}&callback={callback5}&type={tokentype6}&credit_card.type={cardtype7}&credit_card.cardholder_name={cardholdername8}}&credit_card.card_number={cardnumber9}&credit_card.exp_date={expiry10}&credit_card.cvv={cvv11}&auth={auth12}&ta_token={tatoken13}";
	      //https://api-int.payeezy.com/v1/securitytokens?
	      //auth=false
	      //&ta_token=NOIW
	      //&apikey=fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ
	      //&trtoken=y6pzAbc3Def123
	      //&callback=Payeezy.callback
	      //&type=FDToken
	      //&credit_card.type=American%20Express
	      //&credit_card.cardholder_name=xyz
	      //&credit_card.card_number=373953192351004
	      //&credit_card.exp_date=0416
	      //&credit_card.cvv=1234
	      //&auth=false
	      //&ta_token=NOIW
	      URI uri = null;
	      request.getBody().setCallback("Payeezy.callback");
	      if(request.getBody().getToken().getToken_type() == "payeezy")
	      {
	   	   url = url.replace("api-int", "api-cert");
	          UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
	       	        .queryParam("apikey", this.appId)
	       	        //.queryParam("trtoken", this.securedSecret)
	       	        .queryParam("trtoken", this.trToken)
	       	        .queryParam("callback", request.getBody().getCallback())
	       	        .queryParam("type", request.getBody().getToken().getToken_type())
	       	        .queryParam("credit_card.type", request.getBody().getToken().getTokenData().getType())
	       	        .queryParam("credit_card.cardholder_name", request.getBody().getToken().getTokenData().getName())
	       	        .queryParam("credit_card.card_number", request.getBody().getToken().getTokenData().getNumber())
	          			.queryParam("credit_card.exp_date", request.getBody().getToken().getTokenData().getExpiryDt())
	          			.queryParam("credit_card.cvv", request.getBody().getToken().getTokenData().getCvv());
	          url = builder.build().toUri().toString();
	          url = builder.build().toUri().toURL().toString();
	      }
	      else
	      {
	   	   url = url.replace("api-cert", "api-int");
	   	   url = url.replace("/transactions/tokens", "/securitytokens");
	          UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
	         	        .queryParam("auth", request.getBody().getAuth())
	         	        .queryParam("ta_token", request.getBody().getToken().getTokenData().getValue())
	         	        .queryParam("apikey", this.appId)
	         	        //.queryParam("trtoken", this.securedSecret)
	         	        .queryParam("trtoken", this.trToken)
	         	        .queryParam("callback", request.getBody().getCallback())
	         	        .queryParam("type", request.getBody().getToken().getToken_type())
	         	        .queryParam("credit_card.type", request.getBody().getToken().getTokenData().getType())
	         	        .queryParam("credit_card.cardholder_name", request.getBody().getToken().getTokenData().getName())
	         	        .queryParam("credit_card.card_number", request.getBody().getToken().getTokenData().getNumber())
	            		.queryParam("credit_card.exp_date", request.getBody().getToken().getTokenData().getExpiryDt())
	            		.queryParam("credit_card.cvv", request.getBody().getToken().getTokenData().getCvv())
	            		.queryParam("autha", request.getBody().getAuth())
	            		.queryParam("ta_tokena", request.getBody().getToken().getTokenData().getValue())
	            		;
	          uri = builder.build().toUri();
	            url = builder.build().toUri().toString();
	            url = builder.build().toUri().toURL().toString();
	            url =  url.replace("autha", "auth");
	            url =  url.replace("ta_tokena", "ta_token");
	            uri = new URI(url);
	            //url = url + "&auth=" + request.getBody().getAuth();
	            //url = url + "&ta_token=" + request.getBody().getToken().getTokenData().getValue();
	      }
	      
	      String urlnew = this.url + "/securitytokens" + "?";
	      urlnew = urlnew + "auth=false";
	      urlnew = urlnew + "&ta_token=NOIW";
	      urlnew  = urlnew  + "&apikey=fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ";
	      urlnew  = urlnew  + "&trtoken=y6pzAbc3Def123";
	      urlnew  = urlnew  + "&callback=Payeezy.callback";
	      urlnew  = urlnew  + "&type=FDToken";
	      urlnew  = urlnew  + "&credit_card.type=American%20Express";
	      urlnew  = urlnew  + "&credit_card.cardholder_name=xyz";
	      urlnew  = urlnew  + "&credit_card.card_number=373953192351004";
	      urlnew  = urlnew  + "&credit_card.exp_date=0416";
	      urlnew  = urlnew  + "&credit_card.cvv=1234";
	      urlnew  = urlnew  + "&auth=false";
	      urlnew  = urlnew  + "&ta_token=NOIW";
	      
	      
	      
	      
	      //request.getHeaders().add("x-merchant-identifier", this.merchantid);
	      //request.getHeaders().add("x-merchant-identifier", "OGEzNGU3NjM0ODQyMTU3NzAxNDg0MjE4NDY4ZTAwMDA=");
	      
	      //Object objresponse= restTemplate.getForObject(uri, Object.class);
	      String objresponse= restTemplate.getForObject(uri, String.class);
	      
	      
	      //ResponseEntity<TransactionResponse> response= restTemplate.exchange(url, HttpMethod.GET, request, TransactionResponse.class);
	      //ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
	      
	      //ResponseEntity<Object> response= restTemplate.exchange("https://api-int.payeezy.com/v1/securitytokens?auth={auth1}&ta_token={tatoken2}&apikey={apikey3}&trtoken={trtoken4}&callback={callback5}&type={tokentype6}&credit_card.type={cardtype7}&credit_card.cardholder_name={cardholdername8}}&credit_card.card_number={cardnumber9}&credit_card.exp_date={expiry10}&credit_card.cvv={cvv11}&auth={auth12}&ta_token={tatoken13}", HttpMethod.GET, request, Object.class, "false", request.getBody().getTa_token(), this.appId, this.getTrToken(), "Payeezy.callback", request.getBody().getToken().getTokenType(), request.getBody().getToken().getTokenData().getType(), request.getBody().getToken().getTokenData().getName(), request.getBody().getToken().getTokenData().getNumber(), request.getBody().getToken().getTokenData().getExpiryDt(), request.getBody().getToken().getTokenData().getCvv(), "false", request.getBody().getTa_token() );
	      //ResponseEntity<Object> response= restTemplate.exchange("https://api-int.payeezy.com/v1/securitytokens?auth=false&ta_token=NOIW&apikey=fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ&trtoken=y6pzAbc3Def123&callback=Payeezy.callback&type=FDToken&credit_card.type=American%20Express&credit_card.cardholder_name=xyz&credit_card.card_number=373953192351004&credit_card.exp_date=0416&credit_card.cvv=1234&auth=false&ta_token=NOIW", HttpMethod.GET, request, Object.class, "false" );
	      Object response = objresponse;
	      System.out.println(response.toString());
	      String resString = response.toString();
	      //String ro = response.getBody().toString();
	      //TransactionResponse r = response.getBody(); 
	      TransactionResponse r = GetTokenTransactionResponse(response.toString()); 
	      return r;
	      
	  }



}
	