package com.example.roomsearch;

import Datenbank.SecurityService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * In dieser Ansicht kann jemand der ein Profil hat sich einloggen und damit
 * auf seine Daten zugreifen.
 * 
 * Als Gast kann man einfach den Button drücken um weiter zukommen.
 * 
 * @author Andreas Oettinger
 *
 */
public class LoginSicht extends Activity implements OnClickListener, OnMenuItemClickListener {
	private Button senden;
	private Button gast;
	// Die Klasse überprüft den Accountnamen und das dazu gehörige Passwort
	private SecurityService ss = new SecurityService();
	
	/**
	 * Ansicht setzen
	 */
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

	/**
	 * Klick-Event
	 */
	public void onClick(View v) {
		// Wird Senden-Button gedrückt dann mache das
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
	
	/**
	 * Menu Optionen
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_room_search, menu);
        menu.add("deutsch"); 
        menu.add("english");
        MenuItem lang = menu.getItem(1);
        lang.setOnMenuItemClickListener(this);
        return true;
    }

	public boolean onMenuItemClick(MenuItem item) {
		return false;
	}
	
	/**
	 * Zurück Ansicht
	 */
	@Override
	public void onBackPressed() {
		// TODO Automatisch generierter Methodenstub
		super.onBackPressed();
		final Intent in = new Intent (LoginSicht.this, LoginSicht.class);
			startActivity(in);  
	}
}
