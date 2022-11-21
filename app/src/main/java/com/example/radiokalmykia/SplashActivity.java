package com.example.radiokalmykia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, PlayerActivity.class);
            startActivity(intent);
        },2000);
    }
}