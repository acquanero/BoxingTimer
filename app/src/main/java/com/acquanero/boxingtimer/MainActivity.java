package com.acquanero.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView round_seconds_txt;
    private TextView round_minutes_txt;

    int roundSeconds;
    int roundMinutes;

    private Button buttonAddTime;
    private  Button buttonRestTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        round_seconds_txt = findViewById(R.id.label_reound_seconds_shown);
        roundSeconds = Integer.parseInt(round_seconds_txt.getText().toString());

        round_minutes_txt = findViewById(R.id.label_round_minutes_shwon);
        roundMinutes = Integer.parseInt(round_minutes_txt.getText().toString());

        buttonAddTime = findViewById(R.id.button_add_time_to_round);
        buttonRestTime = findViewById(R.id.button_rest_time_to_round);

        buttonAddTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addTimeToRound();

            }
        });

        buttonRestTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restTimeToRound();

            }
        });

    }

    private void addTimeToRound(){

        //functiona that adds seconds to the round and changes te minutes when 59sec arrives. And also add zero when 1 unit second

        if(roundSeconds == 59){

            roundMinutes = roundMinutes + 1;
            round_minutes_txt.setText(String.valueOf(roundMinutes));
            roundSeconds = 00;
            round_seconds_txt.setText("00");

        } else {

            roundSeconds = roundSeconds + 1;

            if (String.valueOf(roundSeconds).length() == 1){

                round_seconds_txt.setText("0" + String.valueOf(roundSeconds));

            } else {

                round_seconds_txt.setText(String.valueOf(roundSeconds));

            }

        }

    }
    private void restTimeToRound(){

        if (roundSeconds > 0){

            roundSeconds = roundSeconds - 1;

            if (String.valueOf(roundSeconds).length() == 1){

                round_seconds_txt.setText("0" + String.valueOf(roundSeconds));

            } else round_seconds_txt.setText(String.valueOf(roundSeconds));



        } else {

            if (roundMinutes != 0){

                roundMinutes = roundMinutes - 1;
                roundSeconds = 59;
                round_seconds_txt.setText("59");
                round_minutes_txt.setText(String.valueOf(roundMinutes));

            }

        }

    }
}