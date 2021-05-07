package com.acquanero.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Timer;

public class BoxingClockActivity extends AppCompatActivity {

    int roundNumbers;
    int roundSeconds;
    int roundMinutes;
    int restSeconds;
    int restMinutes;

    int counter;

    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxing_clock);

        data = getIntent().getExtras();

        roundNumbers = data.getInt("roundNumbers");
        roundSeconds = data.getInt("roundSeconds");
        roundMinutes = data.getInt("roundMinutes");
        restSeconds = data.getInt("restSeconds");
        restMinutes = data.getInt("restMinutes");

        counter = 1;

        int totalTimeInMilisec = (roundSeconds * 1000) + ((roundMinutes * 60) * 1000);

        TextView roundCounter = findViewById(R.id.label_round_counter);
        roundCounter.setText("1/" + roundNumbers);

        TextView roundMinutesTxt = findViewById(R.id.label_round_minutes_shown);
        roundMinutesTxt.setText(String.valueOf(roundMinutes));

        TextView roundSecondsTxt = findViewById(R.id.label_round_seconds_shown);

        if (String.valueOf(roundSeconds).length() == 1){

            roundSecondsTxt.setText("0" + roundSeconds);

        } else {

            roundSecondsTxt.setText(String.valueOf(roundSeconds));

        }

        CountDownTimer miTimer = new CountDownTimer(totalTimeInMilisec, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                if (roundSeconds == 0){

                    if (roundMinutes > 0){

                        roundMinutes = roundMinutes - 1;
                        roundSeconds = 60;

                        if (String.valueOf(roundSeconds).length() == 1){

                            roundSecondsTxt.setText("0" + roundSeconds);

                        } else {

                            roundSecondsTxt.setText(String.valueOf(roundSeconds));

                        }

                        roundMinutesTxt.setText(String.valueOf(roundMinutes));

                    } else {

                        this.cancel();

                    }


                }

                roundSeconds = roundSeconds - 1;

                if (String.valueOf(roundSeconds).length() == 1){

                    roundSecondsTxt.setText("0" + roundSeconds);

                } else {

                    roundSecondsTxt.setText(String.valueOf(roundSeconds));

                }

            }

            @Override
            public void onFinish() {

                if (counter < roundNumbers) {

                    counter++;

                    roundCounter.setText(counter + "/" + roundNumbers);

                    roundSeconds = data.getInt("roundSeconds");
                    roundMinutes = data.getInt("roundMinutes");

                    this.start();

                }

            }
        };

        miTimer.start();

    }

}