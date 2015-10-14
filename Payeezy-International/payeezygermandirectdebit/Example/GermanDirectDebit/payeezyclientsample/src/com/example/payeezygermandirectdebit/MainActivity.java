package com.example.payeezygermandirectdebit;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



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
