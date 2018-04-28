package com.example.dagger2demo.tasks;

import com.example.dagger2demo.BasePresenter;
import com.example.dagger2demo.BaseView;

public interface TaskContract {
	interface View extends BaseView<Presenter>{
		void showTasks();
	}

	interface Presenter extends BasePresenter<View>{
		void loadTasks();

		void takeView(View view);
	}
}
