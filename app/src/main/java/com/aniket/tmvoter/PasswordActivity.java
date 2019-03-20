package com.aniket.tmvoter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
        fdb.child("Password").addListenerForSingleValueEvent(new ValueEventListener() {
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
                        Toast.makeText(PasswordActivity.this, "Welcome Admin", Toast.LENGTH_LONG).show();
                        tryAgain.setTextColor(Color.GREEN);
                        toNextActivity();
                    }
                    else{
                        Log.i("codes", "Wrong");
                        tryAgain.setVisibility(View.VISIBLE);
                        tryAgain.setTextColor(Color.WHITE);
                    }
                    return;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    public void toNextActivity(){
        fdb.child("Members").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String,String> membermap=(HashMap<String,String>) dataSnapshot.getValue();
                membersList =new ArrayList<>(membermap.values());
                Collections.sort(membersList);
                Intent intent=new Intent(getApplicationContext(),AddRoleDynamic.class);
                startActivity(intent);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
    public static List getMembersList() {
        return membersList;
    }
}
