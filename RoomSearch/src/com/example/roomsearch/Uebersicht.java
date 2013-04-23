package com.example.roomsearch;

import lokalisierung.WifiReceiver;

import com.example.roomsearch.R;

import create.edit.delete.EditPlanActivity;
import datenbank.InterneDatenbank;

import extras.ActivityRegistry;
import extras.DialogBuilder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Uebersicht extends Activity implements OnClickListener, OnMenuItemClickListener {
	private Button plan, suchen, spracheingabe, karte, abmelden;
	public TextView standort;
	private Spinner hausauswahl;
	private EditText raumnummer;
	private String rolle;
	private String[] daten;
	// Lokalisierung
	private WifiManager wifi;
	boolean wifiWasEnabled;
	@SuppressWarnings("unused")
	private int networkID = -1;
	private WifiReceiver wr;
	
	private InterneDatenbank data;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent.getExtras().getString("ROLLE") != null) {
        	rolle = intent.getExtras().getString("ROLLE");
        }
        daten = intent.getExtras().getStringArray("DATEN");
        setContentView(R.layout.uebersicht);
        ActivityRegistry.register(this);
        // 
        data = new InterneDatenbank(this);
        // Spinner und EditText
        hausauswahl = (Spinner)findViewById(R.id.spinner_menu_gebÃ¤ude);
        raumnummer = (EditText)findViewById(R.id.editText_number);
        // Buttons
        plan = (Button)findViewById(R.id.button_mein_plan);
        suchen = (Button)findViewById(R.id.button_menu_suchen);
        spracheingabe = (Button)findViewById(R.id.button_menu_sprach);
        karte = (Button)findViewById(R.id.button_menu_campus);
        abmelden = (Button)findViewById(R.id.button_logout);
        plan.setOnClickListener(this);
        suchen.setOnClickListener(this);
        spracheingabe.setOnClickListener(this);
        karte.setOnClickListener(this);
        abmelden.setOnClickListener(this);
        // Wlan Location
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiWasEnabled = wifi.isWifiEnabled();
        wr = new WifiReceiver(this);
        IntentFilter i = new IntentFilter();
        i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wr,i);
        networkID = wifi.getConnectionInfo().getNetworkId();
        wifi.startScan();
	}
	
	public void onClick(View v) {
		if(v == plan) {
			Intent in = new Intent(Uebersicht.this, VorlesungsPlan.class);
			// übergebe rolle
			in.putExtra("ROLLE", rolle);
			// übergebe die nutzerdaten an die nächste activity
			in.putExtra("DATEN", daten);
			// starte die neue Activity
			startActivity(in);
		}
		
		if(v == abmelden) {
			Intent in = new Intent(Uebersicht.this, LoginSicht.class);
			ActivityRegistry.finishAll();
			startActivity(in);
		}
	}
	
	/**
	 * Menu Optionen
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_room_search, menu);
        for(int i = 0; i < menu.size(); i++) {
        	menu.getItem(i).setOnMenuItemClickListener(this);
        	if(menu.getItem(i).hasSubMenu()) {
    			for(int k = 0; k < menu.getItem(i).getSubMenu().size(); k++) {
    				menu.getItem(i).getSubMenu().getItem(k).setOnMenuItemClickListener(this);
    				if(menu.getItem(i).getSubMenu().getItem(k).getTitle().equals(getString(R.string.create_plan))) {
    			        data.open();
    			        if(!data.controlPlan(daten[0])) {
    			        	menu.getItem(i).getSubMenu().getItem(k).setVisible(false);
    			        }
    			        data.close();
    			    }
    			}
        	}
        }
        return true;
    }

	public boolean onMenuItemClick(MenuItem item) {
		if(item.getTitle().equals(getString(R.string.beenden))){
			DialogBuilder.beendenDialog(this);
		}
		if(item.getTitle().equals(getString(R.string.create_plan))) {			
			Intent intent = new Intent(Uebersicht.this, EditPlanActivity.class);
	        data.open();
	        data.createPlan(daten[0]);
	        data.close();
			intent.putExtra("DATEN", daten);
			startActivity(intent);
		}
		if(item.getTitle().equals(getString(R.string.edit_plan))) {			
			Intent intent = new Intent(Uebersicht.this, EditPlanActivity.class);
			intent.putExtra("DATEN", daten);
			unregisterReceiver(wr);
			startActivity(intent);
		}
		return false;
	}
	
	public WifiManager getWifi() {
	      return wifi;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		ActivityRegistry.finishAll();
		startActivity(getIntent());	
	}
}
