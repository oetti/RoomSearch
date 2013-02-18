  package datenbank;

import java.util.ArrayList;
import java.util.HashMap;

public class Hausverwaltung {
	@SuppressWarnings("rawtypes")
	private ArrayList[] beuth;
	private ArrayList<double[]> raeume;
	private static Hausverwaltung INSTANCE = new Hausverwaltung();
	private Hausverwaltung() {
		fill();
		raeume = raumPosition();
	}
	
	public static Hausverwaltung getInstance() {
		return Hausverwaltung.INSTANCE;
	}
	
	
	public void fill() {
		beuth();
	}
	
	public boolean hausCheck(String nummer, String haus) {
		boolean antwort = false;
		
		if (haus.equals("Beuth")) {
			for(int j = 0; j < beuth.length; j++) {
				String index = ""+j;
				if(index.equals(nummer.substring(0, 1))) {
					for(int g = 0; g < beuth[j].size(); g++) {
						if(beuth[j].get(g).equals(nummer)) {
							antwort = true;
						}
					}
				}
			}
		}
		return antwort;
	}
	
	private void beuth() {
		beuth = new ArrayList[6];
		ArrayList<String> eg = new ArrayList<String>();
		ArrayList<String> og1 = new ArrayList<String>();
		ArrayList<String> og2= new ArrayList<String>();
		ArrayList<String> og3 = new ArrayList<String>();
		ArrayList<String> og4 = new ArrayList<String>();
		ArrayList<String> og5 = new ArrayList<String>();
		
		for(int i = 0; i < beuth.length; i++) {
			if(i == 0) {
				for(int k = 0; k < 54; k++) {
					if(k < 9) {
						eg.add("00"+(k+1));
					} else {
						eg.add("0"+(k+1));
					}
				}
				beuth[i] = eg;
			}
			
			if(i == 1) {
				for(int k = 0; k < 56; k++) {
					if(k < 9) {
						og1.add("10"+(k+1));
					} else {
						og1.add("1"+(k+1));
					}
				}
				beuth[i] = og1;
			}

			if(i == 2) {
				for(int k = 0; k < 54; k++) {
					if(k < 9) {
						og2.add("20"+(k+1));
					} else {
						og2.add("2"+(k+1));
					}
				}
				beuth[i] = og2;
			}

			if(i == 3) {
				for(int k = 0; k < 54; k++) {
					if(k < 9) {
						og3.add("30"+(k+1));
					} else {
						og3.add("3"+(k+1));
					}
				}
				beuth[i] = og3;
			}

			if(i == 4) {
				for(int k = 0; k < 54; k++) {
					if(k < 9) {
						og4.add("40"+(k+1));
					} else {
						og4.add("4"+(k+1));
					}
				}
				beuth[i] = og4;
			}

			if(i == 5) {
				for(int k = 0; k < 54; k++) {
					if(k < 9) {
						og5.add("50"+(k+1));
					} else {
						og5.add("5"+(k+1));
					}
				}
				beuth[i] = og5;
			}
		}
	}

	public ArrayList<String> getRaumInfo (String raumnummer, String haus) {
		ArrayList<String> b342a = new ArrayList<String>();
		b342a.add("http://labor.beuth-hochschule.de/cga/laborraeume/holodeck/");
		b342a.add("9:30 - 12:15");
		HashMap<String, ArrayList<String>> gauss = new HashMap<String, ArrayList<String>>();
		gauss.put("342a", b342a);
		
		return gauss.get(raumnummer);
	}
	
	public void raumControl(double x, double y) {
		for(int i = 0; i < raeume.size(); i++) {
			if(x < raeume.get(i)[0]) {
				// weiter machen
			}
		}
	}
	
	private ArrayList<double[]> raumPosition() {
		// raumbereich = {x-Anfang, x-Ende, y-Anfang, y-Ende}
		double[] b301 = {0, 11, 0, 12};
		double[] b302 = {11, 17, 0, 19};
		double[] b303 = {0, 6, 12, 23};
		double[] b304 = {11, 17, 19, 23};
		double[] b303a = {0, 6, 23, 27};
		double[] b305 = {0, 6, 27, 39};
		double[] b307a = {0, 6, 39, 43};
		double[] b307 = {0, 6, 43, 50};
		double[] b312 = {11, 17, 35, 46};
		double[] b314 = {11, 17, 46, 57};
		double[] b320 = {11, 17, 57, 61};
		double[] b321 = {0, 6, 57, 72};
		double[] b322 = {11, 17, 61, 76};
		double[] b323 = {0, 6, 72, 89};
		double[] b325 = {0, 6, 89, 104};
		double[] b330 = {11, 17, 84, 89};
		double[] b332 = {11, 17, 89, 102};
		double[] b332a = {11, 17, 102, 104};
		double[] b340 = {11, 17, 104, 110};
		double[] b342 = {11, 17, 110, 123};
		double[] b341 = {0, 6, 110, 123};
		double[] b341a = {0, 6, 123, 127};
		double[] b343 = {0, 6, 127, 133};
		double[] b345 = {0, 6, 133, 143};
		double[] b347 = {0, 6, 143, 147};
		double[] b350 = {11, 17, 137, 147};
		
		ArrayList<double[]> raumpositionen = new ArrayList<double[]>();
		raumpositionen.add(b301);
		raumpositionen.add(b302);
		raumpositionen.add(b303a);
		raumpositionen.add(b304);
		raumpositionen.add(b303);
		raumpositionen.add(b305);
		raumpositionen.add(b307a);
		raumpositionen.add(b307);
		raumpositionen.add(b312);
		raumpositionen.add(b314);
		raumpositionen.add(b320);
		raumpositionen.add(b321);
		raumpositionen.add(b322);
		raumpositionen.add(b323);
		raumpositionen.add(b325);
		raumpositionen.add(b330);
		raumpositionen.add(b332);
		raumpositionen.add(b332a);
		raumpositionen.add(b340);
		raumpositionen.add(b342);
		raumpositionen.add(b341);
		raumpositionen.add(b341a);
		raumpositionen.add(b343);
		raumpositionen.add(b345);
		raumpositionen.add(b347);
		raumpositionen.add(b350);
		
		return raumpositionen;
	}
}
