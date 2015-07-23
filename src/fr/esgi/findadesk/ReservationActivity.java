package fr.esgi.findadesk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;

import fr.esgi.utils.JSONParser;
import fr.esgi.utils.Workspace;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ReservationActivity extends Activity {

	private Workspace currentWorkspace;
	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reservation_activity);

		currentWorkspace = (Workspace) getIntent().getSerializableExtra(
				"CurrentWorkspaceBundle");
		userId = getIntent().getStringExtra("userId");
		
		if (currentWorkspace != null)
			LoadCurrentWorkspace(currentWorkspace);
		else {
			Intent intent = new Intent(ReservationActivity.this,
					NoWorkspacesActivity.class);
			startActivity(intent);
		}
	}

	private void LoadCurrentWorkspace(Workspace workspace) {
		TextView txvAdresse = (TextView) findViewById(R.id.AdresseWorkspaceRes);
		txvAdresse.setText(workspace.getAddressWorkspace());
		
		TextView txvIdTypeWorkspace = (TextView) findViewById(R.id.TypeWorkspaceRes);
		
		String[] productType = getApplicationContext().getResources().getStringArray(R.array.product_type_data);
		Map<Integer, String> producTypeMap = new HashMap<Integer, String>();
		
		for (int i = 0; i < productType.length; i++) {
			producTypeMap.put(i, productType[i]);
		}
		
		Iterator it = producTypeMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        if((Integer.parseInt(pair.getKey().toString())) == workspace
					.getIdTypeWorkspace()-1)
	        {
	        	txvIdTypeWorkspace.setText(pair.getValue().toString());
	        }
	    }

		TextView txvSeats = (TextView) findViewById(R.id.SeatsNumberWorkspaceRes);
		txvSeats.setText(String.valueOf(workspace.getSeatsNumberWorkspace()));

		TextView txvEmail = (TextView) findViewById(R.id.EmailAdminWorkspaceRes);
		txvEmail.setText(workspace.getEmailAdmin());

		TextView txvPrice = (TextView) findViewById(R.id.PriceWorkspaceRes);
		txvPrice.setText(String.valueOf(workspace.getPriceWorkspace()));

		TextView txvDescription = (TextView) findViewById(R.id.DescriptionWorkspaceRes);
		txvDescription.setText(workspace.getDescriptionWorkspace());
	}

	public void BtnReserverOnClick(View v) {

		Intent intent = new Intent(ReservationActivity.this,
		FinalReservationActivity.class);
		intent.putExtra("userId", userId);
		Bundle b = new Bundle();
		currentWorkspace.getIdWorkspace();
		b.putSerializable("CurrentWorkspaceBundle", currentWorkspace);
		intent.putExtras(b);
		startActivity(intent);

	}

}
