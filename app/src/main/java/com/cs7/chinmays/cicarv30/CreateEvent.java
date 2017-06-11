



package com.cs7.chinmays.cicarv30;

import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.cs7.chinmays.cicarv30.data.EventContract.EventEntry;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.cs7.chinmays.cicarv30.R.id.date;
import static com.cs7.chinmays.cicarv30.R.id.view;

public class CreateEvent extends AppCompatActivity implements  LoaderManager.LoaderCallbacks<Cursor> {
    private EditText mNameEditText;
    private EditText mDateEditText;
    private EditText mTimeEditText;
    private ImageView mImageView;
    private EditText mDescriptionEditText;
    private Spinner mVenueSpinner;
    private int mVenue = 0;
    private Uri mcurrentEventUri;
    private static final int EXISTING_EVENT_LOADER=0;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

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

        mNameEditText = (EditText) findViewById(R.id.edit_event_name);
        mDateEditText = (EditText) findViewById(R.id.edit_event_date);
        mTimeEditText = (EditText) findViewById(R.id.edit_event_time);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mDescriptionEditText = (EditText) findViewById(R.id.edit_event_description);
        mVenueSpinner = (Spinner) findViewById(R.id.spinner_venue);


        setupSpinner();






    }





    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imageView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter venueSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_venue_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        venueSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mVenueSpinner.setAdapter(venueSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mVenueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.venue_1))) {
                        mVenue = EventEntry.VENUE_1; // Male
                    } else if (selection.equals(getString(R.string.venue_2))) {
                        mVenue = EventEntry.VENUE_2; // Female
                    } else {
                        mVenue = EventEntry.VENUE_3; // Unknown
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mVenue = EventEntry.VENUE_3;
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    private void saveEvent() {
        String event_name_string = mNameEditText.getText().toString().trim();
        String event_time_string = mTimeEditText.getText().toString().trim();
        String event_date_string = mDateEditText.getText().toString().trim();
        String event_desc_string = mDescriptionEditText.getText().toString().trim();


        ContentValues values = new ContentValues();
        values.put(EventEntry.COLUMN_NAME, event_name_string);
        values.put(EventEntry.COLUMN_DATE, event_date_string);
        values.put(EventEntry.COLUMN_VENUE, mVenue);
        values.put(EventEntry.COLUMN_TIME, event_time_string);
        values.put(EventEntry.COLUMN_DESCRIPTION,event_desc_string);
// Insert the new row, returning the primary key value of the new row
        if(mcurrentEventUri==null)
        {
            Uri newUri = getContentResolver().insert(EventEntry.CONTENT_URI, values);

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
                        EventEntry._ID,
                        EventEntry.COLUMN_NAME,
                        EventEntry.COLUMN_DATE,
                        EventEntry.COLUMN_VENUE,
                        EventEntry.COLUMN_TIME,
                        EventEntry.COLUMN_DESCRIPTION
                };
        return new CursorLoader(this, mcurrentEventUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
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
                int descColumnIndex = cursor.getColumnIndex(EventEntry.COLUMN_DESCRIPTION);
                // Extract out the value from the Cursor for the given column index
                String name = cursor.getString(nameColumnIndex);
                String date = cursor.getString(dateColumnIndex);
                String desc = cursor.getString(descColumnIndex);
                int venue = cursor.getInt(venueColumnIndex);
                int time = cursor.getInt(timeColumnIndex);

                // Update the views on the screen with the values from the database
                mNameEditText.setText(name);
                mDateEditText.setText(date);
                mTimeEditText.setText(Integer.toString(time));
                mDescriptionEditText.setText(desc);
                switch (venue) {
                    case EventEntry.VENUE_1:
                        mVenueSpinner.setSelection(0);
                        break;
                    case EventEntry.VENUE_2:
                        mVenueSpinner.setSelection(1);
                        break;
                    default:
                        mVenueSpinner.setSelection(2);
                        break;
                }


            }
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        {
            mNameEditText.setText("");
            mDateEditText.setText("");
            mTimeEditText.setText("");
            mDescriptionEditText.setText("");
            mVenueSpinner.setSelection(0); // Select "Unknown" gender
        }

    }
}