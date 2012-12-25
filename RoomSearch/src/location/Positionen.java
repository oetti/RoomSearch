package location;

import java.util.ArrayList;

public class Positionen {
	ArrayList<Object[]> raeume = new ArrayList<Object[]>();  
	
	public Positionen () {
		raumPositionen();
	}
	
	private void raumPositionen() {
		/*
		 * Haus Gauß
		 */
		// {Raumanfang X, Raumende X, Raumanfang Y, Raumende Y, "Raumbezeichnung"}
		Object[] flur3 = 	{5.9, 11.1, 11.9, 147.1, "Flur"};
		raeume.add(flur3);
		Object[] th1_3 = 	{10.0, 18.0, 23.0, 32.0, "Treppenhaus 1"};
		raeume.add(th1_3);
		Object[] th2_3 = 	{10.0, 18.0, 75.0, 85.0, "Treppenhaus 2"};
		raeume.add(th2_3);
		Object[] th3_3 = 	{10.0, 18.0, 128.0, 138.0, "Treppenhaus 3"};
		raeume.add(th3_3);
		Object[] b301 = 	{0.0, 11.0, 0.0, 12.0, "Raum 301"};
		raeume.add(b301);
		Object[] b302 = 	{11.0, 17.0, 0.0, 18.0, "Raum 302"};
		raeume.add(b302);
		Object[] b303 = 	{0.0, 6.0, 12.0, 28.0, "Raum 303"};
		raeume.add(b303);
		Object[] b305 = 	{0.0, 7.0, 26.0, 40.0, "Raum 305"};
		raeume.add(b305);
		Object[] b307 = 	{0.0, 7.0, 38.0, 51.0, "Raum 307"};
		raeume.add(b307);
		Object[] b314 = 	{10.0, 18.0, 45.0, 58.0, "Raum 314"};
		raeume.add(b314);
		Object[] b321 = 	{0.0, 7.0, 56.0, 73.0, "Raum 321"};
		raeume.add(b321);
		Object[] b322 = 	{10.0, 18.0, 60.0, 77.0, "Raum 322"};
		raeume.add(b322);
		Object[] b323 = 	{0.0, 7.0, 71.0, 90.0,  "Raum 323"};
		raeume.add(b323);
		Object[] b325 = 	{0.0, 7.0, 88.0, 105.0, "Raum 325"};
		raeume.add(b325);
		Object[] flur3_1 = 	{0.0, 7.0, 104.0, 111.0, "Flur"};
		raeume.add(flur3_1);
		Object[] b332 = 	{10.0, 18.0, 88.0, 103.0, "Raum 332"};
		raeume.add(b332);
		Object[] b340 = 	{10.0, 18.0, 103.0, 111.0, "Raum 340"};
		raeume.add(b340);
		Object[] b341 = 	{0.0, 6.0, 109.0, 124.0, "Raum 341"};
		raeume.add(b341);
		Object[] b342 = 	{10.0, 18.0, 109.0, 124.0, "Raum 342"};
		raeume.add(b342);
		Object[] b341a = 	{0.0, 7.0, 122.0, 128.0, "Raum 341a"};
		raeume.add(b341a);
		Object[] b342a = 	{11.0, 18.0, 124.0, 130.0, "Raum 342a"};
		raeume.add(b342a);
		Object[] b343 = 	{0.0,  7.0,  126.0,  134.0, "Raum 343"};
		raeume.add(b343);
		Object[] b345 = 	{0.0,  7.0,  132.0,  144.0, "Raum 345"};
		raeume.add(b345);
		Object[] b347 = 	{0.0,  7.0,  142.0,  148.0, "Raum 347"};
		raeume.add(b347);
		Object[] b350 = 	{10.0,  18.0,  136.0, 148.0, "Raum 350"};
		raeume.add(b350);
		Object[] b352 = 	{5.0,  18.0,  146.0,  160.0, "Raum 352"};
		raeume.add(b352);
		
	}
	
	public String getPosition(double x, double y) {
		String position = "nicht im Messbereich";
		
		for(Object[] raum : raeume) {
			
			if(raum[0] instanceof Double && raum[1] instanceof Double && raum[2] instanceof Double && raum[3] instanceof Double
					&& raum[4] instanceof String) {
				//System.out.println( (Double)raum[0]+"-"+(Double)raum[1]+"-"+(Double)raum[2]+"-"+(Double)raum[3]+"-"+(String)raum[4]);
				if(x > (Double)raum[0] && x < (Double)raum[1] && y > (Double)raum[2] && y < (Double)raum[3]) {
					position = (String)raum[4];
				}
			}
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
