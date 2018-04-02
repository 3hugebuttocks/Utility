package com.example.utility.app;

import android.app.Application;

import com.example.common.timber.TLog;

/**
 * Created by caoyouqiang on 18-3-28.
 */

public class MyApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		TLog.initWrappedDebugTimber();
	}
}
