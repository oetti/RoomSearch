package Datenbank;

import java.util.ArrayList;
import java.util.HashMap;

public class Hausverwaltung {
	@SuppressWarnings("rawtypes")
	private ArrayList[] beuth;
	public Hausverwaltung() {
		fill();
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
}
