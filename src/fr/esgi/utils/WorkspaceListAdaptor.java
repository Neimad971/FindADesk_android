package fr.esgi.utils;

import java.util.List;

import fr.esgi.findadesk.R;
import fr.esgi.findadesk.R.id;
import fr.esgi.findadesk.R.layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WorkspaceListAdaptor extends BaseAdapter {

	private List<Workspace> listWorkspace = null;
	LayoutInflater layoutInflater;
	Context context;
	
	public WorkspaceListAdaptor(Context context, List<Workspace> list)
	{
		layoutInflater = LayoutInflater.from(context);
		this.context = context;
		this.listWorkspace = list;
	}
	
	@Override
	public int getCount() {
		return listWorkspace.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listWorkspace.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	 static class ViewHolderDesk {
		 Workspace workspace = new Workspace();
		  int idView;
		  TextView typeView;
		  TextView cityView;
		  TextView dispoView;
		  TextView seatsNumberView;
		  TextView nbReservationView;
		  TextView priceView;
		 }
	 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolderDesk holderDesk;
		
		if (convertView == null) 
		{
			   convertView = layoutInflater.inflate(R.layout.workspace_row,null);
			   holderDesk = new ViewHolderDesk();
			   
			   holderDesk.typeView = (TextView) convertView.findViewById(R.id.typeDesk);
			   holderDesk.cityView = (TextView) convertView.findViewById(R.id.placeDesk);
			   holderDesk.dispoView = (TextView) convertView.findViewById(R.id.dispoDesk);
			   holderDesk.seatsNumberView = (TextView) convertView.findViewById(R.id.nbPlacesDesk);
			   holderDesk.nbReservationView = (TextView) convertView.findViewById(R.id.nbReservationDesk);
			   holderDesk.priceView = (TextView) convertView.findViewById(R.id.priceDesk);

			   convertView.setTag(holderDesk);
		} 
		else 
		{
			   holderDesk = (ViewHolderDesk) convertView.getTag();
		}
		
		int idWorkspace = listWorkspace.get(position).getIdWorkspace();
		int idTypeWorkspace = listWorkspace.get(position).getIdTypeWorkspace();
		String cityWorkspace = listWorkspace.get(position).getCityWorkspace();
		Boolean dispoWorkspace = listWorkspace.get(position).getDispoWorkspace();
		int seatsNumberWorkspace = listWorkspace.get(position).getSeatsNumberWorkspace();
		Float priceWorkspace = listWorkspace.get(position).getPriceWorkspace();
		int nbReservation = listWorkspace.get(position).getNbReservation();
		
		holderDesk.idView = idWorkspace;
		holderDesk.typeView.setText(String.valueOf(idTypeWorkspace));
		holderDesk.cityView.setText(cityWorkspace);
		if(dispoWorkspace)
			holderDesk.dispoView.setText("Disponible");
		else
			holderDesk.dispoView.setText("Complet");
		
		holderDesk.seatsNumberView.setText(String.valueOf(seatsNumberWorkspace));
		holderDesk.nbReservationView.setTag(String.valueOf(nbReservation));
		holderDesk.priceView.setText(String.valueOf(priceWorkspace) + " €");
		
		holderDesk.workspace = new Workspace(idWorkspace, idTypeWorkspace, priceWorkspace, seatsNumberWorkspace, cityWorkspace,nbReservation,dispoWorkspace);
	  return convertView;
	}

}
