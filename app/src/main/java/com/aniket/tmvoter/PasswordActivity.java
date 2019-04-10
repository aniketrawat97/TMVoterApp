package com.aniket.tmvoter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PasswordActivity extends AppCompatActivity {
    EditText c[];
    int it;
    String code,db_code;
    DatabaseReference fdb;
    TextView tryAgain;
    static List membersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fdb= FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        it=0;
        code="";
        membersList=new ArrayList();
        setContentView(R.layout.activity_password);
        c = new EditText[4];
        c[0]=findViewById(R.id.pcode1);
        c[1]=findViewById(R.id.pcode2);
        c[2]=findViewById(R.id.pcode3);
        c[3]=findViewById(R.id.pcode4);
        tryAgain=findViewById(R.id.try_again);
        c[0].requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        db_code=FirebaseUtils.getPassword();
        Log.i("timing", "Password value assigned "+db_code);
        onTextChange(c[it]);
    }

    public void onTextChange(EditText et){
        Log.i("timing", "onTextChange: ");
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
                    Log.i("codes", "nondb- "+code);
                    Log.i("codes", "db- "+db_code);
                    if(db_code.equals("")){
                        Log.i("codes", "empty");
                    }
                    if(code.equals(db_code)){
                        Log.i("codes", "Correct");
                        startActivity(new Intent(PasswordActivity.this, AddRoleplayer.class));
                        Toast.makeText(PasswordActivity.this, "Welcome Administrator", Toast.LENGTH_LONG).show();
                        tryAgain.setTextColor(Color.GREEN);
                    }
                    else{
                        Log.i("codes", "Wrong");
                        tryAgain.setVisibility(View.VISIBLE);
                        tryAgain.setTextColor(Color.WHITE);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
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
