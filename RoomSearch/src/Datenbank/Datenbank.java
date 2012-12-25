package Datenbank;

import java.util.ArrayList;

public class Datenbank {
	private String vorlesung;
	//protected static String haus;
	//protected static String th;
	//protected static String etage;
	private Hausverwaltung hausverwaltung;
	private Listfiller filler;
	//private Nutzer nutzer;
	
	
	public Datenbank() {
		hausverwaltung = new Hausverwaltung();
		filler = new Listfiller();
		//this.th = th;
		//this.etage = etage;
	}
	/*
	public String suchRaum () {
		String text = "";
		if(haus.equals("Gauﬂ (B)")) {
			gaus = new Gauﬂ();
			text = gaus.e()+ gaus.richtung();
		}
		return text;
	}*/
	
	public ArrayList<String> liste (String option) {
		ArrayList<String> liste = filler.montag();
		
		if(option.equals("di")) {
			liste = filler.dienstag();
		}
		
		if(option.equals("mi")) {
			liste = filler.mittwoch();
		}
		
		if(option.equals("do")) {
			liste = filler.donnerstag();
		}
		
		if(option.equals("fr")) {
			liste = filler.freitag();
		}
		
		if(option.equals("zeit")) {
			liste = filler.zeit();
		}
		
		return liste;
	}
	
	public boolean checkHaus (String nummer, String haus) {
		boolean antwort = hausverwaltung.hausCheck(nummer, haus);
		return antwort;
	}
	
	public void setVorlesung(String vorlesung) {
		this.vorlesung = vorlesung;
	}
	
	public String getVorlesung() {
		return this.vorlesung;
	}
}
