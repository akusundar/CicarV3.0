package com.cs7.chinmays.cicarv30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyProfile extends AppCompatActivity {

    Session session;
    Main3Activity m3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        session=new Session(this);

    }

   //to do put code for create event

    /* public void Imageviewer(View view)
    {
        Intent i = new Intent(this,Insert.class);
        startActivity(i);
    }*/

   /* public void single_event(View view)
    {
        Intent i = new Intent(this, Single_event.class);
        startActivity(i);
    }*/



    public void logout(View view)
    {
        //session.setLoggedin(false);
        //finish();
        //m3.test=0;
        session.logoutUser();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }
public void create_event(View view)
{
    Intent i = new Intent(this, CreateEvent.class);
    startActivity(i);
}

    public void allevents(View view)
    {
        Intent i = new Intent(this, AllEvents.class);
        startActivity(i);
    }


   public void aku(View view)
   {
       Intent i = new Intent(this, CreateVenue.class);
       startActivity(i);
   }


    public void Material_add(View view)
    {
        Intent i = new Intent(this, Create_Material.class);
        startActivity(i);
    }
    public void view_material(View view)
    {
        Intent i = new Intent(this, ViewMaterial.class);
        startActivity(i);
    }


    public void vv(View view)
    {
        Intent i = new Intent(this, ViewVenue.class);
        startActivity(i);
    }


}

/* public void create(View view)
    {
        Intent i = new Intent(this, Create_Activity.class);
        startActivity(i);
    }*/