package com.firstdata.firstapi.client.domain;

import org.codehaus.jackson.annotate.JsonValue;


public enum TransactionType  implements TransactionTypeConstants{

/*	Capture("capture"),Purchase("purchase"),Refund("refund"),Void("void"),Authorize("authorize");
	private String transaction;
	private TransactionType(String transType) {
		this.transaction=transType;
	}
	@JsonValue
	public String getValue() {
		return this.transaction;
	}*/
   
        PURCHASE(){
             
            @JsonValue
            public String getValue() {
                return GGE4_POST;
            }
        },
        AUTHORIZE(){
//            @Override 
            @JsonValue
            public String getValue() {
                return GGE4_PREAUTH;
            }
        }
        ,
        CAPTURE(){
//            @Override 
            @JsonValue
            public String getValue() {
                return GGE4_V12_PREAUTH_COMPLETE;
            }
        },
        REFUND(){
//            @Override        
            @JsonValue
            public String getValue() {
                return GGE4_V12_REFUND;
            }
        },
        VOID(){
//            @Override 
            @JsonValue
            public String getValue() {
                return GGE4_V12_VOID;
            }
        },
        
        CASHOUT(){
//          @Override 
          @JsonValue
          public String getValue() {
              return GGE4_V12_CASHOUT;
          }
        },
        
        ACTIVATION(){
//          @Override 
          @JsonValue
          public String getValue() {
              return GGE4_V12_ACTIVATION;
          }
        },
        
        DEACTIVATION(){
//          @Override 
          @JsonValue
          public String getValue() {
              return GGE4_V12_DEACTIVATION;
          }
        },
        
        BALANCEENQUIRY(){
//          @Override 
          @JsonValue
          public String getValue() {
              return GGE4_V12_BALANCEENQUIRY;
          }
        },
        
        RELOAD(){
//          @Override 
          @JsonValue
          public String getValue() {
              return GGE4_V12_RELOAD;
          }
        },
        
        FORCEDPOST(){
//          @Override 
          @JsonValue
          public String getValue() {
              return GGE4_V11_FORCEDPOST;
          }
        },
        
        PREAUTH(){
//          @Override 
          @JsonValue
          public String getValue() {
              return GGE4_V11_PREAUTH;
          }
        },
        
        PAYPAL(){
//          @Override 
          @JsonValue
          public String getValue() {
              return GGE4_V11_PAYPAL;
          }
        },
        SPLIT(){
//          @Override 
          @JsonValue
          public String getValue() {
              return GGE4_V12_PREAUTH_COMPLETE;
          }
        }

}
