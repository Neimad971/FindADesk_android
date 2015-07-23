package fr.esgi.findadesk;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.esgi.utils.JSONParser;
import fr.esgi.utils.Workspace;

public class MapActivity extends ActionBarActivity implements LocationListener, OnMarkerClickListener {

	private GoogleMap map;
	private JSONArray workspaces;
	private HashMap<String, Marker> markersMaps;
	private String idClickedWorkspace;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.map_activity);

		workspaces = new JSONArray();
		markersMaps = new HashMap<String, Marker>();
		idClickedWorkspace = null;

		try {
			workspaces = new AsyncTaskWorkspaces().execute().get();
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}

		try {
			initilizeMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.setOnMarkerClickListener(this);
		
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker arg0) {
				Workspace currentWorkspace = null;
				
				for (int i = 0; i < workspaces.length(); i++) {
					JSONObject jsonobject = null;
					try {
						jsonobject = (JSONObject) workspaces.get(i);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					if (jsonobject.optString("workspaceId").equals(idClickedWorkspace)) {
						currentWorkspace = new Workspace(Integer.valueOf(jsonobject
								.optString("workspaceId")), Integer.valueOf(jsonobject
										.optString("typeId")), Float.valueOf(jsonobject
										.optString("price")), Integer.valueOf(jsonobject
										.optString("seatsNumber")), jsonobject
										.optString("description"), jsonobject
										.optString("userEmail"), jsonobject.optString("address"),
										jsonobject.optString("city"), Integer.valueOf(jsonobject
												.optString("zipCode")), jsonobject
												.optString("country"), Float.valueOf(jsonobject
												.optString("longitude")), Float.valueOf(jsonobject
												.optString("latitude")), 2, true);
					}
				}
				
				
				Intent intent = new Intent(MapActivity.this, ReservationActivity.class);
				
				Bundle b = new Bundle();
				b.putSerializable("CurrentWorkspaceBundle", currentWorkspace);
				intent.putExtras(b);
				startActivity(intent);
			}
		});
	}

	private void initilizeMap() {
		if (map == null) {
			map = ((MapFragment) getFragmentManager()
					.findFragmentById(R.id.map)).getMap();

			if (map == null) {
				Toast.makeText(getApplicationContext(),
						"Affichage de la carte impossible", Toast.LENGTH_SHORT)
						.show();
			}

			map.setMyLocationEnabled(true);
			map.getUiSettings().setMyLocationButtonEnabled(true);
			map.getUiSettings().setCompassEnabled(true);

			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			boolean enabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			if (!enabled) {
				Toast.makeText(getApplicationContext(), "Information : GPS désactivé", Toast.LENGTH_LONG).show();
			}

			Criteria criteria = new Criteria();
			String bestProvider = locationManager.getBestProvider(criteria,
					true);
			Location location = locationManager
					.getLastKnownLocation(bestProvider);
			if (location != null) {
				onLocationChanged(location);
			}
			locationManager
					.requestLocationUpdates(bestProvider, 20000, 0, this);

			for (int i = 0; i < workspaces.length(); i++) {
				JSONObject jsonobject = null;
				try {
					jsonobject = (JSONObject) workspaces.get(i);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				MarkerOptions marker = new MarkerOptions().position(
						new LatLng(Double.valueOf(jsonobject
								.optString("latitude")), Double
								.valueOf(jsonobject.optString("longitude"))))
						.title(jsonobject.optString("description"));

				Marker m = map.addMarker(marker);
				markersMaps.put(jsonobject.optString("workspaceId"), m);
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

	@Override
	public void onLocationChanged(Location location) {

		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		map.addMarker(new MarkerOptions().position(latLng));
		map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	public class AsyncTaskWorkspaces extends
			AsyncTask<String, String, JSONArray> {
		JSONArray json = null;

		@Override
		protected JSONArray doInBackground(String... arg0) {
			String url = "http://192.168.169.1:8080/workspaces";

			JSONParser jParser = new JSONParser();

			json = jParser.getJSONFromUrl(url);

			return json;
		}
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		for (Entry<String, Marker> e : markersMaps.entrySet()) {
		    if (e.getValue().equals(marker)) {
		    	idClickedWorkspace = e.getKey();
		    }
		}
		
		return false;
	}
}
