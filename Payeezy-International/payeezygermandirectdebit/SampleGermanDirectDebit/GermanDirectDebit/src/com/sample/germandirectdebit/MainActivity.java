package com.sample.germandirectdebit;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.payeezygermandirectdebit.R;
import com.sdk.germandirectdebit.RequestTask;


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

public void onAVSPurchaseVoid(View view)
{
	RequestTask rTask5 = new RequestTask(getApplicationContext());

	String method="direct_debit";
	String transaction_type="purchase";
	String amount="1000";
	String currency_code="EUR";
	String iban="DE34500100600032121604";
	String mandate_ref="ABCD1234";
	String bic="PBNKDEFFXXX";

	String name="P.Kunde";
	String city="random";
	String country="GERMANY";
	String email="Monalisa.mishra@firstdata.com";
	String phonetype="cell";
	String number="678-468-2665";

	String street="LangeStr.12";
	String state_province="LEIPZIG";
	String zip_postal_code="04103";

	rTask5.execute("gdavspurchasevoid",method,transaction_type,amount,currency_code,iban,mandate_ref,bic,name,city,country,email,
			phonetype,number,street,state_province,zip_postal_code);

	System.out.println("gdavspurchasevoid call end");
}
	public void onAVSPurchaseRefund(View view)
	{
			RequestTask rTask5 = new RequestTask(getApplicationContext());

		String method="direct_debit";
		String transaction_type="purchase";
		String amount="1000";
		String currency_code="EUR";
		String iban="DE34500100600032121604";
		String mandate_ref="ABCD1234";
		String bic="PBNKDEFFXXX";

		String name="P.Kunde";
		String city="random";
		String country="GERMANY";
		String email="Monalisa.mishra@firstdata.com";
		String phonetype="cell";
		String number="678-468-2665";

		String street="LangeStr.12";
		String state_province="LEIPZIG";
		String zip_postal_code="04103";


		rTask5.execute("gdavspurchaserefund",method,transaction_type,amount,currency_code,iban,mandate_ref,bic,name,city,country,email,
				phonetype,number,street,state_province,zip_postal_code);

		System.out.println("gdavspurchaserefund call end");
	}
	public void onAVSCreditVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		String method="direct_debit";
		String transaction_type="purchase";
		String amount="1000";
		String currency_code="EUR";
		String iban="DE34500100600032121604";
		String mandate_ref="ABCD1234";
		String bic="PBNKDEFFXXX";

		String name="P.Kunde";
		String city="random";
		String country="GERMANY";
		String email="Monalisa.mishra@firstdata.com";
		String phonetype="cell";
		String number="678-468-2665";

		String street="LangeStr.12";
		String state_province="LEIPZIG";
		String zip_postal_code="04103";

		rTask5.execute("gdavscreditvoid",method,transaction_type,amount,currency_code,iban,mandate_ref,bic,name,city,country,email,
				phonetype,number,street,state_province,zip_postal_code);

		System.out.println("gdavscreditvoid call end");
	}

	public void onSDPurchaseVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String method="direct_debit";
		String transaction_type="purchase";
		String amount="1000";
		String currency_code="EUR";
		String iban="DE34500100600032121604";
		String mandate_ref="ABCD1234";
		String bic="PBNKDEFFXXX";

		String dba_name="SoftDesc 1";
		String street="123 Main Street";
		String region="NY";
		String mid="367337278884";
		String mcc="8812";
		String postal_code="11375";
		String country_code="USA";
		String merchant_contact_info="123 Main street";
		rTask5.execute("gdsdpurchasevoid",method,transaction_type,amount,currency_code,iban,mandate_ref,bic,dba_name,street,
				region,mid,	mcc,postal_code,country_code,merchant_contact_info	);
		System.out.println("gdsdpurchasevoid call end");
	}

	public void onSDPurchaseRefund(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String method="direct_debit";
		String transaction_type="purchase";
		String amount="1000";
		String currency_code="EUR";
		String iban="DE34500100600032121604";
		String mandate_ref="ABCD1234";
		String bic="PBNKDEFFXXX";

		String dba_name="SoftDesc 1";
		String street="123 Main Street";
		String region="NY";
		String mid="367337278884";
		String mcc="8812";
		String postal_code="11375";
		String country_code="USA";
		String merchant_contact_info="123 Main street";
		rTask5.execute("gdsdpurchaserefund",method,transaction_type,amount,currency_code,iban,mandate_ref,bic,dba_name,street,
				region,mid,	mcc,postal_code,country_code,merchant_contact_info	);

		System.out.println("gdsdpurchaserefund call end");
	}

	public void onSDCreditVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String method="direct_debit";
		String transaction_type="credit";
		String amount="1000";
		String currency_code="EUR";
		String iban="DE34500100600032121604";
		String mandate_ref="ABCD1234";
		String bic="PBNKDEFFXXX";

		String dba_name="SoftDesc 1";
		String street="123 Main Street";
		String region="NY";
		String mid="367337278884";
		String mcc="8812";
		String postal_code="11375";
		String country_code="USA";
		String merchant_contact_info="123 Main street";
		rTask5.execute("gdsdcreditvoid",method,transaction_type,amount,currency_code,iban,mandate_ref,bic,dba_name,street,
				region,mid,	mcc,postal_code,country_code,merchant_contact_info	);

		System.out.println("gdsdcreditvoid call end");
	}

	//L2L3

	public void onL2L3PurchaseVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		String method="direct_debit";
		String transaction_type="credit";
		String amount="1000";
		String currency_code="EUR";
		String iban="DE34500100600032121604";
		String mandate_ref="ABCD1234";
		String bic="PBNKDEFFXXX";



