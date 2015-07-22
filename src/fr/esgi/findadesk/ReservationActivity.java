package fr.esgi.findadesk;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reservation_activity);

		currentWorkspace = (Workspace) getIntent().getSerializableExtra(
				"CurrentWorkspaceBundle");
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
		txvIdTypeWorkspace.setText(String.valueOf(workspace
				.getIdTypeWorkspace()));

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
		// Pour Mostafa

		// Intent intent = new Intent(ReservationActivity.this,
		// PaimentActivity.class);
		// Bundle b = new Bundle();
		// b.putSerializable("CurrentWorkspaceBundle", currentWorkspace);
		// intent.putExtras(b);
		// startActivity(intent);

		new AsyncTaskBooking().execute("2", "2", "2015-07-22", "2015-07-25", "2015-07-21", "225");
	}

	public class AsyncTaskBooking extends AsyncTask<String, String, String> {
		JSONArray json = null;

		@Override
		protected String doInBackground(String... arg0) {
			String url = "http://10.0.2.2:8080/bookings/save/" + arg0[0] + "/"
					+ arg0[1] + "/" + arg0[2] + "/" + arg0[3] + "/" + arg0[4] + "/" + arg0[5];

			JSONParser jParser = new JSONParser();

			json = jParser.getJSONFromUrl(url);

			return null;
		}
	}
}
