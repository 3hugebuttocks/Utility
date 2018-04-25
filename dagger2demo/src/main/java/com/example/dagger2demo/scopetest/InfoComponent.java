package com.example.dagger2demo.scopetest;

import com.example.dagger2demo.scopetest.scope.SubScope;

import dagger.Component;
import dagger.Subcomponent;

@SubScope
@Subcomponent(modules = InfoModule.class)
public interface InfoComponent {
	void inject(ScopeActivity activity);
}
