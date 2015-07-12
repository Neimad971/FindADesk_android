package fr.esgi.findadesk;

import org.json.JSONArray;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fr.esgi.utils.JSONParser;

public class LoginActivity extends ActionBarActivity{

	private EditText loginField;
	private EditText passwordField;
	private Button loginBtn;
	
	private JSONArray userJson;
	private Boolean isLogged = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login_activity);
		
		if (isLogged == true) {
			Intent i;
			i = new Intent(this.getApplicationContext(), HomeActivity.class);
			i.putExtra("user", userJson.toString());
			startActivity(i);
		}
		
		loginField = (EditText) findViewById(R.id.login_field);
		passwordField = (EditText) findViewById(R.id.password_field);
		loginBtn = (Button) findViewById(R.id.login_btn);
		
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String sLogin = loginField.getText().toString();
				String sPassword = passwordField.getText().toString();

				if (sLogin.matches("") && sPassword.matches("")) {
					Toast.makeText(getApplicationContext(),
							"Veuillez remplir tous les champs",
							Toast.LENGTH_LONG).show();
				} else {
					new AsyncTaskParseJson().execute(loginField.getText()
							.toString(), passwordField.getText().toString());
				}
			}
		});
	}
	
	public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
		JSONArray json = null;

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2:8080/users/" + arg0[0]
					+ "/" + arg0[1];

			JSONParser jParser = new JSONParser();

			json = jParser.getJSONFromUrl(url);
			resultFromAsyncTask(json);
			
			return null;
		}
	}
	
	private void resultFromAsyncTask(JSONArray data) {
		if (data.toString().equals("[]")) {
			runOnUiThread(new Runnable() {
				public void run() {
					Toast.makeText(LoginActivity.this, "No matching found!",
							Toast.LENGTH_LONG).show();
				}
			});
		} else {
			userJson = data;
			isLogged = true;
			
			Intent i;
			i = new Intent(this.getApplicationContext(), HomeActivity.class);
			i.putExtra("user", data.toString());
			startActivity(i);
		}
	}
}
