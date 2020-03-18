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
import java.util.Locale;

public class AddKnownAddressActivity extends AppCompatActivity {
    double latitude;
    double longitude;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        EditText text = findViewById(R.id.provideddate);
        text.setText(formatter.format(date));
        longitude = Double.parseDouble(getIntent().getStringExtra("LONGITUDE"));
        latitude = Double.parseDouble(getIntent().getStringExtra("LATITUDE"));
        EditText actualAddress = (EditText) findViewById(R.id.providedaddress);
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<android.location.Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 54
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            actualAddress.setText(address);
        }
        catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("AddressBook Notification")
                    .setMessage("Invalid address. Please try again.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            finish();
        }
    }

    public void onClick(View view) {
        EditText title = (EditText) findViewById(R.id.providedtitle);
        EditText description = (EditText) findViewById(R.id.provideddescription);
        EditText actualAddress = (EditText) findViewById(R.id.providedaddress);
        EditText dateAdded = (EditText) findViewById(R.id.provideddate);
        final Address address = new Address();
        address.address = actualAddress.getText().toString();
        address.title = title.getText().toString();
        address.dateAdded = dateAdded.getText().toString();
        address.description = description.getText().toString();
        address.longitude = longitude;
        address.latitude = latitude;
        MapsActivity.manager.addAddress(address);
        finish();
    }
}

