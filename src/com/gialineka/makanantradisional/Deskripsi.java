package com.gialineka.makanantradisional;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Deskripsi extends Activity{
	private TextView tnama;
	private TextView tdeskripsi;
	public String gambar, nama;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deskripsi);
		
		tnama = (TextView) findViewById(R.id.textView1);
		tdeskripsi = (TextView) findViewById(R.id.textView2);
		
		Intent intent = getIntent();
		
		nama = intent.getStringExtra("nama_makanan");
		String deskripsi = intent.getStringExtra("deskripsi");
		gambar = intent.getStringExtra("gambar").toString();
		
		tnama.setText(nama);
		tdeskripsi.setText(deskripsi);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void gambar(View v){
		Intent intent = new Intent(Deskripsi.this, Gambar.class);
		intent.putExtra("nama_makanan", nama);
		intent.putExtra("gambar",gambar);
		startActivity(intent);
	}

}
