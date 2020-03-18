package edu.utep.cs.cs4330.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AddressContract.FeedEntry.TABLE_NAME + " (" +
                    AddressContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    AddressContract.FeedEntry.ID + " TEXT," +
                    AddressContract.FeedEntry.TITLE + " TEXT," +
                    AddressContract.FeedEntry.ADDRESS + " TEXT," +
                    AddressContract.FeedEntry.DESCRIPTION + " TEXT," +
                    AddressContract.FeedEntry.DATE_ADDED+ " TEXT," +
                    AddressContract.FeedEntry.LATITUDE + " TEXT," +
                    AddressContract.FeedEntry.LONGITUDE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AddressContract.FeedEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}