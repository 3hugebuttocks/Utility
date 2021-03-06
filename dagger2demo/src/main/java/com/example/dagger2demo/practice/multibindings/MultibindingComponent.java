package com.example.dagger2demo.practice.multibindings;

import java.util.Map;

import dagger.Component;

@Component(modules = MultibindingModule.class)
public interface MultibindingComponent {
	Map<String, String> aMap();

	void inject(MultiBindingActivity activity);
}
