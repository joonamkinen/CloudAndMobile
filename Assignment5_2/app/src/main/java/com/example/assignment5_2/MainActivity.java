package com.example.assignment5_2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    private LinearLayout mainLayout;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView userInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mainLayout);

        showUser();
    }

    private void showUser() {
        mainLayout.removeAllViews();

        usernameEditText = new EditText(this);
        usernameEditText.setHint("Enter username");

        Button continueButton = new Button(this);
        continueButton.setText("Continue");
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueButton();
            }
        });

        mainLayout.addView(usernameEditText);
        mainLayout.addView(continueButton);
    }

    private void continueButton() {
        String username = usernameEditText.getText().toString().trim();

        if (username.isEmpty()) {
            usernameEditText.setBackgroundColor(Color.RED);
        } else {
            usernameEditText.setBackgroundColor(Color.TRANSPARENT);
            showPassword();
        }
    }

    private void showPassword() {
        mainLayout.removeAllViews();

        passwordEditText = new EditText(this);
        passwordEditText.setHint("Enter password");

        Button continueButton = new Button(this);
        continueButton.setText("Continue");
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuePasswordButton();
            }
        });

        mainLayout.addView(passwordEditText);
        mainLayout.addView(continueButton);
    }

    private void  continuePasswordButton() {
        String password = passwordEditText.getText().toString().trim();

        if (password.isEmpty()) {
            passwordEditText.setBackgroundColor(Color.RED);
        } else {
            passwordEditText.setBackgroundColor(Color.TRANSPARENT);
            showUserInfo();
        }
    }

    private void showUserInfo() {
        mainLayout.removeAllViews();

        userInfoTextView = new TextView(this);
        userInfoTextView.setGravity(Gravity.CENTER);

        Button backButton = new Button(this);
        backButton.setText("Back");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUser();
            }
        });

        mainLayout.addView(userInfoTextView);
        mainLayout.addView(backButton);

        // Display user info and current date/time
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String currentTime = getCurrentTime();

        userInfoTextView.setText("Username: " + username + "\nPassword: " + password + "\nCurrent Time: " + currentTime);
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
