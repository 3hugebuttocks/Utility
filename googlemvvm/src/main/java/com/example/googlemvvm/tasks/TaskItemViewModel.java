package com.example.googlemvvm.tasks;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.googlemvvm.TaskViewModel;
import com.example.googlemvvm.data.source.TasksRepository;

import java.lang.ref.WeakReference;

public class TaskItemViewModel extends TaskViewModel{
	@Nullable
	private WeakReference<TaskItemNavigator> mNavigator;

	public TaskItemViewModel(Context context, TasksRepository tasksRepository) {
		super(context, tasksRepository);
	}

	public void setNavigator(TaskItemNavigator navigator) {
		mNavigator = new WeakReference<>(navigator);
	}

	public void taskClicked(){
		String taskId = getTaskId();
		if (taskId == null) {
			// Click happened before task was loaded, no-op.
			return;
		}
		if (mNavigator != null && mNavigator.get() != null) {
			mNavigator.get().openTaskDetails(taskId);
		}
	}
}
