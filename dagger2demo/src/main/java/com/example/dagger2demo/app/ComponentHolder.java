package com.example.dagger2demo.app;

public class ComponentHolder {
	private static AppComponent myAppComponent;

	public static void setAppComponent(AppComponent component){
		myAppComponent = component;
	}

	public static AppComponent getAppComponent(){
		return myAppComponent;
	}
}
