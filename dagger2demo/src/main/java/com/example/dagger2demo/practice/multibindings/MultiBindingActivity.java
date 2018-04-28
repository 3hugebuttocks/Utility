package com.example.dagger2demo.practice.multibindings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dagger2demo.R;

import java.util.Map;

import javax.inject.Inject;

public class MultiBindingActivity extends AppCompatActivity {
	@Inject
	Map<String, String> mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_binding);

		DaggerMultibindingComponent.create().inject(this);

		for (String s : mMap.values()){
			Log.d("Leo", "val: " + s);
		}
	}
}
