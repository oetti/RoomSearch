package com.example.roomsearch;

import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.TextView;

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
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.vorlesung = intent.getExtras().getString("Vorlesung");
        String raumnum = intent.getExtras().getString("Nummer");
        setContentView(R.layout.cam_nav_sicht);
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
       
	}

	/*
	 * welche Location und Darstellung ausgewählt werden sollen
	 */
	public int getLocationOptions() {
		return 1;
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
