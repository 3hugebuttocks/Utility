package com.example.dagger2demo.di;

import com.example.dagger2demo.tasks.MainActivity;
import com.example.dagger2demo.tasks.TasksModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
	@ActivityScoped
	@ContributesAndroidInjector(modules = TasksModule.class)
	abstract MainActivity MainActivity();
}
