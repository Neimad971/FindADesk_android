package fr.esgi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import fr.esgi.findadesk.R;

public class CustomerReservationsAdaptor extends BaseAdapter {

	private List<Booking> bookings;
	Context context;
	LayoutInflater inflater;

	public CustomerReservationsAdaptor(List<Booking> bookings, Context context) {
		this.bookings = bookings;
		inflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		return bookings.size();
	}

	@Override
	public Object getItem(int position) {
		return bookings.get(position);
	}

	@Override
	public long getItemId(int position) {
		return bookings.get(position).getBookingId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolderDesk holderDesk;

		if (convertView == null) {

			holderDesk = new ViewHolderDesk();
			convertView = this.inflater.inflate(R.layout.customer_reservation_row, parent, false);

			holderDesk.txtWorkspace = (TextView) convertView.findViewById(R.id.customer_reservation_wk);
			holderDesk.txtDateBegin = (TextView) convertView.findViewById(R.id.customer_reservation_db);
			holderDesk.txtDateEnd = (TextView) convertView.findViewById(R.id.customer_reservation_de);

			convertView.setTag(holderDesk);
		} else {
			holderDesk = (ViewHolderDesk) convertView.getTag();
		}
		
		Workspace wk = bookings.get(position).getWorkspace();
		String worksapceDetails = wk.getCityWorkspace();
		worksapceDetails = worksapceDetails + ", " + wk.getDescriptionWorkspace();
		
		Date beginDate = bookings.get(position).getBegin();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String beginDateText = formater.format(beginDate);
		
		Date endDate = bookings.get(position).getBegin();
		String endDateText = formater.format(endDate);
		
		holderDesk.txtWorkspace.setText(worksapceDetails);
		holderDesk.txtDateBegin.setText(beginDateText);
		holderDesk.txtDateEnd.setText(endDateText);

		return convertView;
	}
	
	private class ViewHolderDesk {
		
		TextView txtWorkspace;
		TextView txtDateBegin;
		TextView txtDateEnd;
	}

}
