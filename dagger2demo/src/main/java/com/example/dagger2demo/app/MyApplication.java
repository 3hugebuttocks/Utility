package com.example.dagger2demo.app;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MyApplication extends Application implements HasActivityInjector/*, HasSupportFragmentInjector*/{
	@Inject
	DispatchingAndroidInjector<Activity> dispatchingActivityAndroidInjector;

	/*@Inject
	DispatchingAndroidInjector<Fragment> dispatchingFragmentAndroidInjector;*/

	@Override
	public void onCreate() {
		super.onCreate();
		inject();
	}

	private void inject(){
		AppComponent appComponent = DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.build();

		appComponent.inject(this);
		ComponentHolder.setAppComponent(appComponent);
	}

	@Override
	public AndroidInjector<Activity> activityInjector() {
		return dispatchingActivityAndroidInjector;
	}

	/*@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return dispatchingFragmentAndroidInjector;
	}*/
}
