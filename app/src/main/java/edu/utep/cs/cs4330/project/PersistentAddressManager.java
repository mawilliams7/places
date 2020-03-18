package edu.utep.cs.cs4330.project;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Telephony;


public class PersistentAddressManager extends AddressManager {
    SQLiteDatabase write;
    SQLiteDatabase read;

    public PersistentAddressManager(FeedReaderDbHelper helper) {
        super();
        this.write = helper.getWritableDatabase();
        this.read = helper.getReadableDatabase();
    }

    public void loadDatabase() {
        Cursor cursor = read.rawQuery("select * from " + AddressContract.FeedEntry.TABLE_NAME ,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Address address = new Address();
                address.address = cursor.getString(cursor.getColumnIndex(AddressContract.FeedEntry.ADDRESS));
                address.description = cursor.getString(cursor.getColumnIndex(AddressContract.FeedEntry.DESCRIPTION));
                address.title = cursor.getString(cursor.getColumnIndex(AddressContract.FeedEntry.TITLE));
                address.dateAdded = cursor.getString(cursor.getColumnIndex(AddressContract.FeedEntry.DATE_ADDED));
                address.latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex(AddressContract.FeedEntry.LATITUDE)));
                address.longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex(AddressContract.FeedEntry.LONGITUDE)));
                super.addAddressWithExistingID(address, cursor.getString(cursor.getColumnIndex(AddressContract.FeedEntry.ID)));
                cursor.moveToNext();
            }
        }

    }

    @Override
    public void addAddress(Address address) {
        super.addAddress(address);
        ContentValues values = new ContentValues();
        values.put(AddressContract.FeedEntry.ID, getAddressID(address));
        values.put(AddressContract.FeedEntry.TITLE, address.title);
        values.put(AddressContract.FeedEntry.DESCRIPTION, address.description);
        values.put(AddressContract.FeedEntry.DATE_ADDED, address.dateAdded);
        values.put(AddressContract.FeedEntry.ADDRESS, address.address);
        values.put(AddressContract.FeedEntry.LATITUDE, Double.toString(address.latitude));
        values.put(AddressContract.FeedEntry.LONGITUDE, Double.toString(address.longitude));
        long newRowId = write.insert(AddressContract.FeedEntry.TABLE_NAME, null, values);
    }

    @Override
    public void removeAddress(Address address) {
        String selection = AddressContract.FeedEntry.ID + " LIKE ?";
        String[] selectionArgs = {getAddressID(address)};
        int deletedRows = write.delete(AddressContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
        super.removeAddress(address);
    }

    @Override
    public void removeAddressByID(String id) {
        String selection = AddressContract.FeedEntry.ID + " LIKE ?";
        String[] selectionArgs = {id};
        int deletedRows = write.delete(AddressContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
        super.removeAddressByID(id);
    }

    public void updateAddress(Address address) {
        ContentValues values = new ContentValues();
        values.put(AddressContract.FeedEntry.TITLE, address.title);
        values.put(AddressContract.FeedEntry.DESCRIPTION, address.description);
        String selection = AddressContract.FeedEntry.ID + " LIKE ?";
        String[] selectionArgs = {getAddressID(address)};

        int count = write.update(
                AddressContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}
