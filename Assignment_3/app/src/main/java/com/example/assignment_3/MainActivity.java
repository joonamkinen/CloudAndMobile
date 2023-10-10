package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int randomNumber;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);

        TextView randomNumberTextView = findViewById(R.id.randomNumberTextView);

        Random random = new Random();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                randomNumber = random.nextInt(100);
                randomNumberTextView.setText("Random Number: " + randomNumber);
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("Random", randomNumber);
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        outState.putString("Date", date);
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        date = savedInstanceState.getString("Date");
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
        int latestRandomNumber = savedInstanceState.getInt("Random");
        TextView latestRandomNumberView = findViewById(R.id.latestRandomNumberTextView);
        latestRandomNumberView.setText("Latest Random Number: " + latestRandomNumber);
    }

}
