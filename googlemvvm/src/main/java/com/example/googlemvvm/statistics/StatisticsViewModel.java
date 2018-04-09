package com.example.googlemvvm.statistics;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.annotation.VisibleForTesting;

import com.example.googlemvvm.R;
import com.example.googlemvvm.data.Task;
import com.example.googlemvvm.data.source.TasksDataSource;
import com.example.googlemvvm.data.source.TasksRepository;
import com.example.googlemvvm.util.EspressoIdlingResource;

import java.util.List;

public class StatisticsViewModel extends BaseObservable{
	public final ObservableBoolean dataLoading = new ObservableBoolean(false);

	final ObservableBoolean error = new ObservableBoolean(false);

	@VisibleForTesting
	int mNumberOfActiveTasks = 0;

	@VisibleForTesting
	int mNumberOfCompletedTasks = 0;

	private Context mContext;

	private final TasksRepository mTasksRepository;

	public StatisticsViewModel(Context context, TasksRepository tasksRepository) {
		mContext = context;
		mTasksRepository = tasksRepository;
	}

	public void start() {
		loadStatistics();
	}

	public void loadStatistics() {
		dataLoading.set(true);

		// The network request might be handled in a different thread so make sure Espresso knows
		// that the app is busy until the response is handled.
		EspressoIdlingResource.increment(); // App is busy until further notice

		mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
			@Override
			public void onTasksLoaded(List<Task> tasks) {

				// This callback may be called twice, once for the cache and once for loading
				// the data from the server API, so we check before decrementing, otherwise
				// it throws "Counter has been corrupted!" exception.
				if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
					EspressoIdlingResource.decrement(); // Set app as idle.
				}

				computeStats(tasks);
			}

			@Override
			public void onDataNotAvailable() {
				error.set(true);
			}
		});
	}

	/**
	 * Returns a String showing the number of active tasks.
	 */
	@Bindable
	public String getNumberOfActiveTasks() {
		return mContext.getString(R.string.statistics_active_tasks, mNumberOfActiveTasks);
	}

	/**
	 * Returns a String showing the number of completed tasks.
	 */
	@Bindable
	public String getNumberOfCompletedTasks() {
		return mContext.getString(R.string.statistics_completed_tasks, mNumberOfCompletedTasks);
	}

	/**
	 * Controls whether the stats are shown or a "No data" message.
	 */
	@Bindable
	public boolean isEmpty() {
		return mNumberOfActiveTasks + mNumberOfCompletedTasks == 0;
	}

	/**
	 * Called when new data is ready.
	 */
	private void computeStats(List<Task> tasks) {
		int completed = 0;
		int active = 0;

		for (Task task : tasks) {
			if (task.isCompleted()) {
				completed += 1;
			} else {
				active += 1;
			}
		}
		mNumberOfActiveTasks = active;
		mNumberOfCompletedTasks = completed;

		// There are multiple @Bindable fields in this ViewModel, calling notifyChange() will
		// update all the UI elements that depend on them.
		notifyChange();

		// To update just one of them and avoid unnecessary UI updates,
		// use notifyPropertyChanged(BR.field)

		// Observable fields don't need to be notified. set() will trigger an update.
		dataLoading.set(false);
		error.set(false);
	}
}
