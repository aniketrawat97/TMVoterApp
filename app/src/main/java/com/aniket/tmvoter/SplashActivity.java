package com.aniket.tmvoter;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    Runnable firePrep;
    Handler handler;
    TextView noInternet;



    public static boolean isNetworkAvailable(Context con) {
        try {
            ConnectivityManager cm = (ConnectivityManager) con
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        noInternet = findViewById(R.id.nointernet);
        FirebaseUtils.prepareDatabase();
        noInternet.setVisibility(View.INVISIBLE);
        handler = new Handler();

        if (isNetworkAvailable(this)) {
            firePrep = new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), UseAsActivity.class);
                    startActivity(i);
                    finish();
                }
            };
        } else {
            noInternet.setVisibility(View.VISIBLE);

        }

        handler.postDelayed(firePrep, 3000);

    }


}
