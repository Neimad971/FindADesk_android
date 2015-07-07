package fr.esgi.findadesk;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.logging.SimpleFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.esgi.utils.Booking;
import fr.esgi.utils.CustomerReservationsAdaptor;
import fr.esgi.utils.JSONParser;
import fr.esgi.utils.User;
import fr.esgi.utils.Workspace;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

public class MyReservations extends ActionBarActivity{

	private ListView bookingListView;
	private JSONArray jsonBookings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.list_customer_reservations);
		
		jsonBookings = new JSONArray();
		try {
			jsonBookings = new AsyncTaskParseJson().execute().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	
		bookingListView = (ListView) findViewById(R.id.customer_reservations_list);
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		/*User u = new User(1, "Hussam", "Chaudhry", "Bat D5", "hussam@gmail.com", "dsdsd", "2131", "ddsd");
		Workspace w = new Workspace(1, 2, new Float(20.0), 3, "Paris", 2, false);
		bookings.add(new Booking(1, new Date(), new Date(), u, w));*/
		
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
		
		CustomerReservationsAdaptor adapter = new CustomerReservationsAdaptor(bookings, this);
		bookingListView.setAdapter(adapter);
	}
	
	public class AsyncTaskParseJson extends AsyncTask<String, String, JSONArray> {
		JSONArray json = null;

		@Override
		protected JSONArray doInBackground(String... arg0) {
			String url = "http://10.0.2.2:8080/users/1/bookings";

			JSONParser jParser = new JSONParser();

			json = jParser.getJSONFromUrl(url);
			
			return json;
		}
	}
}
