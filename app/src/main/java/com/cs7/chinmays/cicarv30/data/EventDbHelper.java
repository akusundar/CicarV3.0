package com.cs7.chinmays.cicarv30.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cs7.chinmays.cicarv30.DatabaseHelper;
import com.cs7.chinmays.cicarv30.data.EventContract.EventEntry;

import static com.cs7.chinmays.cicarv30.data.EventContract.EventEntry.TABLE_NAME;
import static com.cs7.chinmays.cicarv30.data.EventContract.EventEntry.TABLE_NAME2;
import static com.cs7.chinmays.cicarv30.data.EventContract.EventEntry.TABLE_NAME3;
import static java.sql.Types.INTEGER;

public class EventDbHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "MainEvents.db";

    public EventDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EventEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + EventEntry.COLUMN_TIME + " INTEGER NOT NULL, "
                + EventEntry.COLUMN_VENUE + " INTEGER NOT NULL, "
                + EventEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + EventEntry.LATITUDE + " INTEGER, "
                + EventEntry.COLUMN_IMAGE + " BLOB, "
                + EventEntry.LONGITUDE + " INTEGER, "
                + EventEntry.MATERIAL_NAME + " TEXT, "
                + EventEntry.COLUMN_DATE + " DATE TYPE CHAR(20) NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_EVENTS_TABLE);


        String SQL_CREATE_VENUE_TABLE = "CREATE TABLE " + TABLE_NAME2 + " ("
                + EventEntry._ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EventEntry.COLUMN_VENUE_NAME + " TEXT NOT NULL, "
                + EventEntry.COLUMN_VENUE_LOCATION + " TEXT NOT NULL, "
                + EventEntry.COLUMN_VENUE_COST + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_VENUE_TABLE);

        String SQL_CREATE_MATERIAL_TABLE = "CREATE TABLE " + TABLE_NAME3 + " ("
                + EventEntry._ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EventEntry.COLUMN_MATERIAL_NAME + " TEXT NOT NULL,"
                + EventEntry.COLUMN_MATERIAL_QUANTITY + " TEXT NOT NULL,"
                + EventEntry.COLUMN_MATERIAL_COST + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_MATERIAL_TABLE);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }


}