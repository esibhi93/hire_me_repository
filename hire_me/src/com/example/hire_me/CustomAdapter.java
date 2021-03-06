package com.example.hire_me;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<DriverInfo> {
	private static final String TAG = "CustomAdapter";
	ArrayList<DriverInfo> records;
	Context context;		
	int groupid;

	public CustomAdapter(Context context, int vg, int id, ArrayList<DriverInfo> 
	records) {

		super(context, vg, id,records);
		this.context = context;
		groupid = vg;
		this.records = records;
	}

	
	@Override
	public DriverInfo getItem(int position) {
		// TODO Auto-generated method stub
		 super.getItem(position);
		 return records.get(position);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG,"getView");
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(groupid, parent, false);
		Log.i(TAG,"driver_name");
		String drivername=records.get(position).getD_name();
		String driverCost=records.get(position).getD_cost();
		TextView driver_name = (TextView) itemView.findViewById(R.id.driver_name);
		driver_name.setText(""+records.get(position).getD_name());

		Log.i(TAG,""+driver_name);
		TextView driver_cost = (TextView) itemView.findViewById(R.id.driver_cost);
		driver_cost.setText("Rs." + records.get(position).getD_cost());
		return itemView;
	}
}


