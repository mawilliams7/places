package edu.utep.cs.cs4330.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditAddressActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        String addressID = getIntent().getStringExtra("ID");
        final Address address = MapsActivity.manager.getAddressByID(addressID);
        Button edit = (Button) findViewById(R.id.acceptedit);
        Button cancel = (Button) findViewById(R.id.canceledit);
        EditText title = (EditText) findViewById(R.id.providedtitleedit);
        EditText description = (EditText) findViewById(R.id.provideddescriptionedit);
        title.setText(address.title);
        description.setText(address.description);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = (EditText) findViewById(R.id.providedtitleedit);
                EditText description = (EditText) findViewById(R.id.provideddescriptionedit);
                address.title = title.getText().toString();
                address.description = description.getText().toString();
                MapsActivity.manager.updateAddress(address);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
