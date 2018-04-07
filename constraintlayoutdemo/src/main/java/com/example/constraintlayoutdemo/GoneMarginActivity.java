package com.example.constraintlayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Gone隐藏掉的控件，会被解析成一个点，并忽略margin。
 */
public class GoneMarginActivity extends AppCompatActivity {
    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gone_margin);

        mBtn = findViewById(R.id.button);
    }

    public void setGone(View view){
        mBtn.setVisibility(View.GONE);
    }

    public void setVisiable(View view){
        mBtn.setVisibility(View.VISIBLE);
    }
}
