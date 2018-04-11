package com.example.googlemvvm;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.googlemvvm.tasks.TasksViewModel;

public class SwipeRefreshLayoutDataBinding {
	@BindingAdapter("android:onRefresh")
	public static void setSwipeRefreshLayoutOnRefreshListener(ScrollChildSwipeRefreshLayout view,
															  final TasksViewModel viewModel){
		view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				viewModel.loadTasks(true);
			}
		});
	}
}
