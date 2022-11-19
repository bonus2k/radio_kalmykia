package com.example.radiokalmykia;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.radiokalmykia.databinding.ActivityMainBinding;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity{

    ActivityMainBinding activityRadioBinding;

    MediaPlayer mediaPlayer;
    ImageView playbtn;
//    SeekBar seekprog;
    //    ImageSlider mainslide;
    Handler handler = new Handler();
//    LottieAnimationView animation1, animation2;

//    DrawerLayout drawerLayout;
//    NavigationView navigationView;
//    MaterialToolbar toolbar;


//    private boolean isbackPressed = false;

    boolean prepared = false;

    String stream = "https://hls-01-vgtrk.hostingradio.ru/vgtrk-rr-klm/playlist.m3u8";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        activityRadioBinding = ActivityPlayerBinding.inflate(getLayoutInflater());
//        setContentView(activityRadioBinding.getRoot());
        activityRadioBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityRadioBinding.getRoot());
        allocateActivityTitle("Radio");

        playbtn = findViewById(R.id.play);
//        seekprog = findViewById(R.id.seekbar);
//        animation1 = findViewById(R.id.animation1_view);
//        animation2 = findViewById(R.id.animation2_view);
//
//
//
//
//        mainslide = findViewById(R.id.image_slider);
//
//        final List<SlideModel> images = new ArrayList<>();
//
//        FirebaseDatabase.getInstance().getReference().child("Slider")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for(DataSnapshot data:dataSnapshot.getChildren())
//                            images.add(new SlideModel(data.child("url").getValue().toString(), ScaleTypes.FIT));
//
//                        mainslide.setImageList(images,ScaleTypes.FIT);
//
//                    }
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });



        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerTask().execute(stream);

        mediaPlayer.setOnPreparedListener(mediaPlayer -> playbtn.setEnabled(true));



    }




    class PlayerTask extends AsyncTask<String, Void, Boolean> implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {

        @Override
        protected Boolean doInBackground(String... strings) {
            System.out.println(strings);
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

            playbtn.setOnClickListener(view -> {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
//                    seekprog.setProgress(0);
                    playbtn.setImageResource(R.drawable.play);
//                        animation1.pauseAnimation();
//                        animation2.pauseAnimation();

                }
                else{
                    mediaPlayer.start();
//                    seekprog.setProgress(100);
                    playbtn.setImageResource(R.drawable.pause);
//                        animation1.playAnimation();
//                        animation2.playAnimation();
                }
            });


        }



    }

    protected void allocateActivityTitle(String titleString){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(titleString);
        }
    }
}