package com.aniket.tmvoter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MeetingCode extends AppCompatActivity {
    EditText c[];
    int it;
    String code,db_code;
    DatabaseReference fdb;
    TextView tryAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fdb= FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        it=0;
        code="";
        setContentView(R.layout.activity_meeting_code);
        c = new EditText[4];
        c[0]=findViewById(R.id.code1);
        c[1]=findViewById(R.id.code2);
        c[2]=findViewById(R.id.code3);
        c[3]=findViewById(R.id.code4);
        tryAgain=findViewById(R.id.try_again);
        c[0].requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        fdb.child("Meeting").child("meeting2").child("meeting_code").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                db_code=dataSnapshot.getValue(String.class);
                Log.i("codes", "db-"+db_code);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        onTextChange(c[it]);

    }

    public void onTextChange(EditText et){

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                code += s;
                it++;
                if (it < 4) {
                    c[it].requestFocus();
                    onTextChange(c[it]);
                }else{
                    Log.i("codes", "nondb-"+code);
                    if(code.equals(db_code)){
                        Log.i("codes", "Correct");

                    }
                    else{
                        Log.i("codes", "Wrong");
                        tryAgain.setVisibility(View.VISIBLE);
                    }
                    return;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
