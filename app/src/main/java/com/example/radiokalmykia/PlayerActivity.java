package com.example.radiokalmykia;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.chibde.visualizer.LineVisualizer;
import com.example.radiokalmykia.databinding.PlayerMainBinding;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener, Constants {


    private PlayerMainBinding binding;
    private ImageView playbtn;
    private static MediaPlayer mediaPlayer;
    private static boolean prepared = false;

    private static final String STREAM = "https://hls-01-vgtrk.hostingradio.ru/vgtrk-rr-klm/playlist.m3u8";
    private static int PERMISSION_MIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PlayerMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupPlayButton();
        playbtn.setOnClickListener(this);
        checkMediaPlayer();
        soundVisualization(this.getCurrentFocus());
        View about = findViewById(R.id.about);
        View contacts = findViewById(R.id.contacts);
        about.setOnClickListener(this::startAboutActivity);
        contacts.setOnClickListener(this::startContactsActivity);

    }

    private void checkMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            new PlayerTask().execute(STREAM);
        }
    }

    private void setupPlayButton() {
        playbtn = findViewById(R.id.play);
        if (mainBundle.getInt("playbtn") == 1) {
            playbtn.setImageResource(R.drawable.pause);
        }
    }

    @Override
    public void startPlayerActivity(View v) {

    }

    @Override
    public void startAboutActivity(View v) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void startContactsActivity(View v) {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mainBundle.putInt("playbtn", 0);
            playbtn.setImageResource(R.drawable.play);
        } else {
            mediaPlayer.start();
            mainBundle.putInt("playbtn", 1);
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

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    public void soundVisualization(View view) {
        int RECORD_AUDIO = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int MODIFY_AUDIO_SETTINGS = ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS);
        if (RECORD_AUDIO == PackageManager.PERMISSION_GRANTED && MODIFY_AUDIO_SETTINGS == PackageManager.PERMISSION_GRANTED) {
            LineVisualizer visualizer = findViewById(R.id.visualizerLineBar);
            visualizer.setVisibility(View.VISIBLE);
            visualizer.setColor(ContextCompat.getColor(this, R.color.blue_for_text));
            visualizer.setStrokeWidth(1);
            visualizer.setPlayer(mediaPlayer.getAudioSessionId());
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS},
                    PERMISSION_MIC);
        }
    }
}