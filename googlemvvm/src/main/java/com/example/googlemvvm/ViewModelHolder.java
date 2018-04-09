package com.example.googlemvvm;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ViewModelHolder<VM> extends Fragment{
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

	@Nullable public VM getmViewModel(){
		return mViewModel;
	}

	public void setViewModel(@NonNull VM viewModel){
		mViewModel = viewModel;
	}
}
