package com.soton.android.buggytheapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBclass mydb= new DBclass(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }







    public void onLoginClicked (View v)
    {
        if (v.getId()==R.id.btnLogin)
        {
            EditText username = (EditText)findViewById(R.id.etUsrName);
            String usernamestr= username.getText().toString();

            EditText varpass = (EditText)findViewById(R.id.etPass);
            String varpassstr= varpass.getText().toString();

            if (mydb.searchpass(usernamestr,varpassstr))
            {
                Intent i = new Intent(MainActivity.this, welcome.class);
                i.putExtra("usernameSent", usernamestr);
                startActivity(i);
            } else
            {
                Toast lpassmsg=Toast.makeText(MainActivity.this, "Password or Username does not match", Toast.LENGTH_SHORT);
                lpassmsg.show();
            }


        }
        if (v.getId()==R.id.btnNewuser)
        {
            Intent i = new Intent(MainActivity.this, signup.class);
            startActivity(i);
        }
    }
}
