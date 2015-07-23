package fr.esgi.findadesk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;

import fr.esgi.findadesk.FindLocationActivity.AsyncTaskParseJson;
import fr.esgi.utils.JSONParser;
import fr.esgi.utils.Workspace;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FinalReservationActivity extends ActionBarActivity {
	
	private Spinner typeCarteSpinner;
	
	private EditText dateDebut;
	private EditText dateFin;
	private EditText numCarte;
	private EditText dateExpiration;
	private EditText crypto;
	
	private Button payButton;
	
	private String _dateDebut;
	private String _dateFin;
	private String _dateNow;
	
	private String _workspaceID;
	private Workspace wk;
	
	private String _userID;
	private String _price;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final_reservation);
		
		typeCarteSpinner = (Spinner) findViewById(R.id.spinnertypecarte);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_carte, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typeCarteSpinner.setAdapter(adapter);
		
		dateDebut = (EditText) findViewById(R.id.dateDebutRes);
		dateFin = (EditText) findViewById(R.id.dateFinRes);
		numCarte = (EditText) findViewById(R.id.numCarte);
		dateExpiration = (EditText) findViewById(R.id.dateExpiration);
		crypto = (EditText) findViewById(R.id.cryptogramme);
		
		wk = (Workspace) getIntent().getSerializableExtra(
				"CurrentWorkspaceBundle");
		_userID = getIntent().getStringExtra("userId");
		//System.out.println(calcule_date("10-05-1993","14-05-1993")*wk.getPriceWorkspace());
		
		
		payButton = (Button) findViewById(R.id.buttonPay);
		
		payButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {			
				
				try {
					_dateDebut = convertFormatDate(dateDebut.getText().toString());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					_dateFin = convertFormatDate(dateFin.getText().toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				_dateNow = getDateNow();
				_workspaceID = String.valueOf(wk.getIdWorkspace());
				_price = String.valueOf((long) (calcule_date("10-05-1993","14-05-1993")*wk.getPriceWorkspace()));				
				
				new AsyncTaskBooking().execute(_userID, _workspaceID, _dateDebut, _dateFin, _dateNow, _price);
				
				finish();
				Toast.makeText(getApplicationContext(),
						"Réservation effectuée", Toast.LENGTH_LONG)
						.show();
				Intent i = new Intent(getApplicationContext(), HomeActivity.class);
				startActivityForResult(i, 1);
				
			}
		});
		
	}
	
	public class AsyncTaskBooking extends AsyncTask<String, String, String> {
		JSONArray json = null;

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2:8080/bookings/save/" + arg0[0] + "/"
					+ arg0[1] + "/" + arg0[2] + "/" + arg0[3] + "/" + arg0[4] + "/" + arg0[5];

			JSONParser jParser = new JSONParser();

			json = jParser.getJSONFromUrl(url);

			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.final_reservation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public int calculNbJourRes(Date dateDeb, Date dateFin)
	{
		
		return 0;
		
	}
	
	public String convertFormatDate(String actuelle) throws ParseException
	{
		//String s = "2011-07-08";
		String start_dt = "2011-01-01";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
		Date date = (Date)formatter.parse(actuelle);
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
		String finalString = newFormat.format(date);
		return finalString;	
	}
	
	public String getDateNow()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public  int calcule_date(String dateDeb, String dateFin)  {

	    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	 
	    long nbDaysFirstDate=0;
	    long nbDaysSecondDate=0;
	 
	 
	    Date d1 = null;
		try {
			d1 = dateFormat.parse(dateFin);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    Date d2 = null;
		try {
			d2 = dateFormat.parse(dateDeb);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    long DayInMillisecond=24*60*60*1000;
	     int nbDaysDate1 = (int) (d1.getTime()/DayInMillisecond);
	     long nbDaysDate2 = d2.getTime()/DayInMillisecond;
	 
	   String NBjour=Long.toString(nbDaysDate1-nbDaysDate2);
	   return Integer.parseInt(NBjour);     
	}
	
	
}
