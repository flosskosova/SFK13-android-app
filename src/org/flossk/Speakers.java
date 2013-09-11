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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Speakers extends Activity
{
	private final int UPDATE = 500; 
	private final int  DIALOG_ABOUT = 100;
	private List<Speaker> mySpeakers = new ArrayList<Speaker>(); // ArrayList to hold all speaker items

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.speakers);
			
			addSpeakerList(); // add  speakers to list
			addListView(); // add 
			
	}
	private void addSpeakerList() 
	{
			mySpeakers.add(new Speaker(R.drawable.tim_dobson,"Tim Dobson"," tdobson.net"," facebook.com/timdobsonuk" ,new String(getString(R.string.TimDobson))));
			mySpeakers.add(new Speaker(R.drawable.alex_lakatos,"Alex Lakatos","twitter.com/lakatos88","" ,new String(getString(R.string.alex))));
			mySpeakers.add(new Speaker(R.drawable.ana_risteska,"Ana Risteska","","" ,new String(getString(R.string.ana_r))));
			mySpeakers.add(new Speaker(R.drawable.jovanka_guliscoska,"Jovanka Guliscoska","","" ,new String(getString(R.string.jovanka))));
			mySpeakers.add(new Speaker(R.drawable.robert_m_ochshorn,"Robert M Ochshorn","","" ,new String(getString(R.string.robert_m))));
			mySpeakers.add(new Speaker(R.drawable.bert_desmet,"Bert Desmet"," twitter.com/bdesmet","" ,new String(getString(R.string.bert))));
			mySpeakers.add(new Speaker(R.drawable.marco_fioretti,"Marco Fioretti"," mfioretti.com"," twitter.com/mfioretti_en" ,new String(getString(R.string.marco))));
			mySpeakers.add(new Speaker(R.drawable.visar_shehu,"Visar Shehu"," seeu.edu.mk/en/~v.shehu","" ,new String(getString(R.string.visar))));
			mySpeakers.add(new Speaker(R.drawable.adrian_besimi,"Adrian Besimi"," seeu.edu.mk/en/~a.besimi","" ,new String(getString(R.string.adrian))));
			mySpeakers.add(new Speaker(R.drawable.baki_goxhaj,"Baki Goxhaj"," twitter.com/banago","" ,new String(getString(R.string.baki))));
			mySpeakers.add(new Speaker(R.drawable.arianit_dobroshi,"Arianit Dobroshi","","" ,new String(getString(R.string.arianit))));
			mySpeakers.add(new Speaker(R.drawable.arbnor_hasani,"Arbnor Hasani"," twitter.com/arbnorhasani","" ,new String(getString(R.string.arbnor))));
			mySpeakers.add(new Speaker(R.drawable.burim_shala,"Burim Shala"," umbrella.al"," facebook.com/shala.burim" ,new String(getString(R.string.burim))));
			mySpeakers.add(new Speaker(R.drawable.dashamir_hoxha,"Dashamir Hoxha"," dashohoxha.blogspot.com","" ,new String(getString(R.string.dashamir))));
			mySpeakers.add(new Speaker(R.drawable.fehmi_dumani,"Fehmi Dumani","","" ,new String(getString(R.string.fehmi))));
			mySpeakers.add(new Speaker(R.drawable.vleran_dushi,"Vleran Dushi","","" ,new String(getString(R.string.vleran))));
			mySpeakers.add(new Speaker(R.drawable.hekuran_doli,"Hekuran Doli","","" ,new String(getString(R.string.hekuran))));
			mySpeakers.add(new Speaker(R.drawable.redon_skikuli,"Redon Skikuli","","" ,new String(getString(R.string.redon))));
			mySpeakers.add(new Speaker(R.drawable.flamur_mavraj,"Flamur Mavraj","empir.io","twitter.com/oxodesign" ,new String(getString(R.string.flamur))));
			
	}
		
	private void addListView() 
	{
		ArrayAdapter<Speaker> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.lv_speakers);
		list.setAdapter(adapter); // set ArrayAdapter for the List View
	}
		
		
		
		private class MyListAdapter extends ArrayAdapter<Speaker> 
		{
			public MyListAdapter() 
			{
				super(Speakers.this, R.layout.speaker_item, mySpeakers);
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) 
			{
				// Make sure we have a view to work with (may have been given null)
				View itemView = convertView;
				if (itemView == null)
				{
					itemView = getLayoutInflater().inflate(R.layout.speaker_item, parent, false);
				}
				
				// Find the Speaker to work with.
				final Speaker currentSpeaker = mySpeakers.get(position);
				
				// Fill the view
				ImageView imageView = (ImageView)itemView.findViewById(R.id.iv_speaker_pic);
				imageView.setImageResource(currentSpeaker.getPicID());
				imageView.setScaleType(ScaleType.FIT_XY);
				
				// Website:
				TextView website = (TextView) itemView.findViewById(R.id.tv_speaker_website);
				website.setText(currentSpeaker.getWebsite());
				website.setOnClickListener(new OnClickListener() // set the onClick listener for sponsor button with anonim lokal class
				{

					@Override
					public void onClick(View v) 
					{
						String url = "http://" + currentSpeaker.getWebsite().replace(" ", "www.");
						startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse(url)));
					}
				});
				
				//Speakers name
				TextView name = (TextView) itemView.findViewById(R.id.tv_speaker_Name);
				name.setText(currentSpeaker.getName());

				// Facebook:
				TextView facebook = (TextView) itemView.findViewById(R.id.tv_speaker_Facebook);
				facebook.setText("" + currentSpeaker.getFacebook());
				facebook.setOnClickListener(new OnClickListener() // set the onClick listener for sponsor button with anonim lokal class
				{

					@Override
					public void onClick(View v) 
					{
						String url = "http://" + currentSpeaker.getFacebook().replace(" ", "www.");
						startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse(url)));
					}
				});
				
				// Description:
				TextView Des = (TextView) itemView.findViewById(R.id.tv_speaker_Des);
				Des.setText(currentSpeaker.getDes());

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
			    		}
			    		else
			    			Toast.makeText(Speakers.this, "No Internet connection!", Toast.LENGTH_LONG).show();
			    		return true;
		        	
			    	case R.id.itemWebsite: // case for Web Icon is selected
			    		startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse("http://sfk.flossk.org/"))); // start Web App with the URL 
			    		return true;
			    	
			    	case R.id.itemDevelopers: // case for Developers selected
			    		showDialog( DIALOG_ABOUT); // calls method showDialog that opens a dialog for the info xml file
			    	
			    }
			    return false; // if not found return false
			}
		   
		   @Override
		   protected Dialog onCreateDialog(int id) // creates a new dialog
		   {
				switch (id) // switches threw the id of items 
				{
					case UPDATE: // the about id case
						return createUpdateDialog(); // calls method createAboutDialog() to create the Update Dialog
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

private boolean isNetworkAvailable() {
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
//					a = server;
					
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
		  	
	
//			return buff.toString();
		  	return a;
		  	   
		  	   
	}

	 protected void onPostExecute(String result) {

		 if(result.equals("Update")){
			 
			 Uri uri = Uri.parse("http://sfk.flossk.org/android");
			 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			 startActivity(intent);
			 
		 }
		 else if (result.equals("No")){
			 Toast.makeText(Speakers.this, "Your app is up to date!", Toast.LENGTH_LONG).show();
			 
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










