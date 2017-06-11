package com.cs7.chinmays.cicarv30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PerformerProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performer_profile);
    }
    public void allevents (View view)
    {
        Intent i = new Intent(this, Performer_allevents.class);
        startActivity(i);
    }
}
