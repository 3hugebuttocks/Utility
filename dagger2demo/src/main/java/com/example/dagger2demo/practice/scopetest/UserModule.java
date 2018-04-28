package com.example.dagger2demo.practice.scopetest;

import com.example.dagger2demo.practice.scopetest.scope.PerScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
	@PerScope
	@Provides
	User providerModule(){
		return new User("", 0);
	}
}
