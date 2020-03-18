package edu.utep.cs.cs4330.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Geocoder;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import com.google.android.gms.vision.barcode.Barcode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddAddressActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        EditText text = findViewById(R.id.provideddate);
        text.setText(formatter.format(date));
    }

    public void onClick(View view){
        String item= getIntent().getStringExtra("ID");
        String itemID = getIntent().getStringExtra("ID");
        EditText title = (EditText) findViewById(R.id.providedtitle);
        EditText description = (EditText) findViewById(R.id.provideddescription);
        EditText actualAddress = (EditText) findViewById(R.id.providedaddress);
        EditText dateAdded = (EditText) findViewById(R.id.provideddate);
        final Address address = new Address();
        address.address = actualAddress.getText().toString();
        address.title = title.getText().toString();
        address.dateAdded = dateAdded.getText().toString();
        address.description = description.getText().toString();
        Geocoder coder = new Geocoder(this);
        List<android.location.Address> possible;
        try {
            possible = coder.getFromLocationName(address.address,5);
            if (address==null) {
                new AlertDialog.Builder(this)
                        .setTitle("AddressBook Notification")
                        .setMessage("Invalid address. Please try again.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return;
            }
            android.location.Address location= possible.get(0);
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            final CharSequence items[] = new CharSequence[possible.size()];
            final android.location.Address[] choices = new android.location.Address[possible.size()];
            int counter = 0;
            for (android.location.Address temp: possible) {
                items[counter] = temp.getAddressLine(0);
                choices[counter] = temp;
                counter++;
            }
            adb.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface d, int n) {
                    android.location.Address location= choices[n];
                    address.longitude = location.getLongitude();
                    address.latitude = location.getLatitude();
                    address.address = location.getAddressLine(0);
                    MapsActivity.manager.addAddress(address);
                    finish();
                }

            });
            adb.setNegativeButton("Cancel", null);
            adb.setTitle("Select address?");
            adb.show();
        }
        catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("AddressBook Notification")
                    .setMessage("Invalid address. Please try again.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
    }
}
