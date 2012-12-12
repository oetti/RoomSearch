package Datenbank;

import java.util.ArrayList;
import java.util.HashMap;

public class Nutzer {
	private String name;
	private int identität;
	private static HashMap<String,ArrayList<String>> nutzer;
	
	public Nutzer() {
		if(nutzer == null) {
			nutzer = new HashMap<String, ArrayList<String>>();
		}
		fill();
	}
	
	public void setProfil(String[] daten) {
		//  alias; passwort; vorname; nachname; D,S,M;
		String id = daten[0];
		
		ArrayList<String> benutzerDaten = new ArrayList<String>();
		benutzerDaten.add(daten[1]);
		benutzerDaten.add(daten[2]);
		benutzerDaten.add(daten[3]);
		benutzerDaten.add(daten[4]);
		nutzer.put(id, benutzerDaten);
		
	}
	public boolean check(String[] daten) {
		return ch(daten);
	}
	
	private boolean ch(String[] daten) {
		boolean status = false;
		try {
			String nutzerArt = nutzer.get(daten[0]).get(3);
			
			
				String control = nutzer.get(daten[0]).get(0);
				if(control.equals(daten[1])) {
					setName(nutzer.get(daten[0]).get(1));
					if(nutzerArt.equals("d")) {
						setIden(0);
					}
					if(nutzerArt.startsWith("s")) {
						setIden(1);
					}
					status = true;
				}
		} catch (Exception e){
			
		}
		return status;
	}
	
	public int getIdentität() {
		return this.identität;
	}
	
	public String getName() {
		return this.name;
	}
	
	private void setIden(int id) {
		this.identität = id;
	}
	private void setName(String name) {
		this.name = name;
	}
	
	public void editProfil(String[] daten) {
		
	}
	
	private void fill() {
		String[] test = {"s", "a", "Max", "Mustermann", "s"};
		setProfil(test);
	}
}
