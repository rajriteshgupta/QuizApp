package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class SelectSubject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
        Button button1 = findViewById(R.id.bda);
        Button button2 = findViewById(R.id.cloud);
        Button button3 = findViewById(R.id.networking);
        Button button4 = findViewById(R.id.python);
        Button button5 = findViewById(R.id.aptitude);
        Button button6 = findViewById(R.id.gk);


    }
}
