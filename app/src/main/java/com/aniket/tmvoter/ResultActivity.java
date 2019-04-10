package com.aniket.tmvoter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    TextView ps_name1,ps_name2,tt_name1,tt_name2,tt_name3,rp_name,arp_name,eval_name;
    TextView ps_votes1,ps_votes2,tt_votes1,tt_votes2,tt_votes3,rp_votes,arp_votes,eval_votes;
    ArrayList<RoleCard> list;
    List <RoleCard>roleCardArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ps_name1=findViewById(R.id.nameps);
        ps_name2=findViewById(R.id.namepssecond);
        tt_name1=findViewById(R.id.ttname);
        tt_name2=findViewById(R.id.ttsecname);
        tt_name3=findViewById(R.id.tthreename);
        rp_name=findViewById(R.id.roleplayername);
        arp_name=findViewById(R.id.auxroleplayername);
        eval_name=findViewById(R.id.evaluatername);

        ps_votes1=findViewById(R.id.count);
        ps_votes2=findViewById(R.id.seccount);
        tt_votes1=findViewById(R.id.ttbcount);
        tt_votes2=findViewById(R.id.ttseccount);
        tt_votes3=findViewById(R.id.tthreecount);
        rp_votes=findViewById(R.id.rolecount);
        arp_votes=findViewById(R.id.roleauxcount);
        eval_votes=findViewById(R.id.evalcount);

        list=new ArrayList<>();
        roleCardArrayList=ScoreBoardActivity.roleCardList;

        BestSetter();
    }
    void BestSetter(){

        // For TableTopic
        for( int i=0;i<roleCardArrayList.size();i++){
            if(FirebaseUtils.rolePlayerType(roleCardArrayList.get(i).roleName).equals("TT")){
                list.add(roleCardArrayList.get(i));
            }
        }

        sortList();

        tt_name1.setText(list.get(0).personName);
        tt_name2.setText(list.get(1).personName);
        tt_name3.setText(list.get(2).personName);

        tt_votes1.setText(Integer.toString(list.get(0).votes));
        tt_votes2.setText(Integer.toString(list.get(1).votes));
        tt_votes3.setText(Integer.toString(list.get(2).votes));

        list.clear();

        // For Prepared Speaker
        for( int i=0;i<roleCardArrayList.size();i++){
            if(FirebaseUtils.rolePlayerType(roleCardArrayList.get(i).roleName).equals("PS")){
                list.add(roleCardArrayList.get(i));
            }
        }

        sortList();

        ps_name1.setText(list.get(0).personName);
        ps_name2.setText(list.get(1).personName);

        ps_votes1.setText(Integer.toString(list.get(0).votes));
        ps_votes2.setText(Integer.toString(list.get(1).votes));

        list.clear();

        //For Roleplayer
        for( int i=0;i<roleCardArrayList.size();i++){
            if(FirebaseUtils.rolePlayerType(roleCardArrayList.get(i).roleName).equals("RP")){
                list.add(roleCardArrayList.get(i));
            }
        }
        sortList();

        rp_name.setText(list.get(0).personName);
        rp_votes.setText(Integer.toString(list.get(0).votes));

        list.clear();

        //For AuxRoleplayer
        for( int i=0;i<roleCardArrayList.size();i++){
            if(FirebaseUtils.rolePlayerType(roleCardArrayList.get(i).roleName).equals("ARP")){
                list.add(roleCardArrayList.get(i));
            }
        }

        sortList();

        arp_name.setText(list.get(0).personName);
        arp_votes.setText(Integer.toString(list.get(0).votes));

        list.clear();

        //For Evaluator
        for( int i=0;i<roleCardArrayList.size();i++){
            if(FirebaseUtils.rolePlayerType(roleCardArrayList.get(i).roleName).equals("EV")){
                list.add(roleCardArrayList.get(i));
            }
        }
        sortList();

        eval_name.setText(list.get(0).personName);
        eval_votes.setText(Integer.toString(list.get(0).votes));

        list.clear();

    }

    void sortList() {
        RoleCard temp ;

        for (int i = 0; i < list.size()-1; i++) {

            for (int j = i+1; j < list.size(); j++) {

                if (list.get(j).votes > list.get(i).votes) {
                    temp = list.get(j);
                    list.set(j,list.get(i));
                    list.set(i,temp);
                }
            }
        }
    }

}
