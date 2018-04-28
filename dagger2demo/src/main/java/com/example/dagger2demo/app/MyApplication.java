package com.example.dagger2demo.app;

import com.example.dagger2demo.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MyApplication extends DaggerApplication{
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
		return DaggerAppComponent.builder().application(this).builder();
	}
}
