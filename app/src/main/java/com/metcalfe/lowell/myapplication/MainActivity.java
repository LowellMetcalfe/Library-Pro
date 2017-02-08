package com.metcalfe.lowell.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        Button suggestReserveButton = (Button) findViewById(R.id.button);
        suggestReserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your second activity
                Intent intent = new Intent(MainActivity.this, SuggestAndReserve.class);
                startActivity(intent);
            }
        });

        ImageButton scanpageButton = (ImageButton) findViewById(R.id.imageButton5);
        scanpageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your second activity
                Intent intent = new Intent(MainActivity.this, ScanPage.class);
                startActivity(intent);
            }
        });
    }
}
