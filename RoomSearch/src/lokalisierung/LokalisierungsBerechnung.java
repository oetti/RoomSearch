package lokalisierung;

import java.util.ArrayList;

public class LokalisierungsBerechnung {
	private static LokalisierungsBerechnung INSTANCE = new LokalisierungsBerechnung();
	
	private LokalisierungsBerechnung () {
		
	}
	
	public static LokalisierungsBerechnung getInstance() {
		return LokalisierungsBerechnung.INSTANCE;
	}

	public double[] rechnenML(ArrayList<double[]> aPs, int length) {
		//if (length == 4) {
			double a1 = aPs.get(0)[0];
			double b1 = aPs.get(0)[1];
			double c1 = aPs.get(0)[2];
			double r1 = getdistance(aPs.get(0)[3]);
			
			// Wlan kreis 2
			double a2 = aPs.get(1)[0];
			double b2 = aPs.get(1)[1];
			double c2 = aPs.get(1)[2];
			double r2 = getdistance(aPs.get(1)[3]);
			
			//Wlan 3
			double a3 = aPs.get(2)[0];
			double b3 = aPs.get(2)[1];
			double c3 = aPs.get(2)[2];
			double r3 = getdistance(aPs.get(2)[3]);
			
			//Wlan 4
			double a4 = aPs.get(3)[0];
			double b4 = aPs.get(3)[1];
			double c4 = aPs.get(3)[2];
			double r4 = getdistance(aPs.get(3)[3]);
			
		
			double x1 = a1 + ((((r1*r1)-(r2*r2))/(2*((r1+r2)*(r1+r2))))+0.5) * (a2-a1);
			double y1 = b1 + ((((r1*r1)-(r2*r2))/(2*((r1+r2)*(r1+r2))))+0.5) * (b2-b1);
			double z1 = c1 + ((((r1*r1)-(r2*r2))/(2*((r1+r2)*(r1+r2))))+0.5) * (c2-c1);
		
			double x2 = a3 + ((((r3*r3)-(r4*r4))/(2*((r3+r4)*(r3+r4))))+0.5) * (a4-a3);
			double y2 = b3 + ((((r3*r3)-(r4*r4))/(2*((r3+r4)*(r3+r4))))+0.5) * (b4-b3);
			double z2 = c3 + ((((r1*r1)-(r2*r2))/(2*((r1+r2)*(r1+r2))))+0.5) * (c4-c3);
		
			double x = (x2+x1)/2;
			double y = (y2+y1)/2;
			double z = (z2+z1)/2;
			
		
			double[] location = {x,y,z};
			return location;
		//}	
	}
	
	public double[] rechnenTL(ArrayList<double[]> aPs) {
		// Wlan kreis 1
		double a1 = aPs.get(0)[0];
		double b1 = aPs.get(0)[1];
		double r1 = getdistance(aPs.get(0)[3]);
		// Wlan kreis 2
		double a2 = aPs.get(1)[0];
		double b2 = aPs.get(1)[1];
		double r2 = getdistance(aPs.get(1)[3]);
		// Wlan kreis 3
		double a3 = aPs.get(2)[0];
		double b3 =	aPs.get(2)[1];
		double r3 = getdistance(aPs.get(2)[3]);
		
		// Quadrat der Variablen
		double a1Sq = a1*a1, a2Sq = a2*a2, a3Sq = a3*a3,
				b1Sq = b1*b1, b2Sq = b2*b2, b3Sq = b3*b3,
				r1Sq = r1*r1, r2Sq = r2*r2, r3Sq = r3*r3;
		
		// Zähler 1
		double zaehler1 = (a2 - a1) * (a3Sq + b3Sq - r3Sq) + (a1 - a3) * (a2Sq +
				b2Sq - r2Sq) + (a3 - a2) * (a1Sq + b1Sq - r1Sq);
		
		// dominator 1
		double beherrscher1 = 2 * (b3 * (a2 - a1) + b2 * (a1 - a3) + b1 * (a3 -a2));
		
		// y - Wert
		double y = zaehler1 / beherrscher1;
		
		// Zähler 2
		double zaehler2 = r2Sq - r1Sq + a1Sq - a2Sq + b1Sq - b2Sq - 2 * (b1 - b2) * y;
		
		// dominator 2
		double beherrscher2 = 2 * (a1 - a2);
		
		// x - Wert
		double x = (zaehler2) / (beherrscher2);
		
		double [] location = {Math.abs(x),Math.abs(y)};
		return location;
	}
	
	private double getdistance(double level) {
		//double meter = (level + 32.0) / -4.5;
		//return meter;
		double distance = 730.24198315 + 52.33325511*level + 1.35152407*Math.pow(level, 2) 
		          + 0.01481265*Math.pow(level, 3) + 0.00005900*Math.pow(level, 4)+0.00541703*180;
		return distance;
	}
}
