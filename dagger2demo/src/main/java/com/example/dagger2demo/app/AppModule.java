package com.example.dagger2demo.app;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
	private MyApplication application;

	public AppModule(MyApplication application){
		this.application = application;
	}

	@Singleton
	@Provides
	SharedPreferences provideSharedPreferences(){
		return application.getSharedPreferences("spfile", Context.MODE_PRIVATE);
	}

	@Provides
	MyApplication provideApplication(){
		return application;
	}
}
