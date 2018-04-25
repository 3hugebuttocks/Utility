package com.example.dagger2demo.scopetest;

import com.example.dagger2demo.scopetest.scope.PerScope;

import dagger.Component;

@PerScope
@Component(modules = UserModule.class)
public interface UserComponent {
	//void inject(ScopeActivity activity);

	InfoComponent plus();
}
