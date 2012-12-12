package location;

import java.util.ArrayList;

public class Trilateration {
	private WifiReceiver wr;
	public Trilateration(WifiReceiver wr) {
		this.wr = wr;
		//System.out.println("WR: " + wr);
	}

	public double[] rechnen(ArrayList<double[]> aPs) {
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
		double r3;
		
		if(wr.isGenerierterAP()) {
			r3 = getdistance(aPs.get(2)[3]) / 2.5;
			System.out.println("Distanz ist generiert");
		} else {
			r3 = getdistance(aPs.get(2)[3]);
		}
		//System.out.println("x3: " + a3);
		//System.out.println("y3: " + b3);
		//System.out.println("r3: " + r3);
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
		/*
		System.out.println("zähler1: " + zaehler1);
		System.out.println("beherrscher1: " + beherrscher1);
		System.out.println("zähler2: " + zaehler2);
		System.out.println("beherrscher2: " + beherrscher2);
		System.out.println("x-Wert: " + (Math.abs(x)));
		System.out.println("y-Wert: " + (Math.abs(y)));
		*/
		double [] location = {Math.abs(x),Math.abs(y)};
		return location;
	}
	
	private double getdistance(double level) {
		
		double meter = (level + 40.0) / -4.5;
		return meter;
		
		//double distance = 730.24198315 + 52.33325511*level + 1.35152407*Math.pow(level, 2) 
		//          + 0.01481265*Math.pow(level, 3) + 0.00005900*Math.pow(level, 4)+0.00541703*180;
		//return distance;
	}
}