String tax1_amount="10";
	String tax1_number="2";
	String tax2_amount="5";
	String tax2_number="3";
	String customer_ref="customer1";

String alt_tax_amount="10";
		String alt_tax_id="098841111";
		String discount_amount="1";
		String duty_amount="0.5";
		String freight_amount="5";
		String ship_from_zip="11235";
		String city="New York";
		String state="NY";
		String zip="11235";
		String country="USA";
		String email="abc@firstdata.com";
		String name="Bob Smith";
		String phone="212-515-1111";
		String address_1="123 Main Street";
		String customer_number="12345";


String description="item 1";
String quantity="5";
String commodity_code="C";
String discount_amountlineitems="1";
String discount_indicator="G";
String gross_net_indicator="P";
String line_item_total="10";
String product_code="F";
String tax_amount="5";
String tax_rate="0.2800000000000000266453525910037569701671600341796875";
String tax_type="Federal";
String unit_cost="1";
String unit_of_measure="meters";

		rTask5.execute("gdl2l3purchasevoid",method,transaction_type,amount,currency_code,iban,mandate_ref,bic,tax1_amount,
		tax1_number,tax2_amount,tax2_number,customer_ref,alt_tax_amount,alt_tax_id,discount_amount,duty_amount,
		freight_amount,ship_from_zip,city,state,zip,country,email,name,phone,address_1,customer_number,description,
		quantity,commodity_code,discount_amountlineitems,discount_indicator,gross_net_indicator,line_item_total,
		product_code,tax_amount,tax_rate,tax_type,unit_cost,unit_of_measure);



		System.out.println("gdl2l3purchasevoid call end");
	}

	public void onL2L3PurchaseRefund(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		String method="direct_debit";
		String transaction_type="credit";
		String amount="1000";
		String currency_code="EUR";
		String iban="DE34500100600032121604";
		String mandate_ref="ABCD1234";
		String bic="PBNKDEFFXXX";



		String tax1_amount="10";
		String tax1_number="2";
		String tax2_amount="5";
		String tax2_number="3";
		String customer_ref="customer1";

		String alt_tax_amount="10";
		String alt_tax_id="098841111";
		String discount_amount="1";
		String duty_amount="0.5";
		String freight_amount="5";
		String ship_from_zip="11235";
		String city="New York";
		String state="NY";
		String zip="11235";
		String country="USA";
		String email="abc@firstdata.com";
		String name="Bob Smith";
		String phone="212-515-1111";
		String address_1="123 Main Street";
		String customer_number="12345";


		String description="item 1";
		String quantity="5";
		String commodity_code="C";
		String discount_amountlineitems="1";
		String discount_indicator="G";
		String gross_net_indicator="P";
		String line_item_total="10";
		String product_code="F";
		String tax_amount="5";
		String tax_rate="0.2800000000000000266453525910037569701671600341796875";
		String tax_type="Federal";
		String unit_cost="1";
		String unit_of_measure="meters";

		rTask5.execute("gdl2l3purchaserefund",method,transaction_type,amount,currency_code,iban,mandate_ref,bic,tax1_amount,
				tax1_number,tax2_amount,tax2_number,customer_ref,alt_tax_amount,alt_tax_id,discount_amount,duty_amount,
				freight_amount,ship_from_zip,city,state,zip,country,email,name,phone,address_1,customer_number,description,
				quantity,commodity_code,discount_amountlineitems,discount_indicator,gross_net_indicator,line_item_total,
				product_code,tax_amount,tax_rate,tax_type,unit_cost,unit_of_measure);





		System.out.println("gdl2l3purchaserefund call end");
	}

	public void onL2L3CreditVoid(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		String method="direct_debit";
		String transaction_type="credit";
		String amount="1000";
		String currency_code="EUR";
		String iban="DE34500100600032121604";
		String mandate_ref="ABCD1234";
		String bic="PBNKDEFFXXX";



		String tax1_amount="10";
		String tax1_number="2";
		String tax2_amount="5";
		String tax2_number="3";
		String customer_ref="customer1";

		String alt_tax_amount="10";
		String alt_tax_id="098841111";
		String discount_amount="1";
		String duty_amount="0.5";
		String freight_amount="5";
		String ship_from_zip="11235";
		String city="New York";
		String state="NY";
		String zip="11235";
		String country="USA";
		String email="abc@firstdata.com";
		String name="Bob Smith";
		String phone="212-515-1111";
		String address_1="123 Main Street";
		String customer_number="12345";


		String description="item 1";
		String quantity="5";
		String commodity_code="C";
		String discount_amountlineitems="1";
		String discount_indicator="G";
		String gross_net_indicator="P";
		String line_item_total="10";
		String product_code="F";
		String tax_amount="5";
		String tax_rate="0.2800000000000000266453525910037569701671600341796875";
		String tax_type="Federal";
		String unit_cost="1";
		String unit_of_measure="meters";

		rTask5.execute("gdl2l3creditvoid",method,transaction_type,amount,currency_code,iban,mandate_ref,bic,tax1_amount,
				tax1_number,tax2_amount,tax2_number,customer_ref,alt_tax_amount,alt_tax_id,discount_amount,duty_amount,
				freight_amount,ship_from_zip,city,state,zip,country,email,name,phone,address_1,customer_number,description,
				quantity,commodity_code,discount_amountlineitems,discount_indicator,gross_net_indicator,line_item_total,
				product_code,tax_amount,tax_rate,tax_type,unit_cost,unit_of_measure);


		System.out.println("gdl2l3creditvoid call end");
	}

}
