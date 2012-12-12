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
				// 2.Etage
				gaussRouter.put("a4:56:30:a2:33:b0", getAPPosition(207));
				gaussRouter.put("a4:56:30:8f:02:00", getAPPosition(215));
				gaussRouter.put("a4:56:30:8f:0b:50", getAPPosition(220));
				gaussRouter.put("a4:56:30:a2:f2:f0", getAPPosition(250));
				gaussRouter.put("a4:56:30:a2:21:e0", getAPPosition(218));
				gaussRouter.put("a4:56:30:a2:33:f0", getAPPosition(208));
				gaussRouter.put("a4:56:30:47:de:f0", getAPPosition(223));
				gaussRouter.put("a4:56:30:8f:12:40", getAPPosition(253));
				gaussRouter.put("a4:56:30:8f:06:60", getAPPosition(230));
				gaussRouter.put("a4:56:30:a2:22:60", getAPPosition(212));
				gaussRouter.put("a4:56:30:5d:2a:40", getAPPosition(221));
				gaussRouter.put("a4:56:30:a2:3e:40", getAPPosition(249));
				gaussRouter.put("a4:56:30:a2:2f:d0", getAPPosition(254));
				gaussRouter.put("a4:56:30:8f:09:20", getAPPosition(243));
				gaussRouter.put("a4:56:30:a2:20:40", getAPPosition(242));
				gaussRouter.put("a4:56:30:8f:18:20", getAPPosition(234));
				gaussRouter.put("a4:56:30:5d:50:80", getAPPosition(201));
				gaussRouter.put("a4:56:30:8e:e9:e0", getAPPosition(257));
				gaussRouter.put("a4:56:30:a2:3d:60", getAPPosition(213));
				gaussRouter.put("a4:56:30:8f:0b:50", getAPPosition(202));
				gaussRouter.put("a4:56:30:a2:31:b0", getAPPosition(225));
				
				// 3.Etage
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
				
				// 4.Etage
				gaussRouter.put("a4:56:30:8f:0a:f0", getAPPosition(421));
				gaussRouter.put("a4:56:30:47:87:50", getAPPosition(445));
				gaussRouter.put("a4:56:30:8f:1a:a0", getAPPosition(452));
				gaussRouter.put("a4:56:30:a2:25:30", getAPPosition(425));
				gaussRouter.put("a4:56:30:8f:1f:40", getAPPosition(451));
				gaussRouter.put("a4:56:30:8f:02:c0", getAPPosition(420));
				gaussRouter.put("a4:56:30:8f:08:80", getAPPosition(441));
				gaussRouter.put("a4:56:30:8f:16:b0", getAPPosition(423));
				gaussRouter.put("a4:56:30:8f:16:d0", getAPPosition(432));
				gaussRouter.put("a4:56:30:a2:2f:50", getAPPosition(442));
				return gaussRouter;
	}
	
	private HashMap<String, String> raumIdfuellen() {
		HashMap<String, String> gaussRouter = new HashMap<String, String>();
		// 2.Etage
		gaussRouter.put("a4:56:30:a2:33:b0", "207");
		gaussRouter.put("a4:56:30:8f:02:00", "215");
		gaussRouter.put("a4:56:30:8f:0b:50", "220");
		gaussRouter.put("a4:56:30:a2:f2:f0", "250");
		gaussRouter.put("a4:56:30:a2:21:e0", "218");
		gaussRouter.put("a4:56:30:a2:33:f0", "208");
		gaussRouter.put("a4:56:30:47:de:f0", "223");
		gaussRouter.put("a4:56:30:8f:12:40", "253");
		gaussRouter.put("a4:56:30:8f:06:60", "230a");
		gaussRouter.put("a4:56:30:a2:22:60", "212");
		gaussRouter.put("a4:56:30:5d:2a:40", "221");
		gaussRouter.put("a4:56:30:a2:3e:40", "249");
		gaussRouter.put("a4:56:30:a2:2f:d0", "254");
		gaussRouter.put("a4:56:30:8f:09:20", "243");
		gaussRouter.put("a4:56:30:a2:20:40", "242");
		gaussRouter.put("a4:56:30:8f:18:20", "234");
		gaussRouter.put("a4:56:30:5d:50:80", "201");
		gaussRouter.put("a4:56:30:8e:e9:e0", "257");
		gaussRouter.put("a4:56:30:a2:3d:60", "213");
		gaussRouter.put("a4:56:30:8f:0b:50", "202");
		gaussRouter.put("a4:56:30:a2:31:b0", "225");
		
		// 3.Etage
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
		
		// 4.Etage
		gaussRouter.put("a4:56:30:8f:0a:f0", "421");
		gaussRouter.put("a4:56:30:47:87:50", "445");
		gaussRouter.put("a4:56:30:8f:1a:a0", "452");
		gaussRouter.put("a4:56:30:a2:25:30", "425");
		gaussRouter.put("a4:56:30:8f:1f:40", "451");
		gaussRouter.put("a4:56:30:8f:02:c0", "420");
		gaussRouter.put("a4:56:30:8f:08:80", "441");
		gaussRouter.put("a4:56:30:8f:16:b0", "423");
		gaussRouter.put("a4:56:30:8f:16:d0", "432");
		gaussRouter.put("a4:56:30:a2:2f:50", "442");
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
		double[] r = new double[3];
		
		switch(nummer) {
		// 2.Etage
		// noch nicht eindeutig
		case 207 : r[0] = 3; r[1]= 15; r[2]= 12.10; break;
		case 215 : r[0] = 1; r[1]= 41; r[2]= 12.10; break;
		case 220 : r[0] = 12; r[1]= 63; r[2]= 12.10; break;
		case 250 : r[0] = 13; r[1]= 141; r[2]= 12.10; break;
		// noch nicht eindeutig
		case 218 : r[0] = 13; r[1]= 51; r[2]= 12.10; break;
		// noch nicht eindeutig
		case 208 : r[0] = 14; r[1]= 21; r[2]= 12.10; break;
		case 223 : r[0] = 5; r[1]= 83; r[2]= 12.10; break;
		case 253 : r[0] = 3; r[1]= 136; r[2]= 12.10; break;
		case 230 : r[0] = 14; r[1]= 93; r[2]= 12.10; break;
		case 212 : r[0] = 14; r[1]= 40; r[2]= 12.10; break;
		case 221 : r[0] = 5; r[1]= 66; r[2]= 12.10; break;
		case 249 : r[0] = 3; r[1]= 128; r[2]= 12.10; break;
		case 254 : r[0] = 8; r[1]= 148; r[2]= 12.10; break;
		case 243 : r[0] = 2; r[1]= 117; r[2]= 12.10; break;
		case 242 : r[0] = 13; r[1]= 119.5; r[2]= 12.10; break;
		case 234 : r[0] = 14; r[1]= 102; r[2]= 12.10; break;
		// noch nicht eindeutig
		case 201 : r[0] = 9; r[1]= 3; r[2]= 12.10; break;
		case 257 : r[0] = 5.5; r[1]= 147; r[2]= 12.10; break;
		case 213 : r[0] = 2.5; r[1]= 29; r[2]= 12.10; break;
		// noch nicht eindeutig
		case 202 : r[0] = 14; r[1]= 7; r[2]= 12.10; break;
		// noch nicht eindeutig
		case 225 : r[0] = 5; r[1]= 83; r[2]= 12.10; break;
		
		// 3.Etage
		case 301 : r[0] = 6; r[1]= 10; r[2]= 16.5; break;
		case 302 : r[0] = 13; r[1]= 18; r[2]= 16.5; break;
		case 303 : r[0] = 3; r[1]= 22; r[2]= 16.5; break;
		case 305 : r[0] = 4; r[1]= 38; r[2]= 16.5; break;
		case 307 : r[0] = 3; r[1]= 49; r[2]= 16.5; break;
		case 314 : r[0] = 12; r[1]= 47; r[2]= 16.5; break;
		case 321 : r[0] = 5; r[1]= 71; r[2]= 16.5; break;
		case 322 : r[0] = 12; r[1]= 62; r[2]= 16.5; break;
		case 323 : r[0] = 5; r[1]= 88; r[2]= 16.5; break;
		case 325 : r[0] = 5; r[1]= 103; r[2]= 16.5; break;
		case 332 : r[0] = 12; r[1]= 90; r[2]= 16.5; break;
		case 341 : r[0] = 3; r[1]= 122; r[2]= 16.5; break;
		case 342 : r[0] = 14; r[1]= 126; r[2]= 16.5; break;
		case 343 : r[0] = 5; r[1]= 132; r[2]= 16.5; break;
		case 345 : r[0] = 5; r[1]= 142; r[2]= 16.5; break;
		case 349 : r[0] = 4; r[1]= 153; r[2]= 16.5; break;
		case 350 : r[0] = 12; r[1]= 138; r[2]= 16.5; break;
		case 352 : r[0] = 14; r[1]= 150; r[2]= 16.5; break;
		
		// 4.Etage
		// noch nicht eindeutig
		case 421 : r[0] = 3; r[1]= 69; r[2]= 20.9; break;
		// noch nicht eindeutig
		case 445 : r[0] = 3; r[1]= 143; r[2]= 20.9; break;
		// noch nicht eindeutig
		case 452 : r[0] = 14; r[1]= 143; r[2]= 20.9; break;
		// noch nicht eindeutig
		case 425 : r[0] = 5; r[1]= 104; r[2]= 20.9; break;
		// noch nicht eindeutig
		case 451 : r[0] = 2; r[1]= 152; r[2]= 20.9; break;
		// noch nicht eindeutig
		case 420 : r[0] = 14; r[1]= 64; r[2]= 20.9; break;
		case 441 : r[0] = 3; r[1]= 120; r[2]= 20.9; break;
		case 423 : r[0] = 3; r[1]= 86; r[2]= 20.9; break;
		case 432 : r[0] = 14; r[1]= 94; r[2]= 20.9; break;
		// noch nicht eindeutig
		case 442 : r[0] = 14; r[1]= 111; r[2]= 20.9; break;
		}
		
		return r;
	}
}
