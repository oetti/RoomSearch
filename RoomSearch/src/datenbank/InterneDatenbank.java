package datenbank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.roomsearch.R;

import sqlite.MySQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class InterneDatenbank {
	  private SQLiteDatabase database;
	  private ArrayList<HashMap<String,String>>rolelist;
	  private String role ="";
	  private MySQLiteHelper dbHelper;
	  private String[] allNutzerdatenColumns = { MySQLiteHelper.COLUMN_IDNUTZER,
			  MySQLiteHelper.COLUMN_BENUTZERNAME, MySQLiteHelper.COLUMN_ROLLE,
			  MySQLiteHelper.COLUMN_VORNAME, MySQLiteHelper.COLUMN_NACHNAME,
			  MySQLiteHelper.COLUMN_EMAIL,MySQLiteHelper.COLUMN_PASSWORT};
	  
	  private String[] allWochentagColumns = { MySQLiteHelper.COLUMN_IDWOCHENTAG,
			  MySQLiteHelper.COLUMN_WOCHENTAG};
	  
	  private String[] allPlanColumns = { MySQLiteHelper.COLUMN_IDPLAN,
			  MySQLiteHelper.COLUMN_KENNZEICHNUNG, MySQLiteHelper.COLUMN_FKBENUTZERNAME};
	  
	  private String[] allVorlesungColumns = { MySQLiteHelper.COLUMN_IDVORLESUNG,
			  MySQLiteHelper.COLUMN_VORLESUNGSNAME, MySQLiteHelper.COLUMN_RAUM,
			  MySQLiteHelper.COLUMN_HAUS, MySQLiteHelper.COLUMN_BLOCK,
			  MySQLiteHelper.COLUMN_FKPLAN, MySQLiteHelper.COLUMN_FKTAG};
	  
	  private String[] allBeschreibungColumns = { MySQLiteHelper.COLUMN_IDBESCHREIBUNG,
			  MySQLiteHelper.COLUMN_INFO, MySQLiteHelper.COLUMN_FKVORLESUNG};
	  
	  private String[] allDetailColumns = { MySQLiteHelper.COLUMN_IDDETAIL,
			  MySQLiteHelper.COLUMN_DETAILNAME, MySQLiteHelper.COLUMN_FKVORLESUNG2};

	  public InterneDatenbank(Context context) {
	    dbHelper = new MySQLiteHelper(context);
	    rolelist = setRoleList(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }
	  /*
	  public void fillWochentag() {
		  ContentValues werte = new ContentValues();
		  String[] wochentag = {"Montag","Dienstag","Mittwoch","Donnerstag","Freitag"};
		  open();
		  for(int i = 0; i < wochentag.length; i++) {
			  werte.put(MySQLiteHelper.COLUMN_WOCHENTAG, wochentag[i]);
			  long insertId = database.insert(MySQLiteHelper.TABLE_WOCHENTAG, null,
						 werte);
				 Cursor cursor = database.query(MySQLiteHelper.TABLE_WOCHENTAG,
						 allWochentagColumns, MySQLiteHelper.COLUMN_IDWOCHENTAG + " = " + insertId, null,
						 null, null, null);
				 cursor.close();
		  }
		  close();
	  }*/
	  
	  public int getBlock(int vorlesungID) {
		  String sql = "SELECT "+MySQLiteHelper.COLUMN_BLOCK
	    			+" FROM "+MySQLiteHelper.TABLE_VORLESUNG
	    			+" WHERE "+MySQLiteHelper.COLUMN_IDVORLESUNG+" = "+vorlesungID+";";
	  Cursor vIDCursor = database.rawQuery(sql, null);
	  int block=0;
	  vIDCursor.moveToFirst();
		while (!vIDCursor.isAfterLast()) {
			block = vIDCursor.getInt(0);
			vIDCursor.moveToNext();
		}
		vIDCursor.close();
	  return block;
	  }
	  
	  /**
	   * In dieser Methode werden die Wochentage aus internen Datenbank abgefragt und zurückgegeben.
	   * 
	   * @return List<String> wochentage
	   */
	  public List<String> gibWoche () {
		    List<String> daten = new ArrayList<String>();
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_WOCHENTAG,
		    		allWochentagColumns, null, null, null, null, MySQLiteHelper.COLUMN_IDWOCHENTAG);
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      daten.add(cursor.getString(1));
		      cursor.moveToNext();
		    }
		    cursor.close();
		    return daten;
	  }
	  
	  /**
	   * In dieser Methode werden die IDs der Vorlesungen aus der internen Datenbank abgefragt und zurückgegeben.
	   * Für die Relation werden die Fremdschlüssel übergeben und nach dem Block geordnet.
	   * 
	   * @param FKPlan
	   * @param FKTag
	   * @return ArrayList<Integer> IDs der Vorlesungen
	   */
	  public ArrayList<Integer> gibVorlesungIDs (int FKPlan, int FKTag) {
		  ArrayList<Integer> vorlesungsID = new ArrayList<Integer>();
		// SELECT vorlesungsname FROM vorlesung WHERE fkPlan = int AND fkTag = int ORDER BY block;
		    String sql = "SELECT "+MySQLiteHelper.COLUMN_IDVORLESUNG+", "+MySQLiteHelper.COLUMN_HAUS+", "+MySQLiteHelper.COLUMN_RAUM
		    			+" FROM "+MySQLiteHelper.TABLE_VORLESUNG
		    			+" WHERE "+MySQLiteHelper.COLUMN_FKPLAN+" = "+FKPlan
		    			+" AND "+MySQLiteHelper.COLUMN_FKTAG+" = "+FKTag
		    			+" ORDER BY "+MySQLiteHelper.COLUMN_BLOCK+";";
		  Cursor vIDCursor = database.rawQuery(sql, null);
		  vIDCursor.moveToFirst();
			while (!vIDCursor.isAfterLast()) {
				vorlesungsID.add(vIDCursor.getInt(0));
				vIDCursor.moveToNext();
			}
			vIDCursor.close();
		  return vorlesungsID;
	  }
	  
	  /**
	   * In dieser Methode werden die Beschreibung zu den dazugehörigen Vorlesung wiedergegeben.
	   * 
	   * @param FKvorlesung
	   * @return String Beschreibung
	   */
	  public String getBeschreibung(int FKvorlesung) {
		    String sql = "SELECT "+MySQLiteHelper.COLUMN_INFO
		    			+" FROM "+MySQLiteHelper.TABLE_BESCHREIBUNG
		    			+" WHERE "+MySQLiteHelper.COLUMN_FKVORLESUNG+" = "+FKvorlesung;
		    
		    Cursor cursor = database.rawQuery(sql, null);
		    cursor.moveToFirst();
		    String result = "";
		    while (!cursor.isAfterLast()) {
		      result = cursor.getString(0);
		      cursor.moveToNext();
		    }
		    cursor.close();
		    return result;
	  }
	  
	  /**
	   * In dieser Methode wird zu dem Benutzer die Id des Vorlesungsplanes zurückgegeben.
	   * 
	   * @param username
	   * @return Integer
	   */
	  public int getPlanID(String username) {
		  Cursor planCursor = database.query(MySQLiteHelper.TABLE_PLAN,
					allPlanColumns, null, null, null, null, null);
		  planCursor.moveToFirst();
		  int planID = 0;
			while (!planCursor.isAfterLast()) {
				planID = getID(planCursor, username);
				planCursor.moveToNext();
			}
			planCursor.close();
			return planID;
	  }
	  
	  /**
	   * In dieser Methode wird kontrolliert ob der Nutzer bereits einen Vorlesungsplan besitzt.
	   * 
	   * 
	   * @param username
	   * @return Boolean
	   */
	  public boolean controlPlan(String username) {
		  boolean result = false;
		  Cursor planCursor = database.query(MySQLiteHelper.TABLE_PLAN,
					allPlanColumns, null, null, null, null, null);
		  planCursor.moveToFirst();
		  int planID = 0;
			while (!planCursor.isAfterLast()) {
				planID = getID(planCursor, username);
				planCursor.moveToNext();
			}
			if(planID==0) {
				result = true;
			}
			planCursor.close();
			return result;
	  }
	  
	  /**
	   * In dieser Methode wird ein Plan für den Nutzer in der Datenbank angelegt.
	   * 
	   * @param username
	   */
	  public void createPlan(String username) {
			Cursor nutzerCursor = database.query(MySQLiteHelper.TABLE_NUTZERDATEN,
			allNutzerdatenColumns, null, null, null, null, null);
			nutzerCursor.moveToFirst();
			int nutzerID = 0;
			while (!nutzerCursor.isAfterLast()) {
				nutzerID = getID(nutzerCursor, username);
				nutzerCursor.moveToNext();
			}
			nutzerCursor.close();
			// Vorlesungsplan anlegen
			ContentValues werte = new ContentValues();
			 werte.put(MySQLiteHelper.COLUMN_KENNZEICHNUNG, username);
			 werte.put(MySQLiteHelper.COLUMN_FKBENUTZERNAME, nutzerID);
			 long insertId = database.insert(MySQLiteHelper.TABLE_PLAN, null,
					 werte);
			 Cursor cursor = database.query(MySQLiteHelper.TABLE_PLAN,
					 allPlanColumns, MySQLiteHelper.COLUMN_IDPLAN + " = " + insertId, null,
					 null, null, null);
			 cursor.close();
	  }
	  
	  /**
	   * In dieser Methode wird das Profil für den Nutzer in der Datenbank angelegt.
	   * 
	   * @param daten
	   */
	  public void createProfil(String[] daten) {
			 ContentValues werte = new ContentValues();
			 werte.put(MySQLiteHelper.COLUMN_BENUTZERNAME, daten[0]);
			 werte.put(MySQLiteHelper.COLUMN_ROLLE, daten[1]);
			 werte.put(MySQLiteHelper.COLUMN_VORNAME, daten[2]);
			 werte.put(MySQLiteHelper.COLUMN_NACHNAME, daten[3]);
			 werte.put(MySQLiteHelper.COLUMN_EMAIL, daten[4]);
			 werte.put(MySQLiteHelper.COLUMN_PASSWORT, daten[5]);
			 long insertId = database.insert(MySQLiteHelper.TABLE_NUTZERDATEN, null,
					 werte);
			 Cursor cursor = database.query(MySQLiteHelper.TABLE_NUTZERDATEN,
					 allNutzerdatenColumns, MySQLiteHelper.COLUMN_IDNUTZER + " = " + insertId, null,
					 null, null, null);
			 cursor.close();
	  }
	  
	  /**
	   * In dieser Methode wird die Vorlesung sowie die dazu gehörige Beschreibung
	   * in die Interne Datenbank geschrieben.
	   * 
	   * @param daten
	   */
	  public void createVorlesung(Object[] daten, Boolean nonFreeBlock) {
		//Vorlesung
		ContentValues vorlesungsWerte = new ContentValues();
		vorlesungsWerte.put(MySQLiteHelper.COLUMN_VORLESUNGSNAME, (String) daten[0]);
		vorlesungsWerte.put(MySQLiteHelper.COLUMN_RAUM, (String) daten[1]);
		vorlesungsWerte.put(MySQLiteHelper.COLUMN_HAUS, (String) daten[2]);
		vorlesungsWerte.put(MySQLiteHelper.COLUMN_BLOCK, (Integer) daten[3]);
		vorlesungsWerte.put(MySQLiteHelper.COLUMN_FKPLAN, (Integer)daten[4]);
		vorlesungsWerte.put(MySQLiteHelper.COLUMN_FKTAG, (Integer)daten[5]);
		long insertVorlesungId = database.insert(MySQLiteHelper.TABLE_VORLESUNG, null,
			 vorlesungsWerte);
		Cursor vorlesungCursor = database.query(MySQLiteHelper.TABLE_VORLESUNG,
			 allVorlesungColumns, MySQLiteHelper.COLUMN_IDVORLESUNG + " = " + insertVorlesungId, null,
			 null, null, null);
		vorlesungCursor.close();
		// Beschreibung	 
		if(nonFreeBlock) {
			ContentValues beschreibungsWerte = new ContentValues();
			beschreibungsWerte.put(MySQLiteHelper.COLUMN_INFO, (String) daten[6]);
			beschreibungsWerte.put(MySQLiteHelper.COLUMN_FKVORLESUNG, insertVorlesungId);
			long insertBeschreibungId = database.insert(MySQLiteHelper.TABLE_BESCHREIBUNG, null,
			beschreibungsWerte);
			Cursor beschreibungCursor = database.query(MySQLiteHelper.TABLE_BESCHREIBUNG,
				 allBeschreibungColumns, MySQLiteHelper.COLUMN_IDBESCHREIBUNG + " = " + insertBeschreibungId, null,
				 null, null, null);
			beschreibungCursor.close();
		}
	  }
	  
	  /**
	   * In dieser Methode wird die Detailbeschreibung für die Vorlesung in der Datenbank angelegt.
	   * 
	   * @param daten
	   */
	  public void createDetail(Object[] daten) {
	  }
	  
	  
	  public boolean datenKontrolle (String[] eingabe) {
		  boolean result = false;
		  for(String daten : gibNutzerdaten()) {
			  // eingabe [ Benutzername, Passwort]
			  // splitDaten [Benutzername, Passwort, Rolle, Vorname, Nachname, Email]
			  String[] splitDaten = daten.split("-");
			  if(splitDaten[0].equals(eingabe[0]) && splitDaten[1].equals(eingabe[1])) {
				  setRole(splitDaten[1]);
				  result = true;
			  }
		  }
		  return result;
	  }
	  
	  public List<String> gibAllBenutzerdaten(String[] eingabe) {
		  List<String> benutzerdaten = new ArrayList<String>();
		  for(String daten : gibNutzerdaten()) {
			  String[] splitDaten = daten.split("-");
			  if(splitDaten[0].equals(eingabe[0]) && splitDaten[1].equals(eingabe[1])) {
				  for(int i = 0; i < splitDaten.length; i++) {
					  benutzerdaten.add(splitDaten[i]);
				  }
			  }
		  }
		  return benutzerdaten;
	  }
	  
	  private int getID(Cursor cursor, String name) {
		  int id = 0;
		  if(cursor.getString(1).equals(name)) {
			id = cursor.getInt(0);  
		  }
		  return id;
	  }
	  
	  public List<String> gibNutzerdaten () {
			    List<String> daten = new ArrayList<String>();
			    Cursor cursor = database.query(MySQLiteHelper.TABLE_NUTZERDATEN,
			    		allNutzerdatenColumns, null, null, null, null, null);
			    cursor.moveToFirst();
			    while (!cursor.isAfterLast()) {
			      String[] benutzerdaten = gibDaten(cursor);
			      daten.add(benutzerdaten[0]+"-"+benutzerdaten[1]+"-"+benutzerdaten[2]+"-"+
			      benutzerdaten[3]+"-"+benutzerdaten[4]+"-"+benutzerdaten[5]);
			      cursor.moveToNext();
			    }
			    cursor.close();
			    return daten;
	  }
	  
	  private String[] gibDaten(Cursor cursor) {
		  // daten [Benutzername, Passwort, Rolle, Vorname, Nachname, Email]
		  String[] daten = {cursor.getString(1),cursor.getString(6),cursor.getString(2), cursor.getString(3),
				  cursor.getString(4),cursor.getString(5)};
		  return daten;
	  }
	  
	  public List<String> gibSelectedVorlesungen(int FKPlan, int FKTag) {
		    List<String> daten = new ArrayList<String>();
		    // SELECT vorlesungsname FROM vorlesung WHERE fkPlan = int AND fkTag = int ORDER BY block;
		    String sql = "SELECT "+MySQLiteHelper.COLUMN_VORLESUNGSNAME+", "+MySQLiteHelper.COLUMN_HAUS+", "+MySQLiteHelper.COLUMN_RAUM
		    			+" FROM "+MySQLiteHelper.TABLE_VORLESUNG
		    			+" WHERE "+MySQLiteHelper.COLUMN_FKPLAN+" = "+FKPlan
		    			+" AND "+MySQLiteHelper.COLUMN_FKTAG+" = "+FKTag
		    			+" ORDER BY "+MySQLiteHelper.COLUMN_BLOCK+";";
		    
		    Cursor cursor = database.rawQuery(sql, null);
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	String[]ergebnis = gibVorlesungName(cursor);
		    	String result = ergebnis[1].substring(0,1)+ " " + ergebnis[2] + " "+ ergebnis[0];
		    	daten.add(result);
		      
		      cursor.moveToNext();
		    }
		    cursor.close();
		    return daten;
	  }
	  
	  private String[] gibVorlesungName(Cursor cursor) {
		  String[] daten = {cursor.getString(0),cursor.getString(1),cursor.getString(2)};
		  return daten;
	  }
	  
	  private void setRole(String role) {
		  for(int i = 0; i < rolelist.size(); i++) {
			  if(rolelist.get(i).containsKey(role)) {
				  this.role = rolelist.get(i).get(role);
			  }
		  }
	  }
	  
	  public String getRole() {
		  return this.role;
	  }
	  
	  private ArrayList<HashMap<String,String>> setRoleList (Context context) {
		  HashMap<String,String> studentrole = new HashMap<String, String>();
		  HashMap<String,String> dozentrole = new HashMap<String, String>();
		  HashMap<String,String> gastrole = new HashMap<String, String>();
		  studentrole.put(context.getString(R.string.student), context.getString(R.string.studentenbereich));
		  dozentrole.put(context.getString(R.string.dozent), context.getString(R.string.dozentenbereich));
		  gastrole.put(context.getString(R.string.gast), context.getString(R.string.gastbereich));
		  ArrayList<HashMap<String,String>> liste = new ArrayList<HashMap<String,String>>();
		  liste.add(studentrole);
		  liste.add(dozentrole);
		  liste.add(gastrole);
		  return liste;
	  }
	  
	  // Ab hier alle Methoden zum lesen der Datenbank
	  public List<String> gibWochentage() {
		    List<String> daten = new ArrayList<String>();
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_WOCHENTAG,
		    		allWochentagColumns, null, null, null, null, null);
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      String[] benutzerdaten = gibWochenDaten(cursor);
		      daten.add(benutzerdaten[0]);
		      cursor.moveToNext();
		    }
		    cursor.close();
		    return daten;
	  }
	  
	  private String[] gibWochenDaten(Cursor cursor) {
		  String[] daten = {cursor.getString(1)};
		  return daten;
	  }
	  
	  public List<String> gibVorlesungen() {
		    List<String> daten = new ArrayList<String>();
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_VORLESUNG,
		    		allVorlesungColumns, null, null, null, null, null);
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Object[] benutzerdaten = gibVorlesungDaten(cursor);
		      daten.add("ID: "+ benutzerdaten[0]+"-Name: "+benutzerdaten[1]+"-Raum: "+benutzerdaten[2]+"-Block: "+benutzerdaten[3]);
		      cursor.moveToNext();
		    }
		    cursor.close();
		    return daten;
	  }
	  
	  public List<String> gibBeschreibung() {
		    List<String> daten = new ArrayList<String>();
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_BESCHREIBUNG,
		    		allBeschreibungColumns, null, null, null, null, null);
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      daten.add("Info: "+ cursor.getString(1)+"-VorlesungFK Id: "+cursor.getString(2));
		      cursor.moveToNext();
		    }
		    cursor.close();
		    return daten;
	  }
	  
	  private Object[] gibVorlesungDaten(Cursor cursor) {
		  Object[] daten = {cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(4)};
		  return daten;
	  }
}
