package com.example.dagger2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import dagger.android.support.DaggerAppCompatActivity;


public class BaseActivity extends DaggerAppCompatActivity{
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
