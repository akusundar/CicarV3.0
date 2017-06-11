package com.cs7.chinmays.cicarv30;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.cs7.chinmays.cicarv30.data.EventContract.EventEntry;

import com.cs7.chinmays.cicarv30.data.EventContract;

public class Performer_Event extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>
{
    private TextView mNameTextView;
    private TextView mDateTextView;
    private TextView mTimeTextView;
    private TextView mVenueTextView;
    private Spinner mVenueSpinner;
    private static final int EVENT_LOADER = 0;
    private int mVenue = 0;
    private Uri mcurrentEventUri;
    EventCAdapter mCursorAdapter;
    private static final int EXISTING_EVENT_LOADER=0;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performer__event);

        Intent intent = getIntent();
        mcurrentEventUri = intent.getData();
        if (mcurrentEventUri == null) {
            // This is a new pet, so change the app bar to say "Add a Pet"
            setTitle("Add event");
        } else {
            // Otherwise this is an existing pet, so change app bar to say "Edit Pet"
            setTitle("Current Event");

            // Initialize a loader to read the pet data from the database
            // and display the current values in the editor
            getLoaderManager().initLoader(EXISTING_EVENT_LOADER, null, this);
            mNameTextView = (TextView) findViewById(R.id.event_name_data);
            mVenueTextView = (TextView) findViewById(R.id.event_venue_data);
            mTimeTextView = (TextView) findViewById(R.id.event_time_data);
            mDateTextView = (TextView) findViewById(R.id.event_date_data);


        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.floatingActionButton:
                saveEvent();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void saveEvent() {
        ContentValues values = new ContentValues();
        values.put(EventEntry.COLUMN_EVENT_ID, EventEntry._ID );


// Insert the new row, returning the primary key value of the new row
        if(mcurrentEventUri==null)
        {
            Uri newUri = getContentResolver().insert(EventEntry.CONTENT_URI4, values);

            if (newUri == null) {
                Toast.makeText(this, getString(R.string.editor_insert_pet_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_insert_pet_successful),Toast.LENGTH_SHORT).show();
            }

        }
        else {
            int rowsAffected = getContentResolver().update(mcurrentEventUri, values, null, null);



            if (rowsAffected == 0)
            {
                Toast.makeText(this, getString(R.string.editor_update_pet_failed),Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, getString(R.string.editor_update_pet_successful),Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void home(View view)
    {
        Intent i = new Intent(this,PerformerProfile.class);
        startActivity(i);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection =
                {
                        EventEntry._ID,
                        EventEntry.COLUMN_NAME,
                        EventEntry.COLUMN_DATE,
                        EventEntry.COLUMN_VENUE,
                        EventEntry.COLUMN_TIME
                };
        return new CursorLoader(this, mcurrentEventUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
    {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            // Find the columns of pet attributes that we're interested in
            int nameColumnIndex = cursor.getColumnIndex(EventEntry.COLUMN_NAME);
            int dateColumnIndex = cursor.getColumnIndex(EventEntry.COLUMN_DATE);
            int venueColumnIndex = cursor.getColumnIndex(EventEntry.COLUMN_VENUE);
            int timeColumnIndex = cursor.getColumnIndex(EventEntry.COLUMN_TIME);

            // Extract out the value from the Cursor for the given column index
            String name = cursor.getString(nameColumnIndex);
            String date = cursor.getString(dateColumnIndex);
            String venue = cursor.getString(venueColumnIndex);
            int time = cursor.getInt(timeColumnIndex);

            // Update the views on the screen with the values from the database
            mNameTextView.setText(name);
            mDateTextView.setText(date);
            mTimeTextView.setText(Integer.toString(time));
            mVenueTextView.setText(venue);
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        mNameTextView.setText("");
        mDateTextView.setText("");
        mTimeTextView.setText("");
        mVenueSpinner.setSelection(0);
    }
}
