package com.example.dagger2demo.data;

import android.support.annotation.NonNull;

public interface TaskDataSource {
	interface LoadTaskCallback{
		void onTaskLoaded();
		void onDataNotAvailable();
	}

	void getTasks(@NonNull LoadTaskCallback callback);
}
