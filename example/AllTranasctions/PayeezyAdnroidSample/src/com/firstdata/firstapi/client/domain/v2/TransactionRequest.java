package com.firstdata.firstapi.client.domain.v2;



import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.example.sampleapp6.PZToken;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TransactionRequest {
	public TransactionRequest() {
	}

	//@JsonProperty("transaction_id")
	@JsonIgnore
	private String id;

	@JsonProperty("transaction_type")
	private String transactionType;
	
	@JsonProperty("merchant_ref")
	private String referenceNo;
	@JsonProperty("method")
	private String paymentMethod;
	@JsonProperty("amount")
	private String amount;
	@JsonProperty("currency_code")
	private String currency;
	@JsonProperty("transaction_tag")
	private String transactionTag;
	
	@JsonProperty("transaction_id")
	private String transactionId;
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@JsonProperty("credit_card")
	private Card card;
	
	@JsonProperty("billing_address")
	private Address billing;
	
	@JsonProperty("soft_descriptors")
	private SoftDescriptor descriptor;
	
	@JsonProperty("3DS")
	private ThreeDomainSecureToken threeDomainSecureToken;
	
	@JsonProperty("token")
	private Token token;
	
	//@JsonProperty("trtoken")
	private PZToken pztoken;
	
	public PZToken getPztoken() {
		return pztoken;
	}
	public void setPztoken(PZToken pztoken) {
		this.pztoken = pztoken;
	}

	@JsonProperty("split_tender_id)")
	private String split_tender_id;
	
	
	public String getSplit_tender_id() {
		return split_tender_id;
	}
	public void setSplit_tender_id(String split_tender_id) {
		this.split_tender_id = split_tender_id;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
	public ThreeDomainSecureToken getThreeDomainSecureToken() {
		return threeDomainSecureToken;
	}
	public void setThreeDomainSecureToken(ThreeDomainSecureToken threeDomainSecureToken) {
		this.threeDomainSecureToken = threeDomainSecureToken;
	}	
	
	public SoftDescriptor getDescriptor() {
		return descriptor;
	}
	public void setDescriptor(SoftDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTransactionTag() {
		return transactionTag;
	}
	public void setTransactionTag(String transactionTag) {
		this.transactionTag = transactionTag;
	}

	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}

	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@JsonInclude(Include.NON_NULL)
	//@JsonProperty("value_link")
	@JsonProperty("valuelink")
	private ValueLinkCard giftcard;
	
	@JsonInclude(Include.NON_NULL)
	public ValueLinkCard getGiftcard() {
		return giftcard;
	}

	
	public void setGiftcard(ValueLinkCard giftcard) {
		this.giftcard = giftcard;
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("tele_check")
	private Telecheck check;

	@JsonInclude(Include.NON_NULL)
	public Telecheck getCheck() {
		return check;
	}
	
	
	public void setCheck(Telecheck check) {
		this.check = check;
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("level2")
	private Level2 level2;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("level3")
	private Level3 level3;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("line_items")
	private Line_item[] lineitems;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("eci_indicator")
	private String eciindicator;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("Recurring")
	private Recurring recurring;

	//@JsonInclude(Include.NON_NULL)
	//@JsonProperty("3DS")
	//private ThreeDS threeDS;

	public Recurring getRecurring() {
		return recurring;
	}
	public void setRecurring(Recurring recurring) {
		this.recurring = recurring;
	}
	/*public ThreeDS getThreeDS() {
		return threeDS;
	}
	public void setThreeDS(ThreeDS threeDS) {
		this.threeDS = threeDS;
	}*/
	public Paypal_transaction_details getPaypal_transaction_details() {
		return paypal_transaction_details;
	}
	public void setPaypal_transaction_details(
			Paypal_transaction_details paypal_transaction_details) {
		this.paypal_transaction_details = paypal_transaction_details;
	}
	public String getSplitShipment() {
		return splitShipment;
	}
	public void setSplitShipment(String splitShipment) {
		this.splitShipment = splitShipment;
	}

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("paypal_transaction_details")
	private Paypal_transaction_details paypal_transaction_details;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("split_shipment")
	private String splitShipment;

	
	@JsonInclude(Include.NON_NULL)
	public String getEciindicator() {
		return eciindicator;
	}
	@JsonInclude(Include.NON_NULL)
	public void setEciindicator(String eciindicator) {
		this.eciindicator = eciindicator;
	}
	@JsonInclude(Include.NON_NULL)
	public Level2 getLevel2() {
		return level2;
	}

	@JsonInclude(Include.NON_NULL)
	public void setLevel2(Level2 level2) {
		this.level2 = level2;
	}
	@JsonInclude(Include.NON_NULL)
	public Level3 getLevel3() {
		return level3;
	}
	@JsonInclude(Include.NON_NULL)
	public void setLevel3(Level3 level3) {
		this.level3 = level3;
	}
	@JsonInclude(Include.NON_NULL)
	public Line_item[] getLineitems() {
		return lineitems;
	}
	@JsonInclude(Include.NON_NULL)
	public void setLineitems(Line_item[] lineitems2) {
		this.lineitems = lineitems2;
	}

	
	//@JsonProperty("callback")
		private String callback;
		
		//@JsonProperty("authorization")
		private String auth;
		
		@JsonProperty("type")
		private String Type;
		
		public String getType() {
			return Type;
		}
		public void setType(String type) {
			Type = type;
		}

		@JsonProperty("ta_token")
		private String ta_token;
		
		public String getTa_token() {
			return ta_token;
		}
		public void setTa_token(String ta_token) {
			this.ta_token = ta_token;
		}
		public String getAuth() {
			return auth;
		}
		public void setAuth(String auth) {
			this.auth = auth;
		}
		public String getCallback() {
			return callback;
		}
		public void setCallback(String callback) {
			this.callback = callback;
		}
	
}
