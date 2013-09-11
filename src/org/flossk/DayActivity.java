package org.flossk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DayActivity extends Activity
{
	
	private final int  DIALOG_ABOUT = 0;
	Day day;
	TextView tv_title;
	TextView tv_time;
	TextView tv_speaker;
	TextView tv_description;
	TextView tv_type;
	
	public DayActivity(Day d)
	{
		super();
		day = d;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.day_layout);
		
		tv_title = (TextView) findViewById(R.id.Text_View_Title);
		tv_title.setText(day.getTitlef());
		
		tv_time = (TextView) findViewById(R.id.Text_View_Time);
		tv_time.setText(day.getTimeStart()+" - "+day.getTimeEnd());
		
		tv_speaker = (TextView) findViewById(R.id.Text_View_Speaker);
		tv_speaker.setText(day.getSpeaker());
		
		tv_description = (TextView) findViewById(R.id.Text_View_Description);
		tv_description.setText(day.getDescription());
		
		tv_type = (TextView) findViewById(R.id.Text_View_Type);
		tv_type.setText(day.getType());
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

}
