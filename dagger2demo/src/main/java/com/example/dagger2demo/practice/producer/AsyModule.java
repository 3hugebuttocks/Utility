package com.example.dagger2demo.practice.producer;

import android.util.Log;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class AsyModule {

	@Provides
	HeavyExternalLibrary provideHeavyExternalLibrary(){
		Log.d("Leo", "provideHeavyExternalLibrary...");
		HeavyExternalLibrary heavyExternalLibrary = new HeavyExternalLibrary();
		heavyExternalLibrary.init();
		return heavyExternalLibrary;
	}

	@Provides
	@Singleton
	Observable<HeavyExternalLibrary> provideHeavyExternalLibraryObservable(final Provider<HeavyExternalLibrary> heavyExternalLibraryLazy){
		Log.d("Leo", "provideHeavyExternalLibraryObservable...");
		return Observable.fromCallable(heavyExternalLibraryLazy::get)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}
}
