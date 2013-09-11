package org.flossk;

// Developed by : Arlind Hajredinaj - Email: arlind.hajredinaj@Gmail.com


import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	
	private final int  DIALOG_ABOUT = 0; // used for the item menu which is selected
	private Button sponsors; // sponsor button on the home activity that opens sponsor Activity
	private Button about; //about button on the home activity opens about activity
	private Button speakers; // speakers button on the home acticity opens speakers activity
	private Button saturday; // saturday button on the home activity opens saturday activity
	private Button sunday; // sunday button on the home activity opens saturday activity
	private Button monday; // monday button on the home activity opens saturday activity
	
	@Override
	protected void onCreate(Bundle savedInstanceState) // onCreate method 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		sponsors = (Button) findViewById(R.id.buttonSponsors); // set sponsors button with the id
		sponsors.setOnClickListener(new OnClickListener() // set the onClick listener for sponsor button with anonim lokal class
		{

			@Override
			public void onClick(View v) 
			{
				Intent ourIntent = new Intent(MainActivity.this,Sponsor.class); //new intent to open new the Sponsors Activity
				startActivity(ourIntent); // starts the intent
			}
		});
		
		about= (Button) findViewById(R.id.buttonAbout);
		about.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				Intent ourIntent = new Intent(MainActivity.this,About.class);
				startActivity(ourIntent);
			}
		});
		
		saturday = (Button) findViewById(R.id.buttonSaturDay);
		saturday.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent ourIntent = new Intent(MainActivity.this, Saturday.class);
				startActivity(ourIntent);
			}
		});
		
		sunday = (Button) findViewById(R.id.buttonSunday);
		sunday.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent ourIntent = new Intent(MainActivity.this, Sunday.class);
				startActivity(ourIntent);
			}
		});
		
		monday = (Button) findViewById(R.id.buttonMonday);
		monday.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Intent ourIntent = new Intent(MainActivity.this, Monday.class);
				startActivity(ourIntent);
			}
		});
		
		
	}
	
	
    

	public boolean onCreateOptionsMenu(Menu menu) // for the Menu 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	   
	   public boolean onOptionsItemSelected(MenuItem item)
	   {
		    switch (item.getItemId()) // switches the item that is selected
		    {
		    	case R.id.itemWebsite: // case for Web Icon is selected
		    		startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse("http://sfk.flossk.org/"))); // start Web App with the URL 
		    		return true;
		    	
		    	case R.id.itemDevelopers: // case for Developers selected
		    		showDialog( DIALOG_ABOUT); // calls method showDialog that opens a dialog for the info xml file
		    	return true;
		    }
		    return false; // if not found return false
		}
	   
	   @Override
	   protected Dialog onCreateDialog(int id) // creates a new dialog
	   {
			switch (id) // switches threw the id of items 
			{
				case DIALOG_ABOUT: // the about id case
					return createAboutDialog(); // calls method createAboutDialog() to create the About Dialog
				default:
					return null;
			}
			
	   }
	   
	   private Dialog createAboutDialog() 
	   {
			final AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			final View view = getLayoutInflater().inflate(R.layout.dev_about, null, false);// inflates the dev_about layout
			builder.setTitle(getString(R.string.app_name)); // sets title to AlertDialog
			builder.setIcon(R.drawable.action_a); // sets the icon to Alert Dialog
			builder.setView(view); //// sets the view for builder
			builder.setPositiveButton(getString(android.R.string.ok), null); // the possitive button strings is ok
			builder.setCancelable(true); // the dialog is cancelable after clicked ok
			return builder.create(); // creates the dialog
		}
	   
	   /*public final void facebook() 
	   {
	        final String urlFb = "facebook://"+"softwarefreedomkosova";
	        Intent intent = new Intent(Intent.ACTION_VIEW);
	        intent.setData(Uri.parse(urlFb));

	        // If Facebook application is installed, use that else launch a browser
	        final PackageManager packageManager = getPackageManager();
	        List<ResolveInfo> list =  packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
	        
	        if (list.size() == 0) 
	        {
	            final String urlBrowser = "https://www.facebook.com/"+"softwarefreedomkosova";
	            intent.setData(Uri.parse(urlBrowser));
	        }

	        startActivity(intent);
	    }*/
	   

}
