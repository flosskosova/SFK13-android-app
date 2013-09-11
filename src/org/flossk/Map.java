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

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlay;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButtonsController;
import android.widget.ZoomControls;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
 
public class Map extends Activity {
  
	private final int UPDATE = 500;
	private final int ADRESS_DIALOG = 200;
	private final int  DIALOG_ABOUT = 100;
	MapController mapController;
	MyItemizedOverlay myItemizedOverlay = null;
	private TextView osm;
	private TextView adress;
	
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        adress = (TextView) findViewById(R.id.tv_map_adress);
        adress.setOnClickListener(new OnClickListener() // set the onClick listener for sponsor button with anonim lokal class
		{

			@Override
			public void onClick(View v) 
			{
				showDialog(ADRESS_DIALOG);
			}
		});
        
        osm = (TextView) findViewById(R.id.tv_OSM);
        osm.setOnClickListener(new OnClickListener() // set the onClick listener for sponsor button with anonim lokal class
		{

			@Override
			public void onClick(View v) 
			{
				startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse("http://www.openstreetmap.org/")));
			}
		});
       
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(false);
        mapView.setMultiTouchControls(true);
        mapView.setMinZoomLevel(4);
        
        mapController = mapView.getController();
        mapController.setZoom(17);
        mapController.setCenter(new GeoPoint(42.656192,21.164275));
        
        ZoomControls zoomControls = (ZoomControls) findViewById(R.id.zoomcontrols);
    	
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() 
        {
     		@Override
     		public void onClick(View v) 
     		{
     			mapController.zoomIn();
     		}
     	});
     	zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
     		@Override
     		public void onClick(View v) {
     			mapController.zoomOut();
     		}
     	});
     	
        
        double latitudeOfFed = 42.657841;
        double LongitudeOfFed = 21.163653;
        GeoPoint myPoint = new GeoPoint(latitudeOfFed, LongitudeOfFed);
        
        double latitudeOfICK = 42.655119;
        double LongitudeOfICK = 21.164984;
        GeoPoint myPoint1 = new GeoPoint(latitudeOfICK, LongitudeOfICK);
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        View popUpIck = getLayoutInflater().inflate(R.layout.ick_popup, mapView, false);
        View popUpFed = getLayoutInflater().inflate(R.layout.fed_popup, mapView, false); 
       

      //Get a LayoutInflater and load up the view we want to display. 
      //The false in inflater.inflate prevents the bubble View being added to the MapView straight away 
        LayoutInflater inflater = this.getLayoutInflater();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Drawable marker = getResources().getDrawable(R.drawable.flossk_marker);
        int markerWidth = 100;
     
        int markerHeight = 140;
        
        marker.setBounds(0, markerHeight, markerWidth, 0);
        ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());
         
        myItemizedOverlay = new MyItemizedOverlay(marker, resourceProxy);
        mapView.getOverlays().add(myItemizedOverlay);
        
        
        myItemizedOverlay.addItem(myPoint, "myPoint", "myPoint");
        myItemizedOverlay.addItem(myPoint1, "myPoint1", "myPoint1");
        
         
    } 
    
    
    public boolean onCreateOptionsMenu(Menu menu) // for the Menu 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	   
	   
	   
	   @Override
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
		    			Toast.makeText(Map.this, "No Internet connection!", Toast.LENGTH_LONG).show();
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
	   
	  
	   protected Dialog onCreateDialog(int id) // creates a new dialog
	   {
			switch (id) // switches threw the id of items 
			{
				case UPDATE :
					return createUpdateDialog();
				case DIALOG_ABOUT: // the about id case
					return createAboutDialog(); // calls method createAboutDialog() to create the About Dialog
				case ADRESS_DIALOG:
					return createAdressDialog();
				default:
					return null;
			}
			
	   }
	   
	   private Dialog createAdressDialog() 
	   {
			final AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			final View view = getLayoutInflater().inflate(R.layout.address_dialog, null, false);// inflates the dev_about layout
			builder.setTitle("Adresses"); // sets title to AlertDialog
			builder.setView(view); //// sets the view for builder
			builder.setPositiveButton(getString(android.R.string.ok), null); // the possitive button strings is ok
			builder.setCancelable(true); // the dialog is cancelable after clicked ok
			return builder.create(); // creates the dialog
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
//							a = server;
							
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
				  	
			
//					return buff.toString();
				  	return a;
				  	   
				  	   
			}

			 protected void onPostExecute(String result) {
	 
				 if(result.equals("Update")){
					 
					 Uri uri = Uri.parse("http://sfk.flossk.org/android");
					 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					 startActivity(intent);
					 
				 }
				 else if (result.equals("No")){
					 Toast.makeText(Map.this, "Your app is up to date!", Toast.LENGTH_LONG).show();
					 
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

