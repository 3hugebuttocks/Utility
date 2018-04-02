package com.example.utility.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.utility.R;
import com.example.utility.databinding.ActivityDbBinding;

import butterknife.BindView;

public class DataBindingActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_db);
		ActivityDbBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_db);


		User user = new User("001", "Leo", "github");
		binding.setUser(user);
	}
}
