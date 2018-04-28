package com.example.dagger2demo.di;

import android.app.Application;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationModule {
	@Binds
	abstract Application bindApplication(Application application);
}
