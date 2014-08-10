package com.aidiapp.salonbike;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.R;
import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aidiapp.salonbike.kml.NavigationDataSet;
import com.aidiapp.salonbike.kml.NavigationSaxHandler;
import com.aidiapp.salonbike.kml.Placemark;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapManager extends MapFragment implements OnMarkerClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View r=super.onCreateView(inflater, container, savedInstanceState);
		Log.d("MAPMANAGER","Ya hemos creado el mapa");
		this.getMap().setOnMarkerClickListener(this);
		this.goToYourLocation();
		return r;
	}
public void goToYourLocation(){
	final GoogleMap gm=this.getMap();
	gm.setOnMapLoadedCallback(new OnMapLoadedCallback(){

		@Override
		public void onMapLoaded() {
			// TODO Auto-generated method stub
			Log.d("MAPMANAGER", "El mapa ya está cargado");
			gm.setMyLocationEnabled(true);
			//gm.animateCamera(CameraUpdateFactory.zoomTo(15));
			LatLng sal=new LatLng(40.9705347,-5.6637995);
			gm.animateCamera(CameraUpdateFactory.newLatLngZoom(sal, 12.5f));
			//gm.animateCamera(CameraUpdateFactory.zoomTo(19));
			loadPoints();
		}
		
	});
	//MapsInitializer.initialize(getActivity());
	//gm.animateCamera(CameraUpdateFactory.zoomBy(8f));
	//gm.animateCamera(CameraUpdateFactory.zoomIn());
	
	
}
public void loadPoints(){
	GoogleMap gm=this.getMap();
	
	AssetManager assetManager =this.getActivity().getAssets();
	NavigationDataSet navigationDataSet = null;
	try {
		InputStream is=assetManager.open("intercambiadores.xml");
		SAXParserFactory spf = SAXParserFactory.newInstance();
		   SAXParser sp= spf.newSAXParser();
		   XMLReader xr = sp.getXMLReader();
		   NavigationSaxHandler navSax2Handler = new NavigationSaxHandler(); 
	        xr.setContentHandler(navSax2Handler); 
	        InputSource inStream = new InputSource(is);
	        xr.parse(inStream);
	        navigationDataSet=navSax2Handler.getParsedData();
	        ArrayList<Placemark> puntos=navigationDataSet.getPlacemarks();
	        Iterator i=puntos.iterator();
	        while(i.hasNext()){
	        	
	        	Placemark punto=(Placemark) i.next();
	        	/*
	        	Log.d("MAPMANAGER","El punto es "+punto.getTitle());
	        	String coord[]=punto.getCoordinates().split(",");
	        	Log.d("MAPMANAGER","Latitud: "+coord[1]+", Longitud: "+coord[0]);
	        	LatLng pos=new LatLng(Float.valueOf(coord[1]),Float.valueOf(coord[0]));
	        	Marker mrk=gm.addMarker(new MarkerOptions().position(pos).title(punto.getTitle()));
	        	*/
	        	gm.addMarker(new MarkerOptions().position(punto.getPosition()).title(punto.getTitle()));
	        }
	       // Log.d("MAPMANAGER","navigationDataSet: "+navigationDataSet.toString());
	        
	} catch (IOException e) {
		// TODO Auto-generated catch block
		Log.e("MAPMANAGER", "Error de entrada salida");
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		Log.e("MAPMANAGER", "Error en la configuración del parseador");
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		Log.e("MAPMANAGER", "Error en el parseador");
		e.printStackTrace();
	}
	
}

@Override
public boolean onMarkerClick(Marker marker) {
	// TODO Auto-generated method stub
	Log.d("MAPMANAGER","Has picado en el marcador con posicion "+marker.getPosition().toString());
	return false;
}	
}


