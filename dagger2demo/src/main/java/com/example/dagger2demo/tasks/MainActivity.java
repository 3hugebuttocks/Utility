package com.example.dagger2demo.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.dagger2demo.BaseActivity;
import com.example.dagger2demo.R;
import com.example.dagger2demo.practice.multibindings.MultiBindingActivity;
import com.example.dagger2demo.util.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;


public class MainActivity extends BaseActivity {
	@Inject
	TasksPresenter mTasksPresenter;
	@Inject
	Lazy<TaskFragment> taskFragmentLazy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TaskFragment taskFragment = (TaskFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
		if (taskFragment == null){
			taskFragment = taskFragmentLazy.get();
			ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), taskFragment, R.id.contentFrame);

		}
	}

	public void onClick(View view){
		//startActivity(new Intent(this, FirstActivity.class));
		//startActivity(new Intent(this, MultiBindingActivity.class));
	}
}
