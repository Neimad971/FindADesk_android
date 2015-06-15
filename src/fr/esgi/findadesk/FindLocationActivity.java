package fr.esgi.findadesk;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FindLocationActivity extends ActionBarActivity {

	/*
	// URL to get JSON Array
	private static String url = "http://localhost:8080/users";

	// JSON Node Names
	private static final String TAG_USER = "user";
	private static final String TAG_ID = "id";
	private static final String TAG_FIRSTNAME = "firstName";
	private static final String TAG_LASTNAME = "lastName";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_PWD = "password";

	JSONArray user = null;
	*/
	
	private Spinner produtSpinner;
	private Spinner locationSpinner;
	private Spinner fromSpinner;
	private Spinner minSeatsSpinner;
	private Spinner minPriceSpinner;
	private Spinner maxPriceSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.find_location_activity);
		
		produtSpinner = (Spinner) findViewById(R.id.product_type_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.product_type_data, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		produtSpinner.setAdapter(adapter);
		
		locationSpinner = (Spinner) findViewById(R.id.location_spinner);
		locationSpinner.setAdapter(adapter);
		
		fromSpinner = (Spinner) findViewById(R.id.from_spinner);
		fromSpinner.setAdapter(adapter);
		
		minSeatsSpinner = (Spinner) findViewById(R.id.minimum_seats_spinner);
		minSeatsSpinner.setAdapter(adapter);
		
		minPriceSpinner = (Spinner) findViewById(R.id.min_price_spinner);
		minPriceSpinner.setAdapter(adapter);
		
		maxPriceSpinner = (Spinner) findViewById(R.id.max_price_spinner);
		maxPriceSpinner.setAdapter(adapter);
		
		//new AsyncTaskParseJson().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

		final String TAG = "MainActivity.java";

		// set your json string url here
		String url = "http://10.0.2.2:8080/users";

		// contacts JSONArray
		JSONArray dataJsonArr = null;

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected String doInBackground(String... arg0) {

			try {

				// instantiate our json parser
				JSONParser jParser = new JSONParser();

				// get json string from url
				JSONArray json = jParser.getJSONFromUrl(url);
				
				Log.i(TAG, json.getString(0));
				

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String strFromDoInBg) {
		}
	}*/
}
