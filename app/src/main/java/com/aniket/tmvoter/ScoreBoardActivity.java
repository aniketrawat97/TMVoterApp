package com.aniket.tmvoter;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoardActivity extends AppCompatActivity {
    CustomAdapter customAdapter;
    ListView listView;
    static List<RoleCard> roleCardList;
    ImageView result;
    static ArrayList<String> nameList,roleList,votesList;
    DatabaseReference db;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        listView=findViewById(R.id.list_score);
        result=findViewById(R.id.resultImageView);

        nameList=new ArrayList<>();
        roleList=new ArrayList<>();
        votesList=new ArrayList<>();
        roleCardList=new ArrayList<>();


        i=new Intent(getApplicationContext(),VoteEvaluaterActivity.class);

        try {
            for (int j = 0; j < FirebaseUtils.votesList.size(); j++) {
                Log.i("info", FirebaseUtils.roleList.get(j));
                    Log.i("info", "Speaker found at " + j);
                    roleCardList.add(new RoleCard(FirebaseUtils.roleList.get(j), FirebaseUtils.nameList.get(j)+" -  "+FirebaseUtils.votesList.get(j)+" Votes",true));
            }
        }catch(Exception e){
            Log.i("info", "Exception in prep - "+e);
        }


        db =FirebaseDatabase.getInstance().getReference();
        db.child("Meeting").child("meeting2").child("vote").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("asdf", "onDataChange: ");
                roleCardList.clear();
                for(DataSnapshot myChild : dataSnapshot.getChildren()){
                    roleCardList.add(new RoleCard(myChild.getKey(), myChild.child("name").getValue(String.class)+
                                                                         " -  "+myChild.child("votes").getValue(Long.class).toString()
                                                                                +" Votes",Integer.parseInt(myChild.child("votes").getValue(Long.class).toString()),true));
                }
                customAdapter=new CustomAdapter(ScoreBoardActivity.this,roleCardList,FirebaseUtils.getMembersList(),i);
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUtils.prepareDatabase();
                i=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(i);
            }
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
