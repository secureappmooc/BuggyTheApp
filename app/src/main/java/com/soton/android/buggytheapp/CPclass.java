package com.soton.android.buggytheapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by AndroidHPE on 12/6/2016.
 */

public class CPclass extends ContentProvider {

    static final String CP_NAME= "com.BuggyTheApp.ContentProvider";
    static final Uri MYAPP_USERS_URI= Uri.parse("content://"+CP_NAME+"/"+DBclass.DATABASE_TABLE);
    static final int CPUSERS=1;
    SQLiteDatabase db;
    DBclass dBclass;

    // Define UriMatcher

    static final UriMatcher uriMatcher;

    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CP_NAME, DBclass.DATABASE_TABLE, CPUSERS);
    }



    @Override
    public boolean onCreate()
    {
        dBclass =new DBclass(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        db = dBclass.getReadableDatabase();
        Cursor cursor= db.query(DBclass.DATABASE_TABLE,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        db = dBclass.getWritableDatabase();
        // the if statement is just for demonstration if you have more than one URI in your content provider.
        if (uriMatcher.match(uri)==CPUSERS)
        {
            db.insert(DBclass.DATABASE_TABLE, null, values);
        }
        db.close();
        getContext().getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}