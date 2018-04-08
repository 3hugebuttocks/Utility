package com.example.utility.databinding.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;

@InverseBindingMethods({@InverseBindingMethod(type = PhilView1.class, attribute = "refreshing", event = "refreshingAttrChanged")})
public class PhilView1 extends NestedScrollView {
	private static final String TAG = "Leo";
	private static boolean isRefreshing = false;

	public PhilView1(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	public static void setRefreshing(PhilView view, boolean refreshing){
		if (isRefreshing == refreshing){
			Log.d(TAG, "xxx");
			return;
		}else {
			Log.d(TAG, "setRefreshing " + refreshing);
			isRefreshing = refreshing;
		}
	}

	public static boolean getRefreshing(PhilView view){
		return isRefreshing;
	}

	public static void setRefreshingAttrChanged(PhilView view, final InverseBindingListener inverseBindingListener){
		if (inverseBindingListener == null){
			Log.e(TAG, "inverseBindingListener null");
		}else {
			inverseBindingListener.onChange();
		}
	}

}
