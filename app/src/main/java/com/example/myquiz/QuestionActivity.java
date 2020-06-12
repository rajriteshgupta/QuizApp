package com.example.myquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    AlertDialog.Builder builder;
    String[] questions,options1,options2,options3,options4;
    int i=0,sec=0,min=10,hour=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        final TextView timer=findViewById(R.id.timer);
        Resources res = getResources();
        questions = res.getStringArray(R.array.questions);
        options1 = res.getStringArray(R.array.option1);
        options2 = res.getStringArray(R.array.option2);
        options3 = res.getStringArray(R.array.option3);
        options4 = res.getStringArray(R.array.option4);
        builder = new AlertDialog.Builder(QuestionActivity.this);
        final TextView question = findViewById(R.id.question);
        final RadioGroup radioGroup = findViewById(R.id.radio_group);
        final RadioButton option1 = findViewById(R.id.option1);
        final RadioButton option2 = findViewById(R.id.option2);
        final RadioButton option3 = findViewById(R.id.option3);
        final RadioButton option4 = findViewById(R.id.option4);
        final Button next = findViewById(R.id.next);
        Button previous = findViewById(R.id.previous);

        question.setText(questions[0]);
        option1.setText(options1[0]);
        option2.setText(options2[0]);
        option3.setText(options3[0]);
        option4.setText(options4[0]);

        new CountDownTimer(600000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(sec<0 && min>0){
                    min--;
                    sec=59;
                }
                timer.setText(String.format("%02d", hour)+":"+String.format("%02d", min)+":"+String.format("%02d", sec)+" sec");
                sec--;
            }
            @Override
            public void onFinish() {
                finish();
                //startActivity(new Intent(QuestionActivity.this,MainActivity.class));
            }
        }.start();

        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb) {
                    Toast.makeText(QuestionActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //radioGroup.clearCheck();
                if(i<questions.length-1){
                    i++;
                    question.setText(questions[i]);
                    option1.setText(options1[i]);
                    option2.setText(options2[i]);
                    option3.setText(options3[i]);
                    option4.setText(options4[i]);
                }
                else {
                    endQuiz();
                }
                if(i==questions.length-1){
                    next.setText("Finish");
                }
                else {
                    next.setText("Next");
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //radioGroup.clearCheck();
                if(i>0){
                    i--;
                    question.setText(questions[i]);
                    option1.setText(options1[i]);
                    option2.setText(options2[i]);
                    option3.setText(options3[i]);
                    option4.setText(options4[i]);
                }
                else {
                    Toast.makeText(QuestionActivity.this,"This is the First Question",Toast.LENGTH_LONG).show();
                }
                if(i<questions.length-1){
                    next.setText("Next");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.end_quiz,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.end_quiz){
            endQuiz();
        }
        return super.onOptionsItemSelected(item);
    }

    public void endQuiz(){
        builder.setMessage("Are You Sure?").setTitle("End The Test");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
                //startActivity(new Intent(QuestionActivity.this,MainActivity.class));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        endQuiz();
//        super.onBackPressed();
    }
}
