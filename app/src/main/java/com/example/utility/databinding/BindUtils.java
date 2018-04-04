package com.example.utility.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.utility.R;

/**
 * Created by caoyouqiang on 18-4-3.
 */

public class BindUtils {
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
}
