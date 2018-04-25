package com.example.dagger2demo.scopetest;

import com.example.dagger2demo.scopetest.scope.SubScope;

import dagger.Module;
import dagger.Provides;

@Module
public class InfoModule {
	@Provides
	@SubScope
	Info providerInfo(){
		return new Info("I am sorry!");
	}
}
