package com.example.googlemvvm;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.googlemvvm.data.FakeTasksRemoteDataSource;
import com.example.googlemvvm.data.source.local.TasksLocalDataSource;
import com.example.googlemvvm.data.source.local.ToDoDatabase;
import com.example.googlemvvm.data.source.TasksRepository;
import com.example.googlemvvm.util.AppExecutors;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {
	public static TasksRepository provideTasksRepository(@NonNull Context context){
		checkNotNull(context);
		ToDoDatabase database = ToDoDatabase.getInstance(context);
		return TasksRepository.getInstance(FakeTasksRemoteDataSource.getInstance(),
				TasksLocalDataSource.getInstance(new AppExecutors(), database.taskDao()));
	}
}
