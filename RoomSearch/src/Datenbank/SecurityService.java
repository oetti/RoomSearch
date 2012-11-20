package Datenbank;

import java.util.HashMap;

public class SecurityService {
	private HashMap<String, String[]> dozenten;
	private HashMap<String, String[]> studenten;
	private String name;
	private int identität;
	
	public SecurityService(){
		fill();
	}
	
	public boolean check(String[] daten) {
		return ch(daten);
	}
	
	private boolean ch(String[] daten) {
		boolean status = false;
		try {
			if(daten[0].startsWith("d")) {
				String[] control = dozenten.get(daten[0]);
				if(control[0].equals(daten[1])) {
					setName(control[1]);
					setIden(0);
					status = true;
				}
			}
			
			if(daten[0].startsWith("s")) {
				String[] control = studenten.get(daten[0]);
				if(control[0].equals(daten[1])) {
					setName(control[1]);
					setIden(1);
					status = true;
				}
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
	
	private void fill() {
		dozenten = new HashMap<String, String[]>();
		String[] d4444 = {"1234", "Marcel", "Buchmann"};
		dozenten.put("d4444", d4444);
	
		studenten = new HashMap<String, String[]>();
		String[] s44755 = {"1234", "Andreas", "Oettinger"};
		String[] test = {"a", "Max", "Mustermann"};
		studenten.put("s44755", s44755);
		studenten.put("s" , test);
	}
}
