package com.gialineka.makanantradisional;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MakananDetailActivity extends TabActivity {
	
	protected void onCreate (Bundle SavedInstanceState){
		super.onCreate(SavedInstanceState);
		setContentView(R.layout.activity_detail_makanan);
		
		TabHost tabhost = getTabHost();
		
		//tab for Deskripsi
		TabSpec despec = tabhost.newTabSpec("Deskripsi");
		//setting title and icon for the tab
		despec.setIndicator("Deskripsi");
		Intent desIntent = new Intent(this, Deskripsi.class);
		despec.setContent(desIntent);
		
		//tab for Gambar
		TabSpec gampec = tabhost.newTabSpec("Gambar");
		//setting title and icon for the tab
		gampec.setIndicator("Gambar");
		Intent gamIntent = new Intent(this, Gambar.class);
		gampec.setContent(gamIntent);
		
		//adding all tabspec to tab host
		tabhost.addTab(despec);
		tabhost.addTab(gampec);

	}

}
