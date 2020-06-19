package com.example.myquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    Session session;
    AlertDialog.Builder builder;
    private ActionBar toolbar;
//        String[] questions,options1,options2,options3,options4;
    int i = 0, sec = 0, min = 0, hour = 0, right = 0;
    TextView timer;
//
//    String address = "http://192.168.43.87/quiz/fetch_question.php";
//    InputStream inputStream = null;
//    String line = null;
//    String result = null;
    String subject;
    String[] questions, op1, op2, op3, op4;
    int[] answer;
//    String subject, no_question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        toolbar = getSupportActionBar();

        session = new Session(QuestionActivity.this);
        Intent intent = getIntent();
//        subject = intent.getStringExtra("subject");
//        no_question = intent.getStringExtra("no_question");
        timer = findViewById(R.id.timer);
//        Resources res = getResources();
//
//        QuestionBackgroundWorker questionBackgroundWorker = new QuestionBackgroundWorker(this);
//        questionBackgroundWorker.execute(subject, no_question);

        subject = intent.getStringExtra("subject");
        questions = intent.getStringArrayExtra("question");
        op1 = intent.getStringArrayExtra("option1");
        op2 = intent.getStringArrayExtra("option2");
        op3 = intent.getStringArrayExtra("option3");
        op4 = intent.getStringArrayExtra("option4");
        answer = intent.getIntArrayExtra("answer");

//
//        if(key.equals("1")){
//            toolbar.setTitle("BDA Quiz");
//            questions = res.getStringArray(R.array.bda_questions);
//            options1 = res.getStringArray(R.array.bda_option1);
//            options2 = res.getStringArray(R.array.bda_option2);
//            options3 = res.getStringArray(R.array.bda_option3);
//            options4 = res.getStringArray(R.array.bda_option4);
//        }
//        else if(key.equals("2")){
//            toolbar.setTitle("Cloud Quiz");
//            questions = res.getStringArray(R.array.cloud_questions);
//            options1 = res.getStringArray(R.array.cloud_option1);
//            options2 = res.getStringArray(R.array.cloud_option2);
//            options3 = res.getStringArray(R.array.cloud_option3);
//            options4 = res.getStringArray(R.array.cloud_option4);
//        }
//        else if(key.equals("3")){
//            toolbar.setTitle("Networking Quiz");
//            questions = res.getStringArray(R.array.networking_questions);
//            options1 = res.getStringArray(R.array.networking_option1);
//            options2 = res.getStringArray(R.array.networking_option2);
//            options3 = res.getStringArray(R.array.networking_option3);
//            options4 = res.getStringArray(R.array.networking_option4);
//        }

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
        option1.setText(op1[0]);
        option2.setText(op2[0]);
        option3.setText(op3[0]);
        option4.setText(op4[0]);

        counterFunction(questions.length);

        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (option1.isChecked() && answer[i]==1) {
                    right++;
//                    Toast.makeText(QuestionActivity.this, Integer.toString(right), Toast.LENGTH_SHORT).show();
                }
                if (option2.isChecked() && answer[i]==2) {
                    right++;
//                    Toast.makeText(QuestionActivity.this, Integer.toString(right), Toast.LENGTH_SHORT).show();
                }
                if (option3.isChecked() && answer[i]==3) {
                    right++;
//                    Toast.makeText(QuestionActivity.this, Integer.toString(right), Toast.LENGTH_SHORT).show();
                }
                if (option4.isChecked() && answer[i]==4) {
                    right++;
//                    Toast.makeText(QuestionActivity.this, Integer.toString(right), Toast.LENGTH_SHORT).show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                if (i < questions.length - 1) {
                    i++;
                    question.setText(questions[i]);
                    option1.setText(op1[i]);
                    option2.setText(op2[i]);
                    option3.setText(op3[i]);
                    option4.setText(op4[i]);
                } else {
                    endQuiz();
                }
                if (i == questions.length - 1) {
                    next.setText("Finish");
                } else {
                    next.setText("Next");
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                if (i > 0) {
                    i--;
                    question.setText(questions[i]);
                    option1.setText(op1[i]);
                    option2.setText(op2[i]);
                    option3.setText(op3[i]);
                    option4.setText(op4[i]);
                } else {
                    Toast.makeText(QuestionActivity.this, "This is the First Question", Toast.LENGTH_LONG).show();
                }
                if (i < questions.length - 1) {
                    next.setText("Next");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.end_quiz, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.end_quiz) {
            endQuiz();
        }
        return super.onOptionsItemSelected(item);
    }

    public void endQuiz() {
        dialogBox("End The Test");
    }

    void dialogBox(final String key) {
        final Dialog dialog = new Dialog(QuestionActivity.this);
        dialog.setContentView(R.layout.custom);
        TextView text = dialog.findViewById(R.id.text);
        text.setAlpha(0.0f);
        TextView text1 = dialog.findViewById(R.id.text1);
        text1.setText(key);
        TextView text2 = dialog.findViewById(R.id.text2);
        text2.setText("Are you sure you want to " + key + "?");
        Button ok = (Button) dialog.findViewById(R.id.positive);
        ok.setText("Ok");
        Button cancel = (Button) dialog.findViewById(R.id.negative);
        cancel.setText("Cancel");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                String type = "submit_answer";
                String subject_score = subject+"_score";
                String right_answer = Integer.toString(10*right);
                String username = session.getUsername();
                QuestionBackgroundWorker questionBackgroundWorker = new QuestionBackgroundWorker(QuestionActivity.this);
                questionBackgroundWorker.execute(type, subject_score, right_answer, username);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        endQuiz();
//        super.onBackPressed();
    }

    void counterFunction(int minutes) {

        final int[] min = {minutes};
        int future = min[0] * 60000;
        new CountDownTimer(future, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (sec < 0 && min[0] > 0) {
                    min[0]--;
                    sec = 59;
                }
                timer.setText(String.format("%02d", hour) + ":" + String.format("%02d", min[0]) + ":" + String.format("%02d", sec) + " sec");
                sec--;
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }
}