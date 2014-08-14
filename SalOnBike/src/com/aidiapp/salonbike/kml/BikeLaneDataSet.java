package com.aidiapp.salonbike.kml;

import java.util.ArrayList;

public class BikeLaneDataSet {
private ArrayList<BikeLane> bikeLanesCollection=new ArrayList<BikeLane>();
private BikeLane currentBikeLane;

public void addBikeLane(BikeLane bl){
	this.getBikeLanesCollection().add(bl);
}

public ArrayList<BikeLane> getBikeLanesCollection() {
	return bikeLanesCollection;
}

public void setBikeLanesCollection(ArrayList<BikeLane> bikeLanesCollection) {
	this.bikeLanesCollection = bikeLanesCollection;
}

public BikeLane getCurrentBikeLane() {
	return currentBikeLane;
}

public void setCurrentBikeLane(BikeLane currentBikeLane) {
	this.currentBikeLane = currentBikeLane;
}

public void addCurrentBikeLane(){
	this.bikeLanesCollection.add(currentBikeLane);
}
}
