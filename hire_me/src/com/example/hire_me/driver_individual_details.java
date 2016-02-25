package com.example.hire_me;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class driver_individual_details extends Activity{
	
	private static final String TAG = "driver_individual_details";
	Activity context;
	StringBuffer buffer;
	ProgressDialog pd;
	CustomAdapter adapter;
	ListView listProduct;
	DriverInfo d_info;
	String name="null";
	String selected_id="null";
	String selected_name="null";
	String selected_cost="null";
	int flag=0;
	protected void onCreate(Bundle savedInstanceState) {
		//TODO Auto-generated method stub
		Log.i(TAG,"oncreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.driver_individual);
		Intent intent=getIntent();
		 selected_id=intent.getStringExtra("list_id");
		 selected_name=intent.getStringExtra("list_name");
		 selected_cost=intent.getStringExtra("list_cost");
		TextView driv_name=(TextView)findViewById(R.id.driv_name);
		TextView driv_cost=(TextView)findViewById(R.id.driv_cost);
		driv_name.setText(selected_name);
		driv_cost.setText("I charge Rs."+selected_cost+ "per hour");
		Log.i(TAG,"ending oncreate");
	}

}
