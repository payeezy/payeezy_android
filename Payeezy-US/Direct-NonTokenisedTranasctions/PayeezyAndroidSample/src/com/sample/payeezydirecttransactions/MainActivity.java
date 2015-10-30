package com.sample.payeezydirecttransactions;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.payeezydirecttransactions.R;
import com.firstdata.firstapi.client.domain.TransactionType;
import com.firstdata.firstapi.client.domain.v2.Address;
import com.firstdata.firstapi.client.domain.v2.Card;
import com.firstdata.firstapi.client.domain.v2.Level2;
import com.firstdata.firstapi.client.domain.v2.Level3;
import com.firstdata.firstapi.client.domain.v2.Line_item;
import com.firstdata.firstapi.client.domain.v2.Ship_to_address;
import com.firstdata.firstapi.client.domain.v2.SoftDescriptor;
import com.firstdata.firstapi.client.domain.v2.ThreeDomainSecureToken;
import com.sdk.payeezydirecttransactions.RequestTask;


@SuppressLint("DefaultLocale")
public class MainActivity extends Activity {

	private static Context context;

	public static TextView displayResult;
    public static Context getAppContext() {
        return MainActivity.context;
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		displayResult = (TextView) findViewById(R.id.textView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onAuthorize(View view)
	{
		Toast.makeText(getApplicationContext(), " Authorize Clicked", Toast.LENGTH_SHORT).show();

		String amount="1100";
		String currency="USD";
		String paymentMethod="credit_card";
		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";

		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());
			rTask.execute("authorize",amount,currency,paymentMethod,cvv,expiryDate,Name,Type,number,state,addressline1,zip,country,city);
			System.out.println("first authorize call end");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");

	}
	public void onPurchase(View view)
	{
		Toast.makeText(getApplicationContext(), " Purchase Clicked", Toast.LENGTH_SHORT).show();
		String amount="1100";
		String currency="USD";
		String paymentMethod="credit_card";

		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("purchase",amount,currency,paymentMethod,cvv,expiryDate,Name,Type,number,state,addressline1,zip,country,city);
		
	}
	
	public void onCapture(View view)
	{
		Toast.makeText(getApplicationContext(), " Capture Clicked", Toast.LENGTH_SHORT).show();

		RequestTask rTask = new RequestTask(getApplicationContext());
		String amount="1100";
		String currency="USD";
		String paymentMethod="credit_card";
		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";

		rTask.execute("capture",amount,currency,paymentMethod,cvv,expiryDate,Name,Type,number,state,addressline1,zip,country,city);

	}
	
	public void onRefund(View view)
	{
		Toast.makeText(getApplicationContext(), " Refund Clicked", Toast.LENGTH_SHORT).show();

		RequestTask rTask = new RequestTask(getApplicationContext());
		String amount="1100";
		String currency="USD";
		String paymentMethod="credit_card";

		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";

		rTask.execute("refund",amount,currency,paymentMethod,cvv,expiryDate,Name,Type,number,state,addressline1,zip,country,city);



	}
	
	public void onVoid(View view)
	{
		Toast.makeText(getApplicationContext(), " Void Clicked", Toast.LENGTH_SHORT).show();

		RequestTask rTask = new RequestTask(getApplicationContext());
		String amount="1100";
		String currency="USD";
		String paymentMethod="credit_card";
		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";


			rTask.execute("void",amount,currency,paymentMethod,cvv,expiryDate,Name,Type,number,state,addressline1,zip,country,city);


	}
	public void onValuelink(View view)
	{
		Toast.makeText(getApplicationContext(), " ValueLink Clicked", Toast.LENGTH_SHORT).show();
		try
		{
			
			RequestTask rTask5 = new RequestTask(getApplicationContext());
			rTask5.execute("valuelink");

			System.out.println("ValueLink call end");
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("valuelink call end");

	}
	public void onTelecheck(View view)
	{
		Toast.makeText(getApplicationContext(), " Telecheck Clicked", Toast.LENGTH_SHORT).show();
		try
		{
			
			RequestTask rTask5 = new RequestTask(getApplicationContext());
			rTask5.execute("telecheck");
			//rTask5.wait();
			
			System.out.println("Telecheck call end");
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("telecheck call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}
	public void onAVS(View view)
	{
		String amount="1100";
		String currency="USD";
		String paymentMethod="credit_card";

		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";


		String phoneNumber="212-515-1212";
		String phoneType="cell";
		Toast.makeText(getApplicationContext(), " avs Clicked", Toast.LENGTH_SHORT).show();

		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorizeavs",amount,currency,paymentMethod,cvv,expiryDate,Name,Type,number,state,addressline1,zip,country,city,
				phoneNumber,phoneType);

	}
	
	public void onLevel2(View view)
	{
		String amount="1100";
		String currency="USD";
		String paymentMethod="credit_card";

		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";

		 String level2_tax2_number = "3";
		 String level2_tax2_amount = "5";
		 String level2_tax1_number = "2";
		 String level2_tax1_amount = "10";
		 String level2_customer_ref = "customer1";

		Toast.makeText(getApplicationContext(), " level2 Clicked", Toast.LENGTH_SHORT).show();

		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorizelevel2", amount, currency, paymentMethod, cvv, expiryDate, Name, Type, number, state, addressline1, zip, country, city,
				level2_tax2_number, level2_tax2_amount, level2_tax1_number, level2_tax1_amount, level2_customer_ref);

	}
	
	public void onLevel3(View view)
	{
		String amount="1100";
		String currency="USD";
		String paymentMethod="credit_card";

		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";

		 String level3_duty_amount = "0.5";


		 String level3_shiptoaddress_Cust_number = "12345";
		 String level3_shiptoaddress_Phone = "212-515-1111";
		 String level3_shiptoaddress_Email = "abc@firstdata.com";
		 String level3_shiptoaddress_Name = "Bob Smith";
		 String level3_shiptoaddress_State = "NY";
		 String level3_shiptoaddress_Zip = "11235";
		 String level3_shiptoaddress_Address_1 = "123 Main Street";
		 String level3_shiptoaddress_Country = "USA";
		 String level3_shiptoaddress_City = "New York";

		 String level3_alt_tax_id = "098841111";
		 String level3_alt_tax_amount = "10";
		 String level3_freight_amount = "5";
		 String level3_ship_from_zip = "11235";
		 String level3_discount_amount = "1";

		//line_items
		 String litem_tax_type = "Federal";
		 String litem_unit_cost = "1";
		 String litem_discount_amount = "1";
		 String litem_line_item_total = "10";
		 String litem_unit_of_measure = "meters";
		 String litem_discount_indicator = "G";
		 String litem_gross_net_indicator = "P";
		 String litem_description = "item 1";
		 String litem_commodity_code = "C";
		 String litem_product_code = "F";
		 String litem_tax_amount = "5";
		 String litem_quantity = "5";
		 String litem_tax_rate = "0.2800000000000000266453525910037569701671600341796875";

		 String level3_litem_tax_type = "Federal";
		 String level3_litem_unit_cost = "1";
		 String level3_litem_discount_amount = "1";
		 String level3_litem_line_item_total = "10";
		 String level3_litem_unit_of_measure = "meters";
		 String level3_litem_discount_indicator = "G";
		 String level3_litem_gross_net_indicator = "P";
		 String level3_litem_description = "item 1";
		 String level3_litem_commodity_code = "C";
		 String level3_litem_product_code = "F";
		 String level3_litem_tax_amount = "5";
		 String level3_litem_quantity = "5";
		 String level3_litem_tax_rate = "0.2800000000000000266453525910037569701671600341796875";

		//l2

		"level2":{"customer_ref":"customer1","tax1_amount":"10","tax1_number":"2","tax2_amount":"5","tax2_number":"3"},

		String level2_tax2_number = "3";
		String level2_tax2_amount = "5";
		String level2_tax1_number = "2";
		String level2_tax1_amount = "10";
		String level2_customer_ref = "customer1";

		Toast.makeText(getApplicationContext(), " level3 Clicked", Toast.LENGTH_SHORT).show();

		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorizelevel3", amount, currency, paymentMethod, cvv, expiryDate, Name, Type, number, state, addressline1, zip, country, city,
				level3_duty_amount,level3_shiptoaddress_Cust_number,level3_shiptoaddress_Phone,level3_shiptoaddress_Email,level3_shiptoaddress_Name,
				level3_shiptoaddress_State,level3_shiptoaddress_Zip,level3_shiptoaddress_Address_1,level3_shiptoaddress_Country,
				level3_shiptoaddress_City,level3_alt_tax_id,level3_alt_tax_amount,level3_freight_amount,level3_ship_from_zip,level3_discount_amount,
				litem_tax_type,litem_unit_cost,litem_discount_amount,litem_line_item_total,litem_unit_of_measure,litem_discount_indicator,
				litem_gross_net_indicator,litem_description,litem_commodity_code,litem_product_code,litem_tax_amount,litem_quantity,litem_tax_rate,
				level3_litem_tax_type,level3_litem_unit_cost,level3_litem_discount_amount,level3_litem_line_item_total,level3_litem_unit_of_measure,
				level3_litem_discount_indicator,level3_litem_gross_net_indicator,level3_litem_description,level3_litem_commodity_code,level3_litem_product_code,
				level3_litem_tax_amount,level3_litem_quantity,level3_litem_tax_rate,level2_tax2_number,level2_tax2_amount,level2_tax1_number,
				level2_tax1_amount,level2_customer_ref);


	}
	
	public void on3DS(View view)
	{
		String amount="1100";
		String currency="USD";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";

		 String ThreeDS_card_number = "4788250000028291";
		 String ThreeDS_xid = "1234567";
		 String ThreeDS_cvv = "123";
		 String ThreeDS_exp_date = "1020";
		 String ThreeDS_cardholder_name = "xyz";
		 String ThreeDS_type = "D";
		 String ThreeDS_cavv = "01ade6ae340005c681c3a1890418b53000020000";

		 String method3DS = "3DS";
			Toast.makeText(getApplicationContext(), " 3DS Clicked", Toast.LENGTH_SHORT).show();

		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorize3ds",amount, currency, state, addressline1, zip, country, city,ThreeDS_card_number,ThreeDS_xid,ThreeDS_cvv,
				ThreeDS_exp_date,ThreeDS_cardholder_name,ThreeDS_type,ThreeDS_cavv,method3DS);

	}
	
	public void onSoftDescriptors(View view)
	{
		String amount="1100";
		String currency="USD";
		String paymentMethod="credit_card";

		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";

		 String sdescriptor_dba_name = "SoftDesc 1";
		 String sdescriptor_street = "123 Main Street";
		 String sdescriptor_sd_city = "New York";
		 String sdescriptor_region = "NY";
		 String sdescriptor_mid = "000367337278884";
		 String sdescriptor_mcc = "8812";
		 String sdescriptor_postalCode = "113750000000000";
		 String sdescriptor_countryCode = "USA";
		 String sdescriptor_merchantContactInfo = "123 Main street";




		Toast.makeText(getApplicationContext(), " Softdescriptors Clicked", Toast.LENGTH_SHORT).show();

		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorizesoftdescriptors" ,amount, currency, paymentMethod, cvv, expiryDate, Name, Type, number, state, addressline1, zip, country, city,
				sdescriptor_dba_name,sdescriptor_street,sdescriptor_sd_city,sdescriptor_region,	sdescriptor_mid,sdescriptor_mcc,sdescriptor_postalCode,
				sdescriptor_countryCode,sdescriptor_merchantContactInfo);

	}
	public void onRecurring(View view)
	{
		Toast.makeText(getApplicationContext(), " Recurring Clicked", Toast.LENGTH_SHORT).show();
		//CallCapture();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("recurring");
		//Toast.makeText(getApplicationContext(), " Capture Completed", Toast.LENGTH_SHORT).show();
	}
    
	public void onSplitShipments(View view)
	{
		String amount="1100";
		String currency="USD";
		String paymentMethod="credit_card";

		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		String state="NY";
		String addressline1="sss";
		String zip="11747";
		String country="US";
		String city="NY";
		 String split_shipment = "1/3";
		 String eci_indicator = "5";

		Toast.makeText(getApplicationContext(), " split shipments Clicked", Toast.LENGTH_SHORT).show();

		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorizesplitshipments",amount, currency, paymentMethod, cvv, expiryDate, Name, Type, number, state, addressline1, zip, country, city,
				split_shipment,eci_indicator);

	}
    
	public void onZeroDollar(View view)
	{
		String amount="0";
		String currency="USD";
		String paymentMethod="credit_card";

		String cvv="123";
		String expiryDate="1220";
		String Name="Test Data";
		String Type="visa";
		String number="4012000033330026";

		Toast.makeText(getApplicationContext(), " Zero Dollar Clicked", Toast.LENGTH_SHORT).show();

		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("zerodollar",amount, currency, paymentMethod, cvv, expiryDate, Name, Type, number);

	}

	public void onTransarmor(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("authorizetransarmor");

		System.out.println("authorizetransarmor authorize call end");
	}


}
