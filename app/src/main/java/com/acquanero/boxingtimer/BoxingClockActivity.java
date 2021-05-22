package com.acquanero.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BoxingClockActivity extends AppCompatActivity {

    int roundNumbers;
    int roundSeconds;
    int roundMinutes;
    int restSeconds;
    int restMinutes;

    int counter;

    int totalRoundTimeInMilisec;
    int totalRestTimeInMilisec;
    int totalTimeMilisec;

    int counterToSwitchFromRoundToRest;

    TextView roundCounter;
    TextView roundMinutesTxt;
    TextView roundSecondsTxt;

    TextView restMinutesTxt;
    TextView restSecondsTxt;

    private ImageButton buttonStop;

    private MediaPlayer mysoundPlayer;

    private Boolean alreadyPlayed;

    SharedPreferences dataDepot;

    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxing_clock);

        //boolean variable to assure that the bell plays only one in every round
        alreadyPlayed = false;

        dataDepot = PreferenceManager.getDefaultSharedPreferences(this);

        roundNumbers = dataDepot.getInt("roundNumbers", 6);
        roundSeconds = dataDepot.getInt("roundSeconds", 0);
        roundMinutes = dataDepot.getInt("roundMinutes", 3);
        restSeconds = dataDepot.getInt("restSeconds", 0);
        restMinutes = dataDepot.getInt("restMinutes", 1);

        counterToSwitchFromRoundToRest = 0;

        counter = 1;

        totalRoundTimeInMilisec = (roundSeconds * 1000) + ((roundMinutes * 60) * 1000);
        totalRestTimeInMilisec = (restSeconds * 1000) + ((restMinutes * 60) * 1000);
        totalTimeMilisec = totalRoundTimeInMilisec + totalRestTimeInMilisec + 2000;

        mysoundPlayer = MediaPlayer.create(this, R.raw.boxingbell);

        mysoundPlayer.start();

        //Button stop

        buttonStop = findViewById(R.id.button_stop_time);

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
                finish();


            }
        });

        //Graphical compenents of round time and counter

        roundCounter = findViewById(R.id.label_round_counter);
        roundCounter.setText("1/" + roundNumbers);

        roundMinutesTxt = findViewById(R.id.label_round_minutes_shown);
        roundMinutesTxt.setText(String.valueOf(roundMinutes));

        roundSecondsTxt = findViewById(R.id.label_round_seconds_shown);

        if (String.valueOf(roundSeconds).length() == 1) {

            roundSecondsTxt.setText("0" + roundSeconds);

        } else {

            roundSecondsTxt.setText(String.valueOf(roundSeconds));

        }

        //Graphical components of rest timer

        restMinutesTxt = findViewById(R.id.label_rest_minutes_shown);
        restMinutesTxt.setText(Integer.toString(restMinutes));

        restSecondsTxt = findViewById(R.id.label_rest_seconds_shown);
        if (String.valueOf(restSeconds).length() == 1) {

            restSecondsTxt.setText("0" + restSeconds);

        } else {

            restSecondsTxt.setText(String.valueOf(restSeconds));

        }

        CountDownTimer miTimer = new CountDownTimer(90000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };

        miTimer.start();

    }

}