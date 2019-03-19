package com.aniket.tmvoter;

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
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_role_dynamic);
        listView=findViewById(R.id.asdf);
        membersList=new ArrayList<>();

        roleCardList=new ArrayList<>();
        roleCardList.add(new RoleCard("Toastmaster of the day","TM Aniket Rawat"));
        roleCardList.add(new RoleCard("TableTopic Master","TM Saloni Gupta"));
        roleCardList.add(new RoleCard("Heyy ","TM Prerna"));
        roleCardList.add(new RoleCard("Grammarian ","TM Zakkiuddin Gorakhpurwala"));

        ref = FirebaseDatabase.getInstance().getReference().child("Members");
        membersList = new ArrayList();
        customAdapter=new CustomAdapter(AddRoleDynamic.this,roleCardList);
        listView.setAdapter(customAdapter);

    }

}
