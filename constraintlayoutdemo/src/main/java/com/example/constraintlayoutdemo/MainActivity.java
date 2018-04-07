package com.example.constraintlayoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGoneMarginClick(View view){
        startActivity(new Intent(this, GoneMarginActivity.class));
    }

    public void onBiasClick(View view){
        startActivity(new Intent(this, BiasActivity.class));
    }

    public void onDimenClick(View view){
        startActivity(new Intent(this, DimenActivity.class));
    }

    public void onRatioClick(View view){
        startActivity(new Intent(this, RatioActivity.class));
    }

    public void onChainClick(View view){
        startActivity(new Intent(this, ChainActivity.class));
    }

    public void onPercentClick(View view){
        startActivity(new Intent(this, PercentDimenActivity.class));
    }

    public void onBarrierClick(View view){
        startActivity(new Intent(this, BarrierActivity.class));
    }

    public void onGroupClick(View view){
        startActivity(new Intent(this, GroupActivity.class));
    }

    public void onPlaceHolderClick(View view){
        startActivity(new Intent(this, GroupActivity.class));
    }
}
