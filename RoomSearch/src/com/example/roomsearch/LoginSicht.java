package com.example.roomsearch;

import com.example.roomsearch.R;

import create.edit.delete.CreateProfilActivity;
import datenbank.DBSicht;
import datenbank.InterneDatenbank;
import extras.ActivityRegistry;
import extras.DialogBuilder;
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
	private Button senden, gast, erstellen;
	// Die Klasse überprüft den Accountnamen und das dazu gehörige Passwort
	private InterneDatenbank data;
	
	/**
	 * Ansicht setzen
	 */
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_sicht);
		ActivityRegistry.finishAll();
		ActivityRegistry.register(this);
		//interne datenbank
		data = new InterneDatenbank(this);
		//data.fillWochentag();
		// Sende-Button
		senden = (Button) findViewById(R.id.eingabe_button);
		senden.setOnClickListener(this);
		// Gast-Button
		gast = (Button) findViewById(R.id.gast_button);
		gast.setOnClickListener(this);
		// Gast-Button
		erstellen = (Button) findViewById(R.id.button_profilerstellen_login);
		erstellen.setOnClickListener(this);
	}

	/**
	 * Klick-Event
	 */
	public void onClick(final View v) {
		// Wird Senden-Button gedrückt dann mache das
		if(v == senden) {
			startNutzerActivity();
		}
		
		if(v == gast) {
			startGastActivity();
		}
		
		if(v==erstellen) {
			Intent in = new Intent(LoginSicht.this, CreateProfilActivity.class);
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
        	
        	if(menu.getItem(i).getTitle().equals(getString(R.string.profil)) 
        			|| menu.getItem(i).getTitle().equals(getString(R.string.vorlesungsplan))) {
        		menu.getItem(i).setVisible(false);
        	}
        	menu.getItem(i).setOnMenuItemClickListener(this);
        }
        return true;
    }

	public boolean onMenuItemClick(MenuItem item) {
		if(item.getTitle().equals(getString(R.string.beenden))){
			DialogBuilder.beendenDialog(this);
		}
		if(item.getTitle().equals("DB")){
			Intent in = new Intent(LoginSicht.this, DBSicht.class);
			startActivity(in);
		}
		return false;
	}
	
	private void startGastActivity() {
		Intent in = new Intent(LoginSicht.this, GastUebersicht.class);
		in.putExtra("ROLLE", getString(R.string.gastbereich));
		String[] gastdaten = {getString(R.string.besucher)};
		in.putExtra("DATEN", gastdaten);
		startActivity(in);
	}
	
	private void startNutzerActivity() {
		// HRZ-Kennung und Passwort
		TextView name = (TextView) findViewById(R.id.matText);
		TextView pw = (TextView) findViewById(R.id.pwText);
		// dummy
		name.setText("Oetti");
		pw.setText("1234");
		// Daten ins Array packen
		String [] daten = {name.getText().toString(),pw.getText().toString()};
		
		data.open();
		if(data.datenKontrolle(daten)) {
			// Sind die Daten korrekt wechsel die Ansicht
			Intent in = new Intent(LoginSicht.this, Uebersicht.class);
			// durchlaufen der liste mit den nutzerdaten und fülle diese um in das array
			String[] nutzerdaten = new String[6];
			int zaehler = 0;
			for(String datenitem : data.gibAllBenutzerdaten(daten)) {
				nutzerdaten[zaehler] = datenitem;
				zaehler++;
			}
			// übergebe role
			in.putExtra("ROLLE", data.getRole());
			// übergebe die nutzerdaten an die nächste activity
			in.putExtra("DATEN", nutzerdaten);
			data.close();
			// starte die neue Activity
			startActivity(in);
		} else {
			Toast toast = Toast.makeText(this, R.string.login_fehler, Toast.LENGTH_LONG);
			toast.show();
			data.close();
		}
	}
	
	@Override
	public void onBackPressed() {
		DialogBuilder.beendenDialog(this);
	}
}
