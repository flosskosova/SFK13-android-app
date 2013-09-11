/*
  <SFK'13 is and android app used for the Software Freedom Kosovo 2013 conference which is organized by flossk >
    Copyright (C) 2013  Arlind Hajredinaj and Jeton Ahmetaj

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
	
	SFK'13 Copyright (C) 2013  Arlind Hajredinaj and Jeton Ahmetaj
    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
    This is free software, and you are welcome to redistribute it
    under certain conditions; type `show c' for details.
 */
package org.flossk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
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

public class Monday extends Activity
{
	private final int UPDATE = 500;
	private final int  DIALOG_ABOUT = 100; // used for the item menu which is selected
	 final int ITEM_0 = 0,ITEM_1 = 1,ITEM_2 = 2, ITEM_3 = 3, ITEM_4 = 4,ITEM_5 = 5,ITEM_6 = 6, ITEM_7 = 7, ITEM_8 = 8, ITEM_9 = 9, ITEM_10 = 10, ITEM_11 = 11;
	private ArrayList<Day> Days = new ArrayList<Day>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.day);
		
		TextView tv = (TextView) findViewById(R.id.tvTitleListView);
		tv.setText("Monday Sessions - Workshops");
		
		TextView tv1 = (TextView) findViewById(R.id.tvTitleAddress);
		tv1.setText("(@ Innovation Centre Kosovo)");
		
		populateDays();
		populateListView();
		registerClickCallback();
		
	}
	
	private void populateDays() 
	{
		Days.add(new Day("Tuning PostgreSQL","13:00","14:30","Bert Desmet",new String(getString(R.string.postgres)),""));
		Days.add(new Day("A more awesome web with HTML5 / CSS3","15:00","16:30","Vleran Dushi",new String(getString(R.string.html_css)),""));
		Days.add(new Day("Wordpress and Template development","17:00","18:30","Burim Shala",new String(getString(R.string.wordpress)),""));
		Days.add(new Day("WMKIT Arduino workshop. Learn electronics and programing easy!","19:00","20:30","Redon Skikuli",new String(getString(R.string.arduino)),""));
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
				showDialog(position);
				/*String message = "You clicked position " + position+ "  The Time for this is " + clickedDay.getTimeStart()+" - "+clickedDay.getTimeEnd()
								+". The topic is "+clickedDay.getTitlef();
				
				Toast.makeText(Saturday.this, message, Toast.LENGTH_LONG).show();
				*/
			}
			
		});
	}
	
	private Dialog createListItemDialog(Day clickedDay)
	   {
		   	final AlertDialog.Builder builder = new AlertDialog.Builder(this); 
		   	
		   	final View view = getLayoutInflater().inflate(R.layout.list_view_item, null, false);// inflates the dev_about layout
			
		    final TextView tvTime = (TextView) view.findViewById(R.id.Text_ViewItem_Time);
		    tvTime.setText(clickedDay.getTime());
		    
		    final TextView tvDescription = (TextView) view.findViewById(R.id.Text_ViewItem_Description);
		    tvDescription.setText(clickedDay.getDescription());
		    
		    final TextView tvSpeaker = (TextView) view.findViewById(R.id.Text_ViewItem_Speaker);
		    tvSpeaker.setText(clickedDay.getSpeaker());
		    
		   	builder.setTitle(clickedDay.getTitlef()); // sets title to AlertDialog*/
			//builder.setIcon(R.drawable.action_a); // sets the icon to Alert Dialog
			builder.setView(view); //// sets the view for builder
			builder.setPositiveButton(getString(android.R.string.ok), null); // the possitive button strings is ok
			builder.setCancelable(true); // the dialog is cancelable after clicked ok
			//builder.setMessage(clickedDay.getDescription());
			return builder.create(); // creates the dialog
	   }
	
	private class MyListAdapter extends ArrayAdapter<Day> 
	{
		public MyListAdapter() 
		{
			super(Monday.this, R.layout.listitem, Days);
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
		    	case R.id.itemRefresh:
		    		boolean network = isNetworkAvailable();
		    		if(network == true)
		    		{
		    			showDialog(UPDATE);
		    		}else
		    			Toast.makeText(Monday.this, "No Internet connection!", Toast.LENGTH_LONG).show();
		    		return true;
		    	
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
				case UPDATE :
					return createUpdateDialog();
				case DIALOG_ABOUT: // the about id case
					return createAboutDialog(); // calls method createAboutDialog() to create the About Dialog
				case  ITEM_0:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_1:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_2:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_3:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_4:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_5:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_6:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_7:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_8:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_9:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_10:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
				case  ITEM_11:
					return createListItemDialog(Days.get(id)); // to create aboutDialog for list view item that is clicked
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
	   
	   
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

private boolean isNetworkAvailable() 
{
ConnectivityManager connectivityManager 
= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
return activeNetworkInfo != null && activeNetworkInfo.isConnected();
}

private class Update extends AsyncTask<URL,String,String>{

@Override
protected String doInBackground(URL... params) {

String input = "";

HttpURLConnection con;
InputStream in;
BufferedReader rd;
StringBuilder buff = new StringBuilder();
String a = null;



try {
con = (HttpURLConnection) params[0].openConnection();

PackageInfo pInfo = null;
try {
pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
} catch (NameNotFoundException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
String curr_version = pInfo.versionName;

try {
in = con.getInputStream();

rd = new BufferedReader(new InputStreamReader(in,"UTF-8"));

int ch;
String line;

String server = rd.readLine();
//a = server;

if(!curr_version.equals(server)){
a = "Update";
}else{
a = "No";
}

} catch (Exception e) {
setContentView(R.layout.home);
}

} catch (IOException e) {
setContentView(R.layout.home);
}


//return buff.toString();
return a;


}

protected void onPostExecute(String result) {

if(result.equals("Update")){

Uri uri = Uri.parse("http://sfk.flossk.org/android");
Intent intent = new Intent(Intent.ACTION_VIEW, uri);
startActivity(intent);

}
else if (result.equals("No")){
Toast.makeText(Monday.this, "Your app is up to date!", Toast.LENGTH_LONG).show();

}

}

}

private Dialog createUpdateDialog() 
{
final AlertDialog.Builder builder = new AlertDialog.Builder(this); 
final View view = getLayoutInflater().inflate(R.layout.update_layout, null, false);// inflates the dev_about layout
builder.setTitle("Update App"); // sets title to AlertDialog
builder.setView(view); //// sets the view for builder
builder.setPositiveButton(getString(android.R.string.ok), new android.content.DialogInterface.OnClickListener()
{

@Override
public void onClick(DialogInterface dialog, int which) {
try {
new Update().execute(new URL("http://sfk.flossk.org/sites/default/files/android/a.php"));
} catch (Exception e) {
setContentView(R.layout.home);
}

}

}); // the possitive button strings is ok
builder.setNegativeButton("Cancel",new android.content.DialogInterface.OnClickListener()
{

@Override
public void onClick(DialogInterface dialog, int which) {
if (which == Dialog.BUTTON_NEGATIVE)
dialog.dismiss();

}

});
builder.setCancelable(true); // the dialog is cancelable after clicked ok
return builder.create(); // creates the dialog
}


	  
}
