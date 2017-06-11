package com.cs7.chinmays.cicarv30;

/**
 * Created by cs7 on 12/22/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.cs7.chinmays.cicarv30.data.EventContract.EventEntry;



public class MaterialCAdapter extends CursorAdapter{


    public MaterialCAdapter(Context context, Cursor c)
    {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)


    {
        return LayoutInflater.from(context).inflate(R.layout.list_item1, parent, false);


    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor)

    {

        TextView tvname = (TextView) view.findViewById(R.id.name);     //change name here
        TextView time = (TextView) view.findViewById(R.id.time);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(EventEntry.COLUMN_MATERIAL_NAME));
        int cost = cursor.getInt(cursor.getColumnIndexOrThrow(EventEntry.COLUMN_MATERIAL_COST));


        tvname.setText(name);
        time.setText(String.valueOf(cost));


    }
}

