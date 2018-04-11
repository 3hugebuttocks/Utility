package com.example.googlemvvm.addedittask;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googlemvvm.R;
import com.example.googlemvvm.databinding.AddtaskFragBinding;
import com.example.googlemvvm.util.SnackbarUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddEditTaskFragment extends Fragment{
	public static final String ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID";

	private AddEditTaskViewModel mViewModel;

	private AddtaskFragBinding mViewDataBinding;

	private Observable.OnPropertyChangedCallback mSnackbarCallback;

	public static AddEditTaskFragment newInstance() {
		return new AddEditTaskFragment();
	}

	public AddEditTaskFragment() {
		// Required empty public constructor
	}

	@Override
	public void onResume() {
		super.onResume();
		if (getArguments() != null) {
			mViewModel.start(getArguments().getString(ARGUMENT_EDIT_TASK_ID));
		} else {
			mViewModel.start(null);
		}
	}

	public void setViewModel(@NonNull AddEditTaskViewModel viewModel) {
		mViewModel = checkNotNull(viewModel);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setupFab();

		setupSnackbar();

		setupActionBar();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		final View root = inflater.inflate(R.layout.addtask_frag, container, false);
		if (mViewDataBinding == null) {
			mViewDataBinding = AddtaskFragBinding.bind(root);
		}

		mViewDataBinding.setViewmodel(mViewModel);

		setHasOptionsMenu(true);
		setRetainInstance(false);

		return mViewDataBinding.getRoot();
	}

	@Override
	public void onDestroy() {
		if (mSnackbarCallback != null) {
			mViewModel.snackbarText.removeOnPropertyChangedCallback(mSnackbarCallback);
		}
		super.onDestroy();
	}

	private void setupSnackbar() {
		mSnackbarCallback = new Observable.OnPropertyChangedCallback() {
			@Override
			public void onPropertyChanged(Observable observable, int i) {
				SnackbarUtils.showSnackbar(getView(), mViewModel.getSnackbarText());
			}
		};
		mViewModel.snackbarText.addOnPropertyChangedCallback(mSnackbarCallback);
	}

	private void setupFab() {
		FloatingActionButton fab =
				(FloatingActionButton) getActivity().findViewById(R.id.fab_edit_task_done);
		fab.setImageResource(R.drawable.ic_done);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mViewModel.saveTask();
			}
		});
	}

	private void setupActionBar() {
		ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
		if (actionBar == null) {
			return;
		}
		if (getArguments().get(ARGUMENT_EDIT_TASK_ID) != null) {
			actionBar.setTitle(R.string.edit_task);
		} else {
			actionBar.setTitle(R.string.add_task);
		}
	}
}
