package com.example.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PostQuestionActivity extends AppCompatActivity {

    Button mPost;
    EditText mQuestion,mOpt1,mOpt2,mOpt3,mOpt4;
    Intent intent;
    int no_question=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);

        intent = getIntent();
        mPost = findViewById(R.id.post);
        mQuestion = findViewById(R.id.question);
        mOpt1 = findViewById(R.id.option1);
        mOpt2 = findViewById(R.id.option2);
        mOpt3 = findViewById(R.id.option3);
        mOpt4 = findViewById(R.id.option4);
        no_question = Integer.valueOf(intent.getStringExtra("question"));
        PostQuestion();
    }

    void PostQuestion(){
        mPost.setText("Post("+no_question+" question left)");

        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = intent.getStringExtra("subject");
                String question = mQuestion.getText().toString();
                String option1 = mOpt1.getText().toString();
                String option2 = mOpt1.getText().toString();
                String option3 = mOpt1.getText().toString();
                String option4 = mOpt1.getText().toString();
                String type = "question_post";
                if(question!=null && option1!=null && option2!=null && option3!=null && option4!=null) {
                    BackgroundWorker backgroundWorker = new BackgroundWorker(PostQuestionActivity.this);
                    backgroundWorker.execute(type, subject, question, option1, option2, option3, option4);
                }
                else{
                    Toast.makeText(PostQuestionActivity.this,"Some Fields are Empty!!!",Toast.LENGTH_LONG).show();
                }
                no_question--;
                if(no_question==0){
                    startActivity(new Intent(PostQuestionActivity.this,MainActivity.class));
                }
                mPost.setText("Post("+no_question+" questions left)");
                mQuestion.setText("");
                mOpt1.setText("");
                mOpt2.setText("");
                mOpt3.setText("");
                mOpt4.setText("");
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(PostQuestionActivity.this,"You can't go back!!!",Toast.LENGTH_SHORT).show();
    }
}
