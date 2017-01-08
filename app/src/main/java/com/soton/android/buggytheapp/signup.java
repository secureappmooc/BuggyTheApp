package com.soton.android.buggytheapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.soton.android.buggytheapp.DBclass.DATABASE_TABLE;

/**
 * Created by AndroidHPE on 12/6/2016.
 */

public class signup extends Activity {
    DBclass mydb= new DBclass(this);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
    }

    public void onsignclicked (View v)
    {
        if (v.getId()==R.id.btnSignUp)
        {
            EditText name= (EditText)findViewById(R.id.etName);
            EditText email= (EditText)findViewById(R.id.etEmail);
            EditText username= (EditText)findViewById(R.id.etUserName);
            EditText pass= (EditText)findViewById(R.id.etPassword);
            EditText passconf= (EditText)findViewById(R.id.etPassconfirm);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String usernamestr = username.getText().toString();
            String passstr = pass.getText().toString();
            String passconfstr = passconf.getText().toString();

            if (!passstr.equals(passconfstr))
            {
                Toast passmsg=Toast.makeText(signup.this, "Passwords does not match", Toast.LENGTH_SHORT);
                passmsg.show();
            }else if (!usernamestr.isEmpty() && !passstr.isEmpty() && !namestr.isEmpty())
            {
                String newuserQuery="INSERT INTO "+ DATABASE_TABLE +" (name, email, username, password)" +
                        "VALUES ('"+namestr+"', '"+emailstr+"', '"+usernamestr+"', '"+passconfstr+"');";

                if(mydb.newuser(newuserQuery))
                {
                    Toast registered=Toast.makeText(signup.this, "Thank you, you can log in now", Toast.LENGTH_SHORT);
                    registered.show();
                    ((EditText) findViewById(R.id.etName)).setText("");
                    ((EditText) findViewById(R.id.etEmail)).setText("");
                    ((EditText) findViewById(R.id.etUserName)).setText("");
                    ((EditText) findViewById(R.id.etPassconfirm)).setText("");
                    ((EditText) findViewById(R.id.etPassword)).setText("");
                } else
                {
                    Toast notregistered=Toast.makeText(signup.this, "for some reasons we cant register you", Toast.LENGTH_SHORT);
                    notregistered.show();
                }
            }
            else
            {
                Toast nodata=Toast.makeText(signup.this, "You did not provide data for one of the fields", Toast.LENGTH_SHORT);
                nodata.show();
            }
        }
    }




}
