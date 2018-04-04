package com.example.utility.databinding.view;

import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Toast;

/**
 * Created by caoyouqiang on 18-4-4.
 */

@BindingMethods({@BindingMethod(type = AppCompatTextView.class, attribute = "texttoast", method = "showPhiToast")})
public class PhilTextView extends android.support.v7.widget.AppCompatTextView {
	public PhilTextView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public void showPhiToast(String s) {
		if (TextUtils.isEmpty(s)) {
			return;
		}

		Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
	}
}
