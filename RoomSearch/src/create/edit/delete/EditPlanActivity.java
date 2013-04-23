package create.edit.delete;

import java.util.ArrayList;
import com.example.roomsearch.R;
import com.example.roomsearch.Uebersicht;
import datenbank.InterneDatenbank;
import extras.ActivityRegistry;
import extras.DialogBuilder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class EditPlanActivity extends Activity implements OnClickListener, OnItemLongClickListener {
	private String[] daten;
	private TextView wochentag;
	private ViewFlipper vorlesungenTag;
	private ListView mo, di, mi, don, fr;
	private ArrayList<Integer> moID, diID, miID, doID, frID;
	private ArrayAdapter<String> moAdapter, diAdapter, miAdapter, doAdapter, frAdapter;
	private Button vor, back, erstellen, freeBlock;
	private int flipperDisplay;
	private InterneDatenbank data;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Intent intent = getIntent();
	        daten = intent.getStringArrayExtra("DATEN");
	        setContentView(R.layout.stundenplan_bearbeiten);
	        ActivityRegistry.register(this);
	        // Datenbank initieren
	        data = new InterneDatenbank(this);
	        // ViewFlipper
	        vorlesungenTag = (ViewFlipper)findViewById(R.id.viewFlipper_vorlesung_plan);
	        flipperDisplay = vorlesungenTag.getDisplayedChild();
	        // Datenbankverkehr öffnen
	        data.open();
	        // Wochentag
	        wochentag = (TextView)findViewById(R.id.st_wochentag);
	        wochentag.setText(data.gibWoche().get(flipperDisplay));
	        // ListView und Adapter
	        mo = (ListView)findViewById(R.id.listView_vorlesung_mo);
	        di = (ListView)findViewById(R.id.listView_vorlesung_di);
	        mi = (ListView)findViewById(R.id.listView_vorlesung_mi);
	        don = (ListView)findViewById(R.id.listView_vorlesung_do);
	        fr = (ListView)findViewById(R.id.listView_vorlesung_fr);
	        moAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data.gibSelectedVorlesungen(data.getPlanID(daten[0]),1));
	        diAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data.gibSelectedVorlesungen(data.getPlanID(daten[0]),2));
	        miAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data.gibSelectedVorlesungen(data.getPlanID(daten[0]),3));
	        doAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data.gibSelectedVorlesungen(data.getPlanID(daten[0]),4));
	        frAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data.gibSelectedVorlesungen(data.getPlanID(daten[0]),5));
	        mo.setAdapter(moAdapter);
	        di.setAdapter(diAdapter);
	        mi.setAdapter(miAdapter);
	        don.setAdapter(doAdapter);
	        fr.setAdapter(frAdapter);
	        mo.setOnItemLongClickListener(this);
	        di.setOnItemLongClickListener(this);
	        mi.setOnItemLongClickListener(this);
	        don.setOnItemLongClickListener(this);
	        fr.setOnItemLongClickListener(this);
	        // ArrayList für die IDs
	        moID= data.gibVorlesungIDs(data.getPlanID(daten[0]), 1);
	        diID= data.gibVorlesungIDs(data.getPlanID(daten[0]), 2);
	        miID= data.gibVorlesungIDs(data.getPlanID(daten[0]), 3);
	        doID= data.gibVorlesungIDs(data.getPlanID(daten[0]), 4);
	        frID= data.gibVorlesungIDs(data.getPlanID(daten[0]), 5);
	        // Buttons
	        vor = (Button)findViewById(R.id.button_st_vor);
	        vor.setOnClickListener(this);
	        back = (Button)findViewById(R.id.button_st_back);
	        back.setOnClickListener(this);
	        erstellen = (Button)findViewById(R.id.button_vorlesung_hinzu);
	        erstellen.setOnClickListener(this);
	        freeBlock = (Button)findViewById(R.id.button_vorlesung_freiblock);
	        freeBlock.setOnClickListener(this);
	 }

	public void onClick(View v) {
		if(v == freeBlock) {
			if(gibVorlesungTagID(flipperDisplay).size()<=7) {
				Boolean[] besetzt = gibBesetztliste();
				int block = 0;
				for(int i = 0; i < besetzt.length; i++) {
					if(!besetzt[i]) {
						block = i+1;
						break;
					}
				}
				Object[] objectdaten = {getString(R.string.freiblock), "0", "0", block, data.getPlanID(daten[0]), (flipperDisplay+1)};
		        data.createVorlesung(objectdaten, false);
		        Intent intent = getIntent();
		        startActivity(intent);
			} else {
				Toast toast = Toast.makeText(this, R.string.limit, Toast.LENGTH_LONG);
				toast.show();
			}
		}
		if(v == erstellen) {
			if(gibVorlesungTagID(flipperDisplay).size()<=7) {
				Intent intent = new Intent(EditPlanActivity.this, CreateVorlesungActivity.class);
				intent.putExtra("TAGID", (flipperDisplay+1));
				intent.putIntegerArrayListExtra("VORLESUNGIDS", gibVorlesungTagID(this.flipperDisplay));
				intent.putExtra("DATEN", daten);
				startActivity(intent);
			} else {
				Toast toast = Toast.makeText(this, R.string.limit, Toast.LENGTH_LONG);
				toast.show();
			}
		}
		if(v == vor || v == back) {
			if(v == vor) {
				if(flipperDisplay==(vorlesungenTag.getChildCount()-1)){
					flipperDisplay = 0;
					vorlesungenTag.setDisplayedChild(flipperDisplay);
					wochentag.setText(data.gibWoche().get(flipperDisplay));
				} else {
					flipperDisplay=flipperDisplay+1;
					vorlesungenTag.setDisplayedChild(flipperDisplay);
					wochentag.setText(data.gibWoche().get(flipperDisplay));
				}
			}
			if(v == back) {
				if(flipperDisplay==0){
					flipperDisplay = vorlesungenTag.getChildCount()-1;
					vorlesungenTag.setDisplayedChild(flipperDisplay);
					wochentag.setText(data.gibWoche().get(flipperDisplay));
				} else {
					flipperDisplay=flipperDisplay-1;
					vorlesungenTag.setDisplayedChild(flipperDisplay);
					wochentag.setText(data.gibWoche().get(flipperDisplay));
				}
			}
		}
	}

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// dieser Integer wird dann als Fremdschlüssel übergeben
		int vorlesungID = 0;
		// je nach Flipperansicht wird eine andere Liste mit IDs übergeben
		ArrayList<Integer> liste = gibVorlesungTagID(this.flipperDisplay);
		for(int i = 0; i < liste.size(); i++) {
			// Ist der Index der gleiche wie der gedrücke item führe Bedingung aus
			if(i==arg2) {
				// übergebe die VorlesungsID
				vorlesungID = liste.get(i);
			}
		}
		// Öffne einen Dialog mit der Beschreibung
		AlertDialog.Builder dialog = DialogBuilder.getDialog(this, "Beschreibung", data.getBeschreibung(vorlesungID));
		dialog.setNeutralButton("ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
			}});
		dialog.show();
		return false;
	}
	
	private ArrayList<Integer> gibVorlesungTagID (int tagAnsicht) {
		ArrayList<Integer> liste = null;
		// Montag Vorlesungen
		if(tagAnsicht==0) {
			liste = this.moID;
		}
		// Dienstag Vorlesungen
		if(tagAnsicht==1) {
			liste = this.diID;
		}
		// Mittwoch Vorlesungen
		if(tagAnsicht==2) {
			liste = this.miID;
		}
		// Donnerstag Vorlesungen
		if(tagAnsicht==3) {
			liste = this.doID;
		}
		// Freitag Vorlesungen
		if(tagAnsicht==4) {
			liste = this.frID;
		}
		return liste;
	}
	
	private Boolean[] gibBesetztliste() {
		Boolean[] besetzt = {false, false, false, false, false, false, false};
		ArrayList<Integer> idListe = gibVorlesungTagID(flipperDisplay);
		ArrayList<Integer> blockzeitraum = new ArrayList<Integer>();
		for(int i = 0; i <idListe.size(); i++) {
			int block = data.getBlock(idListe.get(i))-1;
			blockzeitraum.add(block);
		}
		for(int k = 0; k < blockzeitraum.size(); k++) {
			besetzt[blockzeitraum.get(k)] = true;
		}
		return besetzt;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		data.close();
		Intent intent = new Intent(EditPlanActivity.this, Uebersicht.class);
		intent.putExtra("DATEN", daten);
		ActivityRegistry.finishAll();
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		data.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		data.close();
		super.onPause();
	}
	
	
}
