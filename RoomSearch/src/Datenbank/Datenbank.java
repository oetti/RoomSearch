package Datenbank;

public class Datenbank {
	private String vorlesung;
	//protected static String haus;
	//protected static String th;
	//protected static String etage;
	
	
	public Datenbank(String vorlesung) {
		this.vorlesung = vorlesung;
		//this.haus = haus;
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
	
	public String getVorlesung() {
		return this.vorlesung;
	}
}
