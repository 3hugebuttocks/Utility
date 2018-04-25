package com.example.dagger2demo.subcomponent;

import javax.inject.Inject;

public class FirstPresenter implements IFirstPresenter {
	private HelperClass1 helperClass1;
	private HelperClass2 helperClass2;

	@Inject
	public FirstPresenter(HelperClass1 class1, HelperClass2 class2){
		this.helperClass1 = class1;
		this.helperClass2 = class2;
	}

	@Override
	public void foo() {

	}
}
