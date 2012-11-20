package location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.roomsearch.NavSicht;
import com.example.roomsearch.R;

import location.MacAdressen;
import location.Positionen;
import location.Trill;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

public class WifiReceiver extends BroadcastReceiver{
	WifiManager wifi;
	Activity act;
	Dialog restartingLocation = null;
	private double grad = 0;
	double[] nVector;
	
	public WifiReceiver(Activity act) {
		this.act = act;
		if (act instanceof NavSicht) {
			wifi = ((NavSicht) act).getWifi();
		}
		/*
		if (act instanceof CamNavSicht) {
			wifi = act.getWifi();
		}
		
		if (act instanceof GastNavSicht) {
			wifi = act.getWifi();
		}*/
		
	}
	
	@Override
	public void onReceive(Context ctx, Intent intent) {
		if(restartingLocation!=null)
	    {
	      restartingLocation.dismiss();
	      restartingLocation = null;
	    }
	    
		try {
	    // Code to execute when SCAN_RESULTS_AVAILABLE_ACTION event occurs
	    wifi = nav.getWifi();
	    wifi.startScan();
	    String netz = "Netze: \n";
	    @SuppressWarnings("rawtypes")
		List db = wifi.getScanResults();
	    
	    
	    @SuppressWarnings("rawtypes")
		Iterator it = db.iterator();
	    List<ScanResult> sortList = new ArrayList<ScanResult>();
	    
	    MacAdressen m = new MacAdressen();
    	int level;
    	String mac;
	    while(it.hasNext()) {
	    	ScanResult scan = (ScanResult) it.next();
	    	// OpenNetV3, 3. Etage
	    	if(scan.SSID.startsWith("OpenNetV3") && m.controlling(scan.BSSID)) {
	    		sortList.add(scan);
	    	}
	    }
	   
	    // wlan1 MAC 
	    double[] w1 = m.getAPPPosition(sortList.get(0).BSSID);
	    // wlan1 MAC 
	    double[] w2 = m.getAPPPosition(sortList.get(1).BSSID);
	    // wlan1 MAC 
	    double[] w3 = m.getAPPPosition(sortList.get(2).BSSID);
	   
	    // wlan positionen x und y und distanz
	    double[] wlan1 = {w1[0], w1[1], ((sortList.get(0).level + 32.0f) / -3.8f)};
	    double[] wlan2 = {w2[0], w2[1], ((sortList.get(1).level + 32.0f) / -3.8f)};
	    double[] wlan3 = {w3[0], w3[1], ((sortList.get(2).level + 32.0f) / -3.8f)};
	   
	   // Trilation
	   Trill t = new Trill();
	   
	   // errechnen meiner Location
	   double[] location = t.rechnen(wlan1, wlan2, wlan3);
	   
	   if (nav.getLocationOptions() == 0) {
		   
	   }
	   // Klasse Position für Besonderheiten bestimmter Bereiche z.B. (TH1 z.B.)
	   Positionen p = new Positionen();
	   /*
	   // errechnen des neuen Vektors
	   nVector = new double[2];
	   nVector[0] = (location[0] - 6);
	   nVector[1] = (location[1] - 124);
	      
	   // Abstand zwischen meiner Position bis zum Zielpunkt
	   long abstand =  Math.round(Math.sqrt(nVector[0]*nVector[0] + nVector[1]*nVector[1]));
	   */
	   // TestView
	   nav.standort.setText(netz + "\nMeine Position x: " + Math.round(location[0]) + " y: " + Math.round(location[1]) 
			   				+"\nSpaceLocation: " +  p.getPosition(location[0], location[1]) + "\n\n"); 
		
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	 
	// Pfeil
	  
	  //double hypotenuse = Math.sqrt(nVector[0]*nVector[0] + (89-location[1])*(89-location[1]));
	  //double ankathete =  (location[0] - 6);
		//double cosa = ankathete / hypotenuse;
	//System.out.println( Math.toDegrees(Math.acos(0.7660)));
	 //grad = Math.toDegrees(Math.acos(cosa));
/*		
	// je nach Gradzahl wird ein anderer Pfeil als image gesetzt
	  if(grad > 0 && grad < 10) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_0);}
	  if(grad > 10 && grad < 20) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_10);}
	  if(grad > 20 && grad < 30) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_20);}
	  if(grad >= 30 && grad < 40) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_30);}
	  if(grad >= 40 && grad < 50) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_40);}
	  if(grad >= 50 && grad < 60) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_50);}
	  if(grad >= 60 && grad < 70) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_60);}
	  if(grad >= 70 && grad < 80) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_70);}
	  if(grad >= 80 && grad < 90) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_80);}
	  if(grad >= 90 && grad < 100) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_90);}
	  if(grad >= 100 && grad < 110) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_100);}
	  if(grad >= 110 && grad < 120) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_110);}
	  if(grad >= 120 && grad < 130) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_120);}
	  if(grad >= 130 && grad < 140) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_130);}
	  if(grad >= 140 && grad < 150) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_140);}
	  if(grad >= 150 && grad < 160) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_150);}
	  if(grad >= 160 && grad < 170) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_160);}
	  if(grad >= 170 && grad < 180) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_170);}
	  if(grad >= 180 && grad < 190) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_180);}
	  if(grad >= 190 && grad < 200) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_190);}
	  if(grad >= 200 && grad < 210) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_200);}
	  if(grad >= 210 && grad < 220) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_210);}
	  if(grad >= 220 && grad < 230) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_220);}
	  if(grad >= 230 && grad < 240) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_230);}
	  if(grad >= 240 && grad < 250) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_240);}
	  if(grad >= 250 && grad < 260) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_250);}
	  if(grad >= 260 && grad < 270) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_260);}
	  if(grad >= 270 && grad < 280) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_270);}
	  if(grad >= 280 && grad < 290) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_280);}
	  if(grad >= 290 && grad < 300) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_290);}
	  if(grad >= 300 && grad < 310) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_300);}
	  if(grad >= 310 && grad < 320) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_310);}
	  if(grad >= 320 && grad < 330) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_320);}
	  if(grad >= 330 && grad < 340) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_330);}
	  if(grad >= 340 && grad < 350) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_340);}
	  if(grad >= 350 && grad < 360) {cam.pfeil.setBackgroundResource(R.drawable.pfeil_350);}
	  if(grad == 360) {
			grad = 0;
	  }
	 */
	   grad= grad +5;
	  }
}
