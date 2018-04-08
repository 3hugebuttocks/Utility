package com.example.utility.databinding.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;


public class PhilView extends NestedScrollView{
	private static final String TAG = "Leo";
	private static boolean isRefreshing = false;

	public PhilView(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	@BindingAdapter(value = "refreshing", requireAll = false)
	public static void setRefreshing(PhilView view, boolean refreshing){
		if (isRefreshing == refreshing){
			Log.d(TAG, "xxx");
			return;
		}else {
			Log.d(TAG, "setRefreshing " + refreshing);
			isRefreshing = refreshing;
		}
	}

	@InverseBindingAdapter(attribute = "refreshing", event = "refreshingAttrChanged")
	public static boolean getRefreshing(PhilView view){
		return isRefreshing;
	}

	@BindingAdapter(value = {"refreshingAttrChanged"}, requireAll = false)
	public static void setRefreshingAttrChanged(PhilView view, final InverseBindingListener inverseBindingListener){
		if (inverseBindingListener == null){
			Log.e(TAG, "inverseBindingListener null");
		}else {
			inverseBindingListener.onChange();
		}
	}

}
