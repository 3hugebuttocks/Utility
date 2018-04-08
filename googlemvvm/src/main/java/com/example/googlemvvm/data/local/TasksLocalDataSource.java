package com.example.googlemvvm.data.local;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.example.googlemvvm.data.Task;
import com.example.googlemvvm.data.source.TasksDataSource;
import com.example.googlemvvm.util.AppExecutors;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class TasksLocalDataSource implements TasksDataSource{
	private static volatile TasksLocalDataSource INSTANCE;

	private TasksDao mTasksDao;

	private AppExecutors mAppExecutors;

	private TasksLocalDataSource(@NonNull AppExecutors appExecutors, @NonNull TasksDao tasksDao){
		mAppExecutors = appExecutors;
		mTasksDao = tasksDao;
	}

	public static TasksLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
												   @NonNull TasksDao tasksDao){
		if (INSTANCE == null){
			synchronized (TasksLocalDataSource.class){
				if (INSTANCE == null){
					INSTANCE = new TasksLocalDataSource(appExecutors, tasksDao);
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public void getTasks(final LoadTasksCallback callback) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				final List<Task> tasks = mTasksDao.getTasks();
				mAppExecutors.mainThread().execute(new Runnable() {
					@Override
					public void run() {
						if (tasks.isEmpty()){
							callback.onDataNotAvailable();
						}else {
							callback.onTasksLoaded(tasks);
						}
					}
				});
			}
		};

		mAppExecutors.diskIO().execute(runnable);
	}

	@Override
	public void getTask(@NonNull final String taskId, @NonNull final GetTaskCallback callback) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				final Task task = mTasksDao.getTaskById(taskId);

				mAppExecutors.mainThread().execute(new Runnable() {
					@Override
					public void run() {
						if (task != null) {
							callback.onTaskLoaded(task);
						} else {
							callback.onDataNotAvailable();
						}
					}
				});
			}
		};

		mAppExecutors.diskIO().execute(runnable);
	}

	@Override
	public void saveTask(@NonNull final Task task) {
		checkNotNull(task);
		Runnable saveRunnable = new Runnable() {
			@Override
			public void run() {
				mTasksDao.insertTask(task);
			}
		};
		mAppExecutors.diskIO().execute(saveRunnable);
	}

	@Override
	public void completeTask(@NonNull final Task task) {
		Runnable completeRunnable = new Runnable() {
			@Override
			public void run() {
				mTasksDao.updateCompleted(task.getId(), true);
			}
		};

		mAppExecutors.diskIO().execute(completeRunnable);
	}

	@Override
	public void completeTask(@NonNull String taskId) {
		// Not required for the local data source because the {@link TasksRepository} handles
		// converting from a {@code taskId} to a {@link task} using its cached data.
	}

	@Override
	public void activateTask(@NonNull final Task task) {
		Runnable activateRunnable = new Runnable() {
			@Override
			public void run() {
				mTasksDao.updateCompleted(task.getId(), false);
			}
		};
		mAppExecutors.diskIO().execute(activateRunnable);
	}

	@Override
	public void activateTask(@NonNull String taskId) {
		// Not required for the local data source because the {@link TasksRepository} handles
		// converting from a {@code taskId} to a {@link task} using its cached data.
	}

	@Override
	public void clearCompletedTasks() {
		Runnable clearTasksRunnable = new Runnable() {
			@Override
			public void run() {
				mTasksDao.deleteCompletedTasks();

			}
		};

		mAppExecutors.diskIO().execute(clearTasksRunnable);
	}

	@Override
	public void refreshTasks() {
		// Not required because the {@link TasksRepository} handles the logic of refreshing the
		// tasks from all the available data sources.
	}

	@Override
	public void deleteAllTasks() {
		Runnable deleteRunnable = new Runnable() {
			@Override
			public void run() {
				mTasksDao.deleteTasks();
			}
		};

		mAppExecutors.diskIO().execute(deleteRunnable);
	}

	@Override
	public void deleteTask(@NonNull final String taskId) {
		Runnable deleteRunnable = new Runnable() {
			@Override
			public void run() {
				mTasksDao.deleteTaskById(taskId);
			}
		};

		mAppExecutors.diskIO().execute(deleteRunnable);
	}

	@VisibleForTesting
	static void clearInstance(){
		INSTANCE = null;
	}
}
