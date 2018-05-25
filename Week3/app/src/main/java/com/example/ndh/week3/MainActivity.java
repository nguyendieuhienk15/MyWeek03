package com.example.ndh.week3;

import android.content.ComponentName;
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

    public void onRxClick(View view) {
        Intent intent= getPackageManager().getLaunchIntentForPackage("com.example.ndh.myrx");
        startActivity(intent);
    }

    public void onDAOClick(View view) {
        Intent intent= getPackageManager().getLaunchIntentForPackage("com.example.ndh.reminder");
        startActivity(intent);
    }
}
