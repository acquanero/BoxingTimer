package com.acquanero.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
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

    int roundCounter;

    int totalRoundTimeInMilisec;
    int totalRestTimeInMilisec;

    int counterToSwitchFromRoundToRest;

    TextView roundCounterTxt;
    TextView roundMinutesTxt;
    TextView roundSecondsTxt;

    TextView restMinutesTxt;
    TextView restSecondsTxt;

    private ImageButton buttonStop;

    private MediaPlayer mysoundPlayer;
    private ToneGenerator tenSecondSound;

    SharedPreferences dataDepot;

    CountDownTimer roundTimer;
    CountDownTimer restTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxing_clock);

        dataDepot = PreferenceManager.getDefaultSharedPreferences(this);

        roundNumbers = dataDepot.getInt("roundNumbers", 6);
        roundSeconds = dataDepot.getInt("roundSeconds", 0);
        roundMinutes = dataDepot.getInt("roundMinutes", 3);
        restSeconds = dataDepot.getInt("restSeconds", 0);
        restMinutes = dataDepot.getInt("restMinutes", 1);

        counterToSwitchFromRoundToRest = 0;

        roundCounter = 1;

        totalRoundTimeInMilisec = (roundSeconds * 1000) + ((roundMinutes * 60) * 1000);
        totalRestTimeInMilisec = (restSeconds * 1000) + ((restMinutes * 60) * 1000);

        mysoundPlayer = MediaPlayer.create(this, R.raw.boxingbell);
        tenSecondSound = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);

        mysoundPlayer.start();

        //Button stop

        buttonStop = findViewById(R.id.button_stop_time);

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                roundTimer.cancel();
                restTimer.cancel();

                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
                finish();


            }
        });

        //Graphical compenents of round time and counter

        roundCounterTxt = findViewById(R.id.label_round_counter);
        roundCounterTxt.setText("1/" + roundNumbers);

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

        restTimer = new CountDownTimer(totalRestTimeInMilisec, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                if (restSeconds == 0 && restMinutes == 0) {

                    this.cancel();

                }

                if (restSeconds == 0 && restMinutes > 0){

                    restSeconds = 59;
                    restMinutes = restMinutes - 1;

                } else {

                    restSeconds = restSeconds - 1;

                }

                restMinutesTxt.setText(String.valueOf(restMinutes));

                if (String.valueOf(restSeconds).length() == 1) {

                    restSecondsTxt.setText("0" + restSeconds);

                } else {

                    restSecondsTxt.setText(String.valueOf(restSeconds));

                }

            }

            @Override
            public void onFinish() {

                mysoundPlayer.start();

                if (roundCounter <= roundNumbers) {

                    roundCounterTxt.setText( roundCounter  + "/" + roundNumbers);

                    restSeconds = dataDepot.getInt("restSeconds", 0);
                    restMinutes = dataDepot.getInt("restMinutes", 1);

                    restMinutesTxt.setText(Integer.toString(restMinutes));

                    restSecondsTxt = findViewById(R.id.label_rest_seconds_shown);
                    if (String.valueOf(restSeconds).length() == 1) {

                        restSecondsTxt.setText("0" + restSeconds);

                    } else {

                        restSecondsTxt.setText(String.valueOf(restSeconds));

                    }

                    roundTimer.start();

                }

            }
        };

        roundTimer = new CountDownTimer(totalRoundTimeInMilisec, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                if (roundSeconds == 0 && roundMinutes == 0) {

                    this.cancel();

                }

                if (roundSeconds == 0 && roundMinutes > 0){

                    roundSeconds = 59;
                    roundMinutes = roundMinutes - 1;

                } else {

                    roundSeconds = roundSeconds - 1;

                }

                roundMinutesTxt.setText(String.valueOf(roundMinutes));

                if (String.valueOf(roundSeconds).length() == 1) {

                    roundSecondsTxt.setText("0" + roundSeconds);

                } else {

                    roundSecondsTxt.setText(String.valueOf(roundSeconds));

                }

                if (roundMinutes == 0 && (roundSeconds <= 10 && roundSeconds > 2)) {

                    tenSecondSound.startTone(ToneGenerator.TONE_CDMA_PIP,ToneGenerator.MAX_VOLUME);

                }

            }

            @Override
            public void onFinish() {

                mysoundPlayer.start();

                roundCounter++;

                roundSeconds = dataDepot.getInt("roundSeconds", 0);
                roundMinutes = dataDepot.getInt("roundMinutes", 3);

                roundMinutesTxt.setText(String.valueOf(roundMinutes));

                roundSecondsTxt = findViewById(R.id.label_round_seconds_shown);

                if (String.valueOf(roundSeconds).length() == 1) {

                    roundSecondsTxt.setText("0" + roundSeconds);

                } else {

                    roundSecondsTxt.setText(String.valueOf(roundSeconds));

                }

                restTimer.start();

            }
        };

        roundTimer.start();

    }

}