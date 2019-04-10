package com.aniket.tmvoter;

import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoleCard {
    String roleName,personName;
    int votes;
    boolean filled;

    public RoleCard(String roleName, String personName,Boolean filled) {
        this.roleName = roleName;
        this.personName = personName;
        this.votes=0;
        this.filled=filled;
    }
    public RoleCard(String roleName, String personName) {
        this.roleName = roleName;
        this.personName = personName;
        this.votes=0;
        this.filled=false;
    }
    public RoleCard(String roleName, String personName,int votes,Boolean filled) {
        this.roleName = roleName;
        this.personName = personName;
        this.votes=votes;
        this.filled=filled;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public boolean getFilled() {
        return filled;
    }

}
