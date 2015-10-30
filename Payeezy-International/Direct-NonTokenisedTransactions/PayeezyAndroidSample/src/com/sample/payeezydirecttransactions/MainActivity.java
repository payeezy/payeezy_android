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

import com.example.payeezydirecttransactions.R;
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
	
//AVS Transactions
	public void onAVSAuthVoid(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());


			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			String city="St. Louis";
			String country="US";
			String phonetype="home";
			String phonenumber="212-515-1212";
			String street="12115 LACKLAND";
			String state_province="MO";
			String zip_postal_code="63146";
			String email="abc@main.com";

			rTask.execute("avsauthorizevoid",amount,method,currency_code,credit_card_type,cardholder_name,
					card_number,expdate,cvv,city,country,phonetype,phonenumber,street,state_province,zip_postal_code,email);
			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");

	}

	public void onAVSPurchaseVoid(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());
			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			String city="St. Louis";
			String country="US";
			String phonetype="home";
			String phonenumber="212-515-1212";
			String street="12115 LACKLAND";
			String state_province="MO";
			String zip_postal_code="63146";


			rTask.execute("avspurchasevoid",amount,method,currency_code,credit_card_type,cardholder_name,
					card_number,expdate,cvv,city,country,phonetype,phonenumber,street,state_province,zip_postal_code);

			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	public void onAVSAuthCapture(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());

			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			String city="St. Louis";
			String country="US";
			String phonetype="home";
			String phonenumber="212-515-1212";
			String street="12115 LACKLAND";
			String state_province="MO";
			String zip_postal_code="63146";


			rTask.execute("avsauthcapture",amount,method,currency_code,credit_card_type,cardholder_name,
					card_number,expdate,cvv,city,country,phonetype,phonenumber,street,state_province,zip_postal_code);

			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	public void onAVSPurchaseRefund(View view)
	{
		try {
			RequestTask rTask = new RequestTask(getApplicationContext());
			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			String city="St. Louis";
			String country="US";
			String phonetype="home";
			String phonenumber="212-515-1212";
			String street="12115 LACKLAND";
			String state_province="MO";
			String zip_postal_code="63146";


			rTask.execute("avspurchaserefund", amount, method, currency_code, credit_card_type, cardholder_name,
					card_number, expdate, cvv,city,country,phonetype,phonenumber,street,state_province,zip_postal_code);
			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	//3DS Transactions
	public void on3DSAuthVoid(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());
			String method="3DS";
			String amount="1100";

			String currency_code="GBP";
			String threeDS_type="D";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="977";
				String street="dsdsds";
				String state_province="NY";
				String zip_postal_code="11747";
				String city="New York";
				String country="US";
String cavv="jEET5Odser3oCRAyNTY5BVgAAAA";



				rTask.execute("3dsauthorizevoid",method,amount,currency_code,threeDS_type,cardholder_name,
						card_number,expdate,cvv,street,state_province,zip_postal_code,city,	country,cavv);
			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	public void on3DSPurchaseVoid(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());
			String method="3DS";
			String amount="1100";

			String currency_code="GBP";
			String threeDS_type="D";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			String street="dsdsds";
			String state_province="NY";
			String zip_postal_code="11747";
			String city="New York";
			String country="US";
			rTask.execute("3dspurchasevoid",method,amount,currency_code,threeDS_type,cardholder_name,
					card_number,expdate,cvv,street,state_province,zip_postal_code,city,	country);



			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	public void on3DSAuthCapture(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());
			String method="3DS";
			String amount="1100";

			String currency_code="GBP";
			String threeDS_type="D";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			String street="dsdsds";
			String state_province="NY";
			String zip_postal_code="11747";
			String city="New York";
			String country="US";

			rTask.execute("3dsauthcapture",method,amount,currency_code,threeDS_type,cardholder_name,
					card_number,expdate,cvv,street,state_province,zip_postal_code,city,	country);


			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	public void on3DSPurchaseRefund(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());
			String method="3DS";
			String amount="1100";

			String currency_code="GBP";
			String threeDS_type="D";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			String street="dsdsds";
			String state_province="NY";
			String zip_postal_code="11747";
			String city="New York";
			String country="US";

			rTask.execute("3dspurchaserefund",method,amount,currency_code,threeDS_type,cardholder_name,
					card_number,expdate,cvv,street,state_province,zip_postal_code,city,	country);


			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}


	//SD Transactions
	public void onSDAuthVoid(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());

			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";

			String dba_name="SoftDesc 1";
			String street="123 Main Street";
			String region="NY";
			String mid="367337278884";
			String mcc="8812";
			String postal_code="11375";
			String country_code="USA";
			String merchant_contact_info="123 Main street";

			rTask.execute("sdauthorizevoid",amount, method, currency_code, credit_card_type, cardholder_name,
					card_number, expdate, cvv,dba_name,street,region,mid,mcc,postal_code,country_code,merchant_contact_info);
			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	public void onSDPurchaseVoid(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());


			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";

			String dba_name="SoftDesc 1";
			String street="123 Main Street";
			String region="NY";
			String mid="367337278884";
			String mcc="8812";
			String postal_code="11375";
			String country_code="USA";
			String merchant_contact_info="123 Main street";

			rTask.execute("sdpurchasevoid",amount, method, currency_code, credit_card_type, cardholder_name,
					card_number, expdate, cvv,dba_name,street,region,mid,mcc,postal_code,country_code,merchant_contact_info);


			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	public void onSDAuthCapture(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());


			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";

			String dba_name="SoftDesc 1";
			String street="123 Main Street";
			String region="NY";
			String mid="367337278884";
			String mcc="8812";
			String postal_code="11375";
			String country_code="USA";
			String merchant_contact_info="123 Main street";

			rTask.execute("sdauthcapture",amount, method, currency_code, credit_card_type, cardholder_name,
					card_number, expdate, cvv,dba_name,street,region,mid,mcc,postal_code,country_code,merchant_contact_info);


			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	public void onSDPurchaseRefund(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());


			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";

			String dba_name="SoftDesc 1";
			String street="123 Main Street";
			String region="NY";
			String mid="367337278884";
			String mcc="8812";
			String postal_code="11375";
			String country_code="USA";
			String merchant_contact_info="123 Main street";

			rTask.execute("sdpurchaserefund",amount, method, currency_code, credit_card_type, cardholder_name,
					card_number, expdate, cvv,dba_name,street,region,mid,mcc,postal_code,country_code,merchant_contact_info);


			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	//CVV Transactions
	public void onCVVAuthVoid(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());

			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			rTask.execute("cvvauthorizevoid",amount, method, currency_code, credit_card_type, cardholder_name,
					card_number, expdate, cvv);
			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	public void onCVVPurchaseVoid(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());

			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			rTask.execute("cvvpurchasevoid",amount, method, currency_code, credit_card_type, cardholder_name,
					card_number, expdate, cvv);
			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}

	public void onCVVAuthCapture(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());

			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			rTask.execute("cvvauthcapture",amount, method, currency_code, credit_card_type, cardholder_name,
					card_number, expdate, cvv);
			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");

	}

	public void onCVVPurchaseRefund(View view)
	{
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());

			String amount="1100";
			String method="credit_card";
			String currency_code="GBP";
			String credit_card_type="visa";
			String cardholder_name="John Smith";
			String card_number="4035874000424977";
			String expdate="1030";
			String cvv="123";
			rTask.execute("cvvpurchaserefund",amount, method, currency_code, credit_card_type, cardholder_name,
					card_number, expdate, cvv);
			System.out.println("first authorize call end");

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}



}
