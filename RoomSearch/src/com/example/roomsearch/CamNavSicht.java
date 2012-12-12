package com.example.roomsearch;

import java.io.IOException;

import location.WifiReceiver;
import Datenbank.ActivityRegistry;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
//import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Kameraansicht
 * Mit dieser Klasse wird der Nutzer zu seinen Raum gef�hrt.
 * Durch Augmented Reality kann der Nutzer visuell seinen Zielraum sehen, welche Richtung er gehen muss, Informationen
 * zum Raum und wieviel Meter er vom Zielraum entfernt ist.
 *   
 * @author Andreas Oettinger
 * 
 */
public class CamNavSicht extends Activity implements SurfaceHolder.Callback, OnClickListener, OnDrawerCloseListener, OnDrawerOpenListener, OnMenuItemClickListener {
	// Vorlesungsraum und welche Vorlesung
	private String vorlesung;
	// Kamera Zugriff auf die Hardware des Handys auf Androidbasis 
	private android.hardware.Camera cam;
	// Auf dieser Fl�che wird die Aufnahme der Kamera gezeichnet 
	private SurfaceView camView;
	private SurfaceHolder holder;
	private boolean previewing = false;
	// In diesem Button wird die Zielraumnummer angezeigt 
	private Button raumnummer;
	
	private SlidingDrawer slider;
	// dummy Raumposition
	private double[] raumPos = {6, 123};
	// WifiManager greift auf das WLan oder Gps von dem Handy zu
	private WifiManager wifi;
	boolean wifiWasEnabled;
	@SuppressWarnings("unused")
	private int networkID = -1;
	//erbt von BroadcastReceiver behandelt eingehende Signale
	WifiReceiver wr;
	// zeigt die Richtung des Zielraumes an
	public ImageView pfeil;
	// Kompass
	private static SensorManager sensorService;
	private Sensor sensor;
	// zeigt den Abstand zwischen Person und Zielraum (in Meter)
	public TextView abstandView;
	
	/**
	 * In dieser Klasse wird die Ansicht erstellt.
	 */
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �bernimmt und speichert Daten von Activities
        Intent intent = getIntent();
        // uebergibt Daten weiter uebermittelte Daten der vorigen Activity
        // von Nav_Sicht.java
        this.vorlesung = intent.getExtras().getString("Vorlesung");
        // von Gast_Frage_Sicht.java
        String raumnum = intent.getExtras().getString("Nummer");
        // setzt die View als aktuelle anzusehende View
        setContentView(R.layout.cam_nav_sicht);
        ActivityRegistry.register(this);
        
