package com.example.dagger2demo.daggerandroid;

import dagger.Module;
import dagger.Provides;

@Module
public class MJModule {

	@ActivityScope
	@Provides
	public Person provideMj(){
		return new Person("Macheal jackson", 50);
	}
}
