package com.example.dagger2demo.mvp;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
	private final Contacts.ILoginView view;

	public MainModule(Contacts.ILoginView view){
		this.view = view;
	}

	@Provides
	Contacts.ILoginView providerILogView(){
		return view;
	}
}
