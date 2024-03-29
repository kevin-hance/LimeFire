// Kevin Hance and Zach McKee
// Final Project - LimeFire
// TutorialActivity

package com.example.finalprojectmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class TutorialActivity extends AppCompatActivity {


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // define toolbar back button behavior
        switch (id) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        // fetch three "Go To Tutorial" buttons
        Button tutorialButton1 = (Button) findViewById(R.id.tutorialButton1);
        Button tutorialButton2 = (Button) findViewById(R.id.tutorialButton2);
        Button tutorialButton3 = (Button) findViewById(R.id.tutorialButton3);

        // create click listeners for the three tutorial buttons
        // that creates and executes implicit intent to view URL

        tutorialButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://youtu.be/7wt_NNYMwJY";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        tutorialButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://youtu.be/HyiQErESR1c";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        tutorialButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://youtu.be/0Hc9Z2MjKgI";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        // display back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
