package com.example.dagger2demo.mvp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.dagger2demo.BaseActivity;
import com.example.dagger2demo.R;
import com.example.dagger2demo.app.ComponentHolder;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements Contacts.ILoginView{

	@Inject
	LoginPresenterImpl loginPresenter;

	@Inject
	SharedPreferences sharedPreferences;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		//DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
		DaggerMainComponent.builder().mainModule(new MainModule(this)).appComponent(ComponentHolder.getAppComponent()).build().inject(this);
		Log.d("Leo", (sharedPreferences == null) ? "null" : "not null");
	}
}
