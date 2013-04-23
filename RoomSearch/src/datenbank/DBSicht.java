package datenbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.roomsearch.R;

public class DBSicht extends Activity {
	private TextView nutzer, wochentag, vorlesung, beschreibung, detail;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.db);
	        InterneDatenbank data = new InterneDatenbank(this);
	        data.open();
	        nutzer = (TextView)findViewById(R.id.table_nutzer);
	        vorlesung = (TextView)findViewById(R.id.table_vorlesung);
	        beschreibung = (TextView)findViewById(R.id.table_beschreibung);
	        detail = (TextView)findViewById(R.id.table_detail);
	        
	        String nutzertabelle = "";
	        for(String nutzer: data.gibNutzerdaten()) {
	        	nutzertabelle = nutzertabelle + nutzer + "\n"+"-----------------------\n";
	        }
	        nutzer.setText(nutzertabelle);
	        
	        String beschreibungtabelle = "";
	        for(String beschreibung: data.gibBeschreibung()) {
	        	beschreibungtabelle = beschreibungtabelle + beschreibung + "\n"+"-----------------------\n";
	        }
	        beschreibung.setText(beschreibungtabelle);
	       
	        String vorlesungtabelle = "";
	        for(String tag: data.gibVorlesungen()) {
	        	vorlesungtabelle = vorlesungtabelle + tag + "\n"+"-----------------------\n";
	        }
	        vorlesung.setText(vorlesungtabelle);
	        data.close();
	 }

}
