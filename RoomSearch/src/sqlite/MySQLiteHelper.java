package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper	extends SQLiteOpenHelper {
		  // Tabelleinhalt nutzerdaten
		  public static final String TABLE_NUTZERDATEN = "nutzerdaten";
		  public static final String COLUMN_IDNUTZER = "_id";
		  public static final String COLUMN_BENUTZERNAME = "benutzername";
		  public static final String COLUMN_VORNAME = "vorname";
		  public static final String COLUMN_NACHNAME = "nachname";
		  public static final String COLUMN_EMAIL = "email";
		  public static final String COLUMN_PASSWORT = "passwort";
		  public static final String COLUMN_ROLLE = "rolle";
		  // Tabelleninhalt wochentag
		  public static final String TABLE_WOCHENTAG = "wochentagliste";
		  public static final String COLUMN_IDWOCHENTAG = "_id";
		  public static final String COLUMN_WOCHENTAG = "wochentag";
		  // Tabelleninhalt Vorlesungsplan
		  public static final String TABLE_PLAN = "plan";
		  public static final String COLUMN_IDPLAN = "_id";
		  public static final String COLUMN_KENNZEICHNUNG = "kennzeichnung";
		  public static final String COLUMN_FKBENUTZERNAME = "fkuser";
		  // Tabelleninhalt Vorlesung
		  public static final String TABLE_VORLESUNG = "vorlesung";
		  public static final String COLUMN_IDVORLESUNG = "_id";
		  public static final String COLUMN_VORLESUNGSNAME = "vorlesungsname";
		  public static final String COLUMN_RAUM = "raum";
		  public static final String COLUMN_HAUS = "haus";
		  public static final String COLUMN_BLOCK = "block";
		  public static final String COLUMN_FKPLAN = "fkplan";
		  public static final String COLUMN_FKTAG = "fktag";
		  // Tabelleninhalt Beschreibung
		  public static final String TABLE_BESCHREIBUNG = "beschreibung";
		  public static final String COLUMN_IDBESCHREIBUNG = "_id";
		  public static final String COLUMN_INFO = "info";
		  public static final String COLUMN_FKVORLESUNG = "fkvorlesung";
		// Tabelleninhalt Detail
		  public static final String TABLE_DETAIL = "detail";
		  public static final String COLUMN_IDDETAIL = "_id";
		  public static final String COLUMN_DETAILNAME = "detailname";
		  public static final String COLUMN_FKVORLESUNG2 = "fkvorlesung";
		  // Datenbank
		  private static final String DATABASE_NAME = "test1.db";
		  private static final int DATABASE_VERSION = 1;
		  // Database creation sql statement
		  private static final String CREATE_TABLE_NUTZER = createTableNutzerdatenbank();
		  private static final String CREATE_TABLE_WOCHENTAG = createTableWochentag();
		  private static final String CREATE_TABLE_PLAN = createTablePlan();
		  private static final String CREATE_TABLE_VORLESUNG = createTableVorlesung();
		  private static final String CREATE_TABLE_BESCHREIBUNG = createTableBeschreibung();
		  private static final String CREATE_TABLE_DETAIL = createTableDetail();
		  
		  private static String createTableDetail() {
			  String table = "create table "
				      + TABLE_DETAIL + "(" 
				      + COLUMN_IDDETAIL + " integer primary key autoincrement, " 
				      + COLUMN_DETAILNAME + " text not null, "
				      + COLUMN_FKVORLESUNG2 + " integer not null, "
				      + "FOREIGN KEY (" + COLUMN_FKVORLESUNG2 + ") REFERENCES "
				      + TABLE_PLAN + "(" + COLUMN_IDVORLESUNG + ")"
				      + ");";
			return table;
		  }
		  
		  private static String createTableBeschreibung() {
			  String table = "create table "
				      + TABLE_BESCHREIBUNG + "(" 
				      + COLUMN_IDBESCHREIBUNG + " integer primary key autoincrement, " 
				      + COLUMN_INFO + " text not null, "
				      + COLUMN_FKVORLESUNG + " integer not null, "
				      + "FOREIGN KEY (" + COLUMN_FKVORLESUNG + ") REFERENCES "
				      + TABLE_PLAN + "(" + COLUMN_IDVORLESUNG + ")"
				      + ");";
			return table;
		  }
		  
		  private static String createTableVorlesung() {
			  String table = "create table "
				      + TABLE_VORLESUNG + "(" 
				      + COLUMN_IDVORLESUNG + " integer primary key autoincrement, " 
				      + COLUMN_VORLESUNGSNAME + " text not null, "
				      + COLUMN_RAUM + " text not null, "
				      + COLUMN_HAUS + " text not null, "
				      + COLUMN_BLOCK + " integer not null, "
				      + COLUMN_FKPLAN + " integer not null, "
				      + COLUMN_FKTAG + " integer not null, "
				      + "FOREIGN KEY (" + COLUMN_FKPLAN +") REFERENCES "
				      + TABLE_PLAN + "(" + COLUMN_IDPLAN + "), "
				      + "FOREIGN KEY (" + COLUMN_FKTAG + ") REFERENCES "
				      + TABLE_PLAN + "(" + COLUMN_IDWOCHENTAG + ")"
				      + ");";
			return table;
		  }
		  
		  private static String createTablePlan() {
			  String table = "create table "
					  + TABLE_PLAN + "(" 
				      + COLUMN_IDPLAN + " integer primary key autoincrement, " 
				      + COLUMN_KENNZEICHNUNG + " text not null, " 
				      + COLUMN_FKBENUTZERNAME + " integer not null, "
				      + "FOREIGN KEY (" + COLUMN_FKBENUTZERNAME +") REFERENCES "
				      +TABLE_NUTZERDATEN+"("+COLUMN_IDNUTZER+")"
				      + ");";
					  return table;
		  }
		  
		  private static String createTableWochentag() {
			  String table = "create table "
			  + TABLE_WOCHENTAG + "(" 
		      + COLUMN_IDWOCHENTAG + " integer primary key autoincrement, " 
		      + COLUMN_WOCHENTAG + " text not null );";
			  return table;
		  }
		
		  private static String createTableNutzerdatenbank() {
			String table = "create table "
				      + TABLE_NUTZERDATEN + "(" 
				      + COLUMN_IDNUTZER + " integer primary key autoincrement, " 
				      + COLUMN_BENUTZERNAME + " text not null, "
				      + COLUMN_ROLLE + " text not null, "
				      + COLUMN_VORNAME + " text not null, "
				      + COLUMN_NACHNAME + " text not null, "
				      + COLUMN_EMAIL + " text not null, "
				      + COLUMN_PASSWORT + " text not null );"; 
			return table;
		}
		
		public MySQLiteHelper(Context context) {
		    super(context, DATABASE_NAME, null, DATABASE_VERSION);
		  }

		@Override
		public void onCreate(SQLiteDatabase database) {
			  database.execSQL(CREATE_TABLE_NUTZER);
			  database.execSQL(CREATE_TABLE_WOCHENTAG);
			  database.execSQL(CREATE_TABLE_PLAN);
			  database.execSQL(CREATE_TABLE_VORLESUNG);
			  database.execSQL(CREATE_TABLE_BESCHREIBUNG);
			  database.execSQL(CREATE_TABLE_DETAIL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(MySQLiteHelper.class.getName(),
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NUTZERDATEN);
			    onCreate(db);
		}
}
