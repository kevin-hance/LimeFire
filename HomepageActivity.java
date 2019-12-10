// Kevin Hance and Zach McKee
// Final Project - LimeFire
// HomepageActivity

package com.example.finalprojectmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // fetch buttons
        Button padButton = (Button) findViewById(R.id.padButton);
        Button allSoundsButton = (Button) findViewById(R.id.allSoundsButton);
        Button tutorialsButton = (Button) findViewById(R.id.tutorialsButton);

        // create click listener for pad button
        padButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // create click listener for all sounds button
        allSoundsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, SoundsActivity.class);
                startActivity(intent);
            }
        });

        // create click listener for tutorials button
        tutorialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, TutorialActivity.class);
                startActivity(intent);
            }
        });

    }
}
