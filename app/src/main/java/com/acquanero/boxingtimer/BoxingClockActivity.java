package com.acquanero.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
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

        counterToSwitchFromRoundToRest = 0;

        counter = 1;

        totalRoundTimeInMilisec = (roundSeconds * 1000) + ((roundMinutes * 60) * 1000);
        totalRestTimeInMilisec = (restSeconds * 1000) + ((restMinutes * 60) * 1000);
        totalTimeMilisec = totalRoundTimeInMilisec + totalRestTimeInMilisec + 2000;

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
        restMinutesTxt.setText(String.valueOf(restMinutes));
        restSecondsTxt = findViewById(R.id.label_rest_seconds_shown);

        CountDownTimer miTimer = new CountDownTimer(totalTimeMilisec, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                System.out.println("round milisec: " + totalRoundTimeInMilisec);
                System.out.println("switch time milisec: " + counterToSwitchFromRoundToRest);

                System.out.println("restseconds: " + restSeconds);
                if (counterToSwitchFromRoundToRest <= totalRoundTimeInMilisec) {

                    if (roundSeconds == 0){

                        if (roundMinutes > 0){

                            counterToSwitchFromRoundToRest = counterToSwitchFromRoundToRest + 1000;
                            roundMinutes = roundMinutes - 1;
                            roundSeconds = 59;

                            roundMinutesTxt.setText(String.valueOf(roundMinutes));

                            if (String.valueOf(roundSeconds).length() == 1){

                                roundSecondsTxt.setText("0" + roundSeconds);

                            } else {

                                roundSecondsTxt.setText(String.valueOf(roundSeconds));

                            }

                        }


                    }

                    if (String.valueOf(roundSeconds).length() == 1){

                        roundSecondsTxt.setText("0" + roundSeconds);

                    } else {

                        roundSecondsTxt.setText(String.valueOf(roundSeconds));

                    }

                    counterToSwitchFromRoundToRest = counterToSwitchFromRoundToRest + 1000;
                    roundSeconds = roundSeconds - 1;


                } else {

                    if (restSeconds == 0){

                        if (restMinutes > 0){

                            restMinutes = restMinutes - 1;
                            restSeconds = 59;

                            if (String.valueOf(restSeconds).length() == 1){

                                restSecondsTxt.setText("0" + restSeconds);

                            } else {

                                restSecondsTxt.setText(String.valueOf(restSeconds));

                            }

                            restMinutesTxt.setText(String.valueOf(restMinutes));

                        }

                    }

                    if (String.valueOf(restSeconds).length() == 1){

                        restSecondsTxt.setText("0" + restSeconds);

                    } else {

                        restSecondsTxt.setText(String.valueOf(restSeconds));

                    }

                    restSeconds = restSeconds - 1;


                }

            }

            @Override
            public void onFinish() {

                counterToSwitchFromRoundToRest = 0;

                if (counter < roundNumbers) {

                    counter++;

                    roundCounter.setText(counter + "/" + roundNumbers);

                    roundSeconds = data.getInt("roundSeconds");
                    roundMinutes = data.getInt("roundMinutes");

                    restSeconds = data.getInt("restSeconds");
                    restMinutes = data.getInt("restMinutes");

                    this.start();

                }

            }
        };

        miTimer.start();

    }

}