package com.example.dagger2demo.practice.scopetest;

import com.example.dagger2demo.practice.scopetest.scope.PerScope;

import dagger.Component;

@PerScope
@Component(modules = UserModule.class)
public interface UserComponent {
	//void inject(ScopeActivity activity);

	InfoComponent plus();
}
