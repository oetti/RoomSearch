package com.example.roomsearch;

import java.util.ArrayList;

import location.WifiReceiver;
import Datenbank.Datenbank;
import Datenbank.Listfiller;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NavSicht extends Activity implements OnClickListener, OnCheckedChangeListener{
	private Listfiller filler = new Listfiller();
	private Spinner spinMon, spinDie, spinMit, spinDon, spinFre, spinEtage;
	private CheckBox chMo, chDi, chMi, chDo, chFr;
	public TextView standort;
	private Datenbank dt;
	private WifiManager wifi;
	boolean wifiWasEnabled;
	private int networkID = -1;
	WifiReceiver wr;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_sicht);
        // Wlan Location
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiWasEnabled = wifi.isWifiEnabled();
        wr = new WifiReceiver(this);
        IntentFilter i = new IntentFilter();
        i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wr,i);
        networkID = wifi.getConnectionInfo().getNetworkId();
        wifi.startScan();
            
        // Vorlesungen Montag
        spinMon = (Spinner) findViewById(R.id.spinner_mon);
        ArrayAdapter<String> sAmon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.montag()); 
        spinMon.setAdapter(sAmon);
        
        // Vorlesungen Dienstag
        spinDie = (Spinner) findViewById(R.id.spin_die);
        ArrayAdapter<String> sAdie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.dienstag()); 
        spinDie.setAdapter(sAdie);
        
        // Vorlesungen Mittwoch
        spinMit = (Spinner) findViewById(R.id.spin_mit);
        ArrayAdapter<String> sAmit = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.mittwoch()); 
        spinMit.setAdapter(sAmit);
        
        // Vorlesungen Donnerstag
        spinDon = (Spinner) findViewById(R.id.spinner_don);
        ArrayAdapter<String> sAdon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.donnerstag()); 
        spinDon.setAdapter(sAdon);
        
        // Vorlesungen Freitag
        spinFre = (Spinner) findViewById(R.id.spinner_fre);
        ArrayAdapter<String> sAfre = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.freitag()); 
        spinFre.setAdapter(sAfre);
        
        // Standort
        standort = (TextView)findViewById(R.id.textView2);
        
        // Button Raum finden
        Button finden = (Button) findViewById(R.id.button_finden);
        finden.setOnClickListener(this);
        
        // CheckBoxen
        chMo = (CheckBox)findViewById(R.id.checkBox_mon);
		chDi = (CheckBox)findViewById(R.id.checkBox_die);
		chMi = (CheckBox)findViewById(R.id.checkBox_mit);
		chDo = (CheckBox)findViewById(R.id.checkBox_don);
		chFr = (CheckBox)findViewById(R.id.checkBox_fre);
		
		chMo.setOnCheckedChangeListener(this);
		chDi.setOnCheckedChangeListener(this);
		chMi.setOnCheckedChangeListener(this);
		chDo.setOnCheckedChangeListener(this);
		chFr.setOnCheckedChangeListener(this);
	}
	
	 public WifiManager getWifi() {
	      return wifi;
	    }
	
	/*
	 * welche Location und Darstellung ausgewählt werden sollen
	 */
	public int getLocationOptions() {
		return 0;
	}

	public void onClick(View v) {
		// von NavSicht zu CamNavSicht
		final Intent in = new Intent (NavSicht.this, CamNavSicht.class);
			
		Spinner spin = null;
		
		if(chMo.isChecked()){
			spin = spinMon;
		}
		
		if(chDi.isChecked()){
			spin = spinDie;
		}
		
		if(chMi.isChecked()){
			spin = spinMit;
		}
		
		if(chDo.isChecked()){
			spin = spinDon;
		}
		
		if(chFr.isChecked()){
			spin = spinFre;
		}
		
		try {
		dt = new Datenbank(spin.getSelectedItem().toString());
		in.putExtra("Vorlesung", dt.getVorlesung());
		startActivity(in);
		} catch (NullPointerException e) {
			Toast toast = Toast.makeText(this, "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
			toast.show(); 
		}		
	}

	/**
	 * Kontrolliert ob eine der Checkboxen sich ändert
	 */
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if(arg0 == chMo) {
			if(arg1 == true) {
				chMo.setText("ausgewählt");
				chDi.setChecked(false);
				chMi.setChecked(false);
				chDo.setChecked(false);
				chFr.setChecked(false);
			} else {
				chMo.setText(R.string.aktiv);
			}
		}
		
		if(arg0 == chDi) {
			if(arg1 == true) {
				chDi.setText("ausgewählt");
				chMo.setChecked(false);
				chMi.setChecked(false);
				chDo.setChecked(false);
				chFr.setChecked(false);
			} else {
				chDi.setText(R.string.aktiv);
			}
		}
		
		if(arg0 == chMi) {
			if(arg1 == true) {
				chMi.setText("ausgewählt");
				chMo.setChecked(false);
				chDi.setChecked(false);
				chDo.setChecked(false);
				chFr.setChecked(false);
			} else {
				chMi.setText(R.string.aktiv);
			}
		}
		
		if(arg0 == chDo) {
			if(arg1 == true) {
				chDo.setText("ausgewählt");
				chMo.setChecked(false);
				chMi.setChecked(false);
				chDi.setChecked(false);
				chFr.setChecked(false);
			} else {
				chDo.setText(R.string.aktiv);
			}
		}
		
		if(arg0 == chFr) {
			if(arg1 == true) {
				chFr.setText("ausgewählt");
				chMo.setChecked(false);
				chMi.setChecked(false);
				chDo.setChecked(false);
				chDi.setChecked(false);
			} else {
				chFr.setText(R.string.aktiv);
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Automatisch generierter Methodenstub
		super.onBackPressed();
		final Intent in = new Intent (NavSicht.this, LoginSicht.class);
			startActivity(in);  
	}
}
