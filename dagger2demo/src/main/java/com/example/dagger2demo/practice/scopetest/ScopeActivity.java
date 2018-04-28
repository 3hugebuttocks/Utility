package com.example.dagger2demo.practice.scopetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dagger2demo.R;

import javax.inject.Inject;

public class ScopeActivity extends AppCompatActivity {
	UserComponent mUserComponent;
	@Inject
	User mUser;
	/*@Inject
	Info mInfo;*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scope);

		mUserComponent = DaggerUserComponent.create();
		mUserComponent.plus().inject(this);



		Log.d("Leo", "" + mUser.getId());
		//Log.d("Leo", "" + mInfo.getInfo());

	}
}
