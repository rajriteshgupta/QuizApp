package com.example.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {
    EditText uName,pass;
    Button signIn;
    TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        uName = findViewById(R.id.uname);
        pass = findViewById(R.id.pass);
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    public void OnLogin(View view) {
        String username = uName.getText().toString();
        String password = pass.getText().toString();
        String type = "login";
        if(!username.isEmpty() && !password.isEmpty()){
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password);
        }
        else {
            Toast.makeText(LogInActivity.this,"Some Fields are Empty!!!",Toast.LENGTH_LONG).show();
        }
    }
}