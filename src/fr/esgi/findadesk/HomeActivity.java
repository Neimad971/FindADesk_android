package fr.esgi.findadesk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends ActionBarActivity{

	private Button findLocation;
	private Button myReservations;
	private boolean isInternetOn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home_activity);

		findLocation = (Button) findViewById(R.id.find_loc_btn);
		myReservations = (Button) findViewById(R.id.reservations_btn);
		
		isInternetOn = isInternetOn();
		
		if(isInternetOn == false)
		{
			showDialog();
		}

		findLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = getIntent();
				String userId = intent.getStringExtra("userId");
				
				Intent i = new Intent(getApplicationContext(), FindLocationActivity.class);
				i.putExtra("userId", userId);
				startActivity(i);
			}
		});
		
		myReservations.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = getIntent();
				String userId = intent.getStringExtra("userId");
				
				Intent i = new Intent(getApplicationContext(), MyReservations.class);
				i.putExtra("userId", userId);
				startActivity(i);
			}
		});

	}
	
	public final boolean isInternetOn() {

		ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
		
		if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED
				|| connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING
				|| connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING
				|| connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
			return true;

		} else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED
				|| connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
			return false;
		}
		return false;
	}
	
	private void showDialog() {
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
	     
		 alertDialogBuilder.setTitle("Erreur");
		 alertDialogBuilder.setMessage("Pas de connexion Internet !");
		
		 alertDialogBuilder.setNeutralButton("Quitter",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					HomeActivity.this.finish();
				}
			});
		 
		 AlertDialog alertDialog = alertDialogBuilder.create();
		 alertDialog.show();
	}
}
