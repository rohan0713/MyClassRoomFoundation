package com.example.myclassroomfoundation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import androidx.biometric.BiometricPrompt;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;

    private static int SPLASH_SCREEN_TIME_OUT=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent i = new Intent(MainActivity.this, FirstActivity.class);
                    startActivity(i);
                    finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}