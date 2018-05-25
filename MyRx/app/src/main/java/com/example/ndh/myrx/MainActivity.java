package com.example.ndh.myrx;

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

    public void onBookClick(View view) {
        Intent intent= getPackageManager().getLaunchIntentForPackage("com.example.ndh.rxapp");
        startActivity(intent);
    }

    public void onGpsClick(View view) {
        Intent intent= getPackageManager().getLaunchIntentForPackage("com.example.ndh.tracegps");
        startActivity(intent);
    }
}
