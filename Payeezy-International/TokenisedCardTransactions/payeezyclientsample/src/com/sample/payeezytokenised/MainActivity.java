package com.sample.payeezytokenised;



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

import com.sample.payeezytokenised.R;
import com.sdk.payeezytokenised.RequestTask;


@SuppressLint("DefaultLocale")
public class MainActivity extends Activity {

	private static Context context;
	public static TextView displayResult;

    public void onCreate(){
        MainActivity.context = getApplicationContext();

    }

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

	//Token Generation using POST method--08-11
	public void onPostToken(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("posttokenvisa");
		System.out.println("posttoken call end");
	}

//Added for GET token-used
	public void onGetGetToken(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());

		String auth="false";
		String callback="Payeezy.callback";
		String currency="GBP";
		String type="FDToken";
		String cardtype="visa";
		String cardholdername="John Smith";
		String creditcardnumber="4035874000424977";
		String expdate="1030";
		String cvv="123";
		rTask5.execute("gettokenvisa",auth,callback,currency,type,cardtype,cardholdername,
				creditcardnumber,expdate,cvv);

		System.out.println("gettoken call end");
	}

	//Added for GET-Authorise Selection
	public void onGetAuthoriseToken(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		String method="token";
		String amount="1100";
		String currency_code="GBP";

		rTask5.execute("getauthorisetoken",method,amount,currency_code);

		System.out.println("getauthorisetoken call end");
	}

	//Added for GET-Purchase Selection
	public void onGetPurchaseToken(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		String method="token";
		String amount="1100";
		String currency_code="GBP";

		rTask5.execute("getpurchasetoken",method,amount,currency_code);
		System.out.println("getpurchasetoken call end");
	}

	//Added for Authorise-Capture Selection
	public void onGetAuthCaptureToken(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		String method="token";
		String amount="1100";
		String currency_code="GBP";

		rTask5.execute("getauthcapturetoken",method,amount,currency_code);
		System.out.println("getauthcapturetoken call end");
	}

	//Added for Authorise-Void Selection
	public void onGetAuthVoidToken(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		String method="token";
		String amount="1100";
		String currency_code="GBP";
		rTask5.execute("getauthvoidtoken",method,amount,currency_code);

		System.out.println("getauthvoidtoken call end");
	}

	//Added for Purchase-Refund Selection
	public void onGetPurchaseRefundToken(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		String method="token";
		String amount="1100";
		String currency_code="GBP";

		rTask5.execute("getpurchaserefundtoken",method,amount,currency_code);
		System.out.println("getpurchaserefundtoken call end");
	}
}
