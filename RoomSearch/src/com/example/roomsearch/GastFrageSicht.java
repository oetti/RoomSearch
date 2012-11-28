package com.example.roomsearch;

import Datenbank.Hausverwaltung;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * In dieser Klasse wird das Geb�ude angezeigt was der Gast ausgew�hlt hat.
 * Nun wird der Gast hier gefragt welchen Raum er denn sucht.
 * 
 * @author Andreas Oettinger
 *
 */
public class GastFrageSicht extends Activity implements OnClickListener {
	private Button suchen;
	// In dieser Klasser wird die eingebene Raumnummer �berpr�ft.
	private Hausverwaltung h = new Hausverwaltung();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �bernimmt und speichert Daten von Activities
        Intent intent = getIntent();
        // uebergibt Daten weiter uebermittelte Daten der vorigen Activity
        // von Gast_Nav_Sicht
        String haus ="Du hast das Haus " + intent.getExtras().getString("Haus") + " ausgew�hlt.";
        // setzt die View als aktuelle anzusehende View
        setContentView(R.layout.gast_frage_sicht);
        // �berschrift welches Haus ausgew�hlt wurde
        TextView hausname = (TextView) findViewById(R.id.text_info_haus);
        // Geb�udename z.B. Gauss
        hausname.setText(haus);
        
        // Suche-Button
        suchen = (Button) findViewById(R.id.suchen_button);
        suchen.setOnClickListener(this);
	}
	
	/**
	 *  Wird der Button suchen gedr�ckt wird ein Event ausgel�st der eine neue
	 *  Activity startet
	 */
	public void onClick(View arg0) {
		Intent in = new Intent(GastFrageSicht.this, CamNavSicht.class);
		
		// eingegebene Raumnummer
		TextView text = (TextView) findViewById(R.id.editText1);
		try {
			// kontrolliere ob die Nummer auch stimmt, wenn ja gib sie weiter an
			// die n�chste Activity und starte sie
			if(h.beuthCheck(text.getText().toString())) {
				in.putExtra("Nummer", text.getText().toString());
				startActivity(in);
			} else {
				// Dialog Fehler
				Toast toast = Toast.makeText(this, "Tut mir leid. Diesen Raum gibt es nicht!", Toast.LENGTH_SHORT);
				toast.show(); 
			}
		} catch (StringIndexOutOfBoundsException e) {
			
			Toast toast = Toast.makeText(this, "Es wurde keine Raumnummer eingegeben.", Toast.LENGTH_SHORT);
			toast.show(); 
		}
	}
}
