package com.aniket.tmvoter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class GeneratingCodeActivity extends AppCompatActivity {
    ImageView generatebox;
    TextView c1, c2, c3, c4;
    DatabaseReference fdb;
    TextView boxText;
    Handler handler;
    Runnable firePrep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fdb = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_generating_code);
        boxText=findViewById(R.id.generate_me);
        c1 = findViewById(R.id.gcode1);
        c2 = findViewById(R.id.gcode2);
        c3 = findViewById(R.id.gcode3);
        c4 = findViewById(R.id.gcode4);
        generatebox = findViewById(R.id.generating_code_box);
        generatebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mc = new Random().nextInt(10000);
                Log.i("Random", Integer.toString(mc));
                setMC(mc);
                String mcs = c1.getText().toString()
                        + c2.getText().toString()
                        + c3.getText().toString()
                        + c4.getText().toString();

                Log.i("Random", mcs + "  is string");

                boxText.setText("Vote");

                fdb.child("Meeting").child("meeting2").child("meeting_code").setValue(mcs);
                generatebox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        findViewById(R.id.progress).setVisibility(View.VISIBLE);

                        FirebaseUtils.prepareDatabase();

                        handler = new Handler();

                            firePrep = new Runnable() {
                                @Override
                                public void run() {

                                    Intent i = new Intent(getApplicationContext(),MeetingCodeActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            };

                        handler.postDelayed(firePrep, 3000);

                        }
                });
            }
        });

    }

    public void setMC(int a) {
        c4.setText(Integer.toString(a % 10));
        a /= 10;
        c3.setText(Integer.toString(a % 10));
        a /= 10;
        c2.setText(Integer.toString(a % 10));
        a /= 10;
        c1.setText(Integer.toString(a % 10));

    }

    class Meeting{
        String meeting_code;
        Date date;
        public Meeting(String code){
            meeting_code=code;
        }
    }


    //To make Double Back press to exit

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}