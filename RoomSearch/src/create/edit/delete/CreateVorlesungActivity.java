package create.edit.delete;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.roomsearch.R;
import datenbank.InterneDatenbank;
import extras.ActivityRegistry;

public class CreateVorlesungActivity extends Activity implements OnClickListener, OnItemSelectedListener {
	private String[] nutzerdaten;
	private EditText name, number, beschreibung;
	private Spinner hausauswahl, zeitauswahl;
	private ArrayAdapter<String> hausadapter, zeitadapter;
	private ArrayList<Integer> idListe;
	private Button erstellen;
	private int tagID;
	private InterneDatenbank data;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tagID = getIntent().getIntExtra("TAGID", 0);
        nutzerdaten = getIntent().getStringArrayExtra("DATEN");
        idListe = getIntent().getIntegerArrayListExtra("VORLESUNGIDS");
        setContentView(R.layout.vorlesung_erstellen);
        ActivityRegistry.register(this);
        data = new InterneDatenbank(this);
		data.open();
        // Spinner Hausauswahl und Zeitraum
        hausauswahl = (Spinner)findViewById(R.id.spinner_vor_haus);
        String[] gebaeude = {getString(R.string.haus_a), getString(R.string.haus_b), getString(R.string.haus_c), getString(R.string.haus_d)};
        hausadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gebaeude);
        hausauswahl.setAdapter(hausadapter);
        zeitauswahl = (Spinner)findViewById(R.id.spinner_zeitraum);
        String[] zeit = {getString(R.string.time_1), getString(R.string.time_2), getString(R.string.time_3),
        		getString(R.string.time_4), getString(R.string.time_5), getString(R.string.time_6), getString(R.string.time_7)};
        zeitadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, zeit);
        zeitauswahl.setAdapter(zeitadapter);
        zeitauswahl.setOnItemSelectedListener(this);
        // Button
        erstellen = (Button)findViewById(R.id.button_vorlesung_erstellen);
        erstellen.setOnClickListener(this);
    }

	public void onClick(View arg0) {
		/*Validierung fehlt*/
		if(controlZeitraum(zeitauswahl, zeitauswahl.getSelectedItemPosition())) {
			name = (EditText)findViewById(R.id.editText_vorlesung_name);
	        number = (EditText)findViewById(R.id.editText_vor_num);
	        beschreibung = (EditText)findViewById(R.id.editText_vorlesung_dozent);
	        String beschreibungData = zeitauswahl.getSelectedItem()+"\n\n"
	        		+ beschreibung.getText().toString();
	        // Vorlesungsname, Raum, Haus, Block, FKPlan, FKTag, Beschreibung
	        Object[] daten = {name.getText().toString(), number.getText().toString(),hausauswahl.getSelectedItem().toString(),
	        		(zeitauswahl.getSelectedItemPosition()+1),data.getPlanID(nutzerdaten[0]), tagID, beschreibungData};
	        data.createVorlesung(daten, true);
	        Intent intent = new Intent(CreateVorlesungActivity.this,EditPlanActivity.class);
	        intent.putExtra("DATEN", nutzerdaten);
	        data.close();
	        ActivityRegistry.finishAll();
	        startActivity(intent);
		} else {
			zeitauswahl.setBackgroundColor(Color.WHITE);
		}
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		controlZeitraum(arg1, arg2);
	}

	public void onNothingSelected(AdapterView<?> arg0) {}
	
	private Boolean controlZeitraum(View view, int selection) {
		ArrayList<Integer> blockzeitraum = new ArrayList<Integer>();
		for(int i = 0; i <idListe.size(); i++) {
			int block = data.getBlock(idListe.get(i))-1;
			blockzeitraum.add(block);
		}
		for(int k = 0; k < blockzeitraum.size(); k++) {
			if(selection == (blockzeitraum.get(k))) {
				Toast toast = Toast.makeText(this, R.string.vorlesungszeit_vorhanden, Toast.LENGTH_LONG);
				toast.show();
				view.setBackgroundColor(Color.RED);
				return false;
			}
		}
		return true;
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
