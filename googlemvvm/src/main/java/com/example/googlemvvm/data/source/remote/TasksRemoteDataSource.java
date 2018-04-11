package com.example.googlemvvm.data.remote;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.googlemvvm.data.Task;
import com.example.googlemvvm.data.source.TasksDataSource;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TasksRemoteDataSource implements TasksDataSource{
	private static TasksRemoteDataSource INSTANCE;

	private static final int SERVICE_LATENCY_IN_MILLIS = 2000;

	private final static Map<String, Task> TASKS_SERVICE_DATA;

	static {
		TASKS_SERVICE_DATA = new LinkedHashMap<>(2);
		addTask("Build tower in Pisa", "Ground looks good, no foundation work required.", "0");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "1");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "2");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "3");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "4");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "5");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "6");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "7");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "8");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "12");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "13");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "14");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "15");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "16");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "17");
		addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "18");
	}

	private static void addTask(String title, String description, String id){
		Task newTask = new Task(title, description, id);
		TASKS_SERVICE_DATA.put(newTask.getId(), newTask);
	}

	@Override
	public void getTasks(final LoadTasksCallback callback) {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				callback.onTasksLoaded(Lists.newArrayList(TASKS_SERVICE_DATA.values()));
			}
		}, SERVICE_LATENCY_IN_MILLIS);
	}

	@Override
	public void getTask(String taskId, final GetTaskCallback callback) {
		final Task task = TASKS_SERVICE_DATA.get(taskId);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				callback.onTaskLoaded(task);
			}
		}, SERVICE_LATENCY_IN_MILLIS);
	}

	@Override
	public void saveTask(Task task) {
		TASKS_SERVICE_DATA.put(task.getId(), task);
	}

	@Override
	public void completeTask(@NonNull Task task) {
		Task completedTask = new Task(task.getTitle(), task.getDescription(), task.getId(), true);
		TASKS_SERVICE_DATA.put(task.getId(), completedTask);
	}

	@Override
	public void completeTask(@NonNull String taskId) {
		// Not required for the remote data source because the {@link TasksRepository} handles
		// converting from a {@code taskId} to a {@link task} using its cached data.
	}

	@Override
	public void activateTask(@NonNull Task task) {
		Task activeTask = new Task(task.getTitle(), task.getDescription(), task.getId());
		TASKS_SERVICE_DATA.put(task.getId(), activeTask);
	}

	@Override
	public void activateTask(@NonNull String taskId) {
		// Not required for the remote data source because the {@link TasksRepository} handles
		// converting from a {@code taskId} to a {@link task} using its cached data.
	}

	@Override
	public void clearCompletedTasks() {
		Iterator<Map.Entry<String, Task>> it = TASKS_SERVICE_DATA.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry<String, Task> entry = it.next();
			if (entry.getValue().isCompleted()){
				it.remove();
			}
		}
	}

	@Override
	public void refreshTasks() {
		// Not required because the {@link TasksRepository} handles the logic of refreshing the
		// tasks from all the available data sources.
	}

	@Override
	public void deleteAllTasks() {
		TASKS_SERVICE_DATA.clear();
	}

	@Override
	public void deleteTask(@NonNull String taskId) {
		TASKS_SERVICE_DATA.remove(taskId);
	}
}
