package fr.esgi.findadesk;

import fr.esgi.utils.Workspace;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ReservationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reservation_activity);
		
//		int idWorkspace = (int) getIntent().getExtras().get("idWorkspace");
		LoadCurrentWorkspace();
	}
	
	private void LoadCurrentWorkspace()
	{
		Workspace workspace = new Workspace();
		// Se conneter à la base et recuperer le workspace courant
		workspace.setAddressWorkspace("2 rue de Paris");
		workspace.setIdTypeWorkspace(1);
		workspace.setSeatsNumberWorkspace(100);
		workspace.setEmailAdmin("toto@toto.rr");
		workspace.setPriceWorkspace(Float.valueOf(200));
		workspace.setDescriptionWorkspace("tres bon bureau"
				+ "tres bon bureau"
				+ "tres bon bureau"
				+ "tres bon bureau"
				+ "tres bon bureau"
				+ "tres bon bureau"
				+ "tres bon bureau"
				+ "tres bon bureau"
				+ "tres bon bureau"
				+ "tres bon bureau"
				+ "tres bon bureau"
				+ "tres bon bureau");	
		
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
}
