package com.acquanero.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView round_seconds_txt;
    private TextView round_minutes_txt;
    private TextView round_numbers_txt;
    private TextView rest_seconds_txt;
    private TextView rest_minutes_txt;

    int roundSeconds;
    int roundMinutes;
    int roundNumbers;
    
    int restSeconds;
    int restMinutes;

    private Button buttonAddTimeToRound;
    private  Button buttonSubtractTimeToRound;

    private Button buttonAddRounds;
    private  Button buttonRestRounds;

    private Button buttonAddTimeToRest;
    private Button buttonSubtractTimeToRest;
    private Button buttonStartTimer;

    SharedPreferences dataDepot;
    SharedPreferences.Editor dataDepotEditable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataDepot = PreferenceManager.getDefaultSharedPreferences(this);
        dataDepotEditable = dataDepot.edit();

        //buttons and labels for rounds time
        round_seconds_txt = findViewById(R.id.label_round_seconds_shown);
        roundSeconds = dataDepot.getInt("roundSeconds", 00);

        if (String.valueOf(roundSeconds).length() == 1){

            round_seconds_txt.setText("0" + roundSeconds);

        } else {

            round_seconds_txt.setText(String.valueOf(roundSeconds));

        }

        round_minutes_txt = findViewById(R.id.label_round_minutes_shown);
        roundMinutes = dataDepot.getInt("roundMinutes", 3);
        round_minutes_txt.setText(Integer.toString(roundMinutes));

        buttonAddTimeToRound = findViewById(R.id.button_add_time_to_round);
        buttonSubtractTimeToRound = findViewById(R.id.button_subtract_time_to_round);

        buttonStartTimer = findViewById(R.id.button_start_timer);

        buttonAddTimeToRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int[] tiempo = addTime(roundMinutes, roundSeconds);

                roundMinutes = tiempo[0];

                roundSeconds = tiempo[1];

                round_minutes_txt.setText(String.valueOf(tiempo[0]));

                if (String.valueOf(roundSeconds).length() == 1){

                    round_seconds_txt.setText("0" + roundSeconds);

                } else {

                    round_seconds_txt.setText(String.valueOf(roundSeconds));

                }

            }
        });

        buttonSubtractTimeToRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int[] tiempo = restTime(roundMinutes, roundSeconds);

                roundMinutes = tiempo[0];

                roundSeconds = tiempo[1];

                round_minutes_txt.setText(String.valueOf(tiempo[0]));

                if (String.valueOf(roundSeconds).length() == 1){

                    round_seconds_txt.setText("0" + roundSeconds);

                } else {

                    round_seconds_txt.setText(String.valueOf(roundSeconds));

                }


            }
        });

        //buttons and label for round numbers

        round_numbers_txt = findViewById(R.id.round_numbers);
        roundNumbers = dataDepot.getInt("roundNumbers", 12);
        round_numbers_txt.setText(Integer.toString(roundNumbers));

        buttonAddRounds = findViewById(R.id.button_add_rounds);
        buttonRestRounds = findViewById(R.id.button_subtract_rounds);

        buttonAddRounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNumberOfRounds();

            }
        });

        buttonRestRounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                restNumberOfRounds();

            }
        });

        //buttons and labels for rest time

        rest_seconds_txt = findViewById(R.id.label_rest_seconds_shown);
        restSeconds = dataDepot.getInt("restSeconds", 00);
        if (String.valueOf(restSeconds).length() == 1){

            rest_seconds_txt.setText("0" + restSeconds);

        } else {

            rest_seconds_txt.setText(String.valueOf(restSeconds));

        }

        rest_minutes_txt = findViewById(R.id.label_rest_minutes_shown);
        restMinutes = dataDepot.getInt("restMinutes", 1);
        rest_minutes_txt.setText(Integer.toString(restMinutes));

        buttonAddTimeToRest = findViewById(R.id.button_add_time_to_rest);
        buttonSubtractTimeToRest = findViewById(R.id.button_subtract_time_to_rest);

        buttonAddTimeToRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] tiempo = addTime(restMinutes, restSeconds);

                restMinutes = tiempo[0];

                restSeconds = tiempo[1];

                rest_minutes_txt.setText(String.valueOf(tiempo[0]));

                if (String.valueOf(restSeconds).length() == 1){

                    rest_seconds_txt.setText("0" + restSeconds);

                } else {

                    rest_seconds_txt.setText(String.valueOf(restSeconds));

                }

            }
        });

        buttonSubtractTimeToRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] tiempo = restTime(restMinutes, restSeconds);

                restMinutes = tiempo[0];

                restSeconds = tiempo[1];

                rest_minutes_txt.setText(String.valueOf(tiempo[0]));

                if (String.valueOf(restSeconds).length() == 1){

                    rest_seconds_txt.setText("0" + String.valueOf(restSeconds));

                } else {

                    rest_seconds_txt.setText(String.valueOf(restSeconds));

                }

            }
        });

        buttonStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataDepotEditable.putInt("roundNumbers", roundNumbers);
                dataDepotEditable.putInt("roundMinutes", roundMinutes);
                dataDepotEditable.putInt("roundSeconds", roundSeconds);
                dataDepotEditable.putInt("restMinutes", restMinutes);
                dataDepotEditable.putInt("restSeconds", restSeconds);

                dataDepotEditable.apply();

                Intent goToTimer = new Intent(getApplicationContext(), BoxingClockActivity.class);

                startActivity(goToTimer);

            }
        });


    }

    private int[] addTime(int minutes, int seconds){

        int[] minutesSeconds = new int[2];

        //functiona that adds seconds to the timer and changes te minutes when 59sec arrives. And also add zero when 1 unit second

        if(seconds == 55){

            minutes = minutes + 1;
            seconds = 00;

            minutesSeconds[0] = minutes;
            minutesSeconds[1] = seconds;

        } else {

            seconds = seconds + 5;

            minutesSeconds[0] = minutes;
            minutesSeconds[1] = seconds;


        }

        return minutesSeconds;

    }

    private int[] restTime(int minutes, int seconds){

        int[] minutesSeconds = new int[2];

        minutesSeconds[0] = minutes;
        minutesSeconds[1] = seconds;

        //functiona that decrement seconds to the timers

        if (seconds == 1 && minutes == 0) return minutesSeconds;

        if (seconds > 0){

            seconds = seconds - 5;

            minutesSeconds[1] = seconds;

        } else {

            if (minutes != 0){

                minutes = minutes - 1;
                seconds = 55;

                minutesSeconds[0] = minutes;
                minutesSeconds[1] = seconds;

            }

        }

        return minutesSeconds;

    }

    private void addNumberOfRounds(){

        roundNumbers = roundNumbers + 1;

        round_numbers_txt.setText(String.valueOf(roundNumbers));


    }

    private  void restNumberOfRounds(){

        if (roundNumbers > 1){

            roundNumbers = roundNumbers - 1;

            round_numbers_txt.setText(String.valueOf(roundNumbers));

        }

    }
}