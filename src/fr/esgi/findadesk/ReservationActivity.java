package fr.esgi.findadesk;

import fr.esgi.utils.Workspace;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReservationActivity extends Activity {

	private Workspace currentWorkspace;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reservation_activity);
		
		currentWorkspace = (Workspace)getIntent().getSerializableExtra("CurrentWorkspaceBundle");
		if(currentWorkspace != null)
			LoadCurrentWorkspace(currentWorkspace);
		else
		{
			Intent intent = new Intent(ReservationActivity.this, NoWorkspacesActivity.class);
			startActivity(intent);
		}
	}
	
	private void LoadCurrentWorkspace(Workspace workspace)
	{		
		TextView txvAdresse = (TextView) findViewById(R.id.AdresseWorkspaceRes);
		txvAdresse.setText(workspace.getAddressWorkspace());
		
		TextView txvIdTypeWorkspace = (TextView) findViewById(R.id.TypeWorkspaceRes);
		txvIdTypeWorkspace.setText(String.valueOf(workspace.getIdTypeWorkspace()));
		
		TextView txvSeats = (TextView) findViewById(R.id.SeatsNumberWorkspaceRes);
		txvSeats.setText(String.valueOf(workspace.getSeatsNumberWorkspace()));
		
		TextView txvEmail = (TextView) findViewById(R.id.EmailAdminWorkspaceRes);
		txvEmail.setText(workspace.getEmailAdmin());
		
		TextView txvPrice = (TextView) findViewById(R.id.PriceWorkspaceRes);
		txvPrice.setText(String.valueOf(workspace.getPriceWorkspace()));
		
		TextView txvDescription = (TextView) findViewById(R.id.DescriptionWorkspaceRes);
		txvDescription.setText(workspace.getDescriptionWorkspace());
	}
	
	public void BtnReserverOnClick()
	{
//		Pour Mostafa
		
		
//		Intent intent = new Intent(ReservationActivity.this, PaimentActivity.class);
//		Bundle b = new Bundle();
//		b.putSerializable("CurrentWorkspaceBundle", currentWorkspace);
//		intent.putExtras(b);
//		startActivity(intent);
	}
}
