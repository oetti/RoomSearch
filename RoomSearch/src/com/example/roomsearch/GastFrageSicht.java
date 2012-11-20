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

public class GastFrageSicht extends Activity implements OnClickListener {
	private Button suchen;
	private Hausverwaltung h = new Hausverwaltung();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String haus ="Du hast das Haus " + intent.getExtras().getString("Haus") + " ausgewählt.";
        setContentView(R.layout.gast_frage_sicht);
        
        // Überschrift welches Haus ausgewählt wurde
        TextView hausname = (TextView) findViewById(R.id.text_info_haus);
        hausname.setText(haus);
        
        // Suche-Button
        suchen = (Button) findViewById(R.id.suchen_button);
        suchen.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		Intent in = new Intent(GastFrageSicht.this, CamNavSicht.class);
		
		// eingegebene Raumnummer
		TextView text = (TextView) findViewById(R.id.editText1);
		try {
			// kontrolliere ob die Nummer auch stimmt
			if(h.beuthCheck(text.getText().toString())) {
				in.putExtra("Nummer", text.getText().toString());
				startActivity(in);
			} else {
				Toast toast = Toast.makeText(this, "Tut mir leid. Diesen Raum gibt es nicht!", Toast.LENGTH_SHORT);
				toast.show(); 
			}
		} catch (StringIndexOutOfBoundsException e) {
			Toast toast = Toast.makeText(this, "Es wurde keine Raumnummer eingegeben.", Toast.LENGTH_SHORT);
			toast.show(); 
		}
	}
}
