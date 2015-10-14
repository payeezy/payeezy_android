package com.sample.dccUS;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.sdk.dccUS.RequestTask;


@SuppressLint("DefaultLocale")
public class MainActivity extends Activity {

	private static Context context;


    public void onCreate(){
        MainActivity.context = getApplicationContext();

    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

public static TextView displayResult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		displayResult = (TextView) findViewById(R.id.textView1);
	}
/**************DCC**********************/
	//PurchaseVoid-CVVCardRate
public void onDCCCVVPurchaseVoidCardRate(View view)
{
	RequestTask rTask5 = new RequestTask(getApplicationContext());

	String	rate_type="card_rate";
	//String		bin="438980";
	String		bin="547132";
	String		ratetypeamount="100";



	String	amount="100";
	String		transaction_type="Purchase";
	String		method="credit_card";
	//String		currency_code="GBP";
	String		currency_code="USD";
	String		credit_cardtype="visa";
	String		cardholder_name="DCC Demo testing";
	String			card_number="5471320000000001";
	String			exp_date="0416";
	String			cvv="1234";


	//String type="dcc";
/*	String	rate_id="125962";
	String dcc_accepted="true";
*/
	rTask5.execute("dcccvvpurchasevoid",rate_type,bin,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
			card_number,exp_date,cvv);

	System.out.println("gdavspurchasevoid call end");
}

	//PurchaserEFUND-CVV
	public void onDCCCVVPurchaseRefundCardRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="card_rate";
		String		bin="547132";
		String		ratetypeamount="100";


		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5471320000000001";
		String			exp_date="0416";
		String			cvv="1234";

	//	String type="dcc";
	/*	String	rate_id="125962";
		String dcc_accepted="true";
*/
		rTask5.execute("dcccvvpurchaserefund",rate_type,bin,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv);

		System.out.println("dcccvvpurchaserefund call end");
	}
//PurchaseVoid-AVS
	public void onDCCAVSPurchaseVoidCardRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="card_rate";
		String		bin="547132";
		String		ratetypeamount="100";


		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5471320000000001";
		String			exp_date="0416";
		String			cvv="1234";

	/*	String	rate_id="125962";
		String dcc_accepted="true";
*/
		String city="St. Louis";
		String country="US";
		String email="abc@main.com";

		String phoneType="home";
		String number="212-515-1212";

		String street="12115 LACKLAND";
		String state_province="MD";
		String zip_postal_code="63146";



		rTask5.execute("dccavspurchasevoid",rate_type,bin,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,city,country,email,phoneType,number,street,state_province,zip_postal_code);

		System.out.println("gdavspurchasevoid call end");
	}
