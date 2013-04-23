package com.example.roomsearch;

import com.example.roomsearch.R;
import datenbank.Datenbank;
import datenbank.GaussRaumPositionen;
import extras.ActivityRegistry;
import extras.DialogBuilder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class VorlesungsPlan extends Activity implements OnMenuItemClickListener {
	private Datenbank interneDatenbank = new Datenbank();
	private Button finden;
	private String rolle;
	private String[] daten;
	private Animation animAlpha;
	// Liste von Vorlesungen
	private ListView mo, di, mi, don, fr; 
	private View selectedItem = null;
	private String selectedItemString = "";
	// Dateninhalte
	private Datenbank dt;
	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        rolle = intent.getExtras().getString("ROLLE");
        daten = intent.getExtras().getStringArray("DATEN");
        setContentView(R.layout.vorlesungsplan);
        ActivityRegistry.register(this);
        
        GaussRaumPositionen p = new GaussRaumPositionen();
        p.getPosition(17, 30);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        
        
        
        mo = (ListView) findViewById(R.id.listView_mo); 
        ArrayAdapter<String> sAmon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, interneDatenbank.liste("mo")); 
        mo.setBackgroundColor(Color.WHITE);
        mo.setAdapter(sAmon);
        //mo.setOnItemLongClickListener(this);
        //mo.setOnItemClickListener(this);
        /* Vorlesungen Dienstag
        di = (ListView) findViewById(R.id.ListDi);
        ArrayAdapter<String> sAdie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, interneDatenbank.liste("di")); 
        di.setBackgroundColor(Color.WHITE);
        di.setAdapter(sAdie);
        di.setOnItemLongClickListener(this);
        di.setOnItemClickListener(this);
        // Vorlesungen Mittwoch
        mi = (ListView) findViewById(R.id.ListMi);
        ArrayAdapter<String> sAmit = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, interneDatenbank.liste("mi")); 
        mi.setBackgroundColor(Color.WHITE);
        mi.setAdapter(sAmit);
        mi.setOnItemLongClickListener(this);
        mi.setOnItemClickListener(this);
        // Vorlesungen Donnerstag
        don = (ListView) findViewById(R.id.ListDo);
        ArrayAdapter<String> sAdon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, interneDatenbank.liste("do")); 
        don.setBackgroundColor(Color.WHITE);
        don.setAdapter(sAdon);
        don.setOnItemLongClickListener(this);
        don.setOnItemClickListener(this);
        // Vorlesungen Freitag
        fr = (ListView) findViewById(R.id.ListFr);
        ArrayAdapter<String> sAfre = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, interneDatenbank.liste("fr")); 
        fr.setBackgroundColor(Color.WHITE);
        fr.setAdapter(sAfre);
        fr.setOnItemLongClickListener(this);
        fr.setOnItemClickListener(this);
        
        // Button Raum finden
        finden = (Button) findViewById(R.id.button_finden);
        finden.setOnClickListener(this);
        //finden.setOnTouchListener(this);*/
	}
	
	/**
	 * Menu Optionen
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_room_search, menu);
        for(int i = 0; i < menu.size(); i++) {
        	menu.getItem(i).setOnMenuItemClickListener(this);
        }
        
        return true;
    }

	public boolean onMenuItemClick(MenuItem item) {
		if(item.getTitle().equals("Beenden")){
			DialogBuilder.beendenDialog(this);
		}
		return false;
	}
	
	
	
	public void keinNetz(String e) {
		/*
		Toast toast = Toast.makeText(this, "Kein aktiven Accesspoint gefunden!", Toast.LENGTH_SHORT);
		toast.show(); 
		*/
		Toast toast = Toast.makeText(this, e, Toast.LENGTH_SHORT);
		toast.show(); 
	}

	public void onClick(final View v) {
		v.startAnimation(animAlpha);
		Handler handler=new Handler();
      	Runnable r = new Runnable() {
      	public void run() {
        if (v == finden) {
			// von NavSicht zu CamNavSicht
			final Intent in = new Intent (VorlesungsPlan.this, CamNavSicht.class);
			
			try {
				if(selectedItemString.length() == 0) {
					Toast toast = Toast.makeText(v.getContext(), "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
					toast.show(); 
				} else {
					dt = new Datenbank();
					dt.setVorlesung(selectedItemString);
					in.putExtra("Vorlesung", dt.getVorlesung());
					startActivity(in);
				}
			} catch (NullPointerException e) {
				Toast toast = Toast.makeText(v.getContext(), "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
				toast.show(); 
			}	
		}
      	  }
      			}; 
      			handler.postDelayed(r, 20);
	}
	
/*
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		if(arg0.getId() == mo.getId()) {
			for(int k = 0; k < mo.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(interneDatenbank.liste("zeit").get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} else {
						arg1.setLongClickable(false);
					}
				}
			}
		}
			
		if(arg0.getId() == di.getId()) {
			for(int k = 0; k < di.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(interneDatenbank.liste("zeit").get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} else {
						arg1.setLongClickable(false);
					}
				}
			}
		}
		
		if(arg0.getId() == mi.getId()) {
			for(int k = 0; k < mi.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(interneDatenbank.liste("zeit").get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} else {
						arg1.setLongClickable(false);
					}
				}
			}
		}
			
		if(arg0.getId() == don.getId()) {
			for(int k = 0; k < don.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(interneDatenbank.liste("zeit").get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} else {
						arg1.setLongClickable(false);
					}
				}
			}
		}
		
		if(arg0.getId() == fr.getId()) {
			for(int k = 0; k < fr.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(interneDatenbank.liste("zeit").get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} else {
						arg1.setLongClickable(false);
					}
				}
			}
		}
		return false;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if(selectedItem != null) {
			selectedItem.setBackgroundColor(Color.WHITE);
		}
		
		if(arg0.getId() == mo.getId()) {
			for(int k = 0; k < mo.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
				} else {
					arg1.setClickable(false);
				}
				}
			}
		}
			
		if(arg0.getId() == di.getId()) {
			for(int k = 0; k < di.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
				} else {
					arg1.setClickable(false);
				}
				}
			}
		}
		
		if(arg0.getId() == mi.getId()) {
			for(int k = 0; k < mi.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
				} else {
					arg1.setClickable(false);
				}
				}
			}
		}
			
		if(arg0.getId() == don.getId()) {
			for(int k = 0; k < don.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
				} else {
					arg1.setClickable(false);
				}
				}
			}
		}
		
		if(arg0.getId() == fr.getId()) {
			for(int k = 0; k < fr.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
				} else {
					arg1.setClickable(false);
				}
				}
			}
		}
	}

	public boolean onTouch(View arg0, MotionEvent arg1) {
		if(arg1.getAction() == MotionEvent.ACTION_MOVE) {
			final Intent in = new Intent (VorlesungsPlan.this, CamNavSicht.class);
			
			try {
				if(selectedItemString.length() == 0) {
					Toast toast = Toast.makeText(this, "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
					toast.show(); 
				} else {
					dt = new Datenbank();
					dt.setVorlesung(selectedItemString);
					in.putExtra("Vorlesung", dt.getVorlesung());
					startActivity(in);
				}
			} catch (NullPointerException e) {
				Toast toast = Toast.makeText(this, "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
				toast.show(); 
			}
		}
		return false;
	}*/
}
