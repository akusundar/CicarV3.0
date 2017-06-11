package com.cs7.chinmays.cicarv30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    Session session;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void signup(View view)
    {
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
        finish();
    }

    public void signin(View view)
    {
        Intent i=new Intent(this,Main3Activity.class);
        startActivity(i);
        finish();
    }

    public void onBackPressed()
    {


        super.onBackPressed();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();

    }



}






