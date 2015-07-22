package fr.esgi.findadesk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	private Button subscriptionBtn;
	
	private JSONArray userJson;
	private Boolean isLogged = false;
	private String userId;
	
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
		
		userId = null;
		
		loginField = (EditText) findViewById(R.id.login_field);
		passwordField = (EditText) findViewById(R.id.password_field);
		loginBtn = (Button) findViewById(R.id.login_btn);
		subscriptionBtn = (Button) findViewById(R.id.subscription_btn);
		
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
		
		subscriptionBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), SubscriptionActivity.class);
				startActivityForResult(i, 2);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==2)  
        {  
           userId = data.getStringExtra("userId");
           isLogged = data.getBooleanExtra("isLogged", false);
        }
		
		Intent i;
		i = new Intent(this.getApplicationContext(), HomeActivity.class);
		i.putExtra("userId", userId);
		startActivity(i);
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
					Toast.makeText(LoginActivity.this, "Pas de correspondance !",
							Toast.LENGTH_LONG).show();
				}
			});
		} else {
			userJson = data;
			isLogged = true;
			
			for (int i = 0; i < data.length(); i++) {
				JSONObject jsonobject = null;
				try {
					jsonobject = (JSONObject) data.get(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				userId = jsonobject.optString("userId");
			}
			
			Intent i;
			i = new Intent(this.getApplicationContext(), HomeActivity.class);
			i.putExtra("user", data.toString());
			i.putExtra("userId", userId);
			startActivity(i);
		}
	}
}
