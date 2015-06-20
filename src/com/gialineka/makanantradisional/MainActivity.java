package com.gialineka.makanantradisional;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {

	private Spinner spinnerKota;
	private String[] values = null;// {"- pilih daerah -","Sumatra Barat","Jawa Tengah","Jawa Barat"};
	ArrayAdapter<String> adapter;
	public String item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		spinnerKota = (Spinner) findViewById(R.id.spinner1);
		ImageButton masakan = (ImageButton) findViewById(R.id.imageButton1);		
		ImageButton minuman = (ImageButton) findViewById(R.id.imageButton2);
		ImageButton jajanan = (ImageButton) findViewById(R.id.imageButton3);
		
		try {
			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

			String result = CustomHttpClient
					.executeHttpGet("http://10.0.2.2/serverMakananTradisional/tampilProvinsi.php");

			JSONArray jArray = new JSONArray(result);

			values = new String[jArray.length()+1];

			values[0] = "- pilih provinsi -";
			for (int i = 1; i < jArray.length()+1; i++) {
				JSONObject json_data = jArray.getJSONObject(i-1);
				values[i] = json_data.getString("NAMA_PROVINSI");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast toast1 = Toast.makeText(MainActivity.this, e.toString(),
					Toast.LENGTH_LONG);
			toast1.show();

		}
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerKota.setAdapter(adapter);
		
		spinnerKota.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> spinner, View arg1,
					int position, long arg3) {
				if (position != 0) {
					item = spinner.getItemAtPosition(position).toString();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		masakan.setOnClickListener(new View.OnClickListener() {
			private String jenis="masakan";

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				//jenis = "masakan";
				
				Intent intent = new Intent(MainActivity.this,ListMakananActivity.class);
				intent.putExtra("provinsi", item);
				intent.putExtra("jenis", jenis );
				startActivity(intent);
				
			}
		});
		
		minuman.setOnClickListener(new View.OnClickListener() {
			private String jenis="minuman";
			
			TextView tcoba = (TextView) findViewById(R.id.textView1);
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				//jenis = "minuman";
				
				Intent intent = new Intent(MainActivity.this,ListMakananActivity.class);
				intent.putExtra("provinsi", item);
				intent.putExtra("jenis", jenis );
				startActivity(intent);
				
				
			}
		});
		
		jajanan.setOnClickListener(new View.OnClickListener() {
			private String jenis="jajanan";
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				//jenis = "jajanan";
				
				Intent intent = new Intent(MainActivity.this,ListMakananActivity.class);
				intent.putExtra("provinsi", item);
				intent.putExtra("jenis", jenis );
				startActivity(intent);
				
			}
		});
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
