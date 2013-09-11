package org.flossk;

import java.util.ArrayList;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Sunday extends Activity
{
	private final int  DIALOG_ABOUT = 0; // used for the item menu which is selected
	private ArrayList<Day> Days = new ArrayList<Day>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.day);
		
		TextView tv = (TextView) findViewById(R.id.tvTitleListView);
		tv.setText("Tracks for Sunday");
		
		populateDays();
		populateListView();
		registerClickCallback();
		
	}
	
	private void populateDays() 
	{
		
		Days.add(new Day("Linux","08:00","09:00","Altin Ukshini",new String(getString(R.string.Linux)),"Workshop"));
		Days.add(new Day("Ubuntu","10:00","11:00","Arlind Hajredinaj",new String(getString(R.string.Ubuntu)),"Workshop"));
		Days.add(new Day("Mint","12:00","13:00","Ardian Haxha",new String(getString(R.string.Mint)),"Workshop"));
		Days.add(new Day("Fedora","14:00","15:00","Fatos Nigga",new String(getString(R.string.Fedora)),"Lecture"));
		Days.add(new Day("Arch","08:00","09:00","Lil Wayne",new String(getString(R.string.Arch)),"Lecture"));
		Days.add(new Day("Mozilla","08:00","09:00","Samsung S3",new String(getString(R.string.Mozilla)),"Workshop"));
		
	}
	
	private void populateListView() 
	{
		ArrayAdapter<Day> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.lv);
		list.setAdapter(adapter);
	}
	
	private void registerClickCallback()
	{
		ListView list = (ListView) findViewById(R.id.lv);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked,int position, long id)
			{
				
				Day clickedDay = Days.get(position);
				String message = "You clicked position " + position
								+ "  The Time for this is " + clickedDay.getTimeStart()+" - "+clickedDay.getTimeEnd()
								+". The topic is "+clickedDay.getTitlef();
				Toast.makeText(Sunday.this, message, Toast.LENGTH_LONG).show();
			}
		});
	}
	
	private class MyListAdapter extends ArrayAdapter<Day> 
	{
		public MyListAdapter() 
		{
			super(Sunday.this, R.layout.listitem, Days);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure we have a view to work with (may have been given null)
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.listitem, parent, false);
			}
			
			// Find the Day to work with
			Day currentDay = Days.get(position);
			
			// StartTime:
			TextView startTime = (TextView) itemView.findViewById(R.id.t_v_startTime);
			startTime.setText(currentDay.getTimeStart());

			// Title:
			TextView title = (TextView) itemView.findViewById(R.id.t_v_title);
			title.setText(currentDay.getTitlef());
			
			// Speaker:
			TextView speaker = (TextView) itemView.findViewById(R.id.t_v_speaker);
			speaker.setText(currentDay.getSpeaker());
			
			// Type:
			TextView type = (TextView) itemView.findViewById(R.id.t_v_Type);
			type.setText(currentDay.getType());

			return itemView;
		}				
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
