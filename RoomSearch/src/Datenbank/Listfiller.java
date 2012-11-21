package Datenbank;

import java.util.ArrayList;

public class Listfiller {
	private ArrayList<String> mo = new ArrayList<String>();
	private ArrayList<String> di = new ArrayList<String>();
	private ArrayList<String> mi = new ArrayList<String>();
	private ArrayList<String> don = new ArrayList<String>();
	private ArrayList<String> fr = new ArrayList<String>();
	private ArrayList<String> haus = new ArrayList<String>();
	
	public Listfiller() {
		fuellen();
	}
	
	private void fuellen() {
		// Stundenplan
		mo.add("B 323 Computergrafik II");
		mo.add("B 542 Mobiles Web");
		mo.add("B 343 Computergrafik II �bg.");
		
		di.add("D 114 Algorithmen �bg.");
		di.add("B 323 Algorithmen");
		di.add("B 507 Grundlagen wissen. Arbeitens");
		
		mi.add("D 114 Human Computer Interaction �bg.");
		mi.add("B 325 Human Computer Interaction");
		
		don.add("B 341 Medienprojekt I");
		
		fr.add("D E16a L Software-Engineering II �bg.");
		fr.add("D 209 Software-Engineering II");
		
		// Geb�ude
		haus.add("Beuth (A)");
		haus.add("Gau� (B)");
		haus.add("Grashof (C)");
		haus.add("Bauwesen (D)");
	}
	
	public ArrayList<String> montag() {
		return this.mo;
	}
	
	public ArrayList<String> dienstag() {
		return this.di;
	}

	public ArrayList<String> mittwoch() {
		return this.mi;
	}
	
	public ArrayList<String> donnerstag() {
		return this.don;
	}

	public ArrayList<String> freitag() {
		return this.fr;
	}
	
	public ArrayList<String> haus() {
		return this.haus;
	}
}