package com.example.googlemvvm.data.source;

import com.example.googlemvvm.data.Task;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface TasksDataSource {
	interface LoadTasksCallback{
		void onTasksLoaded(List<Task> tasks);
		void onDataNotAvailable();
	}

	interface GetTaskCallback{
		void onTaskLoaded(Task task);
		void onDataNotAvailable();
	}

	void getTasks(@NonNull LoadTasksCallback callback);
	void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback);
	void saveTask(@NonNull Task task);
	void completeTask(@NonNull Task task);
	void completeTask(@NonNull String taskId);
	void activateTask(@NonNull Task task);
	void activateTask(@NonNull String taskId);
	void clearCompletedTasks();
	void refreshTasks();
	void deleteAllTasks();
	void deleteTask(@NonNull String taskId);
}