        /*
         * Wifi Manager (Zugriff auf WLan und GPS)
        */ 
        // Den Service setzen der Activity
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        // der WifiManager wird freigeschaltet
        wifiWasEnabled = wifi.isWifiEnabled();
        // BroadcastReceiver erzeugen und zuweisen
        wr = new WifiReceiver(this);
        //
        IntentFilter i = new IntentFilter();
        i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wr,i);
        // Netzwerk ID setzen
        // testen ob die beiden unteren Methoden entfernt werden k�nnen
        networkID = wifi.getConnectionInfo().getNetworkId();
        wifi.startScan();
     
        /*
         *  Kompass
         */
        sensorService = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (sensor != null) {
          sensorService.registerListener(mySensorEventListener, sensor,
              SensorManager.SENSOR_DELAY_NORMAL);
          Log.i("Compass MainActivity", "Registerered for ORIENTATION Sensor");

        } else {
          Log.e("Compass MainActivity", "Registerered for ORIENTATION Sensor");
          Toast.makeText(this, "ORIENTATION Sensor not found",
              Toast.LENGTH_LONG).show();
          finish();
        }
        
        // reine Raumnummer setzen z.B. "341"
        String nummer = "";
        // In dieser View wird der Raum + die Vorlesung dann gesetzt
        TextView raum = (TextView) findViewById(R.id.textView_raum);
        
        // dieser Button zeigt an welcher Raum ausgew�hlt wurde
        raumnummer = (Button) findViewById(R.id.button_raum);
        
        // ist die Vorlesung nicht leer splitte den String und
        // nehme den Index 1 aus dem Array und setze ihn als Raumnummer
        if(vorlesung != null) {
        	String[] split = vorlesung.split(" ");
        	nummer = split[1];
        	raumnummer.setText(nummer);
        	
        	// Vorlesung eintragen
        	raum.setText(vorlesung);
        }
        
        // ist der String Raumnum nicht leer setze ihn als Raumnummer
        if(raumnum != null) {
        	raumnummer.setText(raumnum);
        	
        	// Raum eintragen
        	raum.setText(raumnum);
        }
   
        // EventListener wenn der Button geklickt wird
        raumnummer.setOnClickListener(this);
        // EventListener f�r den Slider
        slider = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
        slider.setOnDrawerCloseListener(this);
        slider.setOnDrawerOpenListener(this);
               
        // SlideDrawer f�r Raumplan
        View view = findViewById(R.id.view1);
        // Stimmt die Nummer �berein setze den Raumplan in den SlideDrawer
        if(nummer.equals("341")) {
        	 view.setBackgroundResource(R.drawable.ic2_stundenplan);
        }
                
        /*
         *  Kamera
         */
        // In dieser SurfaceView wird das Kamerabild gezeichnet
        camView = (SurfaceView) findViewById(R.id.surfaceView1);
        holder = camView.getHolder();
        holder.addCallback(this);
        
        /*
         *  Richtungspfeil
         */
        pfeil = (ImageView) findViewById(R.id.pfeil_richtung);
        abstandView = (TextView) findViewById(R.id.text_abstand);
        
        /*
         *  Button erstellen
         */   
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
	
	/**
	 * In dieser Klasse wird SensorEventListener erstellt
	 */
	private SensorEventListener mySensorEventListener = new SensorEventListener() {

	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }

	    /**
	     * Wechselt die Richtung des Handies von Norden nach Osten wird 
	     * darauf hier reagiert
	     */
	    public void onSensorChanged(SensorEvent event) {
	    	// gibt die Gradzahl des Kompass anhand des Nordens an
	    	float azimuth = event.values[0];
	    	// �bergebe den Wert zum Receiver
	    	wr.updateData(azimuth);
	    	raumnummer.setX(azimuth);
	    }
	  };
	  
	  @Override
	  protected void onDestroy() {
	    super.onDestroy();
	    if (sensor != null) {
	      sensorService.unregisterListener(mySensorEventListener);
	    }
	  }

	/**
	 * Gibt die Position des Raumes im Kordinatensystem wieder.
	 */
	public double[] getRaumPosition() {
		return raumPos;
	}
	
	/**
	 * In dieser Methode wird darauf reagiert wenn die SurfaceView neu gezeichnet
	 * werden muss.
	 */
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
		if(previewing){
			 cam.stopPreview();
			 previewing = false;
		}
		
		if (cam != null){
			 try {
				 cam.setPreviewDisplay(holder);
				 cam.setDisplayOrientation(90);
				 cam.startPreview();
				 previewing = true;
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		}
	}

	/**
	 * Gibt den WifiManager wieder.
	 * 
	 * @return WifiManager
	 */
	public WifiManager getWifi() {
	      return wifi;
	}
	
	/**
	 * Wird eine SurfaceView erstellt setze die Kamera.
	 */
	public void surfaceCreated(SurfaceHolder holder) {
		cam = android.hardware.Camera.open();	 
	}

	/**
	 * Wird die SurfaceView gel�scht, stoppe die Kameraaufnahme, gebe sie wieder frei
	 * und setze sie auf null.
	 */
	public void surfaceDestroyed(SurfaceHolder holder) {
		cam.stopPreview();
		cam.release();
		cam = null;
		previewing = false;
	}

	public void onClick(View arg0) {
		SlidingDrawer slider = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		slider.animateOpen();
	}
/*	
	class MySurfaceView extends SurfaceView implements Runnable{

		public MySurfaceView(Context context) {
			super(context);
			// TODO Automatisch generierter Konstruktorstub
		}

		public void run() {
			// TODO Automatisch generierter Methodenstub
			
		}
	}*/

	public void onDrawerClosed() {
		pfeil.setVisibility(1);
	}

	public void onDrawerOpened() {
		pfeil.setVisibility(TRIM_MEMORY_BACKGROUND);
	}
}
