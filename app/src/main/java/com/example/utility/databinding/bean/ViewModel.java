package com.example.utility.databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

public class ViewModel extends BaseObservable{
	public final ObservableBoolean isRefreshing = new ObservableBoolean();
}
