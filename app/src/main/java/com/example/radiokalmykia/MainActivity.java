package com.example.radiokalmykia;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.radiokalmykia.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.Optional;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityMainBinding binding;
    private ImageView playbtn;
    private static MediaPlayer mediaPlayer;
    private static boolean prepared = false;

    private static final String STREAM = "https://hls-01-vgtrk.hostingradio.ru/vgtrk-rr-klm/playlist.m3u8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        playbtn = findViewById(R.id.play);
        playbtn.setOnClickListener(this);
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            new PlayerTask().execute(STREAM);
        }
    }

    public void startAboutActivity(View v) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playbtn.setImageResource(R.drawable.play);
        } else {
            mediaPlayer.start();
            playbtn.setImageResource(R.drawable.pause);
        }
    }


    class PlayerTask extends AsyncTask<String, Void, Boolean> implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.setOnErrorListener(this);
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.prepareAsync();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            return false;
        }

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {

        }
    }
}