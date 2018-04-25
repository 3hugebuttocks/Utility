package com.example.dagger2demo.app;

import android.content.SharedPreferences;

import com.example.dagger2demo.daggerandroid.MainActivityModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AppModule.class,
		MainActivityModule.class,
		AndroidInjectionModule.class})
public interface AppComponent {
	void inject(MyApplication app);

	SharedPreferences sharedPreferences();

	MyApplication myApplication();
}
