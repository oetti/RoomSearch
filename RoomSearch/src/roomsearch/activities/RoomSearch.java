package roomsearch.activities;


import com.example.roomsearch.R;

import datenbank.ActivityRegistry;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class RoomSearch extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_search);
        ActivityRegistry.register(this);
      
        Handler handler=new Handler();
      	Runnable r = new Runnable() {
      		public void run() {
      			final Intent in = new Intent (RoomSearch.this, LoginSicht.class);
      			startActivity(in);     			                    
		    }
		}; 
		handler.postDelayed(r, 2);
    }
}
