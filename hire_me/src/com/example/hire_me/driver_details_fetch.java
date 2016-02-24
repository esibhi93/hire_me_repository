package com.example.hire_me;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.InputStream;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;

import android.os.Bundle;

import android.util.Log;

import android.widget.ListView;

import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;

import org.json.JSONObject;

import android.os.AsyncTask;


public class driver_details_fetch extends Activity{

	
		public static final String TAG = " driver_details_fetch ";
		Activity context;
	HttpPost httppost;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	ProgressDialog pd;
	CustomAdapter adapter;
	ListView listProduct;
	ArrayList<driver_infos> records;
	driver_infos d_info;

	protected void onCreate(Bundle savedInstanceState) {
		//TODO Auto-generated method stub
		Log.i(TAG,"oncreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.driver_details);
		context=this;
		records=new ArrayList<driver_infos>();
		listProduct=(ListView)findViewById(R.id.driver_list);
		adapter=new CustomAdapter(context,R.layout.custom_row,R.id.driver_name
				,records);
		listProduct.setAdapter(adapter);
	}

	public void onStart(){
		super.onStart();
		//execute background task
		BackTask bt=new BackTask();
		bt.execute();
	}

	private class BackTask extends AsyncTask<Void,Void,Void>{
		protected void onPreExecute(){
			super.onPreExecute();
			pd = new ProgressDialog(context);
			pd.setTitle("Retrieving data");
			pd.setMessage("Please wait.");
			pd.setCancelable(true);
			pd.setIndeterminate(true);
			pd.show();
		}


		protected Void doInBackground(Void...params){
			InputStream is=null;
			String result="";
			try{
				Log.i(TAG,"before httpclient");
				httpclient=new DefaultHttpClient();
				httppost= new HttpPost("http://10.0.2.2:5566/hire_me/insert_driver_details.php");
				response=httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				Log.i(TAG,"before httpEntity");
				// Get our response as a String.
				is = entity.getContent();
			}catch(Exception e){
				if(pd!=null)
					pd.dismiss(); //close the dialog if error occurs
				Log.e("ERROR", e.getMessage());
			}
			//convert response to string
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line+"\n");
				}

				is.close();
				result=sb.toString();
			}
			catch(Exception e){
				Log.e("ERROR", "Error converting result "+e.toString());
				
			}
			d_info= new driver_infos();
			try{
				Log.i(TAG,"jasonarray");
				result=result.substring(result.indexOf("["));
				JSONArray jArray =new JSONArray(result);
				for(int i=0;i<jArray.length();i++){
					JSONObject json_data =jArray.getJSONObject(i);
					//Product p=new Product();
					//p.setpName(json_data.getString("pname"));
					//p.setuPrice(json_data.getInt("uprice"));
					d_info.setD_name(json_data.getString("d_name"));
					d_info.setD_cost(json_data.getString("cost_per_km"));
					
					records.add(d_info);
				}
			}
			catch(Exception e){
				Log.e("ERROR", "Error pasting data "+e.toString());
			}
			return null;
		}

		protected void onPostExecute(Void result){
			if(pd!=null) pd.dismiss(); //close dialog
			Log.e("size", records.size() + "");
			adapter.notifyDataSetChanged(); //notify the ListView to get new records
		}
	}

	}



