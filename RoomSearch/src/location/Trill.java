package location;

public class Trill {
	
	public Trill() {
	}

	public double[] rechnen(double[] wlan1, double[] wlan2, double[] wlan3) {
		// Wlan kreis 1
		double a1 = wlan1[0];
		double b1 = wlan1[1];
		double r1 = wlan1[2];
		// Wlan kreis 2
		double a2 = wlan2[0];
		double b2 = wlan2[1];
		double r2 = wlan2[2];
		
		double a3;
		double b3;
		double r3;
		if (wlan3 != null) {
			// Wlan kreis 3
		a3 = wlan3[0];
		b3 = wlan3[1];
		r3 = wlan3[2];
		} else {
			a3 = 0;
			b3 = 0;
			r3 = 0;
			System.out.println("Trill: Es gibt keinen dritten Kreis");
		}
		
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
		//double x = (Math.sqrt(Math.abs((zaehler2) / (beherrscher2))))/2;
		
		double [] location = {x,y};
		return location;
	}
}
