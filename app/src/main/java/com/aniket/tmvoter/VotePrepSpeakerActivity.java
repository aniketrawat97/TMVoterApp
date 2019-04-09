package com.aniket.tmvoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VotePrepSpeakerActivity extends AppCompatActivity {
    CustomAdapter customAdapter;
    ListView listView;
    List<RoleCard> roleCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_prepspeaker);
        int spNo=0;
        listView=findViewById(R.id.list_vote_prep_sp);

        roleCardList=new ArrayList<>();


        Intent i=new Intent(getApplicationContext(),VoteEvaluaterActivity.class);

        customAdapter=new CustomAdapter(VotePrepSpeakerActivity.this,roleCardList,FirebaseUtils.getMembersList(),i);
try {
    for (int j = 0; j < FirebaseUtils.votesList.size(); j++) {
        Log.i("info", FirebaseUtils.roleList.get(j));
        if (FirebaseUtils.rolePlayerType(FirebaseUtils.roleList.get(j)).equals("PS")) {
            spNo++;
            Log.i("info", "Speaker found at " + j);
            roleCardList.add(new RoleCard("Prepared Speaker " + spNo, FirebaseUtils.getRolePlayer("Prepared Speaker " + spNo), true));
        }
    }
}catch(Exception e){
    Log.i("info", "Exception in prep - "+e);
}

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    TextView roleTemp=view.findViewById(R.id.role);

                Log.i("votes", roleTemp.getText().toString());
                String rolename=roleTemp.getText().toString();
                FirebaseUtils.incrementVotes(rolename);
                startActivity(new Intent(getApplicationContext(),VoteEvaluaterActivity.class));
                }catch (Exception e){
                    Log.i("info","Exception in listener -"+e);
                }
            }
        });

    }
}
