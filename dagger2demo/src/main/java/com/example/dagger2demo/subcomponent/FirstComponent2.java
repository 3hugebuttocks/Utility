package com.example.dagger2demo.subcomponent;

import dagger.Subcomponent;

@Subcomponent(modules = FirstModule.class)
public interface FirstComponent2 {
	@Subcomponent.Builder
	interface Builder{
		FirstComponent2.Builder firstModule(FirstModule firstModule);
		FirstComponent2 build();
	}
}
