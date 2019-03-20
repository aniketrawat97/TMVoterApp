package com.aniket.tmvoter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

public class MemberListModifier {
    List membersList;
    DatabaseReference ref;

    public MemberListModifier() {
        ref=FirebaseDatabase.getInstance().getReference();
    }

    public List getMemberList () {
                return membersList;
            }
        public int getMemberListSize () {
            return membersList.size();
        }
        public void addMember (String key, String name){
            ref.child(key).setValue(name);
        }
}
