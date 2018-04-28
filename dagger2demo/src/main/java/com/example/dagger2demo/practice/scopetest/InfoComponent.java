package com.example.dagger2demo.practice.scopetest;

import com.example.dagger2demo.practice.scopetest.scope.SubScope;

import dagger.Subcomponent;

@SubScope
@Subcomponent(modules = InfoModule.class)
public interface InfoComponent {
	void inject(ScopeActivity activity);
}
