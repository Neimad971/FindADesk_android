package fr.esgi.findadesk;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.esgi.utils.Workspace;
import fr.esgi.utils.WorkspaceListAdaptor;

public class ListWorkspaceActivity extends Activity {

	private ListView myListView;
	private ArrayList<Workspace> listWorkspace = new ArrayList<Workspace>();
	
	private String jsonData;
	private JSONArray workspaceJsonArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_workspace_activity);
		
		Intent intent = getIntent();
		jsonData = intent.getStringExtra("workspacesList");
		
		try {
			workspaceJsonArray = new JSONArray(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
		
		myListView = (ListView) findViewById(R.id.listWorkspace);
		
		for (int i = 0; i < workspaceJsonArray.length(); i++) {
			JSONObject jsonobject = null;
			try {
				jsonobject = (JSONObject) workspaceJsonArray.get(i);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			listWorkspace.add(new Workspace(Integer.valueOf(jsonobject
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
							.optString("latitude")), 2, true));
		}
		
		myListView.setAdapter(new WorkspaceListAdaptor(getApplicationContext(), listWorkspace));
		myListView.setOnItemClickListener(itemClickListener);
		
		registerForContextMenu(myListView);
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
	
	
	OnItemClickListener itemClickListener = new OnItemClickListener() 
	{
		
	    public void onItemClick(AdapterView<?> adapter, View view, int pos, long itemID) 
	    {	    	
	    	Intent i = getIntent();
			String userId = i.getStringExtra("userId");
			
	        Intent intent = new Intent(ListWorkspaceActivity.this, ReservationActivity.class);
			intent.putExtra("userId", userId);
			Workspace currentWorkspace = (Workspace) adapter.getItemAtPosition(pos);
			Bundle b = new Bundle();
			b.putSerializable("CurrentWorkspaceBundle", currentWorkspace);
			intent.putExtras(b);
			startActivity(intent);
	    }
	};
	
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) 
    {
    	  if (v.getId()==R.id.listWorkspace) {
    		  AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
    		  menu.setHeaderTitle(listWorkspace.get(info.position).getCityWorkspace());
    		  MenuInflater inflater = getMenuInflater();
    		  inflater.inflate(R.menu.menu_workspace_longclick_item, menu);
    	  }
    }
    
    public boolean onContextItemSelected(MenuItem item) 
    {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        int itemId = item.getItemId();
		if (itemId == R.id.makeFavourite) {
			MakeFavourite(info.position);
			return true;
		} else {
			return super.onContextItemSelected(item);
		}
    }
    
    private void MakeFavourite(int position)
    {
    	
    }
}
