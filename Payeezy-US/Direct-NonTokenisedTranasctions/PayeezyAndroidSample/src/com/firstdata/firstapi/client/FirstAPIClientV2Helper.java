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

					    			transResponse.setMethod(values[1]);
								}
					    		
					    		if(values[0] == "transaction_status")
					    		{

					    			transResponse.setTransactionStatus(values[1]);
								}
					    		if(values[0] == "validation_status")
					    		{

					    			transResponse.setValidationStatus(values[1]);
					    		}
					    		if(values[0] == "transaction_type")
					    		{

					    			transResponse.setTransactionType(values[1]);
					    		}
					    		if(values[0] == "transaction_id")
					    		{

					    			transResponse.setTransactionId(values[1]);
					    		}
					    		if(values[0] == "transaction_tag")
					    		{

					    			transResponse.setTransactionTag(values[1]);
					    		}
					    		
					    		if(values[0] == "amount")
					    		{

					    			transResponse.setAmount(values[1]);
					    		}
					    		if(values[0] == "cardholder_name")
					    		{

					    			transResponse.getCard().setName(values[1]);
					    		}
					    		if(values[0] == "card_number")
					    		{

					    			transResponse.getCard().setNumber(values[1]);
					    		}
					    		if(values[0] == "exp_date")
					    		{

					    			transResponse.getCard().setExpiryDt(values[1]);
					    		}
					    		if(values[0] == "amount")
					    		{

					    			transResponse.setAmount(values[1]);
					    		}
					    		
					    		if(values[0] == "bank_resp_code")
					    		{

					    			transResponse.setBankRespCode(values[1]);
					    		}
					    		if(values[0] == "bank_message")
					    		{

					    			transResponse.setBankMessage(values[1]);
					    		}
					    		if(values[0] == "gateway_resp_code")
					    		{

					    			transResponse.setGatewayRespCode(values[1]);
					    		}
					    		if(values[0] == "gateway_message")
					    		{

					    			transResponse.setGatewayMessage(values[1]);
					    		}
					    		if(values[0] == "correlation_id")
								{

					    			transResponse.setCorrelationID(values[1]);
					    		}
					    		if(values[0] == "valuelink")
					    		{

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
        ResponseEntity<Object> response= restTemplate.exchange(url, HttpMethod.POST, request, Object.class,trans.getTransactionId());
        System.out.println(response.toString());
        String resString = response.toString();
		UserTransactionResponse uresponseStr=  new UserTransactionResponse();


			uresponseStr.setResponseString(resString);
        return uresponseStr;

    }
}
	