//PurchaseRefund-AVS
	public void onDCCAVSPurchaseRefundCardRate(View view) {
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="card_rate";
		String		bin="547132";
		String		ratetypeamount="100";


		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5471320000000001";
		String			exp_date="0416";
		String			cvv="1234";

		String rate_id = "125962";
		String dcc_accepted = "true";

		String city="St. Louis";
		String country="US";
		String email="abc@main.com";

		String phoneType="home";
		String number="212-515-1212";

		String street="12115 LACKLAND";
		String state_province="MD";
		String zip_postal_code="63146";



		rTask5.execute("dccavspurchaserefund",rate_type,bin,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,city,country,email,phoneType,number,street,state_province,zip_postal_code);

		System.out.println("dccavspurchaserefund call end");
	}

	//PurchaseVoid-SoftDescriptor
	public void onDCCSDPurchaseVoidCardRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="card_rate";
		String		bin="547132";
		String		ratetypeamount="100";


		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5471320000000001";
		String			exp_date="0416";
		String			cvv="1234";

	/*	String	rate_id="125962";
		String dcc_accepted="true";
	*/
		String dba_name="SoftDesc 1";
		String street="123 Main Street";
		String region="NY";
		String mid="367337278884";
		String mcc="8812";
		String postal_code="11375";
		String country_code="USA";
		String merchant_contact_info="123 Main street";


		rTask5.execute("dccsdpurchasevoid",rate_type,bin,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,dba_name,street,region,mid,mcc,postal_code,country_code,merchant_contact_info);

		System.out.println("dccsdpurchasevoid call end");
	}

	//SoftDescriptor-PurchaseRefund
	public void onDCCSDPurchaseRefundCardRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="card_rate";
		String		bin="547132";
		String		ratetypeamount="100";


		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5471320000000001";
		String			exp_date="0416";
		String			cvv="1234";
	/*	String	rate_id="125962";
		String dcc_accepted="true";
	*/
		String dba_name="SoftDesc 1";
		String street="123 Main Street";
		String region="NY";
		String mid="367337278884";
		String mcc="8812";
		String postal_code="11375";
		String country_code="USA";
		String merchant_contact_info="123 Main street";


		rTask5.execute("dccsdpurchaserefund",rate_type,bin,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,dba_name,street,region,mid,mcc,postal_code,country_code,merchant_contact_info);

		System.out.println("dccasdpurchaserefund call end");
	}

	//PurchaseVoid-CVVCardRate
	public void onDCCCVVPurchaseVoidMerchantRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="merchant_rate";
		String		merchantratecurrency_code="AUD";
		String		ratetypeamount="100";




		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="4072640000000008";
		String			exp_date="0416";
		String			cvv="1234";


