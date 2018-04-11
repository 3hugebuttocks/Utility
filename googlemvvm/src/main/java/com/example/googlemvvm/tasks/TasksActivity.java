package com.example.googlemvvm.tasks;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.IdlingResource;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.googlemvvm.Injection;
import com.example.googlemvvm.R;
import com.example.googlemvvm.ViewModelHolder;
import com.example.googlemvvm.addedittask.AddEditTaskActivity;
import com.example.googlemvvm.statistics.StatisticsActivity;
import com.example.googlemvvm.taskdetail.TaskDetailActivity;
import com.example.googlemvvm.util.ActivityUtils;
import com.example.googlemvvm.util.EspressoIdlingResource;

public class TasksActivity extends AppCompatActivity implements TaskItemNavigator, TasksNavigator{

	private DrawerLayout mDrawerLayout;

	public static final String TASKS_VIEWMODEL_TAG = "TASKS_VIEWMODEL_TAG";

	private TasksViewModel mViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasks_act);

		setupToolbar();

		setupNavigationDrawer();

		TasksFragment tasksFragment = findOrCreateViewFragment();

		mViewModel = findOrCreateViewModel();
		mViewModel.setNavigator(this);

		// Link View and ViewModel
		tasksFragment.setViewModel(mViewModel);
	}

	@Override
	protected void onDestroy() {
		mViewModel.onActivityDestroyed();
		super.onDestroy();
	}

	private TasksViewModel findOrCreateViewModel() {
		// In a configuration change we might have a ViewModel present. It's retained using the
		// Fragment Manager.
		@SuppressWarnings("unchecked")
		ViewModelHolder<TasksViewModel> retainedViewModel =
				(ViewModelHolder<TasksViewModel>) getSupportFragmentManager()
						.findFragmentByTag(TASKS_VIEWMODEL_TAG);

		if (retainedViewModel != null && retainedViewModel.getViewModel() != null) {
			// If the model was retained, return it.
			return retainedViewModel.getViewModel();
		} else {
			// There is no ViewModel yet, create it.
			TasksViewModel viewModel = new TasksViewModel(
					Injection.provideTasksRepository(getApplicationContext()),
					getApplicationContext());
			// and bind it to this Activity's lifecycle using the Fragment Manager.
			ActivityUtils.addFragmentToActivity(
					getSupportFragmentManager(),
					ViewModelHolder.createContainer(viewModel),
					TASKS_VIEWMODEL_TAG);
			return viewModel;
		}
	}

	@NonNull
	private TasksFragment findOrCreateViewFragment() {
		TasksFragment tasksFragment =
				(TasksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
		if (tasksFragment == null) {
			// Create the fragment
			tasksFragment = TasksFragment.newInstance();
			ActivityUtils.addFragmentToActivity(
					getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
		}
		return tasksFragment;
	}

	private void setupToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar ab = getSupportActionBar();
		ab.setHomeAsUpIndicator(R.drawable.ic_menu);
		ab.setDisplayHomeAsUpEnabled(true);
	}

	private void setupNavigationDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		if (navigationView != null) {
			setupDrawerContent(navigationView);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				// Open the navigation drawer when the home icon is selected from the toolbar.
				mDrawerLayout.openDrawer(GravityCompat.START);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setupDrawerContent(NavigationView navigationView) {
		navigationView.setNavigationItemSelectedListener(
				new NavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(MenuItem menuItem) {
						switch (menuItem.getItemId()) {
							case R.id.list_navigation_menu_item:
								// Do nothing, we're already on that screen
								break;
							case R.id.statistics_navigation_menu_item:
								Intent intent =
										new Intent(TasksActivity.this, StatisticsActivity.class);
								startActivity(intent);
								break;
							default:
								break;
						}
						// Close the navigation drawer when an item is selected.
						menuItem.setChecked(true);
						mDrawerLayout.closeDrawers();
						return true;
					}
				});
	}

	@VisibleForTesting
	public IdlingResource getCountingIdlingResource() {
		return EspressoIdlingResource.getIdlingResource();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		mViewModel.handleActivityResult(requestCode, resultCode);
	}

	@Override
	public void openTaskDetails(String taskId) {
		Intent intent = new Intent(this, TaskDetailActivity.class);
		intent.putExtra(TaskDetailActivity.EXTRA_TASK_ID, taskId);
		startActivityForResult(intent, AddEditTaskActivity.REQUEST_CODE);

	}

	@Override
	public void addNewTask() {
		Intent intent = new Intent(this, AddEditTaskActivity.class);
		startActivityForResult(intent, AddEditTaskActivity.REQUEST_CODE);
	}
}
