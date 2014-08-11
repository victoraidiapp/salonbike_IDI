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

import com.aidiapp.salonbike.kml.BikeStationDataSet;
import com.aidiapp.salonbike.kml.BikeStationSaxHandler;
import com.aidiapp.salonbike.kml.BikeStation;
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

/**
 * Esta clase se encarga de visualizar el mapview y dibujar en e´l los intercambiadores
 * @author aidiapp
 *
 */
public class MapManager extends MapFragment implements OnMarkerClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View r=super.onCreateView(inflater, container, savedInstanceState);
		Log.d("MAPMANAGER","Ya hemos creado el mapa");
		//Como listener de los clicks en los marcadres del mapa establezca el propio mapmanager
		this.getMap().setOnMarkerClickListener(this);
		//Inicialziamos el mapa
		this.init();
		return r;
	}
	
	/**
	 * Este método inicializa el mapa
	 */
public void init(){
	final GoogleMap gm=this.getMap();
	
	gm.setOnMapLoadedCallback(new OnMapLoadedCallback(){

		@Override
		/**
		 * Cuando el mapa ya está cargado realizamos una serie de operaciones
		 */
		public void onMapLoaded() {
			// TODO Auto-generated method stub
			Log.d("MAPMANAGER", "El mapa ya está cargado");
			gm.setMyLocationEnabled(true);
			
			//Establecemos la localización de salamanca
			LatLng sal=new LatLng(40.9705347,-5.6637995);
			
			//Movemos el mapa hasta esa localización
			gm.animateCamera(CameraUpdateFactory.newLatLngZoom(sal, 12.5f));
			
			//CArgamos los intercambiadores
			loadPoints();
		}
		
	});

	
	
}

/**
 * Este método dibuja los intercambiadores como marcadores en el mapa
 */
public void loadPoints(){
	GoogleMap gm=this.getMap();
	
	AssetManager assetManager =this.getActivity().getAssets();
	BikeStationDataSet navigationDataSet = null;
	try {
		InputStream is=assetManager.open("intercambiadores.xml");
		SAXParserFactory spf = SAXParserFactory.newInstance();
		   SAXParser sp= spf.newSAXParser();
		   XMLReader xr = sp.getXMLReader();
		   BikeStationSaxHandler navSax2Handler = new BikeStationSaxHandler(); 
	        xr.setContentHandler(navSax2Handler); 
	        InputSource inStream = new InputSource(is);
	        xr.parse(inStream);
	        navigationDataSet=navSax2Handler.getParsedData();
	        ArrayList<BikeStation> puntos=navigationDataSet.getPlacemarks();
	        Iterator i=puntos.iterator();
	        while(i.hasNext()){
	        	
	        	BikeStation punto=(BikeStation) i.next();
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


