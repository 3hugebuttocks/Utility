package com.example.constraintlayoutdemo;

import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
    }

    public void onGoneClick(View view){
        Group group = findViewById(R.id.group);
        if (group.isShown()) {
            group.setVisibility(View.GONE);
        }else {
            group.setVisibility(View.VISIBLE);
        }
    }
}
