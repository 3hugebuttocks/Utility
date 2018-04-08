package com.example.utility.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.utility.R;
import com.example.utility.databinding.bean.ViewModel;

public class InverseBindingActivity extends AppCompatActivity {
	private static final String TAG = "Leo";
	ActivityInversebindingBinding binding;
	private ViewModel mViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final GestureDetector gestureDetector = createGestureDetector();

		binding = DataBindingUtil.setContentView(this, R.layout.activity_inversebinding);

		mViewModel = new ViewModel();
		binding.setModel(mViewModel);

		binding.philview.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;
			}
		});
	}

	private GestureDetector createGestureDetector() {
		// mGestureDetector用于监测用户在手机屏幕上的上滑和下滑事件。
		GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
				//处于顶部且用户再下拉
				if ((distanceY < 0) && (binding.philview.getScrollY() == 0)) {
					if (mViewModel.isRefreshing.get()) {
						Log.d(TAG, "加载中，请勿重复加载");
					} else {
						Log.d(TAG, "开始下拉刷新...");

						//执行下拉刷新/加载更多事务
						loadMore();
					}
				}

				return super.onScroll(e1, e2, distanceX, distanceY);
			}
		});

		return mGestureDetector;
	}

	private void loadMore() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				//通过设置布尔值改变View
				mViewModel.isRefreshing.set(true);

				try {
					//假设这里做了一个长时间的耗时操作
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				//通过设置布尔值改变View
				mViewModel.isRefreshing.set(false);
			}
		}).start();
	}
}
