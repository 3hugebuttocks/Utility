package com.example.dagger2demo.daggerandroid;

import android.app.Activity;

import com.example.dagger2demo.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = MainActivitySubComponent.class)
public abstract class MainActivityModule {
	@Binds
	@IntoMap
	@ActivityKey(MainActivity.class)
	abstract AndroidInjector.Factory<? extends Activity> bindActivityInjectorFactory(MainActivitySubComponent.Builder builder);
}
