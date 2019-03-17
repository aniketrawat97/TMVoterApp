package com.aniket.tmvoter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.sql.Types.INTEGER;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABSE_NAME = "Votecount";

    private static final String TABLE_USER = "votedata";

    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String USER_ROLE = "role";
    private static final String USER_VOTECONUT = "votecount";
    private static final String USER_TYPE = "type";

    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME
            + " TEXT," + USER_ROLE + " TEXT," + USER_TYPE
            + " TEXT," +USER_VOTECONUT + " INTEGER " + ")";




    public DatabaseHelper(Context context) {
        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
    }


    public boolean insertdata(String name, String role,String type,int votecount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME,name);
        contentValues.put(USER_ROLE,role);
        contentValues.put(USER_TYPE,type);
        contentValues.put(USER_VOTECONUT,votecount);
        long result = db.insert(TABLE_USER,null,contentValues);
        if(result == -1)
          return  false;
        else
            return  true;
    }


    public Cursor getalldata()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_USER,null);
        return res;
    }

}
