package com.aidiapp.salonbike.kml;

import java.util.ArrayList;
import java.util.Iterator;

public class BikeStationDataSet { 

private ArrayList<BikeStation> placemarks = new ArrayList<BikeStation>();
private BikeStation currentPlacemark;
private BikeStation routePlacemark;

public String toString() {
    String s= "";
    for (Iterator<BikeStation> iter=placemarks.iterator();iter.hasNext();) {
        BikeStation p = (BikeStation)iter.next();
        s += p.getTitle() + "\n" + p.getDescription() + "\n\n";
    }
    return s;
}

public void addCurrentPlacemark() {
    placemarks.add(currentPlacemark);
}

public ArrayList<BikeStation> getPlacemarks() {
    return placemarks;
}

public void setPlacemarks(ArrayList<BikeStation> placemarks) {
    this.placemarks = placemarks;
}

public BikeStation getCurrentPlacemark() {
    return currentPlacemark;
}

public void setCurrentPlacemark(BikeStation currentPlacemark) {
    this.currentPlacemark = currentPlacemark;
}

public BikeStation getRoutePlacemark() {
    return routePlacemark;
}

public void setRoutePlacemark(BikeStation routePlacemark) {
    this.routePlacemark = routePlacemark;
}

}
