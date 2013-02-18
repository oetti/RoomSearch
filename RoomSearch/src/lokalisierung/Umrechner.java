package lokalisierung;

public class Umrechner {

	public static double[] umrechnen (double x, double y) {
		double[] newKordinaten = new double[2];
		//System.out.println(x + ":" + y);
		if(x < 6 && x > 11 || y < 160 && y > 0) {
			//System.out.println("geht");
			if(y == 0) {
				newKordinaten[0] = 175;
				newKordinaten[1] = 375;
			}
			
			if(y <= 10 && y > 0) {
				newKordinaten[0] = 168.7;
				newKordinaten[1] = 380.3;
			}
			
			if(y <= 20 && y > 10) {
				newKordinaten[0] = 162.4;
				newKordinaten[1] = 385.6;
			}
			
			if(y <= 30 && y > 20) {
				newKordinaten[0] = 156.1;
				newKordinaten[1] = 390.9;
			}
			
			if(y <= 40 && y > 30) {
				newKordinaten[0] = 149.8;
				newKordinaten[1] = 396.2;
			}
			
			if(y <= 50 && y > 40) {
				newKordinaten[0] = 143.5;
				newKordinaten[1] = 401.5;
			}
			
			if(y <= 60 && y > 50) {
				newKordinaten[0] = 137.2;
				newKordinaten[1] = 406.8;
			}
			//
			if(y <= 70 && y > 60) {
				newKordinaten[0] = 130.9;
				newKordinaten[1] = 412.1;
			}
			
			if(y <= 80 && y > 70) {
				newKordinaten[0] = 124.6;
				newKordinaten[1] = 417.4;
			}
			
			if(y <= 90 && y > 80) {
				newKordinaten[0] = 118.3;
				newKordinaten[1] = 422.7;
			}
			
			if(y <= 100 && y > 90) {
				newKordinaten[0] = 112;
				newKordinaten[1] = 428;
			}
			
			if(y <= 110 && y > 100) {
				newKordinaten[0] = 105.7;
				newKordinaten[1] = 433.3;
			}
			
			if(y <= 120 && y > 110) {
				newKordinaten[0] = 99.4;
				newKordinaten[1] = 438.6;
			}
			//
			if(y <= 130 && y > 120) {
				newKordinaten[0] = 93.1;
				newKordinaten[1] = 443.9;
			}
			
			if(y <= 140 && y > 130) {
				newKordinaten[0] = 86.8;
				newKordinaten[1] = 449.2;
			}
			
			if(y <= 150 && y > 140) {
				newKordinaten[0] = 80.5;
				newKordinaten[1] = 454.5;
			}
			
			if(y <= 160 && y > 150) {
				newKordinaten[0] = 75;
				newKordinaten[1] = 460;
			}
		}
		//System.out.println(newKordinaten[0] + "-" +	newKordinaten[1]);
		return newKordinaten;
	}
}
