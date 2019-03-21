package com.aniket.tmvoter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddRoleDynamic extends AppCompatActivity {
    CustomAdapter customAdapter;
    ListView listView;
    List<RoleCard> roleCardList;
    static List<String >membersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_role_dynamic);
        listView=findViewById(R.id.asdf);
        membersList=new ArrayList<>();

        roleCardList=new ArrayList<>();
        roleCardList.add(new RoleCard("Toastmaster Of The Day",""));
        roleCardList.add(new RoleCard("TableTopic Master",""));
        roleCardList.add(new RoleCard("General Evaluator",""));
        roleCardList.add(new RoleCard("Grammarian",""));

        membersList = new ArrayList();
        Intent i=new Intent(getApplicationContext(),AddAuxilaryRoleplayerActivity.class);
        customAdapter=new CustomAdapter(AddRoleDynamic.this,roleCardList,FirebaseUtils.getMembersList(),i);
        listView.setAdapter(customAdapter);
    }
}
