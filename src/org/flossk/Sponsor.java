package org.flossk;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData.Item;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Sponsor extends Activity
{
	private final int  DIALOG_ABOUT = 0; // used for the item menu which is selected
	ImageView ipkoFoundation;
	ImageView ick;
	ImageView ilab;
	ImageView digjitale;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sponsor);
		
		//set the views for ImageViews in the layout
		ipkoFoundation = (ImageView) findViewById(R.id.imageViewIpkoFoundation);
		ick = (ImageView) findViewById(R.id.imageViewICK);
		ilab = (ImageView) findViewById(R.id.imageViewILAB);
		digjitale = (ImageView) findViewById(R.id.imageViewDigjitale);
		
		//set the OnClickListeners to open the web pages of the ImageViews when clicked (the sponsors LOGO web pages)
		
		ipkoFoundation.setOnClickListener(ListenerIpko);
		ick.setOnClickListener(ListenerIck);
		ilab.setOnClickListener(ListenerIlab);
		digjitale.setOnClickListener(ListenerDigjitale);
	}
	public OnClickListener ListenerIpko = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  Intent webIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://ipkofoundation.org/"));                      
	    	  startActivity(webIntent);
	      }
	   };
	   
	   public OnClickListener ListenerIck = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  Intent webIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://www.ickosovo.com/"));                      
	    	  startActivity(webIntent);
	      }
	   };
	   public OnClickListener ListenerIlab = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  Intent webIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://www.kosovoinnovations.org/"));                      
	    	  startActivity(webIntent);
	      }
	   };
	   public OnClickListener ListenerDigjitale = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  Intent webIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://digjitale.com/"));                      
	    	  startActivity(webIntent);
	      }
	   };
	
	public OnClickListener ListenerWeb = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  Intent webIntent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://sfk.flossk.org/"));                      
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
		   
		  /* public final void facebook() 
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
