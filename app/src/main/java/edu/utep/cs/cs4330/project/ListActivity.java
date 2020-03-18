package edu.utep.cs.cs4330.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static edu.utep.cs.cs4330.project.MapsActivity.manager;

public class ListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    RecyclerView recyclerView;
    static RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    CustomAdapter customAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list;
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        customAdapter = new CustomAdapter(manager.getAllAddresses().toArray(new Address[manager.getAllAddresses().size()]), this);
        mAdapter = customAdapter;
        recyclerView.setAdapter(mAdapter);
        recyclerView.setVerticalScrollBarEnabled(true);
        toolbar = findViewById(R.id.list_toolbar);
        toolbar.inflateMenu(R.menu.list_menu);
        toolbar.setTitle("Address Book");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.option_address_map) {
                    finish();
                }
                else if (item.getItemId() == R.id.option_new_location_list) {
                    startActivity(new Intent("android.intent.action.AddAddressActivity"));
                }
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        customAdapter.setItems(manager.getAllAddresses().toArray(new Address[manager.getAllAddresses().size()]));
        mAdapter.notifyDataSetChanged();
    }
}
