package com.example.roomsearch;

import location.WifiReceiver;
import Datenbank.ActivityRegistry;
import Datenbank.Datenbank;
import Datenbank.Listfiller;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NavSicht extends Activity implements OnClickListener, OnMenuItemClickListener, OnItemLongClickListener, OnItemClickListener, OnTouchListener {
	private Listfiller filler = new Listfiller();
	private Button finden;
	private Animation animAlpha;
	// Liste von Vorlesungen
	private ListView mo, di, mi, don, fr; 
	private View selectedItem = null;
	private String selectedItemString = "";
	// View für den Standort
	public TextView standort;
	// Dateninhalte
	private Datenbank dt;
	private WifiManager wifi;
	boolean wifiWasEnabled;
	@SuppressWarnings("unused")
	private int networkID = -1;
	private WifiReceiver wr;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_sicht);
        ActivityRegistry.register(this);
        
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        /*
         *  Wlan Location
        */ 
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiWasEnabled = wifi.isWifiEnabled();
        wr = new WifiReceiver(this);
        IntentFilter i = new IntentFilter();
        i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wr,i);
        networkID = wifi.getConnectionInfo().getNetworkId();
        wifi.startScan();
        
        /*
         * Stundenlan
         */
        // Vorlesungen Montag
        
        mo = (ListView) findViewById(R.id.ListMo); 
        ArrayAdapter<String> sAmon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.montag()); 
        mo.setBackgroundColor(Color.WHITE);
        mo.setAdapter(sAmon);
        mo.setOnItemLongClickListener(this);
        mo.setOnItemClickListener(this);
        // Vorlesungen Dienstag
        di = (ListView) findViewById(R.id.ListDi);
        ArrayAdapter<String> sAdie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.dienstag()); 
        di.setBackgroundColor(Color.WHITE);
        di.setAdapter(sAdie);
        di.setOnItemLongClickListener(this);
        di.setOnItemClickListener(this);
        // Vorlesungen Mittwoch
        mi = (ListView) findViewById(R.id.ListMi);
        ArrayAdapter<String> sAmit = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.mittwoch()); 
        mi.setBackgroundColor(Color.WHITE);
        mi.setAdapter(sAmit);
        mi.setOnItemLongClickListener(this);
        mi.setOnItemClickListener(this);
        // Vorlesungen Donnerstag
        don = (ListView) findViewById(R.id.ListDo);
        ArrayAdapter<String> sAdon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.donnerstag()); 
        don.setBackgroundColor(Color.WHITE);
        don.setAdapter(sAdon);
        don.setOnItemLongClickListener(this);
        don.setOnItemClickListener(this);
        // Vorlesungen Freitag
        fr = (ListView) findViewById(R.id.ListFr);
        ArrayAdapter<String> sAfre = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.freitag()); 
        fr.setBackgroundColor(Color.WHITE);
        fr.setAdapter(sAfre);
        fr.setOnItemLongClickListener(this);
        fr.setOnItemClickListener(this);
        
		/*
         * Standort
         */
        standort = (TextView)findViewById(R.id.textView2);
        standort.setBackgroundColor(Color.WHITE);
        
        // Button Raum finden
        finden = (Button) findViewById(R.id.button_finden);
        finden.setOnClickListener(this);
        //finden.setOnTouchListener(this);
	}
	
	/**
	 * Menu Optionen
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_room_search, menu);
        for(int i = 0; i < menu.size(); i++) {
        	menu.getItem(i).setOnMenuItemClickListener(this);
        }
        
        return true;
    }

	public boolean onMenuItemClick(MenuItem item) {
		if(item.getTitle().equals("Beenden")){
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
			 myAlertDialog.setTitle("Beenden");
			 myAlertDialog.setMessage("Willst du Room Search wirklich beenden?");
			 myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				 ActivityRegistry.finishAll();
			 }});
			 myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				  // tue nicht
			 }});
			 
			 myAlertDialog.show();
		}
		return false;
	}
	
	public WifiManager getWifi() {
	      return wifi;
	}
	
	public void keinNetz(String e) {
		/*
		Toast toast = Toast.makeText(this, "Kein aktiven Accesspoint gefunden!", Toast.LENGTH_SHORT);
		toast.show(); 
		*/
		Toast toast = Toast.makeText(this, e, Toast.LENGTH_SHORT);
		toast.show(); 
	}

	public void onClick(final View v) {
		v.startAnimation(animAlpha);
		Handler handler=new Handler();
      	Runnable r = new Runnable() {
      	public void run() {
        if (v == finden) {
			// von NavSicht zu CamNavSicht
			final Intent in = new Intent (NavSicht.this, CamNavSicht.class);
			
			try {
				if(selectedItemString.length() == 0) {
					Toast toast = Toast.makeText(v.getContext(), "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
					toast.show(); 
				} else {
					dt = new Datenbank(selectedItemString);
					in.putExtra("Vorlesung", dt.getVorlesung());
					startActivity(in);
				}
			} catch (NullPointerException e) {
				Toast toast = Toast.makeText(v.getContext(), "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
				toast.show(); 
			}	
		}
      	  }
      			}; 
      			handler.postDelayed(r, 20);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Automatisch generierter Methodenstub
		super.onBackPressed();
		final Intent in = new Intent (NavSicht.this, LoginSicht.class);
			startActivity(in);  
	}

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		System.out.println();
		
		if(arg0.getId() == mo.getId()) {
			for(int k = 0; k < mo.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(filler.zeit().get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} else {
						arg1.setLongClickable(false);
					}
				}
			}
		}
			
		if(arg0.getId() == di.getId()) {
			for(int k = 0; k < di.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(filler.zeit().get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} else {
						arg1.setLongClickable(false);
					}
				}
			}
		}
		
		if(arg0.getId() == mi.getId()) {
			for(int k = 0; k < mi.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(filler.zeit().get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} else {
						arg1.setLongClickable(false);
					}
				}
			}
		}
			
		if(arg0.getId() == don.getId()) {
			for(int k = 0; k < don.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(filler.zeit().get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} else {
						arg1.setLongClickable(false);
					}
				}
			}
		}
		
		if(arg0.getId() == fr.getId()) {
			for(int k = 0; k < fr.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(filler.zeit().get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} else {
						arg1.setLongClickable(false);
					}
				}
			}
		}
		return false;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if(selectedItem != null) {
			selectedItem.setBackgroundColor(Color.WHITE);
		}
		
		if(arg0.getId() == mo.getId()) {
			for(int k = 0; k < mo.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
				} else {
					arg1.setClickable(false);
				}
				}
			}
		}
			
		if(arg0.getId() == di.getId()) {
			for(int k = 0; k < di.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
				} else {
					arg1.setClickable(false);
				}
				}
			}
		}
		
		if(arg0.getId() == mi.getId()) {
			for(int k = 0; k < mi.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
				} else {
					arg1.setClickable(false);
				}
				}
			}
		}
			
		if(arg0.getId() == don.getId()) {
			for(int k = 0; k < don.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
				} else {
					arg1.setClickable(false);
				}
				}
			}
		}
		
		if(arg0.getId() == fr.getId()) {
			for(int k = 0; k < fr.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
				} else {
					arg1.setClickable(false);
				}
				}
			}
		}
	}

	public boolean onTouch(View arg0, MotionEvent arg1) {
		if(arg1.getAction() == MotionEvent.ACTION_MOVE) {
			final Intent in = new Intent (NavSicht.this, CamNavSicht.class);
			
			try {
				if(selectedItemString.length() == 0) {
					Toast toast = Toast.makeText(this, "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
					toast.show(); 
				} else {
					dt = new Datenbank(selectedItemString);
					in.putExtra("Vorlesung", dt.getVorlesung());
					startActivity(in);
				}
			} catch (NullPointerException e) {
				Toast toast = Toast.makeText(this, "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
				toast.show(); 
			}
		}
		return false;
	}
}
