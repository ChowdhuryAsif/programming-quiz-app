package com.example.programming_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent i=new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                catch (InterruptedException e){

                    e.printStackTrace();
                }
            }
        });
    }
}