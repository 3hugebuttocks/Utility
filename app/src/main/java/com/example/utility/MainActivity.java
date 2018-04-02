package com.example.utility;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


import com.example.utility.databinding.DataBindingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
	@BindView(R.id.btn_db)
	Button mBtnBind;
	Unbinder mUnbinder;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mUnbinder = ButterKnife.bind(this);


		Timber.tag("Leo");
		Timber.d("fuck codes...");

		//Timber.d(new Exception("aaa"));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mUnbinder.unbind();
	}

	@OnClick(R.id.btn_db)
	void trendBindingView(){
		startActivity(new Intent(this, DataBindingActivity.class));
	}
}
