package com.example.dagger2demo.scopetest;

import com.example.dagger2demo.scopetest.scope.PerScope;

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
