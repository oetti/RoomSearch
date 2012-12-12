package com.example.roomsearch;

import Datenbank.ActivityRegistry;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * 
 * @author Andreas Oettinger
 *
 */
public class GastNavSicht extends Activity implements OnClickListener, OnMenuItemClickListener {
	private Button beuth, stras, gauss, bauwesen;
	
	/**
	 * In der Ansicht wird der Campusplan für den Besucher angezeigt 
	 */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gast_nav_sicht);
        ActivityRegistry.register(this);
        
        beuth = (Button) findViewById(R.id.beuth_button);
        beuth.setOnClickListener(this);
        stras = (Button) findViewById(R.id.grashof_button);
        stras.setOnClickListener(this);
        gauss = (Button) findViewById(R.id.gauss_button);
        gauss.setOnClickListener(this);
        bauwesen = (Button) findViewById(R.id.bauwesen_button);
        bauwesen.setOnClickListener(this);
	}

	public void onClick(View v) {
		final Intent in = new Intent(GastNavSicht.this, GastFrageSicht.class);
		
		if(beuth == v) {
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
			 myAlertDialog.setTitle("Haus Beuth");
			 myAlertDialog.setMessage("Willst du zum Gebäude Beuth?\n\nWeitere Informationen zu dem Gebäude kommen hier.");
			 myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				in.putExtra("Haus", "Gauß");
				startActivity(in);
			 }});
			 myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
			 }});
			 
			 myAlertDialog.show();
		}
		
		if(stras == v) {
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
			 myAlertDialog.setTitle("Haus Grashof");
			 myAlertDialog.setMessage("Willst du zum Gebäude Grashof?\n\nWeitere Informationen zu dem Gebäude kommen hier.");
			 myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				in.putExtra("Haus", "Gauß");
				startActivity(in);
			 }});
			 myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
			 }});
			 
			 myAlertDialog.show();
		}
		 
		if(gauss == v) {
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
			 myAlertDialog.setTitle("Haus Gauß");
			 myAlertDialog.setMessage("Willst du zum Gebäude Gauß?\n\nWeitere Informationen zu dem Gebäude kommen hier.");
			 myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				in.putExtra("Haus", "Gauß");
				startActivity(in);
			 }});
			 myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
			}});
			 
			 myAlertDialog.show();
			
		}
		
		if(bauwesen == v) {
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
			 myAlertDialog.setTitle("Haus Bauwesen");
			 myAlertDialog.setMessage("Willst du zum Gebäude Bauwesen?\n\nWeitere Informationen zu dem Gebäude kommen hier.");
			 myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				in.putExtra("Haus", "Gauß");
				startActivity(in);
			 }});
			 myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
			 }});
			 
			 myAlertDialog.show();
		}
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
	
	/**
	 * Menu Optionen
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_room_search, menu);
        menu.getItem(3).setVisible(false);
        for(int i = 0; i < menu.size(); i++) {
        	menu.getItem(i).setOnMenuItemClickListener(this);
        }
        
        return true;
    }
	
	public boolean onMenuItemClick(MenuItem item) {
		if(item.getTitle().equals("Programm")){
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
			 myAlertDialog.setTitle("Beenden");
			 myAlertDialog.setMessage("Willst du Room Search wirklich beenden?");
			 myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				 ActivityRegistry.finishAll();
			 }});
			 myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				  // tue nicht
			 }});
			 
			 myAlertDialog.show();
		}
		return false;
	}
}
