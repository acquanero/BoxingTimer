package com.acquanero.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //buttons and labels for rounds time
        round_seconds_txt = findViewById(R.id.label_round_seconds_shown);
        roundSeconds = Integer.parseInt(round_seconds_txt.getText().toString());

        round_minutes_txt = findViewById(R.id.label_round_minutes_shown);
        roundMinutes = Integer.parseInt(round_minutes_txt.getText().toString());

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

        roundNumbers = Integer.parseInt(round_numbers_txt.getText().toString());

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
        restSeconds = Integer.parseInt(rest_seconds_txt.getText().toString());

        rest_minutes_txt = findViewById(R.id.label_rest_minutes_shown);
        restMinutes = Integer.parseInt(rest_minutes_txt.getText().toString());

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

                Intent goToTimer = new Intent(getApplicationContext(), BoxingClockActivity.class);

                goToTimer.putExtra("roundNumbers", roundNumbers);
                goToTimer.putExtra("roundMinutes", roundMinutes);
                goToTimer.putExtra("roundSeconds", roundSeconds);
                goToTimer.putExtra("restMinutes", restMinutes);
                goToTimer.putExtra("restSeconds", restSeconds);

                startActivity(goToTimer);

            }
        });


    }

    private int[] addTime(int minutes, int seconds){

        int[] minutesSeconds = new int[2];

        //functiona that adds seconds to the timer and changes te minutes when 59sec arrives. And also add zero when 1 unit second

        if(seconds == 59){

            minutes = minutes + 1;
            seconds = 00;

            minutesSeconds[0] = minutes;
            minutesSeconds[1] = seconds;

        } else {

            seconds = seconds + 1;

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

            seconds = seconds - 1;

            minutesSeconds[1] = seconds;

        } else {

            if (minutes != 0){

                minutes = minutes - 1;
                seconds = 59;

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