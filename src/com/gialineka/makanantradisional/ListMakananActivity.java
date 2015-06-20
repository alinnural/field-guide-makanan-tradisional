package com.gialineka.makanantradisional;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListMakananActivity extends Activity{

	String data[] = null;
	String gambar[] = null;
	String deskripsi[] = null; 
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_list_makanan);

		ListView listData = (ListView) findViewById(R.id.listView1);
		Intent it = getIntent();

		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("provinsi", it
				.getStringExtra("provinsi")));
		postParameters.add(new BasicNameValuePair("jenis", it
				.getStringExtra("jenis")));
		
		TextView tjudul = (TextView) findViewById(R.id.txtJudul);
		tjudul.setText(it.getStringExtra("provinsi"));
		
	     String url = "http://10.0.2.2/serverMakananTradisional/tampilMakanan.php";
	     try {
				if (android.os.Build.VERSION.SDK_INT > 9){
					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
					StrictMode.setThreadPolicy(policy);
				}
				String result = CustomHttpClient.executeHttpPost(url, postParameters);
				JSONArray jarray = new JSONArray(result);

				data = new String[jarray.length()];
				gambar = new String[jarray.length()];
				deskripsi = new String[jarray.length()]; 
				
				if (jarray.length()>0){
					for (int i=0;i<jarray.length();i++){
						JSONObject jobjek = jarray.getJSONObject(i);
						data[i] = jobjek.getString("NAMA_MAKANAN");
						gambar[i]=jobjek.getString("GAMBAR");
						deskripsi[i]=jobjek.getString("DESKRIPSI");
						
					}
				}else{
					Toast t = Toast.makeText(this,"Menu Belum Tersedia",Toast.LENGTH_LONG);
					t.show();
				}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast toast1 = Toast.makeText(ListMakananActivity.this,
					e.toString(), Toast.LENGTH_LONG);
			toast1.show();

		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ListMakananActivity.this,
				android.R.layout.simple_list_item_1, data);
		listData.setAdapter(adapter);

		listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			/*TextView tnama = (TextView) findViewById(R.id.textView1);
			TextView tdes = (TextView) findViewById(R.id.textView2);*/
			@Override
			public void onItemClick(AdapterView<?> list, View arg1,
					int position, long arg3) {
				/*tnama.setText(data[position]);
				tdes.setText(deskripsi[position]);*/
				
				Intent intent = new Intent(ListMakananActivity.this,
						Deskripsi.class);
				intent.putExtra("nama_makanan", data[position]);
				intent.putExtra("gambar", gambar[position]);
				intent.putExtra("deskripsi", deskripsi[position]);
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
