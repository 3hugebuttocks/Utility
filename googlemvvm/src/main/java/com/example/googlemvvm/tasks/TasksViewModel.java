package com.example.googlemvvm.tasks;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;

import com.example.googlemvvm.R;
import com.example.googlemvvm.addedittask.AddEditTaskActivity;
import com.example.googlemvvm.data.Task;
import com.example.googlemvvm.data.source.TasksDataSource;
import com.example.googlemvvm.data.source.TasksRepository;
import com.example.googlemvvm.taskdetail.TaskDetailActivity;
import com.example.googlemvvm.util.EspressoIdlingResource;

import java.util.ArrayList;
import java.util.List;

public class TasksViewModel extends BaseObservable{
	public final ObservableList<Task> items = new ObservableArrayList<>();
	public final ObservableBoolean dataLoading = new ObservableBoolean(false);
	public final ObservableField<String> currentFilteringLabel = new ObservableField<>();
	public final ObservableField<String> noTasksLabel = new ObservableField<>();
	public final ObservableField<Drawable> noTaskIconRes = new ObservableField<>();
	public final ObservableBoolean tasksAddViewVisible = new ObservableBoolean();

	final ObservableField<String> snackbarText = new ObservableField<>();

	private TasksFilterType mCurrentFiltering = TasksFilterType.ALL_TASKS;

	private final TasksRepository mTasksRepository;

	private final ObservableBoolean mIsDataLoadingError = new ObservableBoolean(false);

	private Context mContext;

	private TasksNavigator mNavigotor;

	public TasksViewModel(TasksRepository repository, Context context){
		mContext = context.getApplicationContext();
		mTasksRepository = repository;

		setFiltering(TasksFilterType.ALL_TASKS);
	}

	void setNavigator(TasksNavigator navigator){
		mNavigotor = navigator;
	}

	void onActivityDestroyed(){
		mNavigotor = null;
	}

	public void start(){
		loadTasks(false);
	}

	@Bindable
	public boolean isEmpty(){
		return items.isEmpty();
	}

	private void loadTasks(boolean forceUpdate) {
		loadTasks(forceUpdate, true);
	}

	public void cleaCompletedTask(){
		mTasksRepository.clearCompletedTasks();
		snackbarText.set(mContext.getString(R.string.completed_tasks_cleared));
		loadTasks(false, false);
	}

	public String getSnackbarText(){
		return snackbarText.get();
	}

	public void addNewTask(){
		if (mNavigotor != null){
			mNavigotor.addNewTask();
		}
	}

	void handleActivityResult(int requestCode, int resultCode){
		if (AddEditTaskActivity.REQUEST_CODE == requestCode) {
			switch (resultCode) {
				case TaskDetailActivity.EDIT_RESULT_OK:
					snackbarText.set(
							mContext.getString(R.string.successfully_saved_task_message));
					break;
				case AddEditTaskActivity.ADD_EDIT_RESULT_OK:
					snackbarText.set(
							mContext.getString(R.string.successfully_added_task_message));
					break;
				case TaskDetailActivity.DELETE_RESULT_OK:
					snackbarText.set(
							mContext.getString(R.string.successfully_deleted_task_message));
					break;
			}
		}
	}

	private void loadTasks(boolean forceUpdate, final boolean showLoadingUI){
		if (showLoadingUI){
			dataLoading.set(true);
		}
		if (forceUpdate){
			mTasksRepository.refreshTasks();
		}

		EspressoIdlingResource.increment();

		mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
			@Override
			public void onTasksLoaded(List<Task> tasks) {
				List<Task> tasksToShow = new ArrayList<>();

				// This callback may be called twice, once for the cache and once for loading
				// the data from the server API, so we check before decrementing, otherwise
				// it throws "Counter has been corrupted!" exception.
				if (!EspressoIdlingResource.getIdlingResource().isIdleNow()){
					EspressoIdlingResource.decrement();
				}

				for (Task task : tasks) {
					switch (mCurrentFiltering) {
						case ALL_TASKS:
							tasksToShow.add(task);
							break;
						case ACTIVE_TASKS:
							if (task.isActive()) {
								tasksToShow.add(task);
							}
							break;
						case COMPLETED_TASKS:
							if (task.isCompleted()) {
								tasksToShow.add(task);
							}
							break;
						default:
							tasksToShow.add(task);
							break;
					}
				}

				if (showLoadingUI){
					dataLoading.set(false);
				}
				mIsDataLoadingError.set(false);

				items.clear();
				items.addAll(tasksToShow);
				notifyPropertyChanged(BR.empty);
			}

			@Override
			public void onDataNotAvailable() {
				mIsDataLoadingError.set(true);
			}
		});
	}

	private void setFiltering(TasksFilterType requestType) {
		mCurrentFiltering = requestType;

		switch (requestType){
			case ALL_TASKS:
				currentFilteringLabel.set(mContext.getString(R.string.label_all));
				noTasksLabel.set(mContext.getResources().getString(R.string.no_tasks_all));
				noTaskIconRes.set(mContext.getResources().getDrawable(
						R.drawable.ic_assignment_turned_in_24dp));
				tasksAddViewVisible.set(true);
				break;
			case ACTIVE_TASKS:
				currentFilteringLabel.set(mContext.getString(R.string.label_active));
				noTasksLabel.set(mContext.getResources().getString(R.string.no_tasks_active));
				noTaskIconRes.set(mContext.getResources().getDrawable(
						R.drawable.ic_check_circle_24dp));
				tasksAddViewVisible.set(false);
				break;
			case COMPLETED_TASKS:
				currentFilteringLabel.set(mContext.getString(R.string.label_completed));
				noTasksLabel.set(mContext.getResources().getString(R.string.no_tasks_completed));
				noTaskIconRes.set(mContext.getResources().getDrawable(
						R.drawable.ic_verified_user_24dp));
				tasksAddViewVisible.set(false);
				break;
		}
	}
}
