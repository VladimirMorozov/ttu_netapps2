package com.example.buildingwikiclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

//import com.example.communication.Communicator;
//import com.example.data.Building;



import communicator.Communicator;

import data.Building;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView label = (TextView)findViewById(R.id.textView2);
		new Thread(new RetrieveBuildingsRunnable(label)).start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class RetrieveBuildingsRunnable implements Runnable {

		TextView textView;
		public RetrieveBuildingsRunnable(TextView textView) {
			this.textView = textView;
		}
		List<Building> buildings;
		
		@Override
		public void run() {
			try {
				buildings = Communicator.getBuildings();
				runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                    	textView.setText(buildings.toString());
                    }
                });
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}
	

}
