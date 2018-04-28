package com.example.dagger2demo.practice.producer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dagger2demo.R;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AsyActivity extends AppCompatActivity {
	@Inject
	Observable<HeavyExternalLibrary> heavyExternalLibraryObservable;

	HeavyExternalLibrary heavyExternalLibrary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asy);

		DaggerAsyComponent.create().inject(this);

		heavyExternalLibraryObservable.subscribe(heavyExternalLibrary1 -> heavyExternalLibrary = heavyExternalLibrary1
				, throwable -> {});

		heavyExternalLibraryObservable.subscribe(heavyExternalLibrary1 -> heavyExternalLibrary = heavyExternalLibrary1
				, throwable -> {});
	}
}
