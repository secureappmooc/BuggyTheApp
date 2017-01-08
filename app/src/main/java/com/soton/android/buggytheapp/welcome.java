package com.soton.android.buggytheapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by AndroidHPE on 12/6/2016.
 */

public class welcome extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        String username= getIntent().getStringExtra("usernameSent");
        TextView tv= (TextView)findViewById(R.id.tvCurrentUser);
        tv.setText(username);

       /* if(!broadcaster.broadcasting) {
            broadcaster.broadcasting = true;
            Intent intent = new Intent(this, broadcaster.class);
            startService(intent);
        }*/

    }


    public void copyFile(View v) throws IOException {
        try {
            File backupDB = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "usersinfo_BACKUP.db");
            File workingDB = getApplicationContext().getDatabasePath("usersinfo.db");
            if (workingDB.exists())
            {
                FileInputStream fin = new FileInputStream(workingDB);
                FileOutputStream fout = new FileOutputStream(backupDB);
                fout.getChannel().transferFrom(fin.getChannel(), 0, fin.getChannel().size());
                fin.close();
                fout.close();
                Toast done=Toast.makeText(welcome.this, "The database backup is done successfully", Toast.LENGTH_SHORT);
                done.show();

            } else
            {
                Toast nodatabase=Toast.makeText(welcome.this, "Sorry, the database is not found", Toast.LENGTH_SHORT);
                nodatabase.show();
            }
        }
        catch (IOException e)
        {
            Toast cannoterror=Toast.makeText(welcome.this, "Sorry,for some reasons we cant backup your database", Toast.LENGTH_LONG);
            cannoterror.show();
        }
    }


    public void httpserverrequest(View v)
    {
        Intent i = new Intent(welcome.this, HRtest.class);
        startActivity(i);
    }

    public void startbroadcasting (View v)
    {
        if(!broadcaster.broadcasting) {
            broadcaster.broadcasting  = true;
            Intent intent = new Intent(this, broadcaster.class);
            startService(intent);

        }
    }

    public void stopbroadcasting (View v)
    {
        if(broadcaster.broadcasting) {
            broadcaster.broadcasting  = false;
            Intent intent = new Intent(this, broadcaster.class);
            startService(intent);

        }
    }


}
