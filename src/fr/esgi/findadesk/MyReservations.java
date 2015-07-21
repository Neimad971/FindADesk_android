package fr.esgi.findadesk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import fr.esgi.utils.Booking;
import fr.esgi.utils.CustomerReservationsAdaptor;
import fr.esgi.utils.JSONParser;
import fr.esgi.utils.User;
import fr.esgi.utils.Workspace;

public class MyReservations extends ActionBarActivity{

	private ListView bookingListView;
	private JSONArray jsonBookings;
	private String userId;
	private ArrayList<Booking> bookings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.list_customer_reservations);
		
		Intent intent = getIntent();
		userId = intent.getStringExtra("userId");
		
		jsonBookings = new JSONArray();
		try {
			jsonBookings = new AsyncTaskParseJson().execute(userId).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	
		bookingListView = (ListView) findViewById(R.id.customer_reservations_list);
		bookings = new ArrayList<Booking>();
		
		
		if (jsonBookings != null) {
			for (int i = 0; i < jsonBookings.length(); i++) {
				JSONObject jsonobject = null;
				try {
					jsonobject = (JSONObject) jsonBookings.get(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				JSONObject userJson = jsonobject.optJSONObject("user");
				User u = new User(Integer.valueOf(userJson.optInt("userId")), userJson.optString("firstName"), userJson.optString("lastName"), userJson.optString("address"), userJson.optString("email"), userJson.optString("password"), userJson.optString("phoneNumber"), userJson.optString("company"));
				
				JSONObject workspaceJson = jsonobject.optJSONObject("workspace");
				Workspace w = new Workspace(Integer.valueOf(workspaceJson
						.optString("workspaceId")), Integer.valueOf(workspaceJson
						.optString("typeId")), Float.valueOf(workspaceJson
						.optString("price")), Integer.valueOf(workspaceJson
						.optString("seatsNumber")), workspaceJson
						.optString("description"), workspaceJson
						.optString("userEmail"), workspaceJson.optString("address"),
						workspaceJson.optString("city"), Integer.valueOf(workspaceJson
								.optString("zipCode")), workspaceJson
								.optString("country"), Float.valueOf(workspaceJson
								.optString("longitude")), Float.valueOf(workspaceJson
								.optString("latitude")), 2, true);
				
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
				String bDate = jsonobject.optString("begin");
				String eDate = jsonobject.optString("end");
				
				Date beginDate = null;
				Date endDate = null;
				try {
					beginDate = formater.parse(bDate);
					endDate = formater.parse(eDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				bookings.add(new Booking(Integer.valueOf(jsonobject.optString("bookingId")), beginDate, endDate, u, w));
			}
		}
		
		CustomerReservationsAdaptor adapter = new CustomerReservationsAdaptor(bookings, this);
		bookingListView.setAdapter(adapter);
		
		bookingListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
			
				deleteBooking(String.valueOf(id));
				
				return false;
			}
		});
	}
	
	public void deleteBooking(String idBooking) {
		
		final String id = idBooking;
		
		AlertDialog.Builder alert = new AlertDialog.Builder(
                MyReservations.this);
        alert.setTitle("Suppression de la réservation");
        alert.setMessage("Confirmer la suppression");
        alert.setPositiveButton("OK", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            	new AsyncTaskDeleteBooking().execute(id);                            
                dialog.dismiss();
                
                finish();
                startActivity(getIntent());
            }
        });
        
        alert.setNegativeButton("Annuler", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
	}
	
	public class AsyncTaskParseJson extends AsyncTask<String, String, JSONArray> {
		JSONArray json = null;

		@Override
		protected JSONArray doInBackground(String... arg0) {
			String url = "http://10.0.2.2:8080/users/" + arg0[0] + "/bookings";
			
			JSONParser jParser = new JSONParser();

			json = jParser.getJSONFromUrl(url);
			
			return json;
		}
	}
	
	public class AsyncTaskDeleteBooking extends AsyncTask<String, String, String> {
		JSONArray json = null;

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2:8080/bookings/delete/" + arg0[0];
			
			JSONParser jParser = new JSONParser();

			json = jParser.getJSONFromUrl(url);
			
			return null;
		}
	}
}
