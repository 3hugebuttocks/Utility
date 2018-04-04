package com.example.utility.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.utility.R;
import com.example.utility.databinding.Utils.BindUtils;
import com.example.utility.databinding.bean.Avantar;

public class DataBingdingMapActicity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityDbMapBinding dbMapBinding = DataBindingUtil.setContentView(this, R.layout.activity_db_map);

		final Avantar avantar = new Avantar();
		dbMapBinding.setAva(avantar);
		BindUtils utils = new BindUtils();
		dbMapBinding.setUtil(utils);

		avantar.id.set(0);
		avantar.name.set("Leo");
		avantar.quality.put("normal", "N url");
		avantar.quality.put("high", "H url");
		avantar.quality.put("super", "S url");
		avantar.current.set("normal");

		dbMapBinding.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				avantar.id.set(1);
				avantar.quality.put("high", "new H url");
				avantar.current.set("high");
			}
		});
	}

}
