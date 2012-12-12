package Datenbank;

import java.util.ArrayList;

public class Stundenplan {
	private ArrayList<String> mo = new ArrayList<String>();
	private ArrayList<String> di = new ArrayList<String>();
	private ArrayList<String> mi = new ArrayList<String>();
	private ArrayList<String> don = new ArrayList<String>();
	private ArrayList<String> fr = new ArrayList<String>();
	private ArrayList<String> haus = new ArrayList<String>();
	
	
	public void erstellenSp(String[] daten) {
		// Tag, Raumnummer, Vorlesung, Uhrzeit
		
		if(daten[0].equals("mo")) {
			String vorlesung = daten[1] + " " + daten[2];
			mo.add(vorlesung);
		}
		
		if(daten[0].equals("mo")) {
			String vorlesung = daten[1] + " " + daten[2];
			mo.add(vorlesung);
		}
		
		if(daten[0].equals("mo")) {
			String vorlesung = daten[1] + " " + daten[2];
			mo.add(vorlesung);
		}
	}
}
