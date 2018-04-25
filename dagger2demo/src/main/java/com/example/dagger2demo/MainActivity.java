package com.example.dagger2demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.dagger2demo.daggerandroid.Person;
import com.example.dagger2demo.multibindings.MultiBindingActivity;
import com.example.dagger2demo.mvp.LoginActivity;
import com.example.dagger2demo.producer.AsyActivity;

import javax.inject.Inject;


public class MainActivity extends BaseActivity {
	@Inject
	Person mPerson;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d("Leo", "" + mPerson.getName());
	}

	public void onClick(View view){
		//startActivity(new Intent(this, FirstActivity.class));
		startActivity(new Intent(this, MultiBindingActivity.class));
	}
}
