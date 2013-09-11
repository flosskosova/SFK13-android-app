package org.flossk;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class About extends Activity
{
	private final int  DIALOG_ABOUT = 0; // used for the item menu which is selected
	TextView TvLinkFour;
	TextView TvLinkFive;
	TextView TvLinkSix;
	TextView TvLinkSeven;
	TextView TvLinkEight;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		TvLinkFour = (TextView) findViewById(R.id.textViewLinkFour); // set view too TextView
		TvLinkFour.setOnClickListener(tvLinkFourListener); // set listener to TextView so it can open the Link
		
		TvLinkFive = (TextView) findViewById(R.id.textViewLinkFive); // set view too TextView
		TvLinkFive.setOnClickListener(tvLinkFiveListener); // set listener to TextView so it can open the Link
		
		TvLinkSix = (TextView) findViewById(R.id.textViewLinkSix); // set view too TextView
		TvLinkSix.setOnClickListener(tvLinkSixListener); // set listener to TextView so it can open the Link
		
		TvLinkSeven = (TextView) findViewById(R.id.textViewLinkSeven); // set view too TextView
		TvLinkSeven.setOnClickListener(tvLinkSevenListener); // set listener to TextView so it can open the Link
		
		TvLinkEight = (TextView) findViewById(R.id.textViewLinkEight); // set view too TextView
		TvLinkEight.setOnClickListener(tvLinkEightListener); // set listener to TextView so it can open the Link
	}
	
	public OnClickListener tvLinkFourListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  Intent webIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://www.fsf.org/"));                      
	    	  startActivity(webIntent);
	      }
	   };
	   
	   public OnClickListener tvLinkFiveListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  Intent webIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://opensource.org/"));                      
	    	  startActivity(webIntent);
	      }
	   };
	   
	   public OnClickListener tvLinkSixListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  Intent webIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://www.wikimedia.org/"));                      
	    	  startActivity(webIntent);
	      }
	   };
	   
	   public OnClickListener tvLinkSevenListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  Intent webIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://web.mit.edu/"));                      
	    	  startActivity(webIntent);
	      }
	   };
	   
	   public OnClickListener tvLinkEightListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  Intent webIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://www.flossk.org"));                      
	    	  startActivity(webIntent);
	      }
	   };
	   
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
		  
}
