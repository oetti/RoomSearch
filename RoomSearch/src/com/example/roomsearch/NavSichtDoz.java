package com.example.roomsearch;

import Datenbank.ActivityRegistry;
import android.app.Activity;
import android.content.Intent;
//import android.content.Intent;
import android.os.Bundle;

public class NavSichtDoz extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent intent = getIntent();
        setContentView(R.layout.nav_sicht_doz);
        ActivityRegistry.register(this);
	}

	@Override
	public void onBackPressed() {
		// TODO Automatisch generierter Methodenstub
		super.onBackPressed();
		final Intent in = new Intent (NavSichtDoz.this, LoginSicht.class);
			startActivity(in);  
	}
	
	
}
