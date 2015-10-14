package com.example.payeezydirecttransactions;



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
	
/*	public void Initialize()
	{
		try
		{
			RequestTask rTask5 = new RequestTask(getApplicationContext());
			rTask5.execute("gettoken");
			rTask5.get();
			//rTask5.wait();
			System.out.println("gettoken call end");
		}
		catch(Exception e)
		{
			System.out.println("Exception GetToken :" + e.getMessage());
		}
	}
*/
	
/*	public void onLogin(View view)
	{
		//AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
		//dlgAlert.setMessage("This is a messsage").show();
		
/*		try
		{
			
			/*
			String osNameString = System.getProperty("os.name");
			String osVersionString = System.getProperty("os.version");
			String osArchString = System.getProperty("os.arch");
			String jvNameString = System.getProperty("java.vm.name");
			String jvVersionString = System.getProperty("java.vm.version");
			String jvArchString = System.getProperty("java.vm.vendor");
			
			Toast.makeText(getApplicationContext(), osNameString, Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), osVersionString, Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), osArchString, Toast.LENGTH_SHORT).show();
			
			Toast.makeText(getApplicationContext(), jvNameString, Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), jvVersionString, Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), jvArchString, Toast.LENGTH_SHORT).show();
			*/
			
			/*
			RequestTask rTask5 = new RequestTask(getApplicationContext());
			rTask5.execute("AuthorizeAVS");
			//rTask5.wait();
			System.out.println("ValueLink authorize call end");
			*/
			
/*			TextView text =  (TextView)findViewById(R.id.txt_rmsg);
			//if(text.getText().toString() != "")
			String[] uri = new String[]{ text.getText().toString()};
			
			//RequestTask rTask6 = new RequestTask(getApplicationContext());
			//rTask6.execute("gettoken");
			
			//String sp = Environment.getExternalStorageDirectory().toString();
			//dlgAlert.setMessage(sp).show();
			
			//rTask6.wait();
			System.out.println("gettoken call end");

			if(uri[0].toLowerCase().equalsIgnoreCase("gettokenget"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("gettokenget");
				//rTask5.wait();
				System.out.println("gettokenget call end");
	    	}
			
	    	if(uri[0].toLowerCase().equalsIgnoreCase("gettoken"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("gettoken");
				//rTask5.wait();
				System.out.println("gettoken call end");
	    	}
	    	
	    	if(uri[0].toLowerCase().equalsIgnoreCase("gettokenvisa"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("gettokenvisa");
				//rTask5.wait();
				System.out.println("gettoken call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("gettokenmc"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("gettokenmc");
				//rTask5.wait();
				System.out.println("gettoken call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("gettokendiscover"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("gettokendiscover");
				//rTask5.wait();
				System.out.println("gettoken call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("gettokenamex"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("gettokenamex");
				//rTask5.wait();
				System.out.println("gettoken call end");
	    	}
	    	
			
			if(uri[0].toLowerCase().equalsIgnoreCase("authorizeavs"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("authorizeavs");
				//rTask5.wait();
				System.out.println("authorizelevel2 authorize call end");
	    	}

	    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizelevel3"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("authorizelevel3");
				//rTask5.wait();
				System.out.println("authorizelevel3 authorize call end");
	    	}
	    	
	    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizesplitshipments"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("authorizesplitshipments");
				//rTask5.wait();
				System.out.println("authorizesplitshipments authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizetransarmor"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("authorizetransarmor");
				//rTask5.wait();
				System.out.println("authorizetransarmor authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizenakedrefunds"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("authorizenakedrefunds");
				//rTask5.wait();
				System.out.println("authorizenakedrefunds authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizesoftdescriptors"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("authorizesoftdescriptors");
				//rTask5.wait();
				System.out.println("authorizesoftdescriptors authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("authorizepaypal"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("authorizepaypal");
				//rTask5.wait();
				System.out.println("authorizetransarmor authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("authorize3ds"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("authorize3ds");
				//rTask5.wait();
				System.out.println("authorize3ds authorize call end");
	    	}
			
	    	if(uri[0].toLowerCase().equalsIgnoreCase("recurring"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("recurring");
				//rTask5.wait();
				System.out.println("authorizelevel3 authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("direct"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("direct");
				//rTask5.wait();
				System.out.println("direct authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("directw"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("directw");
				//rTask5.wait();
				System.out.println("direct authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("w"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("w");
				//rTask5.wait();
				System.out.println("direct authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("test"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("test");
				//rTask5.wait();
				System.out.println("direct authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("splitshipmentsothers"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("splitshipmentsothers");
				//rTask5.wait();
				System.out.println("authorizetransarmor authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("recurringothers"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("recurringothers");
				//rTask5.wait();
				System.out.println("authorizetransarmor authorize call end");
	    	}
	    	if(uri[0].toLowerCase().equalsIgnoreCase("nakedrefundsothers"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("nakedrefundsothers");
				//rTask5.wait();
				System.out.println("authorizetransarmor authorize call end");
	    	}
	    	
	    	if(uri[0].toLowerCase().equalsIgnoreCase("zerodollar"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("zerodollar");
				//rTask5.wait();
				System.out.println("authorizetransarmor authorize call end");
	    	}
	    	
	    	if(uri[0].toLowerCase().equalsIgnoreCase("partialpurchase"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("partialpurchase");
				//rTask5.wait();
				System.out.println("partialpurchase authorize call end");
	    	}
	    	
	    	if(uri[0].toLowerCase().equalsIgnoreCase("partialpurchasenakedrefunds"))
	    	{
	    		RequestTask rTask5 = new RequestTask(getApplicationContext());
				rTask5.execute("partialpurchasenakedrefunds");
				//rTask5.wait();
				System.out.println("partialpurchasenakedrefunds authorize call end");
	    	}
	    	
			/*
			RequestTask rTask5 = new RequestTask(getApplicationContext());
			rTask5.execute("AuthorizeValuelink");
			//rTask5.wait();
			System.out.println("ValueLink authorize call end");
			*/
			/*
			File dir = getApplicationContext().getFilesDir();
			
			
			TransactionRequestTask transactionRequestTask = new TransactionRequestTask(getApplicationContext());
			//transactionRequestTask.SetFilePath(dir + "/TestPayLoads.xls");
			transactionRequestTask.SetFilePath("TestPayLoads.xls");
			TextView text =  (TextView)findViewById(R.id.txt_rmsg);
			if(text.getText().toString() != "")
			{
				transactionRequestTask.SetFilePath(text.getText().toString());
			}
			transactionRequestTask.execute("All");
			*/
