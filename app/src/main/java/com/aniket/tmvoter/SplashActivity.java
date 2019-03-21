package com.aniket.tmvoter;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
Runnable firePrep;
Handler handler;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseUtils.prepareDatabase();
        handler=new Handler();
        firePrep=new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(),UseAsActivity.class);
                startActivity(i);
                finish();
            }
        };
        handler.postDelayed(firePrep,3000);

    }
}
