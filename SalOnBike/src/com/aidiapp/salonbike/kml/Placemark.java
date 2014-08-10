package com.aidiapp.salonbike.kml;

import com.google.android.gms.maps.model.LatLng;

public class Placemark {

String title;
String description;
String coordinates;
private LatLng position;
String address;
public static LatLng coordinatesToPosition(String coords){
	String pos[]=coords.split(",");
	LatLng position=new LatLng(Float.valueOf(pos[1]),Float.valueOf(pos[0]));
	return position;
	
}
public String getTitle() {
    return title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public String getCoordinates() {
    return coordinates;
}
public void setCoordinates(String coordinates) {
    this.coordinates = coordinates;
}
public String getAddress() {
    return address;
}
public void setAddress(String address) {
    this.address = address;
}
public LatLng getPosition() {
	return position;
}
public void setPosition(LatLng position) {
	this.position = position;
}

}
