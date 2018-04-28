package com.example.dagger2demo.data;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TaskRepository implements TaskDataSource{
	private final TaskDataSource mTaskDataSource;

	@Override
	public void getTasks(@NonNull LoadTaskCallback callback) {

	}

	@Inject
	TaskRepository(TaskDataSource dataSource){
		mTaskDataSource = dataSource;
	}
}
