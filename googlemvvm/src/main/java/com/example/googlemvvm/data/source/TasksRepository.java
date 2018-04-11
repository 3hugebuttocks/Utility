package com.example.googlemvvm.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.googlemvvm.data.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.support.v4.util.Preconditions.checkNotNull;

public class TasksRepository implements TasksDataSource {
	private static TasksRepository INSTANCE = null;
	private final TasksDataSource mTasksRemoteDataSource;
	private final TasksDataSource mTasksLocalDataSource;

	Map<String, Task> mCachedTasks;

	boolean mCacheIsDirty = false;

	private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,
							@NonNull TasksDataSource tasksLocalDataSource) {
		mTasksRemoteDataSource = checkNotNull(tasksRemoteDataSource);
		mTasksLocalDataSource = checkNotNull(tasksLocalDataSource);
	}

	public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,
											  TasksDataSource tasksLocalDataSource) {
		if (INSTANCE == null) {
			INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
		}
		return INSTANCE;
	}

    /**
     * Used to force {@link #getInstance(TasksDataSource, TasksDataSource)} to create a new instance
     * next time it's called.
     */


    /**
     * Gets tasks from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     * <p>
     * Note: {@link LoadTasksCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCachedTasks != null && !mCacheIsDirty) {
            callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getTasksFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mTasksLocalDataSource.getTasks(new LoadTasksCallback() {
                @Override
                public void onTasksLoaded(List<Task> tasks) {
                    refreshCache(tasks);
                    callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
                }

				@Override
				public void onDataNotAvailable() {
					getTasksFromRemoteDataSource(callback);
				}
			});
		}
	}

	@Override
	public void saveTask(@NonNull Task task) {
		checkNotNull(task);
		mTasksRemoteDataSource.saveTask(task);
		mTasksLocalDataSource.saveTask(task);

		if (mCachedTasks == null){
			mCachedTasks = new LinkedHashMap<>();
		}
		mCachedTasks.put(task.getId(), task);
	}

	@Override
	public void completeTask(@NonNull Task task) {
		checkNotNull(task);
		mTasksRemoteDataSource.completeTask(task);
		mTasksLocalDataSource.completeTask(task);

		Task completedTask = new Task(task.getTitle(), task.getDescription(), task.getId(), true);

		if (mCachedTasks == null) {
			mCachedTasks = new LinkedHashMap<>();
		}
		mCachedTasks.put(task.getId(), completedTask);
	}

	@Override
	public void completeTask(@NonNull String taskId) {
		checkNotNull(taskId);
		completeTask(getTaskWithId(taskId));
	}

	@Override
	public void activateTask(@NonNull Task task) {
		checkNotNull(task);
		mTasksRemoteDataSource.activateTask(task);
		mTasksLocalDataSource.activateTask(task);

		//why?
		Task activeTask = new Task(task.getTitle(), task.getDescription(), task.getId());

		// Do in memory cache update to keep the app UI up to date
		if (mCachedTasks == null) {
			mCachedTasks = new LinkedHashMap<>();
		}
		mCachedTasks.put(task.getId(), activeTask);
	}

    @Override
    public void activateTask(@NonNull String taskId) {
        checkNotNull(taskId);
        activateTask(getTaskWithId(taskId));
    }

    @Override
    public void clearCompletedTasks() {
        mTasksRemoteDataSource.clearCompletedTasks();
        mTasksLocalDataSource.clearCompletedTasks();

		if (mCachedTasks == null){
			mCachedTasks = new LinkedHashMap<>();
		}

		Iterator<Map.Entry<String, Task>> it = mCachedTasks.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry<String, Task> entry = it.next();
			if (entry.getValue().isCompleted()){
				it.remove();
			}
		}
	}

    /**
     * Gets tasks from local data source (sqlite) unless the table is new or empty. In that case it
     * uses the network data source. This is done to simplify the sample.
     * <p>
     * Note: {@link GetTaskCallback#onDataNotAvailable()} is fired if both data sources fail to
     * get the data.
     */
    @Override
    public void getTask(@NonNull final String taskId, @NonNull final GetTaskCallback callback) {
        checkNotNull(taskId);
        checkNotNull(callback);

        Task cachedTask = getTaskWithId(taskId);

        // Respond immediately with cache if available
        if (cachedTask != null) {
            callback.onTaskLoaded(cachedTask);
            return;
        }

        // Load from server/persisted if needed.

        // Is the task in the local data source? If not, query the network.
        mTasksLocalDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                // Do in memory cache update to keep the app UI up to date
                if (mCachedTasks == null) {
                    mCachedTasks = new LinkedHashMap<>();
                }
                mCachedTasks.put(task.getId(), task);
                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable() {
                mTasksRemoteDataSource.getTask(taskId, new GetTaskCallback() {
                    @Override
                    public void onTaskLoaded(Task task) {
                        // Do in memory cache update to keep the app UI up to date
                        if (mCachedTasks == null) {
                            mCachedTasks = new LinkedHashMap<>();
                        }
                        mCachedTasks.put(task.getId(), task);
                        callback.onTaskLoaded(task);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });
            }
        });
    }

	@Override
	public void refreshTasks() {
		mCacheIsDirty = true;
	}

	@Override
	public void deleteAllTasks() {
		mTasksRemoteDataSource.deleteAllTasks();
		mTasksLocalDataSource.deleteAllTasks();

		if (mCachedTasks == null) {
			mCachedTasks = new LinkedHashMap<>();
		}
		mCachedTasks.clear();
	}

	@Override
	public void deleteTask(@NonNull String taskId) {
		mTasksRemoteDataSource.deleteTask(checkNotNull(taskId));
		mTasksLocalDataSource.deleteTask(checkNotNull(taskId));

		mCachedTasks.remove(taskId);
	}

    private void getTasksFromRemoteDataSource(@NonNull final LoadTasksCallback callback) {
        mTasksRemoteDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                refreshCache(tasks);
                refreshLocalDataSource(tasks);
                callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
            }

			@Override
			public void onDataNotAvailable() {
				callback.onDataNotAvailable();
			}
		});
	}

	private void refreshCache(List<Task> tasks){
		if (mCachedTasks == null){
			mCachedTasks = new LinkedHashMap<>();
		}
		mCachedTasks.clear();
		for (Task task : tasks){
			mCachedTasks.put(task.getId(), task);
		}
		mCacheIsDirty = false;
	}

	private void refreshLocalDataSource(List<Task> tasks){
		mTasksLocalDataSource.deleteAllTasks();
		for (Task task : tasks){
			mTasksLocalDataSource.saveTask(task);
		}
	}

	@Nullable
	private Task getTaskWithId(@NonNull String id){
		checkNotNull(id);
		if (mCachedTasks == null || mCachedTasks.isEmpty()){
			return null;
		}else {
			return mCachedTasks.get(id);
		}
	}
}
