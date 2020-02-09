package com.example.deeepwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static android.media.MediaExtractor.MetricsConstants.FORMAT;

public class TimerActivity extends AppCompatActivity {

    private int hours = 0;
    private int minutes = 0;
    private int breakFreqs = 0;
    private static final String FORMAT = "%02d:%02d:%02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Intent intent = getIntent();
        hours = Integer.parseInt(intent.getStringExtra("hours"));
        minutes = Integer.parseInt(intent.getStringExtra("minutes"));
        breakFreqs = Integer.parseInt(intent.getStringExtra("breakFreqs"));

        System.out.println("hours: " + hours);
        System.out.println("minutes: " + minutes);
        System.out.println("breakFreqs: " + breakFreqs);


        Button buttonStop = (Button) findViewById(R.id.bt_stop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final TextView timerView=(TextView)findViewById(R.id.timer);
        int totalTimeInMillis = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000);
        new CountDownTimer(totalTimeInMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                int secs = (int) (millisUntilFinished / 1000);
                int mins = secs / 60;
                secs = secs % 60;
                int hrs = mins / 60;
                mins = mins % 60;

                String timeString = ("" + String.format("%02d", hrs)
                        + ":" + String.format("%02d", mins));
                timerView.setText(timeString);
            }

            public void onFinish() {
                timerView.setText("done!");
            }
        }.start();

    }

}

