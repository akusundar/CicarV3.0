package com.cs7.chinmays.cicarv30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
public class Home extends AppCompatActivity {
    Session session;
    Main2Activity obj;
String organiser ="organiser";
    String participant ="participant";
    String performer ="performer";
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new Session(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.loggedin(), Toast.LENGTH_LONG).show();
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        // name
        String name = user.get(Session.key_name);
        // password
        String pass = user.get(Session.key_password);
        Toast.makeText(getApplicationContext(), "Username:- "+name+" Password:-"+pass, Toast.LENGTH_LONG).show();


        //session.checkLogin();
    }


    public void  profile(View view)
    {



                Intent i = new Intent(this, MyProfile.class);
                session.checkLogin();
                startActivity(i);




    }


   //to do put event code here

   /* public void events9(View view)
    {
        Intent i=new Intent(this,Eventspage.class);
        session.checkLogin();
        startActivity(i);
    }*/

    public void about(View view)
    {
        Intent i=new Intent(this,About.class);
        session.checkLogin();
        startActivity(i);
    }





    /*public void logout()
    {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(Home.this,Main3Activity.class));

    }*/

}