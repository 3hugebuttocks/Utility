package com.example.unitestdemo.mockito;

import com.example.unitestdemo.share.NetworkCallback;

public class LoginPresenter {
	private UserManager mUserManager = new UserManager();
	private PasswordValidator mPasswordValidator = new PasswordValidator();

	public void login(String username, String password){
		if (username == null || username.length() == 0) return;

		if (mPasswordValidator.verifyPassword(password)) return;

		mUserManager.performLogin(username, password);
	}

	public void loginCallbackVersion(String username, String password){
		if (username == null || username.length() == 0) return;

		if (mPasswordValidator.verifyPassword(password)) return;

		mUserManager.performLogin(username, password, new NetworkCallback() {
			@Override
			public void onSuccess(Object data) {

			}

			@Override
			public void onFailure(int code, String msg) {

			}
		});
	}

	public void setmUserManager(UserManager userManager){
		this.mUserManager = userManager;
	}

	public void setmPasswordValidator(PasswordValidator passwordValidator){
		this.mPasswordValidator = passwordValidator;
	}
}
