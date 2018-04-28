package com.example.dagger2demo.di;

import android.app.Application;

import com.example.dagger2demo.app.MyApplication;
import com.example.dagger2demo.data.TaskRepository;
import com.example.dagger2demo.data.TaskRepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {TaskRepositoryModule.class,
		ApplicationModule.class,
		ActivityBindingModule.class,
		AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<MyApplication>{
	TaskRepository getTasksRepository();

	@Component.Builder
	interface Builder{
		@BindsInstance
		AppComponent.Builder application(Application application);

		AppComponent builder();
	}
}
