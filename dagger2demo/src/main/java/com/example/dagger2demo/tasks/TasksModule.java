package com.example.dagger2demo.tasks;

import com.example.dagger2demo.di.ActivityScoped;
import com.example.dagger2demo.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TasksModule {
	@FragmentScoped
	@ContributesAndroidInjector
	abstract TaskFragment taskFragment();

	@ActivityScoped
	@Binds abstract TaskContract.Presenter taskPresenter(TasksPresenter presenter);
}
