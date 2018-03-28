package com.example.utility.app;

import android.app.Application;

import com.example.utility.BuildConfig;
import com.example.utility.common.ReleaseTree;
import com.example.utility.common.ThreadInfoDebugTree;

import timber.log.Timber;

/**
 * Created by caoyouqiang on 18-3-28.
 */

public class MyApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		if (BuildConfig.DEBUG){
			Timber.plant(new ThreadInfoDebugTree());
			//Timber.plant(new Timber.DebugTree());
		}else {
			Timber.plant(new ReleaseTree());
		}
	}
}
