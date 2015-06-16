package fr.esgi.findadesk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends ActionBarActivity{

	private Button findLocation;
	private Button myReservations;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home_activity);

		findLocation = (Button) findViewById(R.id.find_loc_btn);
		myReservations = (Button) findViewById(R.id.reservations_btn);

		findLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), FindLocationActivity.class);
				startActivity(i);
			}
		});

	}
}
