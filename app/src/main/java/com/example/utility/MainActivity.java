package com.example.utility;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Timber.tag("Leo");
		Timber.d("fuck codes...");

		//Timber.d(new Exception("aaa"));
	}
}
