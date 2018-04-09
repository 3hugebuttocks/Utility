package com.example.googlemvvm.taskdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.googlemvvm.R;

public class TaskDetailActivity extends AppCompatActivity {
	public static final String EXTRA_TASK_ID = "TASK_ID";

	public static final String TASKDETAIL_VIEWMODEL_TAG = "TASKDETAIL_VIEWMODEL_TAG";

	public static final int DELETE_RESULT_OK = RESULT_FIRST_USER + 2;

	public static final int EDIT_RESULT_OK = RESULT_FIRST_USER + 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taskdetail_act);
	}
}
