package com.example.dagger2demo.practice.daggerandroid;

import com.example.dagger2demo.tasks.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = MJModule.class)
public interface MainActivitySubComponent extends AndroidInjector<MainActivity>{

	@Subcomponent.Builder
	abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
