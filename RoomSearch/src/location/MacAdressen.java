package location;

import java.util.HashMap;

public class MacAdressen {
	HashMap<String, double[]> macAdressen;
	HashMap<String, String> raumID;
	public MacAdressen() {
		macAdressen = fuellen();
		raumID = raumIdfuellen();
	}
	
	private HashMap<String, double[]> fuellen() {
				HashMap<String, double[]> gaussRouter = new HashMap<String, double[]>();
				gaussRouter.put("a4:56:30:a2:3f:70", getAPPosition(301));
				gaussRouter.put("a4:56:30:8f:1a:d0", getAPPosition(302));
				gaussRouter.put("a4:56:30:a2:20:80", getAPPosition(303));
				gaussRouter.put("a4:56:30:8e:ff:d0", getAPPosition(305));
				gaussRouter.put("a4:56:30:8f:00:00", getAPPosition(307));
				gaussRouter.put("a4:56:30:5c:b7:d0", getAPPosition(314));
				gaussRouter.put("a4:56:30:8f:0a:b0", getAPPosition(321));
				gaussRouter.put("a4:56:30:8f:0b:00", getAPPosition(322));
				gaussRouter.put("a4:56:30:8f:0a:c0", getAPPosition(323));
				gaussRouter.put("a4:56:30:8f:05:70", getAPPosition(325));
				gaussRouter.put("a4:56:30:8f:25:e0", getAPPosition(332));
				gaussRouter.put("a4:56:30:8e:fd:30", getAPPosition(341));
				gaussRouter.put("a4:56:30:46:57:30", getAPPosition(342));
				gaussRouter.put("a4:56:30:a2:49:c0", getAPPosition(343));
				gaussRouter.put("a4:56:30:8f:06:80", getAPPosition(345));
				gaussRouter.put("a4:56:30:a2:3b:40", getAPPosition(349));
				gaussRouter.put("a4:56:30:a2:39:d0", getAPPosition(350));
				gaussRouter.put("a4:56:30:a2:e2:00", getAPPosition(352));
				return gaussRouter;
	}
	
	private HashMap<String, String> raumIdfuellen() {
		HashMap<String, String> gaussRouter = new HashMap<String, String>();
		gaussRouter.put("a4:56:30:a2:3f:70", "301");
		gaussRouter.put("a4:56:30:8f:1a:d0", "302");
		gaussRouter.put("a4:56:30:a2:20:80", "303");
		gaussRouter.put("a4:56:30:8e:ff:d0", "305");
		gaussRouter.put("a4:56:30:8f:00:00", "307");
		gaussRouter.put("a4:56:30:5c:b7:d0", "314");
		gaussRouter.put("a4:56:30:8f:0a:b0", "321");
		gaussRouter.put("a4:56:30:8f:0b:00", "322");
		gaussRouter.put("a4:56:30:8f:0a:c0", "323");
		gaussRouter.put("a4:56:30:8f:05:70", "325");
		gaussRouter.put("a4:56:30:8f:25:e0", "332");
		gaussRouter.put("a4:56:30:8e:fd:30", "341");
		gaussRouter.put("a4:56:30:46:57:30", "342a");
		gaussRouter.put("a4:56:30:a2:49:c0", "343");
		gaussRouter.put("a4:56:30:8f:06:80", "345");
		gaussRouter.put("a4:56:30:a2:3b:40", "349");
		gaussRouter.put("a4:56:30:a2:39:d0", "350");
		gaussRouter.put("a4:56:30:a2:e2:00", "352");
		return gaussRouter;
	}
	public String getRaum(String mac) {
		String raum = raumID.get(mac);
		return raum;
	}
	
	
	public boolean controlling (String mac) {
		boolean fazit = macAdressen.containsKey(mac);
		return fazit;
	}
	
	public double[] getAPPPosition (String mac) {
		if(macAdressen.containsKey(mac)) {
			return macAdressen.get(mac);
		} else {
			double[] falsch = {0,0};
			System.out.println("gibt es nicht");
			return  falsch;
		}
	}
	
	/*
	 * gibt die Positionen der Access Points des jeweiligen raum zurück
	 */
	private double[] getAPPosition (int raum) {
		int nummer = raum;
		double[] r = new double[2];
		
		switch(nummer) {
		case 301 : r[0] = 6; r[1]= 10; break;
		case 302 : r[0] = 13; r[1]= 18; break;
		case 303 : r[0] = 3; r[1]= 22; break;
		case 305 : r[0] = 4; r[1]= 39; break;
		case 307 : r[0] = 3; r[1]= 50; break;
		case 314 : r[0] = 12; r[1]= 48; break;
		case 321 : r[0] = 5; r[1]= 72; break;
		case 322 : r[0] = 12; r[1]= 63; break;
		case 323 : r[0] = 5; r[1]= 89; break;
		case 325 : r[0] = 5; r[1]= 104; break;
		case 332 : r[0] = 12; r[1]= 91; break;
		case 341 : r[0] = 3; r[1]= 123; break;
		case 342 : r[0] = 14; r[1]= 127; break;
		case 343 : r[0] = 5; r[1]= 133; break;
		case 345 : r[0] = 5; r[1]= 143; break;
		case 349 : r[0] = 4; r[1]= 154; break;
		case 350 : r[0] = 12; r[1]= 139; break;
		case 352 : r[0] = 14; r[1]= 151; break;
		}
		
		return r;
	}
}
