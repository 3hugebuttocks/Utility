package com.example.dagger2demo.data;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TaskLocalDataSource implements TaskDataSource{

	@Override
	public void getTasks(@NonNull LoadTaskCallback callback) {

	}

	@Inject
	public TaskLocalDataSource(){

	}
}
