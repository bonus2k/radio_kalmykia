package com.example.radiokalmykia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity implements Constants {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public void startPlayerActivity(View v) {
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAboutActivity(View v) {

    }

    @Override
    public void startContactsActivity(View v) {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }
}