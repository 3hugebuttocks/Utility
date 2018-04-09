package com.example.googlemvvm;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

public class SwipeRefreshLayoutDataBinding {
	@BindingAdapter("android:onRefresh")
	public static void setSwipeRefreshLayoutOnRefreshListener(ScrollChildSwipeRefreshLayout view,
					  final TaskViewModel viewModel){
		view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				//viewModel.loa
			}
		});
	}
}
