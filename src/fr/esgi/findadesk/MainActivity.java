package fr.esgi.findadesk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.esgi.utils.JSONParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		new AsyncTaskParseJson().execute();
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
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// you can make this class as another java file so it will be separated from
	// your main activity.
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
	}
}
