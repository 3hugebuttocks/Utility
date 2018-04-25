package com.example.dagger2demo.mvp;

import com.example.dagger2demo.MainActivity;
import com.example.dagger2demo.app.AppComponent;

import dagger.Component;

@MainScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
	void inject(LoginActivity activity);
}
