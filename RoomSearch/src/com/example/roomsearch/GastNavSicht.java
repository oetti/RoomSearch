package com.example.roomsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * 
 * @author Andreas Oettinger
 *
 */
public class GastNavSicht extends Activity implements OnClickListener {
	@SuppressWarnings("unused")
	private Button beuth, stras, gauss, bauwesen;
	
	/**
	 * In der Ansicht wird der Campusplan für den Besucher angezeigt 
	 */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gast_nav_sicht);
        
        //beuth = (Button) findViewById(R.id.beuth_button);
        //beuth.setOnClickListener(this);
        //stras = (Button) findViewById(R.id.stras_button);
        //stras.setOnClickListener(this);
        gauss = (Button) findViewById(R.id.gauss_button);
        gauss.setOnClickListener(this);
        //bauwesen = (Button) findViewById(R.id.bauwesen_button);
        //bauwesen.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent in = new Intent(GastNavSicht.this, GastFrageSicht.class);
		
		/*if(beuth == v) {
			
		}
		
		if(stras == v) {
			
		}
		 */
		if(gauss == v) {
			in.putExtra("Haus", "Gauß");
			startActivity(in);
		}
		/*
		if(bauwesen == v) {
	
		}*/
	}
	
	/**
	 * Wenn der zurück Button auf Handy gedrückt wird soll eine bestimmte
	 * Ansicht gesetzt werden.
	 */
	@Override
	public void onBackPressed() {
		// TODO Automatisch generierter Methodenstub
		super.onBackPressed();
		final Intent in = new Intent (GastNavSicht.this, LoginSicht.class);
			startActivity(in);  
	}
}
