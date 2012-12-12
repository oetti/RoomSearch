package com.example.roomsearch;

import Datenbank.ActivityRegistry;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class UebergangSicht extends Activity {
	private Handler handler;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        // Vorname
        String name = intent.getExtras().getString("Vorname");
        // Identität
        final int id = intent.getExtras().getInt("ID");
        setContentView(R.layout.uebergang_sicht);
        ActivityRegistry.register(this);
        
        // Begruessung beim laden
        TextView text = (TextView) findViewById(R.id.begruessung_text);
        text.setText("Hallo " + name + ".\n\nDein Standort wird ermittelt, das kann etwas dauern." );
      	
        // thread zum Background laden
        handler=new Handler();
      	Runnable r = new Runnable() {
      		public void run() {
      			// Dozentenansicht
      			if(id == 0) {
      				final Intent in = new Intent (UebergangSicht.this, NavSichtDoz.class);
      				startActivity(in);
      			}
      			
      			// Studentenansicht
      			if(id == 1) {
      				final Intent in = new Intent (UebergangSicht.this, NavSicht.class);
      				startActivity(in);   
      			}
      			
      			// Gastansicht
      			if(id == 2) {
      				final Intent in = new Intent (UebergangSicht.this, GastNavSicht.class);
      				startActivity(in); 
      			}
		    }
		}; 
		handler.postDelayed(r, 5000);
	}
       
}
