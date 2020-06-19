package com.example.myquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;//add these
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Session session;
    Intent intent;
    AlertDialog.Builder builder;
    private ActionBar toolbar;
    private BottomNavigationView b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();
        session = new Session(MainActivity.this);
        builder = new AlertDialog.Builder(MainActivity.this);
        BottomNavigationView navigationView =(BottomNavigationView)findViewById(R.id.botnav);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//setOnlistner type func must be called in oncreate
        toolbar = getSupportActionBar();
        toolbar.setTitle("Home");
        loadFragment(new Home_Fragment());


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.icon1:
                    toolbar.setTitle("Home");
                    fragment = new Home_Fragment();
                    loadFragment(fragment);
                    return true;


                case R.id.icon2:
                    toolbar.setTitle("Topics");
                    fragment = new Topics_Fragment();
                    loadFragment(fragment);
                    return true;

                case R.id.icon3:
                    toolbar.setTitle("Leaderboard");
                    fragment = new LeaderboardFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.icon4:
                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }

    };

        private void loadFragment(Fragment fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.create_quiz){
            startActivity(new Intent(MainActivity.this,SelectSubject.class));
        }
        else if(item.getItemId() == R.id.notification){
            startActivity(new Intent(MainActivity.this,NotificationActivity.class));
        }
        else if(item.getItemId() == R.id.signOut){
            startSignOut();
        }
        else if(item.getItemId() == R.id.exitApp){
            ExitApp();
        }
        return super.onOptionsItemSelected(item);
    }

    public void ExitApp(){
            dialogBox("Exit");
    }

    public void startSignOut(){
            dialogBox("Sign Out");
    }

    @Override
    public void onBackPressed() {
        ExitApp();
    }

    void dialogBox(final String key){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom);
        TextView text = dialog.findViewById(R.id.text);
        text.setAlpha(0.0f);
        TextView text1 = dialog.findViewById(R.id.text1);
        text1.setText(key);
        TextView text2 = dialog.findViewById(R.id.text2);
        text2.setText("Are you sure you want to "+key+"?");
        Button ok = (Button) dialog.findViewById(R.id.positive);
        ok.setText("Ok");
        Button cancel = (Button) dialog.findViewById(R.id.negative);
        cancel.setText("Cancel");
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(key.equals("Exit")){
                    finishAffinity();
                }
                else if(key.equals("Sign Out")){
                    session.logoutUser();
                }
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
}


