package com.example.googlemvvm.addedittask;

import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.googlemvvm.Injection;
import com.example.googlemvvm.R;
import com.example.googlemvvm.ViewModelHolder;
import com.example.googlemvvm.util.ActivityUtils;
import com.example.googlemvvm.util.EspressoIdlingResource;

public class AddEditTaskActivity extends AppCompatActivity implements AddEditTaskNavigator{
	public static final int REQUEST_CODE = 1;

	public static final int ADD_EDIT_RESULT_OK = RESULT_FIRST_USER + 1;

	public static final String ADD_EDIT_VIEWMODEL_TAG = "ADD_EDIT_VIEWMODEL_TAG";

	private AddEditTaskViewModel mViewModel;

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return true;
	}

	@VisibleForTesting
	public IdlingResource getCountingIdlingResource() {
		return EspressoIdlingResource.getIdlingResource();
	}

	@Override
	public void onTaskSaved() {
		setResult(ADD_EDIT_RESULT_OK);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtask_act);

		// Set up the toolbar.
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);

		AddEditTaskFragment addEditTaskFragment = findOrCreateViewFragment();

		mViewModel = findOrCreateViewModel();

		// Link View and ViewModel
		addEditTaskFragment.setViewModel(mViewModel);

		mViewModel.onActivityCreated(this);
	}

	@Override
	protected void onDestroy() {
		mViewModel.onActivityDestroyed();
		super.onDestroy();
	}

	@NonNull
	private AddEditTaskFragment findOrCreateViewFragment() {
		// View Fragment
		AddEditTaskFragment addEditTaskFragment = (AddEditTaskFragment) getSupportFragmentManager()
				.findFragmentById(R.id.contentFrame);

		if (addEditTaskFragment == null) {
			addEditTaskFragment = AddEditTaskFragment.newInstance();

			// Send the task ID to the fragment
			Bundle bundle = new Bundle();
			bundle.putString(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID,
					getIntent().getStringExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID));
			addEditTaskFragment.setArguments(bundle);

			ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
					addEditTaskFragment, R.id.contentFrame);
		}
		return addEditTaskFragment;
	}

	private AddEditTaskViewModel findOrCreateViewModel() {
		// In a configuration change we might have a ViewModel present. It's retained using the
		// Fragment Manager.
		@SuppressWarnings("unchecked")
		ViewModelHolder<AddEditTaskViewModel> retainedViewModel =
				(ViewModelHolder<AddEditTaskViewModel>) getSupportFragmentManager()
						.findFragmentByTag(ADD_EDIT_VIEWMODEL_TAG);

		if (retainedViewModel != null && retainedViewModel.getViewModel() != null) {
			// If the model was retained, return it.
			return retainedViewModel.getViewModel();
		} else {
			// There is no ViewModel yet, create it.
			AddEditTaskViewModel viewModel = new AddEditTaskViewModel(
					getApplicationContext(),
					Injection.provideTasksRepository(getApplicationContext()));

			// and bind it to this Activity's lifecycle using the Fragment Manager.
			ActivityUtils.addFragmentToActivity(
					getSupportFragmentManager(),
					ViewModelHolder.createContainer(viewModel),
					ADD_EDIT_VIEWMODEL_TAG);
			return viewModel;
		}
	}
}
