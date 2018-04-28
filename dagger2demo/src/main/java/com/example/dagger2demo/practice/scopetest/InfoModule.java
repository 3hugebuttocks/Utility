package com.example.dagger2demo.practice.scopetest;

import com.example.dagger2demo.practice.scopetest.scope.SubScope;

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
