package fr.esgi.findadesk;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import fr.esgi.utils.JSONParser;

public class FindLocationActivity extends ActionBarActivity {

	private Spinner produtSpinner;
	private Spinner locationSpinner;
	private Spinner fromSpinner;
	private Spinner minSeatsSpinner;
	private Spinner minPriceSpinner;
	private Spinner maxPriceSpinner;
	
	private String productType;
	private String location;
	private String fromDate;
	private String minimumSeats;
	private String minimumPrice;
	private String maxPrice;
	
	private Button searchButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.find_location_activity);
		
		produtSpinner = (Spinner) findViewById(R.id.product_type_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.product_type_data, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		produtSpinner.setAdapter(adapter);
		
		locationSpinner = (Spinner) findViewById(R.id.location_spinner);
		ArrayAdapter<CharSequence> adapterLoc = ArrayAdapter.createFromResource(this, R.array.city_data, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		locationSpinner.setAdapter(adapterLoc);
		
		fromSpinner = (Spinner) findViewById(R.id.from_spinner);
		fromSpinner.setAdapter(adapter);
		
		minSeatsSpinner = (Spinner) findViewById(R.id.minimum_seats_spinner);
		ArrayAdapter<CharSequence> adapterSeats = ArrayAdapter.createFromResource(this, R.array.min_seats_data, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		minSeatsSpinner.setAdapter(adapterSeats);
		
		minPriceSpinner = (Spinner) findViewById(R.id.min_price_spinner);
		ArrayAdapter<CharSequence> adapterMinPrice = ArrayAdapter.createFromResource(this, R.array.min_price_data, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		minPriceSpinner.setAdapter(adapterMinPrice);
		
		maxPriceSpinner = (Spinner) findViewById(R.id.max_price_spinner);
		ArrayAdapter<CharSequence> adapterMaxPrice = ArrayAdapter.createFromResource(this, R.array.max_price_data, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		maxPriceSpinner.setAdapter(adapterMaxPrice);
		
		searchButton = (Button) findViewById(R.id.search_btn);
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				productType = String.valueOf(produtSpinner.getSelectedItemPosition())+1;
				location = locationSpinner.getSelectedItem().toString();
				fromDate = fromSpinner.getSelectedItem().toString();
				minimumSeats = minSeatsSpinner.getSelectedItem().toString();
				minimumPrice = minPriceSpinner.getSelectedItem().toString();
				maxPrice = maxPriceSpinner.getSelectedItem().toString();
				
				new AsyncTaskParseJson().execute(productType, location, minimumSeats, minimumPrice, maxPrice);
			}
		});
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

	
	public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
		JSONArray json = null;

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2:8080/workspacesListing/" + arg0[0]
					+ "/" + arg0[1] + "/" + arg0[2] + "/"
					+ arg0[3].replace(" €", "") + "/"
					+ arg0[4].replace(" €", "");

			JSONParser jParser = new JSONParser();

			json = jParser.getJSONFromUrl(url);
			resultFromAsyncTask(json);
			
			return null;
		}
	}
	
	private void resultFromAsyncTask(JSONArray data)
	{
		Intent i = new Intent(getApplicationContext(), ListWorkspaceActivity.class);
		i.putExtra("workspacesList", data.toString());
		startActivity(i);
	}
}
