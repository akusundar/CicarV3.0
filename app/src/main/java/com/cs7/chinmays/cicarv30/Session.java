package com.cs7.chinmays.cicarv30;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Session
{
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    int Private_Mode=0;
    public static final String key_name="name";
    public static final String key_password="password";
    public static final String IS_LOGIN="ISLoggedIn";


    public Session(Context ctx)
    {
        this.ctx=ctx;
        prefs=ctx.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        editor=prefs.edit();

    }

    public void createLoginSession(String name,String password)
    {
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(key_name,name);
        editor.putString(key_password,password);
        editor.commit();
    }


    public void setLoggedin(boolean loggedin)
    {
        editor.putBoolean("loggedinmode",loggedin);
        editor.commit();

    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(key_name, prefs.getString(key_name, null));

        // user email id
        user.put(key_password, prefs.getString(key_password, null));

        // return user
        return user;
    }


    public boolean loggedin()
    {

        return prefs.getBoolean(IS_LOGIN,false);
    }


    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */


    public void checkLogin(){
        // Check login status
        if(!this.loggedin()){
            // user is not logged in redirect him to Login Activity

            Intent i = new Intent(ctx, MainActivity.class);

            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            ctx.startActivity(i);
        }

    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(ctx, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        ctx.startActivity(i);
    }


}

