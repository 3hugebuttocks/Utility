package com.example.googlemvvm.tasks;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import com.example.googlemvvm.data.Task;

import java.util.List;

public class TasksListBindings {
	@SuppressWarnings("unchecked")
	@BindingAdapter("items")
	public static void setItems(ListView listView, List<Task> items){
		TasksFragment.TasksAdapter adapter = (TasksFragment.TasksAdapter) listView.getAdapter();
		if (adapter != null){
			adapter.replaceData(items);
		}
	}
}
