package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SelectSubject extends AppCompatActivity {

    String subject,question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);

        Button button = findViewById(R.id.button);
//        Button button1 = findViewById(R.id.bda);
//        Button button2 = findViewById(R.id.cloud);
//        Button button3 = findViewById(R.id.networking);
//        Button button4 = findViewById(R.id.python);
//        Button button5 = findViewById(R.id.aptitude);
//        Button button6 = findViewById(R.id.gk);
//
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        final String[] items1 = new String[] { "BDA", "Cloud", "Networking", "Python", "Aptitude", "GK"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_row, items1);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                subject = items1[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                subject = null;
            }
        });

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        final String[] items2 = new String[] { "5", "10", "15", "20"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_row, items2);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                question = items2[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                question = null;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
                intent.putExtra("subject",subject);
                intent.putExtra("question",question);
                startActivity(intent);
            }
        });
//
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
//                intent.putExtra("key","bda");
//                startActivity(intent);
//            }
//        });
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
//                intent.putExtra("key","cloud");
//                startActivity(intent);
//            }
//        });
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
//                intent.putExtra("key","networking");
//                startActivity(intent);
//            }
//        });
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
//                intent.putExtra("key","python");
//                startActivity(intent);
//            }
//        });
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
//                intent.putExtra("key","aptitude");
//                startActivity(intent);
//            }
//        });
//        button6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SelectSubject.this,PostQuestionActivity.class);
//                intent.putExtra("key","gk");
//                startActivity(intent);
//            }
//        });
    }
}
