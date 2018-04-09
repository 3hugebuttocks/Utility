package com.example.googlemvvm.taskdetail;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.googlemvvm.TaskViewModel;
import com.example.googlemvvm.data.source.TasksRepository;

public class TaskDetailViewModel extends TaskViewModel{
	@Nullable
	private TaskDetailNavigator mTaskDetailNavigator;

	public TaskDetailViewModel(Context context, TasksRepository tasksRepository) {
		super(context, tasksRepository);
	}

	public void setNavigator(TaskDetailNavigator taskDetailNavigator) {
		mTaskDetailNavigator = taskDetailNavigator;
	}

	public void onActivityDestroyed() {
		// Clear references to avoid potential memory leaks.
		mTaskDetailNavigator = null;
	}

	/**
	 * Can be called by the Data Binding Library or the delete menu item.
	 */
	public void deleteTask() {
		super.deleteTask();
		if (mTaskDetailNavigator != null) {
			mTaskDetailNavigator.onTaskDeleted();
		}
	}

	public void startEditTask() {
		if (mTaskDetailNavigator != null) {
			mTaskDetailNavigator.onStartEditTask();
		}
	}
}
