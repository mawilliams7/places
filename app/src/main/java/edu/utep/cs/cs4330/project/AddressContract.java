package edu.utep.cs.cs4330.project;

import android.provider.BaseColumns;

public final class AddressContract {
    private AddressContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "addresses";
        public static final String ID = "id";
        public static final String ADDRESS = "address";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String LONGITUDE = "longitude";
        public static final String LATITUDE = "latitude";
        public static final String DATE_ADDED = "date_added";
    }
}
