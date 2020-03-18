package edu.utep.cs.cs4330.project;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveAddressActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        TextView name = (TextView) findViewById(R.id.consent);
        Button remove = (Button) findViewById(R.id.confirmremove);
        Button cancel = (Button) findViewById(R.id.cancelremove);
        final String title = getIntent().getStringExtra("TITLE");
        final String id = getIntent().getStringExtra("ID");
        name.setText("Are you sure you want to delete " + title + "?");
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapsActivity.manager.removeAddressByID(id);
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
