package edu.utep.cs.cs4330.project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;

import static edu.utep.cs.cs4330.project.MapsActivity.manager;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Address[] addresses;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    public CustomAdapter(Address[] addresses, Context context) {
        this.addresses = addresses;
        this.context = context;
    }

    public void setItems(Address [] items) {
        this.addresses = items;
    }

    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View view = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_listview, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final View view = holder.view;
        final Address address = addresses[position];
        final String id = manager.getAddressID(address);
        final String title = address.title;
        final TextView titleView = view.findViewById(R.id.title);
        final  TextView description = view.findViewById(R.id.description);
        final TextView dateAdded = view.findViewById(R.id.dateAdded);
        final TextView addressView = view.findViewById(R.id.address);
        final TextView latitude = view.findViewById(R.id.latitude);
        final TextView longitude = view.findViewById(R.id.longitude);
        Button remove = view.findViewById(R.id.removeaddress);
        Button edit = view.findViewById(R.id.editaddress);
        addressView.setText("Address: " + address.address);
        latitude.setText(String.format("Latitude: " + address.latitude));
        longitude.setText(String.format("Longitude: " + address.longitude));
        titleView.setText(title);
        description.setText("Description: " + address.description);
        dateAdded.setText("Date added: " + address.dateAdded);
        edit.setText("Edit");
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent("android.intent.action.EditAddressActivity");
                edit.putExtra("ID", id);
                ((Activity) view.getContext()).startActivity(edit);
            }
        });
        remove.setText("Remove Entry");
        remove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent remove = new Intent("android.intent.action.RemoveAddressActivity");
                remove.putExtra("TITLE", title);
                remove.putExtra("ID", id);
                ((Activity) view.getContext()).startActivity(remove);
            }
        });
        Button go = view.findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = "http://maps.google.com/maps?daddr=" + address.latitude + "," + address.longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(location));
                ((Activity) view.getContext()).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addresses.length;
    }

}