/*	String	rate_id="125962";
	String dcc_accepted="true";
*/
		rTask5.execute("dcccvvpurchasevoidMerchantRate",rate_type,merchantratecurrency_code,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv);

		System.out.println("dcccvvpurchasevoidMerchantRate call end");
	}

	//PurchaserEFUND-CVV
	public void onDCCCVVPurchaseRefundMerchantRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="merchant_rate";
		String		merchantratecurrency_code="AUD";
		String		ratetypeamount="100";




		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="4072640000000008";
		String			exp_date="0416";
		String			cvv="1234";
	/*	String	rate_id="125962";
		String dcc_accepted="true";
*/
		rTask5.execute("dcccvvpurchaserefundMerchantRate",rate_type,merchantratecurrency_code,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv);

		System.out.println("dcccvvpurchaserefundMerchantRate call end");
	}
	//PurchaseVoid-AVS
	public void onDCCAVSPurchaseVoidMerchantRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="merchant_rate";
		String		merchantratecurrency_code="AUD";
		String		ratetypeamount="100";




		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="4072640000000008";
		String			exp_date="0416";
		String			cvv="1234";

	/*	String	rate_id="125962";
		String dcc_accepted="true";
*/
		String city="St. Louis";
		String country="US";
		String email="abc@main.com";

		String phoneType="home";
		String number="212-515-1212";

		String street="12115 LACKLAND";
		String state_province="MD";
		String zip_postal_code="63146";



		rTask5.execute("dccavspurchasevoidMerchantRate",rate_type,merchantratecurrency_code,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,city,country,email,phoneType,number,street,state_province,zip_postal_code);

		System.out.println("gdavspurchasevoidMerchantRate call end");
	}
	//PurchaseRefund-AVS
	public void onDCCAVSPurchaseRefundMerchantRate(View view) {
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="merchant_rate";
		String		merchantratecurrency_code="AUD";
		String		ratetypeamount="100";




		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="4072640000000008";
		String			exp_date="0416";
		String			cvv="1234";



		String city="St. Louis";
		String country="US";
		String email="abc@main.com";

		String phoneType="home";
		String number="212-515-1212";

		String street="12115 LACKLAND";
		String state_province="MD";
		String zip_postal_code="63146";



		rTask5.execute("dccavspurchaserefundMerchantRate",rate_type,merchantratecurrency_code,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,city,country,email,phoneType,number,street,state_province,zip_postal_code);

		System.out.println("dccavspurchaserefundMerchantRate call end");
	}

	//PurchaseVoid-SoftDescriptor
	public void onDCCSDPurchaseVoidMerchantRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="merchant_rate";
		String		merchantratecurrency_code="AUD";
		String		ratetypeamount="100";




		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="4072640000000008";
		String			exp_date="0416";
		String			cvv="1234";

	/*	String	rate_id="125962";
		String dcc_accepted="true";
	*/
		String dba_name="SoftDesc 1";
		String street="123 Main Street";
		String region="NY";
		String mid="367337278884";
		String mcc="8812";
		String postal_code="11375";
		String country_code="USA";
		String merchant_contact_info="123 Main street";


		rTask5.execute("dccsdpurchasevoidMerchantRate",rate_type,merchantratecurrency_code,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,dba_name,street,region,mid,mcc,postal_code,country_code,merchant_contact_info);

		System.out.println("dccsdpurchasevoidMerchantRate call end");
	}

	//SoftDescriptor-PurchaseRefund
	public void onDCCSDPurchaseRefundMerchantRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="merchant_rate";
		String		merchantratecurrency_code="AUD";
		String		ratetypeamount="100";




		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="4072640000000008";
		String			exp_date="0416";
		String			cvv="1234";

	/*	String	rate_id="125962";
		String dcc_accepted="true";
	*/
		String dba_name="SoftDesc 1";
		String street="123 Main Street";
		String region="NY";
		String mid="367337278884";
		String mcc="8812";
		String postal_code="11375";
		String country_code="USA";
		String merchant_contact_info="123 Main street";


		rTask5.execute("dccsdpurchaserefundMerchantRate",rate_type,merchantratecurrency_code,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,dba_name,street,region,mid,mcc,postal_code,country_code,merchant_contact_info);

		System.out.println("dccasdpurchaserefundMerchantRate call end");
	}

	//CVV- Naked Refund CardRate
	public void onDCCCVVNakedRefundCardRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="card_rate";
		//String		bin="438980";
		String		bin="547132";
		String		ratetypeamount="100";



		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5471320000000001";
		String			exp_date="0416";
		String			cvv="1234";



		rTask5.execute("dcccvvnakedrefund",rate_type,bin,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv);

		System.out.println("dcccvvnakedrefund call end");
	}

	//DCC-AVS-CardRate-NakedRefund
	public void onDCCAVSNakedRefundCardRate(View view){
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="card_rate";
		//String		bin="438980";
		String		bin="547132";
		String		ratetypeamount="100";



		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5471320000000001";
		String			exp_date="0416";
		String			cvv="1234";


		String city="St. Louis";
		String country="US";
		String email="abc@main.com";

		String phoneType="home";
		String number="212-515-1212";

		String street="12115 LACKLAND";
		String state_province="MD";
		String zip_postal_code="63146";



		rTask5.execute("dccavsnakedrefund",rate_type,bin,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,city,country,email,phoneType,number,street,state_province,zip_postal_code);

		System.out.println("dccavsnakedrefund call end");
	}

	//DCC-SD-NakedRefund CardRate
	public void onDCCSDNakedRefundCardRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());


		String	rate_type="card_rate";
		//String		bin="438980";
		String		bin="547132";
		String		ratetypeamount="100";



		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		//String		currency_code="GBP";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5471320000000001";
		String			exp_date="0416";
		String			cvv="1234";


	/*	String	rate_id="125962";
		String dcc_accepted="true";
	*/
		String dba_name="SoftDesc 1";
		String street="123 Main Street";
		String region="NY";
		String mid="367337278884";
		String mcc="8812";
		String postal_code="11375";
		String country_code="USA";
		String merchant_contact_info="123 Main street";


		rTask5.execute("dccsdpurchaserefund",rate_type,bin,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,dba_name,street,region,mid,mcc,postal_code,country_code,merchant_contact_info);

		System.out.println("dccasdpurchaserefundMerchantRate call end");
	}

	//DCC-CVV-NakedRefund-MerchantRate
	public void onDCCCVVNakedRefundMerchantRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="merchant_rate";
		String		merchantratecurrency_code="EUR";
		String		ratetypeamount="100";


		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5100870000000004";
		String			exp_date="0416";
		String			cvv="1234";


	/*	String	rate_id="125962";
		String dcc_accepted="true";
*/
		rTask5.execute("dcccvvnakedrefundMerchantRate",rate_type,merchantratecurrency_code,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv);

		System.out.println("dcccvvnakedrefundMerchantRate call end");
	}
	//DCC-AVS-NakedRefund-MerchantRate
	public void onDCCAVSNakedRefundMerchantRate(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="merchant_rate";
		String		merchantratecurrency_code="EUR";
		String		ratetypeamount="100";


		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5100870000000004";
		String			exp_date="0416";
		String			cvv="1234";



		String city="St. Louis";
		String country="US";
		String email="abc@main.com";

		String phoneType="home";
		String number="212-515-1212";

		String street="12115 LACKLAND";
		String state_province="MD";
		String zip_postal_code="63146";



		rTask5.execute("dccavsnakedrefundMerchantRate",rate_type,merchantratecurrency_code,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,city,country,email,phoneType,number,street,state_province,zip_postal_code);

		System.out.println("dccavsnakedrefundMerchantRate call end");
	}
	//DCC-SD-NakedRefund-MerchantRate
	public void onDCCSDNakedRefundMerchantRate(View view){
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String	rate_type="merchant_rate";
		String		merchantratecurrency_code="EUR";
		String		ratetypeamount="100";


		String	amount="100";
		String		transaction_type="Purchase";
		String		method="credit_card";
		String		currency_code="USD";
		String		credit_cardtype="visa";
		String		cardholder_name="DCC Demo testing";
		String			card_number="5100870000000004";
		String			exp_date="0416";
		String			cvv="1234";


	/*	String	rate_id="125962";
		String dcc_accepted="true";
	*/
		String dba_name="SoftDesc 1";
		String street="123 Main Street";
		String region="NY";
		String mid="367337278884";
		String mcc="8812";
		String postal_code="11375";
		String country_code="USA";
		String merchant_contact_info="123 Main street";


		rTask5.execute("dccsdnakedrefundMerchantRate",rate_type,merchantratecurrency_code,ratetypeamount,amount,transaction_type,method,currency_code,credit_cardtype,cardholder_name,
				card_number,exp_date,cvv,dba_name,street,region,mid,mcc,postal_code,country_code,merchant_contact_info);

		System.out.println("dccasdnakedrefundMerchantRate call end");
	}
	/********************GermanDirectDebit*****************/
	public void onAVSPurchaseVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("gdavspurchasevoid");

		System.out.println("gdavspurchasevoid call end");
	}
	public void onAVSPurchaseRefund(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("gdavspurchaserefund");
		System.out.println("gdavspurchaserefund call end");
	}
	public void onAVSCreditVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("gdavscreditvoid");
		System.out.println("gdavscreditvoid call end");
	}

	public void onSDPurchaseVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("gdsdpurchasevoid");
		System.out.println("gdsdpurchasevoid call end");
	}

	public void onSDPurchaseRefund(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("gdsdpurchaserefund");
		System.out.println("gdsdpurchaserefund call end");
	}

	public void onSDCreditVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("gdsdcreditvoid");
		System.out.println("gdsdcreditvoid call end");
	}

	//L2L3

	public void onL2L3PurchaseVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("gdl2l3purchasevoid");
		System.out.println("gdl2l3purchasevoid call end");
	}

	public void onL2L3PurchaseRefund(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("gdl2l3purchaserefund");
		System.out.println("gdl2l3purchaserefund call end");
	}

	public void onL2L3CreditVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("gdl2l3creditvoid");
		System.out.println("gdl2l3creditvoid call end");
	}

}
