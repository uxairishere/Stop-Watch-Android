package com.uxair.timerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    TextView textViewInsta;
    SeekBar timeSeekBar;
    Boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;
    VideoView videoView;
    MediaPlayer mediaPlayer;

    public void reset(){
        timerTextView.setText("0:30");
        timeSeekBar.setProgress(30);
        timeSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("START");
        counterIsActive = false;
    }

    public void buttonClicked(View view){
        if(counterIsActive){
            reset();

        }else {
            counterIsActive = true;
            timeSeekBar.setEnabled(false);
            goButton.setText("STOP");

            countDownTimer = new CountDownTimer(timeSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                    //MediaPlayer mediaVideo = new MediaPlayer.create(getApplicationContext() ,R.raw.infinitysmoke);
                    //mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.infinitysmoke);


                }

                @Override
                public void onFinish() {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mediaPlayer.start();
                }
            }.start();
        }

    }

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondsString = Integer.toString(seconds);

        if(seconds <= 9){
            secondsString = "0" +  secondsString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSeekBar = findViewById(R.id.seekBar);
        timerTextView = findViewById(R.id.countDownTextView);
        goButton = findViewById(R.id.buttonStart);

        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.infinityneon);
        videoView.start();


        timeSeekBar.setMax(600);
        timeSeekBar.setProgress(30);

        textViewInsta = findViewById(R.id.textViewInsta);
        textViewInsta.setClickable(true);
        textViewInsta.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://www.instagram.com/uxair_abbass'> I N S T A G R A M </a>";
        textViewInsta.setText(Html.fromHtml(text));



        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}