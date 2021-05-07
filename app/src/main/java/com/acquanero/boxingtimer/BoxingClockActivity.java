package com.acquanero.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BoxingClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxing_clock);

        Bundle data = getIntent().getExtras();

        int roundNumbers = data.getInt("roundNumbers");

        int roundSeconds = data.getInt("roundSeconds");
        int roundMinutes = data.getInt("roundMinutes");
        int restSeconds = data.getInt("restSeconds");
        int restMinutes = data.getInt("restMinutes");

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

    }
}