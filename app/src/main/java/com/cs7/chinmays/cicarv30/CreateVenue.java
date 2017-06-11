



package com.cs7.chinmays.cicarv30;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.cs7.chinmays.cicarv30.data.EventContract.EventEntry;

public class CreateVenue extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private EditText mVenueNameEditText;
    private EditText mVenueLocationEditText;
    private EditText mVenueCostEditText;
    private static final int EVENT_LOADER = 0;
    private int mVenue = 0;
    private Uri mcurrentEventUri;
    EventCAdapter mCursorAdapter;
    private static final int EXISTING_EVENT_LOADER=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_venue);


        mVenueNameEditText = (EditText) findViewById(R.id.venue_name);
        mVenueLocationEditText = (EditText) findViewById(R.id.location);
        mVenueCostEditText = (EditText) findViewById(R.id.cost);

        Intent intent = getIntent();
        mcurrentEventUri = intent.getData();
        if (mcurrentEventUri == null) {
            // This is a new pet, so change the app bar to say "Add a Pet"
            setTitle("Add event");
        } else {
            // Otherwise this is an existing pet, so change app bar to say "Edit Pet"
            setTitle("Edit Event");

            // Initialize a loader to read the pet data from the database
            // and display the current values in the editor
            getLoaderManager().initLoader(EXISTING_EVENT_LOADER, null, this);
        }


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    private void saveEvent() {
        String event_name_string = mVenueNameEditText.getText().toString().trim();
        String event_time_string = mVenueLocationEditText.getText().toString().trim();
        String event_date_string = mVenueCostEditText.getText().toString().trim();


        ContentValues values = new ContentValues();
        values.put(EventEntry.COLUMN_VENUE_NAME, event_name_string);
        values.put(EventEntry.COLUMN_VENUE_COST, event_date_string);
        values.put(EventEntry.COLUMN_VENUE_LOCATION, event_time_string);
// Insert the new row, returning the primary key value of the new row
        if(mcurrentEventUri==null)
        {
            Uri newUri = getContentResolver().insert(EventEntry.CONTENT_URI2, values);

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

    private void deleteEvent() {
        // Only perform the delete if this is an existing pet.
        if ( mcurrentEventUri!= null) {
            // Call the ContentResolver to delete the pet at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentPetUri
            // content URI already identifies the pet that we want.
            int rowsDeleted = getContentResolver().delete(mcurrentEventUri, null, null);
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.editor_delete_pet_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_delete_pet_successful),
                        Toast.LENGTH_SHORT).show();
            }

        }
        finish();
    }

    private void showDeleteConfirmationDialog ()
    {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                deleteEvent();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Perform the deletion of the pet in the database.
     */




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                saveEvent();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection =
                {
                        EventEntry._ID1,
                        EventEntry.COLUMN_VENUE_NAME,
                        EventEntry.COLUMN_VENUE_LOCATION,
                        EventEntry.COLUMN_VENUE_COST,

                };
        return new CursorLoader(this, mcurrentEventUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
    {
        {

            if (cursor == null || cursor.getCount() < 1) {
                return;
            }
            if (cursor.moveToFirst()) {
                // Find the columns of pet attributes that we're interested in
                int nameColumnIndex = cursor.getColumnIndex(EventEntry.COLUMN_VENUE_NAME);
                int dateColumnIndex = cursor.getColumnIndex(EventEntry.COLUMN_VENUE_LOCATION);
                int venueColumnIndex = cursor.getColumnIndex(EventEntry.COLUMN_VENUE_COST);

                // Extract out the value from the Cursor for the given column index
                String name = cursor.getString(nameColumnIndex);
                String date = cursor.getString(dateColumnIndex);
                int venue = cursor.getInt(venueColumnIndex);

                // Update the views on the screen with the values from the database
                mVenueNameEditText.setText(name);
                mVenueLocationEditText.setText(date);
                mVenueCostEditText.setText(Integer.toString(venue));

            }
        }



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        mVenueNameEditText.setText("");
        mVenueLocationEditText.setText("");
        mVenueCostEditText.setText("");
    }

}