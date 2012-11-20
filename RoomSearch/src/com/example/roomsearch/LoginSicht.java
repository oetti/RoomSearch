package com.example.roomsearch;

import Datenbank.SecurityService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginSicht extends Activity implements OnClickListener, OnMenuItemClickListener {
	private Button senden;
	private Button gast;
	private SecurityService ss = new SecurityService();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sicht);
        
        // Sende-Button
        senden = (Button) findViewById(R.id.eingabe_button);
        senden.setOnClickListener(this);
        
        // Gast-Button
        gast = (Button) findViewById(R.id.gast_button);
        gast.setOnClickListener(this);
	}

	public void onClick(View v) {
		// Wird Sende-Button gedrückt dann mache das
		if(v == senden) {
			// HRZ-Kennung und Passwort
			TextView name = (TextView) findViewById(R.id.matText);
			TextView pw = (TextView) findViewById(R.id.pwText);
			
			// Daten ins Array packen
			String [] daten = {name.getText().toString(),pw.getText().toString()};
			
			// Kontrolliere die Daten
			if(ss.check(daten)) {
					// Sind die Daten korrekt wechsel die Ansicht
					Intent in = new Intent(LoginSicht.this, UebergangSicht.class);
					// übernehme den Vornamen des Users
					in.putExtra("Vorname", ss.getName());
					// übernehme die Identität (Student oder Dozent)
					in.putExtra("ID", ss.getIdentität());
					// starte die neue Activity
					startActivity(in);
			
			} else {
				Toast toast = Toast.makeText(this, "HRZ-Kennung oder Passwort sind nicht korrekt!", Toast.LENGTH_SHORT);
				toast.show(); 
			}
		}
		
		if(v == gast) {
			Intent in = new Intent(LoginSicht.this, UebergangSicht.class);
			// übernehme den Vornamen des Users
			in.putExtra("Vorname", "Besucher");
			// übernehme die Identität (Student oder Dozent)
			in.putExtra("ID", 2);
			// starte die neue Activity
			startActivity(in);
		}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_room_search, menu);
        menu.add("Deutsch"); 
        menu.add("Englisch");
        MenuItem lang = menu.getItem(1);
        lang.setOnMenuItemClickListener(this);
        return true;
    }

	public boolean onMenuItemClick(MenuItem item) {
		return false;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Automatisch generierter Methodenstub
		super.onBackPressed();
		final Intent in = new Intent (LoginSicht.this, LoginSicht.class);
			startActivity(in);  
	}
}
