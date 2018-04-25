package com.example.dagger2demo.subcomponent;

import dagger.Component;

@Component(modules = SuperModule.class)
public interface SuperComponent {
	FirstComponent plusFirstComponent(FirstModule firstModule);
}
