package com.example.radiokalmykia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ContactsActivity extends AppCompatActivity implements Constants {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }


    @Override
    public void startPlayerActivity(View v) {
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAboutActivity(View v) {
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void startContactsActivity(View v) {

    }
}