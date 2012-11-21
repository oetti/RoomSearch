package com.example.roomsearch;

import java.io.IOException;

import location.WifiReceiver;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

public class CamNavSicht extends Activity implements SurfaceHolder.Callback, OnClickListener {
	private String vorlesung;
	private android.hardware.Camera cam;
	private SurfaceView camView;
	private SurfaceHolder holder;
	private boolean previewing = false;
	private Button raumnummer;
	private Canvas canvas = new Canvas();
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	// dummy Raumposition
	private double[] raumPos = {6, 123};
	// WifiManager
	private WifiManager wifi;
	boolean wifiWasEnabled;
	@SuppressWarnings("unused")
	private int networkID = -1;
	WifiReceiver wr;
	public ImageView pfeil;
	// Kompass
	private static SensorManager sensorService;
	private Sensor sensor;
	
	
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.vorlesung = intent.getExtras().getString("Vorlesung");
        String raumnum = intent.getExtras().getString("Nummer");
        setContentView(R.layout.cam_nav_sicht);
        
        // Wlan Location
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiWasEnabled = wifi.isWifiEnabled();
        wr = new WifiReceiver(this);
        IntentFilter i = new IntentFilter();
        i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wr,i);
        networkID = wifi.getConnectionInfo().getNetworkId();
        wifi.startScan();
        
        // Kompass location
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
        
        String nummer = "";
        
        TextView raum = (TextView) findViewById(R.id.textView_raum);
        
        // Icon
        raumnummer = (Button) findViewById(R.id.button_raum);
        
        if(vorlesung != null) {
        	String[] split = vorlesung.split(" ");
        	nummer = split[1];
        	raumnummer.setText(nummer);
        	
        	// Vorlesung eintragen
        	raum.setText(vorlesung);
        }
        
        if(raumnum != null) {
        	raumnummer.setText(raumnum);
        	
        	// Raum eintragen
        	raum.setText(raumnum);
        }
        
        raumnummer.setOnClickListener(this);        
               
        // SlideDrawer für Raumplan
        View view = findViewById(R.id.view1);
        if(nummer.equals("341")) {
        	 view.setBackgroundResource(R.drawable.ic2_stundenplan);
        }
                
        // Camera
        camView = (SurfaceView) findViewById(R.id.surfaceView1);
        holder = camView.getHolder();
        holder.addCallback(this);
        
        // Paint Linie
        
        // Pfeil
        pfeil = (ImageView) findViewById(R.id.pfeil_richtung);
       
	}
	
	private SensorEventListener mySensorEventListener = new SensorEventListener() {

	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }

	    public void onSensorChanged(SensorEvent event) {
	      float azimuth = event.values[0];
	      wr.updateData(azimuth);
	    }
	  };
	  
	  @Override
	  protected void onDestroy() {
	    super.onDestroy();
	    if (sensor != null) {
	      sensorService.unregisterListener(mySensorEventListener);
	    }
	  }

	/*
	 * gibt die Raumposition wieder
	 */
	public double[] getRaumPosition() {
		return raumPos;
	}
	
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		paint.setColor(Color.GREEN);
        paint.setStrokeWidth(4.0f);
        canvas.drawLine(raumnummer.getX(), raumnummer.getY(), 50, 50, paint);
        canvas.save();
        camView.draw(canvas);
		
		if(previewing){
			 cam.stopPreview();
			 previewing = false;
		}
		
		if (cam != null){
			 try {
			  cam.setPreviewDisplay(holder);
			  cam.setDisplayOrientation(90);
			  cam.startPreview();
			  //camView.buildDrawingCache();
			  previewing = true;
			 } catch (IOException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
			 }
		}
	}

	public WifiManager getWifi() {
	      return wifi;
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
		cam = android.hardware.Camera.open();	 
	}

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

	class MySurfaceView extends SurfaceView implements Runnable{

		public MySurfaceView(Context context) {
			super(context);
			// TODO Automatisch generierter Konstruktorstub
		}

		public void run() {
			// TODO Automatisch generierter Methodenstub
			
		}
	}
}
