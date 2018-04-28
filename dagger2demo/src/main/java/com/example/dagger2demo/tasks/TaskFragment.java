package com.example.dagger2demo.tasks;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dagger2demo.R;
import com.example.dagger2demo.di.ActivityScoped;
import com.example.dagger2demo.practice.daggerandroid.ActivityScope;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
@ActivityScoped
public class TaskFragment extends DaggerFragment implements TaskContract.View{

	@Inject
	TaskContract.Presenter mPresenter;

	@Inject
	public TaskFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_task, container, false);
	}

	@Override
	public void onResume() {
		super.onResume();
		mPresenter.takeView(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mPresenter.takeView(this);
	}

	@Override
	public void showTasks() {
		Toast.makeText(getActivity(), "get task...", Toast.LENGTH_LONG).show();
	}
}