/*		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}*/
	public boolean isExternalStorageReadOnly() { 
        String extStorageState = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
	}
 
    public boolean isExternalStorageAvailable() { 
        String extStorageState = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(extStorageState);
	}
	
	public void onAuthorize(View view)
	{
		Toast.makeText(getApplicationContext(), " Authorize Clicked", Toast.LENGTH_SHORT).show();
		//CallAuthorize();
		
		try
		{
			RequestTask rTask = new RequestTask(getApplicationContext());
			rTask.execute("authorize");
			System.out.println("first authorize call end");
			/*
			RequestTask rTask1 = new RequestTask(getApplicationContext());
			rTask1.execute("AuthorizeVisa");
			System.out.println("visa authorize call end");*/
			
			/*
			RequestTask rTask2 = new RequestTask(getApplicationContext());
			rTask2.execute("AuthorizeMastercard");
			//rTask2.wait();
			System.out.println("mastercard authorize call end");
				
			RequestTask rTask3 = new RequestTask(getApplicationContext());
			rTask3.execute("AuthorizeDiscover");
			//rTask3.wait();
			System.out.println("Discover authorize call end");
			
			
			RequestTask rTask4 = new RequestTask(getApplicationContext());
			rTask4.execute("AuthorizeAmex");
			//rTask4.wait();
			System.out.println("Amex authorize call end");
			
			
			RequestTask rTask5 = new RequestTask(getApplicationContext());
			rTask5.execute("AuthorizeValuelink");
			//rTask5.wait();
			System.out.println("ValueLink authorize call end");
			*/
			/*
			RequestTask rTask6 = new RequestTask(getApplicationContext());
			rTask6.execute("AuthorizeTelecheck");
			//rTask6.wait();
			System.out.println("Telecheck authorize call end");
			*/
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("authorize call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
	}
	public void onPurchase(View view)
	{
		Toast.makeText(getApplicationContext(), " Purchase Clicked", Toast.LENGTH_SHORT).show();
		/*
		URLClassLoader urlClassLoader;
		try {
			urlClassLoader = new URLClassLoader(new URL[]{new URL("file:///C:/nilesh/workspaceADT/app7/lib/org.apache.commons.codec.jar")});
		
			Class gsonClass = urlClassLoader.loadClass("org.apache.commons.codec.binary.Base64");  
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		*/
		
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("purchase");
		
		//Toast.makeText(getApplicationContext(), " Purchase Completed", Toast.LENGTH_SHORT).show();
		//CallPurchase();
	}
	
	public void onCapture(View view)
	{
		Toast.makeText(getApplicationContext(), " Capture Clicked", Toast.LENGTH_SHORT).show();
		//CallCapture();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("capture");
		//Toast.makeText(getApplicationContext(), " Capture Completed", Toast.LENGTH_SHORT).show();
	}
	
	public void onRefund(View view)
	{
		Toast.makeText(getApplicationContext(), " Refund Clicked", Toast.LENGTH_SHORT).show();
		//CallRefund();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("refund");
		//Toast.makeText(getApplicationContext(), " Refund Completed", Toast.LENGTH_SHORT).show();
	}
	
	public void onVoid(View view)
	{
		Toast.makeText(getApplicationContext(), " Void Clicked", Toast.LENGTH_SHORT).show();
		//CallVoid();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("void");
		//Toast.makeText(getApplicationContext(), " Void Completed", Toast.LENGTH_SHORT).show();
	}
	public void onValuelink(View view)
	{
		Toast.makeText(getApplicationContext(), " ValueLink Clicked", Toast.LENGTH_SHORT).show();
		try
		{
			
			RequestTask rTask5 = new RequestTask(getApplicationContext());
			rTask5.execute("valuelink");
			//rTask5.wait();
			System.out.println("ValueLink call end");
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("valuelink call end");
		//Toast.makeText(getApplicationContext(), " Authorize Completed", Toast.LENGTH_SHORT).show();
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
		Toast.makeText(getApplicationContext(), " avs Clicked", Toast.LENGTH_SHORT).show();
		//CallCapture();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorizeavs");
		//Toast.makeText(getApplicationContext(), " Capture Completed", Toast.LENGTH_SHORT).show();
	}
	
	public void onLevel2(View view)
	{
		Toast.makeText(getApplicationContext(), " level2 Clicked", Toast.LENGTH_SHORT).show();
		//CallCapture();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorizelevel2");
		//Toast.makeText(getApplicationContext(), " Capture Completed", Toast.LENGTH_SHORT).show();
	}
	
	public void onLevel3(View view)
	{
		Toast.makeText(getApplicationContext(), " level3 Clicked", Toast.LENGTH_SHORT).show();
		//CallCapture();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorizelevel3");
		//Toast.makeText(getApplicationContext(), " Capture Completed", Toast.LENGTH_SHORT).show();
	}
	
	public void on3DS(View view)
	{
		Toast.makeText(getApplicationContext(), " 3DS Clicked", Toast.LENGTH_SHORT).show();
		//CallCapture();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorize3ds");
		//Toast.makeText(getApplicationContext(), " Capture Completed", Toast.LENGTH_SHORT).show();
	}
	
	public void onSoftDescriptors(View view)
	{
		Toast.makeText(getApplicationContext(), " Softdescriptors Clicked", Toast.LENGTH_SHORT).show();
		//CallCapture();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorizesoftdescriptors");
		//Toast.makeText(getApplicationContext(), " Capture Completed", Toast.LENGTH_SHORT).show();
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
		Toast.makeText(getApplicationContext(), " split shipments Clicked", Toast.LENGTH_SHORT).show();
		//CallCapture();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("authorizesplitshipments");
		//Toast.makeText(getApplicationContext(), " Capture Completed", Toast.LENGTH_SHORT).show();
	}
    
	public void onZeroDollar(View view)
	{
		Toast.makeText(getApplicationContext(), " Zero Dollar Clicked", Toast.LENGTH_SHORT).show();
		//CallCapture();
		RequestTask rTask = new RequestTask(getApplicationContext());
		rTask.execute("zerodollar");
		//Toast.makeText(getApplicationContext(), " Capture Completed", Toast.LENGTH_SHORT).show();
	}

	public void onTransarmor(View view)
	{
		RequestTask rTask5 = new RequestTask(getApplicationContext());
		rTask5.execute("authorizetransarmor");
		//rTask5.wait();
		System.out.println("authorizetransarmor authorize call end");
	}


}
