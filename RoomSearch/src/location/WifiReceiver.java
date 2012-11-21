package location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.example.roomsearch.CamNavSicht;
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
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

public class WifiReceiver extends BroadcastReceiver{
	WifiManager wifi;
	Activity act;
	Dialog restartingLocation = null;
	private double gradUG = 0;
	double[] nVector;
	
	public WifiReceiver(Activity act) {
		this.act = act;
		if (act instanceof NavSicht) {
			wifi = ((NavSicht) act).getWifi();
		}
	
		if (act instanceof CamNavSicht) {
			wifi = ((CamNavSicht) act).getWifi();
		}
		/*
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
			if (act instanceof NavSicht) {
				wifi = ((NavSicht) act).getWifi();
				System.out.println("NavSicht: Wifi wurde gesetzt");
			}
		
			if (act instanceof CamNavSicht) {
				wifi = ((CamNavSicht) act).getWifi();
				System.out.println("CamNavSicht: Wifi wurde gesetzt");
			}
			
			
			//if (act instanceof GastNavSicht) {
			//	wifi = act.getWifi();
			//}
	    
	    wifi.startScan();
	    String netz = "Netze: \n";
	    @SuppressWarnings("rawtypes")
		List db = wifi.getScanResults();
	    
	    
	    @SuppressWarnings("rawtypes")
		Iterator it = db.iterator();
	    List<ScanResult> sortList = new ArrayList<ScanResult>();
	    
	    MacAdressen m = new MacAdressen();
    	//int level;
    	//String mac;
	    while(it.hasNext()) {
	    	ScanResult scan = (ScanResult) it.next();
	    	// OpenNetV3, 3. Etage
	    	if(scan.SSID.startsWith("OpenNetV3") && m.controlling(scan.BSSID)) {
	    		System.out.println("WLan von Raum " + m.getRaum(scan.BSSID));
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
	   
	   
	   // Klasse Position für Besonderheiten bestimmter Bereiche z.B. (TH1 z.B.)
	   Positionen p = new Positionen();
	  
	   // TestView
	   System.out.println(netz + "\nMeine Position x: " + Math.round(location[0]) + " y: " + Math.round(location[1]) 
			   				+"\nSpaceLocation: " +  p.getPosition(location[0], location[1]) + "\n\n"); 
		
	   if (act instanceof NavSicht) {
		   System.out.println("NavSicht Standort wird ermittelt");
			String position = "Haus Beuth\n\n" +
								"3. Etage\n\n" +
								p.getPosition(location[0], location[1]);
		
			((NavSicht) act).standort.setText(position);
	   }
		/*
		if (act instanceof GastNavSicht) {
			
		}*/
		if (act instanceof CamNavSicht) { 
			System.out.println("CamNavSicht Standort und Ziel wird ermittelt");
			
			
	   		// errechnen des neuen Vektors
	   		nVector = new double[2];
	   		nVector[0] = (location[0] - ((CamNavSicht) act).getRaumPosition()[0]);
	   		nVector[1] = (location[1] - ((CamNavSicht) act).getRaumPosition()[1]);
	      
	   		// Abstand zwischen meiner Position bis zum Zielpunkt
	   		//long abstand =  Math.round(Math.sqrt(nVector[0]*nVector[0] + nVector[1]*nVector[1]));
	   
		    //Pfeil
	  		double hypotenuse = Math.sqrt(nVector[0]*nVector[0] + (((CamNavSicht) act).getRaumPosition()[1]-location[1])*(((CamNavSicht) act).getRaumPosition()[1]-location[1]));
	  		double ankathete =  (location[0] - ((CamNavSicht) act).getRaumPosition()[0]);
			double cosa = ankathete / hypotenuse;
	 		gradUG = Math.toDegrees(Math.acos(cosa));
			}
		} catch (RuntimeException e) {
				System.out.println(e);
			}
	}
	
	public void updateData(float position) {
		System.out.println("Grad unbehandelt: " + gradUG);  
	    double grad = (gradUG + position);
	    System.out.println("Postion: " + position);
	    System.out.println("Endgrad: " + grad);
	    
	   // je nach Gradzahl wird ein anderer Pfeil als image gesetzt
	  		if(grad > 0 && grad < 10) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_0);}
	  		if(grad > 10 && grad < 20) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_10);}
	  		if(grad > 20 && grad < 30) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_20);}
	  		if(grad >= 30 && grad < 40) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_30);}
	  		if(grad >= 40 && grad < 50) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_40);}
	  		if(grad >= 50 && grad < 60) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_50);}
	  		if(grad >= 60 && grad < 70) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_60);}
	  		if(grad >= 70 && grad < 80) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_70);}
	  		if(grad >= 80 && grad < 90) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_80);}
	  		if(grad >= 90 && grad < 100) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_90);}
	  		if(grad >= 100 && grad < 110) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_100);}
	  		if(grad >= 110 && grad < 120) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_110);}
	  		if(grad >= 120 && grad < 130) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_120);}
	  		if(grad >= 130 && grad < 140) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_130);}
	  		if(grad >= 140 && grad < 150) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_140);}
	  		if(grad >= 150 && grad < 160) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_150);}
	  		if(grad >= 160 && grad < 170) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_160);}
	  		if(grad >= 170 && grad < 180) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_170);}
	  		if(grad >= 180 && grad < 190) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_180);}
	  		if(grad >= 190 && grad < 200) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_190);}
	  		if(grad >= 200 && grad < 210) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_200);}
	  		if(grad >= 210 && grad < 220) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_210);}
	  		if(grad >= 220 && grad < 230) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_220);}
	  		if(grad >= 230 && grad < 240) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_230);}
	  		if(grad >= 240 && grad < 250) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_240);}
	  		if(grad >= 250 && grad < 260) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_250);}
	  		if(grad >= 260 && grad < 270) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_260);}
	  		if(grad >= 270 && grad < 280) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_270);}
	  		if(grad >= 280 && grad < 290) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_280);}
	  		if(grad >= 290 && grad < 300) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_290);}
	  		if(grad >= 300 && grad < 310) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_300);}
	  		if(grad >= 310 && grad < 320) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_310);}
	  		if(grad >= 320 && grad < 330) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_320);}
	  		if(grad >= 330 && grad < 340) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_330);}
	  		if(grad >= 340 && grad < 350) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_340);}
	  		if(grad >= 350 && grad < 360) {((CamNavSicht) act).pfeil.setBackgroundResource(R.drawable.pfeil_350);}
	 	}  
}
