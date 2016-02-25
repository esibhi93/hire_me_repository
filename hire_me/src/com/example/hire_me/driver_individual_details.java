package com.example.hire_me;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class driver_individual_details extends Activity{
	
	private static final String TAG = "driver_individual_details";

	protected void onCreate(Bundle savedInstanceState) {
		//TODO Auto-generated method stub
		Log.i(TAG,"oncreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.driver_individual);
	
	}
	
}
