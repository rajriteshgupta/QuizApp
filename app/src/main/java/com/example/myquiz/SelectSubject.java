package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
                intent.putExtra("key","bda");
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
                intent.putExtra("key","cloud");
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
                intent.putExtra("key","networking");
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
                intent.putExtra("key","python");
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
                intent.putExtra("key","aptitude");
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
                intent.putExtra("key","gk");
                startActivity(intent);
            }
        });
    }
}
