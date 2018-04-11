package com.example.googlemvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ViewModelHolder<VM> extends android.support.v4.app.Fragment{
	private VM mViewModel;

	public  ViewModelHolder(){}

	public static <M> ViewModelHolder createContainer(@NonNull M viewModel){
		ViewModelHolder<M> viewModelContainer = new ViewModelHolder<>();
		viewModelContainer.setViewModel(viewModel);
		return viewModelContainer;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Nullable public VM getViewModel(){
		return mViewModel;
	}

	public void setViewModel(@NonNull VM viewModel){
		mViewModel = viewModel;
	}
}
