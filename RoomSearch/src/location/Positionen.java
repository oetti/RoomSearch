package location;

public class Positionen {

	
	public String getPosition(double x, double y) {
		String position = "nicht im Messbereich";
		// Flur
		if(x > 5.9 && x < 11.1 && y > 11.9 && y < 147.1) {
			position = "Flur";
		}
		// TH1
		if(x > 10 && x < 18 && y > 23 && y < 32) {
			position = "Treppenhaus 1";
		}
		// TH2
		if(x > 10 && x < 18 && y > 75 && y < 85) {
			position = "Treppenhaus 2";
		}
		// TH3
		if(x > 10 && x < 18 && y > 128 && y < 138) {
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
	
		if(x > 0 && x < 7 && y > 26 && y < 40) {
			position = "Raum 305";
		}
		
		if(x > 0 && x < 7 && y > 38 && y < 51) {
			position = "Raum 307";
		}
	
		if(x > 10 && x < 18 && y > 45 && y < 58) {
			position = "Raum 314";
		}
		
		if(x > 0 && x < 7 && y > 56 && y < 73) {
			position = "Raum 321";
		}
	
		if(x > 10 && x < 18 && y > 60 && y < 77) {
			position = "Raum 322";
		}
		
		if(x > 0 && x < 7 && y > 71 && y < 90) {
			position = "Raum 323";
		}
	
		if(x > 0 && x < 7 && y > 88 && y < 105) {
			position = "Raum 325";
		}
		
		if(x > 0 && x < 7 && y > 104 && y < 111) {
			position = "Flur";
		}
		
		if(x > 10 && x < 18 && y > 88 && y < 103) {
			position = "Raum 332";
		}
	
		if(x > 10 && x < 18 && y > 103 && y < 111) {
			position = "Raum 340";
		}
		
		if(x > 0 && x < 6 && y > 109 && y < 124) {
			position = "Raum 341";
		}
	
		if(x > 10 && x < 18 && y > 109 && y < 124) {
			position = "Raum 342";
		}
		
		if(x > 0 && x < 7 && y > 122 && y < 128) {
			position = "Raum 341a";
		}
		// überprüfen
		if(x > 11 && x < 18 && y > 124 && y < 130) {
			position = "Raum 342a";
		}
		
		if(x > 0 && x < 7 && y > 126 && y < 134) {
			position = "Raum 343";
		}
	
		if(x > 0 && x < 7 && y > 132 && y < 144) {
			position = "Raum 345";
		}
		
		if(x > 0 && x < 7 && y > 142 && y < 148) {
			position = "Raum 347";
		}
	
		if(x > 10 && x < 18 && y > 136 && y < 148) {
			position = "Raum 350";
		}
		
		if(x > 5 && x < 18 && y > 146 && y < 160) {
			position = "Raum 352";
		}
	
		return position;
	}
	
	public String getEtage(double z) {
		String position = "nicht im Messbereich";
		// 2.Etage
		if(z > 8.8 && z < 13) {
			position = "2. Etage";
		}
		// 3.Etage
		if(z > 13 && z < 17) {
			position = "3. Etage";
		}
		// 4.Etage
		if(z > 17 && z < 21) {
			position = "4. Etage";
		}
		
		
		return position;
	}
}
