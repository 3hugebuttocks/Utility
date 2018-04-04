package com.example.utility.databinding.Utils;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.utility.R;
import com.example.utility.databinding.bean.Avantar;

import timber.log.Timber;

/**
 * Created by caoyouqiang on 18-4-3.
 */

public class BindUtils {
	public BindUtils(){}

	@BindingAdapter({"imageUrl"})
	public static void loadImage(ImageView view, String u) {
		RequestOptions options = new RequestOptions()
				.centerCrop()
				.placeholder(R.mipmap.ic_launcher_round)
				.error(R.mipmap.ic_launcher)
				.priority(Priority.HIGH)
				.diskCacheStrategy(DiskCacheStrategy.NONE);

		Glide.with(view.getContext()).applyDefaultRequestOptions(options).load(u).transition(new DrawableTransitionOptions().crossFade(1000)).into(view);
		Toast.makeText(view.getContext(), "change url", Toast.LENGTH_LONG);
	}

	public static String displayPassowrdText(String text) {
		if (text == null) {
			return null;
		}

		String s = "";
		for (int i = 0; i < text.length(); i++) {
			s = s + "*";
		}

		return s;
	}

	public void onMyClick(Avantar ava){
		Timber.d(ava.content.get() + "");
	}

}
