package com.cs7.chinmays.cicarv30;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cs7.chinmays.cicarv30.data.EventContract;

import static com.cs7.chinmays.cicarv30.R.string.organiser;

public class Main3Activity extends AppCompatActivity
{

    DatabaseHelper helper = new DatabaseHelper(this);
    EditText password6;
    EditText username6;
    EditText role;
    Session session;
    public int test=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        username6 = (EditText) findViewById(R.id.username5);     //new
        password6 = (EditText) findViewById(R.id.password5);
        session=new Session(this);



       /* if(session.loggedin())
        {
            startActivity(new Intent(Main3Activity.this,Home.class));
            finish();
        }*/

    }




    public void Signin(View v)
    {
        String username8 = username6.getText().toString();          //new
        String password8 = password6.getText().toString();  //values entered by user
        String pass = helper.searchPass(username8);  //password corresponding to the username from the database

        if(username8.trim().length() > 0 && password8.trim().length() > 0) {


            if (pass.equals(password8)) {
                String role = helper.searchRole(username8);
                 String oraganiser="organiser";
                 String performer="performer";
                 String participant="participant";
                if(role.equals(oraganiser))
                {
                    //session.setLoggedin(true);
                    session.createLoginSession(username8, password8);
                    //test=1;
                    Intent i = new Intent(this, Home.class);
                    startActivity(i);
                    finish();
                }

                if(role.equals(performer)||role.equals(participant))
                {
                    //session.setLoggedin(true);
                    session.createLoginSession(username8, password8);
                    //test=1;
                    Intent i = new Intent(this,PerformerProfile.class);
                    startActivity(i);
                    finish();
                }



            } else {
                Toast temp1 = Toast.makeText(Main3Activity.this, "Password Incorrect", Toast.LENGTH_LONG);
                temp1.show();
            }

        }
        else
        {
            Toast temp2 = Toast.makeText(Main3Activity.this, "Please fill all the fileds", Toast.LENGTH_LONG);
            temp2.show();
        }



    }


}

 /* public void Home3(View view)
    {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
    */
//get value of pair username and password in username9 and password9


/*
        if(username8==username9&&password8==password9)
        {
            //call intent to home page
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
            home2(null);
            //confirm value to pass ->null
        }

        else
        {
            Toast.makeText(getApplicationContext(), "Incorrect password.Try again", Toast.LENGTH_LONG).show();
        }
*/
//  }



/*
    public void home2(View view)
    {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
    }

*/