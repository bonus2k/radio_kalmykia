package com.example.radiokalmykia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public interface Constants {
    Bundle mainBundle = new Bundle();

    void startPlayerActivity(View v);
    void startAboutActivity(View v);
    void startContactsActivity(View v);

}
