package com.cs7.chinmays.cicarv30.data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.sql.Time;
import java.util.Date;

public class EventContract {
    public static final String CONTENT_AUTHORITY = "com.cs7.chinmays.cicarv30";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_EVENTS = "event2";
    public static final String PATH_EVENTS2 = "venuee";
    public static final String PATH_EVENTS3 = "material";
    public static final String PATH_EVENTS4 = "Register";


    public static abstract class EventEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EVENTS);
        public static final Uri CONTENT_URI2 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EVENTS2);
        public static final Uri CONTENT_URI3 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EVENTS3);
        public static final Uri CONTENT_URI4 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EVENTS4);

        public static final String _ID = BaseColumns._ID;

        public static final String TABLE_NAME = "event2";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_VENUE = "venue";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String MATERIAL_NAME = "material_name";

        public static final String _ID1 = BaseColumns._ID;
        public static final String TABLE_NAME2 = "venuee";
        public static final String COLUMN_VENUE_NAME = "venue_name";
        public static final String COLUMN_VENUE_LOCATION = "location";
        public static final String COLUMN_VENUE_COST = "cost";

        public static final String _ID3 = BaseColumns._ID;
        public static final String TABLE_NAME3 = "material";
        public static final String COLUMN_MATERIAL_NAME = "venue_name";
        public static final String COLUMN_MATERIAL_QUANTITY = "location";
        public static final String COLUMN_MATERIAL_COST = "cost";

        public static final String _ID4 = BaseColumns._ID;
        public static final String TABLE_NAME4 = "Register";
        public static final String COLUMN_EVENT_ID = "event_id";
        public static final String COLUMN_EVENT_USERNAME = "user name";

        /**
         * Possible values for the venues of the event.
         */
        public static final int VENUE_1 = 0;
        public static final int VENUE_2 = 1;
        public static final int VENUE_3 = 2;

        public static boolean isValidVenue(int gender)
        {
            if (gender == VENUE_1 || gender == VENUE_2 || gender == VENUE_3) {

            }
            return true;
        }
    }
}











