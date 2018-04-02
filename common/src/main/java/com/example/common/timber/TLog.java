package com.example.common.timber;

import com.example.common.BuildConfig;

import timber.log.Timber;

/**
 * Created by caoyouqiang on 18-4-2.
 */

public class TLog {
	public static void initWrappedFullTimber(){
		if (BuildConfig.DEBUG){
			Timber.plant(new ThreadInfoDebugTree());
			//Timber.plant(new Timber.DebugTree());
		}else {
			Timber.plant(new ReleaseTree());
		}
	}

	public static void initWrappedDebugTimber(){
		Timber.plant(new ThreadInfoDebugTree());
	}

	public static void initOrignalDebugTimber(){
		Timber.plant(new Timber.DebugTree());
	}
}
