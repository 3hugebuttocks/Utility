package com.example.utility.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utility.R;
import com.example.utility.databinding.ActivityDbBinding;
import com.example.utility.databinding.bean.Avantar;
import com.example.utility.databinding.bean.User;

public class DataBindingActivity extends AppCompatActivity implements View.OnClickListener{
	Button mBtnChange;
	User mUser = new User();
	Avantar mAva = new Avantar();
	private String name = "leon";
	private int id = 1;
	private String blog = "github.com";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_db);
		ActivityDbBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_db);
		binding.setUser(mUser);
		binding.setAva(mAva);

		mBtnChange = findViewById(R.id.btn_change);
		mBtnChange.setOnClickListener(this);

		final TextView idView = binding.id;
		final TextView nameView = binding.name;
		final TextView blogView = binding.blog;

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (id == 5) {
								idView.setText("leo");
								nameView.setText("leo");
								blogView.setText("leo");
							} else {
								mUser.setId(String.valueOf(id++) + " " + System.currentTimeMillis());
								mUser.setName(name + " " + System.currentTimeMillis());
								mUser.setBlog(blog + " " + System.currentTimeMillis());
							}
						}
					});

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_change:
				mUser.setName("cyq");
				mAva.url.set("https://www.baidu.com/img/bd_logo1.png");
				break;
		}
	}
}
