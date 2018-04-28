package com.example.dagger2demo.practice.multibindings;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public class MultibindingModule {
	@Provides
	@IntoMap
	@StringKey("a")
	String provideAValue(){
		return "a value";
	}

	@Provides
	@IntoMap
	@StringKey("b")
	String provideBValue(){
		return "b value";
	}
}
