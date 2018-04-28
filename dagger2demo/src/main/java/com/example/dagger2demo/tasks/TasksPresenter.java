package com.example.dagger2demo.tasks;

import com.example.dagger2demo.data.TaskDataSource;
import com.example.dagger2demo.data.TaskRepository;
import com.example.dagger2demo.di.ActivityScoped;
import com.example.dagger2demo.practice.daggerandroid.ActivityScope;

import javax.inject.Inject;

@ActivityScoped
final class TasksPresenter implements TaskContract.Presenter{
	private final TaskRepository mTaskRepository;

	private TaskContract.View mTaskView;

	@Inject
	TasksPresenter(TaskRepository taskRepository){
		mTaskRepository = taskRepository;
	}

	@Override
	public void loadTasks() {
		mTaskRepository.getTasks(new TaskDataSource.LoadTaskCallback() {
			@Override
			public void onTaskLoaded() {
				mTaskView.showTasks();
			}

			@Override
			public void onDataNotAvailable() {

			}
		});
	}

	@Override
	public void takeView(TaskContract.View view) {
		mTaskView = view;
		loadTasks();
	}

	@Override
	public void dropView() {
		mTaskView = null;
	}
}
