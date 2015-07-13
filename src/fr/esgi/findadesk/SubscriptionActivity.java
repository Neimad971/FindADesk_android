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

public class SubscriptionActivity extends ActionBarActivity{

	private Button subscriptionBtnGo;
	private EditText firstName;
	private EditText lastName;
	private EditText address;
	private EditText company;
	private EditText phone;
	private EditText email;
	private EditText password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.customer_subscription_activity);
		
		subscriptionBtnGo = (Button) findViewById(R.id.subscription_btn_go);
		
		firstName = (EditText) findViewById(R.id.firstName);
		lastName = (EditText) findViewById(R.id.lastName);
		address = (EditText) findViewById(R.id.address_subscription);
		company = (EditText) findViewById(R.id.company);
		phone = (EditText) findViewById(R.id.phone);
		email = (EditText) findViewById(R.id.emailAddress);
		password = (EditText) findViewById(R.id.password);
	
		subscriptionBtnGo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new AsyncTaskCreateUser().execute(firstName.getText().toString(), lastName.getText().toString(), address.getText().toString(), company.getText().toString(), phone.getText().toString(), email.getText().toString(), password.getText().toString());
			}
		});
	}

	public class AsyncTaskCreateUser extends AsyncTask<String, String, String> {
		JSONArray json = null;

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2:8080/users/create/" + arg0[0] + "/"
					+ arg0[1] + "/" + arg0[2] + "/" + arg0[5] + "/" + arg0[6]
					+ "/" + arg0[4] + "/" + arg0[3];

			
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
					Toast.makeText(SubscriptionActivity.this, "Error !",
							Toast.LENGTH_LONG).show();
				}
			});
		} else {
			
			String userId = null;
			Boolean isLogged = true;
			
			for (int i = 0; i < data.length(); i++) {
				JSONObject jsonobject = null;
				try {
					jsonobject = (JSONObject) data.get(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				userId = jsonobject.optString("userId");
			}
			
			Intent intent=new Intent();  
            intent.putExtra("userId", userId);
            intent.putExtra("isLogged", isLogged);
            setResult(2,intent);  
            finish();
		}
	}
}
