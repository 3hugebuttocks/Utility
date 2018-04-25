package com.example.dagger2demo.mvp;

import javax.inject.Inject;

import dagger.Reusable;
import dagger.releasablereferences.CanReleaseReferences;

@Reusable
public class LoginPresenterImpl implements Contacts.ILoginPresenter{
	private Contacts.ILoginView loginView;

	@Inject
	public LoginPresenterImpl(Contacts.ILoginView loginView){
		this.loginView = loginView;
	}
}
