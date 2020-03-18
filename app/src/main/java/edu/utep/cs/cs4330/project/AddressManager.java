package edu.utep.cs.cs4330.project;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static edu.utep.cs.cs4330.project.MapsActivity.mMap;

public class AddressManager {
    /** The list of items in the item manager. */
    protected List<Address> addresses;
    protected List<MarkerOptions> markers;
    protected List<String> ids;

    /** Creates a new ItemManager. */
    public AddressManager() {
        this.addresses = new ArrayList<Address>();
        this.ids = new ArrayList<String>();
        this.markers = new ArrayList<MarkerOptions>();
    }

    /** Adds an item to the item manager.
     * @param address The item to be added
     * */
    public void addAddress(Address address) {
        this.addresses.add(address);
        Date date = new Date();
        long id = date.getTime();
        this.ids.add(Long.toString(id));
        MarkerOptions marker = new MarkerOptions();
        LatLng position = new LatLng(address.latitude, address.longitude);
        marker.position(position);
        marker.title(address.title + "\n" + "Description: " + address.description + "\n" + "Address: " + address.address);
        markers.add(marker);
    }

    public void addAddressWithExistingID(Address address, String id) {
        this.addresses.add(address);
        this.ids.add(id);
        MarkerOptions marker = new MarkerOptions();
        LatLng position = new LatLng(address.latitude, address.longitude);
        marker.position(position);
        marker.title(address.title + "\n" + "Description: " + address.description + "\n" + "Address: " + address.address);
        markers.add(marker);
    }

    public String getAddressID(Address address) {
        int index = this.addresses.indexOf(address);
        return this.ids.get(index);
    }

    public Address getAddressByID(String id) {
        int index = this.ids.indexOf(id);
        return this.addresses.get(index);
    }

    /** Removes an item to the item manager.
     * @param address The item to be removed
     * */
    public void removeAddress(Address address) {
        int index = this.addresses.indexOf(address);
        this.addresses.remove(index);
        this.ids.remove(index);
        this.markers.remove(index);
    }

    public void removeAddressByID(String id) {
        int index = this.ids.indexOf(id);
        this.addresses.remove(index);
        this.ids.remove(index);
        this.markers.remove(index);
    }

    /** Gets the complete list of items from the ItemManager.
     * @return The contents of the ItemManager list
     * */
    public List<Address> getAllAddresses(){
        return this.addresses;
    }
    public List<MarkerOptions> getAllMarkers(){
    return this.markers;
}
}

