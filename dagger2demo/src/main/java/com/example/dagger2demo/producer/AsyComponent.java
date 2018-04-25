package com.example.dagger2demo.producer;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AsyModule.class)
public interface AsyComponent {
	void inject(AsyActivity asyActivity);
}
