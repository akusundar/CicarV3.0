package com.cs7.chinmays.cicarv30;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cs7.chinmays.cicarv30.data.EventContract.EventEntry;
import com.cs7.chinmays.cicarv30.data.EventDbHelper;

public class Performer_allevents extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int EVENT_LOADER =0;
    EventCAdapter mCursorAdapter;
    private EventDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performer_allevents);
        ListView eventlistview = (ListView) findViewById(R.id.list);

        View emptyView =findViewById(R.id.empty_view);
        eventlistview.setEmptyView(emptyView);
        eventlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)

            {
                Intent i = new Intent(Performer_allevents.this,Performer_Event.class);


                Uri currentEventUri = ContentUris.withAppendedId(EventEntry.CONTENT_URI,id);

                i.setData(currentEventUri);

                startActivity(i);

            }
        });

        mCursorAdapter = new EventCAdapter(this,null);
        eventlistview.setAdapter(mCursorAdapter);

        mDbHelper = new EventDbHelper(this);
        getLoaderManager().initLoader(EVENT_LOADER,null,this);

    }


    private  void displayDatabaseInfo()
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String projection[]=
        {

          EventEntry._ID,
            EventEntry.COLUMN_NAME,
            EventEntry.COLUMN_DATE,
            EventEntry.COLUMN_TIME,
            EventEntry.COLUMN_VENUE
        };

        Cursor cursor = db.query
                (EventEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null);


        ListView displayView = (ListView) findViewById(R.id.list);
        EventCAdapter adapter =new EventCAdapter(this,cursor);
        displayView.setAdapter(adapter);

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String projection[]=
                {

                        EventEntry._ID,
                        EventEntry.COLUMN_NAME,
                        EventEntry.COLUMN_DATE,};
        return new CursorLoader(this,
                EventEntry.CONTENT_URI,
                projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
        {
mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}