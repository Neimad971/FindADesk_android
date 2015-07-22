package fr.esgi.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import fr.esgi.findadesk.R;

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
		
		String[] productType = context.getResources().getStringArray(R.array.product_type_data);
		Map<Integer, String> producTypeMap = new HashMap<Integer, String>();
		
		for (int i = 0; i < productType.length; i++) {
			producTypeMap.put(i, productType[i]);
		}
		
		Iterator it = producTypeMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        if((Integer.parseInt(pair.getKey().toString())) == idTypeWorkspace-1)
	        {
	        	holderDesk.typeView.setText("Type : " + pair.getValue().toString());
	        }
	    }
		
		holderDesk.cityView.setText(cityWorkspace);
		if(dispoWorkspace)
			holderDesk.dispoView.setText("Disponible");
		else
			holderDesk.dispoView.setText("Complet");
		
		holderDesk.seatsNumberView.setText("Place(s) : " + String.valueOf(seatsNumberWorkspace));
		holderDesk.nbReservationView.setTag(String.valueOf(nbReservation));
		holderDesk.priceView.setText(String.valueOf(priceWorkspace) + " €");
		
		holderDesk.workspace = new Workspace(idWorkspace, idTypeWorkspace, priceWorkspace, seatsNumberWorkspace, cityWorkspace,nbReservation,dispoWorkspace);
	  return convertView;
	}

}
