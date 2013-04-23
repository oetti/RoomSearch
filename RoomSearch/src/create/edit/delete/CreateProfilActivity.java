package create.edit.delete;

import com.example.roomsearch.LoginSicht;
import com.example.roomsearch.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import datenbank.InterneDatenbank;
import extras.ActivityRegistry;

public class CreateProfilActivity extends Activity implements OnClickListener {
	private EditText benutzername, vorname, nachname, email, pw, pwWdh;
	private Spinner rolle;
	private ArrayAdapter<String> rollenAdapter;
	private Button erstellen;
	private InterneDatenbank nutzerdaten;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_erstellen);
        ActivityRegistry.register(this);
        nutzerdaten = new InterneDatenbank(this);
        
        
        // Eingabefelder
        benutzername = (EditText)findViewById(R.id.edit_benutzername);
        vorname = (EditText)findViewById(R.id.edit_vorname);
        nachname = (EditText)findViewById(R.id.edit_nachname);
        email = (EditText)findViewById(R.id.edit_email);
        pw = (EditText)findViewById(R.id.edit_passwort);
        pwWdh = (EditText)findViewById(R.id.edit_passwortwdh);
        
        // Rollen Spinner
        String [] rollenname = {"Student"};
        rolle = (Spinner)findViewById(R.id.spinner_rolle);
        rollenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, rollenname);
        rolle.setAdapter(rollenAdapter);
        
        // Button
        erstellen = (Button)findViewById(R.id.button_profilerstellen);
        erstellen.setOnClickListener(this);
    }

	public void onClick(View v) {
		if(v == erstellen) {
			nutzerdaten.open();
			String[] nutzerpaket = {benutzername.getText().toString(), rolle.getSelectedItem().toString(),
					vorname.getText().toString(), nachname.getText().toString(), email.getText().toString(),
					pw.getText().toString()};
			nutzerdaten.createProfil(nutzerpaket);
			
			Intent intent = new Intent(CreateProfilActivity.this, LoginSicht.class);
			ActivityRegistry.finishAll();
			startActivity(intent);
		}
	}
	
	@Override
	  protected void onResume() {
	    nutzerdaten.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    nutzerdaten.close();
	    super.onPause();
	  }
}
