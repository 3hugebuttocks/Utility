package com.example.utility;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import com.example.utility.databinding.DataBindingActivity;
import com.example.utility.databinding.DataBindingListActivity;
import com.example.utility.databinding.DataBingdingMapActicity;
import com.example.utility.databinding.InverseBindingActivity;

import org.apache.commons.lang3.StringUtils;

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
		boolean val = StringUtils.containsIgnoreCase("aaa" , "");
		Log.d("Leo", "" + val);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mUnbinder.unbind();
	}

	@OnClick(R.id.btn_db)
	void trendBindingView(){
		//startActivity(new Intent(this, DataBindingListActivity.class));
		//startActivity(new Intent(this, DataBindingActivity.class));
		//startActivity(new Intent(this, DataBingdingMapActicity.class));
		startActivity(new Intent(this, InverseBindingActivity.class));
	}
}
