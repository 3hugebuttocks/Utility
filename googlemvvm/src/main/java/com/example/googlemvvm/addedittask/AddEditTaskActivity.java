package com.example.googlemvvm.addedittask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.googlemvvm.R;

public class AddEditTaskActivity extends AppCompatActivity {
	public static final int REQUEST_CODE = 1;

	public static final int ADD_EDIT_RESULT_OK = RESULT_FIRST_USER + 1;

	public static final String ADD_EDIT_VIEWMODEL_TAG = "ADD_EDIT_VIEWMODEL_TAG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtask_act);
	}
}
