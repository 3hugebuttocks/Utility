package com.example.dagger2demo.daggerandroid;

import com.example.dagger2demo.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = MJModule.class)
public interface MainActivitySubComponent extends AndroidInjector<MainActivity>{

	@Subcomponent.Builder
	abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
