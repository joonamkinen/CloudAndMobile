package com.example.assignment5_1;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));


        button1 = new Button(this);
        button1.setText("Visibility");


        button2 = new Button(this);
        button2.setText("Move");


        layout.addView(button1);
        layout.addView(button2);


        setContentView(layout);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button2.getVisibility() == View.VISIBLE) {
                    button2.setVisibility(View.INVISIBLE);
                } else {
                    button2.setVisibility(View.VISIBLE);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float newY = button1.getY() + 100;
                button1.setY(newY);
            }
        });
    }
}
