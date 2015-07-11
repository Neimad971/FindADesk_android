package fr.esgi.findadesk;

import org.json.JSONArray;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import fr.esgi.utils.JSONParser;

public class FindLocationActivity extends ActionBarActivity {

	private Spinner produtSpinner;

	private EditText locationSpinner;
	private EditText minSeatsSpinner;
	private EditText minPriceSpinner;
	private EditText maxPriceSpinner;
	
	private String productType;
	private String location;
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
		
		locationSpinner = (EditText) findViewById(R.id.location_spinner);
		minSeatsSpinner = (EditText) findViewById(R.id.minimum_seats_spinner);
		minPriceSpinner = (EditText) findViewById(R.id.min_price_spinner);
		maxPriceSpinner = (EditText) findViewById(R.id.max_price_spinner);
	
		searchButton = (Button) findViewById(R.id.search_btn);
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				productType = String.valueOf(produtSpinner.getSelectedItemPosition())+1;
				location = locationSpinner.getText().toString();
				minimumSeats = minSeatsSpinner.getText().toString();
				minimumPrice = minPriceSpinner.getText().toString();
				maxPrice = maxPriceSpinner.getText().toString();
				
				new AsyncTaskParseJson().execute(productType, location, minimumSeats, minimumPrice, maxPrice);
			}
		});
	}
	
	public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
		JSONArray json = null;

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2:8080/workspacesListing/" + arg0[0]
					+ "/" + arg0[1] + "/" + arg0[2] + "/" + arg0[3] + "/"
					+ arg0[4];

			JSONParser jParser = new JSONParser();

			json = jParser.getJSONFromUrl(url);
			resultFromAsyncTask(json);

			return null;
		}
	}
	
	private void resultFromAsyncTask(JSONArray data)
	{
		Intent i;
		if(data.length() == 0)
		{
			i = new Intent(getApplicationContext(), NoWorkspacesActivity.class);
			startActivityForResult(i, 1);
		} else {
			i = new Intent(getApplicationContext(), ListWorkspaceActivity.class);
			i.putExtra("workspacesList", data.toString());
			startActivity(i);
		}
	}
}
