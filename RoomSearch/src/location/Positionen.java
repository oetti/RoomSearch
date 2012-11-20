package location;

public class Positionen {

	
	public String getPosition(double x, double y) {
		String position = "nicht im Messbereich";
		// Flur
		if(x > 6 && x < 11 && y > 12 && y < 149) {
			position = "Flur 3.Etage";
		}
		// TH1
		if(x > 11 && x < 17 && y > 23 && y < 32) {
			position = "Treppenhaus 1";
		}
		// TH2
		if(x > 11 && x < 17 && y > 77 && y < 84) {
			position = "Treppenhaus 2";
		}
		// TH3
		if(x > 11 && x < 17 && y > 130 && y < 138) {
			position = "Treppenhaus 3";
		}
		// Raum 301
		if(x > 0 && x < 11 && y > 0 && y < 12) {
			position = "Raum 301";
		}
		// Raum 302
		if(x > 11 && x < 17 && y > 0 && y < 18) {
			position = "Raum 302";
		}
	
		if(x > 0 && x < 6 && y > 12 && y < 28) {
			position = "Raum 303";
		}
	
		if(x > 0 && x < 6 && y > 28 && y < 40) {
			position = "Raum 305";
		}
		
		if(x > 0 && x < 6 && y > 40 && y < 51) {
			position = "Raum 307";
		}
	
		if(x > 11 && x < 17 && y > 47 && y < 58) {
			position = "Raum 314";
		}
		
		if(x > 0 && x < 6 && y > 58 && y < 73) {
			position = "Raum 321";
		}
	
		if(x > 11 && x < 17 && y > 62 && y < 77) {
			position = "Raum 322";
		}
		
		if(x > 0 && x < 6 && y > 73 && y < 90) {
			position = "Raum 323";
		}
	
		if(x > 0 && x < 6 && y > 90 && y < 105) {
			position = "Raum 325";
		}
		
		if(x > 0 && x < 6 && y > 105 && y < 111) {
			position = "Flur 3.Etage";
		}
		
		if(x > 11 && x < 17 && y > 90 && y < 103) {
			position = "Raum 332";
		}
	
		if(x > 11 && x < 17 && y > 103 && y < 111) {
			position = "Raum 340";
		}
		
		if(x > 0 && x < 6 && y > 112 && y < 124) {
			position = "Raum 341";
		}
	
		if(x > 11 && x < 17 && y > 112 && y < 124) {
			position = "Raum 342";
		}
		
		if(x > 0 && x < 6 && y > 124 && y < 128) {
			position = "Raum 341a";
		}
	
		if(x > 11 && x < 17 && y > 124 && y < 130) {
			position = "Raum 342a";
		}
		
		if(x > 0 && x < 6 && y > 128 && y < 134) {
			position = "Raum 343";
		}
	
		if(x > 0 && x < 6 && y > 134 && y < 144) {
			position = "Raum 345";
		}
		
		if(x > 0 && x < 6 && y > 144 && y < 148) {
			position = "Raum 347";
		}
	
		if(x > 11 && x < 17 && y > 138 && y < 148) {
			position = "Raum 350";
		}
		
		if(x > 6 && x < 17 && y > 148 && y < 158) {
			position = "Raum 343";
		}
	
		return position;
	}
}
