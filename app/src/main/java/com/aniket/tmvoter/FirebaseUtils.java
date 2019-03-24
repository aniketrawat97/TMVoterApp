package com.aniket.tmvoter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseUtils {

    static List membersList;
    static String password,meetingCode;
    static DatabaseReference ref;
    static DataSnapshot snapshot;
    static ArrayList<String> nameList,roleList,votesList;


    public static void prepareDatabase(){
        ref=FirebaseDatabase.getInstance().getReference();
        nameList=new ArrayList<>();
        roleList=new ArrayList<>();
        votesList=new ArrayList<>();
        password="";
        meetingCode="";
        downloaddataSnapshot();
    }

    //downloading data

    public static void downloaddataSnapshot(){     //downloading dataSnapshot to save network usage in frequent access
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("infos", "onDataChange: ");
                Log.i("timing", "downloaddataSnapshot: Downloaded");
                snapshot=dataSnapshot;
                prepareMembersList();
                prepareMeetingCode();
                preparePassword();
                prepareVotes();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

    }

    //uploading data
    public static void addMember(String key, String name) {
        ref.child(key).setValue(name);
    }

    public static void updateMeetingCode(String meetingCode){
        ref.child("Meeting").child("meeting2").child("meeting_code").setValue(meetingCode);
    }

    public static void updateCandidateName(String name, String role,int votes) {
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Meeting").child("meeting2").child("vote").child(role).child("name").setValue(name);
        ref.child("Meeting").child("meeting2").child("vote").child(role).child("votes").setValue(votes);
    }

    public static void updateVotes(String name, String role, int v) {
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Meeting").child("meeting2").child("vote").child(role).setValue(name);
        ref.child("Meeting").child("meeting2").child("vote").child(role).setValue(v);
    }


    //Preparing values
    public static void prepareMembersList(){
        Map<String,String> membermap=(HashMap<String,String>) snapshot.child("Members").getValue();
        membersList =new ArrayList<>(membermap.values());
        Collections.sort(membersList);
    }

    public static void preparePassword(){
        password=snapshot.child("Password").getValue(String.class);
        Log.i("timing", "Password Prepared " +password);
    }

    public static void prepareMeetingCode(){
        meetingCode=snapshot.child("Meeting").child("meeting2").child("meeting_code").getValue(String.class);
    }

    public static void prepareVotes(){
        Map<String,ArrayList<String>> votermaps = (HashMap<String,ArrayList<String>>) snapshot.child("Meeting").child("meeting2").child("vote").getValue();
        for(DataSnapshot myChild : snapshot.child("Meeting").child("meeting2").child("vote").getChildren()){
            roleList.add(myChild.getKey());                 //get key  of the object that is added to the roleList
            nameList.add(myChild.child("name").getValue(String.class));   //go to name child and get value
            votesList.add(myChild.child("votes").getValue(Long.class).toString());        //go to vote child and get value
        }
    }

    public static void prepareAllValues(){

        prepareMembersList();

        prepareMeetingCode();

        preparePassword();

        prepareVotes();

    }  // Calls all the prepare methods


    //Getters
    public static List getMembersList() {
        return membersList;
    }

    public static String getPassword() {
        return password;
    }

    public static String getMeetingCode() {
        return meetingCode;
    }

    public static String getName(int i) {
        return nameList.get(i);
    }

    public static String getRole(int i) {
        return roleList.get(i);
    }

    public static String getVotes(int i) {
        return votesList.get(i);
    }

    public static String rolePlayerType(String role,String name){

        if(role.equals("Timer")||role.equals("Ah Counter")||role.equals("Hark Master")){
            return "ARP";
        }
        if(role.equals("Toastmaster Of The Day")||role.equals("TableTopic Master")||role.equals("Grammarian")||role.equals("General Evaluator")){
            return "RP";
        }
        if(role.subSequence(0,10).equals("TableTopic")){
            return "TT";
        }
        if(role.subSequence(0,10).equals("TableTopic")){
            return "TT";
        }
        return "NONE";
    }

    public static String getRolePlayer(String role){
        return nameList.get(roleList.indexOf(role));
    }
    public static void incrementVotes(String role){
        int index=FirebaseUtils.roleList.indexOf(role);
        int votes=Integer.parseInt( votesList.get(index) )+1;
        votesList.set( index,Integer.toString(votes));
        Log.i("votes", "Votes - "+FirebaseUtils.votesList.get(index));
    }
}
