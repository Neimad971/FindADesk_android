package fr.esgi.findadesk;

import fr.esgi.utils.Workspace;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReservationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reservation_activity);
		
		Workspace currentWorkspace = (Workspace)getIntent().getSerializableExtra("CurrentWorkspaceBundle");
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
		//todo ajouter le code de reservation, sauvegarder une nouvelle resa
		//et rediriger vers l'activity de confirmation
	}
}
