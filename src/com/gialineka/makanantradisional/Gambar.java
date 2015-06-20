package com.gialineka.makanantradisional;

import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Gambar extends Activity {
	private ImageView gambar;
	private TextView tjudul;
	private Bitmap loadedImage;
	private long loadInterval = 1000L * 60L * 60L;
	private long lastLoadTime = 0;
	private String imageHttpAddress = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gambar);
		
		gambar = (ImageView) findViewById(R.id.imageView1);
		tjudul = (TextView) findViewById(R.id.textView1);
		
		Intent intent = getIntent();
		
		String linkGambar = intent.getStringExtra("gambar");
		String nama = intent.getStringExtra("nama_makanan");

		tjudul.setText(nama);
		imageHttpAddress = linkGambar;
		downloadGambar(imageHttpAddress);
	}
	
	private void downloadGambar(String imageHttpAddress) {
		if (System.currentTimeMillis() - lastLoadTime > loadInterval) {
			lastLoadTime = System.currentTimeMillis();
			URL imageUrl = null;
			try {
				if (android.os.Build.VERSION.SDK_INT > 9) {
					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
					StrictMode.setThreadPolicy(policy);
				}
				imageUrl = new URL(imageHttpAddress);
				HttpURLConnection conn = (HttpURLConnection) imageUrl
						.openConnection();
				conn.connect();
				loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
				gambar.setImageBitmap(loadedImage);

			} catch (Exception e) {
				Toast.makeText(getApplicationContext(),
						"Unable to load image: " + e.toString(),
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
