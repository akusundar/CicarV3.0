package com.cs7.chinmays.cicarv30;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

import static android.content.ContentValues.TAG;

/**
 * Created by cs7 on 12/14/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_name = "cicar.db";
    public static final String Table_name = "Signup";
    public static final String COL_1 = "name";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";
    public static final String COL_4 = "role";


    public DatabaseHelper(Context context) {
        super(context, Database_name, null, 1);
        //SQLiteDatabase db=this.getReadableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + Table_name + "(name TEXT,username TEXT PRIMARY KEY,password TEXT,role TEXT)");
        //Toast.makeText(Context ,"table created", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists" + Table_name);
        onCreate(db);
    }


    public boolean insertData(String name2, String username2, String password2, String role2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, name2);
        contentValues.put(COL_2, username2);
        contentValues.put(COL_3, password2);
        contentValues.put(COL_4, role2);
        long result = db.insert(Table_name, null, contentValues);
        db.close();

        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_name, null);
        return res;
    }

    public boolean updateData(String name3, String username3, String password3, String role3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name3);
        contentValues.put(COL_2, username3);
        contentValues.put(COL_3, password3);
        contentValues.put(COL_4, role3);

        db.update(Table_name, contentValues, "name = ?", new String[]{name3});
        db.update(Table_name, contentValues, "username = ?", new String[]{username3});
        db.update(Table_name, contentValues, "password = ?", new String[]{password3});
        db.update(Table_name, contentValues, "role = ?", new String[]{role3});
        return true;
    }

   /* public Integer deleteData(String name4) {          //String username4,String password4,String role4) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_name, "name = ?", new String[]{name4});

    }*/


    public String searchPass(String uname) {
        SQLiteDatabase db = this.getReadableDatabase();


        String query3 = "SELECT username,password FROM " + Table_name;
        Cursor cursor1 = db.rawQuery(query3, null);
        String a;
        String b = "Not found";


        if (cursor1 != null && cursor1.getCount() > 0) {

            if (cursor1.moveToFirst()) {
                do {
                    a = cursor1.getString(0);

                    if (a.equals(uname)) {
                        b = cursor1.getString(1);
                    }
                }
                while (cursor1.moveToNext());
            }

        }
        return b;

    }

    public String searchRole(String uname) {
        SQLiteDatabase db = this.getReadableDatabase();


        String query3 = "SELECT username,role FROM " + Table_name;
        Cursor cursor1 = db.rawQuery(query3, null);
        String a;
        String b = "Not found";


        if (cursor1 != null && cursor1.getCount() > 0) {

            if (cursor1.moveToFirst()) {
                do {
                    a = cursor1.getString(0);

                    if (a.equals(uname)) {
                        b = cursor1.getString(1);
                    }
                }
                while (cursor1.moveToNext());
            }

        }
        return b;


    }
}