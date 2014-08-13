package com.aidiapp.salonbike.kml;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
public class BikeLane {
private String title,description,coordinates;
private ArrayList<LatLng> puntos;
private float distancia;

public ArrayList<LatLng> coordinatesToPuntos(String coordinates){
	ArrayList<LatLng> pts=new ArrayList<LatLng>();
	
	
	return pts;
}
public BikeLane() {
	// TODO Auto-generated constructor stub
	this.puntos=new ArrayList<LatLng>();
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
public float getDistancia() {
	return distancia;
}
public void setDistancia(float distancia) {
	this.distancia = distancia;
}
public List<LatLng> getPuntos() {
	return puntos;
}
public void setPuntos(ArrayList<LatLng> puntos) {
	this.puntos = puntos;
}

public void addPunto(LatLng p){
	this.puntos.add(p);
}
public String getCoordinates() {
	return coordinates;
}
public void setCoordinates(String coordinates) {
	this.coordinates = coordinates;
}


}
