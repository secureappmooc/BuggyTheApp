package com.soton.android.buggytheapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AndroidHPE on 12/6/2016.
 */

public class DBclass extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="usersinfo.db";
    public static final String DATABASE_TABLE="usertable2";
    public static final String DATABASE_COL2="name";
    public static final String DATABASE_COL3="email";
    public static final String DATABASE_COL4="username";
    public static final String DATABASE_COL5="password";

    SQLiteDatabase db;

    private static final String CREAT_QUERY="create table usertable2 (name text, email text,username text,password text)";

    public DBclass(Context context)
    {

        super(context, DATABASE_NAME, null ,DATABASE_VERSION);
    }


    public boolean newuser(String newuserquery)
    {
        db= this.getWritableDatabase();
        try
        {
            db.execSQL(newuserquery);
        } catch (SQLException e)
        {
            db.close();
            return false;
        }
        db.close();
        return true;

    }

    public boolean searchpass(String loginUsername, String loginpass)
    {
        db= this.getReadableDatabase();
        String searchquery="select * from "+DATABASE_TABLE+" where username ='"+loginUsername+"' and password='"+loginpass+"';";
        Cursor cursor=db.rawQuery(searchquery,null);
        if (cursor.getCount()>0)
        {
            db.close();
            return true;
        } else
        {
            db.close();
            return false;
        }


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_QUERY);
        db.execSQL("INSERT INTO usertable2 (name,email,username,password) VALUES ('System Admin','admin@example.com','admin','impossible')");
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE IF EXIST"+ DATABASE_TABLE;
        db.execSQL(query);
        this.onCreate(db);
    }
}